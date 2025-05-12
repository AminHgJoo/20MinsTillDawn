package com.example.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.MathUtils;

public class CursorUtil {
    /**
     * Pads a Pixmap to the next power-of-two dimensions.
     *
     * @param original The original pixmap.
     * @return A new pixmap with dimensions that are powers of two, containing the original image at the top-left.
     */
    public static Pixmap padPixmapToPOT(Pixmap original) {
        int originalWidth = original.getWidth();
        int originalHeight = original.getHeight();
        int potWidth = MathUtils.nextPowerOfTwo(originalWidth);
        int potHeight = MathUtils.nextPowerOfTwo(originalHeight);

        if (potWidth == originalWidth && potHeight == originalHeight) {
            return original;
        }

        Pixmap padded = new Pixmap(potWidth, potHeight, original.getFormat());

        padded.setColor(0, 0, 0, 0);
        padded.fill();

        padded.drawPixmap(original, 0, 0);

        original.dispose();

        return padded;
    }

    public static Pixmap scaleCursorSize(Pixmap original, int scaleFactor) {

        int desiredWidth = original.getWidth() / scaleFactor;
        int desiredHeight = original.getHeight() / scaleFactor;

        Pixmap scaledPixmap = new Pixmap(desiredWidth, desiredHeight, original.getFormat());

        scaledPixmap.drawPixmap(original, 0, 0, original.getWidth(), original.getHeight()
            , 0, 0, desiredWidth, desiredHeight);

        int hotspotX = desiredWidth / 2;
        int hotspotY = desiredHeight / 2;

        original.dispose();
        return scaledPixmap;
    }
}
