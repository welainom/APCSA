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

 import info.gridworld.actor.Bug;

public class DancingBug extends Bug
{
    private int[] turnList;
    private int currentStep;

    public DancingBug(int[] turns)
    {
        turnList = turns;
        currentStep = 0;
    }
    public void turn(int times)
    {
        for(int j = 1; j <= times; j++)
        {
            turn();
        }
    }

    public void act()
    {
        if(currentStep == turnList.length)
            currentStep = 0;
        turn (turnList[currentStep]);
        currentStep++;
        super.act();
    }
}