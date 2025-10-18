package com.shpp.p2p.cs.vmarchenko.assignment10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
    public static void main(String[] args) {
        System.out.println("The given equation and its parameters:");
        for (String result : args) {
            System.out.println(result);
        }

        if (args.length == 0) {
            throw new IllegalArgumentException("Please, write a mathematical expression and parameters " +
                    "if it's necessary");
        }

        String formula = args[0].replace(" ", "");

        HashMap<String, Double> variables = parseVariablesFromArgs(args);

        System.out.println("Result: " + calculate(formula, variables));

    }

    /**
     * Performs operations with priority.
     * First, exponentiation (^) is performed, then multiplication (*) and division (/),
     * and lastly, addition and subtraction.
     * Also correctly handles expressions that begin with a minus sign.
     *
     * @param formula   mathematical expression
     * @param variables HashMap, which containing variables and their numerical values
     * @return The result of calculating the expression.
     */
    private static double calculate(String formula, HashMap<String, Double> variables) {
        if (formula.startsWith("-")) {
            formula = "0" + formula;
        }

        String[] elementsForArray = formula.split("(?<=[-+*/^])|(?=[-+*/^])");
        ArrayList<String> elements = new ArrayList<>(Arrays.asList(elementsForArray));

        processOperation("^", elements, variables);
        processOperation("*", elements, variables);
        processOperation("/", elements, variables);

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
    private static HashMap<String, Double> parseVariablesFromArgs(String[] args) {
        HashMap<String, Double> variables = new HashMap<>();
        for (int i = 1; i < args.length; i++) {

            if (!args[i].matches("[a-zA-Z]+=-?\\d+(\\.\\d+)?")) {
                System.out.println("Incorrect syntax. Please, use the following syntax: param=value");
                System.exit(1);
            }

            String key = args[i].substring(0, args[i].indexOf("="));
            String value = args[i].substring(args[i].indexOf("=") + 1);

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
    private static double mathOperation(double num1, double num2, String operator) {

        return switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            case "^" -> Math.pow(num1, num2);
            default -> 0;
        };
    }

    /**
     * Converts a string to a double number,
     * replacing the comma with a period to support both decimal number formats.
     *
     * @param number A string representing a number.
     * @return A number of type double.
     */
    private static double parseStringToDouble(String number) {
        return Double.parseDouble(number.replace(",", "."));
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
    private static double convertParamToNumber(String number1, HashMap<String, Double> variables) {
        double num1;
        try {
            num1 = parseStringToDouble(number1);
            return num1;
        } catch (NumberFormatException e) {
            if (variables.containsKey(number1)) {
                return variables.get(number1);
            } else {
                System.out.println("Error. You don't have this parameter");
                System.exit(1);
            }
        }
        return 0;
    }

    /**
     * Finds and performs all operations of a specific type in a list of elements.
     *
     * @param operation Type of operation to search for and perform
     * @param elements  The list of expression elements to be modified.
     * @param variables HashMap of variables required for calculations.
     */
    private static void processOperation(String operation,
                                         ArrayList<String> elements,
                                         HashMap<String, Double> variables) {
        List<String> operators = new ArrayList<>();
        operators.add("+");
        operators.add("-");
        operators.add("*");
        operators.add("/");
        operators.add("^");

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