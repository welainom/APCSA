import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *	Population - Uses sorts to process data on cities across the US.
 * 				 Operations supported:
 * 				 1. Fifty least populous cities in USA (Selection Sort)
 *				 2. Fifty most populous cities in USA (Merge Sort)
 *				 3. First fifty cities sorted by name (Insertion Sort)
 *				 4. Last fifty cities sorted by name descending (Merge Sort)
 *				 5. Fifty most populous cities in named state (Merge Sort)
 *				 6. All cities matching a name sorted by population (Merge Sort)
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author		William Liu
 *	@since		12/8/24
 */
public class Population {
	
	// List of cities
	private List<City> cities;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";

	private CityComparatorByName comp;
	
	public Population() {
		cities = new ArrayList<City>();
		comp = new CityComparatorByName();
	}
	
	public static void main(String[] args) {
		Population p = new Population();
		p.run();
	}
	
	public void run() {
		// Load cities and print intro stuff
		loadData();
		printIntroduction();
		System.out.println("\n" + cities.size() + " cities in database\n");
		
		// variables for loop
		int choice = -1;
		long startTime, endTime;
		boolean finished = false;
		SortMethods s = new SortMethods();
		
		while (choice != 9) { // while not quit
			printMenu();
			
			// get user choice 
			while (choice == -1 || !(1 <= choice && choice <= 6) && choice != 9) {
				choice = Prompt.getInt("\nEnter Selection\n");
			}
			
			// process each choice
			switch(choice) {
				case 1:
					System.out.println("\n50 Least Populous States\n");
					
					startTime = System.currentTimeMillis();
					s.selectionSort(cities); // Sorting
					endTime = System.currentTimeMillis();
					
					// Print cities and time
					for (int i = 0 ;i<50; i++) System.out.printf("%-3s %s\n", "" + (i + 1) + ":", cities.get(i));
					System.out.println("\nElapsed Time " + (endTime - startTime) + " milliseconds\n");
					break;
				case 2:
					System.out.println("\n50 Most Populous States\n");
					
					startTime = System.currentTimeMillis();
					s.mergeSort(cities); // Sorting
					endTime = System.currentTimeMillis();
					
					// Print cities and time
					for (int i = 0 ;i<50; i++) System.out.printf("%-3s %s\n", "" + (i + 1) + ":", cities.get(i));
					System.out.println("\nElapsed Time " + (endTime - startTime) + " milliseconds\n");
					break;
				case 3:
					System.out.println("\n50 Cities Sorted by Name\n");
					
					startTime = System.currentTimeMillis();
					s.insertionSort(cities, comp); // Sorting
					endTime = System.currentTimeMillis();
					
					// Print cities and time
					for (int i = 0 ;i<50; i++) System.out.printf("%-3s %s\n", "" + (i + 1) + ":", cities.get(i));
					System.out.println("\nElapsed Time " + (endTime - startTime) + " milliseconds\n");
					break;
				case 4:
					System.out.println("\n50 Cities Sorted by Name Descending\n");
					
					startTime = System.currentTimeMillis();
					s.mergeSortOther(cities, comp); // Sorting
					endTime = System.currentTimeMillis();
					
					// Print cities and time
					for (int i = 0 ;i<50; i++) System.out.printf("%-3s %s\n", "" + (i + 1) + ":", cities.get(i));
					System.out.println("\nElapsed Time " + (endTime - startTime) + " milliseconds\n");
					break;
				case 5:
					String stateName = "";
					ArrayList<City> newCities1 = new ArrayList<City>();
					
					// Get user state name
					while (newCities1.size() == 0) {
						stateName = Prompt.getString("Enter State Name (ie. Alabama)");
						
						// If there are no cities that have the same state, the state does not exist
						for (City c : cities) {
							if (c.getState().equals(stateName)) newCities1.add(new City(c));
						}
					}
						
					System.out.println("\n50 Most Populous Cities in a State\n");
					
					startTime = System.currentTimeMillis();
					s.mergeSort(newCities1); // Sorting
					endTime = System.currentTimeMillis();
					
					// Print cities and time. Math.min in case there are less than 50 cities
					for (int i = 0 ;i<Math.min(50, newCities1.size()); i++) System.out.printf("%-3s %s\n", "" + (i + 1) + ":", newCities1.get(i));
					System.out.println("\nElapsed Time " + (endTime - startTime) + " milliseconds\n");
					break;
				case 6:
					String name = "";
					ArrayList<City> newCities2 = new ArrayList<City>();
					
					// Get user city Name
					while (newCities2.size() == 0) {
						name = Prompt.getString("Enter City Name");
						
						// If there are no cities with this name, it is invalid
						for (City c : cities) {
							if (c.getName().equals(name)) newCities2.add(new City(c));
						}
					}
				
					System.out.println("\n50 Most Populous Cities with a Name\n");
					
					startTime = System.currentTimeMillis();
					s.mergeSort(newCities2); // Sorting
					endTime = System.currentTimeMillis();
					
					// Print cities and time. Math.min in case there are less than 50 cities
					for (int i = 0 ;i<Math.min(50, newCities2.size()); i++) System.out.printf("%-3s %s\n", "" + (i + 1) + ":", newCities2.get(i));
					System.out.println("\nElapsed Time " + (endTime - startTime) + " milliseconds\n");
					break;
				case 9:
				
					// If 9, end the program
					finished = true;
					System.out.println("Thanks for using Population!");
					break;
			}
			if (!finished) choice = -1;
		}
	}

