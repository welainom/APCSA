import java.util.Scanner;
import java.io.PrintWriter;

/**
 *	MVCipher - Add your description here
 *	Requires Prompt and FileUtils classes.
 *	
 *	@author	
 *	@since	
 */
public class MVCipher {
	
	// fields go here
		
	/** Constructor */
	public MVCipher() { }
	
	public static void main(String[] args) {
		MVCipher mvc = new MVCipher();
		mvc.run();
	}
	
	/**
	 *	Method header goes here
	 */
	public void run() {
		System.out.println("\n Welcome to the MV Cipher machine!\n");
		
		/* Prompt for a key and change to uppercase
		   Do not let the key contain anything but alpha
		   Use the Prompt class to get user input */
		
		String key = getKey().toUpperCase();
		System.out.println("KEY: " + key);
		
		/* Prompt for encrypt or decrypt */
		
		int choice = Prompt.getInt("Encrypt or Decrypt?", 1, 2);
		System.out.println(choice);	
			
		/* Prompt for an input file name */
		
		String inFile = Prompt.getString("Name of file to encrypt");
		Scanner input = FileUtils.openToRead(inFile);
		
		/* Prompt for an output file name */
		
		String outFile = Prompt.getString("Name of file to decrypt");
		PrintWriter output = FileUtils.openToWrite(outFile);	
		
		/* Read input file, encrypt or decrypt, and print to output file */
		
		
		/* Don't forget to close your output file */
	}
	
	public String getKey() {
		boolean works = false;
		String key = "";
		while (key.length() < 3 || works == false) {
			boolean temp = true;
			key = Prompt.getString("Please input a word to use as key (letters only)");
			for (char c : key.toCharArray()) {
				if (!(65 <= c && c <= 90) && !(97 <= c && c <= 122)){
					temp = false;
				}
			}
			if (temp) works = true;
			else works = false;
		}
		
		return key;
	}
	
	public String encryptString(String key, String line) {
		String result = "";
		for (int i = 0; i<line.length(); i++) {
			char c = line.charAt(i);
			if (65 <= c && c <= 90) {
				if ((c + result.charAt(i % key.length())) > 90) {
					result += (char) (65 + (90 - (c + result.charAt(i % key.length()))));
				}
				else {
					result += (char) c + result.charAt(i % key.length());
				}
			}
			else if (97 <= c && c <= 122) {
				if ((c + result.charAt(i % key.length())) > 122) {
					result += (char) (97 + (122 - (c + result.charAt(i % key.length()))));
				}
				else {
					result += (char) c + result.charAt(i % key.length());
				}
			}
			else {
				result += c;
			}
		}
		return result;
	}
}
