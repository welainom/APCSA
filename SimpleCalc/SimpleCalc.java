import java.time.chrono.IsoEra;
import java.util.List;		// used by expression evaluator
import java.util.ArrayList;

/**
 *	<Description goes here>
 *
 *	@author		William Liu
 *	@since		3/5/25
 */
public class SimpleCalc {
	
	private ExprUtils utils;	// expression utilities
	
	private ArrayStack<Double> valueStack;		// value stack
	private ArrayStack<String> operatorStack;	// operator stack

	// constructor	
	public SimpleCalc() {
		utils = new ExprUtils();
		valueStack = new ArrayStack<Double>();
		operatorStack = new ArrayStack<String>();
	}
	
	public static void main(String[] args) {
		SimpleCalc sc = new SimpleCalc();
		sc.run();
	}
	
	public void run() {
		System.out.println("\nWelcome to SimpleCalc!!!");
		runCalc();
		System.out.println("\nThanks for using SimpleCalc! Goodbye.\n");
	}
	
	/**
	 *	Prompt the user for expressions, run the expression evaluator,
	 *	and display the answer.
	 */
	public void runCalc() {
		boolean run = true;
		while (run) {
			List<String> tokens = utils.tokenizeExpression(Prompt.getString(""));
			System.out.println(evaluateExpression(tokens));
		}
	}
	
	/**	Print help */
	public void printHelp() {
		System.out.println("Help:");
		System.out.println("  h - this message\n  q - quit\n");
		System.out.println("Expressions can contain:");
		System.out.println("  integers or decimal numbers");
		System.out.println("  arithmetic operators +, -, *, /, %, ^");
		System.out.println("  parentheses '(' and ')'");
	}
	
	/**
	 *	Evaluate expression and return the value
	 *	@param tokens	a List of String tokens making up an arithmetic expression
	 *	@return			a double value of the evaluated expression
	 */
	public double evaluateExpression(List<String> tokens) {
		double value = 0;
		
		for (int i = 0; i<tokens.size(); i++) {
			String cur = tokens.get(i);
			double num = -1.0;

			if (cur.compareTo("0") >= 0 && cur.compareTo("9") <= 9) {
				num = Double.parseDouble(cur);
				valueStack.push(num);
			}
			else {
				if (operatorStack.isEmpty()) {
					operatorStack.push(cur);
				}
				else {
					if (hasPrecedence(operatorStack.peek(), cur)) {
						operatorStack.push(cur);
					}
					else {
						double a = valueStack.pop(), b = valueStack.pop();
						String op = operatorStack.pop();

						if (op.equals("+")) {
							valueStack.push(a + b);
						}
						if (op.equals("-")) {
							valueStack.push(a - b);
						}
					}
				}
			}
		}

		boolean done = valueStack.isEmpty() && operatorStack.isEmpty();
		while (!done) {
			double a = valueStack.pop(), b = valueStack.pop();
			String op = operatorStack.pop();
			
			if (op.equals("+")) {
				valueStack.push(a + b);
			}
			if (op.equals("-")) {
				valueStack.push(a - b);
			}
			if (op.equals("*")) {
				valueStack.push(a * b);
			}
			if (op.equals("/")) {
				valueStack.push(a / b);
			}
			
			done = valueStack.isEmpty() && operatorStack.isEmpty();
		}

		value = valueStack.pop();

		return value;
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
