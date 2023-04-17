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
                Win = "Player 0 won with " + gameState2.getPlayer0Score() + " Sets";
            }
            else {
                Win = "Player 1 won with " + gameState2.getPlayer1Score() + " Sets";
            }
        }

        return Win;
    }

    @Override
    protected boolean makeMove(GameAction action) {
        FishGameState gameState3 = (FishGameState) super.state;
        if(action instanceof FishAskAction) {
            if (gameState3.getCurrentPlayer() == 0) {
                if (playerNames.equals("Local Human Player")) {
                    boolean hasCard = false;
                    //ArrayList<FishCard> cardsToRemove = new ArrayList<>();
                    for(int i = 0; i < gameState3.getPlayer1Hand().size(); ++i) {
                        if((R.id.cardAskNum) == gameState3.getPlayer1Hand().get(i).getValue()) {
                            gameState3.player0Hand.add(gameState3.getPlayer1Hand().get(i));
                            //cardsToRemove.add(gameState.getPlayer1Hand().get(i));
                            gameState3.player1Hand.remove(i);
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
                        int draw = q.nextInt(gameState3.getDeck().size());
                        gameState3.player0Hand.add(gameState3.getDeck().get(draw));
                        gameState3.deck.remove(draw);
                        gameState3.setCurrentPlayer(1);
                        return true;
                    }

                }
                //Computer action
                if (playerNames.equals("Computer Player")) {
                    Random z = new Random();
                    int num = z.nextInt(13);
                    boolean hasCard = false;
                    //ArrayList<FishCard> cardsToRemove = new ArrayList<>();
                    for(int i = 0; i < gameState3.getPlayer1Hand().size(); ++i) {
                        if((num +1) == gameState3.getPlayer1Hand().get(i).getValue()) {
                            gameState3.player0Hand.add(gameState3.getPlayer1Hand().get(i));
                            //cardsToRemove.add(gameState.getPlayer1Hand().get(i));
                            gameState3.player1Hand.remove(i);
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
                        int draw = q.nextInt(gameState3.getDeck().size());
                        gameState3.player0Hand.add(gameState3.getDeck().get(draw));
                        gameState3.deck.remove(draw);
                        gameState3.setCurrentPlayer(1);
                        return true;
                    }
                }
                if (playerNames.equals("Smart Computer Player")) {
                    //Works Same as DumbAI right now
                    Random z = new Random();
                    int num = z.nextInt(13);
                    boolean hasCard = false;
                    //ArrayList<FishCard> cardsToRemove = new ArrayList<>();
                    for(int i = 0; i < gameState3.getPlayer1Hand().size(); ++i) {
                        if((num +1) == gameState3.getPlayer1Hand().get(i).getValue()) {
                            gameState3.player0Hand.add(gameState3.getPlayer1Hand().get(i));
                            //cardsToRemove.add(gameState.getPlayer1Hand().get(i));
                            gameState3.player1Hand.remove(i);
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
                        int draw = q.nextInt(gameState3.getDeck().size());
                        gameState3.player0Hand.add(gameState3.getDeck().get(draw));
                        gameState3.deck.remove(draw);
                        gameState3.setCurrentPlayer(1);
                        return true;
                    }
                }
                return false;
            }
            else {
                if (playerNames.equals("Local Human Player")) {
                    boolean hasCard = false;
                    //ArrayList<FishCard> cardsToRemove = new ArrayList<>();
                    for(int i = 0; i < gameState3.getPlayer0Hand().size(); ++i) {
                        if((R.id.cardAskNum) == gameState3.getPlayer0Hand().get(i).getValue()) {
                            gameState3.player1Hand.add(gameState3.getPlayer0Hand().get(i));
                            //cardsToRemove.add(gameState.getPlayer1Hand().get(i));
                            gameState3.player0Hand.remove(i);
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
                        int draw = q.nextInt(gameState3.getDeck().size());
                        gameState3.player1Hand.add(gameState3.getDeck().get(draw));
                        gameState3.deck.remove(draw);
                        gameState3.setCurrentPlayer(1);
                        return true;
                    }

                }
                //Computer action
                if (playerNames.equals("Computer Player")) {
                    Random z = new Random();
                    int num = z.nextInt(13);
                    boolean hasCard = false;
                    //ArrayList<FishCard> cardsToRemove = new ArrayList<>();
                    for(int i = 0; i < gameState3.getPlayer0Hand().size(); ++i) {
                        if((num +1) == gameState3.getPlayer0Hand().get(i).getValue()) {
                            gameState3.player1Hand.add(gameState3.getPlayer0Hand().get(i));
                            //cardsToRemove.add(gameState.getPlayer1Hand().get(i));
                            gameState3.player0Hand.remove(i);
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
                        int draw = q.nextInt(gameState3.getDeck().size());
                        gameState3.player1Hand.add(gameState3.getDeck().get(draw));
                        gameState3.deck.remove(draw);
                        gameState3.setCurrentPlayer(1);
                        return true;
                    }
                }
                if (playerNames.equals("Smart Computer Player")) {
                    Random z = new Random();
                    int num = z.nextInt(13);
                    boolean hasCard = false;
                    //ArrayList<FishCard> cardsToRemove = new ArrayList<>();
                    for(int i = 0; i < gameState3.getPlayer0Hand().size(); ++i) {
                        if((num +1) == gameState3.getPlayer0Hand().get(i).getValue()) {
                            gameState3.player1Hand.add(gameState3.getPlayer0Hand().get(i));
                            //cardsToRemove.add(gameState.getPlayer1Hand().get(i));
                            gameState3.player0Hand.remove(i);
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
                        int draw = q.nextInt(gameState3.getDeck().size());
                        gameState3.player1Hand.add(gameState3.getDeck().get(draw));
                        gameState3.deck.remove(draw);
                        gameState3.setCurrentPlayer(1);
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
