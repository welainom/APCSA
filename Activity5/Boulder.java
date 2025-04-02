import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import java.awt.Color;


/**
 * The Boulder is similar to a Rock, but it explodes after some time
 * Turns red right before it explodes, and then followed by Kaboom
 * @author Harshil Dalal
 * @since Apr 30, 2023
 */
public class Boulder extends Actor
{
    private int lifetime; //lifespan of boulder
    private final int THRESHOLD = 3; //to set color to red

    /**
     * constructor, sets color null and lifetime random
     */
    public Boulder()
    {
        setColor(null);
        lifetime = (int)(Math.random()*200)+1; //1-200
    }

    /**
     * constructor, sets color null and lifetime based on input
     */
    public Boulder(int lifetime2)
    {
        setColor(null);
        lifetime = lifetime2;
    }

    /**
     * act method for boulder behavior
     * Whether to explode when lifetime is 0
     * Or set red when lifetime is less than threshold
     * Lifetime continues to decrease with each call
     */
    public void act()
    {
        if(lifetime == 0)
        {
            Location loc = getLocation();
            Kaboom kaboom = new Kaboom();
            kaboom.putSelfInGrid(getGrid(), loc);
        } else if(lifetime < THRESHOLD)
        {
            setColor(Color.RED);
        }
        lifetime--;
    }
}
