package Thereds;

import Entity.Person;
import Enums.Rotation;


public class MovmentPlayer implements Runnable {
    private int x, y;

    private Person person;
    private Rotation rot = Rotation.nothing;

    public int[][] map;

    private int points;

    private int addingsPoits = 100;
    private long waitngTime = 200;

    private Rotation temprot = Rotation.nothing;
    private Boolean isGameStillRunning = true;


    public void setRot(Rotation rot) {
        this.rot = rot;
    }

    public MovmentPlayer(Person person, int[][] arr) {
        this.person = person;
        this.map = arr;
        x = person.getX();
        y = person.getY();
        points = 0;

    }

    private void UseBonus() {
        switch (map[x][y] % 10) {
            case 0:
                person.setHealth(person.getHealth() + 1);
                break;
            case 1:
                Runnable doublePiots = () -> {
                    addingsPoits = 200;
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    addingsPoits = 100;
                };
                new Thread(doublePiots).start();
                break;
            case 2:
                Runnable fasterMovment = () -> {
                    waitngTime = 150;
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    waitngTime = 200;
                };
                new Thread(fasterMovment).start();
                break;
            case 3:
                Runnable slowEnemyMovemt = () -> {
                    MovmentEnemy.setWaitingTime(250);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    MovmentEnemy.setWaitingTime(200);
                };
                new Thread(slowEnemyMovemt).start();
                break;
            case 4:
                Runnable playerShied = () ->
                {
                    RefreshMap.setPlayerShied(true);
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    RefreshMap.setPlayerShied(false);
                };
                new Thread(playerShied).start();
                break;
        }
    }


    public void setX(int x) {
        this.x = x;
    }

    public int getPoints() {
        return points;
    }

    public void setGameStillRunning(Boolean gameStillRunning) {
        isGameStillRunning = gameStillRunning;
    }

    @Override
    public void run() {
        while (isGameStillRunning) {
            switch (rot) {
                case north: {
                    if (map[x - 1][y] != 1) temprot = rot;
                    break;
                }
                case south: {
                    if (map[x + 1][y] != 1) temprot = rot;
                    break;
                }
                case east: {
                    if (map[x][y + 1] != 1) temprot = rot;
                    break;
                }
                case west: {
                    if (map[x][y - 1] != 1) temprot = rot;
                    break;
                }
            }

            switch (temprot) {
                case north: {
                    if (map[x - 1][y] != 1) {
                        if (map[x - 1][y] == 0) points += addingsPoits;
                        map[x--][y] = 2;
                    }
                    break;
                }
                case south: {
                    if (map[x + 1][y] != 1) {
                        if (map[x + 1][y] == 0) points += addingsPoits;
                        map[x++][y] = 2;
                    }
                    break;
                }
                case east: {
                    if (map[x][y + 1] != 1) {
                        if (map[x][y + 1] == 0) points += addingsPoits;
                        map[x][y++] = 2;
                    }
                    break;
                }
                case west: {
                    if (map[x][y - 1] != 1) {
                        if (map[x][y - 1] == 0) points += addingsPoits;
                        map[x][y--] = 2;
                    }
                    break;
                }
            }
            if (map[x][y] / 10 == 5) UseBonus();
            map[x][y] = 3;
            map = map;
            try {
                Thread.sleep(waitngTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
