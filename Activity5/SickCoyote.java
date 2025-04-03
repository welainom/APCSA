import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import java.util.ArrayList;
import java.awt.Color;


/**
 * @author 	William Liu
 * @since 	3/26/25
 */
public class SickCoyote extends Actor {
	private int lifetime;				// time will stay sick
	private final int THRESHOLD = 10;	// the duration of sickness -- default value

	/**
	 * Creates default sick coyote
	 * Sets the color to null and sets the coyote lifetime to the default duration
	 */
	public SickCoyote() {
		setColor(null);
		lifetime = THRESHOLD;
	}

	/**
	 * Creates sick coyote based on input
	 * Sets the color to null and sets the coyote lifetime to the given parameter
	 * @param time 		amount of time the coyote is sick
	 */
	public SickCoyote(int time) {
		setColor(null);
		lifetime = time;
	}
	
	/**
	 * Decrements the lifetime field until it hits zero
	 * A normal Coyote will then take the place of sick coyote
	 */
	public void act() {
		if (lifetime == 0) {
			Coyote coyote = new Coyote();
			coyote.putSelfInGrid(getGrid(), getLocation());
		}
		lifetime--;
	}

}


