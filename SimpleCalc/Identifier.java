/**
 *	Identifier with name and value assigned to it
 *
 *	@author     William Liu
 *	@since	    3/3/25
 * */

public class Identifier {
    private String name;
    private double value; 

    //constructor
    public Identifier(String name, double value) {
        this.name = name;
        this.value = value;
    }

    /**
     *	getter for Identifier's name
     *	@return			a string value of the Identifier's name
     */
    public String getName() { 
        return name; 
    }

    /**
     *	setter for Identifier's name
     *	@param			String name2 to change Identifier's name to
     */
    public void setName(String name) { 
        this.name = name; 
    }

    /**
     *	getter for Identifier's value
     *	@return			a double value of the Identifier's value
     */
    public double getValue() { 
        return value; 
    }

    /**
     *	Setter for Identifier's value
     *	@param			double value2 to change Identifier's value to
     */
    public void setValue(double value) { 
        this.value = value; 
    }
}