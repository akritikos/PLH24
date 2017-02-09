package info.akritikos.championship;

/**
 * Created by akritikos on 08/02/17.
 */
public class BasketballTeam extends Team {
	// points on each game result:
	private static final int VICTORY  = 2;
	private static final int DRAW = 0;
	private static final int DEFEAT = 1;

	public BasketballTeam(String name, int maxNumOfGames){
		super(name,maxNumOfGames);
	}

	@Override
	public int calcPoints(){
		return calcPoints(VICTORY,DRAW,DEFEAT);
	}
}
