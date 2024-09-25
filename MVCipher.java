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
	private final int UPPERCASE_MIN = 65;
	private final int UPPERCASE_MAX = 90;
	private final int LOWERCASE_MIN = 97;
	private final int LOWERCASE_MAX = 122;
	private final int NUM_LETTERS = 26;
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
		
		String choicePrompt;
		if (choice == 1) choicePrompt = "Name of file to encrypt";
		else choicePrompt = "Name of file to decrypt";
			
		String inFile = Prompt.getString(choicePrompt);
		Scanner input = FileUtils.openToRead(inFile);
		
		String outFile = Prompt.getString("Name of file to output to");
		PrintWriter output = FileUtils.openToWrite(outFile);	
		
		if (choice == 1) {
			while (input.hasNext()) {
				String line = input.nextLine();
				output.println(encryptString(line, key));
			}
		}
		else {
			while (input.hasNext()) {
				String line = input.nextLine();
				output.println(decryptString(line, key));
			}
		}
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
	
	public String encryptString(String line, String key) {
		String result = "";
		for (int i = 0; i<line.length(); i++) {
			char lineChar = line.charAt(i);
			char keyChar = key.charAt(i % key.length());
			
			result += encryptChar(lineChar, keyChar);
		}
		return result;
	}
	
	public char encryptChar(char lineChar, char keyChar) {
		int shift = (int) (keyChar - UPPERCASE_MIN + 1);
		int charNum;
		char result;
		if (LOWERCASE_MIN <= lineChar && lineChar <= LOWERCASE_MAX) {
			charNum = (int) (lineChar - LOWERCASE_MIN + 1);
			if (charNum + shift > NUM_LETTERS) {
				result = (char) (LOWERCASE_MIN + (charNum + shift - NUM_LETTERS));
			}
			else {
				result = (char) (LOWERCASE_MIN + charNum + shift);
			}
		}
		else if (UPPERCASE_MIN <= lineChar && lineChar <= UPPERCASE_MAX) {
			charNum = (int) (lineChar - UPPERCASE_MIN + 1);
			if (charNum + shift > NUM_LETTERS) {
				result = (char) (UPPERCASE_MIN + (charNum + shift - NUM_LETTERS));
			}
			else {
				result = (char) (UPPERCASE_MIN + charNum + shift);
			}
		}
		else {
			result = (char) (lineChar + 1);
		}
		
		return (char) (result - 1);
	}
	
	public String decryptString(String key, String line) {
		return "";
	}
	
	public char decryptChar(char lineChar, char keyChar) {
		return 'z';
	}
}
