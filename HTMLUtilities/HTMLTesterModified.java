import java.util.Scanner;

/**
 *	Test class for HTMLUtilities
 *	This class can be run with the following command:
 *
 *		java HTMLTester <htmlFileName>
 *
 *	Requires the HTMLUtilities and FileUtils classes.
 *
 *	@author Mr Greenstein
 */

public class HTMLTesterModified {

	private HTMLUtilities util;			// HTMLUtilities used in tester
	
	public HTMLTesterModified() {
		util = new HTMLUtilities();
	}
	
	public static void main(String[] args) {
		HTMLTesterModified ht = new HTMLTesterModified();
		ht.run();
	}

	// FAKE RUN METHOD
	// WHEN SUBMITTING REMOVE THIS!!!
	
	public void run() {
		String fileName = "example4.html";
		// if the command line contains the file name, then store it
		
		// Open the HTML file
		Scanner input = FileUtils.openToRead(fileName);
		
		// Read each line of the HTML file, tokenize, then print tokens
		while (input.hasNext()) {
			String line = input.nextLine();
			System.out.println("\n" + line);
			String [] tokens = util.tokenizeHTMLString(line);
			util.printTokens(tokens);
		}
		input.close();
	}

	/**
	 *	Opens the HTML file specified on the command line
	 *	then inputs each line and prints out the line and the
	 *	tokens produced by HTMLUtilities.
	 *	@param args		the String array holding the command line arguments
	 */
	public void run(String[] args) {
		Scanner input = null;
		String fileName = "example1.html";
		// if the command line contains the file name, then store it
		if (args.length > 0)
			fileName = args[0];
		// otherwise print out usage message
		else {
			System.out.println("Usage: java HTMLTester <htmlFileName>");
			System.exit(0);
		}
		
		// Open the HTML file
		input = FileUtils.openToRead(fileName);
		
		// Read each line of the HTML file, tokenize, then print tokens
		while (input.hasNext()) {
			String line = input.nextLine();
			System.out.println("\n" + line);
			String [] tokens = util.tokenizeHTMLString(line);
			util.printTokens(tokens);
		}
		input.close();
	}
}
