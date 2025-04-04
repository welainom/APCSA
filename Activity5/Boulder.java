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
public class Boulder extends Actor {
    private int lifetime; //lifespan of boulder
    private final int THRESHOLD = 3; //to set color to red

    /**
     * constructor, sets color null and lifetime random
     */
    public Boulder() {
        setColor(null);
        lifetime = (int)(Math.random()*200)+1; //1-200
    }

    /**
     * constructor, sets color null and lifetime based on input
     */
    public Boulder(int lifetime) {
        setColor(null);
        this.lifetime = lifetime;
    }

    /**
     * act method for boulder behavior
     * Whether to explode when lifetime is 0
     * Or set red when lifetime is less than threshold
     * Lifetime continues to decrease with each call
     */
    public void act() {
        if(lifetime == 0) {
            Location loc = getLocation();
            Kaboom kaboom = new Kaboom();
            kaboom.putSelfInGrid(getGrid(), loc);
        } 
        else if(lifetime < THRESHOLD) {
            setColor(Color.RED);
        }
        lifetime--;
    }
}
