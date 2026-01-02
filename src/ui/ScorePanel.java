package ui;

import java.awt.Color;
import java.awt.Graphics;

public class ScorePanel {
    int scoreRectangleSizeX = 300;
    int scoreRectangleSizeY = 80;
    private Score score;

    public ScorePanel(Score score) {
        this.score = score;
    }

    public void draw(Graphics g) {
        g.setColor(new Color(3, 252, 236));
        g.fillRect(750, 20, scoreRectangleSizeX, scoreRectangleSizeY);

        g.setColor(Color.black);
        g.drawString("Score : " + score.getValue(), 850, 70);
    }
}
