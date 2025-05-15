package com.example.models;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.example.models.enums.types.EnemyTypes;

public class Enemy {
    private Vector2 position;
    private Vector2 velocity;

    private Rectangle collisionRectangle;

    private EnemyTypes type;

    private int HP;

    private transient Animation<TextureRegion> animation;

    public void loadAnimation() {
        //TODO: Implement
    }

    public Enemy() {
    }

    public Enemy(Vector2 velocity, Vector2 position, EnemyTypes type) {
        this.type = type;

        loadAnimation();

        this.velocity = velocity;
        this.position = position;
        //TODO: Update rectangle in movement
        Rectangle rectangle = new Rectangle();
        //TODO: 1- Load HP from enemy type
        //      2- Load Animations from type
    }
}
