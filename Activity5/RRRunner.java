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
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;

/**
 * RRRunner
 * Creates a grid (10x10) with two Roadrunner actors and five Coyote actors
 * @author Harshil
 * @since April 30, 2023
 */
public class RRRunner
{
	/**
	 * main method
	 */
    public static void main(String[] args)
    {
        BoundedGrid<Actor> rrGrid = new BoundedGrid<Actor>(10, 10);
        ActorWorld world = new ActorWorld(rrGrid);
        
		world.add(new Location(2, 2), new RR());
		world.add(new Location(8, 8), new RR());
		
		world.add(new Location(2, 0), new Coyote());
		world.add(new Location(4, 2), new Coyote());
		world.add(new Location(7, 7), new Coyote());
		world.add(new Location(4, 4), new Coyote());
		world.add(new Location(8, 2), new Coyote());

        world.show();
    }
}
