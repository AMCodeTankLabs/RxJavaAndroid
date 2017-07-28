package com.codetanklabs.rxjavasample.model;

public enum GameState {
    TIE(0),
    WIN(1),
    LOSE(2);

    private int numVal;

    GameState(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}



