package com.shpp.p2p.cs.vmarchenko.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * The third task: Raising to a power.
 * The program implements raising a number to a power.
 * The user writes what number and to what power they want to raise it,
 * and the program outputs the result.
 */
public class Assignment3Part3 extends TextProgram {
    /**
     * The main method of application. Allows to test the given program.
     * Calls a method that raises the given number to the given power.
     */
    public void run() {
        println(raiseToPower(0, 0));

    }

    /**
     * A method for implementing raising a given number to a given power.
     *
     * @param base     given number.
     * @param exponent given number that will be the power.
     * @return result of raising to a power.
     */
    private double raiseToPower(double base, int exponent) {
        // Anything raised to the 0 power will equal 1.
        if (exponent == 0) return 1;

        // If a number raised to any power equals 0, then the result will be 0.
        if (base == 0) return 0;

        // If the power is negative, then the number being raised to the power is
        // inverted (the fraction is flipped), and the power now becomes positive.
        if (exponent < 0) {
            base = 1 / base;
            exponent = -exponent;
        }

        // Variable for storing the result of a calculation
        double result = 1;

        // In the loop, the variable to store the result is multiplied by the base.
        // This operation is repeated as many times as specified in exponent.
        for (int i = 1; i <= exponent; i++) {
            result *= base;
        }
        return result;
    }
}