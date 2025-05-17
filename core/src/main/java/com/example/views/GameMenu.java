package com.example.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.example.MainApp;
import com.example.controllers.*;
import com.example.models.AppData;
import com.example.models.GameData;
import com.example.models.Player;
import com.example.models.enums.Translation;
import com.example.utilities.CursorManager;
import com.example.utilities.CursorUtil;

public class GameMenu implements Screen, InputProcessor {

    public static final float SCREEN_WIDTH = 1552;
    public static final float SCREEN_HEIGHT = 720;
    public static final float baseEntitySpeed = 40;

    final MainApp mainApp;
    final private PauseMenu pauseMenu;

    private SpriteBatch batch;
    private final OrthographicCamera camera;
    private final StretchViewport viewport;

    final private Texture backgroundTexture;

    private final Stage uiStage;
    private Label elapsedTimeLabel;
    private Label remainingTimeLabel;
    private Label hpLabel;
    private Label killsLabel;
    private Label ammoLabel;
    private Label levelLabel;
    private Label xpLabel;
    private ProgressBar xpBar;

    private final Player player;
    private final GameData gameData;

    public GameMenu(final MainApp mainApp, final GameData gameData) {
        this.mainApp = mainApp;

        this.pauseMenu = new PauseMenu(mainApp, this);

        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        this.viewport = new StretchViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera);


        this.backgroundTexture = new Texture(Gdx.files.internal("game_menu_assets/game_map.png"));

        this.gameData = gameData;

        this.player = gameData.getPlayer();
        AppData.setCurrentPlayer(player);

        this.uiStage = new Stage(new ScreenViewport());
        initUI();

        PlayerController.gameData = this.gameData;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        if (gameData.isPlayerAutoAiming()) {
            CursorUtil.hideCursor();
        } else {
            CursorManager.getInstance().setCursorToHover();
        }
    }

    public void clearResetScreen() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.position.set(player.getPosition().x, player.getPosition().y, 0);
        camera.update();
        viewport.apply();
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void render(float delta) {
        CentralGameController.backgroundUpdates(gameData, delta, camera);

        clearResetScreen();

        CentralGameController.renderGraphics(gameData, delta, batch, backgroundTexture);

        CentralGameController.handleGameLogic(gameData, delta);

        handleUI(delta);

        CentralGameController.endingChecks(gameData, delta, mainApp, this);
    }

    private void handleUI(float delta) {
        updateUI();
        uiStage.act(delta);
        uiStage.draw();
    }

    private void initUI() {
        Label label = new Label(Translation.ELAPSED_TIME.translate() + ": " + String.format("%.0f", gameData.getElapsedTimeInSeconds()), AppData.skin);
        label.setColor(Color.CYAN);
        label.setFontScale(1.3f);
        label.setPosition(0, uiStage.getHeight() - label.getHeight());
        elapsedTimeLabel = label;
        uiStage.addActor(elapsedTimeLabel);

        Label label2 = new Label(Translation.REMAINING_TIME.translate() + ": " + (gameData.getGameEndTimeInMins() * 60), AppData.skin);
        label2.setColor(Color.CYAN);
        label2.setFontScale(1.3f);
        label2.setPosition(0, label.getY() - label2.getHeight());
        remainingTimeLabel = label2;
        uiStage.addActor(remainingTimeLabel);

        Label label1 = new Label(Translation.HP.translate() + ": " + player.getHP() + "/" + player.getMaxHP(), AppData.skin);
        label1.setColor(Color.CYAN);
        label1.setFontScale(1.3f);
        label1.setPosition(0, label2.getY() - label1.getHeight());
        hpLabel = label1;
        uiStage.addActor(hpLabel);

        Label label3 = new Label(Translation.KILLS.translate() + ": " + player.getKills(), AppData.skin);
        label3.setColor(Color.CYAN);
        label3.setFontScale(1.3f);
        label3.setPosition(0, label1.getY() - label3.getHeight());
        killsLabel = label3;
        uiStage.addActor(killsLabel);

        Label label4 = new Label(Translation.AMMO.translate() + ": " + player.getWeapon().getMaxMagSize() + "/" + player.getWeapon().getMaxMagSize(), AppData.skin);
        label4.setColor(Color.CYAN);
        label4.setFontScale(1.3f);
        label4.setPosition(0, label3.getY() - label4.getHeight());
        ammoLabel = label4;
        uiStage.addActor(ammoLabel);

        Label label5 = new Label(Translation.LEVEL.translate() + ": " + player.getLevel(), AppData.skin);
        label5.setColor(Color.CYAN);
        label5.setFontScale(1.3f);
        label5.setPosition(0, label4.getY() - label5.getHeight());
        levelLabel = label5;
        uiStage.addActor(levelLabel);

        Label label6 = new Label("XP: " + player.getXp() + "/" + player.howMuchXpToNextLevel(), AppData.skin);
        label6.setColor(Color.CYAN);
        label6.setFontScale(1.3f);
        label6.setPosition(0, label5.getY() - label6.getHeight());
        xpLabel = label6;
        uiStage.addActor(xpLabel);

        ProgressBar progressBar = new ProgressBar(0, player.howMuchXpToNextLevel(), 1, false, AppData.skin);
        progressBar.setColor(Color.CYAN);
        progressBar.setPosition(0, label6.getY() - progressBar.getHeight());
        progressBar.setValue(0);
        xpBar = progressBar;
        uiStage.addActor(xpBar);
    }

    private void updateUI() {
        elapsedTimeLabel.setText(Translation.ELAPSED_TIME.translate() + ": " + String.format("%.0f", gameData.getElapsedTimeInSeconds()));

        hpLabel.setText(Translation.HP.translate() + ": " + player.getHP() + "/" + player.getMaxHP());

        float remainingTime = gameData.getGameEndTimeInMins() * 60 - gameData.getElapsedTimeInSeconds();
        remainingTimeLabel.setText(Translation.REMAINING_TIME.translate() + ": " + String.format("%.0f", remainingTime));

        killsLabel.setText(Translation.KILLS.translate() + ": " + player.getKills());

        ammoLabel.setText(Translation.AMMO.translate() + ": " + player.getWeapon().getBulletsRemaining() + "/" + player.getWeapon().getMaxMagSize());

        levelLabel.setText(Translation.LEVEL.translate() + ": " + player.getLevel());

        xpLabel.setText("XP: " + player.getXp() + "/" + player.howMuchXpToNextLevel());

        xpBar.setRange(0, player.howMuchXpToNextLevel());
        xpBar.setValue(player.getXp());
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        uiStage.getViewport().update(width, height, true);
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
        batch.dispose();
        uiStage.dispose();
        AppData.setCurrentPlayer(null);
    }

    @Override
    public boolean keyDown(int keycode) {
        PlayerController.handleInputKeyDown(keycode, player);
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        PlayerController.handleInputKeyUp(keycode, player);
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        PlayerController.handleInputKeyDown(button, player);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        PlayerController.handleInputKeyUp(button, player);
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public PauseMenu getPauseMenu() {
        return pauseMenu;
    }

    public GameData getGameData() {
        return gameData;
    }
}
