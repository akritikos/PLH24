package info.akritikos.evoting;

import info.akritikos.evoting.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by akritikos on 09/02/17.
 */
public class EVoting {
	public static EntityManagerFactory emf;
	private static EntityManager em;

	private static int rand(int min, int max){
		return new Random().nextInt(max - min +1);
	}

	private static void resetDB(){
		List<String> queries = new ArrayList<>(Arrays.asList("Vote","Candidate","PoliticalParty","ElectoralPeriphery"));
		try{
			em.getTransaction().begin();
			queries.forEach(s->{
				em.createQuery("DELETE FROM ".concat(s)).executeUpdate();
			});
			em.getTransaction().commit();
		}
		catch(Exception e){
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	public static void main(String[] args){
		emf = Persistence.createEntityManagerFactory("eVotingPU");
		em = emf.createEntityManager();
		resetDB();

		ElectoralPeriphery chania = new ElectoralPeriphery("Chania",50);

		List<PoliticalParty> parties = new ArrayList<>();
		parties.add(new PoliticalParty("PASOK","Fofi Gennimata",chania));
		parties.add(new PoliticalParty("Nea Dimokratia","Vangelis Meimarakis",chania));

		List<Candidate> cP = new ArrayList<>();
		List<Candidate> cND = new ArrayList<>();

		cP.add(new Candidate("Spyros","Danellhs",parties.get(0)));
		cP.add(new Candidate("Dhmhtrhs","Droutsas",parties.get(0)));
		cP.add(new Candidate("Xrysoula","Paliadelh",parties.get(0)));
		cP.add(new Candidate("Sylvana","Rapth",parties.get(0)));
		cP.add(new Candidate("Giwrgos","Stayrakakhs",parties.get(0)));

		cND.add(new Candidate("Marietta","Giannakou",parties.get(1)));
		cND.add(new Candidate("Giwrgos","Koumoutsakos",parties.get(1)));
		cND.add(new Candidate("Iwannhs","Tsoukalas",parties.get(1)));
		cND.add(new Candidate("Giwrgos","Papanikolaou",parties.get(1)));
		cND.add(new Candidate("Kwstas","Poupakhs",parties.get(1)));

		parties.get(0).setCandidateList(cP);
		parties.get(1).setCandidateList(cND);

		int pshfoi_apoxh = rand(0,3);
		int pshfoi_leyka = rand(0,10);
		int pshfoi_akyra = rand(0,5);

		List<Vote> votes = new ArrayList<>();
		for (int i = 0; i < pshfoi_leyka; i++){
			Vote v = new Vote(false, true, chania);
			votes.add(v);
		}
		for (int i = 0; i < pshfoi_akyra; i++){
			Vote v = new Vote(true, false, chania);
			votes.add(v);
		}
		for (int i = 0; i < chania.getFldRegisteredCitizensCount() - pshfoi_akyra - pshfoi_apoxh - pshfoi_leyka; i++){
			int p = rand(0, 1);
			int c = rand(0, 4);
			Vote v = new Vote(parties.get(p), parties.get(p).getCandidateList().get(c),chania);
		}

		try {
			em.getTransaction().begin();

			em.persist(chania);
			parties.forEach(p->{em.persist(p);});
			cP.forEach(c->{em.persist(c);});
			cND.forEach(c->{em.persist(c);});
			votes.forEach(v->{em.persist(v);});

			em.getTransaction().commit();
		}
		catch (Exception e){
			e.printStackTrace();
			em.getTransaction().rollback();
		}
		finally {
			em.close();
		}

		VotingReporting stats = new VotingReporting();
		stats.displayParties();
		stats.displayCandidates();
		stats.displayVotes();
		stats.displayVoteStatistics();
		stats.displayElected();
		stats.dispose();

		emf.close();
	}
}
