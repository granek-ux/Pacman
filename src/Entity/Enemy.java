package Entity;

public class Enemy {
    private int x, y;
    private int currentid;
    private static int id = 0;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;
        currentid = id++;
        if (id == 4) id =0;
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

    public int getId() {
        return currentid;
    }
}
