/**
 * 	The board for the Snake Game. Functionalities such as creating, printing
 *  adding a snake, and checking locations
 * 
 *  @author	William Liu
 *  @since 	May 13, 2025
 */
public class SnakeBoard {
	/* fields */
	private char[][] board;			// The 2D array to hold the board
	
	/* Constructor */
	public SnakeBoard(int h, int w) {
		board = new char[h + 2][w + 2];
		createBoard();
	}
	
	/**
	 *	Print the board to the screen.
	 */
	public void printBoard(Snake snake, Coordinate tgt) {

		// Create board, place snake, place target
		createBoard(); 
	        placeSnake(snake);  
	        board[tgt.getX()][tgt.getY()] = '+'; 
	        
	        // Print each cell of the board
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + " "); 
			}
			
			System.out.println();
		}
	}
	
	/**
	 * This method initializes all cells of the board to empty space,
	 * draws borders on the top and bottom rows, draws borders on the left
	 * and right columns, and draws corners.
	 */
	public void createBoard() {
		// Initialize all cells of the board to empty space
		for(int i = 0; i < board.length; i++) {
			for (int j = 0; j < (board[0]).length; j++) {
				board[i][j] = ' '; 
			}
		}
		
		// Borders for top and bottom
		for(int i = 0; i < board[0].length; i++) {
			board[0][i] = '-';
			board[board.length - 1][i] = '-';
		} 
		
		// Borders for left and right
		for(int i = 0; i < board.length; i++) {
			board[i][0] = '|';
			board[i][(board[0]).length - 1] = '|';
		} 
		
		// Draw corners
		board[0][(board[0]).length - 1] = '+';
		board[0][0] = '+';
		board[board.length - 1][(board[0]).length - 1] = '+';
		board[board.length - 1][0] = '+';
	}
	
	/* Helper methods go here	*/
	
	/**
	 * This method checks if the coordinate passed in is a valid location 
	 */
	public boolean isValidLoc(Coordinate c) {
		return (c.getY() > 0 && c.getY() <= getWidth() &&
			c.getX() > 0 && c.getX() <= getHeight());
	}
	
	/**
	 * This method draws the snake on the snake game board.
	 */
	public void placeSnake(Snake snake) {
		if (snake.size() >= 1) {		
			// head at current position
			board[snake.get(0).getValue().getX()][snake.get(0).getValue().getY()] = '@';

			// body segments at their positions
			for (int i = 1; i < snake.size(); i++) {
				board[snake.get(i).getValue().getX()][snake.get(i).getValue().getY()] = '*'; 
			}
		}
	}
	
	/*	Accessor methods	*/
	
	// Returns the height 
	public int getHeight()
	{
		return board.length - 2;
	}
	
	// Returns the width 
	public int getWidth()
	{
		return board[0].length - 2;
	}
	
	// returns char at the coord
	public char getChar(Coordinate c)
	{
		return board[c.getX()][c.getY()];
	}
	
	/********************************************************/
	/********************* For Testing **********************/
	/********************************************************/
	
	public static void main(String[] args)
	{
		// Create the board
		int height = 10, width = 15;
		SnakeBoard sb = new SnakeBoard(height, width);
		// Place the snake
		Snake snake = new Snake(3, 3);
		// Place the target
		Coordinate target = new Coordinate(1, 7);
		// Print the board
		sb.printBoard(snake, target);
	}
}
