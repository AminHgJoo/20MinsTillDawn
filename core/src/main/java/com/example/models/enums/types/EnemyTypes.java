package com.example.models.enums.types;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.example.models.AppData;

import java.util.HashMap;

public enum EnemyTypes {

    TREE_MONSTER(Double.POSITIVE_INFINITY, 0, "Tree Monster", treeMonsterTextures(), 2f),
    TENTACLE_MONSTER(25, 2, "Tentacle Monster", tentacleMonsterTextures(), 0.1f),
    EYEBAT(50, 2, "Eyebat", eyebatTextures(), 0.1f),
    ELDER_BOSS(400, 20, "Elder Boss", elderTextures(), 0.1f);

    public final String name;
    public final int speedFactor;
    public final double HP;
    public final TextureRegion[] textures;
    public final float animationSpeed;

    public static Texture getEnemyCorpseTexture() {
        return AppData.getEnemyAssets().get("EnemyCorpse");
    }

    private static TextureRegion[] treeMonsterTextures() {
        HashMap<String, Texture> map = AppData.getEnemyAssets();

        return new TextureRegion[]{new TextureRegion(map.get("TreeMonster0")),
            new TextureRegion(map.get("TreeMonster1")),
            new TextureRegion(map.get("TreeMonster2"))};
    }

    private static TextureRegion[] tentacleMonsterTextures() {
        HashMap<String, Texture> map = AppData.getEnemyAssets();

        return new TextureRegion[]{new TextureRegion(map.get("TentacleMonster0")),
            new TextureRegion(map.get("TentacleMonster1")),
            new TextureRegion(map.get("TentacleMonster2")),
            new TextureRegion(map.get("TentacleMonster3"))};
    }

    private static TextureRegion[] eyebatTextures() {
        HashMap<String, Texture> map = AppData.getEnemyAssets();

        return new TextureRegion[]{new TextureRegion(map.get("Eyebat0")),
            new TextureRegion(map.get("Eyebat1")),
            new TextureRegion(map.get("Eyebat2")),
            new TextureRegion(map.get("Eyebat3"))};
    }

    private static TextureRegion[] elderTextures() {
        HashMap<String, Texture> map = AppData.getEnemyAssets();

        return new TextureRegion[]{new TextureRegion(map.get("Elder0")),
            new TextureRegion(map.get("Elder1")),
            new TextureRegion(map.get("Elder2")),
            new TextureRegion(map.get("Elder3"))};
    }

    EnemyTypes(double HP, int speedFactor, String name, TextureRegion[] textures, float animationSpeed) {
        this.HP = HP;
        this.name = name;
        this.speedFactor = speedFactor;
        this.textures = textures;
        this.animationSpeed = animationSpeed;
    }
}
