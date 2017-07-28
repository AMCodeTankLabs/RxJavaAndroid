package com.codetanklabs.rxjavasample.model;

public enum Hand {
    PAPER(0),
    ROCK(1),
    LIZARD(2),
    SPOCK(3),
    SCISSORS(4);

    private int numVal;

    Hand(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}