/**
*    A coordinate on a grid. Integer XY values.
*
*  @author    William Liu
*  @since     May 13th, 2025
*/


public class Coordinate implements Comparable<Coordinate>
{
   private int x;     // x value
   private int y;     // y value

   public Coordinate(int x, int y) {
       this.x = x;
       this.y = y;
   }
   
   /* Accessor methods */
   public int getX() {return x;}
   public int getY() {return y;}
   
   @Override
   /**
    * Checks if this coordinate object equals another one
    */
   public boolean equals(Object c)
   {
       if(c != null && c instanceof Coordinate &&
         (x == ((Coordinate) c).x) && (y == ((Coordinate) c).y)) return true;
       else return false;
   }
   
   @Override
   /**
    *    Compares Coordinate Objects based on a set of rules
    */
   public int compareTo(Coordinate c) {
       if (! (c instanceof Coordinate)) throw new IllegalArgumentException("compareTo not Coordinate object");
       if (y > ((Coordinate) c).y || y < ((Coordinate) c).y) return y - ((Coordinate) c).y;
       if (x > ((Coordinate) c).x || x < ((Coordinate) c).x) return x - ((Coordinate) c).x;
       return 0;
   }
   
   public String toString() {
      return "[ " + x+ ", " + y     + "]";  
   }
}
