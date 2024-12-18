import java.util.Scanner;
import java.util.ArrayList;

/**
 *	HTMLRender
 *	This program renders HTML code into a JFrame window.
 *	It requires your HTMLUtilities class and
 *	the SimpleHtmlRenderer and HtmlPrinter classes.
 *
 *	The tags supported:
 *		<html>, </html> - start/end of the HTML file
 *		<body>, </body> - start/end of the HTML code
 *		<p>, </p> - Start/end of a paragraph.
 *					Causes a newline before and a blank line after. Lines are restricted
 *					to 80 characters maximum.
 *		<hr>	- Creates a horizontal rule on the following line.
 *		<br>	- newline (break)
 *		<b>, </b> - Start/end of bold font print
 *		<i>, </i> - Start/end of italic font print
 *		<q>, </q> - Start/end of quotations
 *		<hX>, </hX> - Start/end of heading with size X = 1, 2, 3, 4, 5, 6
 *		<pre>, </pre> - Preformatted text
 *
 *	@author		William Liu
 *	@version	12/3/24
 */
 
public class HTMLRender {
	
	// the array holding all the tokens of the HTML file
	private String [] tokens;
	private final int TOKENS_SIZE = 100000;	// size of array

	// SimpleHtmlRenderer fields
	private SimpleHtmlRenderer render;
	private HtmlPrinter browser;
	private HTMLUtilities utils;
		
	public HTMLRender() {
		// Initialize token array
		tokens = new String[TOKENS_SIZE];
		
		// Initialize Simple Browser
		render = new SimpleHtmlRenderer();
		browser = render.getHtmlPrinter();
		
		utils = new HTMLUtilities();
	}
	
	public static void main(String[] args) {
		HTMLRender hf = new HTMLRender();
		hf.run(args);
	}
	
