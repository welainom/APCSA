import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;

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

	public int getScore(int type) {return scores[type - 1];}

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
	}
	
	public void fullHouse(DiceGroup dg) {
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (Dice d : dg.getDice()) {
			int value = d.getValue();
			if (map.containsKey(value)) {
				map.put(value, map.get(value) + 1);
			}
			else map.put(value, 1);
		}
		
		boolean containsThree = false;
		boolean containsTwo = false;
		for (int key : map.keySet()) {
			if (map.get(key) == 3) containsThree = true;
			if (map.get(key) == 2) containsTwo = true;
		}
		if (containsThree && containsTwo) scores[8] = 25;
		else scores[8] = 0;
	}
	
	public void smallStraight(DiceGroup dg) {
		SortedSet<Integer> set = new TreeSet<>();
		
		for (Dice d : dg.getDice()) {
			set.add(d.getValue());
		}
	
		List<Integer> list = new ArrayList(set);
		boolean works = false;
		
		for (int i = 0; i<list.size() - 3; i++) {
			if (list.get(i + 3) == list.get(i + 2) + 1 &&
				list.get(i + 2) == list.get(i + 1) + 1 &&
				list.get(i + 1) == list.get(i) + 1) works = true;
		}
		
		if (works) scores[9] = 30;
		else scores[9] = 0;
	}
	
	public void largeStraight(DiceGroup dg) {
		SortedSet<Integer> set = new TreeSet<>();
		
		for (Dice d : dg.getDice()) {
			set.add(d.getValue());
		}
	
		List<Integer> list = new ArrayList(set);
		boolean works = false;
		
		for (int i = 0; i<list.size() - 4; i++) {
			if (list.get(i + 4) ==  list.get(i + 3) + 1 &&
				list.get(i + 3) == list.get(i + 2) + 1 &&
				list.get(i + 2) == list.get(i + 1) + 1 &&
				list.get(i + 1) == list.get(i) + 1) works = true;
		}
		
		if (works) scores[10] = 40;
		else scores[10] = 0;
	}
	
	public void chance(DiceGroup dg) {
		int totalScore = 0;
		
		for (Dice d : dg.getDice()) totalScore += d.getValue();
		
		scores[11] = totalScore;
	}
	
	public void yahtzeeScore(DiceGroup dg) {
		int first = dg.getDice()[0].getValue();
		boolean works = true;
		
		for (Dice d : dg.getDice()) if (d.getValue() != first) works = false;
		
		if (works) scores[12] = 50;
		else scores[12] = 0; 
	}
}
