import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class SpaceInvaderApp extends JFrame{

    public static final int ROWS = 25;
    public static final int CENTER_WIDTH = 73;
    private static int score;
    static PlayerObject player;

    public static JTextArea centerTextArea = getTextArea(CENTER_WIDTH);

    private JPanel leftPanel;
    private JPanel centerPanel;
    private JPanel rightPanel;

    SpaceInvaderApp() {
        setLeftPanel();
        setCenterPanel();
        setRightPanel();
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

    private static JTextArea getTextArea(int columns) {
        JTextArea textArea = new JTextArea(ROWS, columns);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
        textArea.setLineWrap(false);
        textArea.setEditable(false);
        return textArea;
    }

    private void setLeftPanel() {
        leftPanel = new JPanel();
        JTextArea textArea = getTextArea(3);
        setPanelSize(leftPanel, textArea);
        printInitLeftPanel(textArea);
        leftPanel.add(textArea);
        add(leftPanel);
    }

    private void printInitLeftPanel(JTextArea textArea) {
        for (int i = 0; i < ROWS; i++) {
            textArea.append("..#");
            breakLineByCondition(textArea, i);
        }
    }

    private void breakLineByCondition(JTextArea textArea, int i) {
        if (i != ROWS - 1) {
            textArea.append("\n");
        }
    }

    private void setCenterPanel() {
        centerPanel = new JPanel();
        setPanelSize(centerPanel, centerTextArea);
        printInitObject();
        centerPanel.add(centerTextArea);
        add(centerPanel);
        mapKeyAction();
    }

    private void addKeyBinding(int keyCode, ActionListener listener) {
        getRootPane().getInputMap().put(KeyStroke.getKeyStroke(keyCode, 0), String.valueOf(keyCode));
        getRootPane().getActionMap().put(String.valueOf(keyCode), new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.actionPerformed(e);
            }
        });
    }

    private void mapKeyAction() {
        addKeyBinding(KeyEvent.VK_RIGHT, e -> replacePos(player, 1, 0));
        addKeyBinding(KeyEvent.VK_LEFT, e -> replacePos(player, -1, 0));
        addKeyBinding(KeyEvent.VK_UP, e -> replacePos(player, 0, -1));
        addKeyBinding(KeyEvent.VK_DOWN, e -> replacePos(player, 0, 1));
        addKeyBinding(KeyEvent.VK_SPACE, e -> fireBullet());
    }

    private void fireBullet() {
        int posX = player.getPosX();
        int posY = player.getPosY() - 1;
        Thread bulletThread = new Thread(new BulletObject(posX, posY));
        bulletThread.start();
    }

    public static void replacePos(GameObject o, int dx, int dy) {
        int curX = o.getPosX();
        int curY = o.getPosY();
        if ((curX + dx <= 1 || curX + dx >= CENTER_WIDTH - 2 || curY + dy < 0 || curY + dy >= ROWS)) {
            return;
        }

        String replaceStr;
        int size;
        if (o instanceof BulletObject) {
            replaceStr = " ";
            size = 0;
        } else {
            replaceStr = "     ";
            size = 2;
        }

        centerTextArea.replaceRange(replaceStr, o.getIndex() - size, o.getIndex() + size + 1);
        o.setPosX(curX + dx);
        o.setPosY(curY + dy);
        centerTextArea.replaceRange(o.getImage(), o.getIndex() - size, o.getIndex() + size + 1);
    }

    private void printInitObject() {
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

    private void createEnemy(int posX, int posY) {
        EnemyObject enemy = new EnemyObject(posX, posY);
        replacePos(enemy, 0, 0);
    }


    private void setRightPanel() {
        rightPanel = new JPanel();
        JTextArea textArea = getTextArea(24);
        setPanelSize(rightPanel, textArea);
        printInitRightPanel(textArea);
        rightPanel.add(textArea);
        add(rightPanel);
    }

    private void printInitRightPanel(JTextArea textArea) {
        /**
         * ┌ ─ ┐
         * ⎮   ⎮
         * └ ─ ┘
         */
        for (int i = 0; i < ROWS; i++) {
            if (i == 1) {
                textArea.append("#...┌───────────────┐..." + "\n");
                continue;
            } else if (i == 2) {
                textArea.append("#...⎮ SCORE: " + score + "      ⎮..." + "\n");
                continue;
            } else if (i == 3) {
                textArea.append("#...└───────────────┘..." + "\n");
                continue;
            } else if (i == 23) {
                textArea.append("#......... by Dohyeon..." + "\n");
                continue;
            }
            textArea.append("#.......................");
            breakLineByCondition(textArea, i);
        }
    }

    private static void setPanelSize(JPanel panel, JTextArea textArea) {
        Dimension textAreaSize = textArea.getPreferredSize();
        panel.setPreferredSize(textAreaSize);
        panel.setMaximumSize(textAreaSize);
        panel.setMinimumSize(textAreaSize);
    }

    public static void main(String[] args) {
        new SpaceInvaderApp();
    }
}