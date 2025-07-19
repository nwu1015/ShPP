package com.shpp.p2p.cs.vmarchenko.assignment3;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

/**
 * The fourth task: Pyramid.
 * The program builds a pyramid of the specified size.
 * The structure starts from the bottom of the window.
 */
public class Assignment3Part4 extends WindowProgram {
    /**
     * A constant that sets the height of the brick.
     */
    private static final int BRICK_HEIGHT = 30;

    /**
     * A constant that sets the width of the brick.
     */
    private static final int BRICK_WIDTH = 50;

    /**
     * A constant that sets how many bricks will be in the base.
     */
    private static final int BRICKS_IN_BASE = 10;

    /**
     * A constant that sets the height of the window.
     */
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * A constant that sets the width of the window.
     */
    public static final int APPLICATION_WIDTH = 600;

    /**
     * The main method of application. Allows to test the given program.
     * Calls the method to build a pyramid.
     */
    public void run() {
        buildPyramid();
    }

    /**
     * A method for creating a pyramid in the program window.
     * The size of the pyramid is calculated using constants defined in the program.
     */
    public void buildPyramid() {
        // Number of rows in the pyramid
        for (int row = 0; row < BRICKS_IN_BASE; row++) {
            // The number of bricks in one row.
            int numberOfBricks = BRICKS_IN_BASE - row;

            // The starting location from which a row of bricks is built.
            double x = (getWidth() - numberOfBricks * BRICK_WIDTH) / 2.0;
            double y = getHeight() - (row + 1) * BRICK_HEIGHT;

            // A loop to create bricks in a row.
            for (int i = 0; i < numberOfBricks; i++) {
                GRect rect = new GRect(x + i * BRICK_WIDTH, y, BRICK_WIDTH, BRICK_HEIGHT);
                add(rect);
            }
        }
    }
}