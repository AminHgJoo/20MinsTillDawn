package com.example.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.example.models.Player;
import com.example.models.enums.HeroTypes;

public class GameMenu implements Screen, InputProcessor {

    public static final float SCREEN_WIDTH = 3104;
    public static final float SCREEN_HEIGHT = 1440;
    public static final float basePlayerSpeed = 20;

    final MainApp mainApp;

    private SpriteBatch batch;
    private OrthographicCamera camera;
    private StretchViewport viewport;

    final private Texture backgroundTexture;

    private Stage uiStage;

    private Player player;

    public GameMenu(MainApp mainApp) {
        this.mainApp = mainApp;

        this.batch = new SpriteBatch();

        this.camera = new OrthographicCamera();

        this.viewport = new StretchViewport(SCREEN_WIDTH, SCREEN_HEIGHT, camera);

        this.uiStage = new Stage(new ScreenViewport());

        this.backgroundTexture = new Texture(Gdx.files.internal("game_menu_assets/game_map.png"));

        this.player = new Player(HeroTypes.SHANA);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {
        //TODO: Render
        PlayerController.handlePlayerRenderingUpdates(delta, player);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.set(player.getPosition().x, player.getPosition().y, 0);
        camera.update();
        viewport.apply();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(backgroundTexture, 0, 0);
        PlayerController.animatePlayer(player, batch);
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

        if (keycode == Input.Keys.W) {
            player.getSpeed().y = player.getHeroSpeedFactor() * basePlayerSpeed;
        } else if (keycode == Input.Keys.S) {
            player.getSpeed().y = -player.getHeroSpeedFactor() * basePlayerSpeed;
        }

        if (keycode == Input.Keys.A) {
            player.getSpeed().x = -player.getHeroSpeedFactor() * basePlayerSpeed;
        } else if (keycode == Input.Keys.D) {
            player.getSpeed().x = player.getHeroSpeedFactor() * basePlayerSpeed;
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.W || keycode == Input.Keys.S) {
            player.getSpeed().y = 0;
        }
        if (keycode == Input.Keys.A || keycode == Input.Keys.D) {
            player.getSpeed().x = 0;
        }

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return true;
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
}
