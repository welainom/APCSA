import java.util.Scanner;
import java.io.PrintWriter;

/**
 * 	The classic snake game. A snake, controlled by you can move in four directions
 * 	and eat tgts. If it eats a tgt, it grows by one segment. If it hits a wall
 * 	or runs into itself, game over.
 * 
 * 	@author 	William Liu
 * 	@since 		May 13, 2025
 */
public class SnakeGame {
	private Snake snake;		// the snake in the game
	private SnakeBoard board;	// the game board
	private Coordinate tgt;	// the tgt for the snake
	private int score;			// the score of the game
	
	/*	Constructor	*/
	public SnakeGame() {
		snake = new Snake(3, 3);
		board = new SnakeBoard(20, 25);
		score = 0;
		board.placeSnake(snake);
		tgt = newTarget();
	}
	
	/*	Main method	*/
	public static void main(String[] args) {
		SnakeGame snakeGame = new SnakeGame();
		snakeGame.run();
	}
	
	/**
	 * Runs the game. Loops until game over
	 */
	public void run() {
		printIntroduction();
		
		boolean run = true; 
		while (run) {
			board.printBoard(snake, tgt); 
			run = moved(); 
			
			// Check if the game is over
			if (hasGameEnded()) {
				run = false; 
				board.printBoard(snake, tgt); 
			}
		}

		System.out.println("Thanks for playing SnakeGame!!!");
	}
	
	/**
	 * Asks user for their input and acts on it
	 */
	public char getInput() {
		char in = ' '; 
		boolean selected = false; 
		
		// Continue looping until selected
		while(!selected) {
			in = Prompt.getChar("Score: " + score + 
				" (w - North, s - South, d - East, a - West, h - Help)");
			
			// check if valid
			if (in == 'w' || in == 'd' || in == 's' || in == 'a' || in == 'q' 
				|| in == 'f' || in == 'r' || in == 'h') {
				selected = true; // now valid
			}
		}
		
		return in; // return choice
	}
	
	/**
	 * Choose and create a random next target for the snake
	 */
	public Coordinate newTarget() {
		// valid coords
		SinglyLinkedList<Coordinate> coords = new SinglyLinkedList<Coordinate>();
		
		// Find empty spaces
		for (int i = 0; i < board.getHeight(); i++) {
			for (int j = 0; j < board.getWidth(); j++) {
				if (board.getChar(new Coordinate(i, j)) == ' ') {
					coords.add(new Coordinate(i, j)); // add to list
				}
			}
		}
		
		// return a random one
		return coords.get((int) (Math.random() * coords.size())).getValue();
	}
	
	/**
	 * Interprets player input
	 */
	public boolean moved() {
		char in = getInput(); 
		boolean moved = true; 
		
		// act on input
		switch (in) {
			case 'w':
				// north
				moved = move(new Coordinate(snake.get(0).getValue().getX() - 1, 
											snake.get(0).getValue().getY()));
				break;
			case 'd':
				// east
				moved = move(new Coordinate(snake.get(0).getValue().getX(), 
											snake.get(0).getValue().getY() + 1));
				break;
			case 's':
				// south
				moved = move(new Coordinate(snake.get(0).getValue().getX() + 1, 
											snake.get(0).getValue().getY()));
				break;
			case 'a':
				// west
				moved = move(new Coordinate(snake.get(0).getValue().getX(), 
											snake.get(0).getValue().getY() - 1));
				break;
			case 'q':
				// confirmation
				char input = Prompt.getChar("\nDo you really want to quit? (yes (y) or no (n))");
				if (input == 'y') moved = false;
				else moved = true;
				break;
			case 'f':
				// save game
				moved = save();
				break;
			case 'r':
				// restore game
				restore();
				break;
			case 'h':
				// help menu
				helpMenu();
				break;
			default:
				break;
		}
	
		return moved;
	}
	
	/**
	 * checks if the snake is able to move to the coord
	 */
	public boolean move(Coordinate coord) {
		// valid or ran into itself
		if (!board.isValidLoc(coord) || snake.contains(coord)) {
			return false; 
		}
		
		snake.add(0, coord);
		
		// eaten target or no
		if (tgt.equals(coord)) {
			score++;
			tgt = newTarget();
			
			// if target failed
			if (tgt == null) {
				return false;
			}
		}
		else {
			snake.remove(snake.size() - 1);
		}

		return true;
	}
	
