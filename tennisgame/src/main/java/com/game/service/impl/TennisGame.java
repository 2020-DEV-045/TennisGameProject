
package com.game.service.impl;

import com.game.model.Player;
import com.game.util.GameConstant;

/**
 * @author 2020-DEV-045
 *
 */
public class TennisGame {

	private Player firstPlayer;
	private Player secondPlayer;

	/*
	 * Method to valid player score input parameter.
	 */
	public boolean isValidScore(int playerOneScore, int playerTwoScore) {
		boolean validScore = true;
		if(playerOneScore < 0 || playerTwoScore < 0 
				|| (playerOneScore >= 3 && playerTwoScore >= 3 && Math.abs(playerOneScore - playerTwoScore) > 2)) {
			validScore=false;
		}
		return validScore;
	}

	/*
	 * Method to check player with highest score
	 */
	public String getLeadingScorer(Player firstPlayer, Player secondPlayer) {
		if (firstPlayer.getPlayerScore() > secondPlayer.getPlayerScore()) {
			return firstPlayer.getPlayerName();
		} else {
			return secondPlayer.getPlayerName();
		}
	}

	/*
	 * Method to check which player is winner
	 */
	public String checkForWinner(Player firstPlayer, Player secondPlayer) {
		if ((firstPlayer.getPlayerScore() >= 3 && firstPlayer.getPlayerScore() >= secondPlayer.getPlayerScore() + 2)) {
			return firstPlayer.getPlayerName()+GameConstant.WINS;

		} else if((secondPlayer.getPlayerScore() >= 3 && secondPlayer.getPlayerScore() >= firstPlayer.getPlayerScore() + 2)) {
			return secondPlayer.getPlayerName()+GameConstant.WINS;
		}
		return GameConstant.NO_PLAYER_WON;
	}

	/*
	 * Method to check whether player is advantage.
	 */

	public String checkForAdvantage(Player firstPlayer, Player secondPlayer) {

		if ((firstPlayer.getPlayerScore() >= 3 && firstPlayer.getPlayerScore() == secondPlayer.getPlayerScore() + 1)) {
			return firstPlayer.getPlayerName()+GameConstant.ADVANTAGE;
		}
		if((secondPlayer.getPlayerScore() >= 3 && secondPlayer.getPlayerScore() == firstPlayer.getPlayerScore() + 1)) {
			return secondPlayer.getPlayerName()+GameConstant.ADVANTAGE;
		}

		return GameConstant.PLAYER_NOT_IN_ADVANTAGE;

	}

	/*
	 * Method to check player is in deuce
	 */
	public boolean checkForDeuce(Player firstPlayer, Player secondPlayer) {
		boolean deuce = firstPlayer.getPlayerScore() >= 3
				&& secondPlayer.getPlayerScore() == firstPlayer.getPlayerScore();
		return deuce;
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

	/*
	 * Method to add score for a player
	 */
	public void addScore(Player player) {
		player.setPlayerScore(player.getPlayerScore() + 1);
	}

	/**
	 * @return the firstPlayer
	 */
	public Player getFirstPlayer() {
		return firstPlayer;
	}

	/**
	 * @param firstPlayer the firstPlayer to set
	 */
	public void setFirstPlayer(Player firstPlayer) {
		this.firstPlayer = firstPlayer;
	}

	/**
	 * @return the secondPlayer
	 */
	public Player getSecondPlayer() {
		return secondPlayer;
	}

	/**
	 * @param secondPlayer the secondPlayer to set
	 */
	public void setSecondPlayer(Player secondPlayer) {
		this.secondPlayer = secondPlayer;
	}

}
