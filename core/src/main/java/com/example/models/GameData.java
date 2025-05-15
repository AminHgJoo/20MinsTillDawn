package com.example.models;

import java.util.ArrayList;

public class GameData {
    private Player player;

    private int gameEndTimeInMins;

    //TODO: Update these two
    private int elapsedTimeInSeconds;
    private boolean isGameInBossStage;

    private ArrayList<Enemy> enemies;

    /**
     * @author AminHg
     * @apiNote Used after JSON deserialization of saved game, loads all graphical assets.
     */
    public void loadTransientObjects() {
        for (Enemy enemy : enemies) {
            enemy.loadAnimation();
        }
        player.getWeapon().loadSprite();
        player.loadAnimations();
    }

    public GameData() {
    }

    public GameData(int gameEndTimeInMins, Player player) {
        this.gameEndTimeInMins = gameEndTimeInMins;
        this.player = player;

        elapsedTimeInSeconds = 0;
        enemies = new ArrayList<>();
        isGameInBossStage = false;
    }

    public int getElapsedTimeInSeconds() {
        return elapsedTimeInSeconds;
    }

    public void setElapsedTimeInSeconds(int elapsedTimeInSeconds) {
        this.elapsedTimeInSeconds = elapsedTimeInSeconds;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    public int getGameEndTimeInMins() {
        return gameEndTimeInMins;
    }

    public void setGameEndTimeInMins(int gameEndTimeInMins) {
        this.gameEndTimeInMins = gameEndTimeInMins;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isGameInBossStage() {
        return isGameInBossStage;
    }

    public void setGameInBossStage(boolean gameInBossStage) {
        isGameInBossStage = gameInBossStage;
    }
}
