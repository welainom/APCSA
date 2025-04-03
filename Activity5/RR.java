/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */

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
public class RR extends Critter {

	/**
	 * constructor, sets color null
	 */
	public RR() {
		setColor(null);
	}


	/**
	 * Gets all possible valid move locations up to 3 steps away
	 * If there is no obstacle present, it is added as a possible location
	 * @return 		list of valid locations to move to
	 */
	public ArrayList<Location> getMoveLocations() {
		Grid<Actor> gr = getGrid();
        ArrayList<Location> moveLocs = new ArrayList<Location>();
        int[] directions = {Location.NORTH, Location.NORTH, Location.NORTHEAST,
            Location.EAST, Location.SOUTHEAST, Location.SOUTH, Location.SOUTHWEST, 
            Location.WEST, Location.NORTHWEST};
        for (int dir : directions) {
            Location loc = getLocation();
            for (int step = 0; step < 3; ++step) {
                Location next = loc.getAdjacentLocation(dir);

                if (gr.isValid(next)) {
					if ((gr.get(next) == null || gr.get(next) instanceof Boulder || gr.get(next) instanceof Coyote)) {
						moveLocs.add(next);
					}
					if (gr.get(next) != null) {
						step = 3;
					}
                } 
                else {
					step = 3;
				}

                loc = next;
            }
        }
        return moveLocs;
	}
	
	/** Moves the Roadrunner to the given location
	 * If there is a Coyote in that location, turned into a SickCoyote (bumped into)
	 * If there is a Boulder in that location, it will explode and the Roadrunner
	 * will be removed from the grid.
	 * @param 		the location to move to
	 */
	public void makeMove(Location loc) {
		Grid<Actor> gr = getGrid();
        if (loc == null) {
            removeSelfFromGrid();
            return;
		}
        else if (gr.get(loc) instanceof Boulder) {
			Kaboom kaboom = new Kaboom();
			kaboom.putSelfInGrid(gr, loc);
			removeSelfFromGrid();

			return;
		}
		else if (gr.get(loc) instanceof Coyote) {
			SickCoyote sck = new SickCoyote();
			ArrayList<Location> adj = gr.getValidAdjacentLocations(loc);
			if (!adj.isEmpty())
			{
				int rand = (int)(Math.random()*adj.size());
				sck.putSelfInGrid(gr, adj.get(rand));
			}
		}
        moveTo(loc);
    }
}


