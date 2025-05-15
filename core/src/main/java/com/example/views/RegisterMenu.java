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
import com.example.controllers.LoginAndRegistration;
import com.example.models.AppData;
import com.example.models.UIPopupHelper;
import com.example.models.UserSettings;
import com.example.models.enums.Translation;
import com.example.utilities.CursorManager;

public class RegisterMenu implements Screen {
    final private MainApp mainApp;

    final private Skin skin;
    final private Texture backgroundTexture;

    private Stage stage;

    public RegisterMenu(final MainApp mainApp) {
        this.mainApp = mainApp;

        this.skin = AppData.skin;

        this.backgroundTexture = new Texture("register_menu/background.jpg");

        initializeGUI();
    }

    private void initializeGUI() {
        stage = new Stage(new ScreenViewport());

        Image background = new Image(backgroundTexture);
        background.setFillParent(true);
        stage.addActor(background);

        Label label = new Label(Translation.CHOOSE_A_USERNAME.translate(), skin);
        label.setColor(Color.CORAL);
        label.setFontScale(1.5f);

        TextField usernameField = new TextField("", skin);
        usernameField.setMessageText(Translation.USERNAME.translate());

        TextField passwordField = new TextField("", skin);
        passwordField.setMessageText(Translation.PASSWORD.translate());

        TextField securityQuestionField = new TextField("", skin);
        securityQuestionField.setMessageText(Translation.SECURITY_QUESTION.translate());

        TextField securityAnswerField = new TextField("", skin);
        securityAnswerField.setMessageText(Translation.SECURITY_ANSWER.translate());

        TextButton confirmButton = new TextButton(Translation.CONFIRM.translate(), skin);
        confirmButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Translation message = LoginAndRegistration.registerUser(usernameField.getText().trim(), passwordField.getText().trim()
                    , securityQuestionField.getText().trim(), securityAnswerField.getText().trim());

                if (message == Translation.SUCCESS) {
                    mainApp.setScreen(new MainMenu(mainApp));
                    dispose();
                } else {
                    UIPopupHelper uiPopupHelper = new UIPopupHelper(stage, skin);
                    uiPopupHelper.showDialog(message.translate(), Translation.ERROR);
                }
            }
        });

        TextButton playAsGuest = new TextButton(Translation.PLAY_AS_GUEST.translate(), skin);
        playAsGuest.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainApp.setScreen(new MainMenu(mainApp));
                AppData.setCurrentUserSettings(new UserSettings(true));
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


        InputMultiplexer inputMultiplexer = new InputMultiplexer(CursorManager.getInstance(), stage);

        Gdx.input.setInputProcessor(inputMultiplexer);
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
        backgroundTexture.dispose();
    }
}
