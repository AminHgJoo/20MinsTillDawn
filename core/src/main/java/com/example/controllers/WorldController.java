package com.example.controllers;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.example.models.AppData;
import com.example.models.Player;

public class WorldController {
    public static void handleLightGradiant(Player player, SpriteBatch batch) {
        Texture light = AppData.getMapAssets().get("Light");

        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE);
        batch.draw(light, player.getPosition().x - (float) light.getWidth() / 2
            , player.getPosition().y - (float) light.getHeight() / 2
            , light.getWidth()
            , light.getHeight());
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }
}
