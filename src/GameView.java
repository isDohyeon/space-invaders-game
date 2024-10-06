import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {

    private static final int HEIGHT = 25;
    private static final int LEFT_WIDTH = 3;
    public static final int CENTER_WIDTH = 73;
    private static final int RIGHT_WIDTH = 24;

    private static final int ENEMY_AMOUNT = 8;

    public JPanel leftPanel = new JPanel();
    public JPanel centerPanel = new JPanel();
    public JPanel rightPanel = new JPanel();

    public JTextArea leftTextArea = getTextArea(LEFT_WIDTH);
    public JTextArea centerTextArea = getTextArea(CENTER_WIDTH);
    public JTextArea rightTextArea = getTextArea(RIGHT_WIDTH);

    public GameView() {
        setPanel(leftPanel, leftTextArea);
        setPanel(centerPanel, centerTextArea);
        setPanel(rightPanel, rightTextArea);

        printLeftTextArea();
        printCenterTextArea();
        printRightTextArea();

        setFrame();
        setVisible(true);
    }

    private void setFrame() {
        setTitle("Let's Play Space Invader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);
        pack();
        setLocationRelativeTo(null);
    }

    private void setPanel(JPanel panel, JTextArea textArea) {
        setPanelSize(panel, textArea);
        panel.add(textArea);
        add(panel);
    }

    private static void setPanelSize(JPanel panel, JTextArea textArea) {
        Dimension textAreaSize = textArea.getPreferredSize();
        panel.setPreferredSize(textAreaSize);
        panel.setMaximumSize(textAreaSize);
        panel.setMinimumSize(textAreaSize);
    }

    private static JTextArea getTextArea(int width) {
        JTextArea textArea = new JTextArea(HEIGHT, width);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
        textArea.setLineWrap(false);
        textArea.setEditable(false);
        return textArea;
    }

    private static void breakLineByCondition(JTextArea textArea, int i) {
        if (i != HEIGHT - 1) {
            textArea.append("\n");
        }
    }

    private void printLeftTextArea() {
        for (int i = 0; i < HEIGHT; i++) {
            leftTextArea.append("..#");
            breakLineByCondition(leftTextArea, i);
        }
    }

    public void replacePosition(GameObject object, int dx, int dy) {
        int newX = object.getPosX() + dx;
        int newY = object.getPosY() + dy;
        if (isOutOfBound(newX, newY)) {
            return;
        }

        int indexRange = object.getImage().length() / 2;
        removeOldObject(object, indexRange);
        printNewObject(object, indexRange, newX, newY);
    }

    private static boolean isOutOfBound(int newX, int newY) {
        return newX <= 1 || newX >= CENTER_WIDTH - 2 || newY < 0 || newY >= HEIGHT;
    }

    private void removeOldObject(GameObject object, int indexRange) {
        replaceIndex(object, object.getReplaceImage(), indexRange);
    }

    private void printNewObject(GameObject object, int size, int newX, int newY) {
        object.setPosX(newX);
        object.setPosY(newY);
        replaceIndex(object, object.getImage(), size);
    }

    public void replaceIndex(GameObject object, String replaceStr, int indexRange) {
        int curIndex = object.getIndex();
        int startIndex = curIndex - indexRange;
        int endIndex = curIndex + indexRange + 1;
        centerTextArea.replaceRange(replaceStr, startIndex, endIndex);
    }

    private void printCenterTextArea() {
        initSpace();
        initEnemy();
        initPlayer();
    }

    private void initSpace() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < CENTER_WIDTH; j++) {
                centerTextArea.append(" ");
            }
            breakLineByCondition(centerTextArea, i);
        }
    }

    private void initEnemy() {
        int x = 7;
        int y = 1;
        for (int i = 0; i < ENEMY_AMOUNT; i++) {
            printEnemy(x, y);

            x += 6;
            y = i % 2 == 0 ? y + 2 : y - 2;
        }
    }

    private void printEnemy(int posX, int posY) {
        GameController.enemy = new EnemyObject(posX, posY);
        replacePosition(GameController.enemy, 0, 0);
    }

    private void initPlayer() {
        GameController.player = new PlayerObject(36, 23);
        replacePosition(GameController.player, 0, 0);
    }

    private void printRightTextArea() {
        /**
         * ┌ ─ ┐
         * ⎮   ⎮
         * └ ─ ┘
         */
        for (int i = 0; i < HEIGHT; i++) {
            if (i == 1) {
                rightTextArea.append("#...┌───────────────┐...\n");
                continue;
            } else if (i == 2) {
                rightTextArea.append("#...⎮ SCORE: 0      ⎮...\n");
                continue;
            } else if (i == 3) {
                rightTextArea.append("#...└───────────────┘...\n");
                continue;
            } else if (i == 23) {
                rightTextArea.append("#......... by Dohyeon...\n");
                continue;
            }

            rightTextArea.append("#.......................");
            breakLineByCondition(rightTextArea, i);
        }
    }

    public JFrame getFrame() {
        return this;
    }
}