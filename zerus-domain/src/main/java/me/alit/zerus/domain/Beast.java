package me.alit.zerus.domain;

/**
 * Represents beast entity.
 *
 * @author Alexander Litvinenko
 */
public class Beast {
    private int x;
    private int y;

    public Beast() {
    }

    public Beast(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
