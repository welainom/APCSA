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

	// In both constructors set fields to default 
	public SickCoyote() {
		setColor(null);
		lifetime = THRESHOLD;
	}

	public SickCoyote(int time) {
		setColor(null);
		lifetime = time;
	}
	
	public void act() {

		// If lifetime is 0, turn it into a new coyote
		if (lifetime == 0) {
			Coyote coyote = new Coyote();
			coyote.putSelfInGrid(getGrid(), getLocation());
		}
		lifetime--;
	}

}


