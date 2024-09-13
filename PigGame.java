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
		p.playGame();
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
		
		if (playerScore >= 100) {
			
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
