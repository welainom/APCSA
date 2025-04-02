import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;

/**
 * SickCoyoteRunner
 * Creates a grid (5x5) with four SickCoyotes on it
 * @author Harshil Dalal
 * @since Apr 30, 2023
 */
public class SickCoyoteRunner
{
	/**
	 * Creates the grid and adds four SickCoyote actors to it
	 * @param args		arguments on the command line
	 */
    public static void main(String[] args)
    {
        BoundedGrid<Actor> sGrid = new BoundedGrid<Actor>(5, 5);
        ActorWorld world = new ActorWorld(sGrid);

		world.add(new Location(2, 2), new SickCoyote(2));
		world.add(new Location(4, 4), new SickCoyote(4));
		world.add(new Location(6, 6), new SickCoyote(6));
		world.add(new Location(8, 8), new SickCoyote(8));
        world.show();
    }
}
