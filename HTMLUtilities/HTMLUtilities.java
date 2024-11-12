/**
 *	Utilities for handling HTML
 *
 *	@author	William Liu
 *	@since	11/12/24
 */
 
import java.util.ArrayList;
public class HTMLUtilities {

	/**
	 *	Break the HTML string into tokens. The array returned is
	 *	exactly the size of the number of tokens in the HTML string.
	 *	Example:	HTML string = "Goodnight moon goodnight stars"
	 *				returns { "Goodnight", "moon", "goodnight", "stars" }
	 *	@param str			the HTML string
	 *	@return				the String array of tokens
	 */
	 
// 	public static void main(String[] args) {
// 		HTMLUtilities h = new HTMLUtilities();
// 		h.test();
// 	}
	
// 	public void test() {
// 		System.out.println("");
// 		tokenizeHTMLString("<q>Quote this line.</q>Horizontal rule <hr></body></html>");
// 		System.out.println("");
// 	}
	
	
	// returns String[]
	// METHOD DONE except for tokenizing numbers
	// Must make HELPER METHODS for each type of tokenization
	// example3.html does not work for -0.987.
	public String[] tokenizeHTMLString(String str) {
		// make the size of the array large to start
		String[] result = new String[10000];
		
		ArrayList<String> tokens = new ArrayList<String>();
		for (int i = 0; i<str.length(); i++) {
			Interval intv = new Interval();
			boolean tagFound = false;
			boolean wordFound = false;
			boolean numFound = false;
			char c = str.charAt(i);

			if (('0' <= c && c <= '9') || (c == '-' && ('0' <= str.charAt(i + 1) && str.charAt(i + 1) <= '9'))) {
				numFound = true;
				intv.setLeft(i);
			} 
			else if (c == '.' || c == ',' || c == ';' || c == ':' || c == '(' || c == ')' || 
				c == '?' || c == '!' || c == '=' || c == '&' || c == '~' || c == '+' || c == '-') {
				tokens.add(Character.toString(c));
			}
			else if (c == '<') {
				tagFound = true;
				intv.setLeft(i);
			}
			else if ( ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') && !tagFound) {
				wordFound = true;
				intv.setLeft(i);
			}
			
			for (int j = i + 1; j<str.length() && tagFound; j++) {
				if (str.charAt(j) == '>') {
					tagFound = false;
					intv.setRight(j);
					i = j;
					tokens.add(intv.processInterval(str));
				}
			}

			for (int j = i + 1; j<str.length() && wordFound; j++) {
				if (!(('a' <= str.charAt(j) && str.charAt(j) <= 'z') || ('A' <= str.charAt(j) && str.charAt(j) <= 'Z') || str.charAt(j) == '-')) {
					wordFound = false;
					intv.setRight(j - 1);
					i = j - 1;
					tokens.add(intv.processInterval(str));
				}
			}

			for (int j = i + 1; j<str.length() && numFound; j++) {
				if ( ('0' > str.charAt(j) || str.charAt(j) > '9') && (str.charAt(j) != 'e' && str.charAt(j) != '.' && str.charAt(j) != '-')) {
					numFound = false;
					intv.setRight(j - 1);
					i = j - 1;
					tokens.add(intv.processInterval(str));
				}
			}
		}
		
		// for (String s : tokens) System.out.println(s);
		
		result = new String[tokens.size()];
		for (int i = 0; i<tokens.size(); i++) {
			result[i] = tokens.get(i);
		}

		// return the correctly sized array
		return result;
	}
	
	/**
	 *	Print the tokens in the array to the screen
	 *	Precondition: All elements in the array are valid String objects.
	 *				(no nulls)
	 *	@param tokens		an array of String tokens
	 */
	public void printTokens(String[] tokens) {
		if (tokens == null) return;
		for (int a = 0; a < tokens.length; a++) {
			if (a % 5 == 0) System.out.print("\n  ");
			System.out.print("[token " + a + "]: " + tokens[a] + " ");
		}
		System.out.println();
	}
}

class Interval {
	private int left, right;
	
	public Interval() {
		left = -1;
		right = -1;
	}
	
	public void setLeft(int left) {
		this.left = left;
	}
	
	public void setRight(int right) {
		this.right = right;
	}
	
	public String processInterval(String s) {
		return s.substring(left, right + 1);
	}
}
