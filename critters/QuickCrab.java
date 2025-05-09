import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import java.awt.Color;
import java.util.ArrayList;
/**
 * A <code>QuickCrab</code> looks at a limited set of neighbors when it
 * eats and moves.<br />
 */
public class QuickCrab extends CrabCritter {
    public QuickCrab() {
        setColor(Color.CYAN);
    } 
    /**
     * @return list of empty locations
     * two locations to the right and two locations to the left
     */
    public ArrayList<Location> getMoveLocations() {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid g = getGrid();

        addIfGoodTwoAwayMove(locs,getDirection() + Location.LEFT);
        addIfGoodTwoAwayMove(locs,getDirection() + Location.RIGHT);

        if (locs.size() == 0) return super.getMoveLocations();

        return locs;
    }

    /**
    * Adds a valid and empty two away location in direction dir to the
    * ArrayList locs.
    * To be a valid two away location, the location that is one away in
    * direction dir must also be valid and empty.
    */
    private void addIfGoodTwoAwayMove(ArrayList<Location> locs,int dir) {
        Grid g = getGrid();
        Location loc = getLocation();

        Location temp = loc.getAdjacentLocation(dir);

        if(g.isValid(temp) && g.get(temp) == null) {
            Location loc2 = temp.getAdjacentLocation(dir);
            
            if(g.isValid(loc2) && g.get(loc2)== null) locs.add(loc2);
        }
    }
} 