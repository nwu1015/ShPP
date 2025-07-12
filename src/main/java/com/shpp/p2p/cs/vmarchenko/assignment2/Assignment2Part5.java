package com.shpp.p2p.cs.vmarchenko.assignment2;

import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

/**
 * The fifth task: optical illusion.
 * The program builds a matrix of a given size from filled black squares.
 * The matrix is centered in the application window.
 */
public class Assignment2Part5 extends WindowProgram {
    /**
     * The number of rows and columns in the grid, respectively.
     */
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 6;

    /**
     * The width and height of each box.
     */
    private static final double BOX_SIZE = 30;

    /**
     * The horizontal and vertical spacing between the boxes.
     */
    private static final double BOX_SPACING = 10;

    /**
     * The main method of application. Allows to test the given program.
     * Calculates the size of the matrix and also calls the method for constructing rectangles,
     * which is in a nested loop.
     */
    public void run() {
        // the size of the matrix
        double matrixWidth = NUM_COLS * BOX_SIZE + (NUM_COLS - 1) * BOX_SPACING;
        double matrixHeight = NUM_ROWS * BOX_SIZE + (NUM_ROWS - 1) * BOX_SPACING;

        // first cycle for constructing rows
        for (int row = 0; row < NUM_ROWS; row++) {

            // second loop to build columns
            for (int col = 0; col < NUM_COLS; col++) {
                createRect((getWidth() - matrixWidth) / 2.0 + col * (BOX_SIZE + BOX_SPACING),
                        (getHeight() - matrixHeight) / 2.0 + row * (BOX_SIZE + BOX_SPACING),
                        BOX_SIZE, BOX_SIZE);
            }
        }
    }

    /**
     * Creates a rectangle according to given parameters.
     *
     * @param x1 First point on the x-axis
     * @param y1 First point on the y-axis
     * @param x2 Second point on the x-axis
     * @param y2 Second point on the y-axis
     */
    public void createRect(double x1, double y1, double x2, double y2) {
        GRect rect = new GRect(x1, y1, x2, y2);
        rect.setFilled(true);
        add(rect);
    }
}