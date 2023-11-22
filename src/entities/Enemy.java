package entities;

import static utilz.Constants.EnemyConstants.*;

import java.awt.geom.Rectangle2D;
import java.util.GregorianCalendar;

import static utilz.Constants.PlayerConstants.SCORE;

public abstract class Enemy extends Entity {
    protected int enemyType;
    protected int maxHealth;
    protected int currentHealth;
    protected boolean active = true;

    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;
        initHitbox(x, y, width, height);
        maxHealth = GetMaxHealth(enemyType);
        currentHealth = maxHealth;
    }

    public void hurt(int amount) {
        currentHealth -= amount;
        SCORE += 10;
        if(currentHealth <= 0){
            currentHealth = 0;
            SCORE += 20;
            active = false;
        }
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void resetEnemy() {
        hitbox.x = x;
        hitbox.y = y;
        currentHealth = maxHealth;
        active = true;
    }

}
