package com.codetanklabs.rxjavasample.presenter;

import android.content.Context;
import android.util.Log;

import com.codetanklabs.rxjavasample.model.Game;
import com.codetanklabs.rxjavasample.model.GameState;
import com.codetanklabs.rxjavasample.model.Hand;
import com.codetanklabs.rxjavasample.model.NetworkHand;
import com.codetanklabs.rxjavasample.network.HandService;
import com.codetanklabs.rxjavasample.view.RPSView;

import java.util.Random;

public class RPSPresenter implements Presenter {

    private RPSView view;
    private Context context;
    private Game model;
    private static final Random randomHand = new Random();

    public RPSPresenter(RPSView view, Context ctx) {
        this.view = view;
        this.context = ctx;
        this.model = new Game();
    }

    @Override
    public void onCreate() {
        model = new Game();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    public void onHandSelected(final Hand playerHand) {
        model.setHand(playerHand);

        HandService service = new HandService(context);
        service.getNetworkHand(new HandService.HandCallback() {
            @Override
            public void onSuccess(NetworkHand hand) {
                //Hand androidHand = Hand.values()[randomHand.nextInt(Hand.values().length)];
                Hand androidHand = Hand.values()[hand.androidHand];
                view.setAndroidHand(androidHand);

                GameState state = model.getWinner(playerHand, androidHand);
                view.showWinner(state);
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                Log.i("ERROR", "ERR");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void onResetSelected() {
        view.clearWinnerDisplay();
        view.clearButtons();
        model.restart();
    }


}
