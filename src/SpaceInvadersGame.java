import controller.GameController;
import model.GameData;
import model.ObjectHandler;
import model.PlayerHandler;
import view.panel.GamePanel;
import view.InputView;
import view.OutputView;
import view.panel.ScorePanel;
import view.panel.StartPanel;

import javax.swing.*;
import java.awt.*;

public class SpaceInvadersGame {

    private GameController controller;
    private GameData gameData;
    private InputView inputView;
    private OutputView outputView;
    private JFrame frame;
    Thread controllerThread;

    public SpaceInvadersGame() {
        initMVC();
        initJFrame();
        startGame();
    }

    private void startGame() {
        controllerThread = new Thread(controller);
        controllerThread.start();

        while (!controller.isGameOver()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        recordHighScore(gameData.getScore());
        announceResult();
    }

    private void announceResult() {
        String resultMessage = controller.isPlayerLose() ? "You Lose!" : "You Win!";
        int result = JOptionPane.showConfirmDialog(frame, "Play Again?", resultMessage, JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            restartGame();
        } else {
            finishGame();
        }
    }

    private  void finishGame() {
        System.exit(0);
    }

    private void restartGame() {
        frame.dispose();
        new SpaceInvadersGame();
    }

    private void initMVC() {
        gameData = new GameData();
        inputView = new InputView();
        outputView = new OutputView(new GamePanel(gameData), new ScorePanel(ScoreLoader.loadScore()));
        controller = new GameController(new PlayerHandler(gameData), new ObjectHandler(gameData), inputView, outputView);
    }

    private void initJFrame() {
        frame = new JFrame();
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new CardLayout());
        StartPanel startPanel = new StartPanel();
        contentPane.add(startPanel, "start");
        contentPane.add(outputView, "play");
        frame.add(contentPane);
        frame.pack();
        frame.setTitle("Let's play Space Invaders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputView.getInputMap());
        frame.getRootPane().setActionMap(inputView.getActionMap());
        frame.setVisible(true);
        startPanel.setStartButtonListener(e -> {
            CardLayout cardLayout = (CardLayout) contentPane.getLayout();
            cardLayout.show(contentPane, "play");
        });
    }

    private void recordHighScore(long score) {
        ScoreLoader.saveHighScore(score);
    }

    public static void main(String[] args) {
        new SpaceInvadersGame();
    }
}
