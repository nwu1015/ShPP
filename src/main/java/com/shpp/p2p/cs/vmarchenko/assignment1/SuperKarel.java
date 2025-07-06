package com.shpp.p2p.cs.vmarchenko.assignment1;

import com.shpp.karel.KarelTheRobot;

/**
 * Superclass of the application. Inherited to add generic methods
 * like a turnRight(), turnAround().
 */
public class SuperKarel extends KarelTheRobot {

    /**
     * Implements left movement for Karel.
     * Performed by turning left 3 times
     *
     * @throws Exception if Karel is unable to turn
     */
    public void turnRight() throws Exception {
        turnLeft();
        turnLeft();
        turnLeft();
    }

    /**
     * Karel's 180 degrees turn.
     * Performed by turning left 2 times
     *
     * @throws Exception if Karel is unable to turn
     */
    public void turnAround() throws Exception {
        turnLeft();
        turnLeft();
    }

    /**
     * Karel moves forward until there are no walls in front of him.
     */
    public void goForward() throws Exception {
        while (frontIsClear()) {
            move();
        }
    }
}
