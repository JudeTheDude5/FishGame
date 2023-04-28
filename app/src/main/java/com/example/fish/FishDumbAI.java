package com.example.fish;

import com.example.GameFramework.players.GameComputerPlayer;
import com.example.GameFramework.infoMessage.GameInfo;

import java.util.Random;

public class FishDumbAI extends GameComputerPlayer {

    public FishDumbAI(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        FishGameState recieve = new FishGameState((FishGameState)info);
        Random z = new Random();
        int num = 1;
        int rand = 0;
        if(this.playerNum == 0) {
            rand = z.nextInt(recieve.player0Hand.size());
            num = recieve.player0Hand.get(rand).getValue();
        }
        else if(this.playerNum == 1) {
            rand = z.nextInt(recieve.player1Hand.size());
            num = recieve.player1Hand.get(rand).getValue();
        }

        try {
            Thread.sleep(1000);
            if(recieve.getCurrentPlayer() != this.playerNum) {
                return;
            }
            else {
                FishAskAction play = new FishAskAction(this, num);
                game.sendAction(play);
            }

        }
        catch (Exception e) {

        }
    }


}
