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
import com.example.models.enums.Translation;
import com.example.utilities.CursorManager;

public class LauncherMenu implements Screen {
    final private MainApp mainApp;

    final private Skin skin;
    final private Texture backgroundTexture;

    private boolean hasLanguageChanged;

    private Stage stage;

    public LauncherMenu(final MainApp app) {
        this.mainApp = app;
        this.hasLanguageChanged = false;

        this.skin = AppData.skin;
        this.backgroundTexture = new Texture("game_cover.jpg");

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

        Label title = new Label(Translation.LAUNCHER_GAME_DEV.translate(), skin);
        title.setFontScale(1.3f);
        title.setColor(Color.CYAN);
        title.setPosition(10, 10);
        stage.addActor(title);

        TextButton loginButton = new TextButton(Translation.LOGIN.translate(), skin);
        loginButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainApp.setScreen(new LoginMenu(mainApp));
                dispose();
            }
        });

        TextButton registerButton = new TextButton(Translation.REGISTER.translate(), skin);
        registerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainApp.setScreen(new RegisterMenu(mainApp));
                dispose();
            }
        });

        TextButton languageButton = new TextButton(Translation.LANGUAGE_BUTTON_TEXT.translate(), skin);
        languageButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (AppData.getLang().compareToIgnoreCase("english") == 0) {
                    AppData.setLang("dutch");
                    hasLanguageChanged = true;
                } else if (AppData.getLang().compareToIgnoreCase("dutch") == 0) {
                    AppData.setLang("english");
                    hasLanguageChanged = true;
                }
            }
        });

        table.center();
        table.padTop(50);
        table.add(loginButton).width(300).height(60).pad(10);
        table.row();
        table.add(registerButton).width(300).height(60).pad(10);
        table.row();
        table.add(languageButton).width(300).height(60).pad(10);

        InputMultiplexer inputMultiplexer = new InputMultiplexer(CursorManager.getInstance(), stage);

        Gdx.input.setInputProcessor(inputMultiplexer);
    }


    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
