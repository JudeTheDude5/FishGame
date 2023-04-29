package com.example.fish;

import com.example.GameFramework.infoMessage.GameState;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class FishGameState extends GameState and initializes the variables
 * needed to return the state of the game
 *
 * @author Jude Reynolds, Alexis Nguyen, Isaela Timogene-Julien, Heidi Pham
 * @version April 28, 2023
 */
public class FishGameState extends GameState {
    // Instances
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

    /**
     * Constructor for FishGameState
     */
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

    /**
     * Copy Constructor for FishGameState
     *
     * @param deep The FishGameState object to copy from
     */
    public FishGameState(FishGameState deep) {
        // Variables
        currentPlayer = deep.currentPlayer;

        deck = new ArrayList<>();

        // Copies the deck
        for(int i = 0; i < deep.deck.size(); i++) {
            this.deck.add(new FishCard(deep.deck.get(i)));
        }

        // Copies the scores
        player0Score = deep.player0Score;
        player1Score = deep.player1Score;

        // Copies the asking states for the AI
        priority = deep.priority;
        doNotAsk = deep.doNotAsk;

        // Initializes hands for the players
        player0Hand = new ArrayList<>();
        player1Hand = new ArrayList<>();

        // Copies the player 0 hand
        for(int i = 0; i < deep.player0Hand.size(); i++) {
            this.player0Hand.add(new FishCard(deep.player0Hand.get(i)));
        }

        // Copies the player 1 hand
        for(int i = 0; i < deep.player1Hand.size(); i++) {
            this.player1Hand.add(new FishCard(deep.player1Hand.get(i)));
        }
        this.currAsk = deep.currAsk;
    }

    /**
     * Creates an ArrayList of FishCard Objects as the deck
     *
     * @return An ArrayList of FishCards
     */
    public ArrayList<FishCard> createDeck() {
        // Variables
        ArrayList<FishCard> tempDeck = new ArrayList<>();

        // Creates the deck
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

    /**
     * Deals the FishCards to each player
     *
     * @return An ArrayList representing a randomized deck for each player
     */
    public ArrayList<FishCard> dealCards() {
        // Variables
        ArrayList<FishCard> tempDeck = new ArrayList<>();

        // Deals the cards
        for (int i = 0; i < 7; i++) {
            Random random = new Random();
            int k = random.nextInt(deck.size());
            tempDeck.add(deck.get(k));
            deck.remove(k);
        }
        return tempDeck;
    }

    /**
     * Block of getters for FishGameState
     */
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

    /**
     * Block of setters for FishGameState
     */
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

    /**
     * Checks the player hand for four of a kind
     */
    public void checkForFour() {
        // Variables
        int numHowMany = 0;

        // Counts the amount of times a value shows up in the player 0 hand
        for(int i = 1; i <= 13; ++i) {
            for(int j = player0Hand.size() - 1; j >= 0; --j) {
                if((player0Hand.get(j).getValue()) == i) {
                    numHowMany++;
                }
            }

            // If the player 0 has 4, remove the cards from the hand
            if(numHowMany == 4) {
                for(int j = player0Hand.size() - 1; j >= 0; --j) {
                    if((player0Hand.get(j).getValue()) == i) {
                        player0Hand.remove(j);
                    }
                }
                player0Score++;
            }
            numHowMany = 0;

            // Counts the amount of times a value shows up in the player 1 hand
            for(int j = player1Hand.size() - 1; j >= 0; --j) {
                if((player1Hand.get(j).getValue()) == i) {
                    numHowMany++;
                }
            }

            // If the player 1 has 4, remove the cards from the hand
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
        }
        else {
            if (getPlayer1Hand().size() == 0) {
                System.out.println("Computer drawing 5 cards");
                drawFive(1);
            }
        }
    }

    /**
     * Checks the player hand for four of a kind
     */
    public void drawFive(int playerIdx) {
        if (playerIdx == 0) { // user
            for (int i = 0; i < 5; i++) {
                if (getDeck().size() != 0) { // while the deck isn't empty

                    // drawing 5 random cards from the deck
                    Random q = new Random();
                    int draw = q.nextInt(getDeck().size());
                    getPlayer0Hand().add(getDeck().get(draw));
                    getDeck().remove(draw);
                    checkForFour(); // checks if the new cards include a four of a kind
                }
            }
        }
        else { // computer
            for (int i = 0; i < 5; i++) {
                if (getDeck().size() != 0) { //while the deck isn't empty
                    // drawing 5 random cards from the deck
                    Random q = new Random();
                    int draw = q.nextInt(getDeck().size());
                    getPlayer1Hand().add(getDeck().get(draw));
                    getDeck().remove(draw);
                    checkForFour(); // checks if the new cards include a four of a kind
                }
            }
        }
    }

    /**
     * Gets the smarter value for the SmartAI to ask
     *
     * @return An int
     */
    public int getSmartVal() {
        // Variables
        int value;

        // If the computer hand is empty just return a number
        if (getPlayer1Hand().size() == 0) {
            return -1;
        }

        // First loop through
        // Checks to see if the computer hand contains any cards from the priority array
        if (getPriority() != null) {
            for (FishCard card : getPlayer1Hand()) {
                if (getPriority().contains(card.getValue())) {
                    value = card.getValue();
                    Integer integerToRemove = value;
                    getPriority().remove(integerToRemove);
                    return value; // return value if it is in it
                }
            }
        }
        // Second loop through
        // Checks to see if card is in doNotAsk array
        if (getDoNotAsk() != null) {
            for (FishCard card : getPlayer1Hand()) {
                if (!(getDoNotAsk().contains(card.getValue()))) {
                    value = card.getValue();
                    return value; // will return the first card that isn't in the array
                }
            }
        }

        // Last loop if none of its cards are in priority array
        // And all of them are in the doNotAsk array
        Random z = new Random();
        int rand = z.nextInt(getPlayer1Hand().size());

        // This is to typecast it from a Card object to an integer
        ArrayList<Integer> intValues = new ArrayList<Integer>();
        for (FishCard card : getPlayer1Hand()) {
            intValues.add(card.getValue());
        }
        return intValues.get(rand); // returns a random number from hand
    }
    /**
     *  Checks if game is over
     *
     * @return A boolean
     */
    public boolean isGameOver() {
        if(player0Hand.isEmpty() || player1Hand.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * For the most recent ask display to be the correct text rather than a number
     *
     * @param i The index
     * @return A String
     */
    public String numberToString(int i) {
        String stringNum = "";
        switch (i) {
            case 1:
                stringNum = "Ace";
                break;
            case 2:
                stringNum = "2";
                break;
            case 3:
                stringNum = "3";
                break;
            case 4:
                stringNum = "4";
                break;
            case 5:
                stringNum = "5";
                break;
            case 6:
                stringNum = "6";
                break;
            case 7:
                stringNum = "7";
                break;
            case 8:
                stringNum = "8";
                break;
            case 9:
                stringNum = "9";
                break;
            case 10:
                stringNum = "10";
                break;
            case 11:
                stringNum = "Jack";
                break;
            case 12:
                stringNum = "Queen";
                break;
            case 13:
                stringNum = "King";
                break;
        }
        return stringNum;
    }

}
