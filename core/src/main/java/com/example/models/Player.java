package com.example.models;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.example.models.enums.HeroTypes;
import com.example.views.GameMenu;

public class Player {
    private Vector2 position;
    private Vector2 speed;

    private Rectangle rectangle;

    private final Animation<TextureRegion> idleAnimation;
    private final Animation<TextureRegion> walkingAnimation;
    private float stateTimeIdle;
    private float stateTimeWalking;

    private int HP;
    private int maxHP;
    private int heroSpeedFactor;

    private boolean isIdle;

    public Player(HeroTypes heroType) {
        this.idleAnimation = new Animation<>(0.1f, heroType.idleTextures);
        this.walkingAnimation = new Animation<>(0.1f, heroType.walkingTextures);
        this.stateTimeIdle = 0;
        this.stateTimeWalking = 0;
        this.isIdle = true;
        this.position = new Vector2(GameMenu.SCREEN_WIDTH / 2, GameMenu.SCREEN_HEIGHT / 2);
        this.speed = new Vector2(0, 0);
        walkingAnimation.setPlayMode(Animation.PlayMode.LOOP);
        idleAnimation.setPlayMode(Animation.PlayMode.LOOP);
        HP = heroType.baseHP;
        maxHP = heroType.baseHP;
        heroSpeedFactor = heroType.baseSpeed;
        rectangle = new Rectangle();
    }

    public Animation<TextureRegion> getIdleAnimation() {
        return idleAnimation;
    }

    public boolean isIdle() {
        return isIdle;
    }

    public void setIdle(boolean idle) {
        isIdle = idle;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getSpeed() {
        return speed;
    }

    public void setSpeed(Vector2 speed) {
        this.speed = speed;
    }

    public float getStateTimeIdle() {
        return stateTimeIdle;
    }

    public void setStateTimeIdle(float stateTimeIdle) {
        this.stateTimeIdle = stateTimeIdle;
    }

    public float getStateTimeWalking() {
        return stateTimeWalking;
    }

    public void setStateTimeWalking(float stateTimeWalking) {
        this.stateTimeWalking = stateTimeWalking;
    }

    public Animation<TextureRegion> getWalkingAnimation() {
        return walkingAnimation;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getHeroSpeedFactor() {
        return heroSpeedFactor;
    }

    public void setHeroSpeedFactor(int heroSpeedFactor) {
        this.heroSpeedFactor = heroSpeedFactor;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
