import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import java.awt.Color;

/**
 * A Kaboom is an actor that looks like an
 * explosion and it shows up when the Boulder explodes.
 * A Kaboom lasts only a few steps before it disappears
 * @author Harshil Dalal
 * @since April 30th 2023
 */
public class Kaboom extends Actor
{
    private int lifetime; //lifespan of kaboom
    private final int THRESHOLD = 3; //threshold of kaboom

    /**
     * constructor, sets lifetime to threshold
     */
    public Kaboom()
    {
        lifetime = THRESHOLD;
    }

    /**
     * act method for kaboom behavior
     * Moves itself when lifetime is 0
     * Lifetime continues decreasing with each method call
     */
    public void act()
    {
        if(lifetime == 0)
        {
            removeSelfFromGrid();
        }
        lifetime--;
    }
}
