package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import main.Game;

public class GameOverOverlay {
    
    private Game game;
    public GameOverOverlay(Game game) {   
        this.game = game;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, utilz.Constants.WindowSize.WINDOW_SIZE_X, utilz.Constants.WindowSize.WINDOW_SIZE_Y);

        g.setColor(Color.white);
        g.drawString("Game Over", utilz.Constants.WindowSize.WINDOW_SIZE_X / 2, 150);
        g.drawString("Appuyez sur Echap pour reccommencer!", utilz.Constants.WindowSize.WINDOW_SIZE_X / 2, 300);
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            game.resetAll(); 
        }
    }
}
