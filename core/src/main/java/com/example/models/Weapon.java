package com.example.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.example.models.enums.types.WeaponTypes;

public class Weapon {
    private int magSize;
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
        //TODO: Everything else loaded from enum!
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getMagSize() {
        return magSize;
    }

    public void setMagSize(int magSize) {
        this.magSize = magSize;
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
}
