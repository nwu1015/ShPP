package com.shpp.p2p.cs.vmarchenko.assignment4;

import acm.graphics.*;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * The class implements the game "Breakout".
 * A racket, a ball and bricks are created that the player needs to knock down.
 * The player has a total of 3 attempts.
 * If all the bricks are knocked down, a message about victory will be displayed.
 * If 3 attempts were lost, a message about defeat will be displayed.
 */
public class Breakout extends WindowProgram {
    /**
     * Width and height of application window in pixels
     */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /**
     * Dimensions of the paddle
     */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /**
     * Offset of the paddle up from the bottom
     */
    private static final int PADDLE_Y_OFFSET = 30;

    /**
     * Number of bricks per row
     */
    private static final int NBRICKS_PER_ROW = 10;

    /**
     * Number of rows of bricks
     */
    private static final int NBRICK_ROWS = 10;

    /**
     * Separation between bricks
     */
    private static final int BRICK_SEP = 4;

    /**
     * It's a bad idea to calculate brick width from APPLICATION_WIDTH
     */
    private static int BRICK_WIDTH;

    /**
     * Height of a brick
     */
    private static final int BRICK_HEIGHT = 8;

    /**
     * Radius of the ball in pixels
     */
    private static final int BALL_RADIUS = 10;

    /**
     * Offset of the top brick row from the top
     */
    private static final int BRICK_Y_OFFSET = 70;

    /**
     * Number of turns
     */
    private static int NTURNS = 3;

    /**
     * Brick colors
     */
    private static final Color[] COLORS_IN_BRICKS =
            {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN};

    /**
     * Brick counter
     */
    private int brickCounts = NBRICKS_PER_ROW * NBRICK_ROWS;

    /**
     * Racket object
     */
    private GRect paddle;

    /**
     * Ball object
     */
    private GOval ball;

    /**
     * Change of coordinates
     */
    private double vx, vy = 3.0;

    /**
     * Variable for reproducing the movement of the ball
     */
    private boolean isRunning = true;

    /**
     * The main method of application. Allows to test the given program.
     * Calculates the width of the brick, creates the necessary objects (paddle, ball, bricks).
     * Also adds mouse tracking.
     */
    public void run() {
        BRICK_WIDTH = (getWidth() - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

        buildLevel();
        createPaddle();
        addMouseListeners();
        createBall();
        waitForClick();
        moveBall();
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        // The middle of the paddle will be near the mouse
        double newXPos = mouseEvent.getX() - PADDLE_WIDTH / 2.0;

        // Leave the paddle on the map if the mouse is not in the application window
        if (newXPos < 0) newXPos = 0;
        if (newXPos + PADDLE_WIDTH > getWidth()) newXPos = getWidth() - PADDLE_WIDTH;

        paddle.setLocation(newXPos, getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
    }

    /**
     * The basic implementation of the ball's movement.
     * Its speed and angle of fall are determined.
     * It is also checked what it touches.
     * The ball bounces off objects and walls.
     */
    public void moveBall() {
        // Generation of the angle of incidence along the X coordinate
        RandomGenerator rgen = RandomGenerator.getInstance();
        vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5))
            vx = -vx;

        while (isRunning) {
            ball.setLocation(ball.getX() + vx, ball.getY() + vy);

            checkBallCollisionWithBottomWall();
            checkBallCollisionWithWall();
            checkBallCollisionWithObjects();
            pause(10);
        }
    }

    /**
     * Checks 4 points around the ball.
     * If the ball touches any object with at least one corner,
     * the method will return that object.
     *
     * @return the object that the ball touched.
     */
    public GObject getCollidingObject() {
        // upper left corner
        GObject collidingObject = getElementAt(ball.getX(), ball.getY());
        if (collidingObject != null) return collidingObject;

        // upper right corner
        GObject collidingObject2 = getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY());
        if (collidingObject2 != null) return collidingObject2;

        // lower left corner
        GObject collidingObject3 = getElementAt(ball.getX(), ball.getY() + BALL_RADIUS * 2);
        if (collidingObject3 != null) return collidingObject3;

        // lower right corner
        GObject collidingObject4 = getElementAt(ball.getX() + BALL_RADIUS * 2,
                ball.getY() + BALL_RADIUS * 2);
        if (collidingObject4 != null) return collidingObject4;

