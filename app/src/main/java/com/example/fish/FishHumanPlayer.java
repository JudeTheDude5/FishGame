package com.example.fish;

import static android.graphics.Color.RED;

import android.os.Build;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.GameFramework.GameMainActivity;
import com.example.GameFramework.infoMessage.GameInfo;
import com.example.GameFramework.players.GameHumanPlayer;
import com.example.game_test_b.R;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class FishHumanPlayer extends GameHumanPlayer implements View.OnClickListener {


    private LinearLayout playerLayout = null;
    private LinearLayout opponentLayout = null;
    private ImageButton deck = null;
    private TextView opponentHand = null;
    private ArrayList<ImageButton> images;
    private ArrayList<ImageButton> opponentImages;
    private TextView oppScore = null;
    private TextView myScore = null;


    private GameMainActivity myActivity;
    private FishGameState fishState;
    private TextView lastAsk = null;


    /**
     * constructor
     *
     * @param name the name of the player
     */
    public FishHumanPlayer(String name) {
        super(name);
    }

    @Override
    public void onClick(View view) {
//        int ask = 0;
//        for(int i = 1; i < 14; i++) {
//            if (askNum.getText().toString().equals(i + "")) {
//                ask = i;
//            }
//        }
//
//        if((ask <= 13) && (ask >= 1)) {
//            FishAskAction askAction = new FishAskAction(this,ask);
//            game.sendAction(askAction);
//        }
//        else {
//            title.setText("You have to pick a card that's from 1-13");
//            return;
//        }
    }

    @Override
    public View getTopView() {
        return myActivity.findViewById(R.id.layout);
    }
    //Need to set up view for GUI

    @Override
    public void receiveInfo(GameInfo info) {
        if (!(info instanceof FishGameState)) {
            flash(RED, 2);
            return;
        }
        else {
            // ArrayLists of player and opponent hand
            ArrayList<FishCard> playerArrHand = ((FishGameState) info).getPlayer0Hand();
            ArrayList<FishCard> opponentArrHand = ((FishGameState) info).getPlayer1Hand();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                playerArrHand.sort(new Comparator<FishCard>() {
                    @Override
                    public int compare(FishCard fishCard1, FishCard fishCard2) {
                        return Integer.compare(fishCard1.getValue(), fishCard2.getValue());
                    }
                });
            }
            // TESTING/////////////////////////////
            String playerHandText = "";
            String opponentHandText = "";
            String playerScoreText = "";
            String opponentScoreText = "";

            for (int i = 0; i < playerArrHand.size(); ++i) {
                playerHandText = playerHandText + (playerArrHand.get(i).getValue() + ", ");
            }
            for (int j = 0; j < ((FishGameState) info).getPlayer1Hand().size(); ++j) {
                opponentHandText = opponentHandText + (((FishGameState) info).getPlayer1Hand().get(j).getValue() + ", ");
            }
            playerScoreText = String.valueOf(((FishGameState) info).getPlayer0Score());
            opponentScoreText = String.valueOf(((FishGameState) info).getPlayer1Score());

            System.out.println(playerHandText);
            System.out.println(opponentHandText);
//            opponentHand.setText(opponentHandText);
            ///////////////////////////////////////////

            // updates the score
            myScore.setText(playerScoreText);
            oppScore.setText(opponentScoreText);

            // updates the deck to be invisible once cards are gone
            if (((FishGameState) info).getDeck().isEmpty()) {
                deck.setVisibility(View.INVISIBLE);
            }

            // updates card images for both player and opponent hand
            setPlayerCardImages(playerArrHand, images);
            setOpponentCardImages(opponentArrHand, opponentImages);
            setDeck();
            // set the background image
            setBackground(R.drawable.fish_background);
            //show what AI ask for
            if(((FishGameState) info).getCurrAsk() == 0) {
                this.lastAsk.setText("No Card Has Been Asked For Yet");
            }
            else {
                this.lastAsk.setText("Last Card Asked For Was A " + ((FishGameState) info).getCurrAsk());
            }


            // set on click listeners for each player card
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

    @Override
    public void setAsGui(GameMainActivity activity) {
        // activity instance
        myActivity = activity;
        activity.setContentView(R.layout.activity_main);

        // instantiate layouts where the cards are
        this.playerLayout = (LinearLayout) activity.findViewById(R.id.player_hand);
        this.opponentLayout = (LinearLayout) activity.findViewById(R.id.opponent_hand);

        // initialize image array to cards on the GUI
        this.images = new ArrayList<>();
        this.images.add((ImageButton) activity.findViewById(R.id.player_card1));
        this.images.add((ImageButton) activity.findViewById(R.id.player_card2));
        this.images.add((ImageButton) activity.findViewById(R.id.player_card3));
        this.images.add((ImageButton) activity.findViewById(R.id.player_card4));
        this.images.add((ImageButton) activity.findViewById(R.id.player_card5));
        this.images.add((ImageButton) activity.findViewById(R.id.player_card6));
        this.images.add((ImageButton) activity.findViewById(R.id.player_card7));

        // initialize opponent images to the cards on the GUI
        this.opponentImages = new ArrayList<>();
        this.opponentImages.add((ImageButton) activity.findViewById(R.id.opponent_card1));
        this.opponentImages.add((ImageButton) activity.findViewById(R.id.opponent_card2));
        this.opponentImages.add((ImageButton) activity.findViewById(R.id.opponent_card3));
        this.opponentImages.add((ImageButton) activity.findViewById(R.id.opponent_card4));
        this.opponentImages.add((ImageButton) activity.findViewById(R.id.opponent_card5));
        this.opponentImages.add((ImageButton) activity.findViewById(R.id.opponent_card6));
        this.opponentImages.add((ImageButton) activity.findViewById(R.id.opponent_card7));

        // initialize deck to deck on GUI
        this.deck = (ImageButton) activity.findViewById(R.id.deck);

        // initialize scores
        this.myScore = (TextView) activity.findViewById(R.id.player0score);
        this.oppScore = (TextView) activity.findViewById(R.id.player1score);

        //set the background image
        //LinearLayout layout = (LinearLayout) activity.findViewById(R.id.layout);
        //layout.setBackgroundResource(R.drawable.fish_background);

        // set the background image
        setBackground(R.drawable.fish_background);

        //show what AI ask for
        this.lastAsk = (TextView) activity.findViewById(R.id.lastAsk);
    }

    // helper method to set the card images on game start
    public void setPlayerCardImages(ArrayList<FishCard> hand, ArrayList<ImageButton> images) {
        // variables
        int numCards = hand.size();

        // resets view for redraw
        playerLayout.removeAllViews();
        images.clear();

        Map<Integer, Integer> cardCounts = new HashMap<>();

        // loops through the player hand and assigns the correct card image
        for (int i = 0; i < numCards; i++) {
            // variables
            int value = hand.get(i).getValue();

            // makes a new image button
            ImageButton imageButton = new ImageButton(myActivity);
            imageButton.setLayoutParams(new LinearLayout.LayoutParams(130, 190));
            imageButton.setScaleType(ImageView.ScaleType.FIT_XY);
            String fileName = numToString(hand, i); // finds file name for card value

            // sets the ImageViews on each card to the corresponding filename
            int imageResource = myActivity.getResources().getIdentifier(fileName,
                    "drawable", myActivity.getPackageName());
            imageButton.setImageResource(imageResource);

            // if card is already in hand, stack the card
            if (cardCounts.containsKey(value)) {
                cardCounts.put(value, cardCounts.get(value) + 1);
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageButton.getLayoutParams();
                params.setMargins(-90, 0, 0, 0);
                imageButton.setLayoutParams(params);
                images.add(imageButton);
                playerLayout.addView(imageButton);
            }

            // if it isn't in the hand already
            else {
                // adds the image button normally
                images.add(imageButton);
                playerLayout.addView(imageButton); // add the ImageButton to the playerLayout
                cardCounts.put(value, 1);
            }
        }
    }

    public void setOpponentCardImages(ArrayList<FishCard> hand, ArrayList<ImageButton> images) {
        // variables
        int numCards = hand.size();

        // clear opponent layout
        opponentLayout.removeAllViews();
        opponentImages.clear();

        for (int i = 0; i < numCards; i++) {
            // FishCard card = hand.get(i);
            ImageButton imageButton = new ImageButton(myActivity);
            imageButton.setLayoutParams(new LinearLayout.LayoutParams(130, 190));
            imageButton.setScaleType(ImageView.ScaleType.FIT_XY);

            // sets the ImageViews on each card to the corresponding filename
            int imageResource = myActivity.getResources().getIdentifier("card_back",
                    "drawable", myActivity.getPackageName());
            imageButton.setImageResource(imageResource);

            // overlap opponent cards
            if (i > 0) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) imageButton.getLayoutParams();
                params.setMargins(-80, 0, 0, 0);
                imageButton.setLayoutParams(params);
            }
            images.add(imageButton);
            opponentLayout.addView(imageButton);
        }
    }

    // sets deck image
    public void setDeck () {
        deck.setLayoutParams(new LinearLayout.LayoutParams(130, 190));
        deck.setScaleType(ImageView.ScaleType.FIT_XY);

        // sets the ImageViews on each card to the corresponding filename
        int imageResource = myActivity.getResources().getIdentifier("card_back",
                "drawable", myActivity.getPackageName());
        deck.setImageResource(imageResource);
    }

    public void setBackground(int resourceId) {
        LinearLayout layout = (LinearLayout) myActivity.findViewById(R.id.layout);
        layout.setBackgroundResource(resourceId);
    }

    // num to string for before stacking
    public String numToString(ArrayList<FishCard> hand, int i) {
        FishCard card = hand.get(i);
        String stringNum = "";
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


