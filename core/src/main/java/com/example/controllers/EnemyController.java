package com.example.controllers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.example.models.Bullet;
import com.example.models.Enemy;
import com.example.models.GameData;
import com.example.models.Player;
import com.example.models.enums.types.BulletConstants;
import com.example.models.enums.types.EnemyTypes;
import com.example.utilities.VectorUtil;
import com.example.views.GameMenu;

public class EnemyController {
    public final static float scaleFactor = 2;
    public final static float zoomFactor = 2;

    public static void handleEnemyRenderingUpdates(float delta, Array<Enemy> enemies) {
        for (Enemy enemy : enemies) {
            TextureRegion currentFrame = enemy.getAnimation().getKeyFrame(enemy.getAnimationTimer(), true);
            enemy.setAnimationTimer(enemy.getAnimationTimer() + delta);

            enemy.getPosition().set(
                MathUtils.clamp(enemy.getVelocity().x * delta + enemy.getPosition().x
                    , currentFrame.getRegionHeight() * scaleFactor / 2
                    , GameMenu.SCREEN_WIDTH * zoomFactor - currentFrame.getRegionWidth() * scaleFactor / 2)
                , MathUtils.clamp(enemy.getVelocity().y * delta + enemy.getPosition().y
                    , currentFrame.getRegionHeight() * scaleFactor / 2
                    , GameMenu.SCREEN_HEIGHT * zoomFactor - currentFrame.getRegionHeight() * scaleFactor / 2));

            enemy.getCollisionRectangle().set(enemy.getPosition().x - (float) currentFrame.getRegionWidth() * scaleFactor / 2
                , enemy.getPosition().y - (float) currentFrame.getRegionHeight() * scaleFactor / 2
                , currentFrame.getRegionWidth() * scaleFactor
                , currentFrame.getRegionHeight() * scaleFactor);
        }
    }

    public static void animateEnemies(Array<Enemy> enemies, SpriteBatch batch) {
        for (Enemy enemy : enemies) {

            TextureRegion currentFrame = enemy.getAnimation().getKeyFrame(enemy.getAnimationTimer(), true);

            int reversalFactor = enemy.getVelocity().x < 0 ? -1 : 1;

            batch.draw(currentFrame
                , enemy.getPosition().x - (float) currentFrame.getRegionWidth() * scaleFactor * reversalFactor / 2
                , enemy.getPosition().y - (float) currentFrame.getRegionHeight() * scaleFactor / 2
                , currentFrame.getRegionWidth() * scaleFactor * reversalFactor
                , currentFrame.getRegionHeight() * scaleFactor);
        }
    }

    public static void handleEnemyAI(GameData gameData, float delta) {
        Player player = gameData.getPlayer();

        for (Enemy enemy : gameData.getEnemies()) {
            if (player.getRectangle().overlaps(enemy.getCollisionRectangle())) {
                continue;
            }

            if (enemy.getType() == EnemyTypes.TREE_MONSTER) {
                continue;
            }

            if (enemy.getType() == EnemyTypes.TENTACLE_MONSTER) {
                handleChasingAI(enemy, player);
            }

            if (enemy.getType() == EnemyTypes.EYEBAT) {
                handleChasingAI(enemy, player);
                handleBatShootingAI(enemy, player, gameData, delta);
            }

            if (enemy.getType() == EnemyTypes.ELDER_BOSS) {
                //TODO: Boss battle.
            }
        }
    }

    public static void handleChasingAI(Enemy enemy, Player player) {
        Vector2 playerVecCopy = new Vector2(player.getPosition());
        playerVecCopy.mulAdd(enemy.getPosition(), -1);
        float angle = playerVecCopy.angleRad();
        enemy.getVelocity().set(VectorUtil.createPolarVector(enemy.getSpeedMagnitude(), angle));
    }

    public static void handleBatShootingAI(Enemy enemy, Player player, GameData gameData, float delta) {
        if (!(enemy.getBehaviourTimer() >= 3)) {
            enemy.setBehaviourTimer(enemy.getBehaviourTimer() + delta);
            return;
        }
        enemy.setBehaviourTimer(0);

        Vector2 playerVecCopy = new Vector2(player.getPosition());
        playerVecCopy.mulAdd(enemy.getPosition(), -1);
        float angle = playerVecCopy.angleRad();
        Vector2 bulletVelocity = VectorUtil.createPolarVector(
            BulletConstants.BAT_PROJECTILE.speedFactor * GameMenu.baseEntitySpeed, angle);
        gameData.getBullets().add(new Bullet(1, false, new Vector2(enemy.getPosition()), bulletVelocity));
    }

    public static void handleEnemiesDamagingPlayer(GameData gameData) {
        //TODO:
    }

    public static void handleEnemiesGettingDamaged(GameData gameData) {
        //TODO:

    }

    public static void spawnEnemies(GameData gameData, float delta) {
        spawnTentacles(gameData, delta);
        spawnEyebats(gameData, delta);
    }

    private static void spawnEyebats(GameData gameData, float delta) {
        if (!(gameData.getElapsedTimeInSeconds() >= gameData.getGameEndTimeInMins() * 60 / 4)) {
            return;
        }

        if (gameData.getTimeElapsedFromLastEyebatSpawn() >= 10) {
            gameData.setTimeElapsedFromLastEyebatSpawn(0);

            final int numOfBats = (int) ((gameData.getElapsedTimeInSeconds() * 4
                - gameData.getGameEndTimeInMins() * 60
                + 30) / 30);
            final int batSize = 200;

            for (int i = 0; i < numOfBats; i++) {
                int xPos = MathUtils.random(batSize, (int) GameMenu.SCREEN_WIDTH * 2 - batSize);
                int yPos = MathUtils.random(batSize, (int) GameMenu.SCREEN_HEIGHT * 2 - batSize);
                gameData.getEnemies().add(new Enemy(new Vector2(xPos, yPos), EnemyTypes.EYEBAT));
            }

        } else {
            gameData.setTimeElapsedFromLastEyebatSpawn(gameData.getTimeElapsedFromLastEyebatSpawn() + delta);
        }
    }

    private static void spawnTentacles(GameData gameData, float delta) {
        if (gameData.getTimeElapsedFromLastTentacleSpawn() >= 3) {
            gameData.setTimeElapsedFromLastTentacleSpawn(0);

            final int numOfTentacles = (int) (gameData.getElapsedTimeInSeconds() / 30);
            final int tentacleSize = 100;

            for (int i = 0; i < numOfTentacles; i++) {
                int xPos = MathUtils.random(tentacleSize, (int) GameMenu.SCREEN_WIDTH * 2 - tentacleSize);
                int yPos = MathUtils.random(tentacleSize, (int) GameMenu.SCREEN_HEIGHT * 2 - tentacleSize);
                gameData.getEnemies().add(new Enemy(new Vector2(xPos, yPos), EnemyTypes.TENTACLE_MONSTER));
            }
        } else {
            gameData.setTimeElapsedFromLastTentacleSpawn(gameData.getTimeElapsedFromLastTentacleSpawn() + delta);
        }
    }
}
