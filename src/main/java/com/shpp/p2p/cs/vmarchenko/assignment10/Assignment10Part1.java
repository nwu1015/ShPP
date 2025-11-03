package com.shpp.p2p.cs.vmarchenko.assignment10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Assignment 10: Simple Calculator.
 * The expression to be evaluated and, if available,
 * the parameters for this expression are taken from the args array.
 * The expression is then evaluated and the result is printed to the console.
 */
public class Assignment10Part1 {
    /**
     * The main method of the program. Allows you to run the program for testing.
     * Splits the argument array into two groups: the expression itself and its parameters.
     *
     * @param args an array of data fed to the program input.
     */
    public static void main(String[] args) throws Exception {

        Assignment10Part1 obj = new Assignment10Part1();

        System.out.println("The given equation and its parameters:");
        for (String result : args) {
            System.out.println(result);
        }

        if (args.length == 0 || args[0] == null) {
            System.out.println("Please, write a mathematical expression and parameters " +
                    "if it's necessary");
            return;
        }

        String formula = obj.createFormula(args);

        HashMap<String, Double> variables = obj.parseVariablesFromArgs(args);

        ArrayList<String> elements = new ArrayList<>(Arrays.asList(
                formula.split("(?<=[-+*/^])|(?=[-+*/^])")
        ));

        System.out.println("Result: " + obj.calculate(variables, elements));
    }

    /**
     * Prepares a mathematical formula from the input arguments.
     * Removes all spaces from the expression and adds a leading zero
     * if the formula starts with a minus sign (to handle negative numbers correctly).
     *
     * @param args Array of command-line arguments, where the first element
     *             contains the mathematical expression entered by the user.
     * @return A formatted formula string ready for parsing and calculation.
     */
    private String createFormula(String[] args) {
        String formula = args[0].replace(" ", "");

        if (formula.startsWith("-")) {
            formula = "0" + formula;
        }

        return formula;
    }

    /**
     * Performs operations with priority.
     * First, exponentiation (^) is performed, then multiplication (*) and division (/),
     * and lastly, addition and subtraction.
     * Also correctly handles expressions that begin with a minus sign.
     *
     * @param variables HashMap, which containing variables and their numerical values
     * @return The result of calculating the expression.
     */
    private double calculate(HashMap<String, Double> variables, ArrayList<String> elements) throws Exception {

        ArrayList<String> operators = new ArrayList<>();
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
        operators.add("^");

        processHighPriorityOperation(operators, variables, elements);

        return calculateSequentially(variables, elements);
    }

    /**
     * Performs all mathematical operations with higher priority.
     * This method sequentially processes exponentiation (^), multiplication (*),
     * and division (/), modifying the list of elements by replacing
     * completed operations with their results.
     *
     * @param elements  List of expression elements (numbers, variables, and operators)
     * @param variables HashMap containing variable names and their numerical values
     * @param operators List of all supported operators
     * @throws Exception if an invalid operation or variable is encountered
     */
    private void processHighPriorityOperation(ArrayList<String> elements,
                                              HashMap<String, Double> variables,
                                              ArrayList<String> operators) throws Exception {
        processOperation("^", elements, variables, operators);
        processOperation("*", elements, variables, operators);
        processOperation("/", elements, variables, operators);
    }

    /**
     * Calculates the remaining mathematical operations sequentially from left to right.
     * After higher-priority operations have been processed, this method performs
     * addition and subtraction to produce the final result.
     *
     * @param variables HashMap containing variable names and their numerical values
     * @param elements  List of expression elements (numbers, variables, and operators)
     * @return The final numerical result of the evaluated expression
     * @throws Exception if an invalid operator or variable is encountered
     */
    private double calculateSequentially(HashMap<String,
                                                 Double> variables,
                                         ArrayList<String> elements) throws Exception {

        double result = convertParamToNumber(elements.getFirst(), variables);

        for (int i = 1; i < elements.size(); i += 2) {
            String operator = elements.get(i);
            double num1 = result;
            double num2 = convertParamToNumber(elements.get(i + 1), variables);

            result = mathOperation(num1, num2, operator);
        }

        return result;
    }

    /**
     * Parses command line arguments, starting with the second one
     *
     * @param args An array of command line arguments.
     * @return HashMap, where the key is the name of the variable
     * and the value is its numeric representation.
     */
    private HashMap<String, Double> parseVariablesFromArgs(String[] args) throws Exception {
        HashMap<String, Double> variables = new HashMap<>();
        for (int i = 1; i < args.length; i++) {

            args[i] = args[i].replace(" ", "");

            if (!args[i].matches("[a-zA-Z]+=-?\\d+(\\.\\d+)?")) {
                throw new Exception("Incorrect syntax. Please, use the following syntax: param=value");
            }

            String[] values = args[i].split("=");
            String key = values[0];
            String value = values[1];

            variables.put(key, Double.parseDouble(value));
        }

        return variables;
    }

    /**
     * Performs one mathematical operation on two numbers.
     *
     * @param num1     First number
     * @param num2     Second number
     * @param operator A string representing an operator
     * @return The result of the operation.
     */
    private double mathOperation(double num1, double num2, String operator) {
        Operator op = Operator.checkSymbol(operator);

        return switch (op) {
            case ADD -> num1 + num2;
            case MINUS -> num1 - num2;
            case MULTIPLY -> num1 * num2;
            case DIVIDE -> {
                if (num2 == 0) {
                    throw new ArithmeticException("You can't divide by zero");
                }
                yield num1 / num2;
            }
            case POWER -> Math.pow(num1, num2);
        };
    }

    /**
     * Converts a string to a number. First tries to recognize it as a number.
     * If that fails, looks for the string in the variable map.
     * If the variable is not found, prints an error and terminates the program.
     *
     * @param number1   A string that can be a number or a variable name.
     * @param variables HashMap of variables to search.
     * @return Numerical value.
     */
    private double convertParamToNumber(String number1, HashMap<String, Double> variables) throws Exception {
        double num1;
        try {
            num1 = Double.parseDouble(number1.replace(",", "."));
            return num1;
        } catch (NumberFormatException e) {
            if (variables.containsKey(number1)) {
                return variables.get(number1);
            } else {
                throw new Exception("Error. You don't have this parameter");
            }
        }
    }

    /**
     * Finds and performs all operations of a specific type in a list of elements.
     *
     * @param operation Type of operation to search for and perform
     * @param elements  The list of expression elements to be modified.
     * @param variables HashMap of variables required for calculations.
     */
    private void processOperation(String operation,
                                  ArrayList<String> elements,
                                  HashMap<String, Double> variables,
                                  ArrayList<String> operators) throws Exception {

        for (int i = 1; i < elements.size(); i += 2) {
            if (operators.contains(elements.get(i)) && elements.get(i).equals(operation)) {
                double num1 = convertParamToNumber(elements.get(i - 1), variables);
                double num2 = convertParamToNumber(elements.get(i + 1), variables);

                elements.set(i - 1, String.valueOf(mathOperation(num1, num2, operation)));
                elements.remove(i + 1);
                elements.remove(i);

                i -= 2;
            }
        }
    }
}