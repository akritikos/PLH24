package info.akritikos.eelections;

import info.akritikos.eelections.contracts.IDBEntities;
import info.akritikos.eelections.contracts.ISearchQueries;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper for the persistence layer, handles CRUD operations with generic types
 * on entities implementing the IDBEntities marker interface. Capable of quering
 * with parameter-oriented queries through the use of ISearchQueries marker interface.
 *
 * Currently used with package-private access modifier
 */
public class DataManager {
	// For the purposes of this project, EntityManagerFactory can be maintained for the entire
	// application cycle without the need to close it.
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("eElectionsDB");
	private static EntityManager em;

        /**
         * Simplified parameterless constructor, defaults to "eElectionsDB"
         * for the Persistence Unit.
         */
	public DataManager() {
            this("eElectionsDB");
	}
        
        /**
         * 
         * @param PUnit Name of the persistence unit to connect to.
         */
        public DataManager(String PUnit){
            try {
                emf = Persistence.createEntityManagerFactory(PUnit);
                checkEM();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
        }
        
        /**
         * Internal use, starts a new EntityManager if the current one 
         * is closed or invalid.
         */
        private void checkEM(){
            if (em == null || !em.isOpen())
                em = emf.createEntityManager();
        }

	/**
	 * Retrieves all records of a selected type from persistence
	 *
	 * @param type T.class to be returned
	 * @param <T>
	 * @return
	 */
	public <T> List<T> retrieveAll(Class<? extends IDBEntities> type) {
		System.out.println(type.getSimpleName());
		List<T> result = new ArrayList<>();
		try {
                        checkEM();
			Query q = em.createNamedQuery(type.getSimpleName() + ".findAll", type);
			result = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			// No records to return
			return null;
		}
		// Dealing with EclipseLink Vector issues (Sorts/forEach in IndirectLists don't work)
		List<T> output = new ArrayList<>();
		for (T r : result) {
			output.add(r);
		}
		return output;
	}

	/**
	 * Finds a specific subset of entries via named queries, returns a List
	 * even for single results
	 *
	 * @param type  T.class to be returned
	 * @param query Enum identifying the named query to be accessed
	 * @param val   Value being searched for
	 * @param <T>
	 * @return
	 */
	public <T> List<T> find(Class<? extends IDBEntities> type, ISearchQueries query, String val) {
		// Nulls are not allowed on queryable fields
		if (val == null) return null;

		// While the query name requires the first character in uppercase, the
		// actual parameter name is lowercase
		char first = Character.toLowerCase(query.toString().charAt(0));
		String parameter = first + query.toString().substring(1);

		Query q = em.createNamedQuery(type.getSimpleName() + ".findBy" + query.toString());
		// Queries accept either strings or integers as parameter, try to parse into
		// integers and failing that we pass the parameter as a string
		// Try to convert passed value to integer:
		try {
			int intVal = Integer.parseInt(val);
			q.setParameter(parameter, intVal);
		}
		// Value was a string:
		catch (NumberFormatException e) {
			q.setParameter(parameter, val);
		}

		List<T> result = new ArrayList<>();
		try {
                        checkEM();
			result = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			// No records to return
			return null;
		}
		// Dealing with EclipseLink Vector issues (Sorts/forEach in IndirectLists don't work)
		List<T> output = new ArrayList<>();
		for (T r : result) {
			output.add(r);
		}
		return output;
	}

	/**
	 * Inserts a new element into persistence or updates the values
	 * of an existing one
	 *
	 * @param element Element to be updated/inserted
	 * @param <T>
	 */
	public <T extends IDBEntities> void saveChanges(T element) {
		// Don't enter nulls into DB
		if (element != null) {
			try {
                            checkEM();
                            em.getTransaction().begin();
                            em.persist(element);
                            em.getTransaction().commit();
			} catch (Exception e) {
                            e.printStackTrace();
                            em.getTransaction().rollback();
			}
		}
	}

	/**
	 * Removes a specific element from persistence
	 *
	 * @param element The element to be removed
	 * @param <T>
	 */
	public <T extends IDBEntities> void delete(T element) {
		// Don't try to delete null entries
		if (element != null) {
			try {
                            checkEM();
                            em.getTransaction().begin();
                            em.merge(element);
                            em.remove(element);
                            em.getTransaction().commit();
			} catch (Exception e) {
                            e.printStackTrace();
                            em.getTransaction().rollback();
			}
		}
	}
}