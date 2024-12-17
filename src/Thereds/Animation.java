package Thereds;

import Structure.FloorPacman;

public class Animation implements Runnable {
    private boolean isGameRunning = true;

    public void setGameRunning(boolean gameRunning) {
        isGameRunning = gameRunning;
    }

    @Override
    public void run() {
        while (isGameRunning) {
            switch (FloorPacman.getOpeningStatus()) {
                case 1, 2, 3, 4, 5:
                    FloorPacman.setOpeningStatus(FloorPacman.getOpeningStatus() + 1);
                    break;
                default:
                    FloorPacman.setOpeningStatus(1);
            }
            try {
                Thread.sleep(125);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
