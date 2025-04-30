package com.example;

import com.badlogic.gdx.Game;
import com.example.views.MainMenu;

public class MainApp extends Game {

    @Override
    public void create() {
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
