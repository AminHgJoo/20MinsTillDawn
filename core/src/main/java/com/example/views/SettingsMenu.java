package com.example.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.example.MainApp;
import com.example.models.AppData;
import com.example.models.UserSettings;
import com.example.models.enums.Translation;
import com.example.utilities.CursorManager;

public class SettingsMenu implements Screen {
    final MainApp mainApp;

    final private Texture backgroundTexture;
    final private Skin skin;
    private boolean hasLanguageChanged;

    private Stage stage;

    public SettingsMenu(final MainApp mainApp) {
        this.mainApp = mainApp;
        this.hasLanguageChanged = false;

        this.backgroundTexture = new Texture("settings_menu/background.png");

        this.skin = AppData.skin;

        initializeGUI();
    }

    private void initializeGUI() {
        stage = new Stage(new ScreenViewport());

        Image background = new Image(backgroundTexture);
        background.setFillParent(true);
        stage.addActor(background);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        TextButton changeLanguage = new TextButton(Translation.LANGUAGE_BUTTON_TEXT.translate(), skin);
        changeLanguage.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (AppData.getLang().compareToIgnoreCase("english") == 0) {
                    AppData.setLang("dutch");
                    AppData.getCurrentUser().getUserSettings().setLang("dutch");
                    hasLanguageChanged = true;
                } else if (AppData.getLang().compareToIgnoreCase("dutch") == 0) {
                    AppData.setLang("english");
                    AppData.getCurrentUser().getUserSettings().setLang("english");
                    hasLanguageChanged = true;
                }
            }
        });

        TextButton toggleSFX = new TextButton(Translation.TOGGLE_SFX.translate(), skin);
        toggleSFX.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                UserSettings settings = AppData.getCurrentUser().getUserSettings();
                settings.setPlaySFX(!settings.isPlaySFX());

                if (settings.isPlaySFX()) {
                    if (!mainApp.music.isPlaying()) {
                        mainApp.music.play();
                    }
                } else {
                    if (mainApp.music.isPlaying()) {
                        mainApp.music.stop();
                    }
                }
            }
        });

        TextButton toggleAutoReload = new TextButton(Translation.TOGGLE_AUTO_RELOAD.translate(), skin);
        toggleAutoReload.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                UserSettings settings = AppData.getCurrentUser().getUserSettings();
                settings.setAutoReload(!settings.isAutoReload());
            }
        });

        TextButton changeMusic = new TextButton(Translation.CHANGE_MUSIC.translate(), skin);
        changeMusic.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (mainApp.music.isPlaying()) {
                    mainApp.music.stop();
                }

                UserSettings settings = AppData.getCurrentUser().getUserSettings();
                String musicPath = settings.getMusicPath();

                if (musicPath.equals("sfx/music/Pretty Dungeon.wav")) {
                    settings.setMusicPath("sfx/music/Wasteland Combat.wav");
                } else if (musicPath.equals("sfx/music/Wasteland Combat.wav")) {
                    settings.setMusicPath("sfx/music/Pretty Dungeon.wav");
                }

                if (mainApp.music != null) {
                    mainApp.music.dispose();
                }

                mainApp.music = Gdx.audio.newMusic(Gdx.files.internal(settings.getMusicPath()));
                mainApp.music.setLooping(true);
                mainApp.music.setVolume(settings.getSoundVolume());

                if (settings.isPlaySFX()) {
                    mainApp.music.play();
                }
            }
        });

        Label sliderLabel = new Label(Translation.MUSIC_VOLUME.translate(), skin);
        sliderLabel.setColor(Color.RED);
        sliderLabel.setFontScale(1.5f);

        Slider slider = new Slider(0, 1, 0.05f, false, skin);
        slider.setValue(mainApp.music.getVolume());
        slider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                AppData.getCurrentUser().getUserSettings().setSoundVolume(slider.getValue());
                mainApp.music.setVolume(slider.getValue());
            }
        });

        TextButton goBack = new TextButton(Translation.GO_BACK.translate(), skin);
        goBack.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                AppData.getCurrentUser().saveSettingsToJson();
                mainApp.setScreen(new MainMenu(mainApp));
                AppData.setCurrentScreen(mainApp.getScreen());
                dispose();
            }
        });

        TextButton keyBinds = new TextButton(Translation.CONTROLS.translate(), skin);
        keyBinds.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainApp.setScreen(new KeybindsMenu(mainApp));
                AppData.setCurrentScreen(mainApp.getScreen());
                AppData.getCurrentUser().saveSettingsToJson();
                dispose();
            }
        });

        table.center();
        table.padTop(50);
        table.add(changeLanguage).width(700).height(60).pad(10).row();
        table.add(toggleSFX).width(700).height(50).pad(10).row();
        table.add(toggleAutoReload).width(700).height(50).pad(10).row();
        table.add(changeMusic).width(700).height(50).pad(10).row();
        table.add(sliderLabel).pad(10).row();
        table.add(slider).width(700).height(50).pad(10).row();
        table.add(keyBinds).width(700).height(50).pad(10).row();
        table.add(goBack).width(700).height(50).pad(10).row();


        InputMultiplexer inputMultiplexer = new InputMultiplexer(CursorManager.getInstance(), stage);

        Gdx.input.setInputProcessor(inputMultiplexer);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (hasLanguageChanged) {
            stage.dispose();
            initializeGUI();
            hasLanguageChanged = false;
        }
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        backgroundTexture.dispose();
    }
}
