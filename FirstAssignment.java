/**
 *	FirstAssignment.java
 *	Display a brief description of your summer vacation on the screen.
 *
 *	To compile Linux:	javac -cp .:mvAcm.jar FirstAssignment.java
 *	To execute Linux:	java -cp .:mvAcm.jar FirstAssignment
 *
 *	To compile MS Powershell:	javac -cp ".;mvAcm.jar" FirstAssignment.java
 *	To execute MS Powershell:	java -cp ".;mvAcm.jar" FirstAssignment
 *
 *	@author	Your name
 *	@since	Today's date
 */
import java.awt.Font;

import acm.program.GraphicsProgram;
import acm.graphics.GLabel;

public class FirstAssignment extends GraphicsProgram {
    
    public void run() {
    	//	The font to be used
    	Font f = new Font("Serif", Font.BOLD, 18);
    	
    	//	Line 1
    	GLabel s1 = new GLabel("What I did on my summer vacation ...", 10, 20);
    	s1.setFont(f);
    	add(s1);
    	
    	GLabel s2 = new GLabel("My summer vacation was very fun. I spent lots of time with my friends and family.", 10, 40);
    	s2.setFont(f);
    	add(s2);
    	
    	GLabel s3 = new GLabel("I also took a few classes to learn more. I also did a couple of summer programs.", 10, 60);
    	s3.setFont(f);
    	add(s3);
    	
    	GLabel s4 = new GLabel("One of my programs was an engineering one, where I built a robot.", 10, 80);
    	s4.setFont(f);
    	add(s4);
    	
    	GLabel s5 = new GLabel("Another one was a program about statistics and data science.", 10, 100);
    	s5.setFont(f);
    	add(s5);
    	
    	GLabel s6 = new GLabel("Other than studying, I also hung out with my friends. We went out to dinner a few times.", 10, 120);
    	s6.setFont(f);
    	add(s6);
    	
    	GLabel s7 = new GLabel("One of the restaurants that we went to was called BJ's. It is a brewery that seves American food.", 10, 140);
    	s7.setFont(f);
    	add(s7);
    	
    	GLabel s8 = new GLabel("I think the food there is just okay, but what makes it fun is hanging out with your friends.", 10, 160);
    	s8.setFont(f);
    	add(s8);
    	
    	GLabel s9 = new GLabel("Other than going to restaurants, we also played a lot of basketball together. ", 10, 180);
    	s9.setFont(f);
    	add(s9);
    	
    	GLabel s10 = new GLabel("Most of the time we went to Kennedy Middle and Monta Vista to use the courts.", 10, 200);
    	s10.setFont(f);
    	add(s10);
    	
    	GLabel s11 = new GLabel("Sometimes, ", 10, 220);
    	s11.setFont(f);
    	add(s11);
    	    	
    	//	Continue adding lines until you have 12 to 15 lines
    	
    }
    
}
