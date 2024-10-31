
public class YahtzeeScoreCard {
	private int[] scores;
	private boolean[] scored;
	private final int NUM_SCORES = 13;
	private final int NUM_DICE = 5;
	
	public YahtzeeScoreCard() {
		scores = new int[NUM_SCORES];
		scored = new boolean[NUM_SCORES];
		for (int i = 0; i<NUM_SCORES; i++) {
			scores[i] = -1;
			scored[i] = false;
		}
	}
	
	/**
	 *  Print the scorecard header
	 */
	public void printCardHeader() {
		System.out.println("\n");
		System.out.printf("\t\t\t\t\t       3of  4of  Fll Smll Lrg\n");
		System.out.printf("  NAME\t\t  1    2    3    4    5    6   Knd  Knd  Hse " +
						"Strt Strt Chnc Ytz!\n");
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}
	
	/**
	 *  Prints the player's score
	 */
	public void printPlayerScore(YahtzeePlayer player) {
		System.out.printf("| %-12s |", player.getName());
		for (int i = 1; i < 14; i++) {
			if (getScore(i) > -1)
				System.out.printf(" %2d |", getScore(i));
			else System.out.printf("    |");
		}
		System.out.println();
		System.out.printf("+----------------------------------------------------" +
						"---------------------------+\n");
	}

	public int getScore(int type) {return scores[type - 1];}

	public boolean beenScored(int type) {return scored[type - 1];}

	/**
	 *  Change the scorecard based on the category choice 1-13.
	 *
	 *  @param choice The choice of the player 1 to 13
	 *  @param dg  The DiceGroup to score
	 *  @return  true if change succeeded. Returns false if choice already taken.
	 */
	public boolean changeScore(int choice, DiceGroup dg) {
		if (scores[choice - 1] == -1 && (1 <= choice && choice <= 13)) {
			switch(choice) {
				case 7:
					threeOfAKind(dg);
					break;
				case 8:
					fourOfAKind(dg);
					break;
				case 9:
					fullHouse(dg);
					break;
				case 10: 
					smallStraight(dg);
					break;
				case 11:
					largeStraight(dg);
					break;
				case 12:
					chance(dg);
					break;
				case 13:
					yahtzeeScore(dg);
					break;
				default:
					numberScore(choice, dg);
					break;
			}
			return true;
		}
		return false;
	}
	
	/**
	 *  Change the scorecard for a number score 1 to 6
	 *
	 *  @param choice The choice of the player 1 to 6
	 *  @param dg  The DiceGroup to score
	 */
	public void numberScore(int choice, DiceGroup dg) {
		int totalScore = 0;
		for (Dice d : dg.getDice()) {
			if (d.getValue() == choice) totalScore += choice;
		}
		scores[choice - 1] = totalScore;
		scored[choice - 1] = true;
	}
	
	/**
	 *	Updates the scorecard for Three Of A Kind choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void threeOfAKind(DiceGroup dg) {
		int totalScore = 0;
		boolean works = false;
		for (int i = 1; i<=6; i++) {
			int numMatching = 0;
			for (Dice d : dg.getDice()) {
				if (d.getValue() == i) numMatching++;
			}
			if (numMatching >= 3) {
				works = true;
				break;
			}
		}
		for (Dice d : dg.getDice()) {
			totalScore += d.getValue();
		}
		if (works) scores[6] = totalScore;
		else scores[6] = 0;
		scored[6] = true;
	}
	
	public void fourOfAKind(DiceGroup dg) {
		int totalScore = 0;
		boolean works = false;
		for (int i = 1; i<=6; i++) {
			int numMatching = 0;
			for (Dice d : dg.getDice()) {
				if (d.getValue() == i) numMatching++;
			}
			if (numMatching >= 4) {
				works = true;
				break;
			}
		}
		for (Dice d : dg.getDice()) {
			totalScore += d.getValue();
		}
		if (works) scores[7] = totalScore;
		else scores[7] = 0;
		scored[7] = true;
	}
	
	public void fullHouse(DiceGroup dg) {
        int[] counts = new int[6];
        for (Dice d : dg.getDice()) {
            counts[d.getValue() - 1]++;
        }

        boolean containsThree = false;
        boolean containsTwo = false;
        for (int count : counts) {
            if (count == 3) containsThree = true;
            if (count == 2) containsTwo = true;
        }
        if (containsThree && containsTwo) scores[8] = 25;
        else scores[8] = 0;
        scored[8] = true;
    }
	
	public void smallStraight(DiceGroup dg) {
        int[] counts = new int[6];
        for (Dice d : dg.getDice()) {
            counts[d.getValue() - 1] = 1;
        }

        boolean works = hasConsecutiveSequence(counts, 4);
        scores[9] = works ? 30 : 0;
        scored[9] = true;
    }

	
	public void largeStraight(DiceGroup dg) {
        int[] counts = new int[6];
        for (Dice d : dg.getDice()) {
            counts[d.getValue() - 1] = 1;
        }

        boolean works = hasConsecutiveSequence(counts, 5);
        scores[10] = works ? 40 : 0;
        scored[10] = true;
    }

	private boolean hasConsecutiveSequence(int[] counts, int length) {
        int consecutiveCount = 0;
        for (int count : counts) {
            if (count == 1) {
                consecutiveCount++;
                if (consecutiveCount >= length) return true;
            } else {
                consecutiveCount = 0;
            }
        }
        return false;
    }
	
	public void chance(DiceGroup dg) {
		int totalScore = 0;
		
		for (Dice d : dg.getDice()) totalScore += d.getValue();
		
		scores[11] = totalScore;
		scored[11] = true;
	}
	
	public void yahtzeeScore(DiceGroup dg) {
		int first = dg.getDice()[0].getValue();
		boolean works = true;
		
		for (Dice d : dg.getDice()) if (d.getValue() != first) works = false;
		
		if (works) scores[12] = 50;
		else scores[12] = 0; 
		scored[12] = true;
	}
}
