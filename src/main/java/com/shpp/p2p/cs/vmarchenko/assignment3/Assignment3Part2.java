package com.shpp.p2p.cs.vmarchenko.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part2 extends TextProgram {
    public void run(){
        int number = askNumber();
        while(number != 1){
            if(number % 2 == 0){
                println(number + " is even so I take half: " + number / 2);
                number = number / 2;
            }else {
                println(number + " is odd so I make 3n + 1: " + (3 * number) + 1);
                number = (3 * number) + 1;
            }

            if(number == 1){
                println("END");
                break;
            }
        }
    }

    private int askNumber() {
        while (true) {
            try {
                return readInt("Enter a number: ");
            }catch (Exception e){
                println("Please enter a number.");
                readLine();
            }
        }
    }
}
