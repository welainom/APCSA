/**
 *    A SinglyLinkedList of Coordinate objects representing
 *    a snake on a two-dimensional grid.
 *
 *	@author 	William Liu  
 *  @since    	May 13, 2025
 */

public class Snake extends SinglyLinkedList<Coordinate> {   
	
	// Default constructor
	public Snake(Coordinate loc)  {
		for (int i = 0; i <= 4; i++) {
			Coordinate c = new Coordinate(loc.getX() + i, loc.getY());
			this.add(c);
		}
	}
	
	// other possible constructor
	public Snake(int x, int y) {
		this(new Coordinate(x, y));
	}
}
