package com.example;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.example.models.AppData;
import com.example.models.User;
import com.example.views.LauncherMenu;

public class MainApp extends Game {
    @Override
    public void create() {
        AppData.initializeAssets();
        User.loadUsersFromDB();
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
        for (String key : AppData.getAssets().keySet()) {
            Texture asset = AppData.getAssets().get(key);
            asset.dispose();
        }
    }
}
