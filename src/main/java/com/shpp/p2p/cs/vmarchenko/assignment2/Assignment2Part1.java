package com.shpp.p2p.cs.vmarchenko.assignment2;

import com.shpp.cs.a.console.TextProgram;

public class Assignment2Part1 extends TextProgram {
    public void run() {
        double a = readDouble("Enter the first parameter (a): ");
        double b = readDouble("Enter the second parameter (b): ");
        double c = readDouble("Enter the third parameter (c): ");

        double discriminator = Math.pow(b, 2) - 4 * a * c;
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
