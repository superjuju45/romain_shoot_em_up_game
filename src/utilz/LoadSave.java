package utilz;

import static utilz.Constants.EnemyConstants.NINJA_BIRD;
import static utilz.Constants.EnemyConstants.NINJA_BIRD_X_SPAWN;
import static utilz.Constants.EnemyConstants.NINJA_BIRD_Y_SPAWN;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entities.NinjaBird;
import main.GamePanel;
import utilz.Constants.EnemyConstants;
import static objects.ObjectManager.projectiles;
import static entities.NinjaBird.birdspeed;

public class LoadSave {
    //private BufferedImage img;
    private static final String source = "C:/Users/Julien D/Documents/Entrainement&formation_boulot/Exos_projets_java/Romain_video_game/Videogame2_v1.1/src/res/";
    public static final String BACK_GROUND = "terrain_de_tennis_bckgrnd.png";
    public static final String ROMAIN = "Romain_sprite_without_bg.png";
    public static final String TENNIS_BALL = "balle_tennis_without_bg.png";
    public static final String NINJA_BIRD = "oiseau_without_bg.png";
    public static final String STATUS_BAR = "health_power_bar.png";

    //variables statiques temporelles
    private static ArrayList<NinjaBird> list = new ArrayList<>();
    private static int[] birdspawns = {125, 250, 375, 500, 90, 177, 302, 437};
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

    static double deltaU = 0;
    static double deltaF = 0;
    static double deltabird = 0;

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

    public static ArrayList<NinjaBird> GetBirds() {

        BufferedImage img = GetSpriteAtlas(BACK_GROUND);
        // for (int j = 0; j < img.getHeight(); j++){
        //     for (int i = 0; i < img.getWidth() ; i++) {
        //         Color color = new Color(img.getRGB(i,j));
        //         int value = color.getGreen();
        //         if (value == EnemyConstants.NINJA_BIRD) {
        //             list.add(new NinjaBird(NINJA_BIRD_X_SPAWN, NINJA_BIRD_Y_SPAWN)); //endroit d'où spawn les oiseaux
        //             //point améliorable par la suite
        //         }
        //     }
        // }
        //list.add(new NinjaBird(NINJA_BIRD_X_SPAWN, NINJA_BIRD_Y_SPAWN));*
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
                //previousTime = currentTime;
                //currentTime = System.currentTimeMillis();
                
                // while (deltaInterWaveTime <= 1) {
                // previousTime = currentTime;
                // currentTime = System.currentTimeMillis();
                // deltaInterWaveTime += (currentTime - previousTime)/interWaveTime;
                    
                // }
                    //régler le problème du freeze
                    //tenter la méthode basique du double if 🙃
                    // aussi penser au spam tir => crash
                if (deltaInterWaveTime >= 1) {
                    deltaInterWaveTime = 0;
                    previousTime = currentTime;
                    list.clear();
                    projectiles.clear();
                    birdspeed *= 1.2;
                    spawnTime *= 0.93;
                    deltaWaveTime = 0;
                }
                
            } else {
                list.add(new NinjaBird(NINJA_BIRD_X_SPAWN, birdspawns[cpt]));
            }
            
        }
        return list;
    }

    public static void drawDeltaWaveTime(Graphics g) {
        g.setColor(new Color(255, 0, 0));
        g.drawString(String.valueOf(deltaWaveTime), 50, 550);
    }
}