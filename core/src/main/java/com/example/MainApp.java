package com.example;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.example.models.AppData;
import com.example.models.User;
import com.example.views.LauncherMenu;

public class MainApp extends Game {
    public Music music = null;

    @Override
    public void create() {
        AppData.initializeAssets();
        AppData.setMainApp(this);
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
        if (music != null) {
            music.dispose();
        }
        screen.dispose();
        for (String key : AppData.getProfileAssets().keySet()) {
            Texture asset = AppData.getProfileAssets().get(key);
            asset.dispose();
        }
    }
}
