package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import inputs.KeyboardInputs;



public class GamePanel extends JPanel {

    private BufferedImage backgrndImage = utilz.LoadSave.GetSpriteAtlas(utilz.LoadSave.BACK_GROUND);
    KeyboardInputs keyboardInputs = new KeyboardInputs(this);
    private Game game;

    public GamePanel(Game game) {
        this.game = game;

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
    }

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
        g.drawImage(backgrndImage,0, 0,utilz.Constants.WindowSize.WINDOW_SIZE_X, utilz.Constants.WindowSize.WINDOW_SIZE_Y, null);
        game.render(g);
    }

    public Game getGame() {
        return game;
    }
}
