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
		map.setupCanvas();
	}
	
	public void readCities() {
		Scanner input = FileUtils.openToRead("cities.txt");
		
		while (input.hasNext()) {
			String line = input.nextLine();
			String first, second;
			first = line.substring(0, 5);
			second = line.substring(6, 11);
			City temp = new City(Double.parseDouble(first), Double.parseDouble(second), line.substring(12));
			cities.add(temp);
		}
/*
		for (int i = 0; i<cities.size(); i++) {
			System.out.println(cities.get(i).getX() + " " + cities.get(i).getY() + " " + cities.get(i).getName());
		}*/
	}
	
	public void setupCanvas() {
		StdDraw.setTitle("USMap");
		StdDraw.setCanvasSize(900, 512);
		StdDraw.setXscale(128.0, 65.0);
		StdDraw.setYscale(22.0, 52.0);
		StdDraw.setPenColor(StdDraw.GRAY);
		StdDraw.setPenRadius(0.006);
		
		for (int i = 0; i<cities.size(); i++) {
			StdDraw.point(cities.get(i).getY(), cities.get(i).getX());
		}
	}
}

class City {
	private double x, y;
	private String name;
	
	public City(double x, double y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
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
}
