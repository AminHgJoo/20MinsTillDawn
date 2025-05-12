package com.example.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.example.MainApp;
import com.example.models.AppData;
import com.example.models.UIHelper;
import com.example.models.enums.Translation;

import java.util.HashMap;
import java.util.Map;

public class KeybindsMenu implements Screen {
    final private MainApp mainApp;

    final private Texture backgroundTexture;
    final private Skin skin;

    private Stage stage;

    public KeybindsMenu(final MainApp mainApp) {
        this.mainApp = mainApp;

        this.backgroundTexture = new Texture("settings_menu/background.png");

        this.skin = AppData.skin;

        initializeGUI();
    }

    private Map<Translation, Integer> keyBinds;
    private Map<Translation, TextButton> keyButtons;
    private Translation currentlyRebinding = null;

    private void initializeGUI() {

        stage = new Stage(new ScreenViewport());

        Image background = new Image(backgroundTexture);
        background.setFillParent(true);
        stage.addActor(background);

        keyBinds = new HashMap<>();
        for (String key : AppData.getCurrentUser().getUserSettings().keyBinds.keySet()) {
            keyBinds.put(Translation.getLangObj(key), AppData.getCurrentUser().getUserSettings().keyBinds.get(key));
        }

        keyButtons = new HashMap<>();

        Table table = new Table();
        table.setFillParent(true);
        table.center();
        stage.addActor(table);

        int fact = 1;

        for (Map.Entry<Translation, Integer> entry : keyBinds.entrySet()) {
            final Translation function = entry.getKey();
            Integer keyCode = entry.getValue();

            Label functionLabel = new Label(function.translate(), skin);
            functionLabel.setColor(Color.BLUE);

            TextButton keyButton = new TextButton(Input.Keys.toString(keyCode), skin);
            keyButtons.put(function, keyButton);

            keyButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (currentlyRebinding == null) {
                        currentlyRebinding = function;
                        keyButton.setText(Translation.PRESS_A_NEW_KEY.translate());
                    }
                }
            });

            table.add(functionLabel).pad(10);
            table.add(keyButton).pad(10);
            if (fact == -1) {
                table.row();
            }
            fact *= -1;
        }

        TextButton backButton = new TextButton(Translation.GO_BACK.translate(), skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                AppData.getCurrentUser().saveSettingsToJson();
                mainApp.setScreen(new MainMenu(mainApp));
                AppData.setCurrentScreen(mainApp.getScreen());
                dispose();
            }
        });

        table.add(backButton).colspan(2).pad(10);

        stage.addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {
                if (currentlyRebinding != null) {

                    if (AppData.getCurrentUser().getUserSettings().detectKeybindConflict(keycode)) {
                        TextButton keyButton = keyButtons.get(currentlyRebinding);
                        keyButton.setText(Input.Keys.toString(AppData.getCurrentUser().getUserSettings().keyBinds.get(currentlyRebinding.english)));

                        currentlyRebinding = null;

                        UIHelper uiHelper = new UIHelper(stage, skin);
                        uiHelper.showDialog(Translation.KEYBIND_CONFLICT.translate(), Translation.ERROR);
                        return true;
                    }

                    keyBinds.put(currentlyRebinding, keycode);

                    TextButton btn = keyButtons.get(currentlyRebinding);
                    btn.setText(Input.Keys.toString(keycode));

                    AppData.getCurrentUser().getUserSettings().keyBinds.put(currentlyRebinding.english, keycode);

                    currentlyRebinding = null;
                    return true;
                }
                return false;
            }
        });

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
    }
}
