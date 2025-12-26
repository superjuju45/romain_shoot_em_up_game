package entities;

import static utilz.Constants.EnemyConstants.*;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import main.Game;
import utilz.LoadSave;

public class EnemyManager {

    private Game game;
    private BufferedImage flyingSaucerImage;
    public static ArrayList<FlyingSaucer> flyingSaucers = new ArrayList<>();
    private float xFlyingSaucerOffset = 0;
    private float yFlyingSaucerOffset = 35;

    public EnemyManager(Game game){
        this.game = game;
        addEnemies();
    }

    public void addEnemies() {
        flyingSaucers = LoadSave.GetSaucers();
        System.out.println("size of flyingSaucers:" + flyingSaucers.size());
    }

    public void update(Player player) {
        for (FlyingSaucer b : flyingSaucers) {
            if(b.isActive()) {
                b.update(player);
            }
            if(b.getHitbox().x <= -200) {
                b.setActive(false);
            }
        }
    }

    public void draw(Graphics g) {
        drawSaucers(g);
    }

    private void drawSaucers(Graphics g) {
        for (FlyingSaucer b : flyingSaucers) {
            if(b.isActive()) {
                BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.FLYING_SAUCER);
                g.drawImage(temp, (int)(b.getHitbox().x - xFlyingSaucerOffset), (int)(b.getHitbox().y - yFlyingSaucerOffset), FLYING_SAUCER_WIDTH_DEFAULT, FLYING_SAUCER_HEIGHT_DEFAULT, null);
                //b.drawHitbox(g);
                //b.drawAttackBox(g);
            }
        }
    }

    public void resetAllEnemies() {
       flyingSaucers.clear();
    }
}
