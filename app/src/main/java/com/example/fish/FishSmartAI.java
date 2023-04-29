package com.example.fish;

import com.example.GameFramework.infoMessage.GameInfo;
import com.example.GameFramework.players.GameComputerPlayer;

import java.util.ArrayList;
import java.util.Random;

/**
 * class FishSmartAI extends GameComputerPlayer and creates an
 * efficient AI to play against
 *
 * @author Jude Reynolds, Alexis Nguyen, Isaela Timogene-Julien, Heidi Pham
 * @version April 28, 2023
 */
public class FishSmartAI extends GameComputerPlayer {

    /**
     * Constructor for FishSmartAI
     *
     * @param name The name of the player as a String
     */
    public FishSmartAI(String name) {
        super(name);
    }

    /**
     * Initializes the characteristics of a SmartAI
     *
     * @param info The information from the game
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        // Variables
        FishGameState receive = new FishGameState((FishGameState)info);
        int num = receive.getSmartVal();

        // Slows catch for the characteristics of the SmartAI
        try {
            Thread.sleep(1500); // 3 seconds total so the turn isn't too quick
            if(receive.getCurrentPlayer() != this.playerNum) {
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
