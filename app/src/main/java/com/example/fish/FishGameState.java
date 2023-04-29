package com.example.fish;

import com.example.GameFramework.infoMessage.GameState;

import java.util.ArrayList;
import java.util.Random;

public class FishGameState extends GameState {

    public int currentPlayer;

    public ArrayList<FishCard> deck;
    public ArrayList<FishCard> player0Hand; //Map of the player ID to their hand of cards
    public ArrayList<FishCard> player1Hand;
    public ArrayList<Integer> priority;
    public ArrayList<Integer> doNotAsk;
    //private Map<Integer, Integer> playerScores; // ..scores?!
    private int player0Score;
    private int player1Score;
    public ArrayList<FishCard> recentAsk;

    private int currAsk;

    public FishGameState() {
        currentPlayer = 0;
        deck = new ArrayList<>();
        this.deck = createDeck();
        player0Hand = new ArrayList<>();
        player1Hand = new ArrayList<>();
        priority = new ArrayList<>();
        doNotAsk = new ArrayList<>();
        //dealCards(this.player0Hand);
        this.player0Hand = dealCards();
        this.player1Hand = dealCards();
        //dealCards(this.player1Hand);
        player0Score = 0;
        player1Score = 0;
        currAsk = 0;

    }

    public FishGameState(FishGameState deep) {
        currentPlayer = deep.currentPlayer;

        deck = new ArrayList<>();

        for(int i = 0; i < deep.deck.size(); i++) {
            this.deck.add(new FishCard(deep.deck.get(i)));
        }

        player0Score = deep.player0Score;
        player1Score = deep.player1Score;

        priority = deep.priority;
        doNotAsk = deep.doNotAsk;

        player0Hand = new ArrayList<>();
        player1Hand = new ArrayList<>();

        for(int i = 0; i < deep.player0Hand.size(); i++) {
            this.player0Hand.add(new FishCard(deep.player0Hand.get(i)));
        }

        for(int i = 0; i < deep.player1Hand.size(); i++) {
            this.player1Hand.add(new FishCard(deep.player1Hand.get(i)));
        }
        this.currAsk = deep.currAsk;
    }

    public ArrayList<FishCard> createDeck() {
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
        return tempDeck;
    }

    public ArrayList<FishCard> dealCards() {
        ArrayList<FishCard> tempDeck = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Random random = new Random();
            int k = random.nextInt(deck.size());
            tempDeck.add(deck.get(k));
            deck.remove(k);

        }
        return tempDeck;
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
    public ArrayList<Integer> getPriority() { return priority; }
    public ArrayList<Integer> getDoNotAsk() { return doNotAsk; }
    public int getPlayer0Score() {
        return player0Score;
    }
    public int getPlayer1Score() {
        return player1Score;
    }
    public int getCurrAsk() {
        return currAsk;
    }
    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setCurrAsk(int currAsk) {
        this.currAsk = currAsk;
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
    public void setPriority(ArrayList<Integer> priority) { this.priority = priority; }
    public void setDoNotAsk(ArrayList<Integer> doNotAsk) { this.doNotAsk = doNotAsk; }
    public void setPlayer0Score(int player0Score) {
        this.player0Score = player0Score;
    }
    public void setPlayer1Score(int player1Score) {
        this.player1Score = player1Score;
    }

    public void checkForFour() {
        int numHowMany = 0;
        for(int i = 1; i <= 13; ++i) {
            for(int j = player0Hand.size() - 1; j >= 0; --j) {
                if((player0Hand.get(j).getValue()) == i) {
                    numHowMany++;
                }
            }
            if(numHowMany == 4) {
                for(int j = player0Hand.size() - 1; j >= 0; --j) {
                    if((player0Hand.get(j).getValue()) == i) {
                        player0Hand.remove(j);
                    }
                }
                player0Score++;
            }
            numHowMany = 0;

            for(int j = player1Hand.size() - 1; j >= 0; --j) {
                if((player1Hand.get(j).getValue()) == i) {
                    numHowMany++;
                }
            }
            if(numHowMany == 4) {
                for(int j = player1Hand.size() - 1; j >= 0; --j) {
                    if((player1Hand.get(j).getValue()) == i) {
                        player1Hand.remove(j);
                    }
                }
                player1Score++;
            }
            numHowMany = 0;
        }
        // In case check for four takes all cards
        if (getCurrentPlayer() == 0) {
            if (getPlayer0Hand().size() == 0) {
                System.out.println("User drawing 5 cards");
                drawFive(0);
            }
        } else {
            if (getPlayer1Hand().size() == 0) {
                System.out.println("Computer drawing 5 cards");
                drawFive(1);
            }
        }
    }

    public void drawFive(int playerIdx) {
        if (playerIdx == 0) {
            for (int i = 0; i < 5; i++) {
                if (getDeck().size() != 0) {
                    Random q = new Random();
                    int draw = q.nextInt(getDeck().size());
                    getPlayer0Hand().add(getDeck().get(draw));
                    getDeck().remove(draw);
                    checkForFour();
                }
            }
        } else {
            for (int i = 0; i < 5; i++) {
                if (getDeck().size() != 0) {
                    Random q = new Random();
                    int draw = q.nextInt(getDeck().size());
                    getPlayer1Hand().add(getDeck().get(draw));
                    getDeck().remove(draw);
                    checkForFour();
                }
            }
        }
    }

    public int getSmartVal() {
        int value;

        if (getPlayer1Hand().size() == 0) {
            return -1;
        }

        if (getPriority() != null) {
            for (FishCard card : getPlayer1Hand()) {
                if (getPriority().contains(card.getValue())) {
                    value = card.getValue();
                    Integer integerToRemove = value;
                    getPriority().remove(integerToRemove);
                    return value;
                }
            }
        }
        if (getDoNotAsk() != null) {
            for (FishCard card : getPlayer1Hand()) {
                if (!(getDoNotAsk().contains(card.getValue()))) {
                    value = card.getValue();
                    return value;
                }
            }
        }

        ArrayList<Integer> intValues = new ArrayList<Integer>();
        for (FishCard card : getPlayer1Hand()) {
            intValues.add(card.getValue());
        }
        return intValues.get(0);
    }
    public boolean isGameOver() {
        if(player0Hand.isEmpty() || player1Hand.isEmpty()){
            return true;
        }
        return false;
    }

}
