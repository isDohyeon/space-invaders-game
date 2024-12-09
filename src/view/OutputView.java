package view;

import model.GameData;
import view.panel.GamePanel;
import view.panel.ScorePanel;

import javax.swing.*;
import java.awt.*;

import static view.panel.GamePanel.GAME_PANEL_WIDTH;
import static view.panel.ScorePanel.SCORE_PANEL_WIDTH;

public class OutputView extends JPanel {

    public static final int GAME_HEIGHT = 600;

    private final GamePanel gamePanel;
    private final ScorePanel scorePanel;

    public OutputView(GamePanel gamePanel, ScorePanel scorePanel) {
        setLayout(new BorderLayout());
        this.gamePanel = gamePanel;
        this.scorePanel = scorePanel;
        add(gamePanel, BorderLayout.CENTER);
        add(scorePanel, BorderLayout.EAST);
        setPreferredSize(new Dimension(GAME_PANEL_WIDTH + SCORE_PANEL_WIDTH, GAME_HEIGHT));
    }

    public void updateGameView(GameData gameData) {
        gamePanel.updateGamePanel(gameData);
    }

    public void updateScore(GameData gameData) {
        scorePanel.updateScore(gameData);
    }
}
