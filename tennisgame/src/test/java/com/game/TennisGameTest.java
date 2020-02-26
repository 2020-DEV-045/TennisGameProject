package com.game;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

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
}
