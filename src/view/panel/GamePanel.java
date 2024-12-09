package view.panel;

import model.GameData;
import model.object.GameObject;

import javax.swing.*;
import java.awt.*;

import static view.OutputView.GAME_HEIGHT;

public class GamePanel extends JPanel {

    public static final int GAME_PANEL_WIDTH = 1000;

    private GameData gameData;

    public GamePanel(GameData gameData) {
        this.gameData = gameData;
        this.setPreferredSize(new Dimension(GAME_PANEL_WIDTH, GAME_HEIGHT));
        this.setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        for (GameObject object : gameData.getGameObjects()) {
            if (object == null) {
                continue;
            }
            g2d.drawImage(object.getImage(), object.getPosX(), object.getPosY(), this);
        }
    }

    public void updateGamePanel(GameData gameData) {
        this.gameData = gameData;
        this.repaint();
    }
}
