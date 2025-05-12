package com.example.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.example.MainApp;
import com.example.utilities.CursorManager;
import com.example.controllers.LoginAndRegistration;
import com.example.models.AppData;
import com.example.models.UIHelper;
import com.example.models.User;
import com.example.models.enums.Translation;

public class ProfileMenu implements Screen {
    final MainApp mainApp;

    final private Skin skin;
    final private Texture backgroundTexture;

    private Stage stage;

    public ProfileMenu(final MainApp mainApp) {
        this.mainApp = mainApp;

        this.skin = AppData.skin;

        this.backgroundTexture = new Texture("game_cover.jpg");

        initializeStage();
    }

    private void initializeStage() {
        stage = new Stage(new ScreenViewport());

        Image background = new Image(backgroundTexture);
        background.setFillParent(true);
        stage.addActor(background);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        TextField usernameField = new TextField("", skin);
        usernameField.setMessageText(Translation.NEW_USERNAME.translate());

        TextButton changeUsernameButton = new TextButton(Translation.CONFIRM.translate(), skin);
        changeUsernameButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String newUsername = usernameField.getText();

                User user = AppData.getCurrentUser();
                User query = User.getUserByName(newUsername);

                if (query == null) {
                    user.setUsername(newUsername);
                    user.changeUsernameInDB(newUsername);

                    usernameField.setText("");

                    UIHelper uiHelper = new UIHelper(stage, skin);
                    uiHelper.showDialog(Translation.SUCCESS.translate(), Translation.SUCCESS);
                } else {
                    UIHelper uiHelper = new UIHelper(stage, skin);
                    uiHelper.showDialog(Translation.USERNAME_ALREADY_EXISTS.translate(), Translation.ERROR);
                }
            }
        });

        TextField passwordField = new TextField("", skin);
        passwordField.setMessageText(Translation.NEW_PASSWORD.translate());

        TextButton changePasswordButton = new TextButton(Translation.CONFIRM.translate(), skin);
        changePasswordButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String newPassword = passwordField.getText();

                boolean success = LoginAndRegistration.validatePasswordStrength(newPassword);
                User user = AppData.getCurrentUser();

                if (success) {
                    user.setPassword(newPassword);
                    user.changePasswordInDB(newPassword);

                    passwordField.setText("");

                    UIHelper uiHelper = new UIHelper(stage, skin);
                    uiHelper.showDialog(Translation.SUCCESS.translate(), Translation.SUCCESS);
                } else {
                    UIHelper uiHelper = new UIHelper(stage, skin);
                    uiHelper.showDialog(Translation.PASSWORD_IS_WEAK.translate(), Translation.ERROR);
                }
            }
        });

        TextButton deleteUser = new TextButton(Translation.DELETE_ACCOUNT.translate(), skin);
        deleteUser.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (mainApp.music.isPlaying()) {
                    mainApp.music.stop();
                }

                User toBeDeleted = AppData.getCurrentUser();
                AppData.setCurrentUser(null);
                LoginAndRegistration.deleteUser(toBeDeleted);

                mainApp.setScreen(new LauncherMenu(mainApp));
                AppData.setCurrentScreen(mainApp.getScreen());
                dispose();
            }
        });

        TextButton avatarButton = new TextButton(Translation.AVATAR_MENU.translate(), skin);
        avatarButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainApp.setScreen(new AvatarMenu(mainApp));
                AppData.setCurrentScreen(mainApp.getScreen());
                dispose();
            }
        });

        TextButton backButton = new TextButton(Translation.GO_BACK.translate(), skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainApp.setScreen(new MainMenu(mainApp));
                AppData.setCurrentScreen(mainApp.getScreen());
                dispose();
            }
        });

        table.center();
        table.padTop(50);
        table.add(usernameField).width(600).height(50).pad(10).row();
        table.add(changeUsernameButton).width(600).height(50).pad(10).row();
        table.add(passwordField).width(600).height(50).pad(10).row();
        table.add(changePasswordButton).width(600).height(50).pad(10).row();
        table.add(deleteUser).width(600).height(50).pad(10).row();
        table.add(avatarButton).width(600).height(50).pad(10).row();
        table.add(backButton).width(600).height(50).pad(10).row();


        InputMultiplexer inputMultiplexer = new InputMultiplexer(stage, CursorManager.getInstance());

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
