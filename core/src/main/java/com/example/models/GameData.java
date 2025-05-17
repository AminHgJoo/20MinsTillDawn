package com.example.models;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.example.models.enums.types.EnemyTypes;
import com.example.views.GameMenu;

public class GameData {
    private Player player;

    private int gameEndTimeInMins;

    private float elapsedTimeInSeconds;

    private boolean isGameInBossStage;
    private transient Texture safeZoneOverlay;
    private Rectangle safeZone;
    private Vector2 shrinkingVelocity;
    private Enemy boss;

    private Array<Enemy> enemies;
    private Array<Bullet> bullets;

    private float timeElapsedFromLastTentacleSpawn;
    private float timeElapsedFromLastEyebatSpawn;

    private transient boolean isPlayerAutoAiming;

    private transient Array<ExplosionFXHelper> explosionFX;
    private Array<DroppedXpHelper> droppedXp;

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
        for (DroppedXpHelper helper : droppedXp) {
            helper.loadTransientObjects();
        }
        player.loadAnimations();
        isPlayerAutoAiming = false;
        explosionFX = new Array<>();

        if (boss != null) {
            boss.loadAnimation();
        }

        loadSafeZone();
    }

    private void loadSafeZone() {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);

        pixmap.setColor(1f, 0.65f, 0f, 0.15f);
        pixmap.fill();

        safeZoneOverlay = new Texture(pixmap);
        pixmap.dispose();
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
        explosionFX = new Array<>();
        droppedXp = new Array<>();

        isGameInBossStage = false;

        addTreeMonsters();

        timeElapsedFromLastTentacleSpawn = 0;
        timeElapsedFromLastEyebatSpawn = 0;

        isPlayerAutoAiming = false;

        boss = null;

        //It will take 60 seconds until the screen is completely consumed.
        shrinkingVelocity = new Vector2(GameMenu.SCREEN_WIDTH / 60f, GameMenu.SCREEN_HEIGHT / 60f);

        safeZone = new Rectangle(0, 0, GameMenu.SCREEN_WIDTH * 2, GameMenu.SCREEN_HEIGHT * 2);

        loadSafeZone();
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

    public void setPlayerAutoAiming(boolean isPlayerAutoAiming) {
        this.isPlayerAutoAiming = isPlayerAutoAiming;
    }

    public Array<ExplosionFXHelper> getExplosionFX() {
        return explosionFX;
    }

    public void setExplosionFX(Array<ExplosionFXHelper> explosionFX) {
        this.explosionFX = explosionFX;
    }

    public Array<DroppedXpHelper> getDroppedXp() {
        return droppedXp;
    }

    public void setDroppedXp(Array<DroppedXpHelper> droppedXp) {
        this.droppedXp = droppedXp;
    }

    public Enemy getBoss() {
        return boss;
    }

    public void setBoss(Enemy boss) {
        this.boss = boss;
    }

    public Vector2 getShrinkingVelocity() {
        return shrinkingVelocity;
    }

    public void setShrinkingVelocity(Vector2 shrinkingVelocity) {
        this.shrinkingVelocity = shrinkingVelocity;
    }

    public Texture getSafeZoneOverlay() {
        return safeZoneOverlay;
    }

    public void setSafeZoneOverlay(Texture safeZoneOverlay) {
        this.safeZoneOverlay = safeZoneOverlay;
    }

    public Rectangle getSafeZone() {
        return safeZone;
    }

    public void setSafeZone(Rectangle safeZone) {
        this.safeZone = safeZone;
    }
}
