import java.util.Scanner;
import java.util.ArrayList;
/**
 *	USMap program
 *
 *	@author	William Liu
 *	@since	September 4, 2024
 */

public class USMap {
	private ArrayList<City> cities = new ArrayList<>();
	
	public static void main(String[] args) {
		USMap map = new USMap();
		map.readCities();
	}
	
	public void readCities() {
		/**
		 *	readCities mathod
		 * 	Method to read in all the data from both text files and create new Cities. 
		*/
		Scanner input = FileUtils.openToRead("cities.txt");
		// Reading in first file
		while (input.hasNext()) {
			String line = input.nextLine();
			String first, second;
			first = line.substring(0, 5);
			second = line.substring(6, 11);
			City temp = new City(Double.parseDouble(first), Double.parseDouble(second), line.substring(12), 0, false);
			cities.add(temp);
		}
		// Reading in second file
		input = FileUtils.openToRead("bigCities.txt");
		int cnt = 1;
		while (input.hasNext()) {
			String line = input.nextLine();
			
			for (City c : cities) {
				int idx = line.indexOf(c.getName());
				if (idx != -1) {
					if (cnt <= 10) c.setTopTen(true);
					c.setPopulation(Integer.parseInt(line.substring(idx + c.getName().length() + 1)));
				}
			}
			cnt++;
		}
		setupCanvas();
		draw();
		input.close();
	}
	
	public void setupCanvas() {
		// Setting up Canvas for drawing
		StdDraw.setTitle("USMap");
		StdDraw.setCanvasSize(900, 512);
		StdDraw.setXscale(128.0, 65.0);
		StdDraw.setYscale(22.0, 52.0);
		StdDraw.setPenColor(StdDraw.GRAY);
		StdDraw.setPenRadius(0.006);
	}
	
	public void draw() {
		// method to draw the cities
		// Iterates through all the cities, and changes color and size of the pen
		for (int i = 0; i<cities.size(); i++) {
			City c = cities.get(i);
			if (c.getPopulation() != 0) {
				StdDraw.setPenRadius(0.6 * (Math.sqrt(c.getPopulation())/18500));
				if (c.getTopTen()) {
					StdDraw.setPenColor(StdDraw.RED);
				}
				else {
					StdDraw.setPenColor(StdDraw.BLUE);
				}
			}
			else {
				StdDraw.setPenColor(StdDraw.GRAY);
				StdDraw.setPenRadius(0.006);
			}
			StdDraw.point(cities.get(i).getY(), cities.get(i).getX());
		}
	}
}

class City {
	/**
	 * City class
	 * 
	 * @author William Liu
	 *	Class to describe a City.
	 * 	Contains x coord, y coord, name, population, and if it is top 10
	 */
	private double x, y;
	private String name;
	private int population;
	private boolean topTen;
	
	public City(double x, double y, String name, int population, boolean topTen) {
		this.x = x;
		this.y = y;
		this.name = name;
		this.population = population;
		this.topTen = topTen;
	}
	
	// methods to get the x and y coords
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	//method to get the city name
	public String getName() {
		return this.name;
	}
	
	// sets the population based on the bigCities file
	public void setPopulation(int population) {
		this.population = population;
	}

	public int getPopulation() {
		return this.population;
	}
	
	public boolean getTopTen() {
		return this.topTen;
	}
	
	// Method to mark if the city is in the top 10 largest based on bigCities file.
	public void setTopTen(boolean b) {
		this.topTen = b;
	}
 }
