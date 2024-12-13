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
                    swap(cities, j, j + 1); //swap if its not in order
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
            int minIdx = i; // index of min element
            for (int j = i + 1; j < n; j++) {
                if (cities.get(j).compareTo(cities.get(minIdx)) < 0) {
                    minIdx = j; // find min
                }
            }
            swap(cities, i, minIdx); // swap the min
        }
    }

    /**
     * Insertion Sort algorithm - in ascending order
     * @param cities list of City objects to sort
     */
    public void insertionSort(List<City> cities, CityComparatorByName c) {
        int n = cities.size();
        for (int i = 1; i < n; i++) {
            City key = cities.get(i); // select element
            int j = i - 1;
            while (j >= 0 && c.compare(cities.get(j), key) <= 0) {
                cities.set(j + 1, cities.get(j));
                j--;
            } // find where it fits in the first split of array
            cities.set(j + 1, key); // insert
        }
    }

    /**
     * Merge Sort algorithm - in ascending order
     * @param cities list of City objects to sort
     */
    public void mergeSort(List<City> cities) {
        if (cities.size() > 1) {
            int mid = cities.size() / 2;

            // split list into two halves
            List<City> left = new ArrayList<>();
            List<City> right = new ArrayList<>();
			left = cities.subList(0, mid);
			right = cities.subList(mid, cities.size());
			
            // sort the two halves
            mergeSort(left);
            mergeSort(right);

            // merge sorted halves
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

        // merge elements
        while (i < left.size() && j < right.size()) {
            if (left.get(i).compareTo(right.get(j)) >= 0) {
                cities.set(k++, left.get(i++)); 
                // This ++ increments after so set in not messed up
            } else {
                cities.set(k++, right.get(j++));
            }
        }

        // copy remaining elements from left
        while (i < left.size()) {
            cities.set(k++, left.get(i++));
        }

        // copy remaining elements from right
        while (j < right.size()) {
            cities.set(k++, right.get(j++));
        }
    }


	/**
	 * 	Merge sort for sorting by city name instead of population in descending order
	 * 	@param cities 	list of City objects to sort
	 */
	public void mergeSortOther(List<City> cities, CityComparatorByName c) {
        if (cities.size() > 1) {
            int mid = cities.size() / 2;

            // split list into two halves
            List<City> left = new ArrayList<>();
            List<City> right = new ArrayList<>();
			left = cities.subList(0, mid);
			right = cities.subList(mid, cities.size());
            // sort the two halves
            mergeSortOther(left, c);
            mergeSortOther(right, c);

            // merge sorted halves
            mergeOther(cities, left, right, c);
        }
    }

    /**
     * Merges two sorted lists into one sorted list
     * @param cities 	the original list to merge into
     * @param left 		the left sorted list
     * @param right 	the right sorted list
     */
    private void mergeOther(List<City> cities, List<City> left, List<City> right, CityComparatorByName c) {
        int i = 0, j = 0, k = 0;

        // merge elements
        while (i < left.size() && j < right.size()) {
            if (c.compare(left.get(i), right.get(j)) <= 0) {
                cities.set(k++, left.get(i++));
                // This ++ increments after so set in not messed up
            } 
            else {
                cities.set(k++, right.get(j++));
            }
        }

        // copy remaining elements from left
        while (i < left.size()) {
            cities.set(k++, left.get(i++));
        }

        // copy remaining elements from right
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
