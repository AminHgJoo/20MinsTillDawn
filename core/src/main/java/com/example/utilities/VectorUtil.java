package com.example.utilities;

import com.badlogic.gdx.math.Vector2;

public class VectorUtil {
    /**
     * @param angle The angle in radians with respect to the x-axis
     * @param mag   Magnitude of the vector
     * @author AminHg
     */
    public static Vector2 createPolarVector(double mag, double angle) {
        Vector2 vector = new Vector2();
        vector.x = (float) (mag * Math.cos(angle));
        vector.y = (float) (mag * Math.sin(angle));
        return vector;
    }

    /**
     * @param angle The angle in radians with respect to the x-axis
     * @param mag   Magnitude of the vector
     * @author AminHg
     */
    public static Vector2 createPolarVector(float mag, float angle) {
        Vector2 vector = new Vector2();
        vector.x = (float) (mag * Math.cos(angle));
        vector.y = (float) (mag * Math.sin(angle));
        return vector;
    }
}
