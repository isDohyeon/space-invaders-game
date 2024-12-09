import java.io.*;

public class ScoreLoader {

    private static final String SCORE_FILE_PATH = "score.txt";

    public static void saveHighScore(long score) {
        if (!isHighScore(score)) {
            return;
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SCORE_FILE_PATH))) {
            writer.write(String.valueOf(score));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long loadScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(SCORE_FILE_PATH))) {
            String score = reader.readLine();
            return Long.parseLong(score);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static boolean isHighScore(long newScore) {
        return loadScore() < newScore;
    }
}
