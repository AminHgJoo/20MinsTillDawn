package com.example.controllers;

import com.example.models.GameData;

public class BackgroundGameController {
    public static void handleGameTimer(GameData gameData, float delta) {
        gameData.setElapsedTimeInSeconds(gameData.getElapsedTimeInSeconds() + delta);
    }
}
