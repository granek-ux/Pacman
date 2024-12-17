package Entity;

public class Person {

    private int x, y, Health;

    public Person(int x, int y) {
        this.x = x;
        this.y = y;
        this.Health = 3;
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public int getHealth() {
        return Health;
    }

    public void setHealth(int health) {
        Health = health;
    }
}
