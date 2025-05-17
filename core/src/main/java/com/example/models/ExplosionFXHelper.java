package com.example.models;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.example.models.enums.types.BulletConstants;

public class ExplosionFXHelper {
    private Vector2 position;
    private float animationTimer;
    private Animation<TextureRegion> animation;

    private boolean isEnemyCorpse;

    public ExplosionFXHelper(Vector2 position, boolean isEnemyCorpse) {
        this.position = position;
        this.isEnemyCorpse = isEnemyCorpse;
        animation = new Animation<>(0.1f, BulletConstants.eyebatExplosionFX());
        animation.setPlayMode(Animation.PlayMode.NORMAL);
        animationTimer = 0f;
    }

    public Animation<TextureRegion> getAnimation() {
        return animation;
    }

    public void setAnimation(Animation<TextureRegion> animation) {
        this.animation = animation;
    }

    public float getAnimationTimer() {
        return animationTimer;
    }

    public void setAnimationTimer(float animationTimer) {
        this.animationTimer = animationTimer;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void addTime(float time) {
        animationTimer += time;
    }

    public boolean isEnemyCorpse() {
        return isEnemyCorpse;
    }

    public void setEnemyCorpse(boolean enemyCorpse) {
        isEnemyCorpse = enemyCorpse;
    }
}
