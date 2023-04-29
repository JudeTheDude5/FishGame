package com.example.fish;

import com.example.GameFramework.GameMainActivity;
import com.example.GameFramework.gameConfiguration.GameConfig;
import com.example.GameFramework.infoMessage.GameState;
import com.example.GameFramework.LocalGame;
import com.example.GameFramework.gameConfiguration.GamePlayerType;
import com.example.GameFramework.players.GamePlayer;

import java.util.ArrayList;

/**
 * class FishMainActivity extends GameMainActivity and creates the landing
 * screen on the game
 *
 * @author Jude Reynolds, Alexis Nguyen, Isaela Timogene-Julien, Heidi Pham
 * @version April 28, 2023
 */

public class FishMainActivity extends GameMainActivity {
    // Instances
    private static final int PORT_NUMBER = 2278;

    /**
     * Creates the default configuration for the start screen
     *
     * @return createDefaultConfig
     */
    @Override
        public GameConfig createDefaultConfig() {
        // Variables
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // Create players
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new FishHumanPlayer(name);
            }});
        playerTypes.add(new GamePlayerType("Computer Player") {
            public GamePlayer createPlayer(String name) {
                return new FishDumbAI(name);
            }});
        playerTypes.add(new GamePlayerType("Smart Computer Player") {
            public GamePlayer createPlayer(String name) { return new FishSmartAI(name); }});

        // Add players to picker
        GameConfig defaultConfig = new GameConfig(playerTypes, 1, 2, "Go Fish", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0); // player 1: a human player
        defaultConfig.addPlayer("Computer", 1); // player 2: a computer player
        defaultConfig.setRemoteData("Remote Human Player", "", 0);
        return defaultConfig;
    }

    /**
     * Creates a new game
     *
     * @param gameState A GameState object
     */
    @Override
    public LocalGame createLocalGame(GameState gameState) {
        return new FishLocalGame();
    }
}