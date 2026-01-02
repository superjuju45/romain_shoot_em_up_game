package entities;

import static utilz.Constants.EnemyConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class FlyingSaucer extends Enemy {
    public static double saucerSpeed = 1;
    //AttackBox
    private Rectangle2D.Float attackBox;
    private float xDrawOffset = 30;
    private float yDrawOffset = 40;
    private boolean hasAttacked = false;

    public FlyingSaucer(float x, float y) {
        super(x, y, FLYING_SAUCER_WIDTH_DEFAULT, FLYING_SAUCER_HEIGHT_DEFAULT, FLYING_SAUCER);
        initHitbox(x, y, 70, 50);
        initAttackBox();
    }   

    private void initAttackBox() {
        attackBox = getHitbox();
    }

    public void update(Player player) {
        updateMove(player);
        updateAttackBox();
    }
    
    private void updateAttackBox() {
        attackBox.x = getHitbox().x;
        attackBox.y = getHitbox().y;
    }

    private void updateMove(Player player) {
        getHitbox().x += (-1)*saucerSpeed; // l'ennemi avance de droite à gauche!
        checkEnemyHit(attackBox, player);
    }

    protected void checkEnemyHit(Rectangle2D.Float attackBox, Player player) {
        if(hasAttacked == false) {
            if(attackBox.intersects(player.getHitbox())) {
                player.changeHealth(-GetEnemyDmg(FLYING_SAUCER));
                hasAttacked = true;
            }
        }
    }

    public void drawAttackBox(Graphics g) {
        //To debug the attack box
        g.setColor(Color.red);
        g.drawRect((int)attackBox.x, (int)attackBox.y, (int)attackBox.width, (int)attackBox.height);
    }
}
