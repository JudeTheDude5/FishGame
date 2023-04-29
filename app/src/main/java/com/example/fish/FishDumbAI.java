package com.example.fish;

import com.example.GameFramework.players.GameComputerPlayer;
import com.example.GameFramework.infoMessage.GameInfo;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class FishDumbAI extends GameComputerPlayer and creates a less
 * efficient AI to play against
 *
 * @author Jude Reynolds, Alexis Nguyen, Isaela Timogene-Julien, Heidi Pham
 * @version April 28, 2023
 */
public class FishDumbAI extends GameComputerPlayer {

    /**
     * Constructor for FishDumbAI
     *
     * @param name The name of the payer as a String
     */
    public FishDumbAI(String name) {
        super(name);
    }

    /**
     * Initializes the characteristics of a DumbAI
     *
     * @param info The information from the game
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        // Variables
        FishGameState recieve = new FishGameState((FishGameState)info);
        int num;
        //Dumb AI Will randomly choose a card from it's desk to ask for

        // Initializes the characteristics of the DumbAI
        if (recieve.getPlayer0Hand().size() == 0) {
            num = -1;
        }
        else {
            Random z = new Random();
            ArrayList<Integer> intValues = new ArrayList<Integer>();
            int rand = z.nextInt(recieve.getPlayer1Hand().size());
            for (FishCard card : recieve.getPlayer1Hand()) {
                intValues.add(card.getValue());
            }
            num = intValues.get(rand);
        }

        // Slows the AI turn
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
