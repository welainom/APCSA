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
 *	@author
 *	@version
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
	
	public void run(String[] args) {
		Scanner input = null;
		String fileName = "";
		
		// if the command line contains the file name, then store it
		if (args.length > 0)
			fileName = args[0];
		// otherwise print out usage message
		else {
			System.out.println("Usage: java HTMLTester <htmlFileName>");
			System.exit(0);
		}
		
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
		
		tokens = tkns.toArray(new String[tkns.size()]);
		
		int size = tokens.length;
		int i = 0;
		boolean bold = false, italic = false, pre = false;
		int numChars = 0;
		int headerType = 0;
		while (i < size) {
			String cur = tokens[i];
			
			if (cur != null) {
				if (cur.equalsIgnoreCase("<p>")) {
					browser.printBreak();
					numChars = 0;
				}
				if (cur.equalsIgnoreCase("<b>")) 
					bold = true; 
				if (cur.equalsIgnoreCase("</b>")) 
					bold = false;
				if (cur.equalsIgnoreCase("<i>")) 
					italic = true;
				if (cur.equalsIgnoreCase("</i>"))
					italic = false;
				if (cur.equalsIgnoreCase("<pre>")) {
					pre = true;
					browser.printBreak();
					numChars = 0;
				}
				if (cur.equalsIgnoreCase("</pre>")) 
					pre = false;
				if (cur.equalsIgnoreCase("<q>")) 
					browser.print("\"");
				if (cur.equalsIgnoreCase("</q>")) 
					browser.print("\"");
				if (cur.equalsIgnoreCase("<hr>")) {
					browser.printHorizontalRule();
					numChars = 0;
				}
				if (cur.equalsIgnoreCase("<br>")) {
					browser.printBreak();
					numChars = 0;
				}
				if (cur.equalsIgnoreCase("</p>") && tokens[i+1] != null && !tokens[i+1].equalsIgnoreCase("<p>")) {
					browser.printBreak();
					numChars = 0;
				}
					/*
				if (cur.equalsIgnoreCase("<h1>")) 
					headerType = 1;
				if (cur.equalsIgnoreCase("<h2>")) 
					headerType = 2;
				if (cur.equalsIgnoreCase("<h3>")) 
					headerType = 3;
				if (cur.equalsIgnoreCase("<h4>")) 
					headerType = 4;
				if (cur.equalsIgnoreCase("<h5>")) 
					headerType = 5;
				if (cur.equalsIgnoreCase("<h6>")) 
					headerType = 6;
				if (cur.equalsIgnoreCase("</h1>")) 
					headerType = 0;
				if (cur.equalsIgnoreCase("</h2>")) 
					headerType = 0;
				if (cur.equalsIgnoreCase("</h3>")) 
					headerType = 0;
				if (cur.equalsIgnoreCase("</h4>")) 
					headerType = 0;
				if (cur.equalsIgnoreCase("</h5>")) 
					headerType = 0;
				if (cur.equalsIgnoreCase("</h6>")) 
					headerType = 0;
					*/
				
				if (cur.indexOf('<') != 0 || cur.indexOf('>') != cur.length() - 1) {
					System.out.println(numChars);
					if (numChars + cur.length() + 1 > 80 && !pre) {
						browser.println();
						numChars = 0;
					}
					else if (numChars > 0 && ".,;()?!=&~+:".indexOf(cur) == -1 && !pre) {
						browser.print(" ");
					} 
					
					numChars += cur.length() + 1;
					
					switch (headerType) {/*
						case 1:
							browser.printHeading1(cur);
							break;
						case 2:
							browser.printHeading2(cur);
							break;
						case 3:
							browser.printHeading3(cur);
							break;
						case 4:
							browser.printHeading4(cur);
							break;
						case 5:
							browser.printHeading5(cur);
							break;
						case 6:
							browser.printHeading6(cur);
							break;*/
						default:
							if (pre) {
								browser.printPreformattedText(cur);
								browser.println();
							}
							else if (bold) {
								browser.printBold(cur);
							}
							else if (italic) {
								browser.printItalic(cur);
							}
							else {
								browser.print(cur);
							}
							break;
					}
				}
			}
			i++;
		}
		
		// for (String s : tokens) if (s != null) System.out.println(s);
	}
}
