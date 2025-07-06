package com.shpp.p2p.cs.vmarchenko.assignment1;

/**
 * The second task: build a columns
 * This class implements the following actions: Karel builds columns in 1st, 4th, 9th cells in the world.
 * The last column will be close to the edge of the level.
 * It also has the ability to build columns of different heights.
 */
public class Assignment1Part2 extends SuperKarel {
    /**
     * The main method of application. Allows to test the given program.
     * Initially, Karel is at the south-western point of the map.
     * Here he builds the first column, returns to his original position,
     * and checks if there are any more cells on which to build other columns.
     */
    public void run() throws Exception {
        processColumn();
        while (frontIsClear()) {
            goToTheNextColumn();
            processColumn();
        }
    }

    /**
     * Karel builds a column. Before starting the construction,
     * Karel must be on the "floor" and face north.
     * The method is implemented using recursion,
     * which places beepers on new available cells.
     * As a result, the beeper will make a column of the length available to him.
     */
    public void buildColumn() throws Exception {
        if (noBeepersPresent()) {
            putBeeper();
        }
        if (frontIsClear()) {
            move();
            buildColumn();
        }
    }

    /**
     * Karel turns back to the "floor" and faces east.
     * Before starting the execution,
     * Karel must be at the top of the column he has already built.
     * As a result, the character will be on the "floor".
     */
    public void goBack() throws Exception {
        turnAround();
        goForward();
        turnLeft();
    }

    /**
     * Implements the mechanism for building a column.
     * When Karel tries to build a column at a certain location,
     * he turns left, builds it, and turns back.
     */
    public void processColumn() throws Exception {
        turnLeft();
        buildColumn();
        goBack();
    }

    /**
     * Karel moves 4 steps forward, to the next column.
     * Before the start of the performance, Karel must be on the "floor"
     */
    public void goToTheNextColumn() throws Exception {
        for (int i = 0; i < 4; i++) {
            move();
        }
    }
}