import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import java.awt.Color;

/**
 * A Stone which is like a Rock
 * but the Stone can change into a Boulder
 * @author Harshil Dalal
 * @since April 9th 2023
 */
public class Stone extends Rock
{
    private int lifetime; //lifespan of stone
    private final int THRESHOLD = 3; //threshold constant

    /**
     * constructor, sets color null and random lifetime
     */
    public Stone()
    {
        setColor(null);
        lifetime = (int)(Math.random()*200)+1; //1-200
    }

    /**
     * constructor, sets color null, input lifetime
     */
    public Stone(int lifetime2)
    {
        setColor(null);
        lifetime = lifetime2;
    }

    /**
     * act method of stone behavior
     * If lifetime 0, turned to boulder
     * If less than threshold, becomes green
     * Continues to decrease with each method call
     */
    public void act()
    {
        if(lifetime == 0)
        {
            Location loc = getLocation();
            Boulder boulder = new Boulder();
            boulder.putSelfInGrid(getGrid(), loc);
        } else if(lifetime < THRESHOLD)
        {
            setColor(Color.GREEN);
        }
        lifetime--;
    }
}
