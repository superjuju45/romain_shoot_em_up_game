package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Projectile {
    private Rectangle2D.Float hitbox;
    private int dir;
    private int ball_speed = 12;
    private boolean active = true;
    public static int tennisBallWidth = 40, tennisBallHeight = 40;
    private int tennisBallHitboxSizeX = 30, tennisBallHitboxSizeY = 30;
    public static int tennisBallxOffset = 5, tennisBallyOffset = 5;

    public Projectile(int x, int y, int dir) {
        hitbox = new Rectangle2D.Float(x,y, tennisBallHitboxSizeX, tennisBallHitboxSizeY);
        this.dir = dir;
    }

    public void drawHitbox(Graphics g) {
        //For debugging the hitbox
        g.setColor(Color.PINK);
        g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width,  (int) hitbox.height);
    }

    public void updatePos() {
        hitbox.x += dir*ball_speed; 
    }

    public void setPos(int x, int y) {
        hitbox.x = x;
        hitbox.y = y;
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active ;
    }

}