	/**
	* Swaps two City objects in a list
	* @param cities list of City objects
	* @param x index of the first object to swap
	* @param y index of the second object to swap
	*/
	private void swap(List<City> cities, int x, int y) {
		City temp = cities.get(x);
		cities.set(x, cities.get(y));
		cities.set(y, temp);
	}
	
	/**
	* Selection Sort algorithm - in ascending order
	* @param cities list of City objects to sort
	*/
	public void selectionSort(List<City> cities) {
		int n = cities.size();
		for (int i = 0; i < n - 1; i++) {
			int minIdx = i;
			for (int j = i + 1; j < n; j++) {
				if (cities.get(j).compareTo(cities.get(minIdx)) < 0) {
					minIdx = j;
				}
			}
			swap(cities, i, minIdx);
		}
	}
	
	/**
	* Insertion Sort algorithm - in ascending order
	* @param cities list of City objects to sort
	*/
	public void insertionSort(List<City> cities) {
		int n = cities.size();
		for (int i = 1; i < n; i++) {
			City key = cities.get(i);
			int j = i - 1;
			while (j >= 0 && comp.compare(cities.get(j), key) > 0) {
				cities.set(j + 1, cities.get(j));
				j--;
			}
		cities.set(j + 1, key);
		}
	}
	
	/**
	* Merge Sort algorithm - in ascending order
	* @param cities list of City objects to sort
	*/
	public void mergeSort(List<City> cities) {
		if (cities.size() > 1) {
			int mid = cities.size() / 2;
			
			// Split the list into two halves
			List<City> left = new ArrayList<>(cities.subList(0, mid));
			List<City> right = new ArrayList<>(cities.subList(mid, cities.size()));
			
			// Recursively sort the two halves
			mergeSort(left);
			mergeSort(right);
			
			// Merge the sorted halves
			merge(cities, left, right);
		}
	}
	
	/**
	* Merges two sorted lists into one sorted list
	* @param cities the original list to merge into
	* @param left the left sorted list
	* @param right the right sorted list
	*/
	private void merge(List<City> cities, List<City> left, List<City> right) {
		int i = 0, j = 0, k = 0;
		
		// Merge elements while both lists have elements
		while (i < left.size() && j < right.size()) {
			if (left.get(i).compareTo(right.get(j)) >= 0) {
				cities.set(k++, left.get(i++));
			} 
			else {
				cities.set(k++, right.get(j++));
			}
		}
		
		// Copy any remaining elements from left
		while (i < left.size()) {
			cities.set(k++, left.get(i++));
		}
		
		// Copy any remaining elements from right
		while (j < right.size()) {
			cities.set(k++, right.get(j++));
		}
	}
	
	
	/**
	* 	Merge sort for sorting by city name instead of population in descending order
	* 	@param cities 	list of City objects to sort
	*/
	public void mergeSortOther(List<City> cities) {
		if (cities.size() > 1) {
			int mid = cities.size() / 2;
			
			// Split the list into two halves
			List<City> left = new ArrayList<>(cities.subList(0, mid));
			List<City> right = new ArrayList<>(cities.subList(mid, cities.size()));
			
			// Recursively sort the two halves
			mergeSortOther(left);
			mergeSortOther(right);
			
			// Merge the sorted halves
			mergeOther(cities, left, right);
		}
	}
	
	/**
	* Merges two sorted lists into one sorted list
	* @param cities 	the original list to merge into
	* @param left 		the left sorted list
	* @param right 	the right sorted list
	*/
	private void mergeOther(List<City> cities, List<City> left, List<City> right) {
		int i = 0, j = 0, k = 0;
		
		// Merge elements while both lists have elements
		while (i < left.size() && j < right.size()) {
			if (comp.compare(left.get(i), right.get(j)) >= 0) {
				cities.set(k++, left.get(i++));
			} 
			else {
				cities.set(k++, right.get(j++));
			}
		}
		
		// Copy any remaining elements from left
		while (i < left.size()) {
			cities.set(k++, left.get(i++));
		}
		
		// Copy any remaining elements from right
		while (j < right.size()) {
			cities.set(k++, right.get(j++));
		}
	}
	
	// Loading data. Use delimiter to split into the four data points
	public void loadData() {
		FileUtils f = new FileUtils();
		
		Scanner in = f.openToRead(DATA_FILE);
		in.useDelimiter("[\t\n]");
		while (in.hasNext()) {
			String line = in.nextLine();
			Scanner lineScan = new Scanner(line);
			lineScan.useDelimiter("\t"); // split by tabs not spaces
			
			// New city object and intialization
			City newCity;
			String state, name, type;
			int population;
			state = lineScan.next();
			name = lineScan.next();
			type = lineScan.next();
			population = Integer.parseInt(lineScan.next());
			newCity = new City(state, name, type, population);
			
			// add it to the list
			cities.add(newCity);
		}
	}
	
	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
}
