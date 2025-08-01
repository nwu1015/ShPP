package com.shpp.p2p.cs.vmarchenko.assignment5;

import com.shpp.cs.a.console.TextProgram;

/**
 * The second task: Algorithm of addition
 * Asks the user for 2 numbers in the form of a string, which are added in the program.
 */
public class Assignment5Part2 extends TextProgram {
    /**
     * The main method of application. Allows to test the given program.
     * Asks the user for 2 numbers and displays the result of the addition.
     */
    public void run() {
        /* Sit in a loop, reading numbers and adding them. */
        while (true) {
            String n1 = readLine("Enter first number:  ");
            String n2 = readLine("Enter second number: ");
            println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
            println();
        }
    }

    /**
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {
        int[] number1 = fillTheArray(n1);
        int[] number2 = fillTheArray(n2);

        String result;

        int difference = number1.length - number2.length;
        if (difference >= 0) {
            result = calculate(number2, number1, difference);
        }else {
            result = calculate(number1, number2, -difference);
        }

        return result;
    }

    /**
     * An array is created from the number entered by the user.
     *
     * @param number number specified by the user.
     * @return array of numbers
     */
    public int[] fillTheArray(String number){
        int[] array = new int[number.length()];

        for (int i = 0; i < number.length(); i++) {
            array[i] = number.charAt(i) - '0';
        }
        return array;
    }

    /**
     * Implementing addition of numbers in an array.
     *
     * @param array1 first array of numbers (shorter array)
     * @param array2 second array of numbers (longer array)
     * @param difference difference in arrays by length
     * @return calculation result
     */
    public String calculate(int[] array1, int[] array2, int difference) {
        int[] sumResult = new int[array2.length + 1];
        int remainderForRounding = 0;

        for(int i = array2.length - 1; i >= 0; i--) {
            int number;

            if (i - difference >= 0) {
                number = array1[i - difference];
            }else {
                number = 0;
            }

            int sum = array2[i] + number + remainderForRounding;
            sumResult[i + 1] = sum % 10;
            remainderForRounding = sum / 10;
        }
        sumResult[0] = remainderForRounding;

        for(int i = array2.length - array1.length; i < array1.length; i++) {
            array2[i] = array1[i - difference];
        }

        StringBuilder output = new StringBuilder();
        int index = 0;
        if (sumResult[0] == 0) {
            index = 1;
        }

        for(int i = index; i < sumResult.length; i++) {
            output.append(sumResult[i]);
        }

        return output.toString();
    }
}