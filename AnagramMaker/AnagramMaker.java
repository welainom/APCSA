/**
 *	AnagramMaker - <description goes here>
 *
 *	Requires the WordUtilities, SortMethods, Prompt, and FileUtils classes
 *
 *	@author		William Liu
 *	@since		1/19/25
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AnagramMaker {
								
	private final String FILE_NAME = "randomWords.txt";	// file containing all words
	
	private WordUtilities wu;	// the word utilities for building the word
								// database, sorting the database,
								// and finding all words that match
								// a string of characters
	
	// variables for constraining the print output of AnagramMaker
	private int numWords;		// the number of words in a phrase to print
	private int maxPhrases;		// the maximum number of phrases to print
	private int numPhrases;		// the number of phrases that have been printed
	
	private ArrayList<String> anagrams;

	/*	Initialize the database inside WordUtilities
	 *	The database of words does NOT have to be sorted for AnagramMaker to work,
	 *	but the output will appear in order if you DO sort.
	 */
	public AnagramMaker() {
		wu = new WordUtilities();
		wu.readWordsFromFile(FILE_NAME);
		wu.sortWords();

		anagrams = new ArrayList<String>();
		numPhrases = 0;
	}
	
	public static void main(String[] args) {
		AnagramMaker am = new AnagramMaker();
		am.run();
	}
	
	/**	The top routine that prints the introduction and runs the anagram-maker.
	 */
	public void run() {
		printIntroduction();
		runAnagramMaker();
		System.out.println("\nThanks for using AnagramMaker!\n");
	}
	
	/**
	 *	Print the introduction to AnagramMaker
	 */
	public void printIntroduction() {
		System.out.println("\nWelcome to ANAGRAM MAKER");
		System.out.println("\nProvide a word, name, or phrase and out comes their anagrams.");
		System.out.println("You can choose the number of words in the anagram.");
		System.out.println("You can choose the number of anagrams shown.");
		System.out.println("\nLet's get started!");
	}
	
	/**
	 *	Prompt the user for a phrase of characters, then create anagrams from those
	 *	characters.
	 */
	public void runAnagramMaker() {
		String input = Prompt.getString("Word(s), name or phrase (q to quit)");
		numWords = Prompt.getInt("Number of words in anagram");
		maxPhrases = Prompt.getInt("Maximum number of anagrams to print");

		String phrase = "";
		for (int i = 0; i<input.length(); i++) { 
			char c = input.charAt(i);
			if ('a' <= c && c <= 'z' || 'A' <= c && c <= 'Z') {
				phrase += c;
			}
		}

		generateAnagrams(phrase);
	}

	public void generateAnagrams(String phrase) {
		if (phrase.length() >= 1 && anagrams.size() < numWords) {
			ArrayList<String> allWords = wu.allWords(phrase);
			
			for (String s : allWords) {
				anagrams.add(s);
				String new_phrase = "";
				int i = 0, j = 0;

				char[] s_arr = s.toCharArray();
				char[] p_arr = phrase.toCharArray();
				Arrays.sort(s_arr);
				Arrays.sort(p_arr);

				while (i < s.length() && j < phrase.length()) {
					if (s_arr[i] != p_arr[j]) {
						new_phrase += p_arr[j];
						j++;
					}
					else {
						i++; 
						j++;
					}
				}
				for (int a = j; a<phrase.length(); a++) {
					new_phrase += p_arr[a];
				}
				generateAnagrams(new_phrase);
				anagrams.remove(s);
			}
		}
		else {
			if (numPhrases >= maxPhrases) return;

			for (String s : anagrams) {
				System.out.print(s + " ");
			}
			numPhrases++;
			System.out.println("");
		
			return;
		}
	}
}
