package com.shpp.p2p.cs.vmarchenko.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part1 extends TextProgram {
    public void run(){
        int cardiovascularHealthCounter = 0;
        int bloodPressureCounter = 0;

        int cardiovascularRecommendation = 5;
        int bloodPressureRecommendation = 3;

        for(int i = 1; i <= 7; i++){
            int minutes = askUser(i);

            if (minutes >= 30){
                cardiovascularHealthCounter++;
            }

            if(minutes >= 40){
                bloodPressureCounter++;
            }
        }

        println("Cardiovascular health:");
        if(cardiovascularHealthCounter >= 5){
            println("\tGreat job! You've done enough exercise for cardiovascular health.");
        }else{
            println("\tYou needed to train hard for at least " +
                    (cardiovascularRecommendation - cardiovascularHealthCounter)  +
                    " more day(s) a week!");
        }

        println("Blood pressure:");
        if(bloodPressureCounter >= 3){
            println("\tGreat job! You've done enough exercise to keep a low blood pressure.");
        }else{
            println("\tYou needed to train hard for at least " +
                    (bloodPressureRecommendation - bloodPressureCounter)  +
                    " more day(s) a week!");
        }
    }

    public int askUser(int day){
        while(true){
            try {
                return readInt("How many minutes did you do on day " + day + "? ");
            }catch (Exception e){
                println("Please enter a number.");
                readLine();
            }
        }
    }
}
