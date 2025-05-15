package com.example.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.example.MainApp;
import com.example.controllers.PlayerController;
import com.example.controllers.WorldController;
import com.example.models.AppData;
import com.example.models.Player;
import com.example.models.enums.types.HeroTypes;
import com.example.utilities.CursorManager;

public class GameMenu implements Screen, InputProcessor {

    public static final float SCREEN_WIDTH = 1552;
    public static final float SCREEN_HEIGHT = 720;
    public static final float basePlayerSpeed = 40;

    final MainApp mainApp;
    final private PauseMenu pauseMenu;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private StretchViewport viewport;

    final private Texture backgroundTexture;

    private Stage uiStage;

    private Player player;

    public GameMenu(MainApp mainApp) {
        this.mainApp = mainApp;

        this.pauseMenu = new PauseMenu(mainApp, this);

        this.batch = new SpriteBatch();

        this.camera = new OrthographicCamera();

        this.viewport = new StretchViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera);

        this.uiStage = new Stage(new ScreenViewport());

        this.backgroundTexture = new Texture(Gdx.files.internal("game_menu_assets/game_map.png"));

        //TODO: Properly load/initialize.
        this.player = new Player(HeroTypes.DASHER, null);
        AppData.setCurrentPlayer(player);
    }

    @Override
    public void show() {
        InputMultiplexer inputMultiplexer = new InputMultiplexer(CursorManager.getInstance(), this);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    public void handleCameraViewportUpdates() {
        camera.position.set(player.getPosition().x, player.getPosition().y, 0);
        camera.update();
        viewport.apply();
    }

    @Override
    public void render(float delta) {
        PlayerController.handlePlayerRenderingUpdates(delta, player);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        handleCameraViewportUpdates();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(backgroundTexture, 0, 0);
        PlayerController.animatePlayer(player, batch);
        batch.end();

        batch.begin();
        WorldController.handleLightGradiant(player, batch);
        batch.end();

        uiStage.act(delta);
        uiStage.draw();
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
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
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
}
