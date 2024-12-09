package model;

import model.object.*;

import java.util.List;
import java.util.Random;

public class ObjectHandler {

    private final GameData gameData;
    private final ObjectRemover remover;

    public ObjectHandler(GameData gameData) {
        this.gameData = gameData;
        this.remover = new ObjectRemover(gameData);
    }

    public void updateGameObjects() {
        updatePlayerBullets();
        updateEnemies();
        updateEnemyBullets();
        updateBonusObject();
        remover.removeObjects();
    }

    private void updateEnemyBullets() {
        for (GameObject enemyBullet : gameData.getEnemyBullets()) {
            enemyBullet.moveDown();
        }
    }

    private void updateEnemies() {
        List<GameObject> enemies = gameData.getEnemies();
        for (GameObject enemy : enemies) {
            if (!enemy.isValidMove()) {
                updateEnemyStates(enemies);
                break;
            }
        }

        for (GameObject e : enemies) {
            EnemyObject enemy = (EnemyObject) e;
            enemy.update();
        }
    }

    private void updateEnemyStates(List<GameObject> enemies) {
        for (GameObject e : enemies) {
            EnemyObject enemy = (EnemyObject) e;
            enemy.reverseDirection();
            enemy.moveDown();
            enemy.increaseSpeed();
        }
    }

    private void updatePlayerBullets() {
        for (GameObject playerBullet : gameData.getPlayerBullets()) {
            playerBullet.moveUp();
        }
    }

    private void updateBonusObject() {
        BonusObject bonusObject = gameData.getBonusObject();
        if (bonusObject != null) {
            bonusObject.moveRight();
        }
    }

    public void createBonusObject() {
        if (gameData.getBonusObject() != null) {
            return;
        }
        if (new Random().nextInt(2) == 0) {
            gameData.createBonusObject();
        }
    }

    public void createEnemyBullet() {
        for (GameObject enemy : gameData.getEnemies()) {
            if (new Random().nextInt(2) == 0) {
                gameData.createEnemyBullet(enemy);
            }
        }
    }

    public boolean isGameOver() {
        return remover.isGameOver();
    }

    public boolean isPlayerDead() {
        return remover.isPlayerDead();
    }

    public GameData getGameData() {
        return gameData;
    }
}
