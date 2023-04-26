package com.example.fish;

import com.example.GameFramework.infoMessage.GameInfo;
import com.example.GameFramework.players.GameComputerPlayer;

import java.util.ArrayList;

public class FishSmartAI extends GameComputerPlayer {

    public FishSmartAI(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        FishGameState recieve = new FishGameState((FishGameState)info);

        int num = recieve.getSmartVal();

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
