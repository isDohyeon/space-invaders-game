package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import objects.EnemyBulletObject;
import objects.EnemyObject;
import view.GameView;

public class EnemyService {

    private final List<EnemyObject> enemies = new ArrayList<>();
    private final EnemyBulletService enemyBulletService;
    private final Timer enemyTimer = new Timer();

    private int enemyDirection = 1;
    private int enemySpeed = 200;

    public EnemyService() {
        createEnemies();
        scheduleEnemyMovement();
        this.enemyBulletService = new EnemyBulletService(enemies);
    }

    private void createEnemies() {
        int x = EnemyObject.INIT_ENEMY_X;
        int y = EnemyObject.INIT_ENEMY_Y;
        for (int i = 0; i < EnemyObject.INIT_ENEMY_AMOUNT; i++) {
            EnemyObject enemy = new EnemyObject(x, y);
            enemies.add(enemy);
            x += 6;
            y += i % 2 == 0 ? 2 : -2;
        }
    }

    private void scheduleEnemyMovement() {
        enemyTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateEnemies();
                enemySpeed--;
            }
        }, 0, enemySpeed);
    }

    private void updateEnemies() {
        if (enemies.stream()
                .anyMatch(enemy -> !isValidMove(enemy.getPosX() + enemyDirection))) {
            enemies.forEach(enemy -> enemy.setPosY(enemy.getPosY() + 1));
            enemyDirection *= -1;
        } else {
            enemies.forEach(enemy -> enemy.setPosX(enemy.getPosX() + enemyDirection));
        }
    }

    private boolean isValidMove(int newX) {
        return newX > -1 && newX < GameView.CENTER_WIDTH - 4;
    }

    public void removeEnemy(EnemyObject enemy) {
        enemies.remove(enemy);
    }

    public List<EnemyObject> getEnemies() {
        return new ArrayList<>(enemies);
    }

    public List<EnemyBulletObject> getEnemyBullets() {
        return enemyBulletService.getEnemyBullets();
    }
}
