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
import com.example.models.UIHelper;
import com.example.models.enums.Translation;
import com.example.utilities.CursorManager;

public class MainMenu implements Screen {
    final private MainApp mainApp;

    final private Skin skin;
    final private Texture backgroundTexture;

    private Stage stage;

    public MainMenu(final MainApp mainApp) {
        this.mainApp = mainApp;

        String musicName;

        if (AppData.getCurrentUser() != null) {
            musicName = AppData.getCurrentUser().getUserSettings().getMusicPath();
        } else {
            musicName = "sfx/music/Pretty Dungeon.wav";
        }

        mainApp.music = Gdx.audio.newMusic(Gdx.files.internal(musicName));

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

        Label title = new Label(Translation.WELCOME.translate() + " " + AppData.getCurrentUsername(), skin);
        title.setFontScale(1.3f);
        title.setColor(Color.CYAN);
        title.setPosition(10, 10);
        stage.addActor(title);

        Image profilePicture;
        if (AppData.getCurrentUser() != null) {
            profilePicture = new Image(AppData.getCurrentUser().getProfileAvatar());
        } else {
            int randomIndex = (int) (Math.random() * 12);
            String key = (String) AppData.getProfileAssets().keySet().toArray()[randomIndex];
            Texture profileTexture = AppData.getProfileAssets().get(key);
            profilePicture = new Image(profileTexture);
        }

        Label scoreTitle;
        if (AppData.getCurrentUser() != null) {
            scoreTitle = new Label(Translation.YOUR_SCORE.translate() + ": " + AppData.getCurrentUser().getUserSettings().getScore(), skin);
        } else {
            scoreTitle = new Label(Translation.YOUR_SCORE.translate() + ": " + 0, skin);
        }
        scoreTitle.setFontScale(1.3f);
        scoreTitle.setColor(Color.CYAN);
        scoreTitle.setPosition(10, 50);
        stage.addActor(scoreTitle);

        profilePicture.setPosition(10, 90);
        profilePicture.setSize(profilePicture.getWidth(), profilePicture.getHeight());
        stage.addActor(profilePicture);

        TextButton settings = new TextButton(Translation.SETTINGS.translate(), skin);
        settings.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (AppData.getCurrentUser() != null) {
                    mainApp.setScreen(new SettingsMenu(mainApp));
                    AppData.setCurrentScreen(mainApp.getScreen());
                    dispose();
                } else {
                    UIHelper uiHelper = new UIHelper(stage, skin);
                    uiHelper.showDialog(Translation.LOGGED_IN_AS_GUEST.translate(), Translation.ERROR);
                }
            }
        });

        TextButton profile = new TextButton(Translation.PROFILE.translate(), skin);
        profile.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (AppData.getCurrentUser() != null) {
                    mainApp.setScreen(new ProfileMenu(mainApp));
                    AppData.setCurrentScreen(mainApp.getScreen());
                    dispose();
                } else {
                    UIHelper uiHelper = new UIHelper(stage, skin);
                    uiHelper.showDialog(Translation.LOGGED_IN_AS_GUEST.translate(), Translation.ERROR);
                }
            }
        });

        TextButton preGame = new TextButton(Translation.PRE_GAME_MENU.translate(), skin);
        preGame.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainApp.setScreen(new PreGameMenu(mainApp));
                AppData.setCurrentScreen(mainApp.getScreen());
                dispose();
            }
        });

        TextButton leaderboard = new TextButton(Translation.LEADERBOARD.translate(), skin);
        leaderboard.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainApp.setScreen(new LeaderboardMenu(mainApp));
                AppData.setCurrentScreen(mainApp.getScreen());
                dispose();
            }
        });

        TextButton talents = new TextButton(Translation.TALENTS.translate(), skin);
        talents.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainApp.setScreen(new TalentMenu(mainApp));
                AppData.setCurrentScreen(mainApp.getScreen());
                dispose();
            }
        });

        TextButton loadGame = new TextButton(Translation.LOAD_FROM_SAVE.translate(), skin);
        loadGame.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainApp.setScreen(new GameMenu(mainApp));
                AppData.setCurrentScreen(mainApp.getScreen());
                dispose();
            }
        });

        TextButton logout = new TextButton(Translation.LOGOUT.translate(), skin);
        logout.addListener(new ChangeListener() {

            @Override
            public void changed(ChangeEvent event, Actor actor) {
                AppData.setCurrentUser(null);
                AppData.setCurrentUserSettings(null);
                mainApp.setScreen(new LauncherMenu(mainApp));
                mainApp.music.stop();
                AppData.setCurrentScreen(mainApp.getScreen());
                dispose();
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

        InputMultiplexer inputMultiplexer = new InputMultiplexer(stage, CursorManager.getInstance());

        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void show() {
        if (!mainApp.music.isPlaying()) {
            if (AppData.getCurrentUser() != null) {
                mainApp.music.setVolume(AppData.getCurrentUser().getUserSettings().getSoundVolume());
                mainApp.music.setLooping(true);
                if (AppData.getCurrentUser().getUserSettings().isPlaySFX()) {
                    mainApp.music.play();
                }
            } else {
                mainApp.music.setVolume(0.75f);
                mainApp.music.setLooping(true);
                mainApp.music.play();
            }
        }
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
