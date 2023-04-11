package com.example.fish;

import com.example.GameFramework.actionMessage.GameAction;
import com.example.GameFramework.players.GamePlayer;

public class FishAskAction extends GameAction {
    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public FishAskAction(GamePlayer player) {
        super(player);
    }
}
