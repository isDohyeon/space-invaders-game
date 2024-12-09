package view.panel;

import javax.swing.*;
import java.awt.*;

public class StartPanel extends JPanel {

    private final JButton startButton;

    public StartPanel() {
        JLabel imageLabel = new JLabel(new ImageIcon("out/images/start_image.png"));
        startButton = new JButton("Game Start");
        this.setLayout(new BorderLayout());
        this.add(imageLabel, BorderLayout.CENTER);
        this.add(startButton, BorderLayout.SOUTH);
    }

    public void setStartButtonListener(java.awt.event.ActionListener listener) {
        startButton.addActionListener(listener);
    }
}
