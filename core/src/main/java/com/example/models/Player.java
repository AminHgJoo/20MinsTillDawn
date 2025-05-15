package com.example.models;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.example.models.enums.types.HeroTypes;
import com.example.views.GameMenu;

import java.util.ArrayList;

public class Player {
    private HeroTypes heroType;

    private Vector2 position;

    private Vector2 velocity;
    private Rectangle rectangle;

    private transient Animation<TextureRegion> idleAnimation;

    private transient Animation<TextureRegion> walkingAnimation;
    private float stateTimeIdle;
    private float stateTimeWalking;

    private int HP;
    private int kills;
    private int xp;
    private int level;

    private int maxHP;
    private int heroSpeedFactor;
    private ArrayList<ActiveAbility> activeAbilities;

    //TODO: Initialize later.
    private Weapon weapon;
    private boolean isIdle;

    public void loadAnimations() {
        this.idleAnimation = new Animation<>(0.1f, heroType.idleTextures);
        this.walkingAnimation = new Animation<>(0.1f, heroType.walkingTextures);
        idleAnimation.setPlayMode(Animation.PlayMode.LOOP);
        walkingAnimation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public int howMuchXpToNextLevel() {
        return 20 * level;
    }

    public Player() {
    }

    public Player(HeroTypes heroType, Weapon weapon) {
        this.heroType = heroType;

        loadAnimations();

        this.velocity = new Vector2(0, 0);
        this.position = new Vector2(GameMenu.SCREEN_WIDTH, GameMenu.SCREEN_HEIGHT);

        this.activeAbilities = new ArrayList<>();

        this.stateTimeIdle = 0;
        this.stateTimeWalking = 0;
        this.isIdle = true;

        HP = heroType.baseHP;
        maxHP = heroType.baseHP;
        heroSpeedFactor = heroType.baseSpeed;
        kills = 0;

        rectangle = new Rectangle();

        this.weapon = weapon;

        xp = 0;
        level = 1;
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

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
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

    public ArrayList<ActiveAbility> getActiveAbilities() {
        return activeAbilities;
    }

    public void setActiveAbilities(ArrayList<ActiveAbility> activeAbilities) {
        this.activeAbilities = activeAbilities;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setWalkingAnimation(Animation<TextureRegion> walkingAnimation) {
        this.walkingAnimation = walkingAnimation;
    }

    public void setIdleAnimation(Animation<TextureRegion> idleAnimation) {
        this.idleAnimation = idleAnimation;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public HeroTypes getHeroType() {
        return heroType;
    }

    public void setHeroType(HeroTypes heroType) {
        this.heroType = heroType;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }
}
