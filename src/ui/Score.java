package ui;

public class Score {
    private int value = 0;

    public Score() {}

    public void add(int points) {
        value += points;
    }

    public int getValue() {
        return value;
    }

    public void resetScore() {
        value = 0;
    }
}
