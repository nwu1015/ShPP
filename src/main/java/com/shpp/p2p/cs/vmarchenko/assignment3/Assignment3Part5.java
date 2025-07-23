package com.shpp.p2p.cs.vmarchenko.assignment3;

import com.shpp.cs.a.console.TextProgram;

import java.util.Random;

/**
 * The fifth task: Bernoulli Casino game.
 * The program implements a casino game with a coin flip.
 * If heads, the bet in the game increases.
 * If tails, then everything on the bet goes to the lucky player.
 */
public class Assignment3Part5 extends TextProgram {
    /**
     * Lucky guy balance
     */
    int luckyGuyBalance = 0;

    /**
     * The number of dollars in the game that the sweaty person puts in.
     */
    int dollarsInGame = 1;

    /**
     * A variable that counts how many games the lucky person had to play.
     */
    int gameCounter = 0;

    /**
     * The main method of application. Allows to test the given program.
     * Generates a random number (similar to heads or tails),
     * calls a method for processing while the lucky person's balance is less than $20.
     */
    public void run() {
        Random rand = new Random();

        while (luckyGuyBalance < 20) {
            // A random number of 1 or 2 is generated.
            int number = rand.nextInt(1, 3);

            continuePlaying(number);
        }

        println("It took " + gameCounter + " games to earn $20");
    }

    /**
     * Implements the basic mechanics of the game.
     * If a 1 (heads) is rolled, the lucky player adds exactly the same amount to
     * the total on the table. If a 2 (tails) is rolled,
     * everything on the table goes to the lucky player.
     *
     * @param number a randomly generated number used for heads or tails.
     */
    public void continuePlaying(int number) {
        if (number % 2 == 0) {
            dollarsInGame += dollarsInGame;
        } else {
            luckyGuyBalance += dollarsInGame;
            println("This game you earned " + dollarsInGame + " dollars");
            println("Your total is " + luckyGuyBalance);
            dollarsInGame = 1;
            gameCounter++;
        }
    }
}