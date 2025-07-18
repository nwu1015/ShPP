package com.shpp.p2p.cs.vmarchenko.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * The second task: Numbers-hailstones
 * The program asks the user for a positive integer.
 * Then, through certain calculations, any number will produce a result of 1.
 */
public class Assignment3Part2 extends TextProgram {
    /**
     * The main method of application. Allows to test the given program.
     * Calls a function for a calculation that accepts a number entered by the user.
     */
    public void run() {
        startCalculating(askNumber());
    }

    /**
     * Prompts the user for a positive integer. If the user enters an invalid value,
     * an appropriate message is displayed.
     *
     * @return the number entered by the user
     */
    public int askNumber() {
        while (true) {
            try {
                int number = readInt("Enter a number: ");
                if (number <= 0) {
                    println("Please enter a number greater than 0");
                } else {
                    return number;
                }
            } catch (Exception e) {
                println("Please enter an integer number.");
                readLine();
            }
        }
    }

    /**
     * The method starts the calculation.
     * If the number is odd, it is multiplied by 3 and 1 is added.
     * If the number is even, it is divided by 2.
     * The algorithm is repeated until the number equals one.
     *
     * @param number the number from which the calculation begins.
     */
    public void startCalculating(int number) {
        while (number != 1) {
            if (number % 2 == 0) {
                println(number + " is even so I take half: " + number / 2);
                number = number / 2;
            } else {
                println(number + " is odd so I make 3n + 1: " + (3 * number) + 1);
                number = (3 * number) + 1;
            }

            if (number == 1) break;
        }
        println("End");
    }
}