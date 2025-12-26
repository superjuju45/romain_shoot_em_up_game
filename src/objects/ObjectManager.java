package objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.EnemyManager;
import entities.FlyingSaucer;
import entities.Player;
import main.Game;
import utilz.LoadSave;
import static utilz.HelpMethods.IsProjectileHittingLevel;

import static objects.Projectile.*;

public class ObjectManager {
    private BufferedImage tennisBallImg;
    public static ArrayList<Projectile> projectiles = new ArrayList<>();
    private static int xShootOffset = 50;

    public ObjectManager(Game game) {
        loadImgs();
    }

    public void update(EnemyManager enemyManager) {
        updateProjectiles(enemyManager);
    }

    private void updateProjectiles(EnemyManager enemyManager) {
        for(Projectile p : projectiles) {
            if(p.isActive()) {
                p.updatePos();
                for(FlyingSaucer b : enemyManager.flyingSaucers) {
                    if(p.getHitbox().intersects(b.getHitbox())) {
                        if(b.isActive()) {
                            b.hurt(30);
                            p.setActive(false);
                        }
                    } else if(IsProjectileHittingLevel(p)) {
                        p.setActive(false);
                    }
                }
            }
            
        }
    }

    public static void shootShip(Player player) {
        projectiles.add(new Projectile((int)(player.getHitbox().x + xShootOffset),(int)player.getHitbox().y, 1));
    }

    private void loadImgs() {
        tennisBallImg = LoadSave.GetSpriteAtlas(LoadSave.MISSILE);
    }

    public void draw(Graphics g) {
        drawProjectiles(g);
    }

    private void drawProjectiles(Graphics g) {
        for(Projectile p: projectiles) {
            if(p.isActive()) {
                g.drawImage(tennisBallImg, (int)(p.getHitbox().x - tennisBallxOffset), (int)(p.getHitbox().y - tennisBallyOffset), tennisBallWidth, tennisBallHeight, null);
            }
        }
    }
}
