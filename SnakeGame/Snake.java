/**
 *    A SinglyLinkedList of Coordinate objects representing
 *    a snake on a two-dimensional grid.
 *
 *  @author 	William Liu  
 *  @since    	May 13, 2025
 */

public class Snake extends SinglyLinkedList<Coordinate> {   
	
	// Default constructor
	public Snake(Coordinate coord)  {
		for (int i = 0; i <= 4; i++) {
			Coordinate c = new Coordinate(coord.getX() + i, coord.getY());
			this.add(c);
		}
	}
	
	// other possible constructor
	public Snake(int x, int y) {
		this(new Coordinate(x, y));
	}
}
