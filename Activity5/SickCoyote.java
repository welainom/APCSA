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
	private int lifetime;				
	private final int THRESHOLD = 10;	

	// Sets to all the default values
	public SickCoyote() {
		setColor(null);
		lifetime = THRESHOLD;
	}

	public SickCoyote(int time) {
		setColor(null);
		lifetime = time;
	}
	
	public void act() {

		// When lifetime reaches 0 replace with a normal coyote
		if (lifetime == 0) {
			Coyote coyote = new Coyote();
			coyote.putSelfInGrid(getGrid(), getLocation());
		}
		lifetime--;
	}

}


