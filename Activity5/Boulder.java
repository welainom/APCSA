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
    private int lifetime;
    private final int THRESHOLD = 3;

    public Boulder() {
        setColor(null);
        lifetime = (int) (Math.random() * 200) + 1; 
    }

    public Boulder(int lifetime) {
        setColor(null);
        this.lifetime = lifetime;
    }

    public void act() {

        // If lifetime is 0 then replace it with kaboom
        if(lifetime == 0) {
            Location loc = getLocation();
            Kaboom kaboom = new Kaboom();
            kaboom.putSelfInGrid(getGrid(), loc);
        } 

        // If lifetime less than threshold, make it red
        else if(lifetime < THRESHOLD) {
            setColor(Color.RED);
        }
        lifetime--;
    }
}
