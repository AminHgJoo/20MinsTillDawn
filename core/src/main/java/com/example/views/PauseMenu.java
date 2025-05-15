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
import com.example.models.ActiveAbility;
import com.example.models.AppData;
import com.example.models.UIPopupHelper;
import com.example.models.enums.Translation;
import com.example.utilities.CursorManager;
import com.example.utilities.GameSaveUtil;

public class PauseMenu implements Screen {
    final private MainApp mainApp;
    final private GameMenu gameMenu;

    private Texture background;
    private Stage stage;
    private Skin skin;

    public PauseMenu(final MainApp mainApp, final GameMenu gameMenu) {
        this.mainApp = mainApp;
        this.gameMenu = gameMenu;

        this.skin = AppData.skin;

        this.background = new Texture(Gdx.files.internal("game_cover.jpg"));
    }

    private void initializeGUI() {

        if (stage != null) {
            stage.dispose();
        }

        stage = new Stage(new ScreenViewport());

        Image backgroundImage = new Image(background);
        backgroundImage.setFillParent(true);
        stage.addActor(backgroundImage);

        TextButton resumeButton = new TextButton(Translation.RESUME.translate(), skin);
        resumeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainApp.setScreen(gameMenu);;
            }
        });

        TextButton giveUpButton = new TextButton(Translation.GIVE_UP.translate(), skin);
        giveUpButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainApp.setScreen(new MainMenu(mainApp));
                gameMenu.dispose();
                dispose();
            }
        });

        TextButton saveButton = new TextButton(Translation.SAVE_AND_QUIT.translate(),  skin);
        saveButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                UIPopupHelper uiPopupHelper = new UIPopupHelper(stage, skin);

                if (AppData.getCurrentUser() == null) {
                    uiPopupHelper.showDialog(Translation.LOGGED_IN_AS_GUEST.translate(), Translation.ERROR);
                    return;
                }

                if (gameMenu.getGameData().isGameInBossStage()) {
                    uiPopupHelper.showDialog(Translation.CANNOT_SAVE_NOW.translate(), Translation.ERROR);
                    return;
                }

                GameSaveUtil.saveGame(gameMenu.getGameData(), AppData.getCurrentUser().getId());
                mainApp.setScreen(new MainMenu(mainApp));
                gameMenu.dispose();
                dispose();
            }
        });

        Table contentTable = new Table();

        contentTable.center();
        Label label0 = new Label(Translation.REDUCE_TIME_CHEAT.translate(), skin);
        label0.setColor(Color.RED);
        Label label1 = new Label(Translation.REDUCE_TIME_DESC.translate(),  skin);
        label1.setColor(Color.RED);
        Label label2 = new Label(Translation.LEVEL_UP_CHEAT.translate(), skin);
        label2.setColor(Color.RED);
        Label label3 = new Label(Translation.LEVEL_UP_DESC.translate(), skin);
        label3.setColor(Color.RED);
        Label label4 = new Label(Translation.INVINCIBILITY_CHEAT.translate(), skin);
        label4.setColor(Color.RED);
        Label label5 = new Label(Translation.INVINCIBILITY_DESC.translate(), skin);
        label5.setColor(Color.RED);
        Label label6 = new Label(Translation.ADD_HP_CHEAT.translate(), skin);
        label6.setColor(Color.RED);
        Label label7 = new Label(Translation.ADD_HP_DESC.translate(), skin);
        label7.setColor(Color.RED);
        Label label8 = new Label(Translation.GO_TO_BOSS_CHEAT.translate(), skin);
        label8.setColor(Color.RED);
        Label label9 = new Label(Translation.SKIP_TO_BOSS_DESC.translate(), skin);
        label9.setColor(Color.RED);
        contentTable.add(label0).pad(10);
        contentTable.add(label1).pad(10).row();
        contentTable.add(label2).pad(10);
        contentTable.add(label3).pad(10).row();
        contentTable.add(label4).pad(10);
        contentTable.add(label5).pad(10).row();
        contentTable.add(label6).pad(10);
        contentTable.add(label7).pad(10).row();
        contentTable.add(label8).pad(10);
        contentTable.add(label9).pad(10).row();

        ScrollPane cheatsScrollPane = new ScrollPane(contentTable, skin);

        Table abilitiesTable = new Table();
        abilitiesTable.center();

        for (ActiveAbility ability : AppData.getCurrentPlayer().getActiveAbilities()) {
            Label label = new Label(ability.getType().name(), skin);
            label.setColor(Color.ORANGE);
            abilitiesTable.add(label).pad(10).row();
        }

        ScrollPane abilitiesScrollPane = new ScrollPane(abilitiesTable, skin);

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.center();
        mainTable.add(resumeButton).pad(10).height(60).row();
        mainTable.add(giveUpButton).pad(10).height(60).row();
        mainTable.add(saveButton).pad(10).height(60).row();
        mainTable.add(new Label("Cheats", skin)).height(60).pad(10).row();
        mainTable.add(cheatsScrollPane).height(300).pad(10).row();
        mainTable.add(new Label("Abilities", skin)).height(60).pad(10).row();
        mainTable.add(abilitiesScrollPane).height(150).pad(10).row();

        stage.addActor(mainTable);
    }

    @Override
    public void show() {
        initializeGUI();
        InputMultiplexer inputMultiplexer = new InputMultiplexer(CursorManager.getInstance(), stage);
        Gdx.input.setInputProcessor(inputMultiplexer);
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
        if (stage != null) {
            stage.dispose();
        }
        background.dispose();
    }

    public GameMenu getGameMenu() {
        return gameMenu;
    }
}
