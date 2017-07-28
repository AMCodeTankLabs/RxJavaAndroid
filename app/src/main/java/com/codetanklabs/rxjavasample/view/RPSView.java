package com.codetanklabs.rxjavasample.view;

import com.codetanklabs.rxjavasample.model.GameState;
import com.codetanklabs.rxjavasample.model.Hand;

public interface RPSView {

    void showWinner(GameState gameState);
    void clearWinnerDisplay();
    void clearButtons();
    void setAndroidHand(Hand hand);

}
