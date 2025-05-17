package com.example.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.example.models.*;
import com.example.models.enums.types.BulletConstants;
import com.example.models.enums.types.EnemyTypes;
import com.example.utilities.CursorManager;
import com.example.utilities.CursorUtil;
import com.example.utilities.VectorUtil;
import com.example.views.GameMenu;

import java.util.HashMap;

public class PlayerController {
    public final static float scaleFactor = 2;
    public final static float zoomFactor = 2;
    public static GameData gameData;

    public static void handlePlayerRenderingUpdates(float delta, Player player) {
        TextureRegion currentFrame;

        if (player.getVelocity().isZero()) {
            player.setIdle(true);
            player.setStateTimeWalking(0);
            currentFrame = player.getIdleAnimation().getKeyFrame(player.getStateTimeIdle(), true);
        } else {
            player.setIdle(false);
            player.setStateTimeIdle(0);
            currentFrame = player.getWalkingAnimation().getKeyFrame(player.getStateTimeWalking(), true);
        }

        if (player.isIdle()) {
            player.setStateTimeIdle(delta + player.getStateTimeIdle());
        } else {
            player.setStateTimeWalking(delta + player.getStateTimeWalking());
        }

        player.getPosition().set(
            MathUtils.clamp(player.getVelocity().x * delta + player.getPosition().x
                , currentFrame.getRegionWidth() * scaleFactor / 2
                , GameMenu.SCREEN_WIDTH * zoomFactor - currentFrame.getRegionWidth() * scaleFactor / 2)
            , MathUtils.clamp(player.getVelocity().y * delta + player.getPosition().y
                , currentFrame.getRegionHeight() * scaleFactor / 2
                , GameMenu.SCREEN_HEIGHT * zoomFactor - currentFrame.getRegionHeight() * scaleFactor / 2));

        player.getRectangle().set(player.getPosition().x - (float) currentFrame.getRegionWidth() * scaleFactor / 2
            , player.getPosition().y - (float) currentFrame.getRegionHeight() * scaleFactor / 2
            , currentFrame.getRegionWidth() * scaleFactor
            , currentFrame.getRegionHeight() * scaleFactor);
    }

    public static void handleInputKeyDown(int keycode, Player player) {
        HashMap<String, Integer> map = AppData.getCurrentUserSettings().keyBinds;

        if (keycode == map.get("upKey")) {
            player.getVelocity().y = GameMenu.baseEntitySpeed * player.getHeroSpeedFactor();
        } else if (keycode == map.get("downKey")) {
            player.getVelocity().y = -GameMenu.baseEntitySpeed * player.getHeroSpeedFactor();
        }

        if (keycode == map.get("leftKey")) {
            player.getVelocity().x = -GameMenu.baseEntitySpeed * player.getHeroSpeedFactor();
        } else if (keycode == map.get("rightKey")) {
            player.getVelocity().x = GameMenu.baseEntitySpeed * player.getHeroSpeedFactor();
        }

        if (Gdx.input.isKeyJustPressed(keycode) && keycode == map.get("pauseKey")) {
            GameMenu gameMenu = (GameMenu) AppData.getCurrentScreen();
            AppData.getMainApp().setScreen(gameMenu.getPauseMenu());
            CursorManager.getInstance().setCursorToHover();
        } else if (keycode == map.get("shootKey")) {
            player.setShooting(true);
        } else if (keycode == map.get("reloadKey")) {
            player.setReloading(true);
        } else if (keycode == map.get("aimbotKey")) {
            gameData.togglePlayerAutoAiming();
            if (gameData.isPlayerAutoAiming()) {
                CursorUtil.hideCursor();
            } else {
                CursorManager.getInstance().setCursorToHover();
            }
        } else if (keycode == map.get("addHpCheat")) {
            player.setHP(player.getMaxHP());
        } else if (keycode == map.get("reduceTimeCheat")) {
            gameData.setGameEndTimeInMins(gameData.getGameEndTimeInMins() - 1);
        } else if (keycode == map.get("goToBossCheat")) {
            if (gameData.getElapsedTimeInSeconds() < gameData.getGameEndTimeInMins() * 60 * 0.5) {
                gameData.setElapsedTimeInSeconds((float) (gameData.getGameEndTimeInMins() * 60 * 0.5));
            }
        } else if (keycode == map.get("invincibilityCheat")) {
            gameData.getPlayer().setInvulnerabilityTimer(0);
            gameData.getPlayer().setInvulnerable(true);
        } else if (keycode == map.get("levelUpCheat")) {
            gameData.getPlayer().setXp(gameData.getPlayer().howMuchXpToNextLevel());
        }
    }

    public static void handleInputKeyUp(int keycode, Player player) {
        HashMap<String, Integer> map = AppData.getCurrentUserSettings().keyBinds;

        if (keycode == map.get("upKey") || keycode == map.get("downKey")) {
            player.getVelocity().y = 0;
        }

        if (keycode == map.get("leftKey") || keycode == map.get("rightKey")) {
            player.getVelocity().x = 0;
        }

        if (keycode == map.get("shootKey")) {
            player.setShooting(false);
            player.setShootingTimer(0);
        }
    }

