package view;

import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {

    public static final int HEIGHT = 25;
    public static final int CENTER_WIDTH = 73;
    private static final int LEFT_WIDTH = 3;
    private static final int RIGHT_WIDTH = 24;

    private final JPanel leftPanel = new JPanel();
    private final JPanel centerPanel = new JPanel();
    private final JPanel rightPanel = new JPanel();

    private final JTextArea leftTextArea = createTextArea(LEFT_WIDTH);
    private static final JTextArea centerTextArea = createTextArea(CENTER_WIDTH);
    private final JTextArea rightTextArea = createTextArea(RIGHT_WIDTH);

    public GameView() {
        setupPanel(leftPanel, leftTextArea);
        setupPanel(centerPanel, centerTextArea);
        setupPanel(rightPanel, rightTextArea);
        initializeTextAreas();
        setupFrame();
        setVisible(true);
    }

    private void setupFrame() {
        setTitle("Let's Play Space Invader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        pack();
        setLocationRelativeTo(null);
    }

    private void setupPanel(JPanel panel, JTextArea textArea) {
        panel.setPreferredSize(textArea.getPreferredSize());
        panel.add(textArea);
        add(panel);
    }

    public void printNewObject(String replaceStr, int index) {
        centerTextArea.replaceRange(replaceStr, index, index + replaceStr.length());
    }

    private static JTextArea createTextArea(int width) {
        JTextArea textArea = new JTextArea(HEIGHT, width);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
        textArea.setLineWrap(false);
        textArea.setEditable(false);
        return textArea;
    }

    public void initializeTextAreas() {
        populateTextArea(leftTextArea, "..#");
        initCenterTextArea();
        populateRightTextArea();
    }

    public void initCenterTextArea() {
        centerTextArea.setText("");
        populateTextArea(centerTextArea, " ".repeat(CENTER_WIDTH));
    }

    private void populateTextArea(JTextArea textArea, String content) {
        for (int i = 0; i < GameView.HEIGHT; i++) {
            textArea.append(content + "\n");
        }
    }

    private void populateRightTextArea() {
        for (int i = 0; i < HEIGHT; i++) {
            if (i == 1) {
                rightTextArea.append("#...┌───────────────┐...\n");
            } else if (i == 2) {
                rightTextArea.append("#...⎮ SCORE: 0      ⎮...\n");
            } else if (i == 3) {
                rightTextArea.append("#...└───────────────┘...\n");
            } else if (i == 23) {
                rightTextArea.append("#......... by dohyeon...\n");
            } else {
                rightTextArea.append("#.......................\n");
            }
        }
    }

    public void showGameOverMessage(boolean result) {
        String message = getMessage(result);
        String[] messageLines = message.split("\n");

        int verticalPadding = (HEIGHT - messageLines.length) / 2;
        int horizontalPadding = (CENTER_WIDTH - messageLines[0].length()) / 2;

        for (int i = 0; i < messageLines.length; i++) {
            int lineStartIndex = (verticalPadding + i) * (CENTER_WIDTH + 1) + horizontalPadding;
            centerTextArea.replaceRange(messageLines[i], lineStartIndex, lineStartIndex + messageLines[i].length());
        }
    }

    private static String getMessage(boolean isWin) {
        String result = isWin ? "║        You Win!!        ║" : "║       You Lose!!        ║";
        return String.join("\n",
                "╔═════════════════════════╗",
                                     result,
                          "║                         ║",
                          "║    Play Again? (Y/N)    ║",
                          "╚═════════════════════════╝"
        );
    }

    public void updateScore(int score) {
        String scoreText = String.format("%2d", score);
        int startIndex = rightTextArea.getText().indexOf("SCORE: ") + 7;
        rightTextArea.replaceRange(scoreText, startIndex, startIndex + 2);
    }
}
