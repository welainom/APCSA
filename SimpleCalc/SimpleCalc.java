import java.util.ArrayList;
import java.util.List;  // Used by expression evaluator

/**
 * A simple calculator that supports arithmetic operations and variable assignments.
 *
 * @author 	William Liu
 * @since 	2/26/25
 */
public class SimpleCalc {
    private ExprUtils utils;	
	
	private ArrayStack<Double> valueStack;		
	private ArrayStack<String> operatorStack;	
	private ArrayList<Identifier> vars; 
	boolean isValidAssignment; 

	public SimpleCalc() {
		utils = new ExprUtils();

		valueStack = new ArrayStack<Double>();
		operatorStack = new ArrayStack<String>();

		vars = new ArrayList<Identifier>();
		vars.add(new Identifier("e", Math.E));
		vars.add(new Identifier("pi", Math.PI));

		isValidAssignment = false;
	}

	public static void main(String[] args) {
		SimpleCalc sc = new SimpleCalc();
		sc.run();
	}

	public void run() {
		System.out.println("\nWelcome to SimpleCalc!!!");

		String exp = Prompt.getString("");
		while(!exp.equals("q"))
		{
			if(exp.equals("h")) {
				printHelp();
			} 
			else if(exp.equals("l")) {
				System.out.println("\nVariables");

				for(int i=0; i<vars.size(); i++) {
					System.out.printf("\t%-15s=%10.2f\n", vars.get(i).getName(), vars.get(i).getValue());
				}

				System.out.println();
			} 
			else if(!exp.equals("q")) {
				List<String> tokens = utils.tokenizeExpression(exp);

				if(exp.contains("=")) {
					inputIdentifier(tokens);
					if(isValidAssignment) {
						System.out.println("  " + vars.get(vars.size()-1).getName() + " = "
								+ vars.get(vars.size()-1).getValue());
					} 
					else System.out.println(0.0);
				} 

				else System.out.println(evaluateExpression(tokens));
			}
			exp = Prompt.getString("");
		}

		System.out.println("\nThanks for using SimpleCalc! Goodbye.\n");
	}
	
	/**	Print help */
	public void printHelp() {
		System.out.println("Help:");
		System.out.println("  h - this message\n  q - quit\n");
		System.out.println("Expressions can contain:");
		System.out.println("  integers or decimal numbers");
		System.out.println("  arithmetic operators +, -, *, /, %, ^");
		System.out.println("  parentheses '(' and ')'\n");
	}

	/**
	 *	Determine whether the identifier name is valid
	 *  Only alphabetic characters allowed (No nums, special chars)
	 *  Adds to vars unless not all letters/already is same Identifier name (resets var)
	 *	@param tokens	a List of String tokens making up an arithmetic expression
	 *	@return			a double value of the evaluated expression (if valid assignment)
	 */
	public double inputIdentifier(List<String> tokens) {
		String expression = tokens.get(0);
		
		for (int j = 0; j < tokens.size(); j++) {
			String token = tokens.get(j);
			
			if (token.charAt(0) == '=') break;
			
			for (char c : token.toCharArray()) {
				if (!Character.isLetter(c)) {
					isValidAssignment = false;
					return 0;
				}
			}
		}
		
		tokens.remove(0); 
		tokens.remove(0); 
		
		double value = evaluateExpression(tokens);
		
		for (Identifier var : vars) {
			if (expression.equals(var.getName())) {
				var.setValue(value);
				isValidAssignment = true;
				return 0;
			}
		}
		
		vars.add(new Identifier(expression, value));
		isValidAssignment = true;
		return value;
	}
	

	/**
	 *	Evaluate expression and return the value
	 *	@param tokens	a List of String tokens making up an arithmetic expression
	 *	@return			a double value of the evaluated expression
	 */
	public double evaluateExpression(List<String> tokens) {
		for (int i = 0; i<tokens.size(); i++) {
			String token = tokens.get(i);

			if (token.equals("(")) {
				operatorStack.push("(");
			}
			else if (token.equals(")")) {

				while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
					computeExpression();
				}

				if (!operatorStack.isEmpty()) {
					operatorStack.pop();
				}
			} 
			else if (token.charAt(0)!='=' && utils.isOperator(token.charAt(0))) {
				String op = String.valueOf(token.charAt(0));

				while (!operatorStack.isEmpty() && hasPrecedence(op, operatorStack.peek())) {
					computeExpression();
				}

				operatorStack.push(op);
			} 
			else if (Character.isDigit(token.charAt(0))) {
				valueStack.push(Double.parseDouble(token));
			} 
			else if (Character.isAlphabetic(token.charAt(0))) {
				boolean found = false;

				for (Identifier var : vars) {
					if (token.equals(var.getName())) {
						valueStack.push(var.getValue());
						found = true;
						break;
					}
				}
				
				if (!found) {
					valueStack.push(0.0);
				}
			}
		}
		
		// Process remaining operators
		while (!operatorStack.isEmpty()) computeExpression();

		if (valueStack.isEmpty()) return 0.0;
		else return valueStack.pop();
	}

	/**
	 *	Calculates highest precedent expression
	 * Pops two operands and one operator off the "top" of the stacks
	 */
	public void computeExpression()
	{
		String op = operatorStack.pop();
		double op1 = valueStack.pop(), op2 = valueStack.pop();

		if (op.equals("+")) valueStack.push(op2+op1);
		else if(op.equals("-")) valueStack.push(op2-op1);
		else if(op.equals("*")) valueStack.push(op2*op1);
		else if(op.equals("/")) valueStack.push(op2/op1);
		else if(op.equals("^")) valueStack.push(Math.pow(op2, op1));
	}
	
	/**
	 *	Precedence of operators
	 *	@param op1	operator 1
	 *	@param op2	operator 2
	 *	@return		true if op2 has higher or same precedence as op1; false otherwise
	 *	Algorithm:
	 *		if op1 is exponent, then false
	 *		if op2 is either left or right parenthesis, then false
	 *		if op1 is multiplication or division or modulus and 
	 *				op2 is addition or subtraction, then false
	 *		otherwise true
	 */
	private boolean hasPrecedence(String op1, String op2) {
		if (op1.equals("^")) return false;
		if (op2.equals("(") || op2.equals(")")) return false;
		if ((op1.equals("*") || op1.equals("/") || op1.equals("%")) 
				&& (op2.equals("+") || op2.equals("-")))
			return false;
		return true;
	}

}
