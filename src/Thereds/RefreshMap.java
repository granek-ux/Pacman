package Thereds;

import Entity.Enemy;
import Entity.Person;
import GUI.GameOver;
import GUI.Menu;
import Enums.Rotation;
import Structure.*;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class RefreshMap implements Runnable {

    private int[][] arr;
    private int[][] oldarr;
    private JLabel[][] jLabels;
    private JPanel panel;
    private JFrame window;
    private MovmentPlayer movmentPlayer;
    private MovmentEnemy movmentEnemy1, movmentEnemy2, movmentEnemy3, movmentEnemy4;
    private SpawningBonus spawningBonus;
    private Time time;
    private Animation animation;
    private Thread threadMp, threadMe1, threadMe2, threadMe3, threadMe4, threadSB, threadTi, threadAn;
    private Enemy enemy1, enemy2, enemy3, enemy4;
    private long playerMovmentTime, EnemyMowmentTime;
    private Person person;
    private boolean IsGameRunning = true;
    private static boolean playerShied = false;

    private int openingStatus;

    public static void setPlayerShied(boolean playerShied) {
        RefreshMap.playerShied = playerShied;
    }

    public int getOpeningStatus() {
        return openingStatus;
    }

    public void setOpeningStatus(int openingStatus) {
        this.openingStatus = openingStatus;
    }

    public boolean getisGameRunning() {
        return IsGameRunning;
    }

    public RefreshMap(int[][] arr, JPanel panel, JFrame jFrame, Person person, Enemy[] enemies) {
        this.arr = new int[arr.length][];

        for (int i = 0; i < arr.length; i++) {
            this.arr[i] = new int[arr[i].length];
            System.arraycopy(arr[i], 0, this.arr[i], 0, this.arr[i].length);
        }
        this.oldarr = arr;

        this.panel = panel;
        panel.setLayout(new GridLayout(arr.length, arr[0].length));
        this.window = jFrame;

        jLabels = new JLabel[arr.length][arr[0].length];

        buildteble();

        enemy1 = enemies[0];
        enemy2 = enemies[1];
        enemy3 = enemies[2];
        enemy4 = enemies[3];

        this.person = person;

        movmentPlayer = new MovmentPlayer(person, arr);
        threadMp = new Thread(movmentPlayer);

        movmentEnemy1 = new MovmentEnemy(enemy1, arr);
        threadMe1 = new Thread(movmentEnemy1);

        movmentEnemy2 = new MovmentEnemy(enemy2, arr);
        threadMe2 = new Thread(movmentEnemy2);

        movmentEnemy3 = new MovmentEnemy(enemy3, arr);
        threadMe3 = new Thread(movmentEnemy3);

        movmentEnemy4 = new MovmentEnemy(enemy4, arr);
        threadMe4 = new Thread(movmentEnemy4);

        spawningBonus = new SpawningBonus(arr);
        threadSB = new Thread(spawningBonus);

        time = new Time();
        threadTi = new Thread(time);

        animation = new Animation();
        threadAn = new Thread(animation);


    }

    private void buildteble() {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {

                jLabels[i][j] = switch (arr[i][j]) {
                    case 50 -> new FloorBonus(0);
                    case 51 -> new FloorBonus(1);
                    case 52 -> new FloorBonus(2);
                    case 53 -> new FloorBonus(3);
                    case 54 -> new FloorBonus(4);
                    case 40 -> new FloorGhost(0);
                    case 41 -> new FloorGhost(1);
                    case 42 -> new FloorGhost(2);
                    case 43 -> new FloorGhost(3);
                    case 3 -> new FloorPacman(playerShied, openingStatus);
                    case 2 -> new Floor();
                    case 0 -> new FloorPoint();
                    default -> new Wall();
                };
            }
        }
    }

    public void takeDamage() {

        boolean tempbool = false;
        if (!playerShied) {
            if (arr[movmentPlayer.getX()][movmentPlayer.getY() + 1] / 10 == 4) tempbool = true;
            if (arr[movmentPlayer.getX() - 1][movmentPlayer.getY()] / 10 == 4) tempbool = true;
            if (arr[movmentPlayer.getX() + 1][movmentPlayer.getY()] / 10 == 4) tempbool = true;
            if (arr[movmentPlayer.getX()][movmentPlayer.getY() - 1] / 10 == 4) tempbool = true;
        }
        if (tempbool) {
            person.setHealth(person.getHealth() - 1);
            arr[movmentPlayer.getX()][movmentPlayer.getY()] = 0;

            movmentPlayer.setX(person.getX());
            movmentPlayer.setY(person.getY());

            arr[movmentEnemy1.getX()][movmentEnemy1.getY()] = movmentEnemy1.getOnWhatStanding();
            arr[movmentEnemy2.getX()][movmentEnemy2.getY()] = movmentEnemy2.getOnWhatStanding();
            arr[movmentEnemy3.getX()][movmentEnemy3.getY()] = movmentEnemy3.getOnWhatStanding();
            arr[movmentEnemy4.getX()][movmentEnemy4.getY()] = movmentEnemy4.getOnWhatStanding();

            movmentEnemy1.setOriginalXandY();
            movmentEnemy2.setOriginalXandY();
            movmentEnemy3.setOriginalXandY();
            movmentEnemy4.setOriginalXandY();

            if (person.getHealth() == -1) {
                window.dispose();
                IsGameRunning = false;
                new GameOver(movmentPlayer.getPoints());
            }
        }
    }

    public void setRot(Rotation rot) {
        movmentPlayer.setRot(rot);
    }

    private boolean isAllPointColected() {
        boolean bool = true;
        for (int[] i : arr)
            for (int j : i)
                if (j == 0) bool = false;
        return bool;
    }

    private void reset() {
        if (isAllPointColected()) {

            window.dispose();
            IsGameRunning = false;
            new GameOver(movmentPlayer.getPoints());
        }
    }

    @Override
    public void run() {
        threadMp.start();
        threadMe1.start();
        threadMe2.start();
        threadMe3.start();
        threadMe4.start();
        threadSB.start();
        threadTi.start();
        threadAn.start();


        JLabel pointsLabel = new JLabel();
        pointsLabel.setSize(new Dimension(60, 60));
        pointsLabel.setForeground(Color.lightGray);

        JLabel labelHearth = new JLabel();
        labelHearth.setSize(new Dimension(60, 60));
        labelHearth.setForeground(Color.lightGray);

        JLabel labeltime = new JLabel();
        labeltime.setSize(new Dimension(60, 60));
        labeltime.setForeground(Color.lightGray);

        JPanel infoPanel = new JPanel();
        infoPanel.setBackground(new Color(5, 5, 5));

        window.add(infoPanel, BorderLayout.NORTH);

        JButton exitbutton = new JButton("EXIT");
        exitbutton.setFocusable(false);
        exitbutton.setSize(60, 60);
        exitbutton.setBackground(Color.red);
        exitbutton.setForeground(Color.LIGHT_GRAY);


        JPanel exitPanel = new JPanel();
        exitPanel.setBackground(new Color(5, 5, 5));
        exitPanel.add(exitbutton);

        exitbutton.addActionListener(
                e -> {
                    IsGameRunning = false;
                    window.dispose();
                    new Menu();
                }
        );

        window.add(exitPanel, BorderLayout.SOUTH);

        while (IsGameRunning) {
            oldarr = Arrays.copyOf(arr, arr.length);

            arr = movmentEnemy1.getArr();


            takeDamage();

            buildteble();
            reset();


            infoPanel.remove(labeltime);
            infoPanel.remove(labelHearth);
            infoPanel.remove(pointsLabel);
            labelHearth.setText(" Ilosc zyc: " + person.getHealth());
            pointsLabel.setText(" Ilosc punktow: " + movmentPlayer.getPoints());
            labeltime.setText(" Czas: " + time.getTimer());

            panel.removeAll();

            for (JLabel[] i : jLabels)
                for (JLabel j : i)
                    panel.add(j);


            infoPanel.add(labelHearth);
            infoPanel.add(pointsLabel);
            infoPanel.add(labeltime);
            window.add(panel);
            window.repaint();
            window.revalidate();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        movmentEnemy1.setGameStillRunning(IsGameRunning);
        movmentEnemy2.setGameStillRunning(IsGameRunning);
        movmentEnemy3.setGameStillRunning(IsGameRunning);
        movmentEnemy4.setGameStillRunning(IsGameRunning);
        movmentPlayer.setGameStillRunning(IsGameRunning);
        spawningBonus.setGameRunning(IsGameRunning);
        animation.setGameRunning(IsGameRunning);
        time.setGameStillRunning(IsGameRunning);
    }
}
