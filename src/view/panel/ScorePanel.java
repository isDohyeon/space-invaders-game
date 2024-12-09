package view.panel;

import model.GameData;

import javax.swing.*;
import java.awt.*;

import static view.OutputView.GAME_HEIGHT;

public class ScorePanel extends JPanel {

    public static final int SCORE_PANEL_WIDTH = 200;
    private static final String SCORE_LABEL = "SCORE:";
    private static final String HIGH_SCORE_LABEL = "TOP SCORE:";
    private final Image image = new ImageIcon("out/images/logo.png").getImage();
    private long score;
    private final long highScore;

    public ScorePanel(long highScore) {
        this.setPreferredSize(new Dimension(SCORE_PANEL_WIDTH, GAME_HEIGHT));
        this.setBackground(Color.WHITE);
        this.score = 0;
        this.highScore = highScore;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        drawScore(g2d, SCORE_LABEL, this.score, getWidth() / 6, getHeight() / 6, 26);
        drawScore(g2d, HIGH_SCORE_LABEL, this.highScore, getWidth() / 6, getHeight() / 4, 18);
        drawLogo(g2d);
        drawStudentInfo(g2d);
    }

    private void drawScore(Graphics2D g2d, String label, long score, int x, int y, int fontSize) {
        g2d.setFont(new Font("Monospaced", Font.BOLD, fontSize));
        g2d.setColor(Color.BLACK);
        FontMetrics metrics = g2d.getFontMetrics();
        g2d.drawString(label, x, y);
        g2d.setColor(Color.BLUE);
        String valueString = Long.toString(score);
        g2d.drawString(valueString, x + metrics.stringWidth(label), y);
    }

    private void drawLogo(Graphics2D g2d) {
        g2d.drawImage(image, 0, getHeight() / 3, this);
    }

    private void drawStudentInfo(Graphics2D g2d) {
        g2d.setFont(new Font("Monospaced", Font.BOLD, 18));
        g2d.setColor(Color.BLACK);
        g2d.drawString("20200839", getWidth() / 2, (getHeight() / 7) * 6);
        FontMetrics fontMetrics = g2d.getFontMetrics();
        g2d.drawString("박도현", getWidth() / 7 * 5, (getHeight() / 7) * 6 + fontMetrics.getHeight());
    }

    public void updateScore(GameData gameData) {
        this.score = gameData.getScore();
        this.repaint();
    }
}
