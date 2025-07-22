package com.shpp.p2p.cs.vmarchenko.assignment4;

import acm.graphics.*;
import acm.util.RandomGenerator;
import com.shpp.cs.a.graphics.WindowProgram;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Breakout extends WindowProgram {
    /** Width and height of application window in pixels */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 600;

    /** Dimensions of the paddle */
    private static final int PADDLE_WIDTH = 60;
    private static final int PADDLE_HEIGHT = 10;

    /** Offset of the paddle up from the bottom */
    private static final int PADDLE_Y_OFFSET = 30;

    /** Number of bricks per row */
    private static final int NBRICKS_PER_ROW = 10;

    /** Number of rows of bricks */
    private static final int NBRICK_ROWS = 10;

    /** Separation between bricks */
    private static final int BRICK_SEP = 4;

    /** It's a bad idea to calculate brick width from APPLICATION_WIDTH */
     private static int BRICK_WIDTH;

    /** Height of a brick */
    private static final int BRICK_HEIGHT = 8;

    /** Radius of the ball in pixels */
    private static final int BALL_RADIUS = 10;

    /** Offset of the top brick row from the top */
    private static final int BRICK_Y_OFFSET = 70;

    /** Number of turns */
    private static int NTURNS = 3;

    private static final Color[] COLORS_IN_BRICKS = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN};

    private static int BRICK_COUNTS = NBRICKS_PER_ROW * NBRICK_ROWS;

    private GRect paddle;
    private GOval ball;

    private double vx, vy = 3.0;

    public void run() {
        BRICK_WIDTH = (getWidth() - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

        addMouseListeners();

        buildLevel();

        paddle = new GRect( (getWidth() - PADDLE_WIDTH) / 2.0, getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);
        paddle.setFilled(true);
        add(paddle);

        createBall();

        waitForClick();
        moveBall();
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        double newXPos = mouseEvent.getX() - PADDLE_WIDTH / 2.0;

        if (newXPos < 0) newXPos = 0;
        if (newXPos + PADDLE_WIDTH > getWidth()) newXPos = getWidth() - PADDLE_WIDTH;

        paddle.setLocation(newXPos, getHeight() - PADDLE_Y_OFFSET - PADDLE_HEIGHT);
    }




    public void moveBall(){
        RandomGenerator rgen = RandomGenerator.getInstance();
        vx = rgen.nextDouble(1.0, 3.0);
        if (rgen.nextBoolean(0.5))
            vx = -vx;

        while(true){
            ball.setLocation(ball.getX() + vx, ball.getY() + vy);

            if(ball.getY() > getHeight()){
                println("YOU LOSE");
                NTURNS -= 1;

                if(NTURNS <= 0) {
                    println("NO TURNS");
                    break;
                }

                remove(ball);
                createBall();
                waitForClick();

            }

            if(ball.getY() < 0 || ball.getY() > getHeight()) vy = -vy;
            if(ball.getX() < 0 || ball.getX() > getWidth()) vx = -vx;

            GObject collider = getCollidingObject();

            if (collider == paddle) {
                vy = -vy;
            } else if (collider != null) {
                remove(collider);
                BRICK_COUNTS -= 1;
                vy = -vy;

                if (BRICK_COUNTS <= 0) {
                    println("NO BRICK_COUNTS");
                    showWinText();
                    break;
                }

            }

            pause(10);
        }
    }

    public GObject getCollidingObject(){
        if (getElementAt(ball.getX(), ball.getY()) != null) return getElementAt(ball.getX(), ball.getY());
        if (getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY()) != null) return getElementAt(ball.getX(), ball.getY());
        if (getElementAt(ball.getX(), ball.getY() + BALL_RADIUS * 2) != null) return getElementAt(ball.getX(), ball.getY());
        if (getElementAt(ball.getX() + BALL_RADIUS * 2, ball.getY() + BALL_RADIUS * 2) != null) return getElementAt(ball.getX(), ball.getY());

        return null;
    }

    public void buildLevel(){
        double bricksWidth = NBRICKS_PER_ROW * BRICK_WIDTH + (NBRICKS_PER_ROW - 1) * BRICK_SEP;

        int stepCounter = 0;
        int colorIndex = 0;

        for(int row = 0; row < NBRICK_ROWS; row++){
            for(int col = 0; col < NBRICKS_PER_ROW; col++){
                GRect rect = new GRect((getWidth() - bricksWidth ) / 2.0 + col * (BRICK_WIDTH + BRICK_SEP),
                        BRICK_Y_OFFSET + row * (BRICK_HEIGHT + BRICK_SEP), BRICK_WIDTH, BRICK_HEIGHT);
                rect.setFilled(true);

                rect.setColor(COLORS_IN_BRICKS[colorIndex]);

                add(rect);
            }
            stepCounter++;

            if(stepCounter > 1){
                colorIndex++;
                stepCounter = 0;
            }

            if(colorIndex >= COLORS_IN_BRICKS.length) colorIndex = 0;
        }
    }

    public void createBall(){
        ball = new GOval((getWidth() - BALL_RADIUS) / 2.0, (getHeight() - BALL_RADIUS) / 2.0, BALL_RADIUS, BALL_RADIUS);
        ball.setFilled(true);
        add(ball);
    }

    public void showWinText(){
        GLabel label = new GLabel("YOU WIN");
        label.setFont(new Font("Times", Font.BOLD, 16));
        label.setColor(Color.RED);
        add(label, (getWidth() - label.getWidth()) / 2.0, getHeight() / 2.0);
    }
}