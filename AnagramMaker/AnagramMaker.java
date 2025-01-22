/**
 *	AnagramMaker - <description goes here>
 *
 *	Requires the WordUtilities, SortMethods, Prompt, and FileUtils classes
 *
 *	@author		William Liu
 *	@since		1/19/25
 */

import java.util.ArrayList;

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
		if (phrase.length() >= 1) {
			ArrayList<String> allWords = new ArrayList<String>();
			for (int i = 0; i<phrase.length(); i++) {
				if (wu.findWord(phrase.substring(0, i)) >= 0) {
					allWords.add(phrase.substring(0, i));
				}
				if (wu.findWord(phrase.substring(i)) >= 0) {
					allWords.add(phrase.substring(i));
				}
			}
			// fake words: like "nt", "vi" for exmaple "monta vista"
			// also cant make anagrams of certain word length
			for (String s : allWords) {
				anagrams.add(s);
				String new_phrase = "";
				for (int i = 0; i<phrase.length(); i++) {
					if (!(phrase.indexOf(s) <= i && i < phrase.indexOf(s) + s.length())) {
						new_phrase += phrase.charAt(i);
					}
				}
				generateAnagrams(new_phrase);
				anagrams.remove(s);
			}
			return;
		}
		else {
			for (String s : anagrams) {
				System.out.print(s + " ");
			}
			System.out.println("");
			return;
		}
	}
}
