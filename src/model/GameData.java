package model;

import model.object.*;

import java.util.ArrayList;
import java.util.List;

import static view.OutputView.GAME_HEIGHT;
import static view.panel.GamePanel.GAME_PANEL_WIDTH;

public class GameData {

    private final List<GameObject> gameObjects;
    private final List<GameObject> playerBullets;
    private final List<GameObject> enemies;
    private final List<GameObject> enemyBullets;
    private PlayerObject player;
    private BonusObject bonusObject;
    private long score;

    public GameData() {
        gameObjects = new ArrayList<>();
        playerBullets = new ArrayList<>();
        enemies = new ArrayList<>();
        enemyBullets = new ArrayList<>();
        createPlayer();
        createEnemies();
        score = 0;
    }

    private void createPlayer() {
        player = new PlayerObject(GAME_PANEL_WIDTH / 2, GAME_HEIGHT / 4 * 3, "player.png");
        gameObjects.add(player);
    }

    private void createEnemies() {
        int enemyAmount = 8;
        int x = 100;
        int y = 70;
        for (int i = 0; i < enemyAmount; i++) {
            enemies.add(new EnemyObject(x, y, "enemy.png"));
            x += 100;
            y += i % 2 != 0 ? -70 : 70;
        }
    }

    public void createEnemyBullet(GameObject enemy) {
        enemyBullets.add(new EnemyBulletObject(enemy.getStartX(), enemy.getPosY(), "enemy_bullet.png"));
    }

    public void createPlayerBullet() {
        playerBullets.add(new BulletObject(player.getStartX(), player.getPosY(), "player_bullet.png"));
    }

    public void createBonusObject() {
        bonusObject = new BonusObject(0, 10, "bonus.png");
    }

    public void increaseScore(GameObject object) {
        if (object instanceof BonusObject) {
            score += 100;
        }
        if (object instanceof EnemyObject) {
            score += 10;
        }
    }

    public void removeObjects(List<GameObject> removeObjects) {
        gameObjects.removeAll(removeObjects);
        playerBullets.removeAll(removeObjects);
        enemies.removeAll(removeObjects);
        enemyBullets.removeAll(removeObjects);
        if (removeObjects.contains(bonusObject)) {
            bonusObject = null;
        }
    }

    public List<GameObject> getGameObjects() {
        gameObjects.addAll(playerBullets);
        gameObjects.addAll(enemies);
        gameObjects.addAll(enemyBullets);
        if (bonusObject != null) {
            gameObjects.add(bonusObject);
        }
        return new ArrayList<>(gameObjects);
    }

    public List<GameObject> getPlayerBullets() {
        return new ArrayList<>(playerBullets);
    }

    public List<GameObject> getEnemies() {
        return new ArrayList<>(enemies);
    }

    public List<GameObject> getEnemyBullets() {
        return new ArrayList<>(enemyBullets);
    }

    public PlayerObject getPlayer() {
        return player;
    }

    public BonusObject getBonusObject() {
        return bonusObject;
    }

    public long getScore() {
        return score;
    }
}