	// Method that runs everything
	public void run(String[] args) {
		Scanner input = null;
		String fileName = "";
		
		// Read the filename
		if (args.length > 0)
			fileName = args[0];
		else {
			System.out.println("Usage: java HTMLTester <htmlFileName>");
			System.exit(0);
		}
		
		// Arraylist of tokens for easier processing
		ArrayList<String> tkns = new ArrayList<String>();
		
		input = FileUtils.openToRead(fileName);
		
		while (input.hasNext()) {
			String line = input.nextLine();
			
			String [] tokens = utils.tokenizeHTMLString(line);
			for (String s : tokens) {
				tkns.add(s);
			}
		}
		input.close();
		
		// filter out all nulls 
		int s = 0;
		for (int i = 0; i<tkns.size(); i++) {
			if (tkns.get(i) != null) {
				s++;
			}
		}
		
		tokens = new String[s];
		int q = 0;
		for (int i = 0; i<tkns.size(); i++) {
			if (tkns.get(i) != null) {
				tokens[q] = tkns.get(i);
				q++;
			}
		}
		
		int size = tokens.length; 
		int i = 0; // current index 
		
		// booleans to keep track of type of text
		boolean bold = false, italic = false, pre = false, header = false, normal = true;
		
		// number of characters, heading type and max chars in a line
		int numChars = 0;
		int headerType = 0;
		int maxChars = 80;
		while (i < size) {
			String cur = tokens[i];
			
			// process all the tags
			// mark corresponding booleans, char nums, max chars, and print breaks if necessary
			if (cur.equalsIgnoreCase("<p>")) {
				browser.printBreak();
				numChars = 0;
			} 
			else if (cur.equalsIgnoreCase("</p>")) {
				browser.printBreak();
				numChars = 0;
			} 
			else if (cur.equalsIgnoreCase("<pre>")) {
				pre = true;
				normal = false;
				browser.printBreak();
				numChars = 0;
			} 
			else if (cur.equalsIgnoreCase("</pre>")) {
				pre = false;
				normal = true;
				numChars = 0;
			} 
			else if (cur.equalsIgnoreCase("<br>")) {
				browser.printBreak();
				numChars = 0;
			} 
			else if (cur.equalsIgnoreCase("<b>")) {
				bold = true;
				normal = false;
			} 
			else if (cur.equalsIgnoreCase("</b>")) {
				bold = false;
				normal = true;
			} 
			else if (cur.equalsIgnoreCase("<i>")) {
				italic = true;
				normal = false;
			} 
			else if (cur.equalsIgnoreCase("</i>")) {
				italic = false;
				normal = true;
			} 
			else if (cur.equalsIgnoreCase("<q>")) {
				browser.print("\"");
			} 
			else if (cur.equalsIgnoreCase("</q>")) {
				browser.print("\" ");
			} 
			else if (cur.equalsIgnoreCase("<hr>")) {
				browser.printHorizontalRule();
				numChars = 0;
			}
			else if (cur.equalsIgnoreCase("<h1>")) {
				header = true;
				normal = false;
				headerType = 1;
				browser.printBreak();
				numChars = 0;
				maxChars = 40;
			}
			else if (cur.equalsIgnoreCase("<h2>")) {
				header = true;
				normal = false;
				headerType = 2;
				browser.printBreak();
				numChars = 0;
				maxChars = 50;
			}
			else if (cur.equalsIgnoreCase("<h3>")) {
				header = true;
				normal = false;
				headerType = 3;
				browser.printBreak();
				numChars = 0;
				maxChars = 60;
			}
			else if (cur.equalsIgnoreCase("<h4>")) {
				header = true;
				normal = false;
				headerType = 4;
				browser.printBreak();
				numChars = 0;
				maxChars = 80;
			}
			else if (cur.equalsIgnoreCase("<h5>")) {
				header = true;
				normal = false;
				headerType = 5;
				browser.printBreak();
				numChars = 0;
				maxChars = 100;
			}
			else if (cur.equalsIgnoreCase("<h6>")) {
				header = true;
				normal = false;
				headerType = 6;
				browser.printBreak();
				numChars = 0;
				maxChars = 120;
			}
			else if (cur.equalsIgnoreCase("</h1>")) {
				header = false; 
				normal = true;
				headerType = 1; 
				maxChars = 80;
			}
			else if (cur.equalsIgnoreCase("</h2>")) {
				header = false; 
				normal = true;
				headerType = 2; 
				maxChars = 80;
			}
			else if (cur.equalsIgnoreCase("</h3>")) {
				header = false; 
				normal = true;
				headerType = 3; 
				maxChars = 80;
			}
			else if (cur.equalsIgnoreCase("</h4>")) {
				header = false; 
				normal = true;
				headerType = 4; 
				maxChars = 80;
			}
			else if (cur.equalsIgnoreCase("</h5>")) {
				header = false; 
				normal = true;
				headerType = 5; 
				maxChars = 80;
			}
			else if (cur.equalsIgnoreCase("</h6>")) {
				header = false; 
				normal = true;
				headerType = 6; 
				maxChars = 80;
			}
			
			// If the current token is not a tag
			if (cur.charAt(0) != '<' && cur.charAt(cur.length() - 1) != '>') {
				if (pre) {
					browser.printPreformattedText(cur);
					browser.printBreak();
				}
				else {
					
					// if the length after the current char exceeds the max go to the next line
					if (numChars + cur.length() > maxChars) {
						browser.println();
						numChars = 0;
					}
					
					// process the different types of test besides preformatted
					if (bold) {
						browser.printBold(cur);
					} 
					else if (italic) {
						browser.printItalic(cur);
					} 
					else if(header)
					{
						
						// print the corresponding heading types
						if (headerType == 1) {
							browser.printHeading1(cur);
						}
						else if (headerType == 2) {
							browser.printHeading2(cur);
						}
						else if (headerType == 3){
							browser.printHeading3(cur);
						}
						else if (headerType == 4) {
							browser.printHeading4(cur);
						}
						else if (headerType == 5) {
							browser.printHeading5(cur);
						}
						else if (headerType == 6) {
							browser.printHeading6(cur);
						}
					}
					else if (normal) {
						browser.print(cur);
					}
				}
				
				// If the token is a punctuation, only add one to char num
				if (".,;()?!=&~+:</q></Q>".indexOf(cur) > -1) {
					numChars += 1;
				}
				// if not, add the length + 1 because of the space 
				else { 
					numChars += (cur.length() + 1);
				}
				
				if (".,;()?!=&~+:</q></Q>".indexOf(tokens[i + 1]) == -1) {
					// if its not a header, print normal space
					if (!header) { 
						browser.print(" ");
					}
					else {
						// if it is a header, must print out heading space
						if (headerType == 1) {
							browser.printHeading1(" ");
						}
						else if (headerType == 2) {
							browser.printHeading2(" ");
						}
						else if (headerType == 3) {
							browser.printHeading3(" ");
						}
						else if (headerType == 4) {
							browser.printHeading4(" ");
						}
						else if (headerType == 5) {
							browser.printHeading5(" ");
						}
						else if (headerType == 6) {
							browser.printHeading6(" ");
						}
					}
				}
			}
			i++; // increment index
		} 
	}
}