	/**
	 * Checks if the game is over
	 */
	public boolean hasGameEnded() {
		// Count the number of empty spaces on the board
		int empty = 0;
		
		for (int i = 1; i < board.getHeight(); i++) {
			for (int j = 1; j < board.getWidth(); j++) {
				if (board.getChar(new Coordinate(i, j)) == ' ') {
				  empty++; 
				}
			} 
		} 
		
		// if empty spaces less than 5
		if (empty < 5) {
			return true; 
		}
		
		// If there are valid moves for snake
		Coordinate s = snake.get(0).getValue();
		Coordinate a = new Coordinate(s.getX() - 1, 
											s.getY());
		Coordinate b = new Coordinate(s.getX() + 1, 
										s.getY());
		Coordinate c = new Coordinate(s.getX(), s.getY() - 1);
		Coordinate d = new Coordinate(s.getX(), s.getY() + 1);
		
		boolean b1 = (!board.isValidLoc(a) || (board.getChar(a) != ' ' 
							&& board.getChar(a) != '+'));
		boolean b2 = (!board.isValidLoc(b) || (board.getChar(b) != ' ' 
							&& board.getChar(b) != '+'));
		boolean b3 = (!board.isValidLoc(c) || (board.getChar(c) != ' ' 
							&& board.getChar(c) != '+'));
		boolean b4 = (!board.isValidLoc(d) || (board.getChar(d) != ' ' 
							&& board.getChar(d) != '+'));

		return (b1 && b2 && b3 && b4);
	}
	
	/**
	 * Saves the current game to a text file containing all necessary
	 * info to reload the game
	 */
	public boolean save() {		
		PrintWriter writer = FileUtils.openToWrite("snakeGameSave.txt");
		
		// Write game data 
		writer.println("Score " + score);
		writer.println("Target");
		writer.println(tgt.getX() + " " + tgt.getY());
		writer.println("Snake " + snake.size());
		
		for (int i = 0; i < snake.size(); i++) {
		  writer.printf("%d %d\n", snake.get(i).getValue().getX(), 
						snake.get(i).getValue().getY()); 
		}
		
		writer.close();
		System.out.println("\nGame saved to snakeGameSave.txt\n");
		return true; 
	}

	/**	
	 * Reads the game info from the text file and reloads the saved game
	 */
	public void restore(){
		int x = 0;
		int y = 0;
		int num = 0;
		
		Scanner in = FileUtils.openToRead("snakeGameSave.txt");
		
		// skip lables and read info
		in.next(); 
		score = in.nextInt(); 
		in.next(); 
		
		// read coords
		x = in.nextInt();
		y = in.nextInt();
		tgt = new Coordinate(x, y);
		
		in.next();
		num = in.nextInt(); // num of coords
		snake.clear(); 
		
		// add coords to snake list
		for (int i = 0; i < num; i++) {
			x = in.nextInt();
			y = in.nextInt();
			snake.add(new Coordinate(x,y));
		}
		
		in.close();
	}
	
	/**	Print the game introduction	*/
	public void printIntroduction() {
		System.out.println("  _________              __            ________");
		System.out.println(" /   _____/ ____ _____  |  | __ ____  /  _____/_____    _____   ____");
		System.out.println(" \\_____  \\ /    \\\\__  \\ |  |/ // __ \\/   \\  ___\\__  \\  /     \\_/ __ \\");
		System.out.println(" /        \\   |  \\/ __ \\|    <\\  ___/\\    \\_\\  \\/ __ \\|  Y Y  \\  ___/");
		System.out.println("/_______  /___|  (____  /__|_ \\\\___  >\\______  (____  /__|_|  /\\___  >");
		System.out.println("        \\/     \\/     \\/     \\/    \\/        \\/     \\/      \\/     \\/");
		System.out.println("\nWelcome to SnakeGame!");
		System.out.println("\nA snake @****** moves around a board " +
							"eating tgts \"+\".");
		System.out.println("Each time the snake eats the tgt it grows " +
							"another * longer.");
		System.out.println("The objective is to grow the longest it can " +
							"without moving into");
		System.out.println("itself or the wall.");
		System.out.println("\n");
	}
	
	/**	Print help menu	*/
	public void helpMenu() {
		System.out.println("\nCommands:\n" +
							"  w - move north\n" +
							"  s - move south\n" +
							"  d - move east\n" +
							"  a - move west\n" +
							"  h - help\n" +
							"  f - save game to file\n" +
							"  r - restore game from file\n" +
							"  q - quit");
		Prompt.getString("Press enter to continue");
	}
}
