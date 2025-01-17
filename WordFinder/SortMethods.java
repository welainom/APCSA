import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;		// for testing purposes

/**
 *	SortMethods - Sorts an ArrayList of Strings in ascending order.
 *
 *	Requires FileUtils class to compile.
 *	Requires file randomWords.txt to execute a test run.
 *
 *	@author	William Liu
 *	@since	Jan 12 2025
 */
public class SortMethods {
	
	/**
	 *	Merge Sort algorithm - in ascending order
	 *	@param arr		List of String objects to sort
	 */
	public void mergeSort(List<String> arr) {
		mergeSortRecurse(arr, 0, arr.size() - 1);
	}
	
	/**
	 *	Recursive mergeSort method.
	 *	@param arr		List of String objects to sort
	 *	@param first	first index of arr to sort
	 *	@param last		last index of arr to sort
	 */
	public void mergeSortRecurse(List<String> arr, int first, int last) {
		// insert your code here
		if (first >= last) 
			return;
		
		int mid = first + (last - first) / 2;
		mergeSortRecurse(arr, first, mid);
		mergeSortRecurse(arr, mid + 1, last);
		merge(arr, first, mid, last);
	}
	
	/**
	 *	Merge two lists that are consecutive elements in array.
	 *	@param arr		List of String objects to merge
	 *	@param first	first index of first list
	 *	@param mid		the last index of the first list;
	 *					mid + 1 is first index of second list
	 *	@param last		last index of second list
	 */
	public void merge(List<String> arr, int first, int mid, int last) 
	{
		// Insert your code here
		
		int leftSize = mid - first + 1;
		int rightSize = last - mid;

		String[] left = new String[leftSize];
		String[] right = new String[rightSize];

		for (int i = 0; i < leftSize; i++) {
			left[i] = arr.get(first + i);
		}
		for (int i = 0; i < rightSize; i++) {
			right[i] = arr.get(mid + 1 + i);
		}
		
		int i = 0, j = 0, k = first;

		while (i < leftSize && j < rightSize) 
		{
			if (left[i].compareTo(right[j]) <= 0)  {
				arr.set(k, left[i]);
				k++; i++;
			}
			else {
				arr.set(k, right[j]);
				k++; j++;
			}
		}

		while (i < leftSize) {
			arr.set(k, left[i]);
			k++; i++;
		}
		while (j < rightSize) {
			arr.set(k, right[j]);
			k++; j++;
		}
	}

	
	/**
	 *	Print an List of Strings to the screen
	 *	@param arr		the List of Strings
	 */
	public void printArray(List<String> arr) {
		if (arr.size() == 0) System.out.print("(");
		else System.out.printf("( %-15s", arr.get(0));
		for (int a = 1; a < arr.size(); a++) {
			if (a % 5 == 0) System.out.printf(",\n  %-15s", arr.get(a));
			else System.out.printf(", %-15s", arr.get(a));
		}
		System.out.println(" )");
	}
	
	/*************************************************************/
	/********************** Test program *************************/
	/*************************************************************/
	private final String FILE_NAME = "randomWords.txt";
	
	public static void main(String[] args) {
		SortMethods se = new SortMethods();
		se.run();
	}
	
	public void run() {
		List<String> arr = new ArrayList<String>();
		// Fill List with random words from file		
		fillArray(arr);
		
		System.out.println("\nMerge Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		mergeSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
	}
	
	// Fill String array with words
	public void fillArray(List<String> arr) {
		Scanner inFile = FileUtils.openToRead(FILE_NAME);
		while (inFile.hasNext())
			arr.add(inFile.next());
		inFile.close();
	}
}
