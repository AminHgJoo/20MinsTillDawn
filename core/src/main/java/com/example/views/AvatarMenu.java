package com.example.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.example.MainApp;
import com.example.models.AppData;
import com.example.models.enums.Languages;

public class AvatarMenu implements Screen {
    final MainApp mainApp;

    final private Skin skin;
    final private Texture backgroundTexture;

    private Stage stage;

    public AvatarMenu(MainApp mainApp) {
        this.mainApp = mainApp;

        this.skin = new Skin(Gdx.files.internal("pixthulhu/skin/pixthulhu-ui.json"));

        this.backgroundTexture = new Texture("game_cover.jpg");

        initializeStage();
    }

    private void initializeStage() {
        stage = new Stage(new ScreenViewport());

        Gdx.input.setInputProcessor(stage);

        Image background = new Image(backgroundTexture);
        background.setFillParent(true);
        stage.addActor(background);

        Table contentTable = new Table();
        contentTable.center();

        int index = 0;
        for (String key : AppData.getAssets().keySet()) {
            TextButton button = new TextButton(key.substring(0, key.length() - 3), skin);
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    AppData.getCurrentUser().getUserSettings().setAvatarKeyString(key);
                    AppData.getCurrentUser().saveSettingsToJson();
                    AppData.getCurrentUser().setProfileAvatar(AppData.getAssets().get(key));
                }
            });
            contentTable.add(button).pad(10).width(200).height(100);

            Image image = new Image(AppData.getAssets().get(key));
            image.setWidth(200);
            image.setHeight(80);
            contentTable.add(image).pad(10);

            contentTable.row();
            index++;
            if (index == 12) {
                break;
            }
        }

        ScrollPane scrollPane = new ScrollPane(contentTable, skin);
        scrollPane.setScrollingDisabled(true, false);

        TextButton backButton = new TextButton(Languages.GO_BACK.translate(), skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainApp.setScreen(new ProfileMenu(mainApp));
                AppData.setCurrentScreen(mainApp.getScreen());
                dispose();
            }
        });

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.center();

        mainTable.add(scrollPane).width(800).height(800);
        mainTable.row();
        mainTable.add(backButton);

        stage.addActor(mainTable);
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
