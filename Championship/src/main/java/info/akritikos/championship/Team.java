/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.akritikos.championship;

import java.util.Arrays;

/**
 *
 * @author akritikos
 */
public abstract class Team {
    // Source provided fields:
	private String name;
    private int maxNumOfGames;
    protected int curNumOfGames;
    protected Game[] teamGames;

    private Integer points;

    public Team(String name, int maxNumOfGames){
        this.name = name;
        this.maxNumOfGames = maxNumOfGames;
        teamGames = new Game[maxNumOfGames];
        curNumOfGames = 0;
    }
    
    void AddGame(Game g){
    	// Checking if g is a valid game for this team
    	if (this != g.getTeamGuest() && this != g.getTeamHome()){
		    System.out.println("You can't add a game that this team isn't participating in!");
		    return;
	    }
	    if (curNumOfGames == maxNumOfGames){
    		System.out.println("You've reached the maximum number of games!");
		    return;
	    }
	    // Only one game can exist for each teamHome & teamGuest combination
	    if (curNumOfGames>0){
	    	boolean duplicate = Arrays.stream(teamGames).anyMatch(
	    			game -> game!=null &&
						    game.getTeamHome() == g.getTeamHome() &&
						    game.getTeamGuest() == g.getTeamGuest()
		    );
	    	if (duplicate) {
	    		System.out.println("The game you're trying to add already exists!");
	    		return;
		    }
	    }

	    teamGames[curNumOfGames] = g;
	    curNumOfGames++;
		// New game has been added, cached points are now invalid
	    points = null;
    }

	/**
	 * Generic calcPoints for compatibility, should be overridden  by children
	 * @return
	 */
	protected int calcPoints(){
    	return 0;
    }

    protected int calcPoints(int victory, int defeat, int draw){
		// If cached value exists, skip execution and return it
		if (points != null)
			return points;

		int total = 0;
		for (Game g : teamGames){
			if (g == null)
				break;

			int myScore = this==g.getTeamHome() ? g.getScoreHome() : g.getScoreGuest();
			int otScore = this==g.getTeamHome() ? g.getScoreGuest() : g.getScoreHome();

			if (myScore > otScore)
				total+=victory;
			else if (myScore == otScore)
				total+=draw;
			else
				total+=defeat;
		}
		// Caching result for future calls
		points = total;
		return total;
    }

    @Override
	public String toString(){
    	int p = points == null ? calcPoints() : points;
    	return "Name: " + name + ", Added games: " + curNumOfGames + ", total score: " + points;
    }

    public void setName(String name){
		this.name = name;
    }
    public String getName(){
    	return name;
    }
}
