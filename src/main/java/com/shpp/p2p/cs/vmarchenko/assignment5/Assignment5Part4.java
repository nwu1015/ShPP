package com.shpp.p2p.cs.vmarchenko.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The fourth task: CSV parsing
 * The program reads the file, column number, and outputs the corresponding content of these columns.
 */
public class Assignment5Part4 extends TextProgram {
    /**
     * The main method of application. Allows to test the given program.
     * Calls a method to process a file, outputs the result.
     */
    public void run(){
        ArrayList<String> result = extractColumn("Task5Part4.csv", 2);
        println(result);
    }

    /**
     * Reads the file sequentially and calls a method to write the content
     * from the corresponding column. If the file does not exist, returns null.
     *
     * @param filename the name of the specified file
     * @param columnIndex column number in the file
     * @return data from the corresponding column.
     */
    private ArrayList<String> extractColumn(String filename, int columnIndex){
        ArrayList<String> row;
        ArrayList<String> result = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                row = fieldsIn(scanner.nextLine());
                result.add(row.get(columnIndex));
            }
        }catch (FileNotFoundException e){
            return null;
        }
        return result;
    }

    /**
     * Method for converting a read string (its content) into a dynamic array.
     *
     * @param line read line
     * @return an array of data that was taken from a string
     */
    private ArrayList<String> fieldsIn(String line) {
        ArrayList<String> result = new ArrayList<>();
        StringBuilder word = new StringBuilder();
        boolean isQuoteBefore = false;

        /*
        I found this approach. I reworked my solution because it did not
        handle the situation when there were still
        quotes inside the quotes. I used the search for the nearest neighboring quotes
        and because of this the program worked incorrectly in such cases.
         */
        for (int i = 0; i < line.length(); i++) {
            char symbol = line.charAt(i);

            if (symbol == '"') {
                isQuoteBefore = !isQuoteBefore;
                if (i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    word.append('"');
                    i++;
                }
            } else if (symbol == ',' && !isQuoteBefore) {
                result.add(word.toString());
                word = new StringBuilder();
            } else {
                word.append(symbol);
            }
        }

        result.add(word.toString());

        return result;
    }
}