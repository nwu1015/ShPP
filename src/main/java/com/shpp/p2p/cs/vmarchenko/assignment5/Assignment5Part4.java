package com.shpp.p2p.cs.vmarchenko.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Assignment5Part4 extends TextProgram {
    public void run(){
        ArrayList<String> result = extractColumn("Task5Part4.csv", 1);

        println(result);
    }

    private ArrayList<String> extractColumn(String filename, int columnIndex){
        ArrayList<String> row;
        ArrayList<String> result = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                row = fieldsIn(scanner.nextLine());
                result.add(row.get(columnIndex));

            }
        }catch (Exception e){
            return null;
        }
        return result;
    }

    private ArrayList<String> fieldsIn(String line) {
        ArrayList<String> result = new ArrayList<>();

        if(!line.contains("\"")){
            String[] fields = line.split(",");
            for (String field : fields) {
                result.add(field.trim());
            }
        }else {
            for(int i = 0; i < line.length(); i++){
                // ЦЕ ВСЕ НИЖЧЕ ТОЧНО ТРЕБА
                String testLine = "";

                int firstQuote = line.indexOf("\"");
                int lastQuote = line.indexOf("\"", firstQuote + 1);

                for(int j = firstQuote + 1; j < lastQuote; j++){
                    testLine = line.substring(firstQuote + 1, lastQuote);
                }
                println(testLine);
                result.add(testLine);
            }
        }

        return result;
    }
}