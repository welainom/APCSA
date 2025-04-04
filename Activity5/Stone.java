import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import java.awt.Color;

/**
 * @author  William Liu
 * @since   3/26/25
 */
public class Stone extends Rock {
    private int lifetime;
    private final int THRESHOLD = 3; 

    public Stone() {
        setColor(null);
        lifetime = (int)(Math.random()*200)+1; 
    }

    public Stone(int lifetime2) {
        setColor(null);
        lifetime = lifetime2;
    }

    public void act() {

        // If lifetime is 0 then replace with a boulder
        if(lifetime == 0) {
            Location loc = getLocation();
            Boulder boulder = new Boulder();
            boulder.putSelfInGrid(getGrid(), loc);
        } 

        // If less than threshold then make it green
        else if(lifetime < THRESHOLD) {
            setColor(Color.GREEN);
        }
        lifetime--;
    }
}
