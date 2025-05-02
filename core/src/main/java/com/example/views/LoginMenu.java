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
import com.example.controllers.LoginAndRegistration;
import com.example.models.AppData;
import com.example.models.UIHelper;
import com.example.models.enums.Languages;

public class LoginMenu implements Screen {
    final private MainApp mainApp;

    final private Skin skin;
    final private Texture background;

    private Stage stage;

    public LoginMenu(final MainApp mainApp) {
        this.mainApp = mainApp;

        this.skin = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));

        this.background = new Texture("register_menu/background.jpg");

        initializeGUI();
    }

    private void initializeGUI() {
        stage = new Stage(new ScreenViewport());

        Image backgroundImage = new Image(background);
        backgroundImage.setFillParent(true);
        stage.addActor(backgroundImage);

        Label label = new Label(Languages.LOGIN.translate(), skin);
        label.setColor(Color.CORAL);
        label.setFontScale(1.5f);

        TextField usernameField = new TextField("", skin);
        usernameField.setMessageText(Languages.USERNAME.translate());

        TextField passwordField = new TextField("", skin);
        passwordField.setMessageText(Languages.PASSWORD.translate());

        TextButton loginButton = new TextButton(Languages.LOGIN.translate(), skin);
        loginButton.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Languages message = LoginAndRegistration.handleLogin(usernameField.getText().trim(), passwordField.getText().trim());
                if (message == Languages.SUCCESS) {
                    mainApp.setScreen(new MainMenu(mainApp));
                    AppData.setCurrentScreen(mainApp.getScreen());
                    dispose();
                } else {
                    UIHelper uiHelper = new UIHelper(stage, skin);
                    uiHelper.showDialog(message.translate(), Languages.ERROR);
                }
            }
        });

        Label label2 = new Label(Languages.ACCOUNT_RECOVERY.translate(), skin);
        label2.setColor(Color.CORAL);
        label2.setFontScale(1.5f);

        TextField oldUsernameField = new TextField("", skin);
        oldUsernameField.setMessageText(Languages.USERNAME.translate());

        TextField newPasswordField = new TextField("", skin);
        newPasswordField.setMessageText(Languages.NEW_PASSWORD.translate());

        TextField securityQuestionField = new TextField("", skin);
        securityQuestionField.setMessageText(Languages.SECURITY_QUESTION.translate());

        TextField securityAnswerField = new TextField("", skin);
        securityAnswerField.setMessageText(Languages.SECURITY_ANSWER.translate());

        TextButton recoverAccountButton = new TextButton(Languages.ACCOUNT_RECOVERY.translate(), skin);
        recoverAccountButton.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Languages message = LoginAndRegistration.handleAccountRecovery(
                    securityQuestionField.getText().trim()
                    , securityAnswerField.getText().trim()
                    , newPasswordField.getText().trim()
                    , oldUsernameField.getText().trim());

                if (message == Languages.SUCCESS) {
                    mainApp.setScreen(new MainMenu(mainApp));
                    AppData.setCurrentScreen(mainApp.getScreen());
                    dispose();
                } else {
                    UIHelper uiHelper = new UIHelper(stage, skin);
                    uiHelper.showDialog(message.translate(), Languages.ERROR);
                }
            }
        });

        TextButton goToRegisterButton = new TextButton(Languages.GO_TO_REGISTRATION.translate(), skin);
        goToRegisterButton.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainApp.setScreen(new RegisterMenu(mainApp));
                AppData.setCurrentScreen(mainApp.getScreen());
                dispose();
            }
        });

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        table.center();
        table.padTop(50);
        table.add(label).pad(10).row();
        table.add(usernameField).width(500).height(60).pad(10).row();
        table.add(passwordField).width(500).height(60).pad(10).row();
        table.add(loginButton).width(500).height(60).pad(10).row();
        table.add(label2).pad(10).row();
        table.add(oldUsernameField).width(500).height(60).pad(10).row();
        table.add(newPasswordField).width(500).height(60).pad(10).row();
        table.add(securityQuestionField).width(500).height(60).pad(10).row();
        table.add(securityAnswerField).width(500).height(60).pad(10).row();
        table.add(recoverAccountButton).width(500).height(60).pad(10).row();
        table.add(goToRegisterButton);

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
        background.dispose();
    }
}
