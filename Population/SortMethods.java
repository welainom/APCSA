import java.util.ArrayList;
import java.util.List;

/**
 * SortMethods - Sorts a list of City objects based on different conditinos
 *
 * @author		William Liu
 * @since		12/8/24
 */
public class SortMethods {

    /**
     * Bubble Sort algorithm - in ascending order
     * @param cities list of City objects to sort
     */
    public void bubbleSort(List<City> cities) {
        int n = cities.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (cities.get(j).compareTo(cities.get(j + 1)) > 0) {
                    swap(cities, j, j + 1);
                }
            }
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
            while (j >= 0 && cities.get(j).compareTo(key, "") > 0) {
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
            } else {
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
            if (comp.compareTo(left.get(i), right.get(j)) >= 0) {
                cities.set(k++, left.get(i++));
            } else {
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
     * Print a list of City objects to the screen
     * @param cities the list of City objects
     */
    public void printList(List<City> cities) {
        for (City city : cities) {
            System.out.println(city);
        }
    }
}
