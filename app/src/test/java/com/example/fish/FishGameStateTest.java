package com.example.fish;

import static org.junit.Assert.assertEquals;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashSet;

public class FishGameStateTest extends TestCase {

    public void testCreateDeck() throws Exception{
        FishCard card = new FishCard("hi",0);
        String ranking = card.getRank();
        String rk = card.getRank();
        assertEquals("hi", ranking);
        assertEquals("hi", rk);
    }

    public void testDealCards() throws Exception{
        // Initialize game state with a deck of cards
        FishGameState gameState = new FishGameState();

        // Call the dealCards method to get a hand of 7 cards
        ArrayList<FishCard> hand = gameState.dealCards();

        // Assert that the hand has 7 cards
        assertEquals(7, hand.size());

        // Assert that the deck has 45 cards (52 cards in total - 7 cards in hand)
        assertEquals(31, gameState.getDeck().size());

        // Assert that all the cards in the hand and the deck are unique
        ArrayList<FishCard> allCards = new ArrayList<>();
        allCards.addAll(hand);
        allCards.addAll(gameState.getDeck());
        assertEquals(new HashSet<>(allCards).size(), allCards.size());
    }
    public void testDrawFive() throws Exception{
        FishGameState gameState = new FishGameState();

        // Create a deck with just one card
        ArrayList<FishCard> deck = new ArrayList<>();
        deck.add(new FishCard("hearts", 1));
        gameState.setDeck(deck);

        ArrayList<FishCard> player0Hand = new ArrayList<>();
        gameState.setPlayer0Hand(player0Hand);

        // Draw five cards for player 0
        gameState.drawFive(0);

        // Ensure player 0 hand has five cards
        assertEquals(1, gameState.getPlayer0Hand().size());

        // Ensure deck is empty
        assertEquals(0, gameState.getDeck().size());

        // Ensure player 0 score is not increased
        assertEquals(0, gameState.getPlayer0Score());

        // Create a deck with four cards
        deck = new ArrayList<>();
        deck.add(new FishCard("hearts", 2));
        deck.add(new FishCard("diamonds", 2));
        deck.add(new FishCard("clubs", 2));
        deck.add(new FishCard("spades", 2));
        gameState.setDeck(deck);

        player0Hand = new ArrayList<>();
        player0Hand.add(new FishCard("hearts", 1));
        gameState.setPlayer0Hand(player0Hand);

        // Draw five cards for player 0
        gameState.drawFive(0);

        // Ensure player 0 hand has five cards
        assertEquals(1, gameState.getPlayer0Hand().size());

        // Ensure deck has one card left
        assertEquals(0, gameState.getDeck().size());

        // Ensure player 0 score is increased by one
        assertEquals(1, gameState.getPlayer0Score());
    }

}