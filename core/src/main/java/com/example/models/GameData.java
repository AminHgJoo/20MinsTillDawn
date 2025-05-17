package com.example.models;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.example.models.enums.types.EnemyTypes;
import com.example.views.GameMenu;

public class GameData {
    private Player player;

    private int gameEndTimeInMins;

    private float elapsedTimeInSeconds;
    //TODO: Boss battle.
    private boolean isGameInBossStage;

    private Array<Enemy> enemies;
    private Array<Bullet> bullets;

    private float timeElapsedFromLastTentacleSpawn;
    private float timeElapsedFromLastEyebatSpawn;

    private transient boolean isPlayerAutoAiming;

    /**
     * @author AminHg
     * @apiNote Used after JSON deserialization of saved game, loads all transient graphical assets.
     */
    public void loadTransientObjects() {
        for (Enemy enemy : enemies) {
            enemy.loadAnimation();
        }
        for (Bullet bullet : bullets) {
            bullet.loadSprite();
        }
        player.loadAnimations();
        isPlayerAutoAiming = false;
    }

    public GameData() {
    }


    private void addTreeMonsters() {
        final int numOfTrees = MathUtils.random(8, 15);
        final int treeSize = 200;

        for (int i = 0; i < numOfTrees; i++) {
            int xPos = MathUtils.random(treeSize, (int) GameMenu.SCREEN_WIDTH * 2 - treeSize);
            int yPos = MathUtils.random(treeSize, (int) GameMenu.SCREEN_HEIGHT * 2 - treeSize);
            enemies.add(new Enemy(new Vector2(xPos, yPos), EnemyTypes.TREE_MONSTER));
        }
    }

    public GameData(int gameEndTimeInMins, Player player) {
        this.gameEndTimeInMins = gameEndTimeInMins;
        this.player = player;

        elapsedTimeInSeconds = 0;
        enemies = new Array<>();
        bullets = new Array<>();
        isGameInBossStage = false;

        addTreeMonsters();

        timeElapsedFromLastTentacleSpawn = 0;
        timeElapsedFromLastEyebatSpawn = 0;

        isPlayerAutoAiming = false;
    }

    public float getElapsedTimeInSeconds() {
        return elapsedTimeInSeconds;
    }

    public void setElapsedTimeInSeconds(float elapsedTimeInSeconds) {
        this.elapsedTimeInSeconds = elapsedTimeInSeconds;
    }

    public Array<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(Array<Enemy> enemies) {
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

    public Array<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(Array<Bullet> bullets) {
        this.bullets = bullets;
    }

    public float getTimeElapsedFromLastTentacleSpawn() {
        return timeElapsedFromLastTentacleSpawn;
    }

    public void setTimeElapsedFromLastTentacleSpawn(float timeElapsedFromLastTentacleSpawn) {
        this.timeElapsedFromLastTentacleSpawn = timeElapsedFromLastTentacleSpawn;
    }

    public float getTimeElapsedFromLastEyebatSpawn() {
        return timeElapsedFromLastEyebatSpawn;
    }

    public void setTimeElapsedFromLastEyebatSpawn(float timeElapsedFromLastEyebatSpawn) {
        this.timeElapsedFromLastEyebatSpawn = timeElapsedFromLastEyebatSpawn;
    }

    public boolean isPlayerAutoAiming() {
        return isPlayerAutoAiming;
    }

    public void togglePlayerAutoAiming() {
        isPlayerAutoAiming = !isPlayerAutoAiming;
    }
}
