
package com.game.service.impl;

/**
 * @author 2020-DEV-045
 *
 */
public class TennisGame {
	
	/*
	 * Method to valid player score input parameter.
	 */
	public boolean isValidScore(int playerOneScore, int playerTwoScore) {
		boolean validScore = true;
		if(playerOneScore < 0 || playerTwoScore < 0) {
			validScore=false;
		}
		return validScore;
	}
}
