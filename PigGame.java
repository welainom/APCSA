/**
 *	The game of Pig.
 *	Pig is a dice game first described by John Scarne in 1945. 
 * 	The rules are simple. Two players try to reach 100 points. 
 * 	In each turn, the player either rolls the die or 
 * 	holds and scores the sum of the turn. 
 * 	During a turn, the player has two choices: 
 *	roll - If the player rolls a 
 *		2 through 6, the number is added to the turn’s total 
 *		1, the player loses their turn and the opponent gets to roll 
 *	hold - The player adds the turn’s total to their score 
 * 	and the opponent gets to roll 
 *	The user will play against the computer. 
 * 	The computer will always hold when reaching at least 20 points.
 *
 *	@author		William Liu
 *	@since		September 13, 2024
 */

public class PigGame {
	private final int WINNING_SCORE = 100;
	private final int TURN_HOLD = 20;
	private final int MIN_TURNS = 1000;
	private final int MAX_TURNS = 1000000;
	
	public static void main(String[] args) {
		PigGame p = new PigGame();
		
		// Read in the user choice
		char choice = Prompt.getChar("Play game or statistics (p or s)");
		if (choice == 'p') {
			p.playGame();
		}
		else {
			p.runStatistics();
		}
	}
	
	/**
	 * 	Method to simulate one computer turn for statistics
	 * 	
	 *	@return 	How many points the turn scored
	 */
	public int runTurn() {
		int turnScore = 0;
		
		while (turnScore < TURN_HOLD) {
			
			// Create a new Dice. Roll.
			Dice d = new Dice();
			d.roll();
			
			// If the score is 1, the score for the turn is 0.
			int score = d.getValue();
			if (score == 1) {
				turnScore = 0;
				break;
			}
			else {
				
				// If not 1, then update the turn score
				turnScore += score;
			}
		}
		
		return turnScore;
	}
	
	/**
	 * 	Method to run Pig statistics
	 */
	public void runStatistics() {
		
		// Get the number of turns the user wants to simulate and set up variables
		int numTurns = Prompt.getInt("Number of turns (1000 - 1000000)", MIN_TURNS, MAX_TURNS);
		int num0 = 0, num20 = 0, num21 = 0, num22 = 0, num23 = 0, num24 = 0, num25 = 0;
	
		for (int i = 0; i<numTurns; i++) {
			
			// Run one turn, and get the score;
			int turnScore = runTurn();
			
			// Add it to each possible score's counter
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
		
		// Displaying all the information for each possible score.
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
	
	/**
	 * 	This method runs the game for the user.
	 */	
	public void playGame() {
		
		// Set up the variables.
		int playerScore = 0, computerScore = 0;
		boolean turn = true; // If turn is true, it is the player's turn
		
		// While no one has won
		while (playerScore < WINNING_SCORE && computerScore < WINNING_SCORE) {
			if (turn) {
				
				// Get the player's score for this turn.
				playerScore = playerTurn(playerScore);
				
				// Make turn the opposite
				turn = !turn;
				System.out.printf("Your total score: %d \n\n", playerScore);
			}
			
			else {
				
				// Get the computer's score for this turn
				computerScore = computerTurn(computerScore);
				
				// Make turn the opposite
				turn = !turn;
				System.out.printf("Computer total score: %d \n\n", computerScore);
			}
		}
		
		// Print the winning player
		if (computerScore >= WINNING_SCORE) {
			System.out.println("Computer Wins.");
		}
		if (playerScore >= WINNING_SCORE) {
			System.out.println("You Win.");
		}
	}
	
	/**
	 * 	This method runs one iteration of the player's turn.
	 * 
	 * 	@param playerScore	The player's total score prior to this turn.
	 * 	@return 			The player's updated score after the turn.
	 */
	public int playerTurn(int playerScore) {
		int turnScore = 0;
		
		System.out.println("**** User turn **** \n");
		while (true) {
			System.out.printf("Your turn score: %d \n", turnScore);
			System.out.printf("Your total score: %d \n \n", playerScore);
			
			// Asking user for their move choice
			char move = Prompt.getChar("(r)oll or (h)old");
			
			if (move == 'r') {
				// Create a new Dice. Roll it and display the result.
				Dice d = new Dice();
				d.roll();
				d.printDice();
				
				// Get the score. If it is one, turnScore is 0. End turn.
				int score = d.getValue();
				if (score == 1) {
					turnScore = 0;
					break;
				}
				else turnScore += score;
			}
			if (move == 'h') {
				break;
			}
		}
		
		// The player's updated score is the turnScore + original score.
		int newScore = playerScore + turnScore;
		return newScore;
	}
	
	/**
	 * 	This method runs one iteration of the computer's turn.
	 * 
	 * 	@param computerScore	The computers's total score prior to this turn.
	 * 	@return 				The player's updated score after the turn.
	 */
	public int computerTurn(int computerScore) {
		int turnScore = 0;
		
		System.out.println("\n**** Computer turn ****");
		while (turnScore < TURN_HOLD) {
			System.out.printf("Computer turn score: %d \n", turnScore);
			System.out.printf("Computer total score: %d \n \n", computerScore);
			
			String s = Prompt.getString("Press enter for computer's turn");
			
			// Create a new Dice. Roll and display result.
			Dice d = new Dice();
			d.roll();
			d.printDice();
			
			// If result is 1, turn score is 0. End turn.
			int score = d.getValue();
			if (score == 1) {
				turnScore = 0;
				break;
			}
			else {
				turnScore += score;
			}
		}
		
		// Computer's updated score is the original score + turn score.
		int newScore = computerScore + turnScore;
		return newScore;
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
