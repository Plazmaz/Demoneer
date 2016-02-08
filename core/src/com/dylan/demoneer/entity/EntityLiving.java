package com.dylan.demoneer.entity;

import com.badlogic.gdx.ai.steer.Steerable;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Dylan on 2/7/2016.
 */
public abstract class EntityLiving extends Entity {
    private double health;
    private double damageDealt = 1;
    private boolean alive;
    public EntityLiving(TextureRegion texture, int spriteWidth, int spriteHeight) {
        super(texture, spriteWidth, spriteHeight);
        health = getMaxHealth();
    }

    public abstract double getMaxHealth();

    public abstract void playDeathAnimation();

    public void damage(double amt) {
        health -= amt;
        if(health <= 0) {
            alive = false;
        }
        velX += (Math.random() / 2) - .25;
        velY += (Math.random() / 2) - .25;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isAlive() {
        return alive;
    }
}
