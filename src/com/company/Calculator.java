package com.company;

import java.util.Stack;

/**
 * The Calculator class can count examples entered in console format. He is aware of such operations as:
 * + (addition)
 * - (subtraction and unary minus)
 * * (multiplication)
 * / (division)
 * "("and ")"
 * Outputs the answer in double.
 *
 * @author Evgeniy Petrov
 */
public class Calculator {

    private Stack<Double> numbers; //Stack with all numbers
    private Stack<Character> operations; //Stack with all operations
    public String expression; // Mathematical expression
    private int currentIndex; // The current index in the row
    private int flagChanges; // -1 - if nothing has started, 0 - if number, 1 - if operations
    private boolean unarMinus; // A flag that checks for the possibility of a unary minus
    private boolean flagOfOpenBrackets; // Flag for checking open brackets
    /**
     * Class Constructor
     * Assigns the desired values to variables.
     *
     * @param expression A mathematical expression entered via the console
     */
    public Calculator(String expression) {
        this.expression = expression;
        numbers = new Stack<>();
        operations = new Stack<>();
        currentIndex = 0;
        flagChanges = -1;
        unarMinus = true;
        flagOfOpenBrackets = false;
    }

    /**
     *The method that processes the string. Checks each element of the string and draws conclusions.
     * If it is a digit, it goes to a separate class method to find the full number.
     * If an operation, then checks their priority and possible errors.
     * If there are brackets, then it finds the first closing one and performs operations.
     *
     * @return The value of the entered mathematical expression
     * @throws Exception Outputs errors if they are found.
     */
    public double computing() throws Exception {
        while (currentIndex != expression.length()) {
            if (expression.charAt(currentIndex) >= '0' && expression.charAt(currentIndex) <= '9' || expression.charAt(currentIndex) == '-' && unarMinus == true) {
                if (expression.charAt(currentIndex) == '-') {
                    currentIndex++;
                    numbers.push((double) Integer.parseInt(number_search()) * (-1));
                }
                else {
                    numbers.push((double) Integer.parseInt(number_search()));
                }
                flagChanges = 0;
                unarMinus = false;
            }

            else if (expression.charAt(currentIndex) == '+' || expression.charAt(currentIndex) == '-') {
                if (flagChanges != 0) {
                    throw new Exception("Incorrect location of operations!");
                }
                if (!operations.isEmpty()) {
                    while (operations.peek() == '+' || operations.peek() == '-' || operations.peek() == '*' || operations.peek() == '/') {
                        calculating_the_value();
                        if (operations.isEmpty()) {
                            break;
                        }
                    }
                }
                operations.push(expression.charAt(currentIndex));
                flagChanges = 1;
                currentIndex++;
                unarMinus = false;
            }

            else if (expression.charAt(currentIndex) == '*' || expression.charAt(currentIndex) == '/') {
                if (flagChanges != 0) {
                    throw new Exception("Incorrect location of operations!");
                }
                if (!operations.isEmpty()) {
                    while (operations.peek() == '*' || operations.peek() == '/') {
                        calculating_the_value();
                        if (operations.isEmpty()) {
                            break;
                        }
                    }
                }
                operations.push(expression.charAt(currentIndex));
                flagChanges = 1;
                currentIndex++;
                unarMinus = false;
            }
            else if (expression.charAt(currentIndex) == '(') {
                if (!count_of_brackets()) {
                    throw new Exception("Incorrect location of operations!");
                }
                if (flagChanges == 0) {
                    throw new Exception("Incorrect location of operations!");
                }
                flagOfOpenBrackets = true;
                operations.push(expression.charAt(currentIndex));
                currentIndex++;
                flagChanges = -1;
                unarMinus = true;
            }
            else if (expression.charAt(currentIndex) == ')') {
                if (!flagOfOpenBrackets) {
                    throw new Exception("Incorrect location of operations!");
                }
                if (flagChanges == 1) {
                    throw new Exception("Incorrect location of operations!");
                }
                while(operations.peek() != '(') {
                    calculating_the_value();
                }
                operations.pop();
                if (!operations.isEmpty()) {
                    if (operations.peek() == '-') {
                        operations.pop();
                        operations.push('+');
                        numbers.push( numbers.pop() * (-1));
                    }
                }
                flagOfOpenBrackets = false;
                flagChanges = -1;
                currentIndex++;
                unarMinus = false;
            }
            else if (expression.charAt(currentIndex) == ' ') {
                currentIndex++;
            }
            else {
                throw new Exception("An unknown character has been entered.");
            }
        }
        while (!operations.isEmpty()) {
            calculating_the_value();
        }
        return numbers.pop();
    }

    /**
     * Private method. Starting from the digit of a number, searches for its
     * end and returns a string equal to that number. Works only with integers.
     *
     * @return A string with the digits of a number.
     */
    private String number_search () {
        String number = "";
        while (expression.charAt(currentIndex) >= '0' && expression.charAt(currentIndex) <= '9') {
            number += expression.charAt(currentIndex);
            if (currentIndex + 1 == expression.length()) { //
                currentIndex++;
                break;
            }
            else {
                currentIndex++;
            }
        }
        return number;
    }

    /**
     * Method for calculating values (+, -, *, /)
     *
     * @throws Exception In case of division by 0, it returns the corresponding error.
     */
    private void calculating_the_value() throws Exception {
        if (operations.peek() == '+') {
            numbers.push(numbers.pop() + numbers.pop());
            operations.pop();
            //System.out.println(numbers.peek());
        }
        else if (operations.peek() == '-') {
            numbers.push(-numbers.pop() + numbers.pop());
            operations.pop();
            //System.out.println(numbers.peek());
        }
        else if (operations.peek() == '*') {
            numbers.push(numbers.pop() * numbers.pop());
            operations.pop();
            //System.out.println(numbers.peek());
        }
        else if (operations.peek() == '/') {
            double value1 = numbers.pop();
            if (value1 == 0.0) {
                throw new Exception("Division by 0 cannot be done!");
            }
            numbers.push(numbers.pop() / value1);
            operations.pop();
           // System.out.println(numbers.peek());
        }
    }

    /**
     * Method for checking the number of brackets.
     * Returns true if the number of brackets is the same.
     *
     * @return Returns true if the number of brackets is the same.
     */
    private boolean count_of_brackets() {
        int i = currentIndex;
        int countOfOpen = 0;
        int countOfClose = 0;
        while (i != expression.length()) {
            if (expression.charAt(i) == '(') {
                countOfOpen += 1;
            }
            else if (expression.charAt(i) == ')') {
                countOfClose += 1;
            }
            i++;
        }
        if (countOfClose == countOfOpen) {
            return true;
        }
        return false;
    }


}
