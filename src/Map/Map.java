package Map;

public abstract class Map {
    protected int[][] map;
    protected int PlayerX, PlayerY;
    protected int Enemy1X, Enemy1Y;
    protected int Enemy2X, Enemy2Y;
    protected int Enemy3X, Enemy3Y;
    protected int Enemy4X, Enemy4Y;
    protected String name;

    public int[][] getMap() {
        return map;
    }

    public int getPlayerX() {
        return PlayerX;
    }

    public int getPlayerY() {
        return PlayerY;
    }

    public int getEnemy1X() {
        return Enemy1X;
    }

    public int getEnemy1Y() {
        return Enemy1Y;
    }

    public int getEnemy2X() {
        return Enemy2X;
    }

    public int getEnemy2Y() {
        return Enemy2Y;
    }

    public int getEnemy3X() {
        return Enemy3X;
    }

    public int getEnemy3Y() {
        return Enemy3Y;
    }

    public int getEnemy4X() {
        return Enemy4X;
    }

    public int getEnemy4Y() {
        return Enemy4Y;
    }

    public String getName() {
        return name;
    }
}