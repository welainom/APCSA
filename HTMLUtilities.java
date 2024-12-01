/**
 *	Utilities for handling HTML
 *	This class provides methods to split and HTML file into tokens
 * 	Handles words, numbers, tags, comments, and preformatted text
 *	
 *	@author	William Liu
 *	@since November 1, 2024
 */

public class HTMLUtilities {
	// Comments and preformat
	private final String COMMENT_START = "<!--"; // Start of comment
	private final String COMMENT_END = "-->";	 // End of comment
	private final String PRE_START = "<pre>";    // Start of preformatted text
	private final String PRE_END = "</pre>";     // End of preformatted text

	// Possible tokenizer states
	private enum TokenState { NONE, COMMENT, PRE }; 
	
	// The current state of the tokenizer
	private TokenState state = TokenState.NONE;
	
	/**
	 *	Break the HTML string into tokens. The array returned is
	 *	exactly the size of the number of tokens in the HTML string.
	 *	Example:	HTML string = "Goodnight moon goodnight stars"
	 *				returns { "Goodnight", "moon", "goodnight", "stars" }
	 *	@param str			the HTML string
	 *	@return				the String array of tokens
	 */
	public String[] tokenizeHTMLString(String str) {
		String[] result = new String[10000];
		int idx = 0;   // Current character index 
		int tokenNum = 0;   // Current index in tokens array
		
		while (idx < str.length()) {
			String token = "";       // Current token 
			boolean tokenFound = false;  // If token has been found
			char cur = str.charAt(idx); // Current character
			
			switch (state) {
				case COMMENT:
					// Handle comments, check for COMMENT_END
					if (str.charAt(idx) == COMMENT_END.charAt(2) &&
						idx > 1 &&
						str.charAt(idx - 1) == COMMENT_END.charAt(1) &&
						str.charAt(idx - 2) == COMMENT_END.charAt(0)) {
						state = TokenState.NONE;
					}
					idx++;
					break;

				case PRE:
					// Handle preformatted text
					while (idx < str.length() && !tokenFound) {
						if (str.charAt(idx) == '<' && 
							str.substring(idx).startsWith(PRE_END)) {
							state = TokenState.NONE;
							token = str.substring(idx, idx + 6);
							tokenFound = true;
						} else {
							token += str.charAt(idx);
							if (idx == str.length() - 1) {
								tokenFound = true;
							}
							idx++;
						}
					}
					if (tokenFound && !token.isEmpty()) {
						result[tokenNum++] = token;
					} else {
						idx++;
					}
					break;

				case NONE:
					if (cur == '<') {
						// Handle tags and preformatted block
						if (str.startsWith(COMMENT_START, idx)) {
							state = TokenState.COMMENT;
						} else {
							tokenFound = true;
							token = str.substring(idx, str.indexOf('>', idx) + 1);
							idx += token.length();
							if (token.equals(PRE_START)) {
								state = TokenState.PRE;
							}
						}
					} 
					else if (isPunctuation(cur)) {
						// Handle punctuation
						token = String.valueOf(cur);
						tokenFound = true;
						idx++;
					} 
					else if (Character.isLetter(cur)) {
						// Handle words
						while (idx < str.length() && Character.isLetter(str.charAt(idx))) {
							token += str.charAt(idx++);
						}
						tokenFound = true;
					} 
					else if(('0' <= cur && cur <= '9')) {
						
						// Create String t to keep adding number token to t
						String t = "";
						int i = idx;
						boolean found = false;
						
						do {
							// Check all the different number cases
							if(isNumber(str.charAt(i))) {
								t += str.charAt(i);
							}
							else if(str.charAt(i) == '.' && i < str.length() - 1 &&
									isNumber(str.charAt(i + 1))) {
								t += str.charAt(i);
							}
							else if(str.charAt(i) == 'e') {
								if(i < str.length() - 1 && isNumber(str.charAt(i + 1))) {
									t += str.charAt(i);
								}
								else if(i < str.length() - 1 && 
									(str.charAt(i + 1) == '-' || str.charAt(i + 1) == '+')) {
										
									if(i < str.length() - 2 && isNumber(str.charAt(i + 2))) {
										t += str.charAt(i);
										t += str.charAt(i + 1);
										i++;
									}
								}
							}
							else {
								found = true;
							}
							if(!found) {
								i++;
							}
						} while(i < str.length() && !found);
						
						token += t;
						idx += token.length();
						tokenFound = true;
					}
					// Handle negative numbers
					else if((cur == '-' && idx < str.length() - 1 && 
							('0' <= str.charAt(idx + 1) && str.charAt(idx + 1) <= '9'))) {
						token += cur;
						idx++;

						String t = "";
						int i = idx;
						boolean found = false;
						
						do {
							if(isNumber(str.charAt(i))) {
								t += str.charAt(i);
							}
							else if(str.charAt(i) == '.' && i < str.length() - 1 &&
									isNumber(str.charAt(i + 1))) {
								t += str.charAt(i);
							}
							else if(str.charAt(i) == 'e') {
								if(i < str.length() - 1 && isNumber(str.charAt(i + 1))) {
									t += str.charAt(i);
								}
								else if(i < str.length() - 1 && 
									(str.charAt(i + 1) == '-' || str.charAt(i + 1) == '+')) {
										
									if(i < str.length() - 2 && isNumber(str.charAt(i + 2))) {
										t += str.charAt(i);
										t += str.charAt(i + 1);
										i++;
									}
								}
							}
							else {
								found = true;
							}
							if(!found) {
								i++;
							}
						} while(i < str.length() && !found);
						
						token += t;
						idx += token.length() - 1;
						tokenFound = true;
					}

					if (tokenFound && !token.isEmpty()) {
						result[tokenNum++] = token;
					} else {
						idx++;
					}
					break;

				default:
					break;
			}
		}

		return result;
	}

	/**
	*	Checks if a char is punctuation
 	* 	@param c	the char you want to check
  	*	@return 	whether c is a punctuation
 	*/
	public boolean isPunctuation(char c) {
		return (".,;()?!=&~+:".indexOf(c) > -1);
	}
	
	/**
	*	Checks if a char is a number
 	* 	@param c	the char you want to check
  	*	@return 	whether c is a number
 	*/	
	public boolean isNumber(char c) {
		return '0' <= c && c <= '9';
	}
	
	/**
	 *	Print the tokens in the array to the screen
	 *	Precondition: All elements in the array are valid String objects.
	 *				(no nulls)
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
