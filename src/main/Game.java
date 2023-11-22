package main;

import java.awt.Graphics;

import entities.Enemy;
import entities.EnemyManager;
import entities.Player;
import objects.ObjectManager;
import ui.GameOverOverlay;
import ui.ScorePanel;
import utilz.LoadSave;
import static utilz.Constants.PlayerConstants.*;
import static utilz.LoadSave.drawDeltaWaveTime;

//import main.GameWindow;

public class Game implements Runnable{
    private GameWindow gameWindow; //private
    private GamePanel gamePanel; //private
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private Player player;
    private ObjectManager objectManager;
    private EnemyManager enemyManager;
    private static GameOverOverlay gameOverOverlay;
    private ScorePanel scorePanel;

    private static boolean gameOver;

    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();

      
        //enemyManager.addEnemies();
        startGameLoop();
    }

    private void initClasses() {
        player = new Player(PLAYER_X_SPAWN, PLAYER_Y_SPAWN, 175, 175, this);
        objectManager = new ObjectManager(this);
        enemyManager = new EnemyManager(this);
        gameOverOverlay = new GameOverOverlay(this);
        scorePanel = new ScorePanel();
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        player.update();
        objectManager.update(enemyManager);
        enemyManager.update(player);
        // System.out.println("update_done"); // Mouchard
    }

    public void render(Graphics g) {
        player.render(g);
        objectManager.draw(g);
        enemyManager.draw(g);
        drawDeltaWaveTime(g);
        if(gameOver) {
            gameOverOverlay.draw(g);
        }
        scorePanel.draw(g);
    }

    public void resetAll() {
        //TODO: reset player, enemy, etc
        gameOver = false;
        player.resetAll();
        enemyManager.resetAllEnemies();
        ScorePanel.resetScore();
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET ;
        double timePerUpdate = 1000000000.0/UPS_SET;
        double timePerBird = 500000000.0;
        // long lastFrame = System.nanoTime(); //précédente boucle de jeu
        // long now = System.nanoTime(); //précédente boucle de jeu

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;
        double deltabird = 0;

        while(true) {

            // now = System.nanoTime(); //précédente boucle de jeu
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            deltabird += (currentTime - previousTime) / timePerBird;
            previousTime = currentTime;

            if(deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if(deltaF >= 1) {
                gamePanel.repaint();
                // lastFrame = now; lastFrame = now;
                frames ++;
                deltaF --;
            }

            if(deltabird >= 1) {
                enemyManager.birds = LoadSave.GetBirds();
                deltabird--;
            }

            // if(now - lastFrame >= timePerFrame) { //précédente boucle de jeu

            //     gamePanel.repaint(); //précédente boucle de jeu
            //     lastFrame = now; lastFrame = now;
            //     frames ++; //précédente boucle de jeu
            // }

            //fps counter

            if(System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public void windowFocusLost() {
        player.resetDirBooleans();
    }

    public Player getPlayer() {
        return player;
    }

    public ObjectManager getObjectManager() {
        return objectManager;
    }

    public static boolean getGameOver() {
        return gameOver;
    }

    public static GameOverOverlay getGameOverOverlay() {
        return gameOverOverlay;
    }

}
