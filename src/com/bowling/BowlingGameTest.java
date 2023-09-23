package com.bowling;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class BowlingGameTest {

	private BowlingGame game = new BowlingGame();
	
	//Player hasn't knocked down any pins in a frame.
	@Test
	void worstGame() {
		rollZeros(20);
		assertEquals(0, game.score());
	}
	
	//Player knocks down 1 pin in first roll
	@Test
	void onePin() {
		game.roll(1);
		rollZeros(19);
		assertEquals(1, game.score());
	}
	
	//Negative Scenario
	@Test
	void negativeRoll() {
		try {
			roll(11);
		}catch(IllegalArgumentException ie) {
			assertEquals("Illegal Argument", ie.getMessage());
		}
		
	}
	
	//Player hits a spare in the first frame and then knocks down 3 pins in the second frame
	@Test
	void oneSpare() {
		roll(5,5,1,2);
		rollZeros(16);
		assertEquals(11 + 3, game.score());
	}
	
	//Player hits a strike in the first frame and then knocks down 5 pins in the second frame
	@Test
	void oneStrike() {
		roll(10,0,2,3);
		rollZeros(16);
		assertEquals(15 + 5, game.score());
	}
	
	private void roll(int... pinsArray) {
		for(int pins : pinsArray) {
			game.roll(pins);
		}
	}
	
	private void rollZeros(int roll) {
		for(int i = 0;i < roll;i++) {
			game.roll(0);
		}
	}

}
