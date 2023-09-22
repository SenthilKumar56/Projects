package com.bowling;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
	
	
	private List<Integer> rolls = new ArrayList<Integer>();
	
	public void roll(int pins) {
		if(pins > 10 || pins < 0) {
			throw new IllegalArgumentException("Illegal Argument");
		}
		rolls.add(pins);
	}
	
	public int score() {
		List<Frame> frames = buildFrames();
		List<Integer> scores = new ArrayList<Integer>();
		for(int i = 0; i < frames.size() - 1; i++) {
			Frame frame = frames.get(i);
			if(frame.isSpare()) {
				scores.add(frame.baseScore()+frames.get(i+1).getRoll1());
			}else if(frame.isStrike()) {
					scores.add(frame.baseScore()+frames.get(i+1).baseScore());	
			}else {
				scores.add(frame.baseScore());
			}
		}
		int totalScore = scores.stream().mapToInt(i->i).sum();
		return totalScore;
	}
	
	private List<Frame> buildFrames(){
		List<Frame> frames = new ArrayList<Frame>();
		for(int i = 0;i < rolls.size();i++) {
			if(rolls.get(i) == 10) {
				frames.add(new Frame(10,0));
			}else {
			    frames.add(new Frame(rolls.get(i),rolls.get(i+1)));
			}
			i++;
		}
		return frames;
	}

}
