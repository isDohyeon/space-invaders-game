package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import objects.BulletObject;
import objects.EnemyBulletObject;
import objects.EnemyObject;
import objects.PlayerObject;
import service.EnemyService;
import service.PlayerService;
import view.GameView;

public class GameController {

    private final GameView view = new GameView();
    private final EnemyService enemyService;
    private final PlayerService playerService;
    private int score = 0;
    private Timer gameTimer;

    public GameController() {
        this.enemyService = new EnemyService();
        this.playerService = new PlayerService();
        setupKeyBindings();
    }

    public void run() {
        gameTimer = new Timer();
        gameTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateGameObjects();
                checkGameState();
                checkCollisions();
            }
        }, 0 ,10);
    }

    private void updateGameObjects() {
        view.initCenterTextArea();

        view.printNewObject(playerService.getPlayer().getImage(), playerService.getPlayer().getIndex());
        for (BulletObject playerBullet : playerService.getPlayerBullets()) {
            view.printNewObject(playerBullet.getImage(), playerBullet.getIndex());
        }

        for (EnemyObject enemy : enemyService.getEnemies()) {
            view.printNewObject(enemy.getImage(), enemy.getIndex());
        }
        for (EnemyBulletObject enemyBullet : enemyService.getEnemyBullets()) {
            view.printNewObject(enemyBullet.getImage(), enemyBullet.getIndex());
        }
    }

    private void checkGameState() {
        if (enemyService.getEnemies().isEmpty()) {
            endGame(true);
        }
        if (!playerService.getPlayer().getAlive()) {
            endGame(false);
        }
    }

    private void checkCollisions() {
        handlePlayerBulletCollisions();
        checkPlayerHitByEnemyBullets();
    }

    private void handlePlayerBulletCollisions() {
        for (BulletObject playerBullet : playerService.getPlayerBullets()) {
            for (EnemyObject enemy : enemyService.getEnemies()) {
                if (isCollision(playerBullet.getPosX(), playerBullet.getPosY(), enemy.getPosX(), enemy.getPosY(), playerBullet.getImage().length())) {
                    score += 10;
                    view.updateScore(score);
                    playerService.removePlayerBullet(playerBullet);
                    enemyService.removeEnemy(enemy);
                    break;
                }
            }
        }
    }

    private void checkPlayerHitByEnemyBullets() {
        for (EnemyBulletObject enemyBullet : enemyService.getEnemyBullets()) {
            PlayerObject player = playerService.getPlayer();
            if (isCollision(enemyBullet.getPosX(), enemyBullet.getPosY(), player.getPosX(), player.getPosY(), enemyBullet.getImage().length())) {
                playerService.getPlayer().setAlive(false);
            }
        }
    }

    private boolean isCollision(int x1, int y1, int x2, int y2, int range) {
        return (x1 == x2 && y1 == y2) || (x1 >= (x2 - range) && x1 <= (x2 + range) && y1 == y2);
    }

    private void endGame(boolean isWin) {
        gameTimer.cancel();
        view.showGameOverMessage(isWin);
        addKeyBinding(KeyEvent.VK_Y, e -> restart());
        addKeyBinding(KeyEvent.VK_N, e -> System.exit(0));
    }

    public void restart() {
        view.dispose();
        new GameController().run();
    }

    private void setupKeyBindings() {
        addKeyBinding(KeyEvent.VK_RIGHT, e -> playerService.movePlayer(2, 0));
        addKeyBinding(KeyEvent.VK_LEFT, e -> playerService.movePlayer(-2, 0));
        addKeyBinding(KeyEvent.VK_UP, e -> playerService.movePlayer(0, -1));
        addKeyBinding(KeyEvent.VK_DOWN, e -> playerService.movePlayer(0, 1));
        addKeyBinding(KeyEvent.VK_SPACE, e -> playerService.fireBullet());
    }

    private void addKeyBinding(int keyCode, ActionListener listener) {
        view.getRootPane().getInputMap().put(KeyStroke.getKeyStroke(keyCode, 0), String.valueOf(keyCode));
        view.getRootPane().getActionMap().put(String.valueOf(keyCode), new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.actionPerformed(e);
            }
        });
    }
}
