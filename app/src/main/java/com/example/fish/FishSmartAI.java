package com.example.fish;

import com.example.GameFramework.infoMessage.GameInfo;
import com.example.GameFramework.players.GameComputerPlayer;

import java.util.ArrayList;
import java.util.Random;

public class FishSmartAI extends GameComputerPlayer {

    public FishSmartAI(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        FishGameState recieve = new FishGameState((FishGameState)info);

        int num = recieve.getSmartVal();
        //Uses number formula in FishGamestate to make smarter ask choices

        try {
            Thread.sleep(1500); // 3 seconds total so the turn isn't too quick
            if(recieve.getCurrentPlayer() != this.playerNum) {
                return;
            }
            else {
                FishAskAction play = new FishAskAction(this, num);
                game.sendAction(play);
            }
            Thread.sleep(1500);

        }
        catch (Exception e) {

        }
    }

}
