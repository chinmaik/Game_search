package com.example.game_search.pojo;

public class Game {
    private String gameName;
    private int gameImage;

    public String getGameName() {
        return gameName;
    }

    public int getGameImage() {
        return gameImage;
    }

    public Game(String gameName, int gameImage) {
        this.gameName = gameName;
        this.gameImage = gameImage;
    }
}
