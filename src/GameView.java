import javax.swing.*;
import java.awt.*;

public class GameView extends JFrame {

    GameController controller;
    public PlayerObject player;

    public static final int ROWS = 25;
    public static final int CENTER_WIDTH = 73;
    public static final int LEFT_WIDTH = 3;
    public static final int RIGHT_WIDTH = 24;

    public JTextArea leftTextArea = getTextArea(LEFT_WIDTH);
    public JTextArea centerTextArea = getTextArea(CENTER_WIDTH);
    public JTextArea rightTextArea = getTextArea(RIGHT_WIDTH);

    public JPanel leftPanel = new JPanel();
    public JPanel centerPanel = new JPanel();
    public JPanel rightPanel = new JPanel();

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
        setTitle("Space Invader");
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

    private void setPanelSize(JPanel panel, JTextArea textArea) {
        Dimension textAreaSize = textArea.getPreferredSize();
        panel.setPreferredSize(textAreaSize);
        panel.setMaximumSize(textAreaSize);
        panel.setMinimumSize(textAreaSize);
    }

    private JTextArea getTextArea(int columns) {
        JTextArea textArea = new JTextArea(ROWS, columns);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
        textArea.setLineWrap(false);
        textArea.setEditable(false);
        return textArea;
    }

    private static void breakLineByCondition(JTextArea textArea, int i) {
        if (i != ROWS - 1) {
            textArea.append("\n");
        }
    }

    private void printLeftTextArea() {
        for (int i = 0; i < ROWS; i++) {
            leftTextArea.append("..#");
            breakLineByCondition(leftTextArea, i);
        }
    }

    public void replacePos(GameObject o, int dx, int dy) {
        int curX = o.getPosX();
        int curY = o.getPosY();
        if ((curX + dx <= 1 || curX + dx >= CENTER_WIDTH - 2 || curY + dy < 0 || curY + dy >= ROWS)) {
            return;
        }

        String replaceStr = o instanceof BulletObject ? " " : "     ";
        int size = o instanceof BulletObject ? 0 : 2;

        replaceByObject(o, replaceStr, size);
        printNewPosition(o, dx, dy, curX, curY, size);
    }

    private void replaceByObject(GameObject o, String replaceStr, int size) {
        centerTextArea.replaceRange(replaceStr, o.getIndex() - size, o.getIndex() + size + 1);
    }

    private void printNewPosition(GameObject o, int dx, int dy, int curX, int curY, int size) {
        o.setPosX(curX + dx);
        o.setPosY(curY + dy);
        replaceByObject(o, o.getImage(), size);
    }

    private void printCenterTextArea() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < centerTextArea.getColumns(); j++) {
                centerTextArea.append(" ");
            }
            breakLineByCondition(centerTextArea, i);
        }

        int x = 7;
        int y = 1;
        for (int i = 0; i < 8; i++) {
            createEnemy(x, y);

            x += 6;
            if (i % 2 == 0) {
                y += 2;
            } else {
                y -= 2;
            }
        }

        replacePos(player = new PlayerObject(36, 23), 0, 0);
    }

    public void createEnemy(int posX, int posY) {
        EnemyObject enemy = new EnemyObject(posX, posY);
        replacePos(enemy, 0, 0);
    }

    private void printRightTextArea() {
        /**
         * ┌ ─ ┐
         * ⎮   ⎮
         * └ ─ ┘
         */
        for (int i = 0; i < ROWS; i++) {
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

    public void setController(GameController controller) {
        this.controller = controller;
    }
}