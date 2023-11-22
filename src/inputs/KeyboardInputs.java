package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Game;
import main.GamePanel;
import static utilz.Constants.Directions.*;

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;
    //public static int speed = 12;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e){
        // if (!main.Game.getGameOver()) {
        //     if (e.getKeyChar() == ' ') {
        //         gamePanel.getGame().getPlayer().shoot();
        //     }
        // }

    }

    @Override
    public void keyReleased(KeyEvent e){
        if (!main.Game.getGameOver()) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_Z:
                    gamePanel.getGame().getPlayer().setUp(false);
                    break;
                case KeyEvent.VK_Q:
                    gamePanel.getGame().getPlayer().setLeft(false);
                    break;
                case KeyEvent.VK_S:
                    gamePanel.getGame().getPlayer().setDown(false);
                    break;
                case KeyEvent.VK_D:
                    gamePanel.getGame().getPlayer().setRight(false);
                    break;
                case KeyEvent.VK_SPACE:
                    gamePanel.getGame().getPlayer().shoot();
                    break;
            }
        }
    }


    @Override
    public void keyPressed(KeyEvent e){
        if(main.Game.getGameOver()) {
            main.Game.getGameOverOverlay().keyPressed(e);
        } else {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_Z:
                    gamePanel.getGame().getPlayer().setUp(true);
                    break;
                case KeyEvent.VK_Q:
                    gamePanel.getGame().getPlayer().setLeft(true);
                    break;
                case KeyEvent.VK_S:
                    gamePanel.getGame().getPlayer().setDown(true);
                    break;
                case KeyEvent.VK_D:
                    gamePanel.getGame().getPlayer().setRight(true);
                    break;
                // case KeyEvent.VK_SPACE:
                //     gamePanel.getGame().getPlayer().shoot();
                //     break;
            }
        }
    }
}
