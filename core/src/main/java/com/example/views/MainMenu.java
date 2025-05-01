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

public class MainMenu implements Screen {
    final private MainApp mainApp;

    final private Skin skin;
    final private Texture backgroundTexture;

    private Stage stage;

    public MainMenu(final MainApp mainApp) {
        this.mainApp = mainApp;

        this.skin = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));

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

        Label title = new Label(Languages.WELCOME.translate() + " " + AppData.getCurrentUsername(), skin);
        title.setFontScale(1.3f);
        title.setColor(Color.CYAN);
        title.setPosition(10, 10);
        stage.addActor(title);

        Image profilePicture;
        if (AppData.getCurrentUser() != null) {
            profilePicture = new Image(AppData.getCurrentUser().getProfileAvatar());
        } else {
            int randomIndex = (int) (Math.random() * 12);
            String key = (String) AppData.getAssets().keySet().toArray()[randomIndex];
            Texture profileTexture = AppData.getAssets().get(key);
            profilePicture = new Image(profileTexture);
        }

        profilePicture.setPosition(10, 50);
        profilePicture.setSize(profilePicture.getWidth(), profilePicture.getHeight());
        stage.addActor(profilePicture);

        TextButton settings = new TextButton(Languages.SETTINGS.translate(), skin);
        settings.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });

        TextButton profile = new TextButton(Languages.PROFILE.translate(), skin);
        profile.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });

        TextButton preGame = new TextButton(Languages.PRE_GAME_MENU.translate(), skin);
        preGame.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });

        TextButton leaderboard = new TextButton(Languages.LEADERBOARD.translate(), skin);
        leaderboard.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });

        TextButton talents = new TextButton(Languages.TALENTS.translate(), skin);
        talents.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });

        TextButton loadGame = new TextButton(Languages.LOAD_FROM_SAVE.translate(), skin);
        loadGame.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });

        TextButton logout = new TextButton(Languages.LOGOUT.translate(), skin);
        logout.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                AppData.setCurrentUser(null);
                mainApp.setScreen(new LauncherMenu(mainApp));
                AppData.setCurrentScreen(mainApp.getScreen());
                dispose();
                //TODO: Maybe save data?
            }
        });

        table.center();
        table.padTop(50);
        table.add(settings).width(500).height(60).pad(10).row();
        table.add(profile).width(500).height(60).pad(10).row();
        table.add(preGame).width(500).height(60).pad(10).row();
        table.add(leaderboard).width(500).height(60).pad(10).row();
        table.add(talents).width(500).height(60).pad(10).row();
        table.add(loadGame).width(500).height(60).pad(10).row();
        table.add(logout).width(500).height(60).pad(10).row();

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
        backgroundTexture.dispose();
        skin.dispose();
    }
}
