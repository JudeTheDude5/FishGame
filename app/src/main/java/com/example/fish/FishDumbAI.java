package com.example.fish;

import com.example.GameFramework.players.GameComputerPlayer;
import com.example.GameFramework.infoMessage.GameInfo;

import java.util.ArrayList;
import java.util.Random;

public class FishDumbAI extends GameComputerPlayer {

    public FishDumbAI(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        FishGameState recieve = new FishGameState((FishGameState)info);
        int num;

        if (recieve.getPlayer0Hand().size() == 0) {
            num = -1;
        } else {
            Random z = new Random();
            ArrayList<Integer> intValues = new ArrayList<Integer>();
            int rand = z.nextInt(recieve.getPlayer1Hand().size());
            for (FishCard card : recieve.getPlayer1Hand()) {
                intValues.add(card.getValue());
            }
            num = intValues.get(rand);
        }

        try {
            Thread.sleep(2000); // 3 seconds total so the turn isn't too quick
            if(recieve.getCurrentPlayer() != this.playerNum) {
                return;
            }
            else {
                FishAskAction play = new FishAskAction(this, num);
                game.sendAction(play);
            }
            Thread.sleep(1000);
        }
        catch (Exception e) {

        }
    }


}
