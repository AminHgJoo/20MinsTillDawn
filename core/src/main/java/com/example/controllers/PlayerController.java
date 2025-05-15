package com.example.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.example.models.AppData;
import com.example.models.Player;
import com.example.views.GameMenu;

import java.util.HashMap;

public class PlayerController {
    public final static float scaleFactor = 2;
    public final static float zoomFactor = 2;

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
            player.getVelocity().y = GameMenu.basePlayerSpeed * player.getHeroSpeedFactor();
        } else if (keycode == map.get("downKey")) {
            player.getVelocity().y = -GameMenu.basePlayerSpeed * player.getHeroSpeedFactor();
        }

        if (keycode == map.get("leftKey")) {
            player.getVelocity().x = -GameMenu.basePlayerSpeed * player.getHeroSpeedFactor();
        } else if (keycode == map.get("rightKey")) {
            player.getVelocity().x = GameMenu.basePlayerSpeed * player.getHeroSpeedFactor();
        }

        if (Gdx.input.isKeyJustPressed(keycode) && keycode == map.get("pauseKey")) {
            GameMenu gameMenu = (GameMenu) AppData.getCurrentScreen();
            AppData.getMainApp().setScreen(gameMenu.getPauseMenu());
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
}
