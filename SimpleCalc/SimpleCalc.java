import java.util.ArrayList;
import java.util.List;  // Used by expression evaluator
import java.util.EmptyStackException;

/**
 * A simple calculator that supports arithmetic operations and variable assignments.
 *
 * @author 
 * @since 
 */
public class SimpleCalc {
    private ExprUtils utils; // Expression utilities
    private ArrayStack<Double> valueStack; // Stack for storing values
    private ArrayStack<String> operatorStack; // Stack for storing operators
    private ArrayList<Identifier> ids; // List of all stored variables
    private int index = 0; // Current token index

    // Constructor
    public SimpleCalc() {
        utils = new ExprUtils();
        ids = new ArrayList<>();
        valueStack = new ArrayStack<>();
        operatorStack = new ArrayStack<>();
        
        // Initialize constants e and pi
        ids.add(new Identifier("e", Math.E));
        ids.add(new Identifier("pi", Math.PI));
    }

    /** Main Method */
    public static void main(String[] args) {
        SimpleCalc sc = new SimpleCalc();
        sc.run();
    }

    /** Runs the calculator and handles user input */
    public void run() {
        System.out.println("Welcome to SimpleCalc!!!\n");
        runCalc();
        System.out.println("\nThanks for using SimpleCalc! Goodbye.\n");
    }

    /** Handles user input and processes expressions */
    public void runCalc() {
        String exp = Prompt.getString("");
        while (!exp.equals("q")) {
            List<String> tokensToPass = utils.tokenizeExpression(exp);

            if (exp.equals("h")) {
                printHelp();
            } else if (exp.equals("l")) {
                listVariables();
            } else if (exp.contains("=")) {
                processAssignment(tokensToPass);
            } else {
                System.out.println(evaluateExpression(tokensToPass));
            }

            exp = Prompt.getString("");
        }
    }

    /** Handles variable assignment */
    private void processAssignment(List<String> tokens) {
        String name = tokens.get(0);
        tokens.remove(0);
        tokens.remove(0); // Remove "="

        boolean isValid = true;
		for (int i = 0; i < name.length(); i++) {
			if (!Character.isLetter(name.charAt(i))) {
				isValid = false;
			}
		}

		if (!isValid) {
			System.out.println("0.0");
			return;
		}


        if (name.equals("e") || name.equals("pi")) {
            System.out.println("Cannot modify constant: " + name);
            return;
        }

        double value = evaluateExpression(tokens);
        Identifier id = new Identifier(name, value);
        
        boolean exists = false;
        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i).getName().equals(name)) {
                ids.set(i, id);
                exists = true;
                break;
            }
        }

        if (!exists) {
            ids.add(id);
        }
        System.out.println(name + " = " + value);
    }

    /** Evaluates an arithmetic expression */
    public double evaluateExpression(List<String> tokens) {
        valueStack = new ArrayStack<>();
        operatorStack = new ArrayStack<>();
        index = 0;

        while (index < tokens.size()) {
			String token = tokens.get(index);
			System.out.println("Processing token: " + token); // Debugging line		
            if (token.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    performOperation();
                }
                if (!operatorStack.isEmpty()) {
                    operatorStack.pop();
                }
            } else if (utils.isOperator(token.charAt(0))) {
                while (!operatorStack.isEmpty() && hasPrecedence(token, operatorStack.peek())) {
                    performOperation();
                }
                operatorStack.push(token);
            } else {
                valueStack.push(getValue(token));
            }
            index++;
        }

        while (!operatorStack.isEmpty()) {
            performOperation();
        }
        return valueStack.isEmpty() ? 0.0 : valueStack.pop();
    }

    /** Gets the numeric value of a token (either a variable or a number) */
    private double getValue(String token) {
		if (Character.isLetter(token.charAt(0))) {
			for (Identifier id : ids) {
				if (id.getName().equals(token)) {
					return id.getValue();
				}
			}
			// Return 0.0 if the variable is undefined
			return 0.0;
		}
		return Double.parseDouble(token);
	}
	

    /** Performs an operation using the top two values in the stack */
    private void performOperation() {
        if (valueStack.isEmpty() || operatorStack.isEmpty()) return;
        double val1 = valueStack.pop();
        if (valueStack.isEmpty()) {
            valueStack.push(val1);
            return;
        }
        double val2 = valueStack.pop();
        String op = operatorStack.pop();
        valueStack.push(doOperations(val1, val2, op));
    }

    /** Prints the list of stored variables */
    private void listVariables() {
        System.out.println("\nVariables:");
        for (Identifier id : ids) {
            System.out.printf("%-18s = %8.2f\n", id.getName(), id.getValue());
        }
        System.out.println();
    }

    /** Performs arithmetic operations */
    private double doOperations(double val1, double val2, String operator) {
        switch (operator) {
            case "+": return val2 + val1;
            case "-": return val2 - val1;
            case "*": return val2 * val1;
            case "/": return val2 / val1;
            case "^": return Math.pow(val2, val1);
            case "%": return val2 % val1;
            default: return 0;
        }
    }

    /** Checks operator precedence */
    private boolean hasPrecedence(String op1, String op2) {
        if (op1.equals("^")) return false;
        if (op2.equals("(") || op2.equals(")")) return false;
        if ((op1.equals("*") || op1.equals("/") || op1.equals("%")) && (op2.equals("+") || op2.equals("-"))) return false;
        return true;
    }

    /** Prints help message */
    public void printHelp() {
        System.out.println("Help:");
        System.out.println("  h - this message\n  q - quit\n");
        System.out.println("Expressions can contain:");
        System.out.println("  integers or decimal numbers");
        System.out.println("  arithmetic operators +, -, *, /, %, ^");
        System.out.println("  parentheses '(' and ')'");
    }
}
