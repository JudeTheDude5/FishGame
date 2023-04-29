package com.example.fish;

import static android.graphics.Color.RED;

import android.app.AlertDialog;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.GameFramework.GameMainActivity;
import com.example.GameFramework.infoMessage.GameInfo;
import com.example.GameFramework.players.GameHumanPlayer;
import com.example.game_test_b.R;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Class FishHumanPlayer extends GameHumanPlayer and implements
 * View.OnClickListener to create an instance of a player that can be
 * used by the user
 *
 * @author Jude Reynolds, Alexis Nguyen, Isaela Timogene-Julien, Heidi Pham
 * @version April 28, 2023
 */
public class FishHumanPlayer extends GameHumanPlayer {
    // Instances
    private GameMainActivity myActivity;
    private LinearLayout playerLayout = null;
    private LinearLayout opponentLayout = null;
    private ImageButton deck = null;
    private ArrayList<ImageButton> images;
    private ArrayList<ImageButton> opponentImages;
    private TextView opponentHand = null;
    private TextView oppScore = null;
    private TextView myScore = null;
    private TextView lastAsk = null;
    private int previousDeckSize;

    /**
     * Constructor for FishHumanPlayer
     *
     * @param name the name of the player
     */
    public FishHumanPlayer(String name) {
        super(name);
    }

    /**
     * View layout of the game
     *
     * @return The layout of the game
     */
    @Override
    public View getTopView() {
        return myActivity.findViewById(R.id.layout);
    }

    /**
     * Initializes the characteristics of a FishHumanPlayer
     *
     * @param info Takes in the GameInfo
     */
    @Override
    public void receiveInfo(GameInfo info) {
        // If the player asks for a card when it is not their turn
        if (!(info instanceof FishGameState)) {
            flash(RED, 2);
            return;
        }
        else {
            // Initialize ArrayLists of player and opponent hand
            ArrayList<FishCard> playerArrHand = ((FishGameState) info).getPlayer0Hand();
            ArrayList<FishCard> opponentArrHand = ((FishGameState) info).getPlayer1Hand();

            // Sort the player hand into ascending order
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                playerArrHand.sort(new Comparator<FishCard>() {
                    /**
                     * Compare the values of the player deck and organize them
                     *
                     * @param fishCard1 The first FishCard to compare
                     * @param fishCard2 The second FishCard to compare
                     */
                    @Override
                    public int compare(FishCard fishCard1, FishCard fishCard2) {
                        return Integer.compare(fishCard1.getValue(), fishCard2.getValue());
                    }
                });
            }

            // Prints the hands in the logcat for comparison
            String playerHandText = "";
            String opponentHandText = "";
            String playerScoreText = "";
            String opponentScoreText = "";

            // Assign each value of the player hand to a String
            for (int i = 0; i < playerArrHand.size(); ++i) {
                playerHandText = playerHandText + (playerArrHand.get(i).getValue() + ", ");
            }

            // Assign each value of the opponent hand to a String
            for (int j = 0; j < ((FishGameState) info).getPlayer1Hand().size(); ++j) {
                opponentHandText = opponentHandText + (((FishGameState) info).getPlayer1Hand().get(j).getValue() + ", ");
            }

            // Sets the scores of each hand to a String
            playerScoreText = String.valueOf(((FishGameState) info).getPlayer0Score());
            opponentScoreText = String.valueOf(((FishGameState) info).getPlayer1Score());

            // Logcats the hands for reference
            Log.d("Player Hand", playerHandText);
            Log.d("Opponent Hand", opponentHandText);

            // Updates the score
            myScore.setText(playerScoreText);
            oppScore.setText(opponentScoreText);

            // Updates the deck to be invisible once cards are gone
            if (((FishGameState) info).getDeck().isEmpty()) {
                deck.setVisibility(View.INVISIBLE);
            }

            // Update and set card images for player hand, opponent hand, and deck
            setPlayerCardImages(playerArrHand, images);
            setOpponentCardImages(opponentArrHand, opponentImages);
            setDeck();

            // Set the background image
            setBackground(R.drawable.fish_background);

            // Shows the state of the current player
            ImageView arrow1 = myActivity.findViewById(R.id.arrow_blue);
            ImageView arrow2 = myActivity.findViewById(R.id.arrow_purple);

            // If it is player 0, the arrow points at them
            if (((FishGameState) info).getCurrentPlayer() == 0) {
                arrow1.setImageResource(R.drawable.arrow_blue);
                arrow1.setVisibility(View.VISIBLE);// make sure the view is visible if the current player is 0
                arrow2.setVisibility(View.INVISIBLE); // or View.GONE to remove the view from the layout entirely
            }
            else { // If it is player 1, the arrow points at them
                arrow2.setImageResource(R.drawable.arrow_purple);
                arrow2.setVisibility(View.VISIBLE);// make sure the view is visible if the current player is not 0
                arrow1.setVisibility(View.INVISIBLE); // or View.GONE to remove the view from the layout entirely
            }

            // Show what card was asked on the screen
            if (((FishGameState) info).getCurrAsk() == 0) {
                this.lastAsk.setText("No Card Has Been Asked Yet");
            } else {
                this.lastAsk.setText("Last Card Asked: " + ((FishGameState) info).getCurrAsk());
            }

            // Check if the deck decreased by one and show "go fish" if it does
            int currentDeckSize = ((FishGameState) info).getDeck().size();
            if (currentDeckSize < previousDeckSize) {
                final Toast toast = Toast.makeText(myActivity, "Go Fish!", Toast.LENGTH_SHORT);
                toast.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 1000); // Delay for 1 second (1000 milliseconds)
            }
            previousDeckSize = currentDeckSize;

