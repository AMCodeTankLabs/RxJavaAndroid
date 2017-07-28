package com.codetanklabs.rxjavasample.model;

public class Game {

    private Player winner;
    private GameState state;
    private Player currentTurn;

    public Game() {
        restart();
    }

    public void restart() {
        winner = null;
    }

    public Player setHand(Hand hand) {
        return null;
    }

    public GameState getWinner(Hand playerHand, Hand androidHand) {

        int index1 = playerHand.getNumVal();
        int index2 = androidHand.getNumVal();
        int dif = index2 - index1;
        if (dif < 0) {
            dif += Hand.values().length;
        }
        while (dif > 2) {
            dif -= 2;
        }

        GameState state = GameState.values()[dif];

        return state;
    }

}
