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
    private int lifetime; //lifespan of kaboom
    private final int THRESHOLD = 3; //threshold of kaboom

    /**
     * constructor, sets lifetime to threshold
     */
    public Kaboom() {
        setColor(null);
        lifetime = THRESHOLD;
    }

    /**
     * act method for kaboom behavior
     * Moves itself when lifetime is 0
     * Lifetime continues decreasing with each method call
     */
    public void act() {
        if(lifetime == 0) {
            removeSelfFromGrid();
        }
        lifetime--;
    }
}
