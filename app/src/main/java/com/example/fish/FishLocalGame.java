package com.example.fish;

import android.widget.EditText;

import com.example.GameFramework.LocalGame;
import com.example.GameFramework.actionMessage.GameAction;
import com.example.GameFramework.players.GamePlayer;
import com.example.game_test_b.R;

import java.util.ArrayList;
import java.util.Random;

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
            if (gameState.getCurrentPlayer() == 0) {
                if (playerNames.equals("Local Human Player")) {
                    boolean hasCard = false;
                    //ArrayList<FishCard> cardsToRemove = new ArrayList<>();
                    for(int i = 0; i < gameState.getPlayer1Hand().size(); ++i) {
                        if((R.id.cardAskNum) == gameState.getPlayer1Hand().get(i).getValue()) {
                            gameState.player0Hand.add(gameState.getPlayer1Hand().get(i));
                            //cardsToRemove.add(gameState.getPlayer1Hand().get(i));
                            gameState.player1Hand.remove(i);
                            hasCard = true;
                            //int bob = R.id.cardAskNum;
                            //gameState.getCurrAsk()
                            //I don't know whether these will work

                        }
                    }
                    if(hasCard == true) {
                        //for(int i = 0; i < gameState.getPlayer1Hand().size(); ++i) {
                        //}
                        return true;
                    }
                    if(hasCard == false) {
                        Random q = new Random();
                        int draw = q.nextInt(gameState.getDeck().size());
                        gameState.player0Hand.add(gameState.getDeck().get(draw));
                        gameState.deck.remove(draw);
                        gameState.setCurrentPlayer(1);
                        return true;
                    }

                }
                //Computer action
                if (playerNames.equals("Computer Player")) {
                    Random z = new Random();
                    int num = z.nextInt(13);
                    boolean hasCard = false;
                    //ArrayList<FishCard> cardsToRemove = new ArrayList<>();
                    for(int i = 0; i < gameState.getPlayer1Hand().size(); ++i) {
                        if((num +1) == gameState.getPlayer1Hand().get(i).getValue()) {
                            gameState.player0Hand.add(gameState.getPlayer1Hand().get(i));
                            //cardsToRemove.add(gameState.getPlayer1Hand().get(i));
                            gameState.player1Hand.remove(i);
                            hasCard = true;
                        }
                    }
                    if(hasCard == true) {
                        //for(int i = 0; i < gameState.getPlayer1Hand().size(); ++i) {
                        //}
                        return true;
                    }
                    if(hasCard == false) {
                        Random q = new Random();
                        int draw = q.nextInt(gameState.getDeck().size());
                        gameState.player0Hand.add(gameState.getDeck().get(draw));
                        gameState.deck.remove(draw);
                        gameState.setCurrentPlayer(1);
                        return true;
                    }
                }
                if (playerNames.equals("Smart Computer Player")) {
                    //Works Same as DumbAI right now
                    Random z = new Random();
                    int num = z.nextInt(13);
                    boolean hasCard = false;
                    //ArrayList<FishCard> cardsToRemove = new ArrayList<>();
                    for(int i = 0; i < gameState.getPlayer1Hand().size(); ++i) {
                        if((num +1) == gameState.getPlayer1Hand().get(i).getValue()) {
                            gameState.player0Hand.add(gameState.getPlayer1Hand().get(i));
                            //cardsToRemove.add(gameState.getPlayer1Hand().get(i));
                            gameState.player1Hand.remove(i);
                            hasCard = true;
                        }
                    }
                    if(hasCard == true) {
                        //for(int i = 0; i < gameState.getPlayer1Hand().size(); ++i) {
                        //}
                        return true;
                    }
                    if(hasCard == false) {
                        Random q = new Random();
                        int draw = q.nextInt(gameState.getDeck().size());
                        gameState.player0Hand.add(gameState.getDeck().get(draw));
                        gameState.deck.remove(draw);
                        gameState.setCurrentPlayer(1);
                        return true;
                    }
                }
                return false;
            }
            else {
                if (playerNames.equals("Local Human Player")) {
                    boolean hasCard = false;
                    //ArrayList<FishCard> cardsToRemove = new ArrayList<>();
                    for(int i = 0; i < gameState.getPlayer0Hand().size(); ++i) {
                        if((R.id.cardAskNum) == gameState.getPlayer0Hand().get(i).getValue()) {
                            gameState.player1Hand.add(gameState.getPlayer0Hand().get(i));
                            //cardsToRemove.add(gameState.getPlayer1Hand().get(i));
                            gameState.player0Hand.remove(i);
                            hasCard = true;
                            //int bob = R.id.cardAskNum;
                            //gameState.getCurrAsk()
                            //I don't know whether these will work

                        }
                    }
                    if(hasCard == true) {
                        //for(int i = 0; i < gameState.getPlayer1Hand().size(); ++i) {
                        //}
                        return true;
                    }
                    if(hasCard == false) {
                        Random q = new Random();
                        int draw = q.nextInt(gameState.getDeck().size());
                        gameState.player1Hand.add(gameState.getDeck().get(draw));
                        gameState.deck.remove(draw);
                        gameState.setCurrentPlayer(1);
                        return true;
                    }

                }
                //Computer action
                if (playerNames.equals("Computer Player")) {
                    Random z = new Random();
                    int num = z.nextInt(13);
                    boolean hasCard = false;
                    //ArrayList<FishCard> cardsToRemove = new ArrayList<>();
                    for(int i = 0; i < gameState.getPlayer0Hand().size(); ++i) {
                        if((num +1) == gameState.getPlayer0Hand().get(i).getValue()) {
                            gameState.player1Hand.add(gameState.getPlayer0Hand().get(i));
                            //cardsToRemove.add(gameState.getPlayer1Hand().get(i));
                            gameState.player0Hand.remove(i);
                            hasCard = true;
                        }
                    }
                    if(hasCard == true) {
                        //for(int i = 0; i < gameState.getPlayer1Hand().size(); ++i) {
                        //}
                        return true;
                    }
                    if(hasCard == false) {
                        Random q = new Random();
                        int draw = q.nextInt(gameState.getDeck().size());
                        gameState.player1Hand.add(gameState.getDeck().get(draw));
                        gameState.deck.remove(draw);
                        gameState.setCurrentPlayer(1);
                        return true;
                    }
                }
                if (playerNames.equals("Smart Computer Player")) {
                    Random z = new Random();
                    int num = z.nextInt(13);
                    boolean hasCard = false;
                    //ArrayList<FishCard> cardsToRemove = new ArrayList<>();
                    for(int i = 0; i < gameState.getPlayer0Hand().size(); ++i) {
                        if((num +1) == gameState.getPlayer0Hand().get(i).getValue()) {
                            gameState.player1Hand.add(gameState.getPlayer0Hand().get(i));
                            //cardsToRemove.add(gameState.getPlayer1Hand().get(i));
                            gameState.player0Hand.remove(i);
                            hasCard = true;
                        }
                    }
                    if(hasCard == true) {
                        //for(int i = 0; i < gameState.getPlayer1Hand().size(); ++i) {
                        //}
                        return true;
                    }
                    if(hasCard == false) {
                        Random q = new Random();
                        int draw = q.nextInt(gameState.getDeck().size());
                        gameState.player1Hand.add(gameState.getDeck().get(draw));
                        gameState.deck.remove(draw);
                        gameState.setCurrentPlayer(1);
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
