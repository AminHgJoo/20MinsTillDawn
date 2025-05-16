package com.example.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.example.models.enums.types.WeaponTypes;

public class Weapon {
    private int bulletsRemaining;

    private int maxMagSize;
    private int reloadTime;
    private int projectileAmount;
    private int dmg;

    private WeaponTypes type;

    private transient Sprite sprite;

    public void loadSprite() {
        //TODO: Implement
    }

    public Weapon() {
    }

    public Weapon(WeaponTypes type) {
        this.type = type;

        loadSprite();

        bulletsRemaining = type.magSize;
        maxMagSize = type.magSize;
        reloadTime = type.reloadTime;
        projectileAmount = type.projectileAmount;
        dmg = type.dmg;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getProjectileAmount() {
        return projectileAmount;
    }

    public void setProjectileAmount(int projectileAmount) {
        this.projectileAmount = projectileAmount;
    }

    public int getReloadTime() {
        return reloadTime;
    }

    public void setReloadTime(int reloadTime) {
        this.reloadTime = reloadTime;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public WeaponTypes getType() {
        return type;
    }

    public void setType(WeaponTypes type) {
        this.type = type;
    }

    public int getBulletsRemaining() {
        return bulletsRemaining;
    }

    public void setBulletsRemaining(int bulletsRemaining) {
        this.bulletsRemaining = bulletsRemaining;
    }

    public int getMaxMagSize() {
        return maxMagSize;
    }

    public void setMaxMagSize(int maxMagSize) {
        this.maxMagSize = maxMagSize;
    }
}
