package com.example.fish;

import com.example.GameFramework.GameMainActivity;
import com.example.GameFramework.gameConfiguration.GameConfig;
import com.example.GameFramework.infoMessage.GameState;
import com.example.GameFramework.LocalGame;
import com.example.GameFramework.gameConfiguration.GamePlayerType;
import com.example.GameFramework.players.GamePlayer;

import java.util.ArrayList;


public class FishMainActivity extends GameMainActivity {

    private static final int PORT_NUMBER = 2222;

    @Override
        public GameConfig createDefaultConfig() {

        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new FishHumanPlayer(name);
            }});

        playerTypes.add(new GamePlayerType("Smart Computer Player") {
            public GamePlayer createPlayer(String name) { return new FishSmartAI(name); }});

        playerTypes.add(new GamePlayerType("Computer Player") {
            public GamePlayer createPlayer(String name) {
                return new FishDumbAI(name);
            }});

        GameConfig defaultConfig = new GameConfig(playerTypes, 1, 2, "Go Fish", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0); // player 1: a human player
        defaultConfig.addPlayer("Computer", 1); // player 2: a computer player
        defaultConfig.setRemoteData("Remote Human Player", "", 0);


        return defaultConfig;
        }

    @Override
    public LocalGame createLocalGame(GameState gameState) {
        return null;
    }


    @Override
    public LocalGame createLocalGame() {
        return new FishLocalGame();
    }
}