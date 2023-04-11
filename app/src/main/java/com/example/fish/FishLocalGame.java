package com.example.fish;

import com.example.GameFramework.LocalGame;
import com.example.GameFramework.actionMessage.GameAction;
import com.example.GameFramework.players.GamePlayer;

public class FishLocalGame extends LocalGame {

    private FishGameState gameState;


    public FishLocalGame() {
        gameState = new FishGameState();
    }
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {

        FishGameState copy = new FishGameState(this.gameState);
        p.sendInfo(copy);
    }

    @Override
    protected boolean canMove(int playerIdx) {
        if(gameState.getCurrentPlayer() == playerIdx) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    protected String checkIfGameOver() {

        String Win = null;

        if((gameState.getPlayer0Score() + gameState.getPlayer1Score() >= 13)) {
            if(gameState.getPlayer0Score() > gameState.getPlayer1Score()) {
                Win = "Player 0 won with " + gameState.getPlayer0Score() + " Sets";
            }
            else {
                Win = "Player 1 won with " + gameState.getPlayer1Score() + " Sets";
            }
        }

        return Win;
    }

    @Override
    protected boolean makeMove(GameAction action) {
        if(action instanceof FishAskAction) {
            if(gameState.getCurrentPlayer() == 0) {
                //Player action, asking for the card
                return true;
            }
            else {
               //Computer action
                if(playerNames.equals("Computer Player")) {

                    return true;
                }
                if(playerNames.equals("Smart Computer Player")) {

                    return true;
                }
            }
        }

        return false;
    }
}
