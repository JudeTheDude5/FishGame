package com.example.fish;

import com.example.GameFramework.GameMainActivity;
import com.example.GameFramework.LocalGame;
import com.example.GameFramework.actionMessage.GameAction;
import com.example.GameFramework.players.GamePlayer;
import java.util.Random;

/**
 * class FishLocalGame extends LocalGame and creates a game that is played
 * on the local network
 *
 * @author Jude Reynolds, Alexis Nguyen, Isaela Timogene-Julien, Heidi Pham
 * @version April 28, 2023
 */

public class FishLocalGame extends LocalGame {

    /**
     * Constructor for FishLocalGame
     */
    public FishLocalGame() {
        super();
        super.state = new FishGameState();
    }

    /**
     * Sends an updates state to the player
     *
     * @param p The GamePlayer object
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        // Copies the state and sends it to the player
        FishGameState copy = new FishGameState((FishGameState)(super.state));
        p.sendInfo(copy);
    }

    /**
     * Allows the player to ask for a card (make a move)
     *
     * @param playerIdx The index of the player
     */
    @Override
    protected boolean canMove(int playerIdx) {
        FishGameState gameState1 = (FishGameState) super.state;

        // If it is the correct players turn, allow them to ask
        if(gameState1.getCurrentPlayer() == playerIdx) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Checks if the game has ended or not
     *
     * @return A String stating who won the game
     */
    @Override
    protected String checkIfGameOver() {
        // Variables
        FishGameState gameState2 = (FishGameState) super.state;
        String Win = null;

        // Checks both hand's score equal 13
        if((gameState2.getPlayer0Score() + gameState2.getPlayer1Score() >= 13)) {
            if(gameState2.getPlayer0Score() > gameState2.getPlayer1Score()) {
                Win = "You won with " + gameState2.getPlayer0Score() + " Sets. ";
            }
            else {
                Win = "Computer won with " + gameState2.getPlayer1Score() + " Sets. ";
            }
        }

        return Win;
    }

    /**
     * Method that allows a player to make a move
     *
     * @param action A GameAction object
     */
    @Override
    protected boolean makeMove(GameAction action) {
        // Variables
        FishGameState gameState3 = (FishGameState) super.state;

        // If the action is of FishAskAction
        if(action instanceof FishAskAction) {
            if (gameState3.getCurrentPlayer() == 0) {
                    boolean hasCard = false;

                    // Updates the priority and doNotAsk arrays with each ask
                    if (!(gameState3.getPriority().contains(((FishAskAction) action).askNum))) {
                        gameState3.getPriority().add(((FishAskAction) action).askNum);
                    }
                    if (gameState3.getDoNotAsk().contains(((FishAskAction) action).askNum)) {
                        Integer integerToRemove = ((FishAskAction) action).askNum;
                        gameState3.getDoNotAsk().remove(integerToRemove);
                    }

                    gameState3.setCurrAsk(((FishAskAction) action).askNum);

                    // Remove cards
                    for(int i = gameState3.getPlayer1Hand().size()-1; i >= 0; --i) {
                        if((((FishAskAction) action).askNum) == gameState3.getPlayer1Hand().get(i).getValue()) {
                            gameState3.player0Hand.add(gameState3.getPlayer1Hand().get(i));
                            gameState3.player1Hand.remove(i);
                            hasCard = true;
                            gameState3.checkForFour();
                        }
                    }
                    if (gameState3.getPlayer1Hand().size() == 0) {
                        System.out.println("Computer drawing 5 cards");
                        gameState3.drawFive(1);
                        gameState3.setCurrentPlayer(1);
                    }

                    System.out.println("User asked for a " + ((FishAskAction) action).askNum);

                    if(hasCard) {
                        return true;
                    }
                    if(!hasCard) {
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

                    // Updates the priority and doNotAsk arrays with each ask
                    if (!(gameState3.getDoNotAsk().contains(((FishAskAction) action).askNum))) {
                        gameState3.getDoNotAsk().add(((FishAskAction) action).askNum);
                    }

                    gameState3.setCurrAsk(((FishAskAction) action).askNum);

                    for(int i = gameState3.getPlayer0Hand().size()-1; i >= 0; --i) {
                        if((((FishAskAction) action).askNum) == gameState3.getPlayer0Hand().get(i).getValue()) {
                            gameState3.player1Hand.add(gameState3.getPlayer0Hand().get(i));
                            gameState3.player0Hand.remove(i);
                            hasCard = true;
                            gameState3.checkForFour();
                        }
                    }

                    if (gameState3.getPlayer0Hand().size() == 0) {
                        System.out.println("User drawing 5 cards");
                        gameState3.drawFive(0);
                        gameState3.setCurrentPlayer(0);
                    }

                    System.out.println("Computer asked for a " + ((FishAskAction) action).askNum);
                    
                    if(hasCard) {
                        return true;
                    }

                    if(!hasCard) {
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
