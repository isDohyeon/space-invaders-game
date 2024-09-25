import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class SpaceInvaderApp extends JFrame {

    public static final int ROWS = 25;
    public static final int CENTER_WIDTH = 73;
    private static int score;
    private PlayerObject player;

    private JTextArea centerTextArea = getTextArea(CENTER_WIDTH);

    private JPanel leftPanel;
    private JPanel centerPanel;
    private JPanel rightPanel;

    SpaceInvaderApp() {
        setLeftPanel();
        setCenterPanel();
        setRightPanel();
        setFrame();
        setVisible(true);
        mapKeyAction();
    }

    private void setFrame() {
        // 프레임(창) 제목
        setTitle("Space Invader");
        // 종료 버튼(x)을 누르면 프로그램도 완전히 종료
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 레이아웃 타입 지정
        setLayout(new BorderLayout());
        add(leftPanel, BorderLayout.WEST);
        add(centerPanel, BorderLayout.CENTER);
        add(rightPanel, BorderLayout.EAST);

        // 패널들이 추가된 후 크기를 자동으로 설정
        pack();
        // 프레임의 위치를 화면의 정 가운데로 오게함
        setLocationRelativeTo(null);
    }

    private JTextArea getTextArea(int columns) {
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
    }

    private void addKeyBinding(int keyCode, ActionListener listener) {
        centerTextArea.getInputMap().put(KeyStroke.getKeyStroke(keyCode, 0), String.valueOf(keyCode));
        centerTextArea.getActionMap().put(String.valueOf(keyCode), new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.actionPerformed(e);
            }
        });
    }

    private void mapKeyAction() {
//        centerTextArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
//        centerTextArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
//        centerTextArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
//        centerTextArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");

        // 이부분 계산 다시 해봐야함 dx dy로 가능
        addKeyBinding(KeyEvent.VK_RIGHT, e -> playerMove(1, 0));
        addKeyBinding(KeyEvent.VK_LEFT, e -> playerMove(-1, 0));
        addKeyBinding(KeyEvent.VK_UP, e -> playerMove(0, -1));
        addKeyBinding(KeyEvent.VK_DOWN, e -> playerMove(0, 1));
    }

    private void playerMove(int dx, int dy) {
        player.setPosX(player.getPosX() + dx);
        player.setPosY(player.getPosY() + dy);
        replacePos(player);
    }

    private void printInitObject() {
        // 초기 빈칸으로 채우기
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < centerTextArea.getColumns(); j++) {
                centerTextArea.append(" ");
            }
            breakLineByCondition(centerTextArea, i);
        }

        // 적 생성
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

        // 초기 플레이어 생성
        replacePos(new PlayerObject(36, 22));
    }

    private void createEnemy(int posX, int posY) {
        EnemyObject enemy = new EnemyObject(posX, posY);
        replacePos(enemy);
    }

    private void replacePos(GameObject o) {
        centerTextArea.replaceRange(o.getImage(), o.getIndex() - 2, o.getIndex() + 3);
    }

    private void setRightPanel() {
        rightPanel = new JPanel();
        JTextArea textArea = getTextArea(24);
        setPanelSize(rightPanel, textArea);

        /**
         * ┌ ─ ┐
         * ⎮   ⎮
         * └ ─ ┘
         */
        printInitRightPanel(textArea);

        rightPanel.add(textArea);
        add(rightPanel);
    }

    private void printInitRightPanel(JTextArea textArea) {
        for (int i = 0; i < ROWS; i++) {
            if (i == 1) { //  └
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
    }

    public static void main(String[] args) {
        new SpaceInvaderApp();
    }
}