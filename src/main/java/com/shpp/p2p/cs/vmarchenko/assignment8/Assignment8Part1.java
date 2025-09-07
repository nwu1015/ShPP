package com.shpp.p2p.cs.vmarchenko.assignment8;

import acm.graphics.GOval;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

/**
 * Makes snowflakes fall from the top of the screen.
 * A certain number of snowflakes are created,
 * the appropriate parameters are set, and an eternal animation is started.
 * It is possible to change the movement of the snowflakes by adding wind.
 */

public class Assignment8Part1 extends WindowProgram {
    /**
     * A constant that denotes the maximum radius of a circle (snowflake)
     */
    private static final int MAX_RADIUS = 50;

    /**
     * A constant that indicates the maximum number of snowflakes on the screen
     */
    private static final int NUMBER_OF_SNOW = 30;

    /**
     * A constant that denotes the maximum amplitude of a snowflake
     */
    private static final double MAX_AMPLITUDE = 30;

    /**
     * A constant that denotes the minimum speed of a snowflake
     */
    private static final double MIN_SPEED_Y = 0.5;

    /**
     * A constant that denotes the maximum speed of a snowflake
     */
    private static final double MAX_SPEED_Y = 2;

    /**
     * The array of colors that can be on snowflakes
     */
    private final Color[] COLORS = {Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.CYAN};

    /**
     * To indicate whether the wind is currently active
     */
    private boolean isWindActive = false;

    /**
     * Wind direction (right or left)
     */
    private double windDirection = 0;

    /**
     * The main method of application. Allows to test the given program.
     * Creates all the necessary snowflakes,
     * adds an event listener to the mouse, and starts the motion animation
     */
    public void run() {
        ArrayList<SnowParameters> snows = new ArrayList<>();
        addAllSnowflakes(snows);
        addMouseListeners();
        doAnimation(snows);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        isWindActive = !isWindActive;

        double mouseX = mouseEvent.getX();

        // If you click the mouse on the left side of the screen,
        // the snowflakes should move to the left and vice versa.
        // The second click turns off the wind.
        if (mouseX < getWidth() / 2.0 && isWindActive) {
            windDirection = -0.5;
        } else if (!isWindActive) {
            windDirection = 0;
        } else {
            windDirection = 0.5;
        }
    }

    /**
     * Creates a circle according to given parameters.
     *
     * @param x1    First point on the x-axis
     * @param y1    First point on the y-axis
     * @param x2    Second point on the x-axis
     * @param y2    Second point on the y-axis
     * @param color Color of the given circle
     * @return A circle has been created and is already added to the program.
     */
    private GOval createSnowflake(double x1, double y1, double x2, double y2, Color color) {
        GOval snow = new GOval(x1, y1, x2, y2);
        snow.setColor(color);
        snow.setFilled(true);
        return snow;
    }

    /**
     * A method that adds the necessary properties for movement to each snowflake.
     *
     * @param snows a dynamic array that will store all the snowflakes.
     */
    private void addAllSnowflakes(ArrayList<SnowParameters> snows) {
        Random rand = new Random();
        for (int i = 0; i < NUMBER_OF_SNOW; i++) {
            int radius = rand.nextInt(1, MAX_RADIUS);
            double startX = rand.nextDouble(getWidth() - radius * 2);
            double speedY = rand.nextDouble(MIN_SPEED_Y, MAX_SPEED_Y);
            double angle = rand.nextDouble(0, 2 * Math.PI);
            double amplitude = rand.nextDouble(1, MAX_AMPLITUDE);

            Color color = COLORS[rand.nextInt(COLORS.length)];

            GOval snow = createSnowflake(startX, 0, radius, radius, color);
            add(snow);

            SnowParameters snowParameters =
                    new SnowParameters(snow, startX, speedY, angle, radius, amplitude);
            snows.add(snowParameters);
        }
    }

    /**
     * Starting the animation. Each snowflake is taken from the array and
     * the next point on the screen is calculated using the formula.
     *
     * @param snows a dynamic array that store all the snowflakes.
     */
    private void doAnimation(ArrayList<SnowParameters> snows) {
        while (true) {
            for (SnowParameters object : snows) {
                // Sinusoidal motion,
                // namely object.getAmplitude() * Math.sin(object.getAngle()),
                // found on the Internet.
                // Necessary to calculate the maximum deviation from the center of
                // the snowflake and movement to the right or left (thanks to angle)
                double x = object.getStartX() +
                        object.getAmplitude() * Math.sin(object.getAngle()) +
                        // move right or left if wind is on
                        windDirection * object.getSnow().getY();
                double y = object.getSnow().getY() + object.getSpeedY();

                object.getSnow().setLocation(x, y);

                object.setAngle(object.getAngle() + 0.1);

                // If the snowflake has flown across the entire screen,
                // it returns to the beginning.
                if (object.getSnow().getY() > getHeight() - object.getRadius()) {
                    object.getSnow().setLocation(x, 0);
                }
            }
            pause(1000 / 60.0);
        }
    }
}