package GUI;

import ScoreBord.FileHandling;
import ScoreBord.ScorePlayer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameOver extends JFrame {
    public GameOver(int score) {
        this.setTitle("Game Over");


        JLabel label = new JLabel("GAME OVER");
        label.setFont(new Font("Arial", Font.BOLD, 45));
        label.setForeground(Color.RED);

        JPanel infopanel = new JPanel(new FlowLayout());
        infopanel.add(label);
        infopanel.setBackground(new Color(5, 5, 5));

        this.setLayout(new GridLayout(2, 1));


        JTextField textField = new JTextField(20);
        textField.setForeground(Color.LIGHT_GRAY);
        textField.setBackground(Color.darkGray);

        JPanel playerpanel = new JPanel(new FlowLayout());

        JLabel label1 = new JLabel("Podaj swoją nazwe: ");
        label1.setForeground(Color.LIGHT_GRAY);
        label1.setBackground(new Color(5, 5, 5));

        playerpanel.add(label1);
        playerpanel.add(textField);
        playerpanel.setBackground(new Color(5, 5, 5));

        textField.addActionListener(
                e -> {
                    AddScore(textField.getText(), score);
                    AddElements();
                    this.remove(playerpanel);
                    this.repaint();
                    this.revalidate();
                }
        );

        this.add(infopanel);
        this.add(playerpanel);


        this.pack();


        this.setBackground(Color.DARK_GRAY);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private static void AddScore(String str, int score) {
        ArrayList<ScorePlayer> list = FileHandling.ReadFromFIle();

        list.add(new ScorePlayer(str, score));

        list.sort((o1, o2) -> o2.getScore() - o1.getScore());

        FileHandling.WriteToFile(list);
    }

    private void AddElements() {
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

        this.add(buttonpanel);

        this.pack();
    }
}