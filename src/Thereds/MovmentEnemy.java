package Thereds;

import Entity.Enemy;
import Enums.Rotation;
import Enums.Whenturn;

import java.util.ArrayList;
import java.util.List;

public class MovmentEnemy implements Runnable {
    private static long waitingTime = 200;
    private int x, y;
    private Whenturn whenturn;
    private boolean willturn;
    private Enemy enemy;
    private int[][] arr;
    private final double chaceOfFastTurn = 0.5;
    Rotation rotation = Rotation.nothing, oppositerotation;
    private List<Rotation> possibleturns;
    private int onWhatStanding;
    private boolean isGameStillRunning = true;

    public MovmentEnemy(Enemy enemy, int[][] arr) {
        this.enemy = enemy;
        this.arr = arr;
        x = enemy.getX();
        y = enemy.getY();
        willturn = false;
        possibleturns = new ArrayList<Rotation>();
        onWhatStanding = 0;
    }

    private void selectwhenturn() {
        if (!willturn) {
            double rand = Math.random();
            if (rand < chaceOfFastTurn) {
                whenturn = Whenturn.firstocasion;
                willturn = true;

            } else if (rand < 0.8) {
                whenturn = Whenturn.later;
                willturn = true;
            } else {
                whenturn = Whenturn.lastocasion;
                willturn = true;
            }
        }
    }

    private void opprot() {
        oppositerotation = switch (rotation) {
            case north -> Rotation.south;
            case south -> Rotation.north;
            case east -> Rotation.west;
            case west -> Rotation.east;
            case nothing -> Rotation.nothing;
        };
    }

    private void choiceOfDirection() {
        opprot();
        if (arr[x - 1][y] != 1 && rotation != Rotation.north && oppositerotation != Rotation.north)
            possibleturns.add(Rotation.north);
        if (arr[x + 1][y] != 1 && rotation != Rotation.south && oppositerotation != Rotation.south)
            possibleturns.add(Rotation.south);
        if (arr[x][y + 1] != 1 && rotation != Rotation.east && oppositerotation != Rotation.east)
            possibleturns.add(Rotation.east);
        if (arr[x][y - 1] != 1 && rotation != Rotation.west && oppositerotation != Rotation.west)
            possibleturns.add(Rotation.west);

        int rand = (int) (Math.random() * possibleturns.size() - 1);

        rotation = possibleturns.get(rand);

        possibleturns.clear();
    }

    private boolean IspossibleTurn() {
        switch (rotation) {
            case north, south:
                if (arr[x][y + 1] != 1) return true;
                if (arr[x][y - 1] != 1) return true;
                break;
            case west, east:
                if (arr[x - 1][y] != 1) return true;
                if (arr[x + 1][y] != 1) return true;
                break;
        }
        return false;
    }

