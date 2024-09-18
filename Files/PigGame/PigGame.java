/**
 *	The game of Pig.
 *	(Description here)
 *
 *	@author	
 *	@since	
 */
public class PigGame {
	public static void main(String[] args) {
		PigGame p = new PigGame();
		char choice = Prompt.getChar("Play game or statistics (p or s)");
		if (choice == 'p') {
			p.playGame();
		}
		else {
			p.runStatistics();
		}
	}
	
	public int runTurn() {
		int turnScore = 0;
		while (turnScore < 20) {
			Dice d = new Dice();
			d.roll();
			int score = d.getValue();
			if (score == 1) {
				turnScore = 0;
				break;
			}
			else {
				turnScore += score;
			}
		}
		
		return turnScore;
	}
	
	public void runStatistics() {
		int numTurns = Prompt.getInt("Number of turns (1000 - 1000000)", 1000, 1000000);
		int num0 = 0, num20 = 0, num21 = 0, num22 = 0, num23 = 0, num24 = 0, num25 = 0;
	
		for (int i = 0; i<numTurns; i++) {
			int turnScore = runTurn();
			switch (turnScore) {
				case 0:
					num0++;
					break;
				case 20:
					num20++;
					break;
				case 21:
					num21++;
					break;
				case 22:
					num22++;
					break;
				case 23:
					num23++;
					break;
				case 24:
					num24++;
					break;
				case 25:
					num25++;
					break;
			}
		}
		System.out.println("      Estimated");
		System.out.println("Score Probability");
		System.out.printf("0      %.5f \n", (double) num0/numTurns);
		System.out.printf("20      %.5f \n", (double) num20/numTurns);
		System.out.printf("21      %.5f \n", (double) num21/numTurns);
		System.out.printf("22      %.5f \n", (double) num22/numTurns);
		System.out.printf("23      %.5f \n", (double) num23/numTurns);
		System.out.printf("24      %.5f \n", (double) num24/numTurns);
		System.out.printf("25      %.5f \n", (double) num25/numTurns);
	}
	
	public void playGame() {
		int playerScore = 0, computerScore = 0, turnScore = 0;
		boolean turn = true;
		printIntroduction();
		
		while (playerScore < 100 && computerScore < 100) {
			if (turn) {
				System.out.println("**** User turn **** \n");
				System.out.printf("Your turn score: %d \n", turnScore);
				System.out.printf("Your total score: %d \n \n", playerScore);
				char move = Prompt.getChar("(r)oll or (h)old");
				
				if (move == 'r') {
					Dice d = new Dice();
					d.roll();
					int score = d.getValue();
					d.printDice();
					if (score == 1) {
						turn = !turn;
						turnScore = 0;
					}
					else turnScore += score;
				}
				if (move == 'h') {
					playerScore += turnScore;
					turnScore = 0;
					turn = !turn;
				}
				System.out.printf("Your turn score: %d \n", turnScore);
				System.out.printf("Your total score: %d \n \n", playerScore);
			}
			
			else {
				System.out.println("**** Computer turn ****");
				while (turnScore < 20) {
					System.out.printf("Computer turn score: %d \n", turnScore);
					System.out.printf("Computer total score: %d \n \n", computerScore);
					String s = Prompt.getString("Press enter for computer's turn");
					Dice a = new Dice();
					a.roll();
					int score = a.getValue();
					a.printDice();
					
					if (score != 1) {
						turnScore += score;
					}
					else {
						turn = !turn;
						break;
					}
					
				}
				if (turnScore >= 20) {
					turn = !turn;
					computerScore += turnScore;
				}
				turnScore = 0;
			}
		}
		
		if (computerScore >= 100) {
			System.out.println("Computer Wins.");
		}
		if (playerScore >= 100) {
			System.out.println("You Win.");
		}
	}
	
	
	/**	Print the introduction to the game */
	public void printIntroduction() {
		System.out.println("\n");
		System.out.println("______ _         _____");
		System.out.println("| ___ (_)       |  __ \\");
		System.out.println("| |_/ /_  __ _  | |  \\/ __ _ _ __ ___   ___");
		System.out.println("|  __/| |/ _` | | | __ / _` | '_ ` _ \\ / _ \\");
		System.out.println("| |   | | (_| | | |_\\ \\ (_| | | | | | |  __/");
		System.out.println("\\_|   |_|\\__, |  \\____/\\__,_|_| |_| |_|\\___|");
		System.out.println("          __/ |");
		System.out.println("         |___/");
		System.out.println("\nThe Pig Game is human vs computer. Each takes a"
							+ " turn rolling a die and the first to score");
		System.out.println("100 points wins. A player can either ROLL or "
							+ "HOLD. A turn works this way:");
		System.out.println("\n\tROLL:\t2 through 6: add points to turn total, "
							+ "player's turn continues");
		System.out.println("\t\t1: player loses turn");
		System.out.println("\tHOLD:\tturn total is added to player's score, "
							+ "turn goes to other player");
		System.out.println("\n");
	}
	
}
