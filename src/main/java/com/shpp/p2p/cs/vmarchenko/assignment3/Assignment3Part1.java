package com.shpp.p2p.cs.vmarchenko.assignment3;

import com.shpp.cs.a.console.TextProgram;

/**
 * The first task: Aerobics
 * The program asks the user how many minutes of exercise they have done per day.
 * It then displays information about whether they have exercised enough per week
 * to maintain their cardiovascular health and lower their blood pressure.
 */
public class Assignment3Part1 extends TextProgram {
    /**
     * Days counter to maintain cardiovascular health
     */
    int cardiovascularHealthCounter = 0;

    /**
     * Days counter to maintain low blood pressure
     */
    int bloodPressureCounter = 0;

    /**
     * Recommendation for how many days you need to exercise
     * to maintain cardiovascular health
     */
    int cardiovascularRecommendation = 5;

    /**
     * Recommendation for how many days you need to exercise
     * to maintain low blood pressure
     */
    int bloodPressureRecommendation = 3;

    /**
     * The main method of application. Allows to test the given program.
     * Calls two methods. The first of which checks a person's activity during the week.
     * The second method outputs the result of whether enough time was spent to maintain health.
     */
    public void run() {
        checkActivity();
        printInfo();
    }

    /**
     * Asks the user how much time they spent training on a given day.
     *
     * @param day a variable that displays the day number.
     * @return number of minutes spent training that day.
     */
    public int askUser(int day) {
        while (true) {
            try {
                int minutesPerDay = readInt("How many minutes did you do on day " + day + "? ");
                if (minutesPerDay < 0) {
                    println("Please enter a number greater than 0.");
                } else {
                    return minutesPerDay;
                }
            } catch (Exception e) {
                println("Please enter a number.");
                readLine();
            }
        }
    }

    /**
     * Checks a person's activity over the week.
     * Calls a method to input how much time the person spent exercising.
     * If the person exercised for more than 30 minutes that day,
     * a counter is incremented that is responsible for maintaining cardiovascular health.
     * If more than 40 minutes were spent, a counter is incremented that is responsible for
     * maintaining low blood pressure.
     */
    public void checkActivity() {
        for (int i = 1; i <= 7; i++) {
            int minutes = askUser(i);

            if (minutes >= 30) {
                cardiovascularHealthCounter++;
            }

            if (minutes >= 40) {
                bloodPressureCounter++;
            }
        }
    }

    /**
     * Displays information about whether enough time was spent on training.
     * If the number of training sessions for maintaining cardiovascular health or
     * low blood pressure equals or exceeds the recommendations,
     * it will display a positive result on the console.
     * If the training sessions were not enough, it will display a corresponding message
     * with the number of more training sessions needed.
     */
    public void printInfo() {
        println("Cardiovascular health:");
        if (cardiovascularHealthCounter >= 5) {
            println("\tGreat job! You've done enough exercise for cardiovascular health.");
        } else {
            println("\tYou needed to train hard for at least " +
                    (cardiovascularRecommendation - cardiovascularHealthCounter) +
                    " more day(s) a week!");
        }

        println("Blood pressure:");
        if (bloodPressureCounter >= 3) {
            println("\tGreat job! You've done enough exercise to keep a low blood pressure.");
        } else {
            println("\tYou needed to train hard for at least " +
                    (bloodPressureRecommendation - bloodPressureCounter) +
                    " more day(s) a week!");
        }
    }
}