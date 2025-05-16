package com.example;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.example.models.AppData;
import com.example.models.User;
import com.example.views.GameMenu;
import com.example.views.LauncherMenu;
import com.example.views.PauseMenu;

public class MainApp extends Game {
    public Music music = null;

    @Override
    public void create() {
        AppData.initializeAssets();
        AppData.setMainApp(this);
        User.loadUsersFromDB();
        this.setScreen(new LauncherMenu(this));
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
        for (String key : AppData.getProfileAssets().keySet()) {
            Texture asset = AppData.getProfileAssets().get(key);
            asset.dispose();
        }
        AppData.skin.dispose();
        if (this.screen instanceof GameMenu gameMenu) {
            gameMenu.getPauseMenu().dispose();
            gameMenu.dispose();
        } else if (this.screen instanceof PauseMenu pauseMenu) {
            pauseMenu.getGameMenu().dispose();
            pauseMenu.dispose();
        } else {
            screen.dispose();
        }
    }

    /**
     * Overridden to automatically set the screen field in AppData as well.
     *
     * @author AminHg
     */
    @Override
    public void setScreen(Screen screen) {
        super.setScreen(screen);
        AppData.setCurrentScreen(screen);
    }
}
