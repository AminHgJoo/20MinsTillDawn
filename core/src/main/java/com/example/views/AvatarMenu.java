package com.example.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.example.MainApp;
import com.example.models.AppData;
import com.example.models.enums.Translation;
import com.example.utilities.CursorManager;

import javax.swing.*;
import java.io.File;

public class AvatarMenu implements Screen {
    final private MainApp mainApp;

    final private Skin skin;
    final private Texture backgroundTexture;

    private Stage stage;
    private Table loadingOverlay;

    public AvatarMenu(MainApp mainApp) {
        this.mainApp = mainApp;

        skin = AppData.skin;

        backgroundTexture = new Texture("game_cover.jpg");

        initializeGUI();
    }

    private void initializeGUI() {
        stage = new Stage(new ScreenViewport());

        InputMultiplexer inputMultiplexer = new InputMultiplexer(CursorManager.getInstance(), stage);

        Gdx.input.setInputProcessor(inputMultiplexer);

        Image background = new Image(backgroundTexture);
        background.setFillParent(true);
        stage.addActor(background);

        Table contentTable = new Table();
        contentTable.center();

        for (String key : AppData.getProfileAssets().keySet()) {
            TextButton button = new TextButton(key.matches("^\\S+Pic$") ? key.substring(0, key.length() - 3) : "Custom", skin);
            button.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    AppData.getCurrentUser().getUserSettings().setAvatarKeyString(key);
                    AppData.getCurrentUser().saveSettingsToJson();
                    AppData.getCurrentUser().setProfileAvatar(AppData.getProfileAssets().get(key));
                }
            });
            contentTable.add(button).pad(10).width(200).height(100);

            Image image = new Image(AppData.getProfileAssets().get(key));
            image.setWidth(200);
            image.setHeight(80);
            contentTable.add(image).pad(10);

            contentTable.row();
        }

        ScrollPane scrollPane = new ScrollPane(contentTable, skin);
        scrollPane.setScrollingDisabled(true, false);

        TextButton backButton = new TextButton(Translation.GO_BACK.translate(), skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mainApp.setScreen(new ProfileMenu(mainApp));
                dispose();
            }
        });

        TextButton dragAndDropPicture = new TextButton(Translation.DRAG_AND_DROP.translate(), skin);
        dragAndDropPicture.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                AppData.isProgramWaitingForFileDrop = true;
                loadingOverlay.setVisible(true);
            }
        });

        TextButton selectFile = new TextButton(Translation.SELECT_FILE.translate(), skin);
        selectFile.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

                new Thread(() -> {

                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    } catch (ClassNotFoundException | InstantiationException
                             | IllegalAccessException | UnsupportedLookAndFeelException e) {
                        e.printStackTrace();
                    }

                    JFileChooser fileChooser = new JFileChooser();
                    int returnVal = fileChooser.showOpenDialog(null);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();

                        Gdx.app.postRunnable(() -> {
                            System.out.println("Selected file: " + selectedFile.getAbsolutePath());

                            processSelectedImage(selectedFile.getAbsolutePath());
                        });
                    }
                }).start();
            }
        });

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.center();

        mainTable.add(scrollPane).width(800).height(800);
        mainTable.row();
        mainTable.add(backButton).height(50).row();
        mainTable.add(dragAndDropPicture).height(50).row();
        mainTable.add(selectFile).height(50).row();

        stage.addActor(mainTable);

        loadingOverlay = new Table(skin);
        loadingOverlay.setFillParent(true);
        Label loadingLabel = new Label("Waiting for file to be dropped to window...", skin);
        loadingOverlay.add(loadingLabel);
        loadingOverlay.setVisible(false);
        loadingOverlay.setColor(0, 0, 0, 0.5f);
        loadingOverlay.setTouchable(Touchable.enabled);

        loadingOverlay.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                event.handle();
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                event.handle();
            }
        });

        stage.addActor(loadingOverlay);
    }

    public void processDraggedImage(String absPath) {
        FileHandle fileHandle = Gdx.files.absolute(absPath);

        AppData.getProfileAssets().put(absPath, new Texture(fileHandle));
        AppData.getCurrentUser().getUserSettings().savedProfileAssetPaths.add(absPath);
        AppData.getCurrentUser().getUserSettings().setAvatarKeyString(absPath);
        AppData.getCurrentUser().saveSettingsToJson();
        AppData.getCurrentUser().setProfileAvatar(AppData.getProfileAssets().get(absPath));

        loadingOverlay.setVisible(false);
    }

    private void processSelectedImage(String absPath) {
        FileHandle fileHandle = Gdx.files.absolute(absPath);

        AppData.getProfileAssets().put(absPath, new Texture(fileHandle));
        AppData.getCurrentUser().getUserSettings().savedProfileAssetPaths.add(absPath);
        AppData.getCurrentUser().getUserSettings().setAvatarKeyString(absPath);
        AppData.getCurrentUser().saveSettingsToJson();
        AppData.getCurrentUser().setProfileAvatar(AppData.getProfileAssets().get(absPath));
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
