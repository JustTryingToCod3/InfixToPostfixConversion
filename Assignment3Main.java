package assignment3;

import java.util.*;

public class Assignment3Main {

    public static void main(String[] args) {
        // Take in user input
        System.out.println("Enter your infix expression: ");
        Scanner infixInput = new Scanner(System.in); 
        String infix = infixInput.nextLine();
        // Split user input
        String[] input = infix.split(""); 

        // Convert the infix expression to postfix notation then display
        String[] postfixConversion = postfix(input);
        System.out.println("Postfix Notation: " + String.join(" ", postfixConversion));

        // Evaluate the postfix expression and print the result
        double resultPrint = result(postfixConversion);
        System.out.println("Result: " + resultPrint);

        infixInput.close(); 
    }

    // Check operator
    private static boolean isOperator(String element) {
        return element.equals("+") || element.equals("-") || element.equals("*") || element.equals("/") ||
               element.equals("^"); 
    }

    // Check if operand (number or variable)
    private static boolean isOperand(String element) {
        return !isOperator(element) && !element.equals("(") && !element.equals(")") && 
               !element.equals("{") && !element.equals("}") && !element.equals("[") && 
               !element.equals("]");
    }

 // Infix to postfix
    private static String[] postfix(String[] input) {
        // Stack for operators
        DoublyLinkedStack<String> Stack = new DoublyLinkedStack<>(); 
        // Store the postfix conversion
        List<String> output = new ArrayList<>();
     
        
        // Iterate and convert postfix
        for (String element : input) {
            if (isOperand(element)) {
                output.add(element); 
            } else if (element.equals("(")) {
                Stack.push(element); 
            } else if (element.equals(")")) {
                while (!Stack.isEmpty() && !Stack.peek().equals("(")) {
                    output.add(Stack.pop());
                }
                Stack.pop();
            }
            if (isOperator(element)) {
                while (!Stack.isEmpty() && precedence(Stack.peek()) >= precedence(element)) {
                    output.add(Stack.pop());
                }
                Stack.push(element); 
            }
        }
        

        // Pop remaining operators from stack to output
        while (!Stack.isEmpty()) {
            output.add(Stack.pop());
        }
        // Return output as String array
        return output.toArray(new String[0]);
    }

    // Determine precedence
    private static int precedence(String operator) {
        switch (operator) {
            case "+": case "-":
                return 1; 
            case "*": case "/":
                return 2; 
            case "^":
                return 3; 
            default:
                return 0; 
        }
    }

    // Evaluate the result of the postfix expression
    private static double result(String[] input) {
        DoublyLinkedStack<Double> Stack = new DoublyLinkedStack<>(); 

        for (String element : input) {
            if (isOperand(element)) {
                Stack.push(Double.parseDouble(element)); 
            } else if (isOperator(element)) {
                double rightOperand = Stack.pop();
                double leftOperand = Stack.pop();
                switch (element) {
                    case "+":
                        Stack.push(leftOperand + rightOperand);
                        break;
                    case "-":
                        Stack.push(leftOperand - rightOperand); 
                        break;
                    case "*":
                        Stack.push(leftOperand * rightOperand); 
                        break;
                    case "/":
                        Stack.push(leftOperand / rightOperand); 
                        break;
                    case "^":
                        Stack.push(Math.pow(leftOperand, rightOperand)); 
                        break;
                }
            }
        }

        return Stack.pop(); 
    }
}
