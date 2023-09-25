package com.bowling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

class BowlingGameTest {

	private BowlingGame game = new BowlingGame();

	// Player hasn't knocked down any pins in a frame.
	@Test
	void worstGame() {
		rollZeros(20);
		assertEquals(0, game.calculateScore());
	}

	// Player knocks down 1 pin in first roll
	@Test
	void onePin() {
		game.roll(1);
		rollZeros(19);
		assertEquals(1, game.calculateScore());
	}

	// Negative Scenario
	@Test
	void negativeRoll() {
		try {
			roll(11);
		} catch (IllegalArgumentException ie) {
			assertEquals("Illegal Argument", ie.getMessage());
		}

	}

	// Player hits a spare in the first frame and then knocks down 3 pins in the
	// second frame
	@Test
	void oneSpare() {
		roll(5, 5, 1, 2);
		rollZeros(16);
		assertEquals(11 + 3, game.calculateScore());
	}

	// Player hits a strike in the first frame and then knocks down 5 pins in the
	// second frame
	@Test
	void oneStrike() {
		roll(10, 0, 2, 3);
		rollZeros(16);
		assertEquals(15 + 5, game.calculateScore());
	}

	// Player hits spare in all frame
	@Test
	void allSpare() {
		roll(9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9);
		assertEquals(19 + 19 + 19 + 19 + 19 + 19 + 19 + 19 + 19 + 19, game.calculateScore());
	}

	// Player hits strike in all frame
	@Test
	void allStrike() {
		roll(10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0);
		assertEquals(30 + 30 + 30 + 30 + 30 + 30 + 30 + 30 + 30 + 30, game.calculateScore());
	}

	private void roll(int... pinsArray) {
		IntStream.of(pinsArray).forEach(pins -> game.roll(pins));
	}

	private void rollZeros(int roll) {
		IntStream.range(0, roll).forEach(i -> game.roll(0));
	}

}
