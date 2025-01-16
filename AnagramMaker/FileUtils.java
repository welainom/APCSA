import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/*
 *	File Utilities for reading and writing 
 *
 *	@author William Liu
 *	@since  August 23, 2024
 */
 
public class FileUtils 
{
	/**
	 *	Opens a file to read using the Scanner class.
	 *  @param fileName		name of the file to open
	 *  @return 			the scanner object to the file
	 */
	 public static java.util.Scanner openToRead(String fileName)
	 {
		 java.util.Scanner input = null;
		 
		 try
		 {
			input = new java.util.Scanner(new java.io.File(fileName));
		 }
		 catch(java.io.FileNotFoundException e)
		 {
			 System.err.println("ERROR: Cannot open " + fileName + 
						" for reading.");
			 System.exit(1);
		 } 
		 return input;
	}
	
	/** 
	 *	Opens a file to write using the PrintWriter class.
	 *	@param fileName 		name of the file to open.
	 *	@return					the PrintWriter object to the file.	
	 */
	 public static PrintWriter openToWrite(String fileName)
	 {
		 PrintWriter output = null;
		 
		 try
		 {
			 output = new PrintWriter(new File(fileName));
	     }
	     catch(FileNotFoundException e)
	     {
			 System.err.println("ERROR: Can't open " + fileName +
						" for writing.");
			System.exit(2);
		}
		return output;
	}
			 
		

}
