package com.example.controllers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.example.MainApp;
import com.example.models.GameData;
import com.example.views.GameEndMenu;
import com.example.views.GameMenu;
import com.example.views.PauseMenu;

public class CentralGameController {
    public static void backgroundUpdates(GameData gameData, float delta, OrthographicCamera camera) {
        BackgroundGameController.handleGameTimer(gameData, delta);
        BackgroundGameController.collectStrayBullets(gameData);
        BackgroundGameController.handleBulletMovement(gameData, delta);

        EnemyController.spawnEnemies(gameData, delta);
        EnemyController.handleEnemyRenderingUpdates(delta, gameData.getEnemies());
        PlayerController.handlePlayerRenderingUpdates(delta, gameData.getPlayer());
        BackgroundGameController.handleExplosionFXUpdates(gameData, delta);
        BackgroundGameController.handleXpPickups(gameData);
        PlayerController.handlePlayerShooting(gameData, camera, delta);
        PlayerController.handlePlayerReloading(gameData, delta);
    }

    public static void renderGraphics(GameData gameData, float delta, SpriteBatch batch, Texture backgroundTexture) {
        batch.begin();
        batch.draw(backgroundTexture, 0, 0);
        EnemyController.animateEnemies(gameData.getEnemies(), batch);
        PlayerController.animatePlayer(gameData.getPlayer(), batch);
        PlayerController.handleAimbot(gameData, delta, batch);
        BackgroundGameController.drawBullets(gameData, batch);
        BackgroundGameController.animateExplosionFX(gameData, batch);
        BackgroundGameController.drawCorpses(gameData, batch);
        batch.end();

        batch.begin();
        WorldController.handleLightGradiant(gameData.getPlayer(), batch);
        batch.end();

        BackgroundGameController.handleBossSafeZone(gameData, batch, delta);
    }

    public static void handleGameLogic(GameData gameData, float delta) {
        EnemyController.handleEnemyAI(gameData, delta);
        EnemyController.handleEnemiesDamagingPlayer(gameData, delta);
        EnemyController.handleEnemiesGettingDamaged(gameData);
    }

    public static void endingChecks(GameData gameData, float delta, MainApp mainApp, GameMenu gameMenu) {
        BackgroundGameController.checkForLevelUp(gameData);
        BackgroundGameController.handleBuffExpiration(gameData, delta);
        EnemyController.handleBossBattleOver(gameData);

        PauseMenu pauseMenu = gameMenu.getPauseMenu();

        if (PlayerController.isPlayerDead(gameData.getPlayer())) {
            mainApp.setScreen(new GameEndMenu(mainApp, gameData, false));
            pauseMenu.dispose();
            gameMenu.dispose();
        } else if (PlayerController.hasPlayerWon(gameData)) {
            mainApp.setScreen(new GameEndMenu(mainApp, gameData, true));
            pauseMenu.dispose();
            gameMenu.dispose();
        }
    }
}
