package com.game.util;

import com.game.exception.TennisGameException;
import com.game.model.Player;

public class GameUtil {
	
	
	/*
	 * Method to create object for player model
	 */
	public static Player setPlayerInfo(int playerScore, String playerName) {

		Player player = new Player();
		player.setPlayerName(playerName);
		player.setPlayerScore(playerScore);
		return player;
	}


	/*
	 * Method to valid player score input parameter.
	 */
	public static boolean isValidScore(int pOneScore, int pTwoScore) {

		boolean validScore = true;

		if (pOneScore < 0 || pTwoScore < 0
				|| (pOneScore >= 3 && pTwoScore >= 3 && Math.abs(pOneScore - pTwoScore) > 2)) {
			throw new TennisGameException(GameConstant.SCORE_EXCEPTION);
		}

		return validScore;
	}


	/*
	 * Method to check player is in deuce
	 */

	public static boolean checkForDeuce(Player firstPlayer, Player secondPlayer) {
		boolean deuce = firstPlayer.getPlayerScore() >= 3
				&& secondPlayer.getPlayerScore() == firstPlayer.getPlayerScore();
		return deuce;
	}

	/*
	 * Method to check player with highest score
	 */

	public static String getLeadingScorer(Player firstPlayer, Player secondPlayer) {
		if (firstPlayer.getPlayerScore() > secondPlayer.getPlayerScore()) {
			return firstPlayer.getPlayerName();
		} else {
			return secondPlayer.getPlayerName();
		}
	}

	/*
	 * Method to check which player is winner
	 */
	public static boolean checkForWinner(Player firstPlayer, Player secondPlayer) {
		if ((secondPlayer.getPlayerScore() >= 3 && secondPlayer.getPlayerScore() >= firstPlayer.getPlayerScore() + 2)
				|| (firstPlayer.getPlayerScore() >= 3
				&& firstPlayer.getPlayerScore() >= secondPlayer.getPlayerScore() + 2)) {
			return true;
		}
		return false;
	}

	/*
	 * Method to check whether player is advantage.
	 */
	public static boolean checkForAdvantage(Player firstPlayer, Player secondPlayer) {

		if ((firstPlayer.getPlayerScore() >= 3 && firstPlayer.getPlayerScore() == secondPlayer.getPlayerScore() + 1)
				|| (secondPlayer.getPlayerScore() >= 3
				&& secondPlayer.getPlayerScore() == firstPlayer.getPlayerScore() + 1)) {
			return true;
		}

		return false;

	}

	/*
	 * Method to add score for a player
	 */
	public static void addScore(Player player) {
		player.setPlayerScore(player.getPlayerScore() + 1);
	}

	/*
	 *  Method to return score in words
	 */

	public static String getScore(int score) {
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