        // no object detected
        return null;
    }

    /**
     * Builds rows of bricks of a given size through constants.
     */
    public void buildLevel() {
        double bricksWidth = NBRICKS_PER_ROW * BRICK_WIDTH + (NBRICKS_PER_ROW - 1) * BRICK_SEP;

        // Every 2 rows are painted in one color
        int stepCounter = 0;

        // Color index in array
        int colorIndex = 0;

        for (int row = 0; row < NBRICK_ROWS; row++) {
            for (int col = 0; col < NBRICKS_PER_ROW; col++) {
                GRect rect = new GRect(
                        (getWidth() - bricksWidth) / 2.0 + col * (BRICK_WIDTH + BRICK_SEP),
                        BRICK_Y_OFFSET + row * (BRICK_HEIGHT + BRICK_SEP),
                        BRICK_WIDTH, BRICK_HEIGHT);
                rect.setFilled(true);

                rect.setColor(COLORS_IN_BRICKS[colorIndex]);

                add(rect);
            }
            stepCounter++;

            if (stepCounter > 1) {
                colorIndex++;
                stepCounter = 0;
            }

            if (colorIndex >= COLORS_IN_BRICKS.length) colorIndex = 0;
        }
    }

    /**
     * A ball is created and added to the application.
     */
    public void createBall() {
        ball = new GOval((getWidth() - BALL_RADIUS) / 2.0,
                (getHeight() - BALL_RADIUS) / 2.0,
                BALL_RADIUS * 2, BALL_RADIUS * 2);
        ball.setFilled(true);
        add(ball);
    }

    /**
     * A paddle is created and added to the application.
     */
    public void createPaddle() {
        paddle = new GRect((getWidth() - PADDLE_WIDTH) / 2.0,
                getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT,
                PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFilled(true);
        add(paddle);
    }

    /**
     * Text is added in the center.
     *
     * @param text what message should be displayed on the screen.
     */
    public void showText(String text) {
        GLabel label = new GLabel(text);
        label.setFont(new Font("Times", Font.BOLD, 16));
        label.setColor(Color.RED);
        add(label, (getWidth() - label.getWidth()) / 2.0, getHeight() / 2.0);
    }

    /**
     * Realization of the ball bouncing off the paddle
     *
     * @param collider the object the ball touched (paddle)
     */
    public void bounceFromPaddle(GObject collider) {
        if (ball.getX() + BALL_RADIUS < collider.getX() + PADDLE_WIDTH / 2.0) {
            vx = -Math.abs(vx);
        } else {
            vx = Math.abs(vx);
        }

        // so that the ball doesn't get stuck inside
        ball.setLocation(ball.getX(), collider.getY() - BALL_RADIUS * 2);

        vy = -vy;
    }

    /**
     * Recover the ball after losing.
     */
    public void resetBall() {
        remove(ball);
        createBall();
        waitForClick();
    }

    /**
     * Implementation of bouncing the ball off the walls.
     */
    public void checkBallCollisionWithWall() {
        if (ball.getY() <= 0) vy = -vy;
        if (ball.getX() <= 0 || ball.getX() + BALL_RADIUS * 2 >= getWidth()) vx = -vx;
    }

    /**
     * Checking what the ball touched.
     * If it's a paddle, the appropriate method is called.
     * Otherwise, it could just be a brick, which is destroyed when it touches the ball.
     */
    public void checkBallCollisionWithObjects() {
        GObject collider = getCollidingObject();

        if (collider == paddle) {
            bounceFromPaddle(collider);
        } else if (collider != null) {
            remove(collider);
            brickCounts -= 1;
            vy = -vy;

            // If there are no bricks left
            if (brickCounts <= 0) {
                showText("YOU WIN");
                isRunning = false;
            }
        }
    }

    /**
     * Checking whether the ball touched the bottom wall.
     * That is, the player did not hit the ball.
     */
    public void checkBallCollisionWithBottomWall() {
        if (ball.getY() > getHeight()) {
            NTURNS -= 1;
            if (NTURNS <= 0) {
                showText("YOU LOSE");
                isRunning = false;
            } else {
                resetBall();
            }
        }
    }
}