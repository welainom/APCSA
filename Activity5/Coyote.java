import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

public class Coyote extends Critter {
    private boolean sleeping = false;
    private int count = 1;
    private int steps = 0;

    // Sets to default values
    public Coyote () {
        setColor(null);
        setDirection((int)(Math.random()*8)*45);
    }

    public void makeMove(Location loc) {
        Location next = getLocation().getAdjacentLocation(getDirection());
    
        // Sleeping or not 
        if (sleeping) {
            sleepingState(next);
        } else {
            awakeState(next);
        }
    }
    
    private void awakeState(Location next) {
        steps++;
        
        // If it doesnt sleep
        if (!getGrid().isValid(next) || getGrid().get(next) instanceof Actor || steps == 6) {
            sleeping = true;
            
            // If it dies put a kaboom
            if (getGrid().isValid(next) && getGrid().get(next) instanceof Boulder) {
                Kaboom boom = new Kaboom();
                boom.putSelfInGrid(getGrid(), next);
                removeSelfFromGrid();
            }
        } 
        else {
            moveTo(next);
        }
    }
    
    private void sleepingState(Location next) {
        count++;
        
        // Check if the bug has slept for enough
        if (count == 5) {
            ArrayList<Location> locs = getGrid().getEmptyAdjacentLocations(getLocation());
            Location rand = locs.get((int) (Math.random() * locs.size()));
            
            // Put a stone if coyote stopped
            if (steps == 6 || (getGrid().isValid(next) && getGrid().get(next) instanceof Actor)) {
                Stone stone = new Stone();
                // put stones and choose random location that is valid
                stone.putSelfInGrid(getGrid(), rand);
                locs.remove(rand);
                rand = locs.get((int)(Math.random() * locs.size()));
            }
            
            // Point the bug in the correct direction
            setDirection(getLocation().getDirectionToward(rand));
            
            // Reset counters 
            count = 1;
            steps = 0;
            sleeping = false;
        }
    }

    public void processActors(ArrayList<Actor> actors) {}
}
