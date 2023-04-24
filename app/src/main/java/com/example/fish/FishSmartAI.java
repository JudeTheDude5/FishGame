package com.example.fish;

import com.example.GameFramework.infoMessage.GameInfo;
import com.example.GameFramework.players.GameComputerPlayer;

import java.util.Random;

public class FishSmartAI extends GameComputerPlayer {

    public FishSmartAI(String name) {
        super(name);
    }
    @Override
    protected void receiveInfo(GameInfo info) {
        FishGameState recieve = new FishGameState((FishGameState)info);
        Random z = new Random();
        int num = z.nextInt(13) + 1;

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
