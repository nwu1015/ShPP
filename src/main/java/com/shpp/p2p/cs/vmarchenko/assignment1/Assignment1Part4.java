package com.shpp.p2p.cs.vmarchenko.assignment1;

/**
 * The fourth task: Chess board
 * This class implements the following actions:
 * Karel always starts from the south-west corner, looking east.
 * Karel build first row,
 * then returns to the starting point and completes the other rows,
 * forming a chessboard.
 */
public class Assignment1Part4 extends SuperKarel {
    /**
     * The main method of application. Allows to test the given program.
     * Calls a method to build a chessboard.
     */
    public void run() throws Exception {
        buildChessBoard();
    }

    /**
     * Method for building a chessboard.
     * At the beginning of the work, Karel should be at the starting point (southwest).
     * After completing the construction of a row, Karel, if possible,
     * moves to the next row and begins to build it.
     */
    public void buildChessBoard() throws Exception {
        // build odd row
        buildLine();
        goBack();

        // Go to the next row.
        if (frontIsClear()) {
            move();
            turnRight();

            // Builds the second (even) row
            if (frontIsClear()) {
                move();
                buildLine();
            }

            goBack();

            // If there is a next odd row,
            // then Karel moves to it and starts building the row.
            if (frontIsClear()) {
                move();
                turnRight();
                buildChessBoard();
            }
        }
    }

    /**
     * After building one line Karel turns back to the western point and looks north.
     */
    public void goBack() throws Exception {
        turnAround();
        goForward();
        turnRight();
    }

    /**
     * Karel builds a row. At the beginning of the work,
     * Karel should be at the western point of the row and facing east.
     */
    public void buildLine() throws Exception {
        putBeeper();

        // Skipping one cell
        if (frontIsClear()) {
            move();
        }

        // If Karel can go further, he moves to the next cell and call the same function.
        if (frontIsClear()) {
            move();
            buildLine();
        }
    }
}