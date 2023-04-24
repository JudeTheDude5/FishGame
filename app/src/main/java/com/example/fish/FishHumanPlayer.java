package com.example.fish;

import static android.graphics.Color.RED;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.GameFramework.GameMainActivity;
import com.example.GameFramework.infoMessage.GameInfo;
import com.example.GameFramework.players.GameHumanPlayer;
import com.example.game_test_b.R;

public class FishHumanPlayer extends GameHumanPlayer implements View.OnClickListener {


    private TextView playerHand = null;
    private TextView opponentHand = null;
    private EditText askNum = null;
    private Button asker = null;
    private TextView title = null;

    private GameMainActivity myActivity;

    /**
     * constructor
     *
     * @param name the name of the player
     */
    public FishHumanPlayer(String name) {
        super(name);
    }

    @Override
    public void onClick(View view) {
        int ask = 0;
        for(int i = 1; i < 14; i++) {
            if (askNum.getText().toString().equals(i + "")) {
                ask = i;
            }
        }

        if((ask <= 13) && (ask >= 1)) {
            FishAskAction askAction = new FishAskAction(this,ask);
            game.sendAction(askAction);
        }
        else {
            title.setText("You have to pick a card that's from 1-13");
            return;
        }
    }

    @Override
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }
    //Need to set up view for GUI

    @Override
    public void receiveInfo(GameInfo info) {
        if(!(info instanceof FishGameState)) {
            flash(RED,2);
            return;
        }
        else {
            //playerHand.setText("Player's Hand is: ");
            //opponentHand.setText("Opponent's Hand is ");

            String playerHandText = "Player's Hand is: ";
            String opponentHandText = "Opponent's Hand is ";

            for(int i = 0; i < ((FishGameState) info).getPlayer0Hand().size(); ++i) {
                //playerHand.setText(((FishGameState) info).getPlayer0Hand().get(i) + ", ");
                playerHandText = playerHandText + (((FishGameState) info).getPlayer0Hand().get(i).getValue() + ", ");
            }
            for(int j = 0; j < ((FishGameState) info).getPlayer1Hand().size(); ++j) {
                //opponentHand.setText(((FishGameState) info).getPlayer1Hand().get(j).getValue() + ", ");
                opponentHandText = opponentHandText + (((FishGameState) info).getPlayer1Hand().get(j).getValue() + ", ");
            }
            playerHand.setText(playerHandText);
            opponentHand.setText(opponentHandText);
            title.setText("Go Fish");
            asker.setText("Ask");
            askNum.setText("Enter Number, 1-13");



        }
    }

    @Override
    public void setAsGui(GameMainActivity activity) {
        myActivity = activity;

        activity.setContentView(R.layout.activity_main);

        this.playerHand = (TextView)activity.findViewById(R.id.playerHand);;
        this.opponentHand = (TextView)activity.findViewById(R.id.opponentHand);;
        this.title = (TextView)activity.findViewById(R.id.gameTitle);;
        this.asker = (Button)activity.findViewById(R.id.askForCard);
        this.askNum = (EditText)activity.findViewById(R.id.cardAskNum);

        asker.setOnClickListener(this);
    }
}
