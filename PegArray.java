/**
 *  This class creates and manages one array of pegs from the game MasterMind.
 *
 *  @author	William Liu
 *  @since	September 27, 2024
*/

public class PegArray {

	// array of pegs
	private Peg [] pegs;

	// the number of exact and partial matches for this array
	// as matched against the master.
	// Precondition: these values are valid after getExactMatches() and getPartialMatches()
	//				are called
	private int exactMatches, partialMatches;
		
	/**
	 *	Constructor
	 *	@param numPegs	number of pegs in the array
	 */
	public PegArray(int numPegs) {	
		exactMatches = 0;
		partialMatches = 0;
		pegs = new Peg[numPegs];
		for (int i = 0; i<numPegs; i++) {
			pegs[i] = new Peg();
		}
	}
	
	/**
	 *	Return the peg object
	 *	@param n	The peg index into the array
	 *	@return		the peg object
	 */
	public Peg getPeg(int n) { return pegs[n]; }
	
	/**
	 * 	Sets the peg array to a new peg array
	 * 	
	 * 	@param p	The new peg array
	 */
	public void setPegArray(Peg[] p) {
		for (int i = 0; i<4; i++) {
			pegs[i] = p[i];
		}
	}
	
	/**
	 *  Finds exact matches between master (key) peg array and this peg array
	 *	Postcondition: field exactMatches contains the matches with the master
	 *  @param master	The master (code) peg array
	 *	@return			The number of exact matches
	 */
	public int getExactMatches(PegArray master) {
		exactMatches = 0;
		
		// Find exact matches. if two chars in the same position are the same, increment
		for (int i = 0; i < pegs.length; i++) {
			if (master.getPeg(i).getLetter() == pegs[i].getLetter()) {
				exactMatches++;
			}
		}
		
		return exactMatches;
	}
	
	/**
	 *  Find partial matches between master (key) peg array and this peg array
	 *	Postcondition: field partialMatches contains the matches with the master
	 *  @param master	The master (code) peg array
	 *	@return			The number of partial matches
	 */
	public int getPartialMatches(PegArray master) {
		partialMatches = 0;
		
		// Arrays to track which have been matched
		boolean[] masterMatched = {false, false, false, false}; 
		boolean[] guessMatched = {false, false, false, false}; 
		
		// Mark exact matches first
		for (int i = 0; i < pegs.length; i++) {
			if (master.getPeg(i).getLetter() == pegs[i].getLetter()) {
				guessMatched[i] = true;
				masterMatched[i] = true; 
			}
		}

		// Match and find number of partial matches
		for (int i = 0; i < pegs.length; i++) {
			if (!guessMatched[i]) { 
				for (int j = 0; j < pegs.length; j++) {
					
					// If has not been matched already 
					if (!masterMatched[j] && pegs[i].getLetter() == master.getPeg(j).getLetter()) {
						partialMatches++;
						masterMatched[j] = true;
						break; 
					}
				}
			}
		}
		
		return partialMatches;
	}
	
	// Accessor methods
	// Precondition: getExactMatches() and getPartialMatches() must be called first
	public int getExact() { return exactMatches; }
	public int getPartial() { return partialMatches; }

}
