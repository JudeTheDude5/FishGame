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
        FishAskAction ask = new FishAskAction(this);
        game.sendAction(ask);
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
            playerHand.setText("Player's Hand is: ");
            opponentHand.setText("Opponent's Hand is ");
            for(int i = 0; i < ((FishGameState) info).getPlayer0Hand().size(); ++i) {
                playerHand.setText(((FishGameState) info).getPlayer0Hand().get(i) + ", ");
            }
            for(int j = 0; j < ((FishGameState) info).getPlayer1Hand().size(); ++j) {
                opponentHand.setText(((FishGameState) info).getPlayer1Hand().get(j) + ", ");
            }
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
