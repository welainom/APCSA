	
public class YahtzeeScoreCard {
	private int[] scores;
	private final int NUM_SCORES = 13;
	private final int NUM_DICE = 5;
	
	public YahtzeeScoreCard() {
		scores = new int[NUM_SCORES];
		for (int i = 0; i<NUM_SCORES; i++) {
			scores[i] = -1;
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

	public int getScore(int type) return scores[type - 1];

	/**
	 *  Change the scorecard based on the category choice 1-13.
	 *
	 *  @param choice The choice of the player 1 to 13
	 *  @param dg  The DiceGroup to score
	 *  @return  true if change succeeded. Returns false if choice already taken.
	 */
	public boolean changeScore(int choice, DiceGroup dg) {
		if (scores[choice - 1] == -1 && (1 <= choice && choice <= 13)) {
			switch(choice):
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
		else return false;
	}
	
	/**
	 *  Change the scorecard for a number score 1 to 6
	 *
	 *  @param choice The choice of the player 1 to 6
	 *  @param dg  The DiceGroup to score
	 */
	public void numberScore(int choice, DiceGroup dg) {
		int totalScore = 0;
		for (Dice d : dg.getDie()) {
			if (d.getValue == choice) totalScore += choice;
		}
		return choice;
	}
	
	/**
	 *	Updates the scorecard for Three Of A Kind choice.
	 *
	 *	@param dg	The DiceGroup to score
	 */	
	public void threeOfAKind(DiceGroup dg) {}
	
	public void fourOfAKind(DiceGroup dg) {}
	
	public void fullHouse(DiceGroup dg) {}
	
	public void smallStraight(DiceGroup dg) {}
	
	public void largeStraight(DiceGroup dg) {}
	
	public void chance(DiceGroup dg) {}
	
	public void yahtzeeScore(DiceGroup dg) {}
}
