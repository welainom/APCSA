/**
 *	The object to store US state information.
 *
 *	@author		William Liu
 *	@since		5/23/25
 */
public class State implements Comparable<State>
{
	private String name;			// state name
	private String abbreviation;	// state abbreviation
	private int population;			// state population
	private int area;				// state area in sq miles
	private int reps;				// number of House Reps
	private String capital;			// state capital city
	private int month;				// month joined Union
	private int day;				// day joined Union
	private int year;				// year joined Union
	
	public State(String name, String abbreviation, int population, int area, 
				int reps, String capital, int month, int day, int year) {
		this.name = name;
		this.abbreviation = abbreviation;
		this.population = population;
		this.area = area;
		this.reps = reps;
		this.capital = capital;
		this.month = month;
		this.day = day;
		this.year = year;
	}
	
	@Override
	public int compareTo(State other) 
	{	return this.getName().compareTo(other.getName()); }
	
	public String getName ( )
	{	return name; }
	
	@Override
	public String toString() {
		return String.format("%-20s %-5s %9d %8d %7d %-20s %2d %2d %5d", 
			name, abbreviation, population, area, reps, capital, month, day, year);
	}
	
}
