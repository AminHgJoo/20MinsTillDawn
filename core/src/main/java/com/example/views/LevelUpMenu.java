package com.example.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.example.MainApp;
import com.example.models.ActiveAbility;
import com.example.models.AppData;
import com.example.models.GameData;
import com.example.models.enums.Translation;
import com.example.models.enums.types.AbilityTypes;
import com.example.utilities.CursorManager;

public class LevelUpMenu implements Screen {
    final private MainApp mainApp;

    final private GameData gameData;
    final private GameMenu gameMenu;

    final private Texture background;
    final private Skin skin;

    private Stage stage;

    public LevelUpMenu(final MainApp mainApp, final GameData gameData, final GameMenu gameMenu) {
        this.mainApp = mainApp;
        this.gameData = gameData;
        this.gameMenu = gameMenu;

        skin = AppData.skin;

        background = new Texture("game_cover.jpg");

        initializeGUI();
    }

    private void initializeGUI() {
        stage = new Stage(new ScreenViewport());

        Image backgroundImage = new Image(background);
        backgroundImage.setFillParent(true);
        stage.addActor(backgroundImage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label label = new Label(Translation.LEVEL_UP.translate(), skin);
        label.setColor(Color.GOLD);
        label.setFontScale(1.5f);
        table.center().add(label).colspan(3).pad(10).row();

        Array<AbilityTypes> abilities = new Array<>(AbilityTypes.values());

        for (int i = 0; i < 3; i++) {
            int selectedAbilityIndex = MathUtils.random(0, abilities.size - 1);
            AbilityTypes selectedAbility = abilities.get(selectedAbilityIndex);
            abilities.removeIndex(selectedAbilityIndex);

            TextButton textButton = new TextButton(selectedAbility.name, skin);
            textButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    selectedAbility.effect.execute(gameData);

                    Array<ActiveAbility> array = gameData.getPlayer().getActiveAbilities();
                    array.get(array.size - 1).setType(selectedAbility);

                    mainApp.setScreen(gameMenu);
                    dispose();
                }
            });
            table.add(textButton).pad(10);
        }
    }

    @Override
    public void show() {
        InputMultiplexer inputMultiplexer = new InputMultiplexer(CursorManager.getInstance(), stage);
        Gdx.input.setInputProcessor(inputMultiplexer);
        CursorManager.getInstance().setCursorToHover();
        if (AppData.getCurrentUserSettings().isPlaySFX()) {
            AppData.getSoundFX().get("YouWin").play();
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

    public GameMenu getGameMenu() {
        return gameMenu;
    }
}
