/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.akritikos.championship;

import java.security.InvalidParameterException;

/**
 *
 * @author akritikos
 */
public class Game {
    private Team teamHome;
    private Team teamGuest;
    private int scoreHome;
    private int scoreGuest;

	/**
	 * Default constructor for games, accepts two teams and their respective scores
	 */
    public Game(Team teamHome, Team teamGuest, int scoreHome, int scoreGuest){
        if (teamHome == teamGuest){
            throw new InvalidParameterException("The provided teams should be different!");
        }
        if (scoreHome < 0 || scoreGuest < 0){
	        throw new InvalidParameterException("Score can't be negative!");
        }

        this.teamGuest = teamGuest;
        this.teamHome = teamHome;
        this.scoreHome = scoreHome;
        this.scoreGuest = scoreGuest;
    }

    public void setTeamHome(Team teamHome){
		this.teamHome = teamHome;
    }
	public Team getTeamHome(){
		return teamHome;
	}

    public void setTeamGuest(Team teamGuest){
		this.teamGuest = teamGuest;
    }
	public Team getTeamGuest(){
		return teamGuest;
	}

	public void setScoreHome(int scoreHome){
		this.scoreHome = scoreHome;
	}
	public int getScoreHome(){
		return scoreHome;
	}

	public void setScoreGuest(int scoreGuest){
		this.scoreGuest = scoreGuest;
	}
	public int getScoreGuest(){
		return scoreGuest;
	}
}
