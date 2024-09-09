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
		// Method to read in city coordinates, name, and population
		Scanner input = FileUtils.openToRead("cities.txt");
		
		while (input.hasNext()) {
			String line = input.nextLine();
			String first, second;
			first = line.substring(0, 5);
			second = line.substring(6, 11);
			City temp = new City(Double.parseDouble(first), Double.parseDouble(second), line.substring(12), 0, false);
			cities.add(temp);
		}

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
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setPopulation(int population) {
		this.population = population;
	}
	
	public int getPopulation() {
		return this.population;
	}
	
	public boolean getTopTen() {
		return this.topTen;
	}
	
	public void setTopTen(boolean b) {
		this.topTen = b;
	}
 }
