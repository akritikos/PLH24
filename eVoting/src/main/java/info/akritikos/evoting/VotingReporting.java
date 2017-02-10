/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.akritikos.evoting;

import info.akritikos.evoting.model.*;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;
/**
 *
 * @author akritikos
 */
class VotingReporting {
	private final EntityManager em;

	ElectoralPeriphery per;
	List<PoliticalParty> parties = new ArrayList<>();

	VotingReporting() {
		em = EVoting.emf.createEntityManager();
		per = em.createNamedQuery("ElectoralPeriphery.findAll",ElectoralPeriphery.class).getSingleResult();
		// O Eclipse link provider den kanei override tis me8odous tou Vector
		// base class me apotelesma na epistrefei adeies listes sta sort, forEach
		// k.a. opote metatrepoume th lista apo IndirectList se aplo ArrayList
		// Epipleon ta IndirectList ston kwdika ginontai iterate me aplo for gia ayto to logo
		List<PoliticalParty> par = per.getPoliticalPartyList();
		for (PoliticalParty party : par){
			parties.add(party);
		}

	}

	void displayParties()
	{
		System.out.println("\n1. displayParties: Politika Kommata systhmatos EVoting");
		parties.sort(Comparator.comparing(PoliticalParty::getFldTitle));
		parties.forEach((p) -> {
			System.out.println("\t"+p.getFldTitle());
		});
	}

	void displayCandidates(){
		System.out.println("2. displayCandidates:");
		for (PoliticalParty party : parties) {
			List<Candidate> cand = party.getCandidateList();
			// vlepe sxolio ston constructor
			List<Candidate> candidates = new ArrayList<>();
			for (Candidate c : cand) candidates.add(c);

			candidates.sort((Candidate c1,Candidate c2)->{
				int surnameCheck = c1.getFldSurname().compareToIgnoreCase(c2.getFldSurname());
				if (surnameCheck == 0)
					return c1.getFldName().compareToIgnoreCase(c2.getFldName());
				return surnameCheck;
			});
			System.out.println("\tYpopshfioi kommatos " + party.getFldTitle() + ":");
			candidates.forEach((c) -> {
				System.out.println("\t\t"+c.getFldSurname() + ", "+c.getFldName());
			});
		}
	}

	void displayVotes(){
		System.out.println("3. displayVotes:");
		for (PoliticalParty party : parties){
			List<Candidate> cand = party.getCandidateList();
			// vlepe sxolio ston constructor
			List<Candidate> candidates = new ArrayList<>();
			for (Candidate c : cand) candidates.add(c);

			candidates.sort((c1, c2) -> c2.getVoteList().size() - c1.getVoteList().size());
			System.out.println("\tPshfoi ana vouleyth tou kommatos " + party.getFldTitle()+":");
			candidates.forEach((c) -> {
				System.out.println("\t\t" + c.getFldSurname() + ", " + c.getFldName() + "\t" + c.getVoteList().size());
			});
		}
	}

	void displayVoteStatistics(){
		System.out.println("4. displayVoteStatistics: Pososta pshfwn:");
		int eggegramenoi = per.getFldRegisteredCitizensCount();
		int pshfisan = per.getVoteList().size();
		int akyra = 0;
		int leyka = 0;
		int apoxi = (int) ((eggegramenoi - pshfisan)/(float)eggegramenoi*100);
		for (Vote v : per.getVoteList()){
			if (v.getFldIsBlank())
				leyka++;
			if (v.getFldIsInvalid())
				akyra++;
		}
		leyka = (int) (100*((float)leyka/eggegramenoi));
		akyra = (int) (100*((float)akyra/eggegramenoi));

		System.out.println("\tApoxh:\t"+apoxi+"%");
		System.out.println("\tLeyka:\t"+leyka+"%");
		System.out.println("\tAkyra:\t"+akyra+"%");
		parties.forEach((party) -> {
			int percent = (int) (party.getVoteList().size()/(float)eggegramenoi*100);
			System.out.println("\t\t"+party.getFldTitle()+": "+percent+"%");
		});
	}

	void displayElected(){
		List<Candidate> candidates = new ArrayList<>();
		System.out.println("5. displayElected: Eklegmenoi vouleytes sthn perifereia " + per.getFldName() + ":");
		parties.forEach((party) -> {
			for (Candidate c : party.getCandidateList()){
				candidates.add(c);
			}
		});
		// Den exei dw8ei tropos epiloghs se isopshfia
		candidates.sort((c1, c2) -> c2.getVoteList().size() - c1.getVoteList().size());
		for (int i = 0; i < 3; i++){
			Candidate elected = candidates.get(i);
			System.out.println("\t\t"+elected.getFkPoliticalPartyId().getFldTitle());
			System.out.println("\t\t\t"+elected.getFldSurname()+", "+elected.getFldName()+" : "+elected.getVoteList().size());
		}
	}

	void dispose(){
		em.close();
	}
}
