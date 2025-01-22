import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordUtilities {

    private List<String> words;		// list of words
	
	public WordUtilities() {
		words = new ArrayList<String>();
	}
	
	/**
	 *	Read in the words from fileName
	 *	@param fileName		name of the file containing words
	 */
	public void readWordsFromFile(String fileName) {
		Scanner inFile = FileUtils.openToRead(fileName);
        
		while (inFile.hasNext()) {
			words.add(inFile.next());
        }

		inFile.close();		
	}
	
	/**
	 *	Uses binary search to find a word in the word List
	 *	Precondition: words must be sorted in ascending order
	 *	@param word		the target word to find
	 *	@return			if found, the index of the word inside words;
	 *					if not found, a negative number
	 */
	public int findWord(String word) {
		return binarySearch(words, word);
	}
	
	/**
	 *	Binary Search - find the target in the List
	 *	Preconditions: listOfWords is sorted in ascending order
	 *	@param listOfWords		List of 0 or more words to check
	 *	@param target			the word to look for
	 *	@return					if found, the index of the word inside words;
	 *							if not found, a negative number
	 */
	public int binarySearch(List<String> listOfWords, String target) {
		// if listOfWords is empty then return not found
		if (listOfWords.size() == 0) return -1;
		
		// otherwise, iteratively perform binary search to find target word
		return binarySearchIterative(listOfWords, target);
	}
	
	/**
	 *	Recursive binary search - find the target word in the list between low and
	 *			high indices
	 *	Precondition: list of words must be sorted in ascending order
	 *	@param listOfWords		list of words to search
	 *	@param target			the word to search for
	 *	@param low				the low index of range to search
	 *	@param high				the high index of range to search
	 *	@return					if found, the index of the word inside words;
	 *							if not found, a negative number
	 */
	public int binarySearchRecurse(List<String> listOfWords, String target, int low, int high) {
		// if low index is greater than high, target not found and return negative number
		if(low < high) 
			return -1;
		// compute middle index
		int middle = (low + high) / 2;
		// compare the target to the mid index
		int compare = target.compareTo(listOfWords.get(middle));
		// if target is equal to mid then return the index of the matching word
		if(compare == 0) {
			return middle;
		}
		// if target is less than mid, then check bottom of list recursively
		else if(compare < 0) {
			return binarySearchRecurse(listOfWords, target, low, middle-1);
		}
		// otherwise, target is greater than mid so check top of list recursively
		else if(compare > 0) {
			return binarySearchRecurse(listOfWords, target, middle+1, high);
		}
		return -1;	// temporary, just to make compiling work, remove later
	}
	
	/**
	 *	Iterative binary search - find the target word in the list
	 *	Precondition: list of words must be sorted in ascending order
	 *	@param listOfWords		list of words to search
	 *	@param target			the word to search for
	 *	@return					if found, the index of the word inside words;
	 *							if not found, a negative number
	 */
	public int binarySearchIterative(List<String> listOfWords, String target) {
		// Insert your code here
		int left = 0;
		int right = listOfWords.size() - 1;
		while(left <= right)
		{
			int mid = (left + right) / 2;
			int compare = target.compareTo(listOfWords.get(mid));
			
			if(compare == 0) {
				return mid;
			}
			else if(compare < 0) {
				right = mid - 1;
			}
			else {
				left = mid + 1;
			}
		}
		// if target not found in list return negative number
		return -1;
	}

    /**
	 *	Determines if a word's characters match a group of letters
	 *	@param word		the word to check
	 *	@param letters	the letters
	 *	@return			true if the word's chars match; false otherwise
	 */
	private boolean wordMatch(String word, String letters) {
		// if the word is longer than letters return false
		if (word.length() > letters.length()) return false;
		
		// while there are still characters in word, check each word character
		// with letters
		while (word.length() > 0) {
			// using the first character in word, find the character's index inside letters
			// and ignore the case
			int index = letters.toLowerCase().indexOf(Character.toLowerCase(word.charAt(0)));
			// if the word character is not in letters, then return false
			if (index < 0) return false;
			
			// remove character from word and letters
			word = word.substring(1);
			letters = letters.substring(0, index) + letters.substring(index + 1);
		}
		// all word letters were found in letters
		return true;
	}
	
	/**
	 *	finds all words that match some or all of a group of alphabetic characters
	 *	Precondition: letters can only contain alphabetic characters a-z and A-Z
	 *	@param letters		group of alphabetic characters
	 *	@return				an ArrayList of all the words that match some or all
	 *						of the characters in letters
	 */
	public ArrayList<String> allWords(String letters) {
		ArrayList<String> wordsFound = new ArrayList<String>();
		// check each word in the database with the letters
		for (String word: words)
			if (wordMatch(word, letters))
				wordsFound.add(word);
		return wordsFound;
	}
	
	/**
	 *	Sort the words in the database
	 */
	public void sortWords() {
		SortMethods sm = new SortMethods();
		sm.mergeSort(words);
	}

}