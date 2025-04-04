import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import java.util.ArrayList;
import java.awt.Color;

/**
 * @author 	William Liu
 * @since 	3/26/25
 */
public class RR extends Critter {
    public RR() {
        setColor(null);
    }

    public ArrayList<Location> getMoveLocations() {
        Grid<Actor> gr = getGrid();
        ArrayList<Location> moveLocs = new ArrayList<>();

        int[] directions = {Location.NORTH, Location.NORTH, Location.NORTHEAST,
                            Location.EAST, Location.SOUTHEAST, Location.SOUTH, 
                            Location.SOUTHWEST, Location.WEST, Location.NORTHWEST};

        // Check directions
        for (int dir : directions) {
            Location loc = getLocation(); 
            
            for (int step = 0; step < 3; ++step) {

				// Get next location
                Location next = loc.getAdjacentLocation(dir);

                if (gr.isValid(next)) {
                    // If location is empty or boulder or coyote
                    if (gr.get(next) == null || gr.get(next) instanceof Boulder || 
                                               gr.get(next) instanceof Coyote) {
                        moveLocs.add(next);
                    }

                    // Stop movement if there is an obstacle
                    if (gr.get(next) != null) {
                        step = 3;
                    }
                } 
                else {
                    step = 3;
                }

                loc = next;
            }
        }
        return moveLocs;
    }

    public void makeMove(Location loc) {
        Grid<Actor> gr = getGrid();

        // If there's no valid move, remove RR from the grid
        if (loc == null) {
            removeSelfFromGrid();
            return;
        }
        // If RR hits boulder remove it and put kaboom
        else if (gr.get(loc) instanceof Boulder) {
            Kaboom kaboom = new Kaboom();
            kaboom.putSelfInGrid(gr, loc);
            removeSelfFromGrid();
            return;
        }
        // Make a sick coyote in a random adjacent location
        else if (gr.get(loc) instanceof Coyote) {
            SickCoyote sck = new SickCoyote();
            ArrayList<Location> adj = gr.getValidAdjacentLocations(loc);

            // Put sick coyote in random location
            if (!adj.isEmpty()) {
                int rand = (int) (Math.random() * adj.size());
                sck.putSelfInGrid(gr, adj.get(rand));
            }
        }
        
        moveTo(loc);
    }
}
