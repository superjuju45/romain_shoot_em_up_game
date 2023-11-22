//package ui;

public class menuButton {
    private int xPos, yPos, rowIndex;
    private Gamestate state;
    private BufferedImages[] imgs;

    public menuButton(int xPos, int yPos, int rowIndex, Gamestate state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImgs();
    }

    private void loadImgs() {
        imgs = new BufferedImage[];
}
