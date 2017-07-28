package com.codetanklabs.rxjavasample.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codetanklabs.rxjavasample.R;
import com.codetanklabs.rxjavasample.model.GameState;
import com.codetanklabs.rxjavasample.model.Hand;
import com.codetanklabs.rxjavasample.presenter.RPSPresenter;

public class RPSActivity extends AppCompatActivity implements RPSView {

    private ImageView playerImageOne;
    private ImageView playerImageTwo;

    private View winnerPlayerViewGroup;
    private TextView winnerPlayerLabel;

    RPSPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        presenter = new RPSPresenter(this, getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rps);

        playerImageOne = (ImageView) findViewById(R.id.player_one_image);
        playerImageTwo = (ImageView) findViewById(R.id.player_two_image);

        winnerPlayerViewGroup = findViewById(R.id.winnerPlayerViewGroup);
        winnerPlayerLabel = (TextView) findViewById(R.id.winnerPlayerLabel);

        presenter.onCreate();
    }

    public void onPlayerOnClick(View view) {
        AlertDialog.Builder handDialogBuilder = new AlertDialog.Builder(RPSActivity.this);
        handDialogBuilder.setIcon(R.drawable.ic_lizard);
        handDialogBuilder.setTitle("Select Your Hand!");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(RPSActivity.this, android.R.layout.select_dialog_item);
        arrayAdapter.add("ROCK");
        arrayAdapter.add("PAPER");
        arrayAdapter.add("SCISSORS");
        arrayAdapter.add("LIZARD");
        arrayAdapter.add("SPOCK");

        handDialogBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        handDialogBuilder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strName = arrayAdapter.getItem(which);
                Hand playedHand = Hand.valueOf(strName);
                setHandImage(playedHand, playerImageOne);

                presenter.onHandSelected(playedHand);
            }
        });
        handDialogBuilder.show();
    }

    public void setHandImage(Hand hand, ImageView view) {
        switch (hand) {
            case ROCK:
                view.setImageDrawable(getDrawable(R.drawable.ic_rock));
                break;
            case PAPER:
                view.setImageDrawable(getDrawable(R.drawable.ic_paper));
                break;
            case SCISSORS:
                view.setImageDrawable(getDrawable(R.drawable.ic_scisors));
                break;
            case LIZARD:
                view.setImageDrawable(getDrawable(R.drawable.ic_lizard));
                break;
            case SPOCK:
                view.setImageDrawable(getDrawable(R.drawable.ic_spock));
                break;
        }
    }

    @Override
    public void setAndroidHand(Hand androidHand) {
        setHandImage(androidHand, playerImageTwo);
    }

    @Override
    public void showWinner(GameState gameState) {
        String winnerText = getString(R.string.winner_tie);
        switch (gameState) {
            case TIE:
                winnerText = getString(R.string.winner_tie);
                break;
            case WIN:
                winnerText = getString(R.string.winner_winner);
                break;
            case LOSE:
                winnerText = getString(R.string.winner_lost);
                break;
        }
        winnerPlayerLabel.setText(winnerText);
        winnerPlayerViewGroup.setVisibility(View.VISIBLE);

    }

    @Override
    public void clearWinnerDisplay() {

    }

    @Override
    public void clearButtons() {
        playerImageOne.setImageDrawable(getDrawable(R.drawable.ic_unknown));
        playerImageTwo.setImageDrawable(getDrawable(R.drawable.ic_unknown));
    }
}
