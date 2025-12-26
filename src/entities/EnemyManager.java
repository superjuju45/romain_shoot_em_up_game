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
    private BufferedImage birdImage;
    public static ArrayList<NinjaBird> birds = new ArrayList<>();
    private float xBirdOffset = 0;
    private float yBirdOffset = 35;

    public EnemyManager(Game game){
        this.game = game;
        addEnemies();
    }

    public void addEnemies() {
        birds = LoadSave.GetBirds();
        System.out.println("size of birds:" + birds.size());
    }

    public void update(Player player) {
        for (NinjaBird b : birds) {
            if(b.isActive()) {
                b.update(player);
            }
            if(b.getHitbox().x <= -200) {
                b.setActive(false);
            }
        }
    }

    public void draw(Graphics g) {
        drawBirds(g);
    }

    private void drawBirds(Graphics g) {
        for (NinjaBird b : birds) {
            if(b.isActive()) {
                BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.NINJA_BIRD);
                g.drawImage(temp, (int)(b.getHitbox().x - xBirdOffset), (int)(b.getHitbox().y - yBirdOffset), NINJA_BIRD_WIDTH_DEFAULT, NINJA_BIRD_HEIGHT_DEFAULT, null);
                //b.drawHitbox(g);
                //b.drawAttackBox(g);
            }
        }
    }

    public void resetAllEnemies() {
       birds.clear();
    }
}
