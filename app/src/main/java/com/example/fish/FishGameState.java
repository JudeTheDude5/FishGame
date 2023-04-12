package com.example.fish;

import com.example.GameFramework.infoMessage.GameState;

import java.util.ArrayList;
import java.util.Random;

public class FishGameState extends GameState {

    public int currentPlayer;

    public ArrayList<FishCard> deck;
    public ArrayList<FishCard> player0Hand; //Map of the player ID to their hand of cards
    public ArrayList<FishCard> player1Hand;
    //private Map<Integer, Integer> playerScores; // ..scores?!
    private int player0Score;
    private int player1Score;

    private int currAsk;

    FishGameState() {
        currentPlayer = 0;
        this.deck = createDeck();
        dealCards(this.player0Hand);
        dealCards(this.player1Hand);
        player0Score = 0;
        player1Score = 0;
        currAsk = 0;
    }

    public FishGameState(FishGameState deep) {
        currentPlayer = deep.currentPlayer;

        for(int i = 0; i < deep.deck.size(); i++) {
            this.deck.add(new FishCard(deep.deck.get(i)));
        }

        player0Score = deep.player0Score;
        player1Score = deep.player1Score;

        for(int i = 0; i < deep.player0Hand.size(); i++) {
            this.player0Hand.add(new FishCard(deep.player0Hand.get(i)));
        }

        for(int i = 0; i < deep.player1Hand.size(); i++) {
            this.player1Hand.add(new FishCard(deep.player1Hand.get(i)));
        }
        this.currAsk = deep.getCurrAsk();
    }

    private ArrayList<FishCard> createDeck() {
        ArrayList<FishCard> tempDeck = new ArrayList<>();
        for (int i = 1; i <= 13; i++) {
            FishCard heartCard = new FishCard("hearts", i);
            FishCard diamondCard = new FishCard("diamonds", i);
            FishCard clubCard = new FishCard("clubs", i);
            FishCard spadeCard = new FishCard("spades", i);
            tempDeck.add(heartCard);
            tempDeck.add(diamondCard);
            tempDeck.add(clubCard);
            tempDeck.add(spadeCard);
        }
        return deck;
    }

    private void dealCards(ArrayList<FishCard> j) {
        for (int i = 0; i < 7; i++) {
            Random random = new Random();
            int k = random.nextInt(deck.size());
            j.add(deck.get(k));
            deck.remove(k);

        }
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }
    public ArrayList<FishCard> getDeck() {
        return deck;
    }
    public ArrayList<FishCard> getPlayer0Hand() {
        return player0Hand;
    }

    public ArrayList<FishCard> getPlayer1Hand() {
        return player1Hand;
    }
    public int getPlayer0Score() {
        return player0Score;
    }
    public int getPlayer1Score() {
        return player1Score;
    }
    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    public void setDeck(ArrayList<FishCard> deck) {
        this.deck = deck;
    }
    public void setPlayer0Hand(ArrayList<FishCard> player0Hand) {
        this.player0Hand = player0Hand;
    }
    public void setPlayer1Hand(ArrayList<FishCard> player1Hand) {
        this.player1Hand = player1Hand;
    }
    public void setPlayer0Score(int player0Score) {
        this.player0Score = player0Score;
    }
    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public int getCurrAsk() {
        return currAsk;
    }

    public void setCurrAsk(int currAsk) {
        this.currAsk = currAsk;
    }
}
