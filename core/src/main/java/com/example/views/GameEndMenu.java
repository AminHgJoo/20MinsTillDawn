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
import com.example.models.GameData;
import com.example.models.enums.Translation;
import com.example.utilities.CursorManager;

public class GameEndMenu implements Screen {
    final private MainApp mainApp;

    final private Texture background;
    final private Skin skin;

    final private GameData finishedGameData;

    final private boolean isVictory;
    final private int score;

    private Stage stage;

    public GameEndMenu(final MainApp mainApp, final GameData finishedGameData, boolean isVictory) {
        CursorManager.getInstance().setCursorToHover();
        this.mainApp = mainApp;
        this.finishedGameData = finishedGameData;
        this.isVictory = isVictory;

        this.background = new Texture("game_cover.jpg");

        this.skin = AppData.skin;

        score = finishedGameData.getPlayer().getKills() * (int) finishedGameData.getElapsedTimeInSeconds();

        initializeGUI();

        addUserStats();
    }

    private void addUserStats() {
        if (AppData.getCurrentUser() == null) {
            return;
        }

        AppData.getCurrentUser().getUserSettings().scorePlusPlus(score);
        AppData.getCurrentUser().getUserSettings().killsPlusPlus(finishedGameData.getPlayer().getKills());
        AppData.getCurrentUser().getUserSettings().updateLongestSurvivalTimeSeconds((int) finishedGameData.getElapsedTimeInSeconds());
        AppData.getCurrentUser().saveSettingsToJson();
    }

    private void initializeGUI() {
        stage = new Stage(new ScreenViewport());

        Image backgroundImage = new Image(background);
        backgroundImage.setFillParent(true);
        stage.addActor(backgroundImage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label label = new Label(AppData.getCurrentUsername(), skin);
        label.setColor(Color.GOLD);
        label.setFontScale(2f);

        Label label1 = new Label(Translation.ELAPSED_TIME.translate() + ": " + String.format("%.0f", finishedGameData.getElapsedTimeInSeconds()), skin);
        label1.setColor(Color.PURPLE);
        label1.setFontScale(1.5f);

        Label label2 = new Label(Translation.KILLS.translate() + ": " + finishedGameData.getPlayer().getKills(), skin);
        label2.setColor(Color.RED);
        label2.setFontScale(1.5f);

        Label label3 = new Label(Translation.SCORE.translate() + ": " + score, skin);
        label3.setColor(Color.PURPLE);
        label3.setFontScale(1.5f);

        Label label4 = new Label((isVictory ? Translation.WIN : Translation.LOSS).translate(), skin);
        label4.setColor(Color.GOLD);
        label4.setFontScale(2f);

        TextButton backToMainMenu = new TextButton(Translation.GO_BACK.translate(), skin);
        backToMainMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainApp.setScreen(new MainMenu(mainApp));
                dispose();
            }
        });

        table.center();
        table.add(label).pad(10).row();
        table.add(label1).pad(10).row();
        table.add(label2).pad(10).row();
        table.add(label3).pad(10).row();
        table.add(label4).pad(10).row();
        table.add(backToMainMenu).pad(10).row();
    }

    @Override
    public void show() {
        InputMultiplexer multiplexer = new InputMultiplexer(CursorManager.getInstance(), stage);
        Gdx.input.setInputProcessor(multiplexer);
        if (AppData.getCurrentUserSettings().isPlaySFX()) {
            AppData.getSoundFX().get(isVictory ? "YouWin" : "YouLose").play();
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
        background.dispose();
    }
}
