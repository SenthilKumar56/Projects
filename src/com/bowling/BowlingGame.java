package com.bowling;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BowlingGame {

	private List<Integer> rolls = new ArrayList<>();

	public void roll(int pins) {
		if (pins > 10 || pins < 0) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		rolls.add(pins);
	}

	public int calculateScore() {
		List<Frame> frames = buildFrames();
		int score = 0;
		int rollsIndex = 0;
		for (int i = 0; i <= frames.size() - 1; i++) {
			Frame currentFrame = frames.get(i);
			score += currentFrame.baseScore();
			if (currentFrame.isStrike()) {
				if (rolls.get(rollsIndex + 2) == 10) {
					score += rolls.get(rollsIndex + 2) + rolls.get(rollsIndex + 4);
				} else {
					score += rolls.get(rollsIndex + 2) + rolls.get(rollsIndex + 3);
				}
			} else if (currentFrame.isSpare()) {
				score += rolls.get(rollsIndex + 2);
			}
			rollsIndex += 2;
		}
		return score;
	}

	private List<Frame> buildFrames() {
		return IntStream.range(0, rolls.size() / 2).limit(10)
				.mapToObj(i -> new Frame(rolls.get(i * 2), rolls.get(i * 2 + 1))).collect(Collectors.toList());
	}

}
