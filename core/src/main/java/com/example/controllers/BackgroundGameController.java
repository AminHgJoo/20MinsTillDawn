package com.example.controllers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.example.MainApp;
import com.example.models.*;
import com.example.views.GameMenu;
import com.example.views.LevelUpMenu;

import static com.example.views.GameMenu.SCREEN_HEIGHT;
import static com.example.views.GameMenu.SCREEN_WIDTH;

public class BackgroundGameController {
    public static void handleGameTimer(GameData gameData, float delta) {
        gameData.setElapsedTimeInSeconds(gameData.getElapsedTimeInSeconds() + delta);
    }

    public static void collectStrayBullets(GameData gameData) {
        Rectangle mapBounds = new Rectangle(0, 0, SCREEN_WIDTH * 2, SCREEN_HEIGHT * 2);

        for (int i = gameData.getBullets().size - 1; i >= 0; i--) {
            if (!gameData.getBullets().get(i).getSprite().getBoundingRectangle().overlaps(mapBounds)) {
                gameData.getBullets().removeIndex(i);
            }
        }
    }

    public static void handleBulletMovement(GameData gameData, float delta) {
        for (Bullet bullet : gameData.getBullets()) {
            bullet.getPosition().set(bullet.getPosition().x + bullet.getVelocity().x * delta, bullet.getPosition().y + bullet.getVelocity().y * delta);
            bullet.getSprite().setPosition(bullet.getPosition().x, bullet.getPosition().y);
        }
    }

    public static void drawBullets(GameData gameData, SpriteBatch batch) {
        for (Bullet bullet : gameData.getBullets()) {
            bullet.getSprite().draw(batch);
        }
    }

    public static void handleExplosionFXUpdates(GameData gameData, float delta) {
        Array<ExplosionFXHelper> array = gameData.getExplosionFX();

        for (int i = array.size - 1; i >= 0; i--) {
            ExplosionFXHelper explosion = array.get(i);

            if (explosion.getAnimation().isAnimationFinished(explosion.getAnimationTimer())) {
                array.removeIndex(i);

                if (explosion.isEnemyCorpse()) {
                    gameData.getDroppedXp().add(new DroppedXpHelper(explosion.getPosition().cpy()));
                }
            } else {
                explosion.addTime(delta);
            }
        }
    }

    public static void animateExplosionFX(GameData gameData, SpriteBatch batch) {

        final float scaleFactor = 1.5f;

        for (ExplosionFXHelper explosion : gameData.getExplosionFX()) {
            TextureRegion currentFrame = explosion.getAnimation().getKeyFrame(explosion.getAnimationTimer());

            float x = explosion.getPosition().x;
            float y = explosion.getPosition().y;

            batch.draw(currentFrame
                , x - currentFrame.getRegionWidth() * scaleFactor / 2
                , y - currentFrame.getRegionHeight() * scaleFactor / 2
                , currentFrame.getRegionWidth() * scaleFactor
                , currentFrame.getRegionHeight() * scaleFactor);
        }
    }

    public static void drawCorpses(GameData gameData, SpriteBatch batch) {
        for (DroppedXpHelper droppedXp : gameData.getDroppedXp()) {
            droppedXp.getSprite().draw(batch);
        }
    }

    public static void handleXpPickups(GameData gameData) {
        Player player = gameData.getPlayer();

        for (int i = gameData.getDroppedXp().size - 1; i >= 0; i--) {
            DroppedXpHelper droppedXp = gameData.getDroppedXp().get(i);

            if (droppedXp.getSprite().getBoundingRectangle().overlaps(player.getRectangle())) {
                gameData.getDroppedXp().removeIndex(i);
                player.addXp(3);
            }
        }
    }

    public static void checkForLevelUp(GameData gameData) {
        Player player = gameData.getPlayer();

        if (player.getXp() >= player.howMuchXpToNextLevel()) {
            player.levelUp();
            MainApp mainApp = AppData.getMainApp();
            GameMenu gameMenu = (GameMenu) mainApp.getScreen();
            mainApp.setScreen(new LevelUpMenu(mainApp, gameData, gameMenu));
        }
    }

    public static void handleBuffExpiration(GameData gameData, float delta) {
        Player player = gameData.getPlayer();

        for (int i = player.getActiveAbilities().size - 1; i >= 0; i--) {
            ActiveAbility activeAbility = player.getActiveAbilities().get(i);

            if (activeAbility.getDuration() <= 0) {
                player.getActiveAbilities().removeIndex(i);
                activeAbility.getType().reverseEffect.execute(gameData);
            } else {
                activeAbility.decreaseDuration(delta);
            }
        }
    }

    public static void handleBossSafeZone(GameData gameData, SpriteBatch batch, float delta) {
        if (!gameData.isGameInBossStage()) {
            return;
        }

        Rectangle zone = gameData.getSafeZone();
        zone.setWidth(Math.max(0, zone.getWidth() - gameData.getShrinkingVelocity().x * 2 * delta));
        zone.setHeight(Math.max(0, zone.getHeight() - gameData.getShrinkingVelocity().y * 2 * delta));
        zone.setCenter(SCREEN_WIDTH, SCREEN_HEIGHT);

        batch.begin();
        batch.draw(gameData.getSafeZoneOverlay(), zone.x, zone.y, zone.width, zone.height);
        batch.end();

        Player player = gameData.getPlayer();

        if (player.isInvulnerable()) {
            return;
        }

        if (!zone.contains(player.getPosition())) {

            player.setHP(player.getHP() - 1);
            player.setInvulnerable(true);
            player.setInvulnerabilityTimer(0);
        }
    }
}
