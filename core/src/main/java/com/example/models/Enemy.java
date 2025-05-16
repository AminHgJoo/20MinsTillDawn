package com.example.models;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.example.models.enums.types.EnemyTypes;
import com.example.views.GameMenu;

public class Enemy {
    private Vector2 position;
    private Vector2 velocity;
    private float speedMagnitude;

    private Rectangle collisionRectangle;

    private EnemyTypes type;

    private double HP;

    private transient Animation<TextureRegion> animation;
    private transient float animationTimer;

    //TODO: Update this for bat and elder
    private float behaviourTimer;

    public void loadAnimation() {
        animationTimer = 0;
        animation = new Animation<>(type.animationSpeed, type.textures);
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public Enemy() {
    }

    public Enemy(Vector2 position, EnemyTypes type) {
        this.type = type;

        loadAnimation();

        this.velocity = new Vector2();
        this.position = position;

        collisionRectangle = new Rectangle();

        HP = type.HP;
        behaviourTimer = 0;
        speedMagnitude = GameMenu.baseEntitySpeed * type.speedFactor;
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

    public void setAnimation(Animation<TextureRegion> animation) {
        this.animation = animation;
    }

    public Rectangle getCollisionRectangle() {
        return collisionRectangle;
    }

    public void setCollisionRectangle(Rectangle collisionRectangle) {
        this.collisionRectangle = collisionRectangle;
    }

    public double getHP() {
        return HP;
    }

    public void setHP(double HP) {
        this.HP = HP;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public EnemyTypes getType() {
        return type;
    }

    public void setType(EnemyTypes type) {
        this.type = type;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public float getAnimationTimer() {
        return animationTimer;
    }

    public void setAnimationTimer(float animationTimer) {
        this.animationTimer = animationTimer;
    }

    public float getBehaviourTimer() {
        return behaviourTimer;
    }

    public void setBehaviourTimer(float behaviourTimer) {
        this.behaviourTimer = behaviourTimer;
    }

    public float getSpeedMagnitude() {
        return speedMagnitude;
    }

    public void setSpeedMagnitude(float speedMagnitude) {
        this.speedMagnitude = speedMagnitude;
    }
}
