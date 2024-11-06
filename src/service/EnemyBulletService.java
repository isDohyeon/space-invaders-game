package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import objects.EnemyBulletObject;
import objects.EnemyObject;
import view.GameView;

public class EnemyBulletService {

    private final List<EnemyObject> enemies;
    private final List<EnemyBulletObject> enemyBullets = new ArrayList<>();
    private final Timer bulletTimer = new Timer();

    public EnemyBulletService(List<EnemyObject> enemies) {
        this.enemies = enemies;
        scheduleBulletCreation();
        scheduleBulletMovement();
    }

    private void scheduleBulletCreation() {
        bulletTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                createBullets();
            }
        }, 0, 1000);
    }

    private void createBullets() {
        for (EnemyObject enemy : enemies) {
            if (Math.random() >= 0.5) {
                enemyBullets.add(new EnemyBulletObject(enemy.getPosX(), enemy.getPosY() + 1));
            }
        }
    }

    private void scheduleBulletMovement() {
        bulletTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateBullets();
            }
        }, 0, 100);
    }

    private void updateBullets() {
        List<EnemyBulletObject> bulletsToRemove = new ArrayList<>();

        for (EnemyBulletObject bullet : new ArrayList<>(enemyBullets)) {
            int newY = bullet.getPosY() + 1;
            if (newY >= GameView.HEIGHT) {
                bulletsToRemove.add(bullet);
            } else {
                bullet.setPosY(newY);
            }
        }
        enemyBullets.removeAll(bulletsToRemove);
    }

    public List<EnemyBulletObject> getEnemyBullets() {
        return new ArrayList<>(enemyBullets);
    }
}
