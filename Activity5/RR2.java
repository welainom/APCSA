import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import java.util.ArrayList;

/**
 * RR is the RoadRunner that can move very fast. It only dies when hitting a boulder and can move up to three steps at a time
 * Important to note that the RR MUST have a clear path to the actor that is within three steps
 * @author Aaryan Doshi
 * @since May 1, 2023
 */


public class RR2 extends Critter
{

    /*Initialize the color of the RR to be null and initial direction as North*/
    public RR2()
    {
        setColor(null);
        setDirection(Location.NORTH);
    }

    /**
     * Performing the behavior of a RoadRunner. Checking cells within three, determining the actors at those location, and accordingly make decision
     */
    public void act()
    {

        Location loc = getLocation();
        ArrayList<Location> possibleLocations = new ArrayList<Location>();
        /*Get initial starting and ending locations for cells within three*/
        int startRow = loc.getRow() - 3;
        int endRow = loc.getRow() + 3;
        int startCol = loc.getCol() - 3;
        int endCol = loc.getCol() + 3;
        Grid<Actor> gr = getGrid();

        for(int i = startRow; i <= endRow; i++){
            for(int j = startCol; j <= endCol; j++){
                Location awayLoc = new Location(i, j);
                /*Make sure that the location is in bounds*/
                if(gr.isValid(awayLoc)){
                    /*RR can only go to that location if its empty, a Boulder, or a Coyote*/
                    Actor actor = gr.get(awayLoc);
                    if(actor == null || actor instanceof Boulder || actor instanceof Coyote){

                        /*IMPORTANT to note that there should be CLEAR PATH. Below I consider all possible squares
                          boulders, coyotes, null. But I ensure there is nothing between this square and the Road Runner*/

                        /*4 Different Cases as Shown below. (2 * 2) because of conditions for rows and cols*/
                        if(loc.getRow() > awayLoc.getRow() && loc.getCol() < awayLoc.getCol()){
                            Location loc1 = new Location(i+1, j-1);
                            Location loc2 = new Location(i+2, j-2);
                            Location loc3 = new Location(i+3, j-3);

                            /*Ok, I got three location BUT maybe they arent a distance of three away, hence the following conditions*/
                            /*SAME Logic Applied in Below Code as well*/

                            if(gr.isValid(loc1) && !gr.isValid(loc2) && !gr.isValid(loc3)){
                                if(gr.get(loc1) == null) possibleLocations.add(awayLoc);
                            }

                            else if(gr.isValid(loc1) && gr.isValid(loc2) && !gr.isValid(loc3)){
                                if(gr.get(loc1) == null && gr.get(loc2) == null) possibleLocations.add(awayLoc);
                            }

                            else if(gr.isValid(loc1) && gr.isValid(loc2) && gr.isValid(loc3)){
                                if(gr.get(loc1) == null && gr.get(loc2) == null & gr.get(loc3) == null) possibleLocations.add(awayLoc);
                            }
                        }

                        else if(loc.getRow() > awayLoc.getRow() && loc.getCol() > awayLoc.getCol()){
                            Location loc1 = new Location(i+1, j+1);
                            Location loc2 = new Location(i+2, j+2);
                            Location loc3 = new Location(i+3, j+3);

                            if(gr.isValid(loc1) && !gr.isValid(loc2) && !gr.isValid(loc3)){
                                if(gr.get(loc1) == null) possibleLocations.add(awayLoc);
                            }

                            else if(gr.isValid(loc1) && gr.isValid(loc2) && !gr.isValid(loc3)){
                                if(gr.get(loc1) == null && gr.get(loc2) == null) possibleLocations.add(awayLoc);
                            }

                            else if(gr.isValid(loc1) && gr.isValid(loc2) && gr.isValid(loc3)){
                                if(gr.get(loc1) == null && gr.get(loc2) == null & gr.get(loc3) == null) possibleLocations.add(awayLoc);
                            }
                        }

                        else if(loc.getRow() < awayLoc.getRow() && loc.getCol() > awayLoc.getCol()){
                            Location loc1 = new Location(i-1, j+1);
                            Location loc2 = new Location(i-2, j+2);
                            Location loc3 = new Location(i-3, j+3);

                            if(gr.isValid(loc1) && !gr.isValid(loc2) && !gr.isValid(loc3)){
                                if(gr.get(loc1) == null) possibleLocations.add(awayLoc);
                            }

                            else if(gr.isValid(loc1) && gr.isValid(loc2) && !gr.isValid(loc3)){
                                if(gr.get(loc1) == null && gr.get(loc2) == null) possibleLocations.add(awayLoc);
                            }

                            else if(gr.isValid(loc1) && gr.isValid(loc2) && gr.isValid(loc3)){
                                if(gr.get(loc1) == null && gr.get(loc2) == null & gr.get(loc3) == null) possibleLocations.add(awayLoc);
                            }
                        }

                        else if(loc.getRow() < awayLoc.getRow() && loc.getCol() < awayLoc.getCol()){
                            Location loc1 = new Location(i-1, j-1);
                            Location loc2 = new Location(i-2, j-2);
                            Location loc3 = new Location(i-3, j-3);

                            if(gr.isValid(loc1) && !gr.isValid(loc2) && !gr.isValid(loc3)){
                                if(gr.get(loc1) == null) possibleLocations.add(awayLoc);
                            }

                            else if(gr.isValid(loc1) && gr.isValid(loc2) && !gr.isValid(loc3)){
                                if(gr.get(loc1) == null && gr.get(loc2) == null) possibleLocations.add(awayLoc);
                            }

                            else if(gr.isValid(loc1) && gr.isValid(loc2) && gr.isValid(loc3)){
                                if(gr.get(loc1) == null && gr.get(loc2) == null & gr.get(loc3) == null) possibleLocations.add(awayLoc);
                            }
                        }
                    }
                }
            }
        }

        /*Get a random actor from all of the possible locations in arraylist*/
        /*In case no locations are available, have this condition*/
        if(possibleLocations.size() != 0){

            int randIndex = (int)(Math.random() * possibleLocations.size());
            Location randLoc = possibleLocations.get(randIndex);
            Actor actorMet = getGrid().get(randLoc);

            /*When coming across a boulder, make it explode. Remove the roadrunner from the grid*/
            if(actorMet instanceof Boulder){
                Kaboom obj = new Kaboom();
                obj.putSelfInGrid(gr, actorMet.getLocation());
                removeSelfFromGrid();
            }

            /*When coming across a Coyote, remove the coyote, replace it with RR, add sickCoyote in random adjacent location*/
            else if(actorMet instanceof Coyote){
                /*Location of the coyote*/
                Location currActorLocation = actorMet.getLocation();
                actorMet.removeSelfFromGrid();
                removeSelfFromGrid();
                /*replacing the coyote with an RR*/
                putSelfInGrid(gr, currActorLocation);
                /*Get a list of all adjacent locations to coyote*/
                ArrayList<Location> adjLocations = getGrid().getEmptyAdjacentLocations(currActorLocation);
                int randIndex2 = (int)(Math.random() * adjLocations.size());
                Location newLoc = adjLocations.get(randIndex2);
                SickCoyote obj2  = new SickCoyote();
                /*place sick coyote in a random, adjacent, empty, location*/
                obj2.putSelfInGrid(gr, newLoc);
            }

            /*otherwise just move to the random location*/
            else{
                moveTo(randLoc);
            }
        }
    }
}

