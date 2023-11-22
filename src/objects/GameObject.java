package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class GameObject {
    
    protected int x, y, objType;
    protected Rectangle2D.Float hitbox;
    protected boolean active = true;
    protected int xDrawOffset, yDrawOffset;

    public GameObject(int x, int y, int objType) {  
        this.x = x;
        this.y = y;
        this.objType = objType;
    }

    protected void initHitbox(int width, int height) {
        hitbox = new Rectangle2D.Float(x, y, (int) (width), (int) (height));
    }

}
