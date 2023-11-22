package ui;

import java.awt.Color;
import java.awt.Graphics;

import utilz.Constants;

import static utilz.Constants.PlayerConstants.SCORE;

public class ScorePanel {
    int scoreRectangleSizeX = 300;
    int scoreRectangleSizeY = 80;

    public ScorePanel() {

    }

    public void draw(Graphics g) {
        g.setColor(new Color(3, 252, 236));
        g.fillRect(750, 20, scoreRectangleSizeX, scoreRectangleSizeY);

        g.setColor(Color.black);
        g.drawString("Score : " + SCORE, 850, 70);
    }

    public static void resetScore() {
        SCORE = 0;
    }
}
