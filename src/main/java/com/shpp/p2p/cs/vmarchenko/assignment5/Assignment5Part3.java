package com.shpp.p2p.cs.vmarchenko.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The third task: Game on the go
 * The program accepts 3 characters from the user
 * and outputs all possible words from those letters.
 */
public class Assignment5Part3 extends TextProgram {
    /**
     * The main method of application. Allows to test the given program.
     * Accepts 3 characters from the user.
     */
    public void run(){
        while(true){
            String input = readLine("Write 3 letters: ");
            showPossibleWords(input);
        }
    }

    /**
     * Checks each word in the dictionary to see if it matches our query.
     *
     * @param letters user-defined letters
     */
    private void showPossibleWords(String letters) {
        // Only accept 3 letters
        if(letters.length() != 3){
            println("Please enter 3 letters");
            return;
        }
        letters = letters.toLowerCase();

        StringBuilder result = new StringBuilder();

        // Read the file sequentially
        try (Scanner scanner = new Scanner(new File("en-dictionary.txt"))) {
            while(scanner.hasNext()){
                String word = scanner.nextLine();

                checkTheWord(letters, word, result);
            }
        }catch(FileNotFoundException e){
            println("File not found");
        }

        // Output of the result
        if(!result.isEmpty()){
            println("Possible words: ");
            println(result.toString());
        }else {
            println("No possible words");
        }
    }

    /**
     * A method for checking a specific word for the presence
     * of certain letters in the correct sequence.
     *
     * @param letters user-defined letters
     * @param word word to check
     * @param result StringBuilder to store the result if this word matches
     */
    private void checkTheWord(String letters, String word, StringBuilder result){
        int index = -1;
        boolean isFound = true;

        for (int i = 0; i < letters.length(); i++) {
            char letter = letters.charAt(i);
            int foundIndex = word.indexOf(letter, index + 1);
            if(foundIndex == -1){
                isFound = false;
                break;
            }
            index = foundIndex;
        }

        if(isFound){
            result.append(word).append("\n");
        }
    }
}