package com.example.models.enums.types;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.example.models.AppData;

import java.util.HashMap;

public enum BulletConstants {
    BAT_PROJECTILE(6),
    PLAYER_PROJECTILE(6);

    public final int speedFactor;

    public static TextureRegion[] eyebatExplosionFX() {
        HashMap<String, Texture> map = AppData.getWeaponAssets();

        return new TextureRegion[]{new TextureRegion(map.get("Explosion0")),
            new TextureRegion(map.get("Explosion1")),
            new TextureRegion(map.get("Explosion2")),
            new TextureRegion(map.get("Explosion3")),
            new TextureRegion(map.get("Explosion4")),
            new TextureRegion(map.get("Explosion5"))};
    }

    BulletConstants(int speedFactor) {
        this.speedFactor = speedFactor;
    }
}
