package info.akritikos.championship;

/**
 * Created by akritikos on 08/02/17.
 */
public class FootballTeam extends Team {
	// points on each game result:
	private static final int VICTORY  = 3;
	private static final int DRAW = 1;
	private static final int DEFEAT = 0;

	public FootballTeam(String name, int maxNumOfGames){
		super(name,maxNumOfGames);
	}

	@Override
	public int calcPoints(){
		return calcPoints(VICTORY,DRAW,DEFEAT);
	}
}
