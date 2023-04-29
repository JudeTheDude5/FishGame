package com.example.fish;

import android.widget.EditText;

import com.example.GameFramework.LocalGame;
import com.example.GameFramework.actionMessage.GameAction;
import com.example.GameFramework.players.GamePlayer;
import com.example.game_test_b.R;

import java.util.ArrayList;
import java.util.Random;

public class FishLocalGame extends LocalGame {

    //private FishGameState gameState;

    public FishLocalGame() {
        super();
        //gameState = new FishGameState();
        super.state = new FishGameState();
    }
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {

        FishGameState copy = new FishGameState((FishGameState)(super.state));
        p.sendInfo(copy);
    }

    @Override
    protected boolean canMove(int playerIdx) {
        FishGameState gameState1 = (FishGameState) super.state;
        if(gameState1.getCurrentPlayer() == playerIdx) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    protected String checkIfGameOver() {
        FishGameState gameState2 = (FishGameState) super.state;
        String Win = null;

        if((gameState2.getPlayer0Score() + gameState2.getPlayer1Score() >= 13)) {
            if(gameState2.getPlayer0Score() > gameState2.getPlayer1Score()) {
                Win = "Player 0 won with " + gameState2.getPlayer0Score() + " Sets ";
            }
            else {
                Win = "Player 1 won with " + gameState2.getPlayer1Score() + " Sets ";
            }
        }

        return Win;
    }

    @Override
    protected boolean makeMove(GameAction action) {
        FishGameState gameState3 = (FishGameState) super.state;
        if(action instanceof FishAskAction) {
            if (gameState3.getCurrentPlayer() == 0) {
                    boolean hasCard = false;

                    //updates the priority and doNotAsk arrays with each ask
                    if (!(gameState3.getPriority().contains(((FishAskAction) action).askNum))) {
                        gameState3.getPriority().add(((FishAskAction) action).askNum);
                    }
                    if (gameState3.getDoNotAsk().contains(((FishAskAction) action).askNum)) {
                        Integer integerToRemove = ((FishAskAction) action).askNum;
                        gameState3.getDoNotAsk().remove(integerToRemove);
                    }

                    //ArrayList<FishCard> cardsToRemove = new ArrayList<>();
                    for(int i = gameState3.getPlayer1Hand().size()-1; i >= 0; --i) {
                        if((((FishAskAction) action).askNum) == gameState3.getPlayer1Hand().get(i).getValue()) {
                            gameState3.player0Hand.add(gameState3.getPlayer1Hand().get(i));
                            //cardsToRemove.add(gameState.getPlayer1Hand().get(i));
                            gameState3.player1Hand.remove(i);
                            hasCard = true;
                            //int bob = R.id.cardAskNum;
                            //gameState.getCurrAsk()
                            //I don't know whether these will work
                            gameState3.checkForFour();
                        }
                    }
                    if (gameState3.getPlayer1Hand().size() == 0) {
                        System.out.println("Computer drawing 5 cards");
                        gameState3.drawFive(1);
                        gameState3.setCurrentPlayer(1);
                    }

                    System.out.println("User asked for a " + ((FishAskAction) action).askNum);

                    if(hasCard == true) {
                        //for(int i = 0; i < gameState.getPlayer1Hand().size(); ++i) {
                        //}
                        return true;
                    }
                    if(hasCard == false) {
                        Random q = new Random();
                        int draw = q.nextInt(gameState3.getDeck().size());
                        gameState3.player0Hand.add(gameState3.getDeck().get(draw));
                        gameState3.deck.remove(draw);
                        gameState3.checkForFour();
                        gameState3.setCurrentPlayer(1);
                        return true;
                    }
                return false;
            }
            else {
                    boolean hasCard = false;

                    //updates the priority and doNotAsk arrays with each ask
                    if (!(gameState3.getDoNotAsk().contains(((FishAskAction) action).askNum))) {
                        gameState3.getDoNotAsk().add(((FishAskAction) action).askNum);
                    }

                    //ArrayList<FishCard> cardsToRemove = new ArrayList<>();
                    for(int i = gameState3.getPlayer0Hand().size()-1; i >= 0; --i) {
                        if((((FishAskAction) action).askNum) == gameState3.getPlayer0Hand().get(i).getValue()) {
                            gameState3.player1Hand.add(gameState3.getPlayer0Hand().get(i));
                            //cardsToRemove.add(gameState.getPlayer1Hand().get(i));
                            gameState3.player0Hand.remove(i);
                            hasCard = true;
                            //int bob = R.id.cardAskNum;
                            //gameState.getCurrAsk()
                            //I don't know whether these will work
                            gameState3.checkForFour();
                        }
                    }
                    if (gameState3.getPlayer0Hand().size() == 0) {
                        System.out.println("User drawing 5 cards");
                        gameState3.drawFive(0);
                        gameState3.setCurrentPlayer(0);
                    }

                    System.out.println("Computer asked for a " + ((FishAskAction) action).askNum);
                    
                    if(hasCard == true) {
                        //for(int i = 0; i < gameState.getPlayer1Hand().size(); ++i) {
                        //}
                        return true;
                    }
                    if(hasCard == false) {
                        Random q = new Random();
                        int draw = q.nextInt(gameState3.getDeck().size());
                        gameState3.player1Hand.add(gameState3.getDeck().get(draw));
                        gameState3.deck.remove(draw);
                        gameState3.checkForFour();
                        gameState3.setCurrentPlayer(0);
                        return true;
                    }
                    return false;
            }
        }

        return false;
    }
}
