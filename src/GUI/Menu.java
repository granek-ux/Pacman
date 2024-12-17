package GUI;

import javax.swing.*;
import java.awt.*;

public class Menu extends JFrame {

    public Menu() {
        this.setTitle("Menu");

        JButton playButton = new JButton("Play");
        playButton.setFont(new Font("Arial", Font.ITALIC, 30));
        playButton.setPreferredSize(new Dimension(200, 100));
        playButton.setBackground(Color.red);
        playButton.setForeground(Color.LIGHT_GRAY);


        JPanel playPanel = new JPanel();
        playPanel.add(playButton, BorderLayout.CENTER);
        playPanel.setBackground(new Color(5, 5, 5));


        JButton highButton = new JButton("ScoreBoard");
        highButton.setFont(new Font("Arial", Font.ITALIC, 30));
        highButton.setPreferredSize(new Dimension(200, 100));
        highButton.setBackground(Color.red);
        highButton.setForeground(Color.LIGHT_GRAY);


        JPanel HighPanel = new JPanel();
        HighPanel.add(highButton, BorderLayout.CENTER);
        HighPanel.setBackground(new Color(5, 5, 5));


        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.ITALIC, 30));
        exitButton.setPreferredSize(new Dimension(200, 100));
        exitButton.setBackground(Color.red);
        exitButton.setForeground(Color.LIGHT_GRAY);


        JPanel exitPanel = new JPanel();
        exitPanel.add(exitButton, BorderLayout.CENTER);
        exitPanel.setBackground(new Color(5, 5, 5));

        JPanel buttonpanel = new JPanel(new GridLayout(3, 1));


        JLabel nameGame = new JLabel("Pacman by ME");
        nameGame.setFont(new Font("Arial", Font.BOLD, 55));
        nameGame.setForeground(Color.LIGHT_GRAY);

        JPanel namePanel = new JPanel(new FlowLayout());
        namePanel.setBackground(new Color(5, 5, 5));

        namePanel.add(nameGame);


        buttonpanel.add(playPanel);
        buttonpanel.add(HighPanel);
        buttonpanel.add(exitPanel);

        playButton.addActionListener(
                e -> {
                    this.dispose();
                    new MapChoice();
                }
        );
        highButton.addActionListener(
                e ->
                {
                    this.dispose();
                    new HighScore();
                }
        );

        exitButton.addActionListener(
                e -> this.dispose()
        );

        this.setBackground(new Color(5, 5, 5));

        this.add(namePanel, BorderLayout.NORTH);
        this.add(buttonpanel, BorderLayout.CENTER);

        this.setSize(600, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}
