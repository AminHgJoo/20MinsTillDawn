package com.example.models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
    private Vector2 position;
    private Vector2 velocity;

    private Rectangle rectangle;

    //Enemies also shoot projectiles.
    private boolean isPlayerProjectile;

    private int dmg;

    private transient Sprite sprite;

    public void loadSprite() {
        //TODO: Implement
    }

    public Bullet() {
    }

    public Bullet(int dmg, boolean isPlayerProjectile, Vector2 position, Vector2 velocity, Rectangle rectangle, Sprite sprite) {
        this.dmg = dmg;
        this.isPlayerProjectile = isPlayerProjectile;
        this.position = position;
        this.rectangle = rectangle;
        this.velocity = velocity;
        this.sprite = sprite;
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

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
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
