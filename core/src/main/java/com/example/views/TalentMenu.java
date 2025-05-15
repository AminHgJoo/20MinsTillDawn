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

public class TalentMenu implements Screen {
    final MainApp mainApp;

    final private Skin skin;
    final private Texture backgroundTexture;

    private Stage stage;

    public TalentMenu(MainApp mainApp) {
        this.mainApp = mainApp;

        skin = AppData.skin;

        backgroundTexture = new Texture("game_cover.jpg");

        initializeStage();
    }

    private void initializeStage() {
        stage = new Stage(new ScreenViewport());

        InputMultiplexer inputMultiplexer = new InputMultiplexer(stage, CursorManager.getInstance());

        Gdx.input.setInputProcessor(inputMultiplexer);

        Image background = new Image(backgroundTexture);
        background.setFillParent(true);
        stage.addActor(background);

        Table contentTable = new Table();
        contentTable.center();

        Label label = new Label(Translation.CHARACTER.translate(), skin);
        label.setColor(Color.VIOLET);
        contentTable.add(label).pad(10);

        Label label2 = new Label(Translation.SPEED.translate(), skin);
        label2.setColor(Color.VIOLET);
        contentTable.add(label2).pad(10);

        Label label3 = new Label(Translation.HP.translate(), skin);
        label3.setColor(Color.VIOLET);
        contentTable.add(label3).pad(10).row();

        Label label4 = new Label("Shana", skin);
        label4.setColor(Color.BLUE);
        contentTable.add(label4).pad(10);

        Label label5 = new Label("4", skin);
        label5.setColor(Color.BLUE);
        contentTable.add(label5).pad(10);

        Label label6 = new Label("4", skin);
        label6.setColor(Color.BLUE);
        contentTable.add(label6).pad(10).row();

        Label label7 = new Label("Diamond", skin);
        label7.setColor(Color.BLUE);
        contentTable.add(label7).pad(10);

        Label label8 = new Label("1", skin);
        label8.setColor(Color.BLUE);
        contentTable.add(label8).pad(10);

        Label label9 = new Label("7", skin);
        label9.setColor(Color.BLUE);
        contentTable.add(label9).pad(10).row();

        Label label10 = new Label("Scarlet", skin);
        label10.setColor(Color.BLUE);
        contentTable.add(label10).pad(10);

        Label label11 = new Label("5", skin);
        label11.setColor(Color.BLUE);
        contentTable.add(label11).pad(10);

        Label label12 = new Label("3", skin);
        label12.setColor(Color.BLUE);
        contentTable.add(label12).pad(10).row();

        Label label13 = new Label("Lilith", skin);
        label13.setColor(Color.BLUE);
        contentTable.add(label13).pad(10);

        Label label14 = new Label("3", skin);
        label14.setColor(Color.BLUE);
        contentTable.add(label14).pad(10);

        Label label15 = new Label("5", skin);
        label15.setColor(Color.BLUE);
        contentTable.add(label15).pad(10).row();

        Label label16 = new Label("Dasher", skin);
        label16.setColor(Color.BLUE);
        contentTable.add(label16).pad(10);

        Label label17 = new Label("10", skin);
        label17.setColor(Color.BLUE);
        contentTable.add(label17).pad(10);

        Label label18 = new Label("2", skin);
        label18.setColor(Color.BLUE);
        contentTable.add(label18).pad(10).row();

        Table contentTable2 = new Table();
        contentTable2.center();

        Label label19 = new Label(Translation.CHEAT_CODE.translate(), skin);
        label19.setColor(Color.SCARLET);
        contentTable2.add(label19).pad(10);

        Label label20 = new Label(Translation.DESCRIPTION.translate(), skin);
        label20.setColor(Color.SCARLET);
        contentTable2.add(label20).pad(10).row();

        Label label21 = new Label(Translation.REDUCE_TIME_CHEAT.translate(), skin);
        label21.setColor(Color.GREEN);
        contentTable2.add(label21).pad(10);

        Label label22 = new Label(Translation.REDUCE_TIME_DESC.translate(), skin);
        label22.setColor(Color.GREEN);
        contentTable2.add(label22).pad(10).row();

        Label label23 = new Label(Translation.LEVEL_UP_CHEAT.translate(), skin);
        label23.setColor(Color.GREEN);
        contentTable2.add(label23).pad(10);

        Label label24 = new Label(Translation.LEVEL_UP_DESC.translate(), skin);
        label24.setColor(Color.GREEN);
        contentTable2.add(label24).pad(10).row();

        Label label25 = new Label(Translation.INVINCIBILITY_CHEAT.translate(), skin);
        label25.setColor(Color.GREEN);
        contentTable2.add(label25).pad(10);

        Label label26 = new Label(Translation.INVINCIBILITY_DESC.translate(), skin);
        label26.setColor(Color.GREEN);
        contentTable2.add(label26).pad(10).row();

        Label label27 = new Label(Translation.ADD_HP_CHEAT.translate(), skin);
        label27.setColor(Color.GREEN);
        contentTable2.add(label27).pad(10);

        Label label28 = new Label(Translation.ADD_HP_DESC.translate(), skin);
        label28.setColor(Color.GREEN);
        contentTable2.add(label28).pad(10).row();

        Label label29 = new Label(Translation.GO_TO_BOSS_CHEAT.translate(), skin);
        label29.setColor(Color.GREEN);
        contentTable2.add(label29).pad(10);

        Label label30 = new Label(Translation.SKIP_TO_BOSS_DESC.translate(), skin);
        label30.setColor(Color.GREEN);
        contentTable2.add(label30).pad(10).row();

        Table contentTable3 = new Table();
        contentTable3.center();

        Label label31 = new Label(Translation.ABILITY.translate(), skin);
        label31.setColor(Color.TEAL);
        contentTable3.add(label31).pad(10);

        Label label32 = new Label(Translation.DESCRIPTION.translate(), skin);
        label32.setColor(Color.TEAL);
        contentTable3.add(label32).pad(10).row();

        Label label33 = new Label("Vitality", skin);
        label33.setColor(Color.BLUE);
        contentTable3.add(label33).pad(10);

        Label label34 = new Label(Translation.VITALITY_DESC.translate(), skin);
        label34.setColor(Color.BLUE);
        contentTable3.add(label34).pad(10).row();

        Label label35 = new Label("Damager", skin);
        label35.setColor(Color.BLUE);
        contentTable3.add(label35).pad(10);

        Label label36 = new Label(Translation.DAMAGER_DESC.translate(), skin);
        label36.setColor(Color.BLUE);
        contentTable3.add(label36).pad(10).row();

        Label label37 = new Label("Procrease", skin);
        label37.setColor(Color.BLUE);
        contentTable3.add(label37).pad(10);

        Label label38 = new Label(Translation.PROCREASE_DESC.translate(), skin);
        label38.setColor(Color.BLUE);
        contentTable3.add(label38).pad(10).row();

        Label label39 = new Label("Amocrease", skin);
        label39.setColor(Color.BLUE);
        contentTable3.add(label39).pad(10);

        Label label40 = new Label(Translation.AMOCREASE_DESC.translate(), skin);
        label40.setColor(Color.BLUE);
        contentTable3.add(label40).pad(10).row();

        Label label41 = new Label("Speedy", skin);
        label41.setColor(Color.BLUE);
        contentTable3.add(label41).pad(10);

        Label label42 = new Label(Translation.SPEEDY_DESC.translate(), skin);
        label42.setColor(Color.BLUE);
        contentTable3.add(label42).pad(10).row();


        TextButton keyBindsMenu = new TextButton(Translation.CONTROLS.translate(), skin);
        keyBindsMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (AppData.getCurrentUser() == null) {
                    UIHelper uiHelper = new UIHelper(stage, skin);
                    uiHelper.showDialog(Translation.LOGGED_IN_AS_GUEST.translate(), Translation.ERROR);
                    return;
                }
                mainApp.setScreen(new KeybindsMenu(mainApp));
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

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.center();

        ScrollPane scrollPane = new ScrollPane(contentTable, skin);
        scrollPane.setScrollingDisabled(true, false);

        ScrollPane scrollPane2 = new ScrollPane(contentTable2, skin);
        scrollPane2.setScrollingDisabled(true, false);

        ScrollPane scrollPane3 = new ScrollPane(contentTable3, skin);
        scrollPane3.setScrollingDisabled(true, false);

        mainTable.add(scrollPane).width(1200).height(250).row();
        mainTable.add(scrollPane2).width(1200).height(250).row();
        mainTable.add(scrollPane3).width(1200).height(250).row();
        mainTable.add(keyBindsMenu).height(50).pad(10).row();
        mainTable.add(backButton).height(50).pad(10).row();

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
        backgroundTexture.dispose();
    }
}
