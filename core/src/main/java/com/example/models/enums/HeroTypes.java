package com.example.models.enums;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.example.models.AppData;

import java.util.HashMap;

public enum HeroTypes {
    SHANA(4, 4, "Shana", shanaWalkingTextures(), shanaIdleTextures()),
    ;

    public final String name;
    public final TextureRegion[] idleTextures;
    public final TextureRegion[] walkingTextures;
    public final int baseSpeed;
    public final int baseHP;

    HeroTypes(int baseHP, int baseSpeed, String name, TextureRegion[] walkingTextures, TextureRegion[] idleTextures) {
        this.baseHP = baseHP;
        this.name = name;
        this.idleTextures = idleTextures;
        this.walkingTextures = walkingTextures;
        this.baseSpeed = baseSpeed;
    }

    private static TextureRegion[] shanaIdleTextures() {
        HashMap<String, Texture> map = AppData.getHeroAssets();

        return new TextureRegion[]{new TextureRegion(map.get("ShanaIdle0"))
            , new TextureRegion(map.get("ShanaIdle1"))
            , new TextureRegion(map.get("ShanaIdle2"))
            , new TextureRegion(map.get("ShanaIdle3"))
            , new TextureRegion(map.get("ShanaIdle4"))
            , new TextureRegion(map.get("ShanaIdle5"))};
    }

    private static TextureRegion[] shanaWalkingTextures() {
        HashMap<String, Texture> map = AppData.getHeroAssets();

        return new TextureRegion[]{new TextureRegion(map.get("ShanaWalking0"))
            , new TextureRegion(map.get("ShanaWalking1"))
            , new TextureRegion(map.get("ShanaWalking2"))
            , new TextureRegion(map.get("ShanaWalking3"))
            , new TextureRegion(map.get("ShanaWalking5"))
            , new TextureRegion(map.get("ShanaWalking6"))
            , new TextureRegion(map.get("ShanaWalking7"))};
    }
}
