package info.akritikos.eelections.utils;

import info.akritikos.eelections.contracts.*;
import info.akritikos.eelections.model.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.eclipse.persistence.exceptions.DatabaseException;

/**
 * Helper for the persistence layer, handles CRUD operations with generic types
 * on entities implementing the IDBEntities marker interface. Capable of quering
 * with parameter-oriented queries through the use of ISearchQueries marker interface.
 * Created by akritikos on 11/03/2017.
 */
public class DataManager {
	private static EntityManagerFactory emf;
	private static String PUnit;
	private static final ThreadLocal<EntityManager> threadLocal = new ThreadLocal<>();

	/**
	 * Simplified parameterless constructor, defaults to "eElectionsDB"
	 * for the Persistence Unit unless it has already been instantiated
	 * with another PU parameter.
	 */
	public DataManager() {
		this(PUnit == null ? "eElectionsDB" : PUnit);
	}

	/**
	 * Creates a new DataManager binding to the Persistence Unit provided
	 * @param PUnit The name of the Persistence Unit to use
	 */
	public DataManager(String PUnit) throws PersistenceException {
		DataManager.PUnit = PUnit;
		if (emf == null || !emf.isOpen())
			emf = Persistence.createEntityManagerFactory(PUnit);
	}

	/**
	 * EntityManager helper to help with threading
	 * @return
	 */
	private EntityManager getEM() {
		if (emf == null || !emf.isOpen())
			emf = Persistence.createEntityManagerFactory(PUnit);
		EntityManager em = threadLocal.get();
		if (em == null || !em.isOpen()) {
			em = emf.createEntityManager();
			threadLocal.set(em);
		}
		return em;
	}

	/**
	 * Retrieves all records of a specified type from the Persistence Unit
	 * @param type T.class to be returned
	 * @param <T> Generic object implementic IDBEntities
	 * @return ObservableList containing result
	 */
	public <T extends IDBEntities> List<T> retrieveAll(Class<? extends IDBEntities> type) {
		if (type == null)
			return new ArrayList<>();
		List<T> result;
		EntityManager em = getEM();
		try {
			Query q = em.createNamedQuery(type.getSimpleName() + ".findAll", type);
			result = q.getResultList();
		} catch (Exception ex) {
			// No records exist
			return null;
		} finally {
			em.close();
		}
		// Dealing with EclipseLink Vector overriding issues (iterators don't work properly)
		List<T> output = new ArrayList<>();
		output.addAll(result);
		return output;
	}

	/**
	 * Finds a specific subset of entries via named queries and returns it
	 * as an ObservableList
	 * @param type T.class to be returned
	 * @param query Enum identifying the named query to be used
	 * @param val Value to search for
	 * @param <T> Generic object implementic IDBEntities
	 * @return ObservableList containing result
	 */
	public <T extends IDBEntities> List<T> find(Class<? extends IDBEntities> type,
			ISearchQueries query, String val) {
		// Transient search is meaningless
		if (val == null)
			return null;
		// Query name must begin in uppercase, yet the actual parameter name starts with lowercase
		char first = Character.toLowerCase(query.toString().charAt(0));
		String parameter = first + query.toString().substring(1);
		EntityManager em = getEM();
		Query q = em.createNamedQuery(type.getSimpleName() + ".findBy" + query.toString());
		// value to search for can by either int or String
		// Try parsing as int:
		try {
			int intVal = Integer.parseInt(val);
			q.setParameter(parameter, intVal);
		}
		// Value was a String:
		catch (NumberFormatException ex) {
			q.setParameter(parameter, val);
		}

		List<T> result;
		try {
			result = q.getResultList();
		} catch (Exception ex) {
			return null;
		} finally {
			em.close();
		}
		// Dealing with EclipseLink Vector overriding issues (iterators don't work properly)
		List<T> output = new ArrayList<>();
		output.addAll(result);
		return output;
	}

