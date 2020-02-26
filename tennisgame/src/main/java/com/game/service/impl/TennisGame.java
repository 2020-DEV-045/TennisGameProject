
package com.game.service.impl;

import com.game.model.Player;
import com.game.util.GameConstant;
import com.game.util.GameUtil;

/**
 * @author 2020-DEV-045
 *
 */
public class TennisGame {

	private Player firstPlayer;
	private Player secondPlayer;

	
	public String getScoreBoard() {

		if (GameUtil.checkForDeuce(firstPlayer, secondPlayer)) {
			return GameConstant.DEUCE;
		}

		if (GameUtil.checkForAdvantage(firstPlayer, secondPlayer)) {
			return GameConstant.ADVANTAGE + GameUtil.getLeadingScorer(firstPlayer, secondPlayer);
		}

		if (GameUtil.checkForWinner(firstPlayer, secondPlayer)) {
			return GameUtil.getLeadingScorer(firstPlayer, secondPlayer) + GameConstant.WINS;
		}

		if (firstPlayer.getPlayerScore() == secondPlayer.getPlayerScore()) {
			return GameUtil.getScore(secondPlayer.getPlayerScore()) + GameConstant.ALL;
		}
		return GameUtil.getScore(firstPlayer.getPlayerScore()) + GameConstant.UNDERSCORE
				+ GameUtil.getScore(secondPlayer.getPlayerScore());
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
