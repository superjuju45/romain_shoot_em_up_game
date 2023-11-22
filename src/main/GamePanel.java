package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
//import java.awt.event.KeyListener;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
//import inputs.MouseInputs;
//import utilz.LoadSave.*;
//import utilz.Constants.WindowSize.*;



public class GamePanel extends JPanel {

    //private MouseInputs mouseInputs;

    // //fps counter (moved to Game class)
    // private int frames = 0;
    // private long lastCheck = 0;

    // private Color color = new Color(150,20,90);
    // private Random random
    //private BufferedImage backgrndImage; //Image d'arrière-plan
    private BufferedImage backgrndImage = utilz.LoadSave.GetSpriteAtlas(utilz.LoadSave.BACK_GROUND);
    KeyboardInputs keyboardInputs = new KeyboardInputs(this);
    //Player player = new Player(this, keyboardInputs);
    private Game game;

    public GamePanel(Game game) {
        //random = new Random();
        //mouseInputs = new MouseInputs(this);
        this.game = game;

        //importBackgrd();

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        //addMouseListener(mouseInputs);
        //addMouseMotionListener(mouseInputs);
    }



    // private void importBackgrd() {
    //     try { backgrndImage = ImageIO.read(new FileInputStream ("C:/Users/Julien D/Documents/Entrainement&formation_boulot/Exos_projets_java/Romain_video_game/Videogame2_v1.1/src/res/terrain_de_tennis_bckgrnd.png"));
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

    private void setPanelSize() {
        Dimension size = new Dimension(utilz.Constants.WindowSize.WINDOW_SIZE_X, utilz.Constants.WindowSize.WINDOW_SIZE_Y);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void updateGame() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // g.setColor(color);
        // g.fillRect((int) xDelta, (int) yDelta, 200, 50);
        // updateRectangle();
        g.drawImage(backgrndImage,0, 0,utilz.Constants.WindowSize.WINDOW_SIZE_X, utilz.Constants.WindowSize.WINDOW_SIZE_Y, null);
        game.render(g);
    }

    public Game getGame() {
        return game;
    }

    // public void shootProjectile(Graphics g) {
    //     g.drawImage(ball_sprite)
    // }

    // private void updateRectangle() {
    //     xDelta += xDir;
    //     if (xDelta > 400 || xDelta < 0) {
    //         xDir *= -1;
    //         //color = getRndColor();
    //     }

    //     yDelta += yDir;
    //     if (yDelta > 400 || yDelta <0) {
    //         yDir *= -1;
    //         //color = getRndColor();
    //     }
    // }

    // private Color getRndColor() {
    //         int r = random.nextInt(255);
    //         int g = random.nextInt(255);
    //         int b = random.nextInt(255);

    //         return new Color(r,g,b);
    //     }
}
