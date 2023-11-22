package entities;

import main.Game;
import main.GamePanel;
import utilz.LoadSave;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import inputs.KeyboardInputs;
import static utilz.Constants.Directions.*;
import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.CanMoveHere;

public class Player extends Entity {
    GamePanel gp;
    KeyboardInputs keyboardInputs;
    //private BufferedImage img; //Image de Romain en scooter
    private BufferedImage img = utilz.LoadSave.GetSpriteAtlas(utilz.LoadSave.ROMAIN);
    //private int playerDir = -1;
    private boolean moving = false;
    public int perso_speed = 5;
    private boolean left, up, right, down;
    private float xDrawOffset = 30;
    private float yDrawOffset = 40;
    
    //StatusBarUI
    private BufferedImage statusBarImg;

    private int statusBarWidth = 192;
    private int statusBarHeight = 58;
    private int statusBarX = 10;
    private int statusBarY = 10;

    private int healthBarWidth = 150;
    private int healthBarHeight = 4;
    private int healthBarXStart = 34;
    private int healthBarYStart = 14;

    private int maxHealth = 100;
    private int currentHealth = maxHealth;
    private int healthWidth = healthBarWidth;

    private Game game;

    public Player(float x, float y, int width, int height, Game game) {
        super(x,y, width, height);
        this.game = game;
        loadAnimations();
        initHitbox(x , y, 110, 90);
        //importImg();
    }

    private void loadAnimations() {
        statusBarImg = LoadSave.GetSpriteAtlas(LoadSave.STATUS_BAR);
    }

    public void update() {
        updateHealthBar();
        if(currentHealth <= 0) {
            game.setGameOver(true);
            return;
        }        

        updatePos();
        updateHitbox();
    }

    private void updateHealthBar() {
        healthWidth = (int)((currentHealth / (float) maxHealth) * healthBarWidth);
    }

    public void render(Graphics g) {
        g.drawImage(img, (int) (hitbox.x- xDrawOffset), (int) (hitbox.y - yDrawOffset),width, height, null);
        //drawHitbox(g);

        drawUI(g);
    }

    // private void importImg() {
    //     //InputStream is = getClass().getResourceAsStream("C:/Users/Julien D/Documents/Entrainement&formation_boulot/Exos_projets_java/Romain_video_game/Videogame2_v1/src/res/Romain_sprite_without_bg.png");
    //     //InputStream is = getClass().getResourceAsStream("/Romain_sprite_without_bg.png");
    //     try {
    //         img = ImageIO.read(new FileInputStream("C:/Users/Julien D/Documents/Entrainement&formation_boulot/Exos_projets_java/Romain_video_game/Videogame2_v1.1/src/res/Romain_sprite_without_bg.png"));
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     } 
    // }

    // public void setDirection(int direction) {
    //     this.playerDir = direction ;
    //     moving = true;
    // }

    // public void setMoving(boolean moving) {
    //     this.moving = moving;
    // }

    private void drawUI(Graphics g) {
        g.drawImage(statusBarImg, statusBarX, statusBarY, statusBarWidth, statusBarHeight, null);
        g.setColor(Color.red);
        g.fillRect(healthBarXStart + statusBarX, healthBarYStart + statusBarY, healthWidth, healthBarHeight);
    }

    private void updatePos() {
        // if(moving) {
        //     switch(playerDir) {
        //         case LEFT:
        //             x -= perso_speed;
        //             break;
        //         case UP:
        //             y -= perso_speed;
        //             break;
        //         case RIGHT:
        //             x += perso_speed;
        //             break;
        //         case DOWN:
        //             y += perso_speed;
        //             break;
        //     }
        // }
        
        moving = false;
        // if (!left && !right && !up && !down) {
        //     return;
        // }

        float xSpeed = 0, ySpeed = 0;

        if (left && !right) {
            xSpeed = -perso_speed;
            // x-= perso_speed;
            // moving = true;
        } else if (right && !left) {
            xSpeed = perso_speed;
            // x+= perso_speed;
            // moving = true;
        } 

        if (up && !down) {
            ySpeed = -perso_speed;
            // y -= perso_speed;
            // moving = true;    
        } else if (down && !up) {
            ySpeed = perso_speed;
            // y += perso_speed;
            // moving = true;
        }

        //System.out.println(CanMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, (int)hitbox.width, (int)hitbox.height)); //Mouchard
        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y + ySpeed, (int)hitbox.width, (int)hitbox.height)) {
            this.x += xSpeed;
            this.y += ySpeed;
            moving = true;
        }
    }

    public void changeHealth (int value) {
        currentHealth += value;

        if (currentHealth <= 0) {
            currentHealth = 0;
            //gameOver();
        } else if (currentHealth >= maxHealth) {
            currentHealth = maxHealth;
        }
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isDown() {
        return down;
    }

    public void shoot() {
        objects.ObjectManager.shootRomain(this);
    }

    public void resetAll() {
        resetDirBooleans();
        moving = false;
        currentHealth = maxHealth;

        this.x = PLAYER_X_SPAWN;
        this.y = PLAYER_Y_SPAWN;
    }
    
}
