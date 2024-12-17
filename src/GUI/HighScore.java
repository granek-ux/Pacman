package GUI;

import ScoreBord.ScorePlayer;

import javax.swing.*;
import java.awt.*;

import static ScoreBord.FileHandling.ReadFromFIle;

public class HighScore extends JFrame {

    public HighScore() {
        this.setTitle("ScoreBaord");

        int id = 1;

        JList<String> ranking;

        DefaultListModel<String> model = new DefaultListModel<>();

        for (ScorePlayer sp : ReadFromFIle())
            model.addElement(id++ + ". " + sp.toString());


        ranking = new JList<>(model);

        JLabel title = new JLabel("SCOREBOARD: ");
        title.setForeground(Color.LIGHT_GRAY);
        title.setBackground(new Color(5, 5, 5));
        title.setFont(new Font("Arial", Font.ITALIC, 45));


        ranking.setForeground(Color.LIGHT_GRAY);
        ranking.setBackground(new Color(5, 5, 5));

        JScrollPane scrollPane = new JScrollPane(ranking);

        JButton button = new JButton("EXIT");
        button.setBackground(Color.red);
        button.setForeground(Color.LIGHT_GRAY);


        JPanel panel = new JPanel();
        panel.setBackground(new Color(5, 5, 5));


        panel.add(button);

        button.addActionListener(
                e -> {
                    this.dispose();
                    new Menu();
                }
        );

        this.add(title, BorderLayout.NORTH);

        this.add(scrollPane, BorderLayout.CENTER);

        this.add(panel, BorderLayout.SOUTH);

        this.setVisible(true);
        this.setSize(600, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
}
