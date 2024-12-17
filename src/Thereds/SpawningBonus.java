package Thereds;

public class SpawningBonus implements Runnable {
    private int x, y;
    private int[][] arr;

    private int whatBonus;

    private boolean IsGameRunning = true;

    public void setGameRunning(boolean gameRunning) {
        IsGameRunning = gameRunning;
    }

    public SpawningBonus(int[][] arr) {
        this.arr = arr;
    }

    private boolean CanSpawn() {
        if (arr[x][y] == 0 || arr[x][y] == 2) return true;
        return false;
    }

    private void Randomising() {
        do {
            x = (int) (Math.random() * arr.length);
            y = (int) (Math.random() * arr[x].length);

        } while (!CanSpawn());
        whatBonus = (int) (Math.random() * 5);
    }

    @Override
    public void run() {
        while (IsGameRunning) {
            if (Math.random() < 0.25) {
                Randomising();
                arr[x][y] = 50 + whatBonus;
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
