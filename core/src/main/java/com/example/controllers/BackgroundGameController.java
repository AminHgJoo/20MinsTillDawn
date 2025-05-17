package com.example.controllers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.example.models.Bullet;
import com.example.models.GameData;
import com.example.utilities.CursorManager;
import com.example.utilities.CursorUtil;
import com.example.views.GameMenu;

public class BackgroundGameController {
    public static void handleGameTimer(GameData gameData, float delta) {
        gameData.setElapsedTimeInSeconds(gameData.getElapsedTimeInSeconds() + delta);
    }

    public static void collectStrayBullets(GameData gameData) {
        Rectangle mapBounds = new Rectangle(0, 0, GameMenu.SCREEN_WIDTH * 2, GameMenu.SCREEN_HEIGHT * 2);

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

    public static void handleAutoAimCursor(GameData gameData) {
        if (!gameData.isPlayerAutoAiming()) {
            return;
        }


    }
}
