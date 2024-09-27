import java.util.Scanner;
import java.io.PrintWriter;

/**
 *	MVCipher - The MV Cipher is a variation on the Caesar cipher and a much stronger encryption
 * 	A program to encrypt and decrypt text files using the MVCipher
 * 	A program to encrypt and decrypt text files using the MVCipher
 *	Requires Prompt and FileUtils classes.
 * 
 * 	*Reading Macbeth.txt cuts off at line 3061 for some reason
 *	
 *	@author		William Liu
 *	@since		September 20, 2024
 */
public class MVCipher {
	private final int UPPERCASE_MIN = 65; // Upper bound for Uppercase
	private final int UPPERCASE_MAX = 90; // Upper bound for Uppercase
	private final int LOWERCASE_MIN = 97; // Lower bound for Lowercase
	private final int LOWERCASE_MAX = 122; // Upper bound for Lowercase 
	private final int NUM_LETTERS = 26; // Number of letters in the alphabet
	private int totalIdx = 0; // Counter for total chars decrypted 
	
	public MVCipher() { }
	
	public static void main(String[] args) {
		MVCipher mvc = new MVCipher();
		mvc.run();
	}
	
	/**
	 *	Main method to run the encryption/decryption process.
	 *	Asks user for the key, operation, and handles input and output.
	 */
	public void run() {
		System.out.println("\n Welcome to the MV Cipher machine!\n");
		
		// Get the user key and make it uppercase
		String key = getKey().toUpperCase();
		System.out.println("KEY: " + key);
		
		// Either encryption or decryption
		int choice = Prompt.getInt("Encrypt or Decrypt?", 1, 2);
		System.out.println("CHOICE: " + choice);	
		
		// Change prompt depending on operation type
		String choicePrompt;
		if (choice == 1) choicePrompt = "Name of file to encrypt";
		else choicePrompt = "Name of file to decrypt";
			
		// Use prompt and fileutils to create Scanner and printwriter
		String inFile = Prompt.getString(choicePrompt);
		Scanner input = FileUtils.openToRead(inFile);
		
		String outFile = Prompt.getString("Name of file to output to");
		PrintWriter output = FileUtils.openToWrite(outFile);	
		
		// Perform encryption or decryption operation
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
	
	/**
	 *	Method to prompt for a valid key from the user.
	 *	Key must contain only alphabetic characters and be at least 3 characters long.
	 *	
	 *	@return	The valid key entered by the user.
	 */
	public String getKey() {
		boolean works = false;
		String key = "";
		while (key.length() < 3 || works == false) {
			boolean temp = true; // tester boolean
			key = Prompt.getString("Please input a word to use as key (letters only)");
			for (char c : key.toCharArray()) {
				
				// Each char must be a letter
				if (!(LOWERCASE_MIN <= c && c <= LOWERCASE_MAX) 
					&& !(UPPERCASE_MIN <= c && c <= UPPERCASE_MAX)){
					temp = false;
				}
			}
			
			// If it works break out of loop
			if (temp) works = true;
			else works = false;
		}
		
		return key;
	}
	
	/**
	 *	Method to encrypt a given string using the provided key.
	 *	
	 *	@param	line	The string to be encrypted.
	 *	@param	key		The key used for encryption.
	 *	@return			The encrypted version of the input string.
	 * 
	 * 	Precondition: The key is made of all Uppercase letters
	 */
	public String encryptString(String line, String key) {
		if (totalIdx == 26000) totalIdx = 0;
		String result = "";
		for (int i = 0; i<line.length(); i++) {
			char lineChar = line.charAt(i);
			char keyChar = key.charAt((totalIdx) % key.length());
			
			result += encryptChar(lineChar, keyChar);
		}
		return result;
	}
	
	/**
	 *	Method to encrypt a single character using the key character.
	 *	
	 *	@param	lineChar	The character to be encrypted.
	 *	@param	keyChar		The key character used for shifting.
	 *	@return				The encrypted character.
	 * 
	 * 	Precondition: The key character is Uppercase
	 */
	public char encryptChar(char lineChar, char keyChar) {
		int shift = (int) (keyChar - UPPERCASE_MIN); // Calculate shift 
		int charNum;
		char result;
		if (LOWERCASE_MIN <= lineChar && lineChar <= LOWERCASE_MAX) {
			totalIdx++; // Increment total chars 
			charNum = (int) (lineChar - LOWERCASE_MIN); // calculate num of result char
			
			// Shift and wrap in case it goes over 26
			if (charNum + shift >= NUM_LETTERS - 1) {
				result = (char) (LOWERCASE_MIN + (charNum + shift - NUM_LETTERS));
			}
			else {
				result = (char) (LOWERCASE_MIN + charNum + shift);
			}
		}
		else if (UPPERCASE_MIN <= lineChar && lineChar <= UPPERCASE_MAX) {
			
			// Do the same for Uppercase
			totalIdx++;
			charNum = (int) (lineChar - UPPERCASE_MIN);
			if (charNum + shift >= NUM_LETTERS - 1) {
				result = (char) (UPPERCASE_MIN + (charNum + shift - NUM_LETTERS));
			}
			else {
				result = (char) (UPPERCASE_MIN + charNum + shift);
			}
		}
		else {
			result = (char) (lineChar - 1);
		}
		
		// Increment result due to some testing I did
		return (char) (result + 1);
	}
	
	/**
	 *	Method to decrypt a given string using the provided key.
	 *	
	 *	@param	line	The string to be decrypted.
	 *	@param	key		The key used for decryption.
	 *	@return			The decrypted version of the input string.
	 * 
	 * 	Preconditions: The key is made of all Uppercase letters
	 */
	public String decryptString(String line, String key) {
		if (totalIdx == 26000) totalIdx = 0; // Reset total idx to prevent possible overflow
		String result = "";
		for (int i = 0; i < line.length(); i++) {
			char lineChar = line.charAt(i);
			
			// Find the decrypted char
			char keyChar = key.charAt((totalIdx) % key.length());

			result += decryptChar(lineChar, keyChar);
		}
		return result;
	}

	/**
	 *	Method to decrypt a single character using the key character.
	 *	
	 *	@param	lineChar	The character to be decrypted.
	 *	@param	keyChar		The key character used for shifting.
	 *	@return				The decrypted character.
	 * 
	 * 	Preconditions: The key character is Uppercase
	 */
	public char decryptChar(char lineChar, char keyChar) {
		int shift = keyChar - UPPERCASE_MIN; // Calculate shift based on the key
		int charNum;
		char result;
		
		if (LOWERCASE_MIN <= lineChar && lineChar <= LOWERCASE_MAX) {
			totalIdx++; // Increment total number of chars converted
			charNum = lineChar - LOWERCASE_MIN; // Number of the decrypted char
			
			// Shift it and wrap if it goes negative
			result = (char) ((charNum - shift + NUM_LETTERS) % NUM_LETTERS + LOWERCASE_MIN);
		} else if (UPPERCASE_MIN <= lineChar && lineChar <= UPPERCASE_MAX) {
			
			// Do the same for uppercase letters
			totalIdx++;
			charNum = lineChar - UPPERCASE_MIN;
			result = (char) ((charNum - shift + NUM_LETTERS) % NUM_LETTERS + UPPERCASE_MIN);
		} else {
			result = (char) (lineChar + 1);
		}

		// decrement it due to some testing I ran
		return (char) (result - 1);
	}
}
