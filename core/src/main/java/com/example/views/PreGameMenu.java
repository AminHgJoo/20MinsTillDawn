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
import com.example.models.GameData;
import com.example.models.Player;
import com.example.models.Weapon;
import com.example.models.enums.Translation;
import com.example.models.enums.types.HeroTypes;
import com.example.models.enums.types.WeaponTypes;

public class PreGameMenu implements Screen {
    final private MainApp mainApp;

    final private Skin skin;
    final private Texture backgroundTexture;

    private Stage stage;

    public PreGameMenu(final MainApp mainApp) {
        this.mainApp = mainApp;

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

        Label label = new Label(Translation.SELECT_A_HERO.translate(), skin);
        label.setColor(Color.CORAL);
        label.setFontScale(1.3f);
        table.add(label).row();

        SelectBox<String> heroList = new SelectBox<>(skin);
        heroList.setItems(HeroTypes.allHeroNames);
        table.add(heroList).height(80).width(600).row();

        Label label1 = new Label(Translation.SELECT_A_WEAPON.translate(), skin);
        label1.setColor(Color.CORAL);
        label1.setFontScale(1.3f);
        table.add(label1).row();

        SelectBox<String> weaponList = new SelectBox<>(skin);
        weaponList.setItems(WeaponTypes.allNames);
        table.add(weaponList).height(80).width(600).row();

        Label label2 = new Label(Translation.SELECT_A_GAME_DURATION.translate(), skin);
        label2.setColor(Color.CORAL);
        label2.setFontScale(1.3f);
        table.add(label2).row();

        SelectBox<String> timeList = new SelectBox<>(skin);
        timeList.setItems("2", "5", "10", "20");
        table.add(timeList).height(80).width(600).row();

        TextButton startGame = new TextButton(Translation.START_GAME.translate(), skin);
        startGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                GameData gameData = new GameData(Integer.parseInt(timeList.getSelected())
                    , new Player(HeroTypes.getHeroTypeByName(heroList.getSelected())
                    , new Weapon(WeaponTypes.getWeaponTypeByName(weaponList.getSelected()))));

                mainApp.setScreen(new GameMenu(mainApp, gameData));
                dispose();
            }
        });
        table.add(startGame).height(80).row();

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
        backgroundTexture.dispose();
        stage.dispose();
    }
}
