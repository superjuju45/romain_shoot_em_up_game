package entities;

import static utilz.Constants.EnemyConstants.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import entities.EnemyListeners.EnemyDeathListener;
import entities.EnemyListeners.EnemyHitListener;
import entities.EnemyListeners.IEnemyListener;

public abstract class Enemy extends Entity {
    private int enemyType;
    private int maxHealth;
    private int currentHealth;
    /**
     * An enemy needs to be active in order to be displayed, mobile and able to hit the player.
     */
    private boolean active = true;

    /**
     * This list of listeners is triggered when the enemy is hit or takes damage.
     */
    private final List<EnemyHitListener> hitListeners = new ArrayList<>();
    /**
     * This list of listeners is triggered when the enemy dies.
     */
    private final List<EnemyDeathListener> deathListeners = new ArrayList<>();

    private static Logger logger = Logger.getLogger(Enemy.class.getName());

    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyType = enemyType;
        initHitbox(x, y, width, height);
        maxHealth = GetMaxHealth(enemyType);
        currentHealth = maxHealth;
    }

    private <T extends IEnemyListener> void fireEnemyListeners(List<T> listenerList) {
        for (T l: listenerList) {
            l.action();
        }
    }

    public void takeDamage(int amount) {
        getHurt(amount);
        checkDeath(); 
    }

    private void getHurt(int amount) {
        currentHealth -= amount;
        fireEnemyListeners(hitListeners);
        logger.info("Enemy got hit");
    }

    private void checkDeath() {
        if(currentHealth <= 0){
            currentHealth = 0;
            fireEnemyListeners(deathListeners);
            logger.info("Enemy died");
            active = false;
        }
    }

    protected int getEnemyType() {
        return enemyType;
    }

    protected void setEnemyType(int type) {
        this.enemyType = type;
    }

    protected int getMaxHealth() {
        return maxHealth;
    }

    protected void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    protected int getCurrentHealth() {
        return currentHealth;
    }

    protected void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void addEnemyHitListener(EnemyHitListener listener) {
        hitListeners.add(listener);
    }
    
    public void removeEnemyHitListener(EnemyHitListener listener) {
        hitListeners.remove(listener);
    }

    public void addEnemyDeathListener(EnemyDeathListener listener) {
        deathListeners.add(listener);
    }
    
    public void removeEnemyDeathListener(EnemyDeathListener listener) {
        deathListeners.remove(listener);
    }

    /**
     * Put back the enemy at his initial state. If it has lost health or if he died: 
     * he gets back all its health.
     */
    public void resetEnemy() {
        setHitbox(getX(),getY());
        resetEnemyHealth();
        logger.info("Enemy resurrected");
        active = true;
    }

    private void resetEnemyHealth() {
        currentHealth = maxHealth;
    }
}
