package com.shpp.p2p.cs.vmarchenko.assignment1;

/**
 * The fourth task: Chess board
 * This class implements the following actions:
 * Karel always starts from the south-west corner, looking east.
 * Karel first builds odd rows,
 * then returns to the starting point and completes the even rows,
 * forming a chessboard.
 */
public class Assignment1Part4 extends SuperKarel {
    /**
     * The main method of application. Allows to test the given program.
     * First calls the method to build odd rows, and then,
     * if the size of the card allows, completes the chessboard with even rows
     */
    public void run() throws Exception {
        oddLines();

        // Return to the starting point.
        // After the construction of all lines, Karel was at the north-western point
        turnAround();
        goForward();
        turnAround();

        if (frontIsClear()) {
            // Move on to even rows. At the beginning this is row 2
            move();
            turnRight();

            evenLines();
        }
    }

    /**
     * Karel builds an odd row. At the beginning of the work,
     * Karel should be at the western point of the odd row and facing east.
     */
    public void buildOddLine() throws Exception {
        putBeeper();

        // Skipping one cell
        if (frontIsClear()) {
            move();
        }

        // If Karel can go further, he moves to the next cell and call the same function.
        if (frontIsClear()) {
            move();
            buildOddLine();
        }
    }

    /**
     * Method for building all odd rows.
     * At the beginning of the work, Karel should be at the starting point (southwest).
     * After completing the construction of a row, Karel, if possible,
     * moves to the next odd row and begins to build it.
     */
    public void oddLines() throws Exception {
        buildOddLine();

        // Karel turns back to the western point and looks north.
        turnAround();
        goForward();
        turnRight();

        // Go to the next row.
        if (frontIsClear()) {
            move();

            // If there is a next odd row,
            // then Karel moves to it and starts building the row.
            if (frontIsClear()) {
                move();
                turnRight();
                oddLines();
            }
        }
    }

    /**
     * Karel builds an even row. At the beginning of the work,
     * Karel should be at the western point of the even row and facing east.
     */
    public void buildEvenLine() throws Exception {
        // Put a beeper on the second cell
        if (frontIsClear()) {
            move();
            putBeeper();
        }

        // If Karel can go further, he moves to the next cell and call the same function.
        if (frontIsClear()) {
            move();
            buildEvenLine();
        }
    }

    /**
     * Method for building all even rows.
     * At the beginning of the work, Karel should be on the second row, at the western point.
     * After completing the construction of a row, Karel, if possible,
     * moves to the next even row and begins to build it.
     */
    public void evenLines() throws Exception {
        buildEvenLine();

        // // Karel turns back to the western point and looks north.
        turnAround();
        goForward();
        turnRight();

        // Go to the next row.
        if (frontIsClear()) {
            move();

            // If there is a next even row,
            // then Karel moves to it and starts building the row.
            if (frontIsClear()) {
                move();
                turnRight();
                evenLines();
            }
        }
    }
}