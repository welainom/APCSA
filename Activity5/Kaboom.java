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
public class Kaboom extends Actor {
    private int lifetime;
    private final int THRESHOLD = 3; 

    public Kaboom() {
        setColor(null);
        lifetime = THRESHOLD;
    }

    public void act() {

        // Countdown lifetime and remove when 0
        if(lifetime == 0) {
            removeSelfFromGrid();
        }
        lifetime--;
    }
}
