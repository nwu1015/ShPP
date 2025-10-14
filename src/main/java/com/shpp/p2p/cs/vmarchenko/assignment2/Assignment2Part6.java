package com.shpp.p2p.cs.vmarchenko.assignment2;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;

/**
 * The sixth task: Caterpillars.
 * The program builds a caterpillar using GOval.
 * The caterpillar can consist of different segments, fill colors, and strokes.
 */
public class Assignment2Part6 extends WindowProgram {
    /**
     * A constant that stores the number of caterpillar segments.
     */
    private static final int SEGMENTS = 11;

    /**
     * A constant that stores the size of a circle (segment).
     */
    private static final double CIRCLE_SIZE = 90;

    /**
     * A constant that stores the color of the circle's fill.
     */
    private static final Color CIRCLE_COLOR = Color.GREEN;

    /**
     * A constant that stores the color of the circle's outline.
     */
    private static final Color BORDER_COLOR = Color.RED;

    /**
     * A constant that controls the horizontal overlap between adjacent circles (segments).
     * The greater the value, the more the segments will overlap.
     */
    private static final double CIRCLE_SPACING = 40;

    /**
     * The main method of application. Allows to test the given program.
     * In turn, it calls the method to build each segment of the track.
     * If it is an even segment, the height from where the circle starts to build changes.
     */
    public void run() {
        // First point on the y-axis
        double y1Height = 0;

        for (int i = 0; i < SEGMENTS; i++) {
            // Every second circle changes the y coordinate
            // so that it is built a little lower.
            if (i % 2 == 0) y1Height = CIRCLE_SIZE / 2.0;

            createOval(i, y1Height);

            y1Height = 0;
        }
    }

    /**
     * Creates an oval (segment) for the caterpillar.
     *
     * @param index segment number
     * @param y1    first point on the y-axis
     */
    public void createOval(int index, double y1) {
        GOval gOval = new GOval(index * (CIRCLE_SIZE - CIRCLE_SPACING), y1, CIRCLE_SIZE, CIRCLE_SIZE);
        gOval.setColor(BORDER_COLOR);
        gOval.setFilled(true);
        gOval.setFillColor(CIRCLE_COLOR);
        add(gOval);
    }
}