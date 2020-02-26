
package com.game.service.impl;

import com.game.util.GameConstant;

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
	
	/*
	 *  Method to return score in words
	 */
	
	public String getScore(int score) {
		switch (score) {
		case 3:
			return GameConstant.SCORE_FORTY;
		case 2:
			return GameConstant.SCORE_THIRTY;
		case 1:
			return GameConstant.SCORE_FIFTEEN;
		default:
			return GameConstant.SCORE_LOVE;
		}
	}
	
}
