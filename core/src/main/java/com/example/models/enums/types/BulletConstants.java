package com.example.models.enums.types;

public enum BulletConstants {
    BAT_PROJECTILE(6),
    PLAYER_PROJECTILE(6);

    public final int speedFactor;

    BulletConstants(int speedFactor) {
        this.speedFactor = speedFactor;
    }
}
