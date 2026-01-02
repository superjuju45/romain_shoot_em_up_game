package utilz;

import static utilz.Constants.EnemyConstants.FLYING_SAUCER;
import static utilz.Constants.EnemyConstants.FLYING_SAUCER_X_SPAWN;
import static utilz.Constants.EnemyConstants.FLYING_SAUCER_Y_SPAWN;
import static objects.ObjectManager.projectiles;
import static entities.FlyingSaucer.saucerSpeed;
import static utilz.Constants.EnemyConstants.ENEMY_HIT_SCORE;
import static utilz.Constants.EnemyConstants.ENEMY_KILLED_SCORE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entities.FlyingSaucer;
import entities.EnemyListeners.EnemyDeathListener;
import entities.EnemyListeners.EnemyHitListener;
import main.GamePanel;
import utilz.Constants.EnemyConstants;
import java.io.File;
import ui.Score;

public class LoadSave {
    //private BufferedImage img;
    private static final String currentFileName = System.getProperty("user.dir");
    private static final String source = new File(currentFileName).getPath() + "/src/res/";
    public static final String BACK_GROUND = "space_bg.png";
    public static final String SPACESHIP = "spaceship_reverse_without_bg.png";
    public static final String MISSILE = "missile_fumee_without_bg.png";
    public static final String FLYING_SAUCER = "soucoupe_without_bg.png";
    public static final String STATUS_BAR = "health_power_bar.png";

    //variables statiques temporelles
    private static ArrayList<FlyingSaucer> list = new ArrayList<>();
    private static int[] saucerspawns = {125, 250, 375, 500, 90, 177, 302, 437};
    private static int cpt = 0;
    private static long previousTime = System.currentTimeMillis();
    private static double deltaTime = 0;
    private static double deltaWaveTime = 0;
    private static double deltaInterWaveTime = 0;
    private static double spawnTime = 1e3;
    private static double waveTime = 30e3;
    private static double interWaveTime = 4e3;
    private static double deltaInterWaveTime_int = 0;

    private static final int FPS_SET = 120;
    private static final int UPS_SET = 200;

    public static Score score;
    private static EnemyHitListener enemyHitListener = new EnemyHitListener() {
        @Override
        public void action() {
            score.add(ENEMY_HIT_SCORE);
        }
    };
    private static EnemyDeathListener enemyKilledListener = new EnemyDeathListener() {
        @Override
        public void action() {
            score.add(ENEMY_KILLED_SCORE);
        }
    };

    static double deltaU = 0;
    static double deltaF = 0;
    static double deltasaucer = 0;

    static GamePanel gamePanel;

    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new FileInputStream(source + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return(img);
        }
    }

    public static ArrayList<FlyingSaucer> GetSaucers() {

        BufferedImage img = GetSpriteAtlas(BACK_GROUND);
        long currentTime = System.currentTimeMillis();
        deltaTime += (currentTime - previousTime) / spawnTime;
        deltaWaveTime += (currentTime - previousTime) / waveTime;
        deltaInterWaveTime_int = (currentTime - previousTime)/interWaveTime;
        previousTime = currentTime;
        if(deltaTime >= 1) {
            deltaTime--;
            cpt += 1;
            if(cpt >= 8) {
                cpt = 0;
            }

            if (deltaWaveTime >= 1) {
                deltaInterWaveTime += deltaInterWaveTime_int;
                if (deltaInterWaveTime >= 1) {
                    deltaInterWaveTime = 0;
                    previousTime = currentTime;
                    list.clear();
                    projectiles.clear();
                    saucerSpeed *= 1.2;
                    spawnTime *= 0.93;
                    deltaWaveTime = 0;
                }
                
            } else {
                FlyingSaucer flyingSaucer = new FlyingSaucer(FLYING_SAUCER_X_SPAWN, saucerspawns[cpt]);
                flyingSaucer.addEnemyHitListener(enemyHitListener);
                flyingSaucer.addEnemyDeathListener(enemyKilledListener);
                list.add(flyingSaucer);
            }
        }
        return list;
    }

    public static void drawDeltaWaveTime(Graphics g) {
        g.setColor(new Color(255, 0, 0));
        g.drawString(String.valueOf(deltaWaveTime), 50, 550);
    }

    public void setScore(Score score) {
        LoadSave.score = score;
    }
}