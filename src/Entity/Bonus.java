package Entity;

public abstract class Bonus {
    private int x, y, id;

    public Bonus(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    abstract public boolean Istouhced();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
