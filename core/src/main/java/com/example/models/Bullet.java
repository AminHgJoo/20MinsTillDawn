package com.example.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
    private Vector2 position;
    private Vector2 velocity;

    //Enemies also shoot projectiles.
    private boolean isPlayerProjectile;

    private int dmg;

    private transient Sprite sprite;

    public void loadSprite() {
        if (isPlayerProjectile) {
            sprite = new Sprite(AppData.getWeaponAssets().get("Bullet"));
        } else {
            sprite = new Sprite(AppData.getWeaponAssets().get("EyebatBullet"));
        }
        sprite.setRotation(velocity.angleDeg());
        sprite.rotate(-90);
        sprite.setPosition(position.x, position.y);
    }

    public Bullet() {
    }

    public Bullet(int dmg, boolean isPlayerProjectile, Vector2 position, Vector2 velocity) {
        this.dmg = dmg;
        this.isPlayerProjectile = isPlayerProjectile;
        this.position = position;
        this.velocity = velocity;
        loadSprite();
    }

    public int getDmg() {
        return dmg;
    }

    public boolean isPlayerProjectile() {
        return isPlayerProjectile;
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

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public void setIsPlayerProjectile(boolean isPlayerProjectile) {
        this.isPlayerProjectile = isPlayerProjectile;
    }
}
