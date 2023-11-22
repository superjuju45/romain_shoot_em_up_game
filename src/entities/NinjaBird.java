package entities;

import static utilz.Constants.EnemyConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class NinjaBird extends Enemy {
    public static double birdspeed = 1;
    //AttackBox
    private Rectangle2D.Float attackBox;
    private float xDrawOffset = 30;
    private float yDrawOffset = 40;
    private boolean hasAttacked = false;

    public NinjaBird(float x, float y) {
        super(x, y, NINJA_BIRD_WIDTH_DEFAULT, NINJA_BIRD_HEIGHT_DEFAULT, NINJA_BIRD);
        initHitbox(x, y, 70, 50);
        initAttackBox();
    }   

    private void initAttackBox() {
        attackBox = hitbox;
    }

    public void update(Player player) {
        updateMove(player);
        updateAttackBox();
    }
    
    private void updateAttackBox() {
        attackBox.x = hitbox.x;
        attackBox.y = hitbox.y;
    }

    private void updateMove(Player player) {
        hitbox.x += (-1)*birdspeed; // l'ennemi avance de droite à gauche!
        checkEnemyHit(attackBox, player);
    }

    protected void checkEnemyHit(Rectangle2D.Float attackBox, Player player) {
        if(hasAttacked == false) {
            if(attackBox.intersects(player.getHitbox())) {
                player.changeHealth(-GetEnemyDmg(NINJA_BIRD));
                hasAttacked = true;
            }
        }
    }

    public void drawAttackBox(Graphics g) {
        g.setColor(Color.red);
        g.drawRect((int)attackBox.x, (int)attackBox.y, (int)attackBox.width, (int)attackBox.height);
    }
}
