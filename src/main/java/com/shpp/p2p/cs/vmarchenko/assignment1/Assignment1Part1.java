package com.shpp.p2p.cs.vmarchenko.assignment1;

/**
 * The first task: Collect a newspaper.
 * This class implements the following actions: Karel goes to the newspaper (beeper),
 * picks it up and returns to its original position.
 */
public class Assignment1Part1 extends SuperKarel {
    /**
     * The main method of application. Allows to test the given program.
     * It has a call to three methods that are provided in the task: go to the newspaper,
     * pick it up and go back.
     */
    public void run() throws Exception {
        goToNewspaper();
        pickUpNewspaper();
        goBack();
    }

    /**
     * Karel go to the newspaper.
     * As a result, he will find himself in the same block as newspaper (beeper).
     */
    public void goToNewspaper() throws Exception {
        goForward();
        turnRight();
        goToDoor();
        turnLeft();
        move();
        move();
    }

    /**
     * Implements newspaper collection.
     */
    public void pickUpNewspaper() throws Exception {
        pickBeeper();
    }

    /**
     * Karel returns to the starting position and
     * looks at the original direction of the world (east).
     */
    public void goBack() throws Exception {
        turnAround();
        goForward();
        turnRight();
        move();
        turnRight();
    }

    /**
     * Karel approaches the door.
     * He begins his movement from the north-east corner of his house.
     */
    public void goToDoor() throws Exception {
        while (leftIsBlocked()) {
            move();
        }
    }
}