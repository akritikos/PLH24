package info.akritikos.championship;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by akritikos on 09/02/17.
 */
public class Championship {
	private static List<FootballTeam> football = new ArrayList<>();
	private static List<BasketballTeam> basketball = new ArrayList<>();

	public static void main(String[] args){
//		New teams
		football.add(new FootballTeam("Olympiakos FC",4));
		football.add(new FootballTeam("AEK FC",4));
		football.add(new FootballTeam("PAO FC",4));
		GenerateGames(football,10);

		basketball.add(new BasketballTeam("PAO BC", 4));
		basketball.add(new BasketballTeam("PAOK BC", 4));
		basketball.add(new BasketballTeam("ARIS BC", 4));
		GenerateGames(basketball,140);

//		Basic data dump
		System.out.println("Football teams:");
		football.forEach(f->{
			System.out.println(f.toString());
		});
		System.out.println("Basketball teams:");
		basketball.forEach(b->{
			System.out.println(b.toString());
		});

		// Calculate champions:
		calcFootballChampion(football);
		calcBasketballChampion(basketball);
		printSorted(football,"Football championship results:");
		printSorted(basketball,"Basketball championship results:");
	}

	private static void calcChampion(List<? extends Team> team, String header){
		int max = -1;
		Team champion = null;
		for (Team t : team){
			if (t.calcPoints() > max){
				max = t.calcPoints();
				champion = t;
			}
		}
		System.out.println(header + champion.getName());
	}

	private static void calcFootballChampion(List<FootballTeam> teams){
		calcChampion(teams,"Football Champion: ");
	}
	private static void calcBasketballChampion(List<BasketballTeam> teams){
		calcChampion(teams,"Basketball Champion: ");
	}

	private static void printSorted(List<? extends Team> team, String header){
		team.sort((Team t1, Team t2) ->
				t2.calcPoints() - t1.calcPoints());
		System.out.println(header);
		team.forEach(System.out::println);
	}
	private static void GenerateGames(List<? extends Team> team, int max){
		Random rand = new Random();
		team.forEach(t1->{
			team.forEach(t2->{
				int scoreHome = rand.nextInt(max);
				int scoreGuest = rand.nextInt(max);

				if (t1 != t2){
					Game g = new Game(t1,t2,scoreHome,scoreGuest);
					t1.AddGame(g);
					t2.AddGame(g);
				}
			});
		});
	}
}
