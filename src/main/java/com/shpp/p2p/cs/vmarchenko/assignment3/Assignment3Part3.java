package com.shpp.p2p.cs.vmarchenko.assignment3;

import com.shpp.cs.a.console.TextProgram;

public class Assignment3Part3 extends TextProgram {
    public void run(){
        println(raiseToPower(4, -2));

    }

    private double raiseToPower(double base, int exponent) {
        if (exponent == 0) return 1;

        if (base == 0) return 0;

        if (exponent < 0){
            base = 1 / base;
            exponent = -exponent;
        }

        double result = 1;
        for (int i = 1; i <= exponent; i++) {
            result *= base;
        }
        return result;
    }
}
