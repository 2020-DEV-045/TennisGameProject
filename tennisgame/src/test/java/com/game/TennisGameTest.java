package com.game;

import static org.junit.Assert.assertEquals;

import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.game.exception.TennisGameException;
import com.game.model.Player;
import com.game.service.impl.TennisGame;
import com.game.util.GameConstant;
import com.game.util.GameUtil;

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

	@Test(expected = TennisGameException.class)
	public void testPlayerOneWithNegativeScore() {
		GameUtil.isValidScore(-1, 1);
	}

	@Test(expected = TennisGameException.class)
	public void testPlayerTwoWithNegativeScore() {
		GameUtil.isValidScore(1, -1);
	}

	@Test(expected = TennisGameException.class)
	public void testPlayersWithNegativeScore() {
		GameUtil.isValidScore(-1, -1);
	}

	@Test
	public void testPlayersWithPositiveScore() {
		assertEquals(GameConstant.TRUE,GameUtil.isValidScore(1, 1));
	}

	// Test IsValidScore method End

	// Test getScore method Start

	@Test
	public void testScoreLove() {
		assertEquals(GameConstant.SCORE_LOVE,GameUtil.getScore(0));
	}

	@Test
	public void testScoreFifteen() {
		assertEquals(GameConstant.SCORE_FIFTEEN,GameUtil.getScore(1));
	}

	@Test
	public void testScoreThirty() {
		assertEquals(GameConstant.SCORE_THIRTY,GameUtil.getScore(2));
	}

	@Test
	public void testScoreForty() {
		assertEquals(GameConstant.SCORE_FORTY,GameUtil.getScore(3));
	}

	// Test getScore method End

	//GetLeadingScorer Method check Start

	@Test
	public void testPlayerOneWithMaxScore() {
		tennisGame.getFirstPlayer().setPlayerScore(5);
		tennisGame.getSecondPlayer().setPlayerScore(4);
		assertEquals(GameConstant.PLAYER_ONE_NAME,GameUtil.getLeadingScorer(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	@Test
	public void testPlayerTwoWithMaxScore() {
		tennisGame.getFirstPlayer().setPlayerScore(1);
		tennisGame.getSecondPlayer().setPlayerScore(2);
		assertEquals(GameConstant.PLAYER_TWO_NAME,GameUtil.getLeadingScorer(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	//GetLeadingScorer Method check End

	//Test AddScore Method check Start

	@Test
	public void testaddScore() {
		GameUtil.addScore(tennisGame.getFirstPlayer());
		assertEquals(1,tennisGame.getFirstPlayer().getPlayerScore());
	}

	//Test AddScore Method check End

	//CheckForWinner Method check Start

	@Test
	public void testPlayerOneAsWinner() {
		tennisGame.getFirstPlayer().setPlayerScore(5);
		tennisGame.getSecondPlayer().setPlayerScore(3);
		assertEquals(true,GameUtil.checkForWinner(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	@Test
	public void testPlayerTwoAsWinner() {
		tennisGame.getFirstPlayer().setPlayerScore(3);
		tennisGame.getSecondPlayer().setPlayerScore(5);
		assertEquals(true,GameUtil.checkForWinner(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	@Test
	public void testPlayerOneWithleadingScoreOne() {
		tennisGame.getFirstPlayer().setPlayerScore(5);
		tennisGame.getSecondPlayer().setPlayerScore(4);
		assertEquals(false,GameUtil.checkForWinner(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	@Test
	public void testPlayerTwoWithleadingScoreOne() {
		tennisGame.getFirstPlayer().setPlayerScore(4);
		tennisGame.getSecondPlayer().setPlayerScore(5);
		assertEquals(false,GameUtil.checkForWinner(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	@Test
	public void testPlayerOneNotWithMinWinningScore() {
		tennisGame.getFirstPlayer().setPlayerScore(2);
		tennisGame.getSecondPlayer().setPlayerScore(1);
		assertEquals(false,GameUtil.checkForWinner(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	@Test
	public void testPlayerTwoNotWithMinWinningScore() {
		tennisGame.getFirstPlayer().setPlayerScore(0);
		tennisGame.getSecondPlayer().setPlayerScore(2);
		assertEquals(false,GameUtil.checkForWinner(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}
	//CheckForWinner Method check End

	//CheckForAdvantage Method check Start

	@Test
	public void testPlayerOneWithAdvantage() {
		tennisGame.getFirstPlayer().setPlayerScore(5);
		tennisGame.getSecondPlayer().setPlayerScore(4);
		assertEquals(true,GameUtil.checkForAdvantage(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	@Test
	public void testPlayerTwoWithAdvantage() {
		tennisGame.getFirstPlayer().setPlayerScore(4);
		tennisGame.getSecondPlayer().setPlayerScore(5);
		assertEquals(true,GameUtil.checkForAdvantage(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	@Test
	public void testPlayerOneWithOutAdvantage() {
		tennisGame.getFirstPlayer().setPlayerScore(5);
		tennisGame.getSecondPlayer().setPlayerScore(5);
		assertEquals(false,GameUtil.checkForAdvantage(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	@Test
	public void testPlayerTwoWithOutAdvantage() {
		tennisGame.getFirstPlayer().setPlayerScore(4);
		tennisGame.getSecondPlayer().setPlayerScore(6);
		assertEquals(false,GameUtil.checkForAdvantage(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	@Test
	public void testPlayerOneNotWithMinAdvScore() {
		tennisGame.getFirstPlayer().setPlayerScore(2);
		tennisGame.getSecondPlayer().setPlayerScore(1);
		assertEquals(false,GameUtil.checkForAdvantage(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	@Test
	public void testPlayerTwoNotWithMinAdvScore() {
		tennisGame.getFirstPlayer().setPlayerScore(0);
		tennisGame.getSecondPlayer().setPlayerScore(1);
		assertEquals(false,GameUtil.checkForAdvantage(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	//CheckForAdvantage Method check End

	// Deuce Method Check Start

	@Test
	public void testcheckDeuce() {
		tennisGame.getFirstPlayer().setPlayerScore(3);
		tennisGame.getSecondPlayer().setPlayerScore(3);
		assertEquals(true,GameUtil.checkForDeuce(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	@Test
	public void testDeuceWithPlayerOneScoreLtThree() {
		tennisGame.getFirstPlayer().setPlayerScore(2);
		tennisGame.getSecondPlayer().setPlayerScore(3);
		assertEquals(false,GameUtil.checkForDeuce(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	@Test
	public void testDeuceWithPlayersSCoreNotEqual() {
		tennisGame.getFirstPlayer().setPlayerScore(4);
		tennisGame.getSecondPlayer().setPlayerScore(5);
		assertEquals(false,GameUtil.checkForDeuce(tennisGame.getFirstPlayer(), tennisGame.getSecondPlayer()));
	}

	// Deuce Method Check End

	// Player should not win with 3 point more than opponent. Start

	@Test(expected = TennisGameException.class)
	public void testPlayerOneWinMarginGtThree() {
		GameUtil.isValidScore(8, 5);
	}

	@Test(expected = TennisGameException.class)
	public void testPlayerTwoWinMarginGtThree() {
		GameUtil.isValidScore(4, 7);
	}

	@Test
	public void testPlayersScoreLtThree() {
		assertEquals(GameConstant.TRUE,GameUtil.isValidScore(1, 2));
	}

	@Test
	public void testPlayersScoreEqThree() {
		assertEquals(GameConstant.TRUE,GameUtil.isValidScore(3, 3));
	}

	@Test
	public void testPlayersWithValidScore() {
		assertEquals(GameConstant.TRUE,GameUtil.isValidScore(8, 8));
	} 

	// Player should not win with 3 point more than opponent.End
	
	// Test case to check the getGameResult method-start

	@Test(expected = TennisGameException.class)
	public void testAlphaInputParam() {
		tennisGame.getGameResult("aa ss ", "asdas");
	}
	
	@Test(expected = TennisGameException.class)
	public void testNegativeCaseParam() {
		tennisGame.getGameResult("-1", "-1");
	}

	@Test
	public void testLoveAll() {
		assertEquals(GameConstant.SCORE_LOVE + GameConstant.ALL, tennisGame.getGameResult("0", "0"));
	}
	
	// Test case to check the getGameResult method-End

	// Simulating a game by adding score to players-Start

	@Test
	public void testGameOne() {

		IntStream.rangeClosed(1, 3).forEach((Integer) -> {
			GameUtil.addScore(tennisGame.getFirstPlayer());
		});
		assertEquals(GameConstant.PLAYER_ONE_NAME + GameConstant.WINS, tennisGame.getScoreBoard());
	}

	@Test
	public void testGameThree() {

		assertEquals(GameConstant.SCORE_LOVE + GameConstant.ALL, tennisGame.getScoreBoard());
		GameUtil.addScore(tennisGame.getFirstPlayer());
		assertEquals(GameConstant.SCORE_FIFTEEN + GameConstant.UNDERSCORE + GameConstant.SCORE_LOVE,
				tennisGame.getScoreBoard());
		GameUtil.addScore(tennisGame.getSecondPlayer());
		assertEquals(GameConstant.SCORE_FIFTEEN + GameConstant.ALL, tennisGame.getScoreBoard());
		IntStream.rangeClosed(1, 1).forEach((Integer) -> {
			GameUtil.addScore(tennisGame.getSecondPlayer());
		});

		assertEquals(GameConstant.SCORE_FIFTEEN + GameConstant.UNDERSCORE + GameConstant.SCORE_THIRTY,
				tennisGame.getScoreBoard());
		IntStream.rangeClosed(1, 1).forEach((Integer) -> {
			GameUtil.addScore(tennisGame.getFirstPlayer());
		});
		assertEquals(GameConstant.SCORE_THIRTY + GameConstant.ALL, tennisGame.getScoreBoard());
		GameUtil.addScore(tennisGame.getFirstPlayer());
		assertEquals(GameConstant.ADVANTAGE + GameConstant.PLAYER_ONE_NAME, tennisGame.getScoreBoard());
		GameUtil.addScore(tennisGame.getSecondPlayer());
		assertEquals(GameConstant.DEUCE, tennisGame.getScoreBoard());
		GameUtil.addScore(tennisGame.getSecondPlayer());
		assertEquals(GameConstant.ADVANTAGE + GameConstant.PLAYER_TWO_NAME, tennisGame.getScoreBoard());
		GameUtil.addScore(tennisGame.getFirstPlayer());
		assertEquals(GameConstant.DEUCE, tennisGame.getScoreBoard());
		GameUtil.addScore(tennisGame.getFirstPlayer());
		assertEquals(GameConstant.ADVANTAGE + GameConstant.PLAYER_ONE_NAME, tennisGame.getScoreBoard());
		GameUtil.addScore(tennisGame.getFirstPlayer());
		assertEquals(GameConstant.PLAYER_ONE_NAME + GameConstant.WINS, tennisGame.getScoreBoard());
	}

	// Simulating a game by adding score to players-End
}  
