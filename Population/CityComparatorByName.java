import java.util.Comparator;

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