            // Set on click listeners for each player card
            for (int i = 0; i < images.size(); i++) {
                final int index = i; // store the current index
                images.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int ask = playerArrHand.get(index).getValue();
                        FishAskAction askAction = new FishAskAction(FishHumanPlayer.this, ask);
                        game.sendAction(askAction);
                    }
                });
            }
        }
    }

    /**
     * Initializes the Graphic User Interface of the game
     *
     * @param activity the activity of the game
     */
    @Override
    public void setAsGui(GameMainActivity activity) {
        // Activity instance
        myActivity = activity;
        activity.setContentView(R.layout.activity_main);

        // Instantiate layouts where the cards are
        this.playerLayout = (LinearLayout) activity.findViewById(R.id.player_hand);
        this.opponentLayout = (LinearLayout) activity.findViewById(R.id.opponent_hand);

        // Initialize image array to cards on the GUI
        this.images = new ArrayList<>();
        this.images.add((ImageButton) activity.findViewById(R.id.player_card1));
        this.images.add((ImageButton) activity.findViewById(R.id.player_card2));
        this.images.add((ImageButton) activity.findViewById(R.id.player_card3));
        this.images.add((ImageButton) activity.findViewById(R.id.player_card4));
        this.images.add((ImageButton) activity.findViewById(R.id.player_card5));
        this.images.add((ImageButton) activity.findViewById(R.id.player_card6));
        this.images.add((ImageButton) activity.findViewById(R.id.player_card7));

        // Initialize opponent images to the cards on the GUI
        this.opponentImages = new ArrayList<>();
        this.opponentImages.add((ImageButton) activity.findViewById(R.id.opponent_card1));
        this.opponentImages.add((ImageButton) activity.findViewById(R.id.opponent_card2));
        this.opponentImages.add((ImageButton) activity.findViewById(R.id.opponent_card3));
        this.opponentImages.add((ImageButton) activity.findViewById(R.id.opponent_card4));
        this.opponentImages.add((ImageButton) activity.findViewById(R.id.opponent_card5));
        this.opponentImages.add((ImageButton) activity.findViewById(R.id.opponent_card6));
        this.opponentImages.add((ImageButton) activity.findViewById(R.id.opponent_card7));

        // Initialize deck to deck on GUI
        this.deck = (ImageButton) activity.findViewById(R.id.deck);

        // Initialize scores
        this.myScore = (TextView) activity.findViewById(R.id.player0score);
        this.oppScore = (TextView) activity.findViewById(R.id.player1score);

        // Set the background image
        setBackground(R.drawable.fish_background);

        // Show what AI ask for
        this.lastAsk = (TextView) activity.findViewById(R.id.lastAsk);
    }

    /**
     * Sets and updates the card images for the player hand
     *
     * @param hand An ArrayList of FishCards representing the player hand
     * @param images An Arraylist of the ImageButtons for the card images
     */
    public void setPlayerCardImages(ArrayList<FishCard> hand, ArrayList<ImageButton> images) {
        // Variables
        int numCards = hand.size();

        // Resets view for redraw
        playerLayout.removeAllViews();
        images.clear();

        // Creates a HashMap to count how many cards in the hand have the same value
        Map<Integer, Integer> cardCounts = new HashMap<>();

        // Loops through the player hand and assigns the correct card image
        for (int i = 0; i < numCards; i++) {
            // Variables
            int value = hand.get(i).getValue();

            // Makes a new image button
            ImageButton imageButton = new ImageButton(myActivity);
            imageButton.setLayoutParams(new LinearLayout.LayoutParams(130, 190));
            imageButton.setScaleType(ImageView.ScaleType.FIT_XY);
            String fileName = numToString(hand, i); // Finds file name for card value

            // Sets the ImageViews on each card to the corresponding filename
            int imageResource = myActivity.getResources().getIdentifier(fileName,
                    "drawable", myActivity.getPackageName());
            imageButton.setImageResource(imageResource);

            // If card is already in hand, stack the card
            if (cardCounts.containsKey(value)) {
                cardCounts.put(value, cardCounts.get(value) + 1);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageButton.getLayoutParams();
                params.setMargins(-90, 0, 0, 0);
                imageButton.setLayoutParams(params);
                images.add(imageButton);
                playerLayout.addView(imageButton);
            }
            else { // If it isn't in the hand already
                // Adds the image button normally
                images.add(imageButton);
                playerLayout.addView(imageButton); // add the ImageButton to the playerLayout
                cardCounts.put(value, 1);
            }
        }
    }

    /**
     * Sets and updates the card images for the opponent hand
     *
     * @param hand An ArrayList of FishCards representing the opponent hand
     * @param images An Arraylist of the ImageButtons for the card images
     */
    public void setOpponentCardImages(ArrayList<FishCard> hand, ArrayList<ImageButton> images) {
        // Variables
        int numCards = hand.size();

        // Clear opponent layout
        opponentLayout.removeAllViews();
        opponentImages.clear();

        // Creates an ImageButton for each oppoenet card
        for (int i = 0; i < numCards; i++) {
            ImageButton imageButton = new ImageButton(myActivity);
            imageButton.setLayoutParams(new LinearLayout.LayoutParams(130, 190));
            imageButton.setScaleType(ImageView.ScaleType.FIT_XY);

            // Sets the ImageViews on each card to the corresponding filename
            int imageResource = myActivity.getResources().getIdentifier("card_back",
                    "drawable", myActivity.getPackageName());
            imageButton.setImageResource(imageResource);

            // Overlap opponent cards
            if (i > 0) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageButton.getLayoutParams();
                params.setMargins(-80, 0, 0, 0);
                imageButton.setLayoutParams(params);
            }

            // Add the ImageButtons to the layout
            images.add(imageButton);
            opponentLayout.addView(imageButton);
        }
    }

    /**
     * Sets the image of the deck in the middle
     */
    public void setDeck() {
        // Set the size for the middle deck
        deck.setLayoutParams(new LinearLayout.LayoutParams(130, 190));
        deck.setScaleType(ImageView.ScaleType.FIT_XY);

        // Sets the ImageViews on each card to the corresponding filename
        int imageResource = myActivity.getResources().getIdentifier("card_back",
                "drawable", myActivity.getPackageName());
        deck.setImageResource(imageResource);
    }

    /**
     * Sets the background image
     *
     * @param resourceId The id of the background as an int
     */
    public void setBackground(int resourceId) {
        // Finds the layout of the background and sets it to an image
        LinearLayout layout = (LinearLayout) myActivity.findViewById(R.id.layout);
        layout.setBackgroundResource(resourceId);
    }

    /**
     * Finds the rank and value of a card and sets the correct image file
     * to the card
     *
     * @param hand An ArrayList of FishCards representing the player hand
     * @param i The index of the ArrayList hand
     */
    public String numToString(ArrayList<FishCard> hand, int i) {
        FishCard card = hand.get(i);
        String stringNum = "";

        // Switch statement to assign the correct file name to the corresponding card
        switch (card.getValue()) {
            case 1:
                stringNum = "ace";
                break;
            case 2:
                stringNum = "two";
                break;
            case 3:
                stringNum = "three";
                break;
            case 4:
                stringNum = "four";
                break;
            case 5:
                stringNum = "five";
                break;
            case 6:
                stringNum = "six";
                break;
            case 7:
                stringNum = "seven";
                break;
            case 8:
                stringNum = "eight";
                break;
            case 9:
                stringNum = "nine";
                break;
            case 10:
                stringNum = "ten";
                break;
            case 11:
                stringNum = "jack";
                break;
            case 12:
                stringNum = "queen";
                break;
            case 13:
                stringNum = "king";
                break;
        }
        stringNum += "_of_";
        switch (card.getRank()) {
            case "hearts":
                stringNum += "hearts";
                break;
            case "diamonds":
                stringNum += "diamonds";
                break;
            case "clubs":
                stringNum += "clubs";
                break;
            case "spades":
                stringNum += "spades";
                break;
        }
        return stringNum;
    }
}


