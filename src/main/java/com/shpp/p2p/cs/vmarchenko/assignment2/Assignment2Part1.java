package com.shpp.p2p.cs.vmarchenko.assignment2;

import com.shpp.cs.a.console.TextProgram;

/**
 * The first task: quadratic equation.
 * The program accepts 3 numbers as parameters of a quadratic equation.
 * If the parameters are entered correctly,
 * the program will show the roots of the equation,
 * which are solved through the discriminant.
 */
public class Assignment2Part1 extends TextProgram {

    /**
     * The main method of application. Allows to test the given program.
     * Calls methods to request numbers from the user,
     * find the discriminant, and display the roots of the equation
     */
    public void run() {
        double firstParam = askFirstParameter("Enter the first parameter (а): ");
        double secondParam = askParameters("Enter the second parameter (b): ");
        double thirdParam = askParameters("Enter the third parameter (c): ");

        double discriminator = findDiscriminator(firstParam, secondParam, thirdParam);
        findRoots(firstParam, secondParam, discriminator);
    }

    /**
     * A method for reading data from the user via the console.
     * The user must enter a number.
     * If the data was entered incorrectly, an appropriate message will be displayed.
     *
     * @param message - message that is displayed on the console when prompted for input.
     * @return the number entered by the user into the console.
     */
    public double askParameters(String message) {
        while (true) {
            try {
                return readDouble(message);
            } catch (Exception e) {
                println("One of the parameters is invalid. Please enter a number. ");
                readLine();
            }
        }
    }

    /**
     * Method for obtaining the first coefficient for a quadratic equation.
     * User must enter a number other than 0.
     * If the data was entered incorrectly, an appropriate message will be displayed.
     *
     * @param message -  message that is displayed on the console when prompted for input.
     * @return a number that can be used in a quadratic equation.
     */
    public double askFirstParameter(String message) {
        while (true) {
            try {
                double value = readDouble(message);
                if (value != 0) return value;
                println("The first parameter (a) cannot be zero. ");
            } catch (Exception e) {
                println("One of the parameters is invalid. Please enter a number. ");
                readLine();
            }
        }
    }

    /**
     * Method for calculating the discriminant.
     *
     * @param a - first coefficient of a quadratic equation.
     * @param b - second coefficient of a quadratic equation.
     * @param c - third coefficient of a quadratic equation.
     * @return result of calculating the discriminant.
     */
    public double findDiscriminator(double a, double b, double c) {
        return Math.pow(b, 2) - 4 * a * c;
    }

    /**
     * Method for finding the roots of a quadratic equation.
     * The number of roots is determined by the discriminant:
     * if the discriminant is positive, then two roots will be derived,
     * if it is zero - one root, if negative - there are no roots.
     *
     * @param a             - first coefficient of a quadratic equation.
     * @param b             - second coefficient of a quadratic equation.
     * @param discriminator - numerical value of the discriminant.
     */
    public void findRoots(double a, double b, double discriminator) {
        double root1, root2;

        if (discriminator > 0) {
            root1 = (-b - Math.sqrt(discriminator)) / (2 * a);
            root2 = (-b + Math.sqrt(discriminator)) / (2 * a);

            println("There are two roots: " + root1 + " and " + root2);
        } else if (discriminator == 0) {
            root1 = -b / (2 * a);

            println("There are one root: " + root1);
        } else {
            println("There are no real roots");
        }
    }
}