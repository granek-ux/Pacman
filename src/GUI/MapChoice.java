package GUI;

import Map.*;

import javax.swing.*;
import java.awt.*;

public class MapChoice extends JFrame {

    public MapChoice() {
        this.setTitle("Map Choice");
        JPanel panel = new JPanel();
        panel.setBackground(new Color(5, 5, 5));

        JButton button = new JButton("MAP 30 x 30");
        button.setBackground(Color.red);
        button.setForeground(Color.LIGHT_GRAY);

        JButton button1 = new JButton("MAP 21 x 21");
        button1.setBackground(Color.red);
        button1.setForeground(Color.LIGHT_GRAY);

        JButton button2 = new JButton("MAP 16 x 18");
        button2.setBackground(Color.red);
        button2.setForeground(Color.LIGHT_GRAY);

        JButton button3 = new JButton("MAP 15 x 15");
        button3.setBackground(Color.red);
        button3.setForeground(Color.LIGHT_GRAY);

        JButton button4 = new JButton("MAP 11 x 24");
        button4.setBackground(Color.red);
        button4.setForeground(Color.LIGHT_GRAY);

        panel.add(button);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        this.add(panel);

        button.addActionListener(
                e -> {
                    this.dispose();
                    new Gaming(new Map01());
                }
        );

        button1.addActionListener(
                e -> {
                    this.dispose();
                    new Gaming(new Map02());
                }
        );

        button2.addActionListener(
                e -> {
                    this.dispose();
                    new Gaming(new Map03());
                }
        );

        button3.addActionListener(
                e -> {
                    this.dispose();
                    new Gaming(new Map04());
                }
        );

        button4.addActionListener(
                e -> {
                    this.dispose();
                    new Gaming(new Map05());
                }
        );

        this.pack();

        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
