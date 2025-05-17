package com.example.controllers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.example.models.*;
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
            if (player.getRectangle().overlaps(enemy.getCollisionRectangle())
                && enemy.getType() != EnemyTypes.ELDER_BOSS) {
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
                handleElderAI(enemy, delta);
                handleChasingAI(enemy, player);
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

    public static void handleElderAI(Enemy boss, float delta) {

        if (boss.getBehaviourTimer() <= 5) {
            boss.setSpeedMagnitude(0);
            boss.setBehaviourTimer(boss.getBehaviourTimer() + delta);
        } else if (boss.getBehaviourTimer() <= 6) {
            boss.setSpeedMagnitude(boss.getType().speedFactor * GameMenu.baseEntitySpeed);
            boss.setBehaviourTimer(boss.getBehaviourTimer() + delta);
        } else {
            boss.setSpeedMagnitude(0);
            boss.setBehaviourTimer(0);
        }
    }

    public static void handleEnemiesDamagingPlayer(GameData gameData, float delta) {
        Player player = gameData.getPlayer();

        if (player.isInvulnerable()) {
            if (player.getInvulnerabilityTimer() >= 1) {
                player.setInvulnerabilityTimer(0);
                player.setInvulnerable(false);
            } else {
                player.setInvulnerabilityTimer(player.getInvulnerabilityTimer() + delta);
            }
            return;
        }

        for (Enemy enemy : gameData.getEnemies()) {
            if (enemy.getCollisionRectangle().overlaps(player.getRectangle())) {
                player.setHP(player.getHP() - 1);
                player.setInvulnerable(true);
                player.setInvulnerabilityTimer(0);
                return;
            }
        }

        Array<Bullet> bullets = gameData.getBullets();

        for (int i = bullets.size - 1; i >= 0; i--) {
            Bullet bullet = bullets.get(i);

            if (bullet.getSprite().getBoundingRectangle().overlaps(player.getRectangle())
                && !bullet.isPlayerProjectile()) {
                bullets.removeIndex(i);
                player.setHP(player.getHP() - 1);
                player.setInvulnerable(true);
                player.setInvulnerabilityTimer(0);
                gameData.getExplosionFX().add(new ExplosionFXHelper(player.getPosition().cpy(), false));
                return;
            }
        }
    }

    public static void handleEnemiesGettingDamaged(GameData gameData) {
        Array<Enemy> enemies = gameData.getEnemies();
        Array<Bullet> bullets = gameData.getBullets();

        for (int i = enemies.size - 1; i >= 0; i--) {
            for (int j = bullets.size - 1; j >= 0; j--) {
                Enemy enemy = enemies.get(i);
                Bullet bullet = bullets.get(j);

                if (bullet.getSprite().getBoundingRectangle().overlaps(enemy.getCollisionRectangle())
                    && bullet.isPlayerProjectile()) {
                    bullets.removeIndex(j);
                    enemy.setHP(enemy.getHP() - bullet.getDmg());
                    //inertial push-back effect
                    enemy.getVelocity().scl(-20);

                    if (enemy.getHP() <= 0) {
                        enemies.removeIndex(i);
                        gameData.getPlayer().killsPlusPlus();
                        gameData.getExplosionFX().add(new ExplosionFXHelper(enemy.getPosition().cpy(), true));
                        break;
                    }
                }
            }
        }
    }

    public static void spawnEnemies(GameData gameData, float delta) {
        spawnTentacles(gameData, delta);
        spawnEyebats(gameData, delta);
        startBossBattle(gameData);
    }

    private static boolean isBossTimeNow(float totalTime, float currentTime) {
        return currentTime >= totalTime * 0.5f;
    }

    private static void startBossBattle(GameData gameData) {
        if (!gameData.isGameInBossStage()
            && gameData.getBoss() == null
            && isBossTimeNow(gameData.getGameEndTimeInMins() * 60, gameData.getElapsedTimeInSeconds())) {

            Enemy boss = new Enemy(new Vector2(GameMenu.SCREEN_WIDTH, GameMenu.SCREEN_HEIGHT), EnemyTypes.ELDER_BOSS);
            gameData.getEnemies().add(boss);
            gameData.setBoss(boss);
            gameData.setGameInBossStage(true);
        }
    }

    private static void spawnEyebats(GameData gameData, float delta) {
        if (!(gameData.getElapsedTimeInSeconds() >= gameData.getGameEndTimeInMins() * 60 * 0.25)) {
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

    public static void handleBossBattleOver(GameData gameData) {
        if (gameData.getBoss() != null) {
            if (gameData.getBoss().getHP() <= 0) {
                gameData.setGameInBossStage(false);
            }
        }
    }
}
