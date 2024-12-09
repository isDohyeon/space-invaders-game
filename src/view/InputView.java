package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class InputView {

    private final InputMap inputMap;
    private final ActionMap actionMap;
    private final Set<Integer> pressedKeys;
    private boolean isSpaceReleased;

    public InputView() {
        JPanel jPanel = new JPanel();
        this.inputMap = jPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        this.actionMap = jPanel.getActionMap();
        this.pressedKeys = new HashSet<>();
        this.isSpaceReleased = true;
        addKeyBindings();
    }

    private void addKeyBindings() {
        addKeyBinding(KeyEvent.VK_UP);
        addKeyBinding(KeyEvent.VK_DOWN);
        addKeyBinding(KeyEvent.VK_LEFT);
        addKeyBinding(KeyEvent.VK_RIGHT);
        addSpaceKeyBinding();
    }

    private void addSpaceKeyBinding() {
        bindKey(KeyEvent.VK_SPACE, false, "PRESSED_SPACE", e -> {
            pressedKeys.add(KeyEvent.VK_SPACE);
        });
        bindKey(KeyEvent.VK_SPACE, true, "RELEASED_SPACE", e -> {
            pressedKeys.remove(KeyEvent.VK_SPACE);
            isSpaceReleased = true;
        });
    }

    private void addKeyBinding(int keyCode) {
        String pressedKey = "PRESSED_" + keyCode;
        String releasedKey = "RELEASED_" + keyCode;
        bindKey(keyCode, false, pressedKey, e -> pressedKeys.add(keyCode));
        bindKey(keyCode, true, releasedKey, e -> pressedKeys.remove(keyCode));
    }

    private void bindKey(int keyCode, boolean onKeyRelease, String keyType, ActionListener actionListener) {
        inputMap.put(KeyStroke.getKeyStroke(keyCode, 0, onKeyRelease), keyType);
        actionMap.put(keyType, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionListener.actionPerformed(e);
            }
        });
    }

    public boolean isSpacePressed() {
        if (pressedKeys.contains(KeyEvent.VK_SPACE) && isSpaceReleased) {
            isSpaceReleased = false;
            return true;
        }
        return false;
    }

    public String getLastCommand() {
        boolean moveUp = pressedKeys.contains(KeyEvent.VK_UP);
        boolean moveDown = pressedKeys.contains(KeyEvent.VK_DOWN);
        boolean moveLeft = pressedKeys.contains(KeyEvent.VK_LEFT);
        boolean moveRight = pressedKeys.contains(KeyEvent.VK_RIGHT);
        return getDirection(moveUp, moveDown, moveLeft, moveRight);
    }

    private String getDirection(boolean moveUp, boolean moveDown, boolean moveLeft, boolean moveRight) {
        if (moveUp && moveLeft) {
            return "UP_LEFT";
        } else if (moveUp && moveRight) {
            return "UP_RIGHT";
        } else if (moveDown && moveLeft) {
            return "DOWN_LEFT";
        } else if (moveDown && moveRight) {
            return "DOWN_RIGHT";
        } else if (moveUp) {
            return "UP";
        } else if (moveDown) {
            return "DOWN";
        } else if (moveLeft) {
            return "LEFT";
        } else if (moveRight) {
            return "RIGHT";
        }
        return "";
    }

    public InputMap getInputMap() {
        return inputMap;
    }

    public ActionMap getActionMap() {
        return actionMap;
    }
}
