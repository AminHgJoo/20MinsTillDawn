package com.example;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.example.views.MainMenu;

import java.util.HashMap;

public class MainApp extends Game {
    public final HashMap<String, Texture> assets = new HashMap<>();

    @Override
    public void create() {
        //initialize assets and load game.
        this.setScreen(new MainMenu(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        screen.dispose();
    }
}
