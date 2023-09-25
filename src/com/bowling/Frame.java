package com.bowling;

public class Frame {

	private final int roll1;
	private final int roll2;

	Frame(int roll1, int roll2) {
		this.roll1 = roll1;
		this.roll2 = roll2;
	}

	public int baseScore() {
		return roll1 + roll2;
	}

	public boolean isSpare() {
		return roll1 + roll2 == 10 && roll2 != 0;
	}

	public boolean isStrike() {
		return roll1 == 10 && roll2 == 0;
	}

}
