package com.shpp.p2p.cs.vmarchenko.assignment8;

import acm.graphics.GOval;

/**
 * Class for storing the necessary snowflake parameters
 */
public class SnowParameters {

    /**
     * snowflake object
     */
    private GOval snow;

    /**
     * initial x-coordinate of the snowflake
     */
    private double startX;

    /**
     * snowflake falling speed
     */
    private double speedY;

    /**
     * The starting position for the snowflake to swing.
     * It is necessary that all snowflakes do not move at the same rate
     */
    private double angle;

    /**
     * Snowflake radius
     */
    private int radius;

    /**
     * maximum amplitude of snowflake oscillations
     */
    private double amplitude;

    /**
     * Constructs a SnowParameters object with all necessary properties for a snowflake.
     *
     * @param snow      the GOval object representing the snowflake
     * @param startX    the initial x-coordinate of the snowflake (center of horizontal oscillation)
     * @param speedY    the vertical falling speed of the snowflake in pixels per frame
     * @param angle     the starting angle for horizontal oscillation in radians
     * @param radius    the radius of the snowflake in pixels
     * @param amplitude the maximum amplitude of horizontal oscillation in pixels
     */
    public SnowParameters(GOval snow, double startX, double speedY,
                          double angle, int radius, double amplitude) {
        this.snow = snow;
        this.startX = startX;
        this.speedY = speedY;
        this.angle = angle;
        this.radius = radius;
        this.amplitude = amplitude;
    }

    /**
     * Returns the GOval object representing the snowflake.
     *
     * @return the snowflake GOval
     */
    public GOval getSnow() {
        return snow;
    }

    /**
     * Sets a new GOval object for the snowflake.
     *
     * @param snow the new GOval object
     */
    public void setSnow(GOval snow) {
        this.snow = snow;
    }

    /**
     * Returns the initial x-coordinate of the snowflake.
     *
     * @return initial x-coordinate
     */
    public double getStartX() {
        return startX;
    }

    /**
     * Sets the initial x-coordinate of the snowflake.
     *
     * @param startX new initial x-coordinate
     */
    public void setStartX(double startX) {
        this.startX = startX;
    }

    /**
     * Returns the vertical falling speed of the snowflake.
     *
     * @return falling speed in pixels per frame
     */
    public double getSpeedY() {
        return speedY;
    }

    /**
     * Sets the vertical falling speed of the snowflake.
     *
     * @param speedY new falling speed in pixels per frame
     */
    public void setSpeedY(double speedY) {
        this.speedY = speedY;
    }

    /**
     * Returns the current angle used for horizontal oscillation.
     *
     * @return current angle in radians
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Sets the angle for horizontal oscillation.
     *
     * @param angle new angle in radians
     */
    public void setAngle(double angle) {
        this.angle = angle;
    }

    /**
     * Returns the radius of the snowflake.
     *
     * @return radius in pixels
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Sets the radius of the snowflake.
     *
     * @param radius new radius in pixels
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    /**
     * Returns the maximum amplitude of horizontal oscillation.
     * This defines how far the snowflake swings from its startX position.
     *
     * @return amplitude in pixels
     */
    public double getAmplitude() {
        return amplitude;
    }

    /**
     * Sets the maximum amplitude of horizontal oscillation.
     *
     * @param amplitude new amplitude in pixels
     */
    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }
}