import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Critter;
import java.util.ArrayList;
import java.awt.Color;

/**
 * @author  William Liu
 * @since   3/26/25
 */
public class Coyote extends Critter {
    private int steps;
    private int sleep; 
    private boolean isWall; 

    public Coyote() {

        // Set fields to the default methods
        setColor(null);
        steps = 5;
        sleep = 5;
        isWall = true;

        // Random direction 
        int dir = ((int) (Math.random() * 360 / 45));
        setDirection(dir * 45);
    }

    public void act() {
        // Increment steps if negative because of cooldown
        if (steps < 0) {
            steps++;
            return;
        }
    
        // Choose a new direction
        if (steps == 0) {
            setDirection((int)(Math.random() * 8) * 45);
    
            if (!isWall) {
                ArrayList<Location> locations = getGrid().getEmptyAdjacentLocations(getLocation());
                if (!locations.isEmpty()) {
                    // Select random location and put stone
                    Location stoneLocation = locations.get((int)(Math.random() * locations.size()));
                    Stone st = new Stone();
                    st.putSelfInGrid(getGrid(), stoneLocation);
                }
            }
        }
    
        // Determine next location
        Location nextLoc = getLocation().getAdjacentLocation(getDirection());
        Grid<Actor> gr = getGrid();
    
        // Check if location is invalid or occupied
        if (!gr.isValid(nextLoc) || gr.get(nextLoc) != null) {
            // Cooldown
            steps = -5;
            sleep = -5;
    
            // If it hit a wall
            isWall = !gr.isValid(nextLoc);
    
            // If the next location contains a Boulder, remove from the grid
            if (gr.isValid(nextLoc) && gr.get(nextLoc) instanceof Boulder) {
                removeSelfFromGrid();
                return;
            }
        } 
        else {
            // Move forward
            steps++;
            sleep++;
    
            // Reset steps 
            if (steps == 6) {
                steps = 0;
                isWall = false;
            }
    
            moveTo(nextLoc);
        }
    }    
}