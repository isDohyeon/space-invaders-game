import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GameController {

    private final GameView view;

    public GameController(GameView view) {
        this.view = view;

        addKeyBinding(KeyEvent.VK_RIGHT, e -> view.replacePos(view.player, 1, 0));
        addKeyBinding(KeyEvent.VK_LEFT, e -> view.replacePos(view.player, -1, 0));
        addKeyBinding(KeyEvent.VK_UP, e -> view.replacePos(view.player, 0, -1));
        addKeyBinding(KeyEvent.VK_DOWN, e -> view.replacePos(view.player, 0, 1));
        addKeyBinding(KeyEvent.VK_SPACE, e -> fireBullet());
    }

    public void addKeyBinding(int keyCode, ActionListener listener) {
        view.getFrame().getRootPane().getInputMap().put(KeyStroke.getKeyStroke(keyCode, 0), String.valueOf(keyCode));
        view.getFrame().getRootPane().getActionMap().put(String.valueOf(keyCode), new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.actionPerformed(e);
            }
        });
    }

    private void fireBullet() {
        int posX = view.player.getPosX();
        int posY = view.player.getPosY() - 1;
        Thread bulletThread = new Thread(new BulletObject(posX, posY, view));
        bulletThread.start();
    }
}