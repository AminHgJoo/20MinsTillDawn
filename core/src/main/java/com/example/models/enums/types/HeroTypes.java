package com.example.models.enums.types;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.example.models.AppData;

import java.util.HashMap;

public enum HeroTypes {
    SHANA(4, 4, "Shana", shanaWalkingTextures(), shanaIdleTextures()),
    DIAMOND(7, 1, "Diamond", diamondWalkingTextures(), diamondIdleTextures()),
    SCARLET(3, 5, "Scarlet", scarletWalkingTextures(), scarletIdleTextures()),
    LILITH(5, 3, "Lilith", lilithWalkingTextures(), lilithIdleTextures()),
    DASHER(2, 10, "Dasher", dasherWalkingTextures(), dasherIdleTextures()),
    ;

    public final static String[] allHeroNames = allHeroesNames();

    public final String name;
    public final TextureRegion[] idleTextures;
    public final TextureRegion[] walkingTextures;
    public final int baseSpeed;
    public final int baseHP;

    private static String[] allHeroesNames() {
        String[] names = new String[values().length];
        for (HeroTypes heroType : values()) {
            names[heroType.ordinal()] = heroType.name;
        }
        return names;
    }

    public static HeroTypes getHeroTypeByName(String name) {
        for (HeroTypes heroType : values()) {
            if (heroType.name.equals(name)) {
                return heroType;
            }
        }
        return null;
    }

    HeroTypes(int baseHP, int baseSpeed, String name, TextureRegion[] walkingTextures, TextureRegion[] idleTextures) {
        this.baseHP = baseHP;
        this.name = name;
        this.idleTextures = idleTextures;
        this.walkingTextures = walkingTextures;
        this.baseSpeed = baseSpeed;
    }

    private static TextureRegion[] dasherIdleTextures() {
        HashMap<String, Texture> map = AppData.getHeroAssets();

        return new TextureRegion[]{new TextureRegion(map.get("DasherIdle0"))
            , new TextureRegion(map.get("DasherIdle1"))
            , new TextureRegion(map.get("DasherIdle2"))
            , new TextureRegion(map.get("DasherIdle3"))
            , new TextureRegion(map.get("DasherIdle4"))
            , new TextureRegion(map.get("DasherIdle5"))};
    }

    private static TextureRegion[] dasherWalkingTextures() {
        HashMap<String, Texture> map = AppData.getHeroAssets();

        return new TextureRegion[]{new TextureRegion(map.get("DasherWalking0"))
            , new TextureRegion(map.get("DasherWalking1"))
            , new TextureRegion(map.get("DasherWalking2"))
            , new TextureRegion(map.get("DasherWalking3"))};
    }

    private static TextureRegion[] lilithIdleTextures() {
        HashMap<String, Texture> map = AppData.getHeroAssets();

        return new TextureRegion[]{new TextureRegion(map.get("LilithIdle0"))
            , new TextureRegion(map.get("LilithIdle1"))
            , new TextureRegion(map.get("LilithIdle2"))
            , new TextureRegion(map.get("LilithIdle3"))
            , new TextureRegion(map.get("LilithIdle4"))
            , new TextureRegion(map.get("LilithIdle5"))};
    }

    private static TextureRegion[] lilithWalkingTextures() {
        HashMap<String, Texture> map = AppData.getHeroAssets();

        return new TextureRegion[]{new TextureRegion(map.get("LilithWalking0"))
            , new TextureRegion(map.get("LilithWalking1"))
            , new TextureRegion(map.get("LilithWalking2"))
            , new TextureRegion(map.get("LilithWalking3"))
            , new TextureRegion(map.get("LilithWalking4"))
            , new TextureRegion(map.get("LilithWalking5"))
            , new TextureRegion(map.get("LilithWalking6"))
            , new TextureRegion(map.get("LilithWalking7"))};
    }

    private static TextureRegion[] scarletIdleTextures() {
        HashMap<String, Texture> map = AppData.getHeroAssets();

        return new TextureRegion[]{new TextureRegion(map.get("ScarletIdle0"))
            , new TextureRegion(map.get("ScarletIdle1"))
            , new TextureRegion(map.get("ScarletIdle2"))
            , new TextureRegion(map.get("ScarletIdle3"))
            , new TextureRegion(map.get("ScarletIdle4"))
            , new TextureRegion(map.get("ScarletIdle5"))};
    }

    private static TextureRegion[] scarletWalkingTextures() {
        HashMap<String, Texture> map = AppData.getHeroAssets();

        return new TextureRegion[]{new TextureRegion(map.get("ScarletWalking0"))
            , new TextureRegion(map.get("ScarletWalking1"))
            , new TextureRegion(map.get("ScarletWalking2"))
            , new TextureRegion(map.get("ScarletWalking3"))};
    }

    private static TextureRegion[] diamondIdleTextures() {
        HashMap<String, Texture> map = AppData.getHeroAssets();

        return new TextureRegion[]{new TextureRegion(map.get("DiamondIdle0"))
            , new TextureRegion(map.get("DiamondIdle1"))
            , new TextureRegion(map.get("DiamondIdle2"))
            , new TextureRegion(map.get("DiamondIdle3"))
            , new TextureRegion(map.get("DiamondIdle4"))
            , new TextureRegion(map.get("DiamondIdle5"))};
    }

    private static TextureRegion[] diamondWalkingTextures() {
        HashMap<String, Texture> map = AppData.getHeroAssets();

        return new TextureRegion[]{new TextureRegion(map.get("DiamondWalking0"))
            , new TextureRegion(map.get("DiamondWalking1"))
            , new TextureRegion(map.get("DiamondWalking2"))
            , new TextureRegion(map.get("DiamondWalking3"))
            , new TextureRegion(map.get("DiamondWalking4"))
            , new TextureRegion(map.get("DiamondWalking5"))
            , new TextureRegion(map.get("DiamondWalking6"))
            , new TextureRegion(map.get("DiamondWalking7"))};
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
