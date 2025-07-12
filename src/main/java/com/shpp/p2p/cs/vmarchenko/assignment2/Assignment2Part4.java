package com.shpp.p2p.cs.vmarchenko.assignment2;

import acm.graphics.GLabel;
import acm.graphics.GLine;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * The fourth task: Tricolor flags.
 * The program creates a flag with the specified colors in the middle.
 * In the lower right corner, an inscription with information about the flag is created.
 */
public class Assignment2Part4 extends WindowProgram {

    /**
     * Constant for defining the width of the flag
     */
    private static final int FLAG_WIDTH = 150;

    /**
     * Constant for defining the height of the flag
     */
    private static final int FLAG_HEIGHT = 150;

    /**
     * Constant for defining the name of the flag
     */
    private static final String FLAG_NAME = "Belgium";

    /**
     * Constant for defining 1 flag color
     */
    private static final Color FLAG_FIRST_LINE_COLOR = Color.BLACK;

    /**
     * Constant for defining 2 flag color
     */
    private static final Color FLAG_SECOND_LINE_COLOR = Color.YELLOW;

    /**
     * Constant for defining 3 flag color
     */
    private static final Color FLAG_THIRD_LINE_COLOR = Color.RED;

    /**
     * Divisor used to calculate text size
     */
    private static final int DIVIDER = 16;

    /**
     * Variable to change the width of the application
     */
    public static final int APPLICATION_WIDTH = 300;

    /**
     * Variable to change the height of the application
     */
    public static final int APPLICATION_HEIGHT = 300;

    /**
     * The main method of application. Allows to test the given program.
     * Calls the appropriate methods to construct each line of the flag,
     * as well as to render the text on the side.
     */
    public void run() {
        // Calculating the width of one line in a flag
        double widthLine = FLAG_WIDTH / 3.0;

        double widthDifference = getWidth() - FLAG_WIDTH;
        double heightDifference = getHeight() - FLAG_HEIGHT;

        createFlagLine(widthDifference / 2.0, heightDifference / 2.0,
                widthLine, FLAG_HEIGHT, FLAG_FIRST_LINE_COLOR);

        createFlagLine((widthDifference / 2.0) + widthLine, heightDifference / 2.0,
                widthLine, FLAG_HEIGHT, FLAG_SECOND_LINE_COLOR);

        createFlagLine((widthDifference / 2.0) + (widthLine * 2), heightDifference / 2.0,
                widthLine, FLAG_HEIGHT, FLAG_THIRD_LINE_COLOR);

        createText();
    }

    /**
     * Creates a rectangle (flag line) according to given parameters.
     *
     * @param x1 First point on the x-axis
     * @param y1 First point on the y-axis
     * @param x2 Second point on the x-axis
     * @param y2 Second point on the y-axis
     */
    public void createFlagLine(double x1, double y1, double x2, double y2, Color color) {
        GRect rect = new GRect(x1, y1, x2, y2);
        rect.setColor(color);
        rect.setFilled(true);
        add(rect);
    }

    /**
     * Method for creating text in the lower right corner of application.
     */
    public void createText() {
        GLabel lbl = new GLabel("Flag of " + FLAG_NAME);
        lbl.setFont(new Font("Times", Font.BOLD, getWidth() / DIVIDER));
        add(lbl, getWidth() - lbl.getWidth(), getHeight() - lbl.getDescent());
    }
}