package com.example.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Disposable;

public class CursorManager extends InputAdapter implements Disposable {
    private static CursorManager instance;
    private final Cursor hoverCursor;
    private final Cursor clickCursor;

    private CursorManager() {
        Texture cursorSheet = new Texture("lava-custom-cursor.png");

        int frameWidth = cursorSheet.getWidth() / 2;
        int frameHeight = cursorSheet.getHeight();

        final int scaleFactor = 10;

        TextureRegion hoverRegion = new TextureRegion(cursorSheet, 0, 0, frameWidth, frameHeight);
        TextureRegion clickRegion = new TextureRegion(cursorSheet, frameWidth, 0, frameWidth, frameHeight);

        Pixmap hoverPixmap = regionToPixmap(hoverRegion);
        Pixmap clickPixmap = regionToPixmap(clickRegion);

        hoverPixmap = CursorUtil.scaleCursorSize(hoverPixmap, scaleFactor);
        clickPixmap = CursorUtil.scaleCursorSize(clickPixmap, scaleFactor);

        hoverPixmap = CursorUtil.padPixmapToPOT(hoverPixmap);
        clickPixmap = CursorUtil.padPixmapToPOT(clickPixmap);

        hoverCursor = Gdx.graphics.newCursor(hoverPixmap, (frameWidth / 2) / scaleFactor, (frameHeight / 2) / scaleFactor);
        clickCursor = Gdx.graphics.newCursor(clickPixmap, (frameWidth / 2) / scaleFactor, (frameHeight / 2) / scaleFactor);

        Gdx.graphics.setCursor(hoverCursor);

        hoverPixmap.dispose();
        clickPixmap.dispose();
        cursorSheet.dispose();
    }

    private Pixmap regionToPixmap(TextureRegion region) {
        Texture texture = region.getTexture();
        Pixmap pixmap = new Pixmap(region.getRegionWidth(), region.getRegionHeight(), Pixmap.Format.RGBA8888);
        texture.getTextureData().prepare();
        Pixmap originalPixmap = texture.getTextureData().consumePixmap();
        pixmap.drawPixmap(originalPixmap, 0, 0, region.getRegionX(), region.getRegionY(), region.getRegionWidth(), region.getRegionHeight());
        originalPixmap.dispose();
        return pixmap;
    }

    public static CursorManager getInstance() {
        if (instance == null) {
            instance = new CursorManager();
        }
        return instance;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            Gdx.graphics.setCursor(clickCursor);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button == Input.Buttons.LEFT) {
            Gdx.graphics.setCursor(hoverCursor);
        }
        return false;
    }

    @Override
    public void dispose() {
        Gdx.graphics.setCursor(null);
    }
}
