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
import com.example.models.User;
import com.example.models.enums.Translation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LeaderboardMenu implements Screen {
    final private MainApp mainApp;

    final private Skin skin;
    final private Texture backgroundTexture;

    private boolean hasSortOptionChanged;
    private final ArrayList<User> users;
    private final ArrayList<Translation> sortOptions;
    private Translation sortedByCurrently;

    private Stage stage;

    public LeaderboardMenu(final MainApp mainApp) {
        this.mainApp = mainApp;
        this.hasSortOptionChanged = false;

        this.skin = AppData.skin;
        this.backgroundTexture = new Texture("game_cover.jpg");

        this.users = new ArrayList<>();
        this.users.addAll(User.users);
        this.users.sort(Comparator.comparingInt((User u) -> u.getUserSettings().getScore()).reversed());

        this.sortedByCurrently = Translation.SCORE;

        this.sortOptions = new ArrayList<>();
        sortOptions.add(Translation.SORT_BY_NAME);
        sortOptions.add(Translation.SORT_BY_SURVIVAL_TIME);
        sortOptions.add(Translation.SORT_BY_KILLS);
        sortOptions.add(Translation.SORT_BY_SCORE);

        initializeStage();
    }

    private void iterateSortOptionsList() {
        Translation currentSortOption = sortOptions.get(0);

        if (currentSortOption == Translation.SORT_BY_NAME) {
            sortedByCurrently = Translation.USERNAME;
        } else if (currentSortOption == Translation.SORT_BY_SURVIVAL_TIME) {
            sortedByCurrently = Translation.SURVIVAL_TIME;
        } else if (currentSortOption == Translation.SORT_BY_KILLS) {
            sortedByCurrently = Translation.KILLS;
        } else if (currentSortOption == Translation.SORT_BY_SCORE) {
            sortedByCurrently = Translation.SCORE;
        }

        Collections.rotate(sortOptions, 1);
    }

    private void initializeStage() {
        stage = new Stage(new ScreenViewport());

        Image background = new Image(backgroundTexture);
        background.setFillParent(true);
        stage.addActor(background);

        Table contentTable = new Table();
        contentTable.center();

        Label rankLabel = new Label(Translation.RANK.translate(), skin);
        rankLabel.setFontScale(1.3f);
        rankLabel.setColor(Color.GOLD);
        contentTable.add(rankLabel).pad(10);

        Label usernameLabel = new Label(Translation.USERNAME.translate(), skin);
        usernameLabel.setFontScale(1.3f);
        usernameLabel.setColor(Color.GOLD);
        contentTable.add(usernameLabel).pad(10);

        Label scoreLabel = new Label(Translation.SCORE.translate(), skin);
        scoreLabel.setFontScale(1.3f);
        scoreLabel.setColor(Color.GOLD);
        contentTable.add(scoreLabel).pad(10);

        Label killsLabel = new Label(Translation.KILLS.translate(), skin);
        killsLabel.setFontScale(1.3f);
        killsLabel.setColor(Color.GOLD);
        contentTable.add(killsLabel).pad(10);

        Label survivalTimeLabel = new Label(Translation.SURVIVAL_TIME.translate(), skin);
        survivalTimeLabel.setFontScale(1.3f);
        survivalTimeLabel.setColor(Color.GOLD);
        contentTable.add(survivalTimeLabel).pad(10);

        contentTable.row();

        int index = 1;

        for (User user : this.users) {
            Color color = (user == AppData.getCurrentUser()) ? Color.RED : Color.GOLD;

            if (index <= 3 && color == Color.GOLD) {
                color = Color.MAGENTA;
            }

            Label rank = new Label(String.valueOf(index), skin);
            rank.setFontScale(1.3f);
            rank.setColor(color);
            contentTable.add(rank).pad(10);

            Label username = new Label(user.getUsername(), skin);
            username.setFontScale(1.3f);
            username.setColor(color);
            contentTable.add(username).pad(10);

            Label score = new Label(String.valueOf(user.getUserSettings().getScore()), skin);
            score.setFontScale(1.3f);
            score.setColor(color);
            contentTable.add(score).pad(10);

            Label kills = new Label(String.valueOf(user.getUserSettings().getKills()), skin);
            kills.setFontScale(1.3f);
            kills.setColor(color);
            contentTable.add(kills).pad(10);

            Label survivalTime = new Label(String.valueOf(
                user.getUserSettings().getLongestSurvivalTimeSeconds()), skin);
            survivalTime.setFontScale(1.3f);
            survivalTime.setColor(color);
            contentTable.add(survivalTime).pad(10);
            contentTable.row();

            index++;
        }

        ScrollPane scrollPane = new ScrollPane(contentTable, skin);
        scrollPane.setScrollingDisabled(true, false);

        TextButton backButton = new TextButton(Translation.GO_BACK.translate(), skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainApp.setScreen(new MainMenu(mainApp));
                AppData.setCurrentScreen(mainApp.getScreen());
                dispose();
            }
        });

        TextButton sortButton = new TextButton(sortOptions.get(0).translate(), skin);
        sortButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                hasSortOptionChanged = true;

                if (sortOptions.get(0).equals(Translation.SORT_BY_NAME)) {
                    users.sort(Comparator.comparing(User::getUsername));
                } else if (sortOptions.get(0).equals(Translation.SORT_BY_SURVIVAL_TIME)) {
                    users.sort(Comparator.comparingInt((User user) -> user.getUserSettings()
                        .getLongestSurvivalTimeSeconds()).reversed());
                } else if (sortOptions.get(0).equals(Translation.SORT_BY_KILLS)) {
                    users.sort(Comparator.comparingInt((User user) -> user.getUserSettings().getKills()).reversed());
                } else if (sortOptions.get(0).equals(Translation.SORT_BY_SCORE)) {
                    users.sort(Comparator.comparingInt((User user) -> user.getUserSettings().getScore()).reversed());
                }

                iterateSortOptionsList();
            }
        });

        Label label = new Label(Translation.SORTED_BY.translate() + " " + sortedByCurrently.translate(), skin);
        label.setFontScale(1.3f);
        label.setColor(Color.GREEN);

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.center();

        mainTable.add(scrollPane).width(1200).height(700).row();
        mainTable.center();
        mainTable.add(backButton).height(50).pad(10).row();
        mainTable.add(sortButton).height(50).pad(10).row();
        mainTable.add(label).pad(10).row();

        stage.addActor(mainTable);

        Gdx.input.setInputProcessor(stage);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        if (hasSortOptionChanged) {
            stage.dispose();
            initializeStage();
            hasSortOptionChanged = false;
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
