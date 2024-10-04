/**
 *	Plays the game of MasterMind.
 *	<Describe the game here>
 *	@author	William Liu	
 *	@since	September 27, 2024
 */

public class MasterMind {

	private boolean reveal;			// true = reveal the master combination
	private PegArray[] guesses;		// the array of guessed peg arrays
	private PegArray master;		// the master (key) peg array
	
	// Constants
	private final int PEGS_IN_CODE = 4;		// Number of pegs in code
	private final int MAX_GUESSES = 10;		// Max number of guesses
	private final int PEG_LETTERS = 6;		// Number of different letters on pegs
											// 6 = A through F

	public MasterMind() {
		reveal = false;
		guesses = new PegArray[MAX_GUESSES];
		master = new PegArray(PEGS_IN_CODE);
		
		for (int i = 0; i<PEGS_IN_CODE; i++) {
			int choice = (int) (Math.random() * PEG_LETTERS);
			master.getPeg(i).setLetter((char) (65 + choice));
		}
		
		for (int i = 0; i < MAX_GUESSES; i++) {
			PegArray p = new PegArray(PEGS_IN_CODE);
			Peg[] temp = new Peg[PEGS_IN_CODE];  // Create a new Peg array for each PegArray
				for (int j = 0; j < PEGS_IN_CODE; j++) {
					temp[j] = new Peg();  // Create new Peg objects for each guess
				}
			p.setPegArray(temp);  // Assign the unique Peg array to the PegArray
			guesses[i] = p;  // Store the PegArray in the guesses array
		}

	}

	public static void main(String[] args) {
		MasterMind m = new MasterMind();
		m.run();
	}
	
	public void run() {
		printIntroduction();
		
		String s = Prompt.getString("Hit the Enter key to start the game ->");
		int guessNum = -1;
		
		while (guessNum < 10 && !isFinished(guessNum)) {
			guessNum++;
			String guess = "";
			while (!guessWorks(guess.toUpperCase())) {
				guess = Prompt.getString("Enter the code using (A,B,C,D,E,F). For example, ABCD or abcd from left-to-right");
				guess.toUpperCase();
			}
			System.out.println(isFinished(guessNum));
			for (int i = 0; i<4; i++) {
				guesses[guessNum].getPeg(i).setLetter((char) (guess.charAt(i) - 32));
			}
			int temp1 = guesses[guessNum].getPartialMatches(master);
			int temp2 = guesses[guessNum].getExactMatches(master);
			
			System.out.print("Guess: ");
			for (int i = 0; i<PEGS_IN_CODE; i++) {
				System.out.print(guesses[guessNum].getPeg(i).getLetter());
			}
			System.out.println("");
			System.out.print("Master: ");
			for (int i = 0; i<4; i++) {
				System.out.print(master.getPeg(i).getLetter());
			}
			System.out.println("");
			/*for (int i = 0 ;i<MAX_GUESSES; i++) {
				PegArray p = guesses[i];
				for (int j = 0; j<4; j++) {
					System.out.print(p.getPeg(j).getLetter());
				}
				System.out.println("");
			}
			*/
			printBoard();
		}
		reveal = true;
		printBoard();
		System.out.printf("\n\nYou won in %d turns \n\n", guessNum + 1);
	}

	public boolean isFinished(int guess) {
		if (guess == -1) return false;
		PegArray g = guesses[guess];
		
		for (int i = 0; i < PEGS_IN_CODE; i++) {
			Peg p1 = g.getPeg(i);
			Peg p2 = master.getPeg(i);

			if (p1.getLetter() != p2.getLetter()) {
				return false;
			}
		}
		return true;
	}


	public boolean guessWorks(String guess) {
		if (guess.length() != 4) return false;
		for (int i = 0 ; i<4; i++) {
			if (guess.charAt(i) < 65 || guess.charAt(i) > 70) {
				return false;
			}
		}
		return true;
	}

	/**
	 *	Print the introduction screen
	 */
	public void printIntroduction() {
		System.out.println("\n");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("| ___  ___             _              ___  ___ _             _                       |");
		System.out.println("| |  \\/  |            | |             |  \\/  |(_)           | |                      |");
		System.out.println("| | .  . |  __ _  ___ | |_  ___  _ __ | .  . | _  _ __    __| |                      |");
		System.out.println("| | |\\/| | / _` |/ __|| __|/ _ \\| '__|| |\\/| || || '_ \\  / _` |                      |");
		System.out.println("| | |  | || (_| |\\__ \\| |_|  __/| |   | |  | || || | | || (_| |                      |");
		System.out.println("| \\_|  |_/ \\__,_||___/ \\__|\\___||_|   \\_|  |_/|_||_| |_| \\__,_|                      |");
		System.out.println("|                                                                                    |");
		System.out.println("| WELCOME TO MONTA VISTA MASTERMIND!                                                 |");
		System.out.println("|                                                                                    |");
		System.out.println("| The game of MasterMind is played on a four-peg gameboard, and six peg letters can  |");
		System.out.println("| be used.  First, the computer will choose a random combination of four pegs, using |");
		System.out.println("| some of the six letters (A, B, C, D, E, and F).  Repeats are allowed, so there are |");
		System.out.println("| 6 * 6 * 6 * 6 = 1296 possible combinations.  This \"master code\" is then hidden     |");
		System.out.println("| from the player, and the player starts making guesses at the master code.  The     |");
		System.out.println("| player has 10 turns to guess the code.  Each time the player makes a guess for     |");
		System.out.println("| the 4-peg code, the number of exact matches and partial matches are then reported  |");
		System.out.println("| back to the user. If the player finds the exact code, the game ends with a win.    |");
		System.out.println("| If the player does not find the master code after 10 guesses, the game ends with   |");
		System.out.println("| a loss.                                                                            |");
		System.out.println("|                                                                                    |");
		System.out.println("| LET'S PLAY SOME MASTERMIND!                                                        |");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("\n");
	}
	
	/**
	 *	Print the peg board to the screen
	 */
	public void printBoard() {
		// Print header
		System.out.print("+--------+");
		for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
		System.out.println("---------------+");
		System.out.print("| MASTER |");
		for (int a = 0; a < PEGS_IN_CODE; a++)
			if (reveal)
				System.out.printf("   %c   |", master.getPeg(a).getLetter());
			else
				System.out.print("  ***  |");
		System.out.println(" Exact Partial |");
		System.out.print("|        +");
		for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
		System.out.println("               |");
		// Print Guesses
		System.out.print("| GUESS  +");
		for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
		System.out.println("---------------|");
		for (int g = 0; g < MAX_GUESSES - 1; g++) {
			printGuess(g);
			System.out.println("|        +-------+-------+-------+-------+---------------|");
		}
		printGuess(MAX_GUESSES - 1);
		// print bottom
		System.out.print("+--------+");
		for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
		System.out.println("---------------+");
	}
	
	/**
	 *	Print one guess line to screen
	 *	@param t	the guess turn
	 */
	public void printGuess(int t) {
		System.out.printf("|   %2d   |", (t + 1));
		// If peg letter in the A to F range
		char c = guesses[t].getPeg(0).getLetter();
		if (c >= 'A' && c <= 'F')
			for (int p = 0; p < PEGS_IN_CODE; p++)
				System.out.print("   " + guesses[t].getPeg(p).getLetter() + "   |");
		// If peg letters are not A to F range
		else
			for (int p = 0; p < PEGS_IN_CODE; p++)
				System.out.print("       |");
		System.out.printf("   %d      %d    |\n",
							guesses[t].getExact(), guesses[t].getPartial());
	}

}
