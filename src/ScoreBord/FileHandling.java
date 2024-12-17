package ScoreBord;

import java.io.*;
import java.util.ArrayList;

public class FileHandling {

    public static void WriteToFile(ArrayList<ScorePlayer> players) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("obj_score.bin"));
            outputStream.writeObject(players);
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<ScorePlayer> ReadFromFIle() {
        ArrayList<ScorePlayer> players;
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("obj_score.bin"));
            players = (ArrayList<ScorePlayer>) inputStream.readObject();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return players;
    }
}