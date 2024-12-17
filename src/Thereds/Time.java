package Thereds;

public class Time implements Runnable {
    private int timer = 0;
    private boolean gameStillRunning = true;

    public void setGameStillRunning(boolean gameStillRunning) {
        this.gameStillRunning = gameStillRunning;
    }

    public int getTimer() {
        return timer;
    }

    @Override
    public void run() {
        while (gameStillRunning) {
            timer++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
