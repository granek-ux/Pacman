package GUI;

import Entity.Enemy;
import Entity.Person;
import Enums.Rotation;
import Map.Map;
import Thereds.RefreshMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gaming extends JFrame {
    public Gaming(Map map) {
        this.setTitle(map.getName());
        this.setLayout(new BorderLayout());


        Enemy[] enemies = new Enemy[4];

        enemies[0] = new Enemy(map.getEnemy1X(), map.getEnemy1Y());
        enemies[1] = new Enemy(map.getEnemy2X(), map.getEnemy2Y());
        enemies[2] = new Enemy(map.getEnemy3X(), map.getEnemy3Y());
        enemies[3] = new Enemy(map.getEnemy4X(), map.getEnemy4Y());

        JPanel mapPanel = new JPanel(new GridLayout(map.getMap().length, map.getMap()[0].length));

        Person person = new Person(map.getPlayerX(), map.getPlayerY());


        RefreshMap refreshMap = new RefreshMap(map.getMap(), mapPanel, this, person, enemies);
        Thread refresh = new Thread(refreshMap);
        refresh.start();

        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                refreshMap.setRot(switch (e.getKeyChar()) {
                    case 'w', 'W' -> Rotation.north;
                    case 's', 'S' -> Rotation.south;
                    case 'd', 'D' -> Rotation.east;
                    case 'a', 'A' -> Rotation.west;
                    default -> Rotation.nothing;
                });
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        this.setSize(900, 900);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);


    }
}
