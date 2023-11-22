package objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.EnemyManager;
import entities.NinjaBird;
import entities.Player;
import main.Game;
import utilz.LoadSave;
import static utilz.HelpMethods.IsProjectileHittingLevel;

import static objects.Projectile.*;

public class ObjectManager {
    //private Playing playing;
    private BufferedImage tennisBallImg;
    public static ArrayList<Projectile> projectiles = new ArrayList<>();
    private Game game;
    private static int xShootOffset = 50;
    //private int ballcpt = 0;
    //private boolean last_removed = false;
    public ObjectManager(Game game) {
        this.game = game;
        loadImgs();
    }

    public void update(EnemyManager enemyManager) {
        updateProjectiles(enemyManager);
        // System.out.println("update_done"); //Mouchard
    }

    private void updateProjectiles(EnemyManager enemyManager) {
        for(Projectile p : projectiles) {
            if(p.isActive()) {
                p.updatePos();
                //System.out.println("update_done"); //Mouchard
                for(NinjaBird b : enemyManager.birds) {
                    if(p.getHitbox().intersects(b.getHitbox())) {
                        if(b.isActive()) {
                            b.hurt(30);
                            p.setActive(false);
                            //p.remove();
                            //projectiles.remove(ballcpt);
                        }
                    } else if(IsProjectileHittingLevel(p)) {
                        p.setActive(false);
                        //projectiles.remove(ballcpt);
                    }
                }
            }
            
        }
        // System.out.println("update_done"); //Mouchard
    }

    public static void shootRomain(Player player) {
        projectiles.add(new Projectile((int)(player.getHitbox().x + xShootOffset),(int)player.getHitbox().y, 1)); 
    }

    private void loadImgs() {
        tennisBallImg = LoadSave.GetSpriteAtlas(LoadSave.TENNIS_BALL);
    }

    public void draw(Graphics g) {
        drawProjectiles(g);
        //System.out.println("update_done"); // Mouchard
    }

    private void drawProjectiles(Graphics g) {
        for(Projectile p: projectiles) {
            if(p.isActive()) {
                g.drawImage(tennisBallImg, (int)(p.getHitbox().x - tennisBallxOffset), (int)(p.getHitbox().y - tennisBallyOffset), tennisBallWidth, tennisBallHeight, null);
                //System.out.println("update_done"); //Mouchard
                //p.drawHitbox(g);
            }
        }
    }
}
