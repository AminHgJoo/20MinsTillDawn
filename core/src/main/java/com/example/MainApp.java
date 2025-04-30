package com.example;

import com.badlogic.gdx.Game;
import com.example.models.AppData;
import com.example.views.LauncherMenu;

public class MainApp extends Game {
    @Override
    public void create() {
        AppData.initializeAssets();
        AppData.setCurrentScreen(new LauncherMenu(this));
        this.setScreen(AppData.getCurrentScreen());
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
