package com.shpp.p2p.cs.vmarchenko.assignment10;

/**
 * Represents basic arithmetic operators such as addition, subtraction,
 * multiplication, division, and exponentiation.
 */
public enum Operator10 {
    ADD("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    POWER("^");

    private final String symbol;

    Operator10(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    /**
     * Finds the corresponding Operator enum constant for a given symbol.
     *
     * @param symbol The symbol of the operator
     * @return The matching constant.
     * @throws IllegalArgumentException If the provided symbol does not match any operator.
     */
    public static Operator10 checkSymbol(String symbol) {
        for (Operator10 op : Operator10.values()) {
            if (op.getSymbol().equals(symbol)) {
                return op;
            }
        }
        throw new IllegalArgumentException("Invalid symbol: " + symbol);
    }
}