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
 * The Sick Coyote is a result of a normal Coyote being hit by a RoadRunner.
 * Becomes normal coyote after a certain lifetime
 * @author Harshil Dalal
 * @since Apr 30, 2023
 */
public class SickCoyote extends Actor
{
	private int lifetime;				// time will stay sick
	private final int THRESHOLD = 10;	// the duration of sickness -- default value

	/**
	 * Creates default sick coyote
	 * Sets the color to null and sets the coyote lifetime to the default duration
	 */
	public SickCoyote()
	{
		setColor(null);
		lifetime = THRESHOLD;
	}

	/**
	 * Creates sick coyote based on input
	 * Sets the color to null and sets the coyote lifetime to the given parameter
	 * @param time 		amount of time the coyote is sick
	 */
	public SickCoyote(int time)
	{
		setColor(null);
		lifetime = time;
	}
	
	/**
	 * Decrements the lifetime field until it hits zero
	 * A normal Coyote will then take the place of sick coyote
	 */
	public void act()
	{
		if (lifetime == 0)
		{
			Coyote coyote = new Coyote();
			coyote.putSelfInGrid(getGrid(), getLocation());
		}
		lifetime--;
	}

}