    public static void animatePlayer(Player player, SpriteBatch batch) {

        TextureRegion currentFrame;

        if (player.isIdle()) {
            currentFrame = player.getIdleAnimation().getKeyFrame(player.getStateTimeIdle(), true);
        } else {
            currentFrame = player.getWalkingAnimation().getKeyFrame(player.getStateTimeWalking(), true);
        }

        int reversalFactor = player.getVelocity().x < 0 ? -1 : 1;

        batch.draw(currentFrame
            , player.getPosition().x - (float) currentFrame.getRegionWidth() * scaleFactor * reversalFactor / 2
            , player.getPosition().y - (float) currentFrame.getRegionHeight() * scaleFactor / 2
            , currentFrame.getRegionWidth() * scaleFactor * reversalFactor
            , currentFrame.getRegionHeight() * scaleFactor);
    }

    public static boolean isPlayerDead(Player player) {
        return player.getHP() <= 0;
    }

    public static boolean hasPlayerWon(GameData gameData) {
        return gameData.getGameEndTimeInMins() * 60 <= gameData.getElapsedTimeInSeconds();
    }

    public static void handlePlayerReloading(GameData gameData, float delta) {
        if (!gameData.getPlayer().isReloading()) {
            return;
        }

        if (gameData.getPlayer().getReloadingTimer() < gameData.getPlayer().getWeapon().getReloadTime()) {
            gameData.getPlayer().setReloadingTimer(gameData.getPlayer().getReloadingTimer() + delta);
        } else {
            gameData.getPlayer().setReloadingTimer(0);
            gameData.getPlayer().setReloading(false);
            gameData.getPlayer().getWeapon().setBulletsRemaining(gameData.getPlayer().getWeapon().getMaxMagSize());
        }
    }

    public static void handlePlayerShooting(GameData gameData, OrthographicCamera camera, float delta) {
        final float shootingCooldown = 0.2f;

        Player player = gameData.getPlayer();
        Weapon weapon = player.getWeapon();

        if (!player.isShooting() || player.isReloading() || gameData.isPlayerAutoAiming()) {
            return;
        }

        if (player.getShootingTimer() >= shootingCooldown) {
            player.setShootingTimer(0);
        } else {
            player.setShootingTimer(player.getShootingTimer() + delta);
            return;
        }

        if (weapon.getBulletsRemaining() > 0) {
            Vector3 rawMousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(rawMousePos);

            Vector2 projectedClickPos = new Vector2(rawMousePos.x, rawMousePos.y);
            projectedClickPos.mulAdd(gameData.getPlayer().getPosition(), -1);

            float mag = BulletConstants.PLAYER_PROJECTILE.speedFactor * GameMenu.baseEntitySpeed;
            Vector2 velocity = VectorUtil.createPolarVector(mag, projectedClickPos.angleRad());

            for (int i = 0; i < player.getWeapon().getProjectileAmount(); i++) {
                Bullet bullet = new Bullet(weapon.getDmg(), true, new Vector2(player.getPosition()), velocity);
                gameData.getBullets().add(bullet);
            }

            weapon.setBulletsRemaining(weapon.getBulletsRemaining() - 1);

            if (AppData.getCurrentUserSettings().isAutoReload() && weapon.getBulletsRemaining() == 0) {
                player.setReloading(true);
                player.setReloadingTimer(0);
            }
        }
    }

    public static void handleAimbot(GameData gameData, float delta, SpriteBatch batch) {
        if (!gameData.isPlayerAutoAiming() || gameData.getElapsedTimeInSeconds() < 30) {
            return;
        }

        Enemy closestEnemy = gameData.getEnemies().get(gameData.getEnemies().size - 1);
        Vector2 playerPos = gameData.getPlayer().getPosition();

        for (Enemy enemy : gameData.getEnemies()) {
            if (enemy.getType() == EnemyTypes.TREE_MONSTER) {
                continue;
            }
            float newDist = playerPos.dst(enemy.getPosition());

            if (newDist < playerPos.dst(closestEnemy.getPosition())) {
                closestEnemy = enemy;
            }
        }

        if (closestEnemy == null) {
            return;
        }

        Texture reticleTexture = AppData.getWeaponAssets().get("Cursor");
        float drawX = closestEnemy.getPosition().x - reticleTexture.getWidth() / 2f;
        float drawY = closestEnemy.getPosition().y - reticleTexture.getHeight() / 2f;
        batch.draw(reticleTexture, drawX, drawY);

        final float shootingCooldown = 0.2f;

        Player player = gameData.getPlayer();
        Weapon weapon = player.getWeapon();

        if (!player.isShooting() || player.isReloading()) {
            return;
        }

        if (player.getShootingTimer() >= shootingCooldown) {
            player.setShootingTimer(0);
        } else {
            player.setShootingTimer(player.getShootingTimer() + delta);
            return;
        }

        if (weapon.getBulletsRemaining() > 0) {
            Vector2 targetRelToPlayer = new Vector2(closestEnemy.getPosition());
            targetRelToPlayer.mulAdd(gameData.getPlayer().getPosition(), -1);

            float mag = BulletConstants.PLAYER_PROJECTILE.speedFactor * GameMenu.baseEntitySpeed;
            Vector2 velocity = VectorUtil.createPolarVector(mag, targetRelToPlayer.angleRad());

            for (int i = 0; i < player.getWeapon().getProjectileAmount(); i++) {
                Bullet bullet = new Bullet(weapon.getDmg(), true, new Vector2(player.getPosition()), velocity);
                gameData.getBullets().add(bullet);
            }

            weapon.setBulletsRemaining(weapon.getBulletsRemaining() - 1);

            if (AppData.getCurrentUserSettings().isAutoReload() && weapon.getBulletsRemaining() == 0) {
                player.setReloading(true);
                player.setReloadingTimer(0);
            }
        }
    }
}
