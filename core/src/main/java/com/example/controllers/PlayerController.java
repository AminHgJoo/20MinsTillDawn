package com.example.controllers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.example.models.Player;

public class PlayerController {
    public final static float scaleFactor = 4;

    public static void handlePlayerRenderingUpdates(float delta, Player player) {
        TextureRegion currentFrame;

        if (player.getSpeed().isZero()) {
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

        player.getPosition().add(player.getSpeed().x * delta, player.getSpeed().y * delta);

        player.getRectangle().set(player.getPosition().x - (float) currentFrame.getRegionWidth() * scaleFactor / 2
            , player.getPosition().y - (float) currentFrame.getRegionHeight() * scaleFactor / 2
            , currentFrame.getRegionWidth() * scaleFactor
            , currentFrame.getRegionHeight() * scaleFactor);
    }

    public static void handlePlayerInput(int keycode) {

    }

    public static void animatePlayer(Player player, SpriteBatch batch) {

        TextureRegion currentFrame;

        if (player.isIdle()) {
            currentFrame = player.getIdleAnimation().getKeyFrame(player.getStateTimeIdle(), true);
        } else {
            currentFrame = player.getWalkingAnimation().getKeyFrame(player.getStateTimeWalking(), true);
        }

        int reversalFactor = player.getSpeed().x < 0 ? -1 : 1;

        batch.draw(currentFrame
            , player.getPosition().x - (float) currentFrame.getRegionWidth() * scaleFactor * reversalFactor / 2
            , player.getPosition().y - (float) currentFrame.getRegionHeight() * scaleFactor / 2
            , currentFrame.getRegionWidth() * scaleFactor * reversalFactor
            , currentFrame.getRegionHeight() * scaleFactor);
    }
}
