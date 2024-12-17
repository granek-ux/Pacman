package ScoreBord;

import java.io.Serializable;

public class ScorePlayer implements Serializable {
    private String name;
    private int score;

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public ScorePlayer(String name, int score) {

        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return name + " score: " + score;
    }
}
