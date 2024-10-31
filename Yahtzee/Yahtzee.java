public class Yahtzee {	
	private YahtzeePlayer playerOne, playerTwo;
	private int turn;
	private DiceGroup dice;
	
	public Yahtzee() {
		playerOne = new YahtzeePlayer();
		playerTwo = new YahtzeePlayer();
		dice = new DiceGroup();
	}
	
	public static void main(String[] args) {
		Yahtzee y = new Yahtzee();
		y.run();
	}
	
	public void run() {
		printHeader();
		
		decideTurns();
		
		int round = 1;
		while (!gameEnd(round)) {
			System.out.println("Round " + round + " of 13 rounds.");
			
			runPlayerTurn(turn);
			turn = 3 - turn;
			runPlayerTurn(turn);
			turn = 3 - turn;
			round++;
		}
		
		int aScore = 0, bScore = 0;
		for (int i = 1; i<=13; i++) aScore += playerOne.getScoreCard().getScore(i);
		for (int i = 1; i<=13; i++) aScore += playerTwo.getScoreCard().getScore(i);
		
		System.out.println(playerOne.getName() + ", you scored " + aScore + " points.");
		System.out.println(playerTwo.getName() + ", you scored " + bScore + " points.");
		
		if (aScore > bScore) System.out.println("Congratulations, " + playerOne.getName() + ", you win!!!");
		else if (bScore > aScore) System.out.println("Congratulations, " + playerTwo.getName() + ", you win!!!");
		else System.out.println("There was a Tie.");
	}
	
	public void runPlayerTurn(int turn) {
		dice.rollDice();
		String name;
		if (turn == 1) name = playerOne.getName();
		else name = playerTwo.getName();
		
		Prompt.getString(name + ", it's your turn to play. Please hit enter to roll the dice ");
		dice.printDice();
		
		String hold = "";
		
		int numRolls = 1;
		do {
			hold = getHold();
			dice.rollDice(hold);
			dice.printDice();
			numRolls++;
		} while(!hold.equals("-1") && numRolls <= 2);
		
		dice.printDice();
		
		playerOne.getScoreCard().printCardHeader();
		playerOne.getScoreCard().printPlayerScore(playerOne);
		playerTwo.getScoreCard().printPlayerScore(playerTwo);
		
		int choice = 1;
		do {
			choice = Prompt.getInt(name + ", now you need to make a choice. Pick a valid integer from the list above ", 1, 13);
		} while(((turn == 1) && (playerOne.getScoreCard().beenScored(choice))) ||
				((turn == 2) && (playerTwo.getScoreCard().beenScored(choice))));
		if (turn == 1) playerOne.getScoreCard().changeScore(choice, dice);
		else playerTwo.getScoreCard().changeScore(choice, dice);
		
		playerOne.getScoreCard().printCardHeader();
		playerOne.getScoreCard().printPlayerScore(playerOne);
		playerTwo.getScoreCard().printPlayerScore(playerTwo);
	}
	
	public String getHold() {
		String hold = "";
		while (!holdWorks(hold)) {
			hold = Prompt.getString("Which di(c)e would you like to keep? Enter the values you'd" + 
				" like to \'hold\' without spaces. For example, if you'd like to \'hold\' die 1, 2, and 5, " + 
				"enter 125 (enter -1 if you'd like to end the turn)");
		}
		return hold;
	}
	
	public boolean holdWorks(String hold) {
		if (hold.length() < 1 || hold.length() > 6) return false;
		if (hold.equals("-1")) return true;
		for (int i = 0; i<hold.length(); i++) {
			char c = hold.charAt(i);
			
			if (c < '1' || c > '6') return false;
		}
		return true;
	}
	
	public boolean gameEnd(int round) {
		if (round > 13) return true;
		else return false;
	}
	
	public void decideTurns() {
		playerOne.setName(Prompt.getString("Player 1, please enter your name."));
		playerTwo.setName(Prompt.getString("Player 2, please enter your name."));
		
		int aRoll, bRoll;
		
		Prompt.getString("Let's see who will go first. " + playerOne.getName() + ", please hit enter to roll the dice");
		dice.rollDice();
		dice.printDice();
		aRoll = dice.getTotal();
		
		Prompt.getString(playerTwo.getName() + ", it's your turn. Please hit enter to roll the dice");
		dice.rollDice();
		dice.printDice();
		bRoll = dice.getTotal();
		
		if (aRoll > bRoll) turn = 1;
		else turn = 2;
		
		System.out.println(playerOne.getName() + ", you rolled a sum of " + aRoll + 
							", and " + playerTwo.getName() + ", you rolled a sum of " + bRoll);
		
		if (turn == 1) System.out.println(playerOne.getName() + ", since your sum was higher, you'll roll first.");
		else System.out.println(playerTwo.getName() + ", since your sum was higher, you'll roll first.");
	}
	
	public void printHeader() {
		System.out.println("\n");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("| WELCOME TO MONTA VISTA YAHTZEE!                                                    |");
		System.out.println("|                                                                                    |");
		System.out.println("| There are 13 rounds in a game of Yahtzee. In each turn, a player can roll his/her  |");
		System.out.println("| dice up to 3 times in order to get the desired combination. On the first roll, the |");
		System.out.println("| player rolls all five of the dice at once. On the second and third rolls, the      |");
		System.out.println("| player can roll any number of dice he/she wants to, including none or all of them, |");
		System.out.println("| trying to get a good combination.                                                  |");
		System.out.println("| The player can choose whether he/she wants to roll once, twice or three times in   |");
		System.out.println("| each turn. After the three rolls in a turn, the player must put his/her score down |");
		System.out.println("| on the scorecard, under any one of the thirteen categories. The score that the     |");
		System.out.println("| player finally gets for that turn depends on the category/box that he/she chooses  |");
		System.out.println("| and the combination that he/she got by rolling the dice. But once a box is chosen  |");
		System.out.println("| on the score card, it can't be chosen again.                                       |");
		System.out.println("|                                                                                    |");
		System.out.println("| LET'S PLAY SOME YAHTZEE!                                                           |");
		System.out.println("+------------------------------------------------------------------------------------+");
		System.out.println("\n\n");
	}
}
