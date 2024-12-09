/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author		William Liu
 *	@since		12/8/24
 */
public class City implements Comparable<City> {
	private String state, name, designation;
	private int population;
	// fields
	
	// Normal constructor
	public City(String state, String name, String designation, int population) {
		this.state = state;
		this.name = name;
		this.designation = designation;
		this.population = population;
	}
	
	// Copy constructor
	public City(City other) {
		this.state = other.getState();
		this.name = other.getName();
		this.designation = other.getDesignation();
		this.population = other.getPopulation();
	}
	// constructor
	
	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	public int compareTo(City other) {
		if (this.population != other.getPopulation()) {
			return this.population - other.getPopulation();
		}
		if (this.state != other.getState()) {
			return this.state.compareTo(other.getState());
		}
		return this.name.compareTo(other.getName());
	}
	
	// CompareTo override for sorting by name. Call with compareTo(city, "")\
	// Sort by name first, then population in ascending order
	public int compareTo(City other, String useless) {
		if (!this.name.equals(other.getName())) {
			return this.name.compareTo(other.getName());
		}
		else return this.population - other.getPopulation();
	}
	
	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	public boolean equals(City other) {
		return this.name == other.getName();
	}
	
	/**	Accessor methods */
	public String getState() {
		return this.state;
	}
	public String getName() {
		return this.name;
	}
	public String getDesignation() {
		return this.designation;
	}
	public int getPopulation() {
		return this.population;
	}
	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-22s %-12s %,12d", state, name, designation,
						population);
	}
}
