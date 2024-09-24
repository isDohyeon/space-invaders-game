import javax.swing.*;
import java.awt.*;

public class SpaceInvaderApp extends JFrame {

    private static final int ROWS = 25;

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

    private void setLeftPanel() {
        leftPanel = new JPanel();
        int leftPanelCol = 3;
        JTextArea textArea = getTextArea(leftPanelCol);
        setPanelSize(leftPanel, textArea);

        for (int i = 1; i <= ROWS; i++) {
            textArea.append("..#");
            if (i != ROWS) {
                textArea.append("\n");
            }
        }

        leftPanel.add(textArea);
        add(leftPanel);
    }

    private static JTextArea getTextArea(int columns) {
        JTextArea textArea = new JTextArea(ROWS, columns);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 20));
        textArea.setLineWrap(false);
        textArea.setEditable(false);
        return textArea;
    }

    private void setCenterPanel() {
        centerPanel = new JPanel();
        JTextArea textArea = getTextArea(73);
        setPanelSize(centerPanel, textArea);


        textArea.append("중간");

        centerPanel.add(textArea);
        add(centerPanel);
    }

    private void setRightPanel() {
        rightPanel = new JPanel();
        JTextArea textArea = getTextArea(24);
        setPanelSize(rightPanel, textArea);

        /**
         * ┌ ── ┐
         * ⎮    ⎮
         * └─ ─ ┘
         */
        for (int i = 1; i <= ROWS; i++) {
            if (i == 2) { //  └
                textArea.append("#...┌───────────────┐..." + "\n");
                continue;
            } else if (i == 3) {
                textArea.append("#...⎮ SCORE: " + getScore() + "      ⎮..." + "\n");
                continue;
            } else if (i == 4) {
                textArea.append("#...└───────────────┘..." + "\n");
                continue;
            }

            textArea.append("#.......................");
            if (i != ROWS) {
                textArea.append("\n");
            }
        }

        rightPanel.add(textArea);
        add(rightPanel);
    }

    private static void setPanelSize(JPanel panel, JTextArea textArea) {
        Dimension textAreaSize = textArea.getPreferredSize();
        panel.setPreferredSize(textAreaSize);
    }

    private static int getScore() {
        int score = 0;
        return score;
    }

    public static void main(String[] args) {
        new SpaceInvaderApp();
    }
}