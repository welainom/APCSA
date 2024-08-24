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
    	
    	GLabel s2 = new GLabel("My summer vacation was very very very very fun. I spent lots of time with my friends and family.", 10, 40);
    	s2.setFont(f);
    	add(s2);
    	
    	GLabel s3 = new GLabel("I also took a class or two or three or four to learn more. I also did a couple of summer programs.", 10, 60);
    	s3.setFont(f);
    	add(s3);
    	
    	GLabel s4 = new GLabel("One of my programs was an engineering one, where I built a robot. The robot followed a ball around.", 10, 80);
    	s4.setFont(f);
    	add(s4);

        GLabel s5 = new GLabel("You could also control it using bluetooth. I also broadcast the video  from its camera to a server", 10, 100);
    	s5.setFont(f);
    	add(s5);
        
    	GLabel s5 = new GLabel("Another was a program about statistics and data science. It was from the Wharton Business School.", 10, 120);
    	s5.setFont(f);
    	add(s5);
    	
    	GLabel s6 = new GLabel("Other than studying, I also hung out with my friends a little. We went out to dinner a few times.", 10, 140);
    	s6.setFont(f);
    	add(s6);
    	
    	GLabel s7 = new GLabel("One of the restaurants that we went to was called BJ's. It is a brewery that seves American food.", 10, 160);
    	s7.setFont(f);
    	add(s7);
    	
    	GLabel s8 = new GLabel("I think the food there is just okay, but what makes it very fun is hanging out with your friends.", 10, 180);
    	s8.setFont(f);
    	add(s8);
    	
    	GLabel s9 = new GLabel("Other than going to restaurants, me and my friends played a large amount of basketball together. ", 10, 200);
    	s9.setFont(f);
    	add(s9);
    	
    	GLabel s10 = new GLabel("Most of the time we went to Kennedy Middle School  and Monta Vista to use the public courts.", 10, 220);
    	s10.setFont(f);
    	add(s10);

        GLabel s11 = new GLabel("When we were tired from basketball, we often went to 711 to get refreshments and snacks for energy.")
        s11.setFont(f);
        add(s11);
        
    	GLabel s12 = new GLabel("When we were done with basketball, we often hung out at each other's houses and played video games.", 10, 260);
        s12.setFont(f);
        add(s12);
        
        GLabel s13 = new GLabel("We played games like Fortnite and Super Smash Bros, and we tried out many new games we found online.", 10, 280);
        s13.setFont(f);
        add(s13);
        
        GLabel s14 = new GLabel("It was a great way to relax and have fun after studying and after doing the many many many classes.", 10, 300);
        s14.setFont(f);
        add(s14);
        
        GLabel s15 = new GLabel("Overall, this summer was a mix of learning, playing, and spending time with my friends and family.", 10, 320);
        s15.setFont(f);
        add(s15);

    	    	
    	//	Continue adding lines until you have 12 to 15 lines
    	
    }
    
}
