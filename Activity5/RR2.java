import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import java.util.ArrayList;

/**
 * @author 	William Liu
 * @since 	3/26/25
 */
public class RR2 extends Critter {
    public RR2() {
        setColor(null);
        setDirection(Location.NORTH);
    }

    public void act() {
        Location loc = getLocation();
        ArrayList<Location> locs = new ArrayList<Location>();
        
        // The square around the RR
        int startRow = loc.getRow() - 3;
        int endRow = loc.getRow() + 3;
        
        int startCol = loc.getCol() - 3;
        int endCol = loc.getCol() + 3;

        Grid<Actor> gr = getGrid();

        // Each location in the square
        for(int i = startRow; i <= endRow; i++) {
            for(int j = startCol; j <= endCol; j++) {
                Location curLoc = new Location(i, j);

                // only if its valid
                if(gr.isValid(curLoc)) {
                    Actor actor = gr.get(curLoc);

                    // Targets that are empty, a Boulder, or a Coyote
                    if(actor == null || actor instanceof Boulder || actor instanceof Coyote) {
                        
                        // All possible locs in the diagonals
                        Location loc1 = new Location(i + 1, j - 1);
                        Location loc2 = new Location(i + 2, j - 2);
                        Location loc3 = new Location(i + 3, j - 3);
                        Location loc4 = new Location(i + 1, j + 1);
                        Location loc5 = new Location(i + 2, j + 2);
                        Location loc6 = new Location(i + 3, j + 3);
                        Location loc7 = new Location(i + 1, j + 1);
                        Location loc8 = new Location(i + 2, j + 2);
                        Location loc9 = new Location(i + 3, j + 3);
                        Location loc10 = new Location(i + 1, j + 1);
                        Location loc11 = new Location(i + 2, j + 2);
                        Location loc12 = new Location(i + 3, j + 3);

                        // Clear diagonal path between current location and target
                        // Check all four diagonal directions
                        if(loc.getRow() > curLoc.getRow() && loc.getCol() < curLoc.getCol()) {
                            // Check all three and add them to locs if they are valid
                            if(gr.isValid(loc1) && !gr.isValid(loc2) && !gr.isValid(loc3)) {
                                if(gr.get(loc1) == null) {
                                    locs.add(curLoc);
                                }
                            }
                            else if(gr.isValid(loc1) && gr.isValid(loc2) && !gr.isValid(loc3)) {
                                if(gr.get(loc1) == null && gr.get(loc2) == null) {
                                    locs.add(curLoc);
                                }
                            }
                            else if(gr.isValid(loc1) && gr.isValid(loc2) && gr.isValid(loc3)) {
                                if(gr.get(loc1) == null && gr.get(loc2) == null & gr.get(loc3) == null) {
                                    locs.add(curLoc);
                                }
                            }
                        }
                        else if(loc.getRow() > curLoc.getRow() && loc.getCol() > curLoc.getCol()) {
                            // Check all three and add them to locs if they are valid
                            if(gr.isValid(loc1) && !gr.isValid(loc2) && !gr.isValid(loc3)) {
                                if(gr.get(loc1) == null) {
                                    locs.add(curLoc);
                                }
                            }
                            else if(gr.isValid(loc1) && gr.isValid(loc2) && !gr.isValid(loc3)) {
                                if(gr.get(loc1) == null && gr.get(loc2) == null) {
                                    locs.add(curLoc);
                                }
                            }
                            else if(gr.isValid(loc1) && gr.isValid(loc2) && gr.isValid(loc3)) {
                                if(gr.get(loc1) == null && gr.get(loc2) == null & gr.get(loc3) == null) {
                                    locs.add(curLoc);
                                }
                            }
                        }
                        else if(loc.getRow() < curLoc.getRow() && loc.getCol() > curLoc.getCol()) {
                            // Check all three and add them to locs if they are valid
                            if(gr.isValid(loc1) && !gr.isValid(loc2) && !gr.isValid(loc3)) {
                                if(gr.get(loc1) == null) {
                                    locs.add(curLoc);
                                }
                            }
                            else if(gr.isValid(loc1) && gr.isValid(loc2) && !gr.isValid(loc3)) {
                                if(gr.get(loc1) == null && gr.get(loc2) == null) {
                                    locs.add(curLoc);
                                }
                            }
                            else if(gr.isValid(loc1) && gr.isValid(loc2) && gr.isValid(loc3)) {
                                if(gr.get(loc1) == null && gr.get(loc2) == null & gr.get(loc3) == null) {
                                    locs.add(curLoc);
                                }
                            }
                        }
                        else if(loc.getRow() < curLoc.getRow() && loc.getCol() < curLoc.getCol()) {
                            // Check all three and add them to locs if they are valid
                            if(gr.isValid(loc1) && !gr.isValid(loc2) && !gr.isValid(loc3)) {
                                if(gr.get(loc1) == null) {
                                    locs.add(curLoc);
                                }
                            }
                            else if(gr.isValid(loc1) && gr.isValid(loc2) && !gr.isValid(loc3)) {
                                if(gr.get(loc1) == null && gr.get(loc2) == null) {
                                    locs.add(curLoc);
                                }
                            }
                            else if(gr.isValid(loc1) && gr.isValid(loc2) && gr.isValid(loc3)) {
                                if(gr.get(loc1) == null && gr.get(loc2) == null & gr.get(loc3) == null) {
                                    locs.add(curLoc);
                                }
                            }
                        }
                    }
                }
            }
        }

        // Pick a random one and act on it
        if(locs.size() != 0) {
            int rand = (int)(Math.random() * locs.size());
            Location randLoc = locs.get(rand);
            Actor collision = getGrid().get(randLoc);

            if(collision instanceof Boulder) {
                // Collision with boulder causes a Kaboom
                Kaboom kaboom = new Kaboom();
                kaboom.putSelfInGrid(gr, collision.getLocation());
                removeSelfFromGrid();
            }
            else if(collision instanceof Coyote) {
                // Collision with Coyote creates a sick coyote
                Location loc2 = collision.getLocation();
                collision.removeSelfFromGrid();
                removeSelfFromGrid();
                putSelfInGrid(gr, loc2);

                ArrayList<Location> adj = getGrid().getEmptyAdjacentLocations(loc2);
                int rand2 = (int)(Math.random() * adj.size());

                Location newLoc = adj.get(rand2);
                SickCoyote sick  = new SickCoyote();

                sick.putSelfInGrid(gr, newLoc);
            }
            else {
                moveTo(randLoc);
            }
        }
    }
}
