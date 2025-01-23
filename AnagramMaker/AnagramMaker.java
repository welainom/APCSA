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
    private final String FILE_NAME = "randomWords.txt"; // file containing all words

    private WordUtilities wu; // the word utilities for building the word
                              // database, sorting the database,
                              // and finding all words that match
                              // a string of characters

    // variables for constraining the print output of AnagramMaker
    private int numWords;      // the number of words in a phrase to print
    private int maxPhrases;    // the maximum number of phrases to print
    private int numPhrases;    // the number of phrases that have been printed

    /* Initialize the database inside WordUtilities
     * The database of words does NOT have to be sorted for AnagramMaker to work,
     * but the output will appear in order if you DO sort.
     */
    public AnagramMaker() {
        wu = new WordUtilities();
        wu.readWordsFromFile(FILE_NAME);
        wu.sortWords();

        numPhrases = 0;
    }

    public static void main(String[] args) {
        AnagramMaker am = new AnagramMaker();
        am.run();
    }

    /** The top routine that prints the introduction and runs the anagram-maker.
     */
    public void run() {
        printIntroduction();
        runAnagramMaker();
        System.out.println("\nThanks for using AnagramMaker!\n");
    }

    /**
     * Print the introduction to AnagramMaker
     */
    public void printIntroduction() {
        System.out.println("\nWelcome to ANAGRAM MAKER");
        System.out.println("\nProvide a word, name, or phrase and out comes their anagrams.");
        System.out.println("You can choose the number of words in the anagram.");
        System.out.println("You can choose the number of anagrams shown.");
        System.out.println("\nLet's get started!");
    }

    /**
     * Prompt the user for a phrase of characters, then create anagrams from those
     * characters.
     */
    public void runAnagramMaker() {
		String input = "";
		boolean notQ = true;
		while (notQ) {
			input = Prompt.getString("Word(s), name or phrase (q to quit)");
			if (input.equals("q")) notQ = false;
			if (notQ) {
				numWords = Prompt.getInt("Number of words in anagram");
				maxPhrases = Prompt.getInt("Maximum number of anagrams to print");

				String phrase = "";
				for (int i = 0; i < input.length(); i++) {
					char c = input.charAt(i);
					if ('a' <= c && c <= 'z' || 'A' <= c && c <= 'Z') {
						phrase += c;
					}
				}

				ArrayList<String> anagrams = new ArrayList<String>();
				numPhrases = 0;
				generateAnagrams(phrase, anagrams);
				System.out.println("Stopped at " + numPhrases + " anagrams.");
			}
		}
    }

    public void generateAnagrams(String phrase, ArrayList<String> anagrams) {
        if (!phrase.equals("")) {
            if (anagrams.size() == numWords) {
				for (String s : anagrams) {
					System.out.print(s + " ");
				}
				System.out.println("");
                numPhrases++;
            }
            return;
        }
        else if (anagrams.size() == numWords) {
            return;
        }
		else if (numPhrases >= maxPhrases) {
			return;
		}
		else {
			ArrayList<String> allWords = wu.allWords(phrase);
			
			for (String s : allWords) {
				anagrams.add(s);

				String newPhrase = phrase;
				for (int i = 0; i<s.length(); i++) {
					char c = s.charAt(i);
					int idx = newPhrase.indexOf(c);

					if (idx >= 0) newPhrase = newPhrase.substring(0, idx) + newPhrase.substring(idx + 1);
				}

				generateAnagrams(newPhrase, anagrams);

				anagrams.remove(anagrams.size() - 1);
			}
		}
    }
}