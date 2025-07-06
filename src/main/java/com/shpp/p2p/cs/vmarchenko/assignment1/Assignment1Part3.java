package com.shpp.p2p.cs.vmarchenko.assignment1;

/**
 * The third task: find the middle
 * This class implements the following actions:
 * Karel starts in the south-west corner and looks east.
 * To find the middle of the line he is standing on, he will do the following:
 * fill the entire row with beepers, then remove the beepers that are at the edges.
 * Once he realizes that this is the last beeper, he will turn it over.
 */
public class Assignment1Part3 extends SuperKarel {
    /**
     * The main method of application. Allows to test the given program.
     * A function is called to fill the line with beepers,
     * later on at the edges Karel will remove the beepers
     * for the correct operation of the algorithm.
     * Next, a method is called to find the middle.
     * Finally, Karel places a beeper in the middle of the line.
     */
    public void run() throws Exception {
        fillTheRowWithBeepers();

        // Beepers are picked up at the edges of the line
        pickBeeper();
        turnAround();
        goForward();
        if (beepersPresent()) {
            pickBeeper();
        }

        findTheMiddle();

        // Place the beeper in the middle of the line
        putBeeper();
    }

    /**
     * Method to fill a line with beepers.
     * Called recursively if there are no walls or map edges in front of Karel yet.
     */
    public void fillTheRowWithBeepers() throws Exception {
        putBeeper();
        if (frontIsClear()) {
            move();
            fillTheRowWithBeepers();
        }
    }

    /**
     * Method for finding the middle. It is implemented as follows:
     * Karel will gradually remove the outer beepers
     * until he reaches the center of the line.
     * Before starting work, the first and last beepers must be removed.
     */
    public void findTheMiddle() throws Exception {
        // A 180 degrees turn, as Karel initially has his back to the beepers
        turnAround();

        // Take a step forward so that Karel stands on the extreme beeper
        if (frontIsClear()) {
            move();
        }

        if (beepersPresent()) {
            pickBeeper();
            move();

            // Moving to the next edge of the line
            while (frontIsClear() && beepersPresent()) {
                move();
            }

            // Call the same method, which will remove the beeper on this edge.
            findTheMiddle();
        }
    }
}