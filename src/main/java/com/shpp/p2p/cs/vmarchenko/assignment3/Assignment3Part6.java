package com.shpp.p2p.cs.vmarchenko.assignment3;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.util.HashMap;

/**
 * The sixth task: Five seconds of fame.
 * The program implements the following animation:
 * the movement of several planets around the Sun.
 */
public class Assignment3Part6 extends WindowProgram {
    /**
     * Variable to change the width of the application
     */
    public static final int APPLICATION_WIDTH = 600;

    /**
     * Variable to change the height of the application
     */
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Animation duration in milliseconds.
     */
    private static final int ANIMATION_DURATION = 5000;

    /**
     * Distance between planets
     */
    private static final int SPACE_BETWEEN_PLANETS = 50;

    /**
     * The size of the sun
     */
    private static final int SUN_SIZE = 50;

    /**
     * The size of the first planet
     */
    private static final int PLANET1_SIZE = 30;

    /**
     * The color of the first planet
     */
    private static final Color PLANET1_COLOR = Color.RED;

    /**
     * The size of the second planet
     */
    private static final int PLANET2_SIZE = 40;

    /**
     * The color of the second planet
     */
    private static final Color PLANET2_COLOR = Color.BLUE;

    /**
     * The size of the third planet
     */
    private static final int PLANET3_SIZE = 40;

    /**
     * The color of the third planet
     */
    private static final Color PLANET3_COLOR = Color.GREEN;

    /**
     * The main method of application. Allows to test the given program.
     * Here the Sun, planets are created and the animation starts.
     */
    public void run() {
        // To maintain the relationship between the planet and the angle of rotation
        HashMap<GOval, Double> planets = new HashMap<>();

        createOval((getWidth() - SUN_SIZE) / 2.0, (getHeight() - SUN_SIZE) / 2.0,
                SUN_SIZE, SUN_SIZE, Color.YELLOW);

        GOval planet1 = createOval((getWidth() - PLANET1_SIZE) / 2.0,
                (getHeight() + SUN_SIZE) / 2.0 + SPACE_BETWEEN_PLANETS,
                PLANET1_SIZE, PLANET1_SIZE, PLANET1_COLOR);
        double planet1Angle = 0.07;
        planets.put(planet1, planet1Angle);

        GOval planet2 = createOval((getWidth() - PLANET2_SIZE) / 2.0,
                (getHeight() + SUN_SIZE) / 2.0 + SPACE_BETWEEN_PLANETS * 2,
                PLANET2_SIZE, PLANET2_SIZE, PLANET2_COLOR);
        double planet2Angle = 0.03;
        planets.put(planet2, planet2Angle);

        GOval planet3 = createOval((getWidth() - PLANET3_SIZE) / 2.0,
                (getHeight() + SUN_SIZE) / 2.0 + SPACE_BETWEEN_PLANETS * 3,
                PLANET3_SIZE, PLANET3_SIZE, PLANET3_COLOR);
        double planet3Angle = 0.01;
        planets.put(planet3, planet3Angle);

        doAnimation(planets);
    }

    /**
     * Creates a circle according to given parameters.
     *
     * @param x     First point on the x-axis
     * @param y     First point on the y-axis
     * @param x2    Second point on the x-axis
     * @param y2    Second point on the y-axis
     * @param color Color of the given circle
     * @return A circle has been created and is already added to the program.
     */
    public GOval createOval(double x, double y, double x2, double y2, Color color) {
        GOval oval = new GOval(x, y, x2, y2);
        oval.setFilled(true);
        oval.setColor(color);
        add(oval);

        return oval;
    }

    /**
     * Starts an animation of the movement of all planets around the sun.
     *
     * @param planets a list of all planets with their rotation angle.
     */
    public void doAnimation(HashMap<GOval, Double> planets) {
        // Start animation timer
        long startTime = System.currentTimeMillis();

        // The animation runs until it is constant with the animation duration.
        while (System.currentTimeMillis() - startTime < ANIMATION_DURATION) {

            // We apply the formula for rotation around an object to each planet.
            for (GOval planet : planets.keySet()) {
                // The formula was taken from the Internet
                // and the initial version was as follows:
                // x' = x * cos(θ) - y * sin(θ)
                // y' = x * sin(θ) + y * cos(θ)
                // where (x, y) - initial coordinates of the point.
                // (x', y') - new coordinates of the point after rotation.
                // θ is the angle of rotation in radians.
                double x = getWidth() / 2.0 + (planet.getX() - getWidth() / 2.0) *
                        Math.cos(planets.get(planet)) -
                        (planet.getY() - getHeight() / 2.0) * Math.sin(planets.get(planet));
                double y = getHeight() / 2.0 + (planet.getX() - getWidth() / 2.0) *
                        Math.sin(planets.get(planet)) +
                        (planet.getY() - getHeight() / 2.0) * Math.cos(planets.get(planet));

                planet.setLocation(x, y);
            }
            pause(1000 / 60.0);
        }

        // Stop animation timer and show in console
        println(System.currentTimeMillis() - startTime);
    }
}