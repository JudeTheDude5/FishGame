package com.example.fish;

/**
 * Class FishCard creates the card object that is used to play the game
 *
 * @author Jude Reynolds, Alexis Nguyen, Isaela Timogene-Julien, Heidi Pham
 * @version April 28, 2023
 */
public class FishCard {
    // Instances
    private String rank;
    private int value;

    /**
     * Constructor for FishCard
     *
     * @param rank Che rank of the card
     * @param value Che value of the card
     */
    public FishCard(String rank, int value) {
        // Initializes class instances
        this.rank = rank;
        this.value = value;
    }

    /**
     *  2nd Constructor for FishCard
     *
     * @param g Creates a copy of a FishCard object
     */
    public FishCard(FishCard g) {
        // Initializes class instances
        this.rank = g.getRank();
        this.value = g.getValue();
    }

    /**
     * Gets the rank of a FishCard object
     *
     * @return the rank of a card as a String
     */
    public String getRank() {
        return this.rank;
    }

    /**
     * Gets the value of a FishCard object
     *
     * @return the value of a card as an int
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Sets the rank of a FishCard object
     *
     * @param rank The rank of a card as a String
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     * Sets the value of a FishCard object
     *
     * @param value The value of a card as an int
     */
    public void setValue(int value) {
        this.value = value;
    }
}
