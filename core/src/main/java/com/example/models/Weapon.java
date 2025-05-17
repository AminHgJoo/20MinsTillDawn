package com.example.models;

import com.example.models.enums.types.WeaponTypes;

public class Weapon {
    private int bulletsRemaining;

    private int maxMagSize;
    private int reloadTime;
    private int projectileAmount;
    private int dmg;

    private float reloadTimer;

    private WeaponTypes type;

    private float dmgMultiplier;

    public Weapon() {
    }

    public Weapon(WeaponTypes type) {
        this.type = type;

        bulletsRemaining = type.magSize;
        maxMagSize = type.magSize;
        reloadTime = type.reloadTime;
        projectileAmount = type.projectileAmount;
        dmg = type.dmg;

        reloadTimer = 0;

        dmgMultiplier = 1;
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

    public float getReloadTimer() {
        return reloadTimer;
    }

    public void setReloadTimer(float reloadTimer) {
        this.reloadTimer = reloadTimer;
    }

    public void projectilePlusPlus() {
        projectileAmount++;
    }

    public void addMagSize(int magSize) {
        maxMagSize += magSize;
    }

    public float getDmgMultiplier() {
        return dmgMultiplier;
    }

    public void setDmgMultiplier(float dmgMultiplier) {
        this.dmgMultiplier = dmgMultiplier;
    }

    public void applyDmgMultiplier(float dmgMultiplier) {
        this.dmgMultiplier *= dmgMultiplier;
    }
}
