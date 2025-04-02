import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Critter;
import java.util.ArrayList;
import java.awt.Color;

/**
 * A coyote The Coyote drops Stones as he wanders around the grid
 * He moves in straight lines, but stops every couple steps to drop a
 * stone and change direction.
 * @author Harshil Dalal
 * @since Apr 30, 2023
 */
public class Coyote extends Critter
{
    private int steps; //steps taken by coyote
    private int sleep; //tracking when to sleep -- helping variable
    private boolean isWall; //checks if wall is present

    /**
     * constructor, sets default values for variables and fields
     */
    public Coyote()
    {
        setColor(null);
        steps = 5;
        sleep = 5;
        isWall = true;
        int dir = ((int)(Math.random()*360/45));
        setDirection( dir * 45 );
    }

    /**
     * act method determining coyote behavior
     * When the coyote should sleep, wake up
     * Also considers if coyote hit a wall before waking up
     * --> Accordingly places a boulder in empty adjacent locaiton based on that
     */
    public void act() {

        if (steps < 0)
        {
            steps++;
            return;
        }

        if (steps == 0)
        {
            int dir = ((int)(Math.random()*360/45));
            setDirection( dir * 45 );
            if (isWall == false)
            {
                ArrayList<Location> locations = getGrid().getEmptyAdjacentLocations(getLocation());
                if (locations.size() != 0)
                {
                    Location stoneLocation = locations.get((int)(Math.random() * locations.size()));
                    Stone st = new Stone();
                    st.putSelfInGrid(getGrid(), stoneLocation);
                }
            }
        }

        Location nextLoc = getLocation().getAdjacentLocation(getDirection());
        Grid<Actor> gr = getGrid();
        if (!gr.isValid(nextLoc) || gr.get(nextLoc) != null)
        {
            steps = -5;
            sleep = -5;
            if (!gr.isValid(nextLoc))
            {
                isWall = true;
            }
            else {
                isWall = false;
            }

            if (gr.isValid(nextLoc) && gr.get(nextLoc) instanceof Boulder)
            {
                removeSelfFromGrid();
                return;
            }
        } else {
            steps++;
            sleep++;
            if (steps == 6) {
                steps = 0;
                isWall = false;
            }
            moveTo(nextLoc);
        }
    }
}
