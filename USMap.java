import java.util.Scanner;
import java.util.ArrayList;
/**
 *	USMap program
 *
 *	@author	William Liu
 *	@since	September 4, 2024
 */


public class USMap {
	private ArrayList<Double> cities_x = new ArrayList<>();
	private ArrayList<Double> cities_y = new ArrayList<>();
	private ArrayList<String> city_names = new ArrayList<>();
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
			
			cities_x.add(Double.parseDouble(first));
			cities_y.add(Double.parseDouble(second));
			city_names.add(line.substring(12));
		}
		
		for (int i = 0; i<cities_x.size(); i++) {
			System.out.println(cities_x.get(i) + " " + cities_y.get(i));
		}
	}
	
	public void setupCanvas() {
		StdDraw.setTitle("USMap");
		StdDraw.setCanvasSize(900, 512);
		StdDraw.setXscale(128.0, 65.0);
		StdDraw.setYscale(22.0, 52.0);
	}
}