    @Override
    public void run() {
        choiceOfDirection();
        selectwhenturn();
        while (isGameStillRunning) {
            switch (whenturn) {

                case lastocasion:
                    switch (rotation) {
                        case north:
                            if (arr[x - 1][y] != 1) {
                                arr[x--][y] = onWhatStanding;
                                if (arr[x][y] != 3 && arr[x][y] / 10 != 4) onWhatStanding = arr[x][y];
                            } else {
                                willturn = false;
                                choiceOfDirection();
                                selectwhenturn();
                            }
                            break;

                        case south:
                            if (arr[x + 1][y] != 1) {
                                arr[x++][y] = onWhatStanding;
                                if (arr[x][y] != 3 && arr[x][y] / 10 != 4) onWhatStanding = arr[x][y];
                            } else {
                                willturn = false;
                                choiceOfDirection();
                                selectwhenturn();
                            }
                            break;
                        case east:
                            if (arr[x][y + 1] != 1) {
                                arr[x][y++] = onWhatStanding;
                                if (arr[x][y] != 3 && arr[x][y] / 10 != 4) onWhatStanding = arr[x][y];
                            } else {
                                willturn = false;
                                choiceOfDirection();
                                selectwhenturn();
                            }
                            break;

                        case west:
                            if (arr[x][y - 1] != 1) {
                                arr[x][y--] = onWhatStanding;
                                if (arr[x][y] != 3 && arr[x][y] / 10 != 4) onWhatStanding = arr[x][y];
                            } else {
                                willturn = false;
                                choiceOfDirection();
                                selectwhenturn();
                            }
                            break;
                    }
                    break;
                case firstocasion:

                    switch (rotation) {
                        case north:
                            if (arr[x - 1][y] != 1) {
                                arr[x--][y] = onWhatStanding;
                                if (arr[x][y] != 3 && arr[x][y] / 10 != 4) onWhatStanding = arr[x][y];
                                if (IspossibleTurn()) {
                                    willturn = false;
                                    choiceOfDirection();
                                    selectwhenturn();
                                }
                            } else {
                                willturn = false;
                                choiceOfDirection();
                                selectwhenturn();
                            }
                            break;

                        case south:
                            if (arr[x + 1][y] != 1) {
                                arr[x++][y] = onWhatStanding;
                                if (arr[x][y] != 3 && arr[x][y] / 10 != 4) onWhatStanding = arr[x][y];
                                if (IspossibleTurn()) {
                                    willturn = false;
                                    choiceOfDirection();
                                    selectwhenturn();
                                }
                            } else {
                                willturn = false;
                                choiceOfDirection();
                                selectwhenturn();
                            }
                            break;

                        case east:
                            if (arr[x][y + 1] != 1) {
                                arr[x][y++] = onWhatStanding;
                                if (arr[x][y] != 3 && arr[x][y] / 10 != 4) onWhatStanding = arr[x][y];
                                if (IspossibleTurn()) {
                                    willturn = false;
                                    choiceOfDirection();
                                    selectwhenturn();
                                }
                            } else {
                                choiceOfDirection();
                                willturn = false;
                                selectwhenturn();
                            }
                            break;

                        case west:
                            if (arr[x][y - 1] != 1) {
                                arr[x][y--] = onWhatStanding;
                                if (arr[x][y] != 3 && arr[x][y] / 10 != 4) onWhatStanding = arr[x][y];
                                if (IspossibleTurn()) {
                                    willturn = false;
                                    choiceOfDirection();
                                    selectwhenturn();
                                }
                            } else {
                                willturn = false;
                                choiceOfDirection();
                                selectwhenturn();
                            }
                            break;
                    }
                    break;
                case later:

                    switch (rotation) {
                        case north:
                            if (arr[x - 1][y] != 1) {
                                arr[x--][y] = onWhatStanding;
                                if (arr[x][y] != 3 && arr[x][y] / 10 != 4) onWhatStanding = arr[x][y];
                                if (IspossibleTurn()) {
                                    willturn = false;
                                    selectwhenturn();
                                }
                            } else {
                                willturn = false;
                                choiceOfDirection();
                                selectwhenturn();
                            }
                            break;

                        case south:
                            if (arr[x + 1][y] != 1) {
                                arr[x++][y] = onWhatStanding;
                                if (arr[x][y] != 3 && arr[x][y] / 10 != 4) onWhatStanding = arr[x][y];
                                if (IspossibleTurn()) {
                                    willturn = false;
                                    selectwhenturn();
                                }
                            } else {
                                willturn = false;
                                choiceOfDirection();
                                selectwhenturn();
                            }
                            break;

                        case east:
                            if (arr[x][y + 1] != 1) {
                                arr[x][y++] = onWhatStanding;
                                if (arr[x][y] != 3 && arr[x][y] / 10 != 4) onWhatStanding = arr[x][y];
                                if (IspossibleTurn()) {
                                    willturn = false;
                                    selectwhenturn();
                                }
                            } else {
                                willturn = false;
                                choiceOfDirection();
                                selectwhenturn();
                            }
                            break;

                        case west:
                            if (arr[x][y - 1] != 1) {
                                arr[x][y--] = onWhatStanding;
                                if (arr[x][y] != 3 && arr[x][y] / 10 != 4) onWhatStanding = arr[x][y];
                                if (IspossibleTurn()) {
                                    willturn = false;
                                    selectwhenturn();
                                }
                            } else {
                                willturn = false;
                                choiceOfDirection();
                                selectwhenturn();
                            }
                            break;
                    }
                    break;
            }


            arr[x][y] = 40 + enemy.getId();
            arr = arr;
            try {
                Thread.sleep(waitingTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getOnWhatStanding() {
        return onWhatStanding;
    }

    public int[][] getArr() {
        return arr;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public static void setWaitingTime(long waitingTime) {
        MovmentEnemy.waitingTime = waitingTime;
    }

    public void setOriginalXandY() {
        this.x = this.enemy.getX();
        this.y = this.enemy.getY();
    }

    public void setGameStillRunning(boolean gameStillRunning) {
        isGameStillRunning = gameStillRunning;
    }
}
