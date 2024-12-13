import java.util.Comparator;

/**
 * CityComparatorByName - Used for comparisons and sorting cities by name
 *
 * @author		William Liu
 * @since		12/8/24
 */

public class CityComparatorByName implements Comparator<City> {
        public int compare(City a, City b) {
                if (a.getName().equals(b.getName())) {
                        return a.getPopulation() - b.getPopulation();
                }
                return a.getName().compareTo(b.getName());
        }

        public boolean equals(City a, City b) {
                return a.getName() == b.getName();
        }
}
