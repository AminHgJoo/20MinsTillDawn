package com.example.views;

import com.badlogic.gdx.Gdx;
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

public class MainMenu implements Screen {
    final MainApp mainApp;

    private Stage stage;
    private Skin skin;
    private Texture backgroundImage;

    public MainMenu(final MainApp app) {
        mainApp = app;

        stage = new Stage(new ScreenViewport());

        skin = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));

        backgroundImage = new Texture("game_cover.jpg");
        Image background = new Image(backgroundImage);
        background.setFillParent(true);
        stage.addActor(background);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label title = new Label("Developed By @AminHgJoo", skin);
        title.setFontScale(2);
        title.setColor(Color.CORAL);
        title.setPosition(10, 10);
        stage.addActor(title);

        TextButton loginButton = new TextButton("Login", skin);
        loginButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });

        TextButton registerButton = new TextButton("Register", skin);
        registerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });

        table.center();
        table.padTop(50);
        table.add(loginButton).width(300).height(60).pad(10);
        table.row();
        table.add(registerButton).width(300).height(60).pad(10);
    }


    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
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
        skin.dispose();
        backgroundImage.dispose();
    }
}