	/**
	 * Inserts a new element into persistence or updates the values
	 * of an existing record
	 * @param element Element to be updated/inserted
	 * @param <T>
	 */
	public <T extends IDBEntities> void saveChanges(T element) {
		// No transient records allowed
		if (element == null)
			return;
		EntityManager em = getEM();
		try {
			em.getTransaction().begin();
			try {
				// If ID is set, this is an existing element
				if (element.getID() != null)
					em.merge(element);
			} catch (NullPointerException ex) {
				em.persist(element);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
			em.close();
		} finally {
			em.close();
		}
	}

	/**
	 * Saves an array of entities into persistence by inserting new and
	 * updating existing ones
	 * @param elements
	 * @param <T>
	 */
	public <T extends IDBEntities> void massSave(List<? extends IDBEntities> elements) {
		if (elements == null)
			return;
		EntityManager em = getEM();
		try {
			em.getTransaction().begin();
			for (IDBEntities element : elements) {
				if (element != null) {
					try {
						if (element.getID() != null)
							em.merge(element);
					} catch (NullPointerException ex) {
						em.persist(element);
					}
				}
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	/**
	 * Removes an element from persistence
	 * @param element Element to be removed
	 * @param <T>
	 */
	public <T extends IDBEntities> void delete(T element) {
		if (element == null)
			return;
		EntityManager em = getEM();
		try {
			em.getTransaction().begin();
			// Entity must be attached
			if (!em.contains(element))
				element = em.merge(element);
			em.remove(element);
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	/**
	 * Deletes a list of entities from persistence
	 * @param elements
	 * @param <T>
	 */
	public <T extends IDBEntities> void massDelete(List<? extends IDBEntities> elements) {
		if (elements == null)
			return;
		EntityManager em = getEM();
		try {
			em.getTransaction().begin();
			for (IDBEntities element : elements) {
				if (element == null)
					continue;
				if (!em.contains(element))
					element = em.merge(element);
				em.remove(element);
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	/**
	 * Clears all records for the entity types provided
	 * @param types
	 * @param <T>
	 */
	public <T extends IDBEntities> void clearEntities(List<Class<? extends IDBEntities>> types) {
		if (types == null)
			return;
		EntityManager em = getEM();
		try {
			em.getTransaction().begin();
			for (Class<? extends IDBEntities> type : types) {
				em.createQuery("DELETE FROM " + type.getSimpleName()).executeUpdate();
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			em.getTransaction().rollback();
		} finally {
			em.close();
		}
	}

	/**
	 *
	 * @param fkCandidateId
	 * @param fkPoliticalPartyId
	 * @param fkElectoralPeripheryId
	 * @param type
	 * @return
	 */
	@SuppressWarnings("empty-statement")
	public int countVotes(Candidate fkCandidateId, PoliticalParty fkPoliticalPartyId,
						  ElectoralPeriphery fkElectoralPeripheryId, Class<? extends IDBEntities> type) {
		EntityManager em = getEM();
		int result = 0;
		final String toQuery = "Vote.Count";
		Query q;
		if (type == null) {
			q = em.createNamedQuery(toQuery);
		} else if (type == Candidate.class) {
			q = em.createNamedQuery(toQuery + "Candidate");
			q.setParameter("fkCandidateId", fkCandidateId);
		} else if (type == PoliticalParty.class && fkElectoralPeripheryId == null) {
			q = em.createNamedQuery(toQuery + "Party");
			q.setParameter("fkPoliticalPartyId", fkPoliticalPartyId);
		} else if (type == PoliticalParty.class) {
			q = em.createNamedQuery(toQuery + "PartyPeriphery");
			q.setParameter("fkPoliticalPartyId", fkPoliticalPartyId);
			q.setParameter("fkElectoralPeripheryId", fkElectoralPeripheryId);
		} else {
			q = em.createNamedQuery(toQuery + "Periphery");
			q.setParameter("fkElectoralPeripheryId", fkElectoralPeripheryId);
		}
		try {
			result = Integer.parseInt(q.getSingleResult().toString());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			em.close();
		}
		return result;
	}

	public int erroneousVotes(boolean fldIsBlank, boolean fldisInvalid, ElectoralPeriphery fkElectoralPeripheryId, PoliticalParty fkPoliticalPartyId) {
		EntityManager em = getEM();
		int result = 0;
		final String toQuery = "Vote.CountErroneous";
		Query q;
		if (fkPoliticalPartyId != null) {
			q = em.createNamedQuery(toQuery + "Full");
			q.setParameter("fkPoliticalPartyId", fkPoliticalPartyId);

		} else if (fkElectoralPeripheryId != null) {
			q = em.createNamedQuery(toQuery + "Periphery");
			q.setParameter("fkElectoralPeripheryId", fkElectoralPeripheryId);
		} else {
			q = em.createNamedQuery(toQuery);
		}
		q.setParameter("fldIsBlank", fldIsBlank);
		q.setParameter("fldIsInvalid", fldisInvalid);
		try {
			result = Integer.parseInt(q.getSingleResult().toString());
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			em.close();
		}
		return result;
	}
}
