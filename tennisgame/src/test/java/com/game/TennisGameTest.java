package com.game;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.game.model.Player;
import com.game.service.impl.TennisGame;
import com.game.util.GameConstant;

@SpringBootTest
public class TennisGameTest {

	@InjectMocks
	TennisGame tennisGame;

	public TennisGameTest() {
	}

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		tennisGame.setFirstPlayer(new Player());
		tennisGame.setSecondPlayer(new Player());
		tennisGame.getFirstPlayer().setPlayerName(GameConstant.PLAYER_ONE_NAME);
		tennisGame.getSecondPlayer().setPlayerName(GameConstant.PLAYER_TWO_NAME);
	}

	// Test IsValidScore method Start

	@Test
	public void testPlayerOneWithNegativeScore() {
		assertEquals(GameConstant.FALSE,tennisGame.isValidScore(-1, 1));
	}

	@Test
	public void testPlayerTwoWithNegativeScore() {
		assertEquals(GameConstant.FALSE,tennisGame.isValidScore(1, -1));
	}

	@Test
	public void testPlayersWithNegativeScore() {
		assertEquals(GameConstant.FALSE,tennisGame.isValidScore(-1, -1));
	}

	@Test
	public void testPlayersWithPositiveScore() {
		assertEquals(GameConstant.TRUE,tennisGame.isValidScore(1, 1));
	}

	// Test IsValidScore method End

	// Test getScore method Start

	@Test
	public void testScoreLove() {
		assertEquals(GameConstant.SCORE_LOVE,tennisGame.getScore(0));
	}

	@Test
	public void testScoreFifteen() {
		assertEquals(GameConstant.SCORE_FIFTEEN,tennisGame.getScore(1));
	}

	@Test
	public void testScoreThirty() {
		assertEquals(GameConstant.SCORE_THIRTY,tennisGame.getScore(2));
	}

	@Test
	public void testScoreForty() {
		assertEquals(GameConstant.SCORE_FORTY,tennisGame.getScore(3));
	}

	// Test getScore method End

	//GetLeadingScorer Method check Start

	@Test
	public void testPlayerOneWithMaxScore() {
		tennisGame.getFirstPlayer().setPlayerScore(5);
		tennisGame.getSecondPlayer().setPlayerScore(4);
		assertEquals(GameConstant.PLAYER_ONE_NAME,tennisGame.getLeadingScorer(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	@Test
	public void testPlayerTwoWithMaxScore() {
		tennisGame.getFirstPlayer().setPlayerScore(1);
		tennisGame.getSecondPlayer().setPlayerScore(2);
		assertEquals(GameConstant.PLAYER_TWO_NAME,tennisGame.getLeadingScorer(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	//GetLeadingScorer Method check End

	//Test AddScore Method check Start

	@Test
	public void testaddScore() {
		tennisGame.addScore(tennisGame.getFirstPlayer());
		assertEquals(1,tennisGame.getFirstPlayer().getPlayerScore());
	}

	//Test AddScore Method check End

	//CheckForWinner Method check Start

	@Test
	public void testPlayerOneAsWinner() {
		tennisGame.getFirstPlayer().setPlayerScore(5);
		tennisGame.getSecondPlayer().setPlayerScore(3);
		assertEquals(GameConstant.PLAYER_ONE_NAME+GameConstant.WINS ,tennisGame.checkForWinner(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	@Test
	public void testPlayerTwoAsWinner() {
		tennisGame.getFirstPlayer().setPlayerScore(3);
		tennisGame.getSecondPlayer().setPlayerScore(5);
		assertEquals(GameConstant.PLAYER_TWO_NAME+GameConstant.WINS ,tennisGame.checkForWinner(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	@Test
	public void testPlayerOneWithleadingScoreOne() {
		tennisGame.getFirstPlayer().setPlayerScore(5);
		tennisGame.getSecondPlayer().setPlayerScore(4);
		assertEquals(GameConstant.NO_PLAYER_WON,tennisGame.checkForWinner(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	@Test
	public void testPlayerTwoWithleadingScoreOne() {
		tennisGame.getFirstPlayer().setPlayerScore(4);
		tennisGame.getSecondPlayer().setPlayerScore(5);
		assertEquals(GameConstant.NO_PLAYER_WON,tennisGame.checkForWinner(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	@Test
	public void testPlayerOneNotWithMinWinningScore() {
		tennisGame.getFirstPlayer().setPlayerScore(2);
		tennisGame.getSecondPlayer().setPlayerScore(1);
		assertEquals(GameConstant.NO_PLAYER_WON,tennisGame.checkForWinner(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	@Test
	public void testPlayerTwoNotWithMinWinningScore() {
		tennisGame.getFirstPlayer().setPlayerScore(0);
		tennisGame.getSecondPlayer().setPlayerScore(2);
		assertEquals(GameConstant.NO_PLAYER_WON,tennisGame.checkForWinner(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	//CheckForWinner Method check End

	//CheckForAdvantage Method check Start

	@Test
	public void testPlayerOneWithAdvantage() {
		tennisGame.getFirstPlayer().setPlayerScore(5);
		tennisGame.getSecondPlayer().setPlayerScore(4);
		assertEquals(tennisGame.getFirstPlayer().getPlayerName()+GameConstant.ADVANTAGE ,tennisGame.checkForAdvantage(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	@Test
	public void testPlayerTwoWithAdvantage() {
		tennisGame.getFirstPlayer().setPlayerScore(4);
		tennisGame.getSecondPlayer().setPlayerScore(5);
		assertEquals(tennisGame.getSecondPlayer().getPlayerName()+GameConstant.ADVANTAGE,tennisGame.checkForAdvantage(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	@Test
	public void testPlayerOneWithOutAdvantage() {
		tennisGame.getFirstPlayer().setPlayerScore(5);
		tennisGame.getSecondPlayer().setPlayerScore(5);
		assertEquals(GameConstant.PLAYER_NOT_IN_ADVANTAGE,tennisGame.checkForAdvantage(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	@Test
	public void testPlayerTwoWithOutAdvantage() {
		tennisGame.getFirstPlayer().setPlayerScore(4);
		tennisGame.getSecondPlayer().setPlayerScore(6);
		assertEquals(GameConstant.PLAYER_NOT_IN_ADVANTAGE,tennisGame.checkForAdvantage(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	@Test
	public void testPlayerOneNotWithMinAdvScore() {
		tennisGame.getFirstPlayer().setPlayerScore(2);
		tennisGame.getSecondPlayer().setPlayerScore(1);
		assertEquals(GameConstant.PLAYER_NOT_IN_ADVANTAGE,tennisGame.checkForAdvantage(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	@Test
	public void testPlayerTwoNotWithMinAdvScore() {
		tennisGame.getFirstPlayer().setPlayerScore(0);
		tennisGame.getSecondPlayer().setPlayerScore(1);
		assertEquals(GameConstant.PLAYER_NOT_IN_ADVANTAGE,tennisGame.checkForAdvantage(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	//CheckForAdvantage Method check End

	// Deuce Method Check Start

	@Test
	public void testcheckDeuce() {
		tennisGame.getFirstPlayer().setPlayerScore(3);
		tennisGame.getSecondPlayer().setPlayerScore(3);
		assertEquals(true,tennisGame.checkForDeuce(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	@Test
	public void testDeuceWithPlayerOneScoreLtThree() {
		tennisGame.getFirstPlayer().setPlayerScore(2);
		tennisGame.getSecondPlayer().setPlayerScore(3);
		assertEquals(false,tennisGame.checkForDeuce(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	@Test
	public void testDeuceWithPlayersSCoreNotEqual() {
		tennisGame.getFirstPlayer().setPlayerScore(4);
		tennisGame.getSecondPlayer().setPlayerScore(5);
		assertEquals(false,tennisGame.checkForDeuce(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	// Deuce Method Check End
}  
