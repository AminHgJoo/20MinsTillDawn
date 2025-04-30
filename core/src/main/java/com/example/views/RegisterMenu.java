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
import com.example.models.AppData;
import com.example.models.enums.Languages;

public class RegisterMenu implements Screen {
    final private MainApp mainApp;

    final private Skin skin;
    final private Texture backgroundTexture;

    private Stage stage;

    public RegisterMenu(final MainApp mainApp) {
        this.mainApp = mainApp;

        this.skin = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));
        ;
        this.backgroundTexture = new Texture("register_menu/background.jpg");

        initializeGUI();
    }

    private void initializeGUI() {
        stage = new Stage(new ScreenViewport());

        Image background = new Image(backgroundTexture);
        background.setFillParent(true);
        stage.addActor(background);

        Label label = new Label(Languages.CHOOSE_A_USERNAME.inThisLang(AppData.getLang()), skin);
        label.setColor(Color.CORAL);
        label.setFontScale(1.5f);

        TextField usernameField = new TextField("", skin);
        usernameField.setMessageText(Languages.USERNAME.inThisLang(AppData.getLang()));

        TextField passwordField = new TextField("", skin);
        passwordField.setMessageText(Languages.PASSWORD.inThisLang(AppData.getLang()));

        TextField securityQuestionField = new TextField("", skin);
        securityQuestionField.setMessageText(Languages.SECURITY_QUESTION.inThisLang(AppData.getLang()));

        TextField securityAnswerField = new TextField("", skin);
        securityAnswerField.setMessageText(Languages.SECURITY_ANSWER.inThisLang(AppData.getLang()));

        TextButton confirmButton = new TextButton(Languages.CONFIRM.inThisLang(AppData.getLang()), skin);
        confirmButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //TODO: Call controller.
            }
        });

        TextButton playAsGuest = new TextButton(Languages.PLAY_AS_GUEST.inThisLang(AppData.getLang()), skin);
        playAsGuest.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainApp.setScreen(new MainMenu(mainApp));
                AppData.setCurrentScreen(mainApp.getScreen());
                dispose();
            }
        });

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        table.center();
        table.padTop(50);
        table.add(label).pad(10);
        table.row();
        table.add(usernameField).width(500).height(60).pad(10);
        table.row();
        table.add(passwordField).width(500).height(60).pad(10);
        table.row();
        table.add(securityQuestionField).width(500).height(60).pad(10);
        table.row();
        table.add(securityAnswerField).width(500).height(60).pad(10);
        table.row();
        table.add(confirmButton).width(400).height(60).pad(10);
        table.row();
        table.add(playAsGuest).width(400).height(60).pad(10);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
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
        skin.dispose();
        backgroundTexture.dispose();
    }
}
