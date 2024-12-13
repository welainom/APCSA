import java.util.Comparator;

/**
 *     City Comparator By Name. 
 *     Used to compare cities by name instead of population.
 *     @author  William Liu
 *     @since   12/8/24
 */
public class CityComparatorByName implements Comparator<City> {
        public int compare(City a, City b) {
                if (!a.getName().equals(b.getName())) {
                        return a.getName().compareTo(b.getName());
                }
                return a.getPopulation() - b.getPopulation();
        }

        public boolean equals(City a, City b) {
                return a.getName().equals(b.getName());
        }
}
