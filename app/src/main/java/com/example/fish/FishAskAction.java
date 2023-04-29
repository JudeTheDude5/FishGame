package com.example.fish;

import com.example.GameFramework.actionMessage.GameAction;
import com.example.GameFramework.players.GamePlayer;

/**
 * Class FishAskAction controls which card is asked for
 *
 * @author Jude Reynolds, Alexis Nguyen, Isaela Timogene-Julien, Heidi Pham
 * @version April 28, 2023
 */
public class FishAskAction extends GameAction {

    /**
     * Constructor for GameAction
     *
     * @param player The player who created the action
     */
    public int askNum;
    //Ask actions wants certain number so it knows what to ask for
    public FishAskAction(GamePlayer player, int g) {

        super(player);
        askNum = g;
    }
}
