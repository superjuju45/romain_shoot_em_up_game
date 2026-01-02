package main;

import java.awt.Graphics;

import entities.EnemyManager;
import entities.Player;
import objects.ObjectManager;
import ui.GameOverOverlay;
import ui.ScorePanel;
import utilz.LoadSave;
import static utilz.Constants.PlayerConstants.*;
import ui.Score;

public class Game implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel; 
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private Player player;
    private ObjectManager objectManager;
    private EnemyManager enemyManager;
    private static GameOverOverlay gameOverOverlay;
    private ScorePanel scorePanel;
    private Score score;

    private static boolean gameOver;

    public Game() {
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();

        startGameLoop();
    }

    private void initClasses() {
        player = new Player(PLAYER_X_SPAWN, PLAYER_Y_SPAWN, 175, 175, this);
        objectManager = new ObjectManager(this);
        enemyManager = new EnemyManager(this);
        gameOverOverlay = new GameOverOverlay(this);
        score = new Score();
        LoadSave.score = score;
        scorePanel = new ScorePanel(score);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        player.update();
        objectManager.update(enemyManager);
        enemyManager.update(player);
    }

    public void render(Graphics g) {
        player.render(g);
        objectManager.draw(g);
        enemyManager.draw(g);
        if(gameOver) {
            gameOverOverlay.draw(g);
        }
        scorePanel.draw(g);
    }

    public void resetAll() {
        gameOver = false;
        player.resetAll();
        enemyManager.resetAllEnemies();
        score.resetScore();
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET ;
        double timePerUpdate = 1000000000.0/UPS_SET;
        double timePerSaucer = 500000000.0;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;
        double deltasaucer = 0;

        while(true) {

            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            deltasaucer += (currentTime - previousTime) / timePerSaucer;
            previousTime = currentTime;

            if(deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if(deltaF >= 1) {
                gamePanel.repaint();
                frames ++;
                deltaF --;
            }

            if(deltasaucer >= 1) {
                enemyManager.flyingSaucers = LoadSave.GetSaucers();
                deltasaucer--;
            }

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
