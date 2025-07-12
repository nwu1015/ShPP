package com.shpp.p2p.cs.vmarchenko.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

/**
 * The third task: build a pawprint.
 * The program allows you to draw a paw print at given coordinates.
 */
public class Assignment2Part3 extends WindowProgram {
    /* Constants controlling the relative positions of the
     * three toes to the upper-left corner of the pawprint.
     */
    private static final double FIRST_TOE_OFFSET_X = 0;
    private static final double FIRST_TOE_OFFSET_Y = 20;
    private static final double SECOND_TOE_OFFSET_X = 30;
    private static final double SECOND_TOE_OFFSET_Y = 0;
    private static final double THIRD_TOE_OFFSET_X = 60;
    private static final double THIRD_TOE_OFFSET_Y = 20;

    /* The position of the heel relative to the upper-left
     * corner of the pawprint.
     */
    private static final double HEEL_OFFSET_X = 20;
    private static final double HEEL_OFFSET_Y = 40;

    /* Each toe is an oval with this width and height. */
    private static final double TOE_WIDTH = 20;
    private static final double TOE_HEIGHT = 30;

    /* The heel is an oval with this width and height. */
    private static final double HEEL_WIDTH = 40;
    private static final double HEEL_HEIGHT = 60;

    /* Variables to change the width and height of the application */
    public static final int APPLICATION_WIDTH = 300;
    public static final int APPLICATION_HEIGHT = 250;

    /**
     * The main method of application. Allows to test the given program.
     * Calls methods to build pawprints.
     */
    public void run() {
        drawPawprint(20, 20);
        drawPawprint(180, 70);
    }

    /**
     * Draws a pawprint.
     *
     * @param x The x coordinate of the upper-left corner of the bounding box for the pawprint.
     * @param y The y coordinate of the upper-left corner of the bounding box for the pawprint.
     */
    private void drawPawprint(double x, double y) {
        createOval(x + HEEL_OFFSET_X, y + HEEL_OFFSET_Y, HEEL_WIDTH, HEEL_HEIGHT);

        createOval(x + FIRST_TOE_OFFSET_X, y + FIRST_TOE_OFFSET_Y,
                TOE_WIDTH, TOE_HEIGHT);

        createOval(x + SECOND_TOE_OFFSET_X, y + SECOND_TOE_OFFSET_Y,
                TOE_WIDTH, TOE_HEIGHT);

        createOval(x + THIRD_TOE_OFFSET_X, y + THIRD_TOE_OFFSET_Y,
                TOE_WIDTH, TOE_HEIGHT);
    }

    /**
     * Creates an oval according to given parameters.
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
}
