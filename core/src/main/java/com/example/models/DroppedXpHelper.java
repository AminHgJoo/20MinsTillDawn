package com.example.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.example.models.enums.types.EnemyTypes;

public class DroppedXpHelper {
    private transient Sprite sprite;
    private Vector2 position;

    public DroppedXpHelper() {
    }

    public void loadTransientObjects() {
        sprite = new Sprite(EnemyTypes.getEnemyCorpseTexture());
        sprite.setPosition(position.x - sprite.getWidth() / 2, position.y - sprite.getHeight() / 2);
        sprite.setScale(1.5f);
    }

    public DroppedXpHelper(Vector2 position) {
        this.position = position;

        loadTransientObjects();
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
