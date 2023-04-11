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

        try {
            Thread.sleep(1000);
            if(recieve.getCurrentPlayer() != this.playerNum) {
                return;
            }
            else {
                FishAskAction play = new FishAskAction(this);
                game.sendAction(play);
            }

        }
        catch (Exception e) {

        }
    }


}
