package test;

import javax.swing.*;
import java.awt.*;

public class JPanelAdvancedExample extends JFrame {
    public JPanelAdvancedExample() {
        // JFrame 기본 설정
        setTitle("JPanel 레이아웃 예제");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 메인 패널 생성 및 BorderLayout 설정
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        // 북쪽 패널 (FlowLayout)
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.add(new JButton("버튼 1"));
        northPanel.add(new JButton("버튼 2"));

        // 중앙 패널 (GridLayout)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 2));
        centerPanel.add(new JButton("버튼 3"));
        centerPanel.add(new JButton("버튼 4"));
        centerPanel.add(new JButton("버튼 5"));
        centerPanel.add(new JButton("버튼 6"));

        // 패널 배경색 설정
        northPanel.setBackground(Color.LIGHT_GRAY);
        centerPanel.setBackground(Color.WHITE);

        // 패널들을 메인 패널에 추가
        mainPanel.add(northPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // JFrame에 메인 패널 추가
        add(mainPanel);

        // 화면에 보이기
        setVisible(true);
    }

    public static void main(String[] args) {
        new JPanelAdvancedExample();
    }
}
