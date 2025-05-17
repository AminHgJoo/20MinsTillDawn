package com.example.utilities;
import com.badlogic.gdx.Input;

public class InputUtil {
    /**
     * Returns a string representation for both keyboard keys and mouse buttons.
     *
     * @param keycode the keycode that could be from Input.Keys or Input.Buttons.
     * @return a user-friendly string for the input.
     */
    public static String inputCodeToString(int keycode) {
        return switch (keycode) {
            case Input.Buttons.LEFT -> "Mouse Left";
            case Input.Buttons.RIGHT -> "Mouse Right";
            case Input.Buttons.MIDDLE -> "Mouse Middle";
            case Input.Buttons.BACK -> "Mouse Back";
            case Input.Buttons.FORWARD -> "Mouse Forward";
            default -> {
                String keyString = Input.Keys.toString(keycode);

                yield (keyString == null || keyString.equals("Unknown key"))
                    ? "Unknown" : keyString;
            }
        };
    }
}
