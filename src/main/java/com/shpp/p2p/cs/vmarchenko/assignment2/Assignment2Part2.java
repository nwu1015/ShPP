package com.shpp.p2p.cs.vmarchenko.assignment2;

import acm.graphics.GOval;
import acm.graphics.GRect;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * The second task: build an illusory contours.
 * The program creates a white rectangle that is superimposed on four circles.
 * The circles are located at the corners of the program window.
 */
public class Assignment2Part2 extends WindowProgram {
    /**
     * Variable to change the width of the application
     */
    public static final int APPLICATION_WIDTH = 300;

    /**
     * Variable to change the height of the application
     */
    public static final int APPLICATION_HEIGHT = 300;

    /**
     * Variable for storing the size of circles
     */
    public static double CIRCLE_SIZE;

    /**
     * A constant used to calculate the size of a circle.
     * It acts as a divisor for the width.
     */
    public static final double DIVIDER = 4;

    /**
     * The main method of application. Allows to test the given program.
     * Calls methods to construct four circles, as well as a white rectangle.
     */
    public void run() {
        CIRCLE_SIZE = getWidth() / DIVIDER;

        createOval(0, 0, CIRCLE_SIZE, CIRCLE_SIZE);
        createOval(0, getHeight() - CIRCLE_SIZE, CIRCLE_SIZE, CIRCLE_SIZE);
        createOval(getWidth() - CIRCLE_SIZE, 0, CIRCLE_SIZE, CIRCLE_SIZE);
        createOval(getWidth() - CIRCLE_SIZE, getHeight() - CIRCLE_SIZE,
                CIRCLE_SIZE, CIRCLE_SIZE);

        createRectangle(CIRCLE_SIZE / 2.0, CIRCLE_SIZE / 2.0,
                getWidth() - CIRCLE_SIZE, getHeight() - CIRCLE_SIZE);
    }

    /**
     * Creates a circle according to given parameters.
     *
     * @param x1 First point on the x-axis
     * @param y1 First point on the y-axis
     * @param x2 Second point on the x-axis
     * @param y2 Second point on the y-axis
     */
    public void createOval(double x1, double y1, double x2, double y2) {
        GOval go = new GOval(x1, y1, x2, y2);
        go.setFilled(true);
        add(go);
    }

    /**
     * Creates a rectangle according to given parameters.
     *
     * @param x1 First point on the x-axis
     * @param y1 First point on the y-axis
     * @param x2 Second point on the x-axis
     * @param y2 Second point on the y-axis
     */
    public void createRectangle(double x1, double y1, double x2, double y2) {
        GRect rect = new GRect(x1, y1, x2, y2);
        rect.setFilled(true);
        rect.setColor(Color.WHITE);
        add(rect);
    }
}