package com.example.utilities;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.MathUtils;

public class CursorUtil {
    /**
     * Pads a Pixmap to the next power-of-two dimensions.
     *
     * @param original The original pixmap.
     * @return A new pixmap with dimensions that are powers of two, containing the original image at the top-left.
     * @author AminHg
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

    public static Pixmap scaleDownCursorSize(Pixmap original, int scaleFactor) {

        int desiredWidth = original.getWidth() / scaleFactor;
        int desiredHeight = original.getHeight() / scaleFactor;

        Pixmap scaledPixmap = new Pixmap(desiredWidth, desiredHeight, original.getFormat());

        scaledPixmap.drawPixmap(original, 0, 0, original.getWidth(), original.getHeight()
            , 0, 0, desiredWidth, desiredHeight);

        original.dispose();
        return scaledPixmap;
    }
}
