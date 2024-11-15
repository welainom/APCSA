/**
 *	Utilities for handling HTML
 *	This class provides methods to split and HTML file into tokens
 * 	Handles words, numbers, tags, comments, and preformatted text
 *	
 *	@author	William Liu
 *	@since November 1, 2024
 */

public class HTMLUtilities {
	// Possible tokenizer states
	private enum TokenState { NONE, COMMENT, PRE }; 
	
	// The current state of the tokenizer
	private TokenState state = TokenState.NONE;
	
	// Comments and preformatted blocks
	private final String COMMENT_START = "<!--"; // Start of comment
	private final String PRE_START = "<pre>";    // Start of preformatted text
	private final String PRE_END = "</pre>";     // End of preformatted text

	/**
	 *	Breaks down an HTML string into individual tokens. 
	 *	Handles comments and preformatted text separately to avoid processing HTML within these blocks.
	 *	
	 *	@param str	The HTML string to tokenize.
	 *	@return		A String array containing each token from the HTML string.
	 */
	public String[] tokenizeHTMLString(String str) {
		// make the size of the array large to start
		String[] result = new String[10000];
		
		int idx = 0;   // Current character index 
		int tokenNum = 0;   // Current index in tokens array
		boolean isTokenFound = false;
		
		while(idx < str.length()) {
			String token = "";      // Current token 
			boolean tokenFound = false;  // If token has been found
			char cur = str.charAt(idx);    // Current character 
			
			switch(state) {
				case COMMENT:
				
					// Continue until the end is reached
					if((str.charAt(idx) == '>' && str.charAt(idx - 1) == '-' &&
						str.charAt(idx - 2) == '-')) {
						state = TokenState.NONE;
					}
					idx++;
					break;
				
				case PRE:
				
					// Take all characters in preformatted block
					while(idx < str.length() && !tokenFound) {
						
						// Check the end of preformatted block
						if(str.charAt(idx) == '<' && str.substring(idx, str.length()).startsWith(PRE_END)) {
							state = TokenState.NONE;
							tokenFound = true;
						} 
						else {
							token += str.charAt(idx);
							
							// End of line and mark token as complete
							if(idx == str.length() - 1) {
								tokenFound = true;
							}
							idx++;
						}
					}
					
					// If token was found and not empty, store in the result array
					if(tokenFound && token.length() > 0) { 
						result[tokenNum] = token;
						tokenNum++;
					} 
					else {
						
						// Move to next character if no token was found
						idx++;
					}
					break;
				
				case NONE:
				
					// Check if current character indicates start of a tag
					if((str.charAt(idx) == '<')) {
						
						// Comment blocks starting with <!--
						if(str.indexOf(COMMENT_START) == idx) {
							state = TokenState.COMMENT;
						} 
						else {
							
							// Tokenize tags
							tokenFound = true;
							token = str.substring(idx, str.indexOf('>', idx) + 1);
							idx += token.length();
							
							// If preformatted switch state to PREFORMAT
							if(token.equals(PRE_START)) {
								state = TokenState.PRE;
							}
						}
					}
					
					// Handle punctuation 
					else if((".,;()?!=&~+-:".indexOf(cur) > -1)) {
						token += cur;
						tokenFound = true;
						idx++;
					}
					
					// Handle words
					else if((cur >= 'A' && cur <= 'Z') || (cur >= 'a' && cur <= 'z')) {
						
						// Take letters until a non letter is found
						while(idx < str.length() && !tokenFound) {
							if(((str.charAt(idx) >= 'A' && str.charAt(idx) <= 'Z') || 
							(str.charAt(idx) >= 'a' && str.charAt(idx) <= 'z') || 
							str.charAt(idx) == '-')) {
								token += str.charAt(idx);
							}
							else {
								tokenFound = true;
							}
							idx++;
						}
						
						if(token.length() > 0) {
							tokenFound = true;
						}
					}
					// Handle normal numbers
					else if(('0' <= cur && cur <= '9')) {
						token += getNum(str, idx);
						idx += token.length();
						tokenFound = true;
					}
					// Handle negative numbers
					else if((cur == '-' &&
							idx < str.length() - 1 && 
							('0' <= str.charAt(idx + 1) && str.charAt(idx + 1) <= '9'))) {
						token += cur;
						idx++;
						token += getNum(str, idx);
						idx += token.length() - 1;
						tokenFound = true;
					}
					
					// If a token was identified, put it in the array
					if(tokenFound) {
						result[tokenNum] = token;
						tokenNum++;
					} else {
						// If not tokens, move to next 
						idx++;
					}
					break;
					
				default:
					break;
			}
		}
		
		// Return the array of tokens
		return result;
	}
	
	/**
	 * Tokenizes numbers from string given starting index
	 * Handles integers, decimals, and scientific notation. Positive or Negative.
	 * 
	 * @param str	The string being processed.
	 * @param i		The starting index of the numeric token.
	 * @return		A String - the numeric token found.
	 */
	private String getNum(String str, int i)
	{
		String token = "";
		boolean isTokenFound = false;
		
		while(i < str.length() && !isTokenFound)
		{
			if(isNumber(str.charAt(i)))
				token += str.charAt(i);
			else if(str.charAt(i) == '.' && i < str.length() - 1 &&
					isNumber(str.charAt(i + 1)))
				token += str.charAt(i);
			else if(str.charAt(i) == 'e')
			{
				if(i < str.length() - 1 && isNumber(str.charAt(i + 1)))
					token += str.charAt(i);
				else if(i < str.length() - 1 && 
					(str.charAt(i + 1) == '-' || str.charAt(i + 1) == '+'))
				{
					if(i < str.length() - 2 && isNumber(str.charAt(i + 2)))
					{
						token += str.charAt(i);
						token += str.charAt(i + 1);
						i++;
					}
				}
			}
			else
				isTokenFound = true;
				
			if(!isTokenFound)
				i++;
		}
		return token;
	}

	
	/**
	 *	Prints tokens in the array to the screen.
	 *	Precondition: All elements in the array are valid String objects.
	 *	@param tokens		an array of String tokens
	 */
	public void printTokens(String[] tokens) {
		if(tokens == null)
			return;
		for(int a = 0; a < tokens.length && tokens[a] != null; a++) {
			if(a % 5 == 0)
				System.out.print("\n  ");
			System.out.print("[token " + a + "]: " + tokens[a] + " ");
		}
		System.out.println();
	}
}
