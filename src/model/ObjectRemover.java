package model;

import model.object.EnemyObject;
import model.object.GameObject;
import model.object.PlayerObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static view.OutputView.GAME_HEIGHT;

public class ObjectRemover {

    private final GameData gameData;
    private final List<GameObject> removeObjects;
    private boolean playerDead;

    public ObjectRemover(GameData gameData) {
        this.gameData = gameData;
        this.removeObjects = new ArrayList<>();
        this.playerDead = false;
    }

    public void removeObjects() {
        findCollisions(gameData);
        removeOutOfBoundsObjects(gameData);
        gameData.removeObjects(removeObjects);
        removeObjects.clear();
    }

    public boolean isGameOver() {
        if (playerDead || gameData.getEnemies().isEmpty()) {
            return true;
        }
        for (GameObject enemy : gameData.getEnemies()) {
            if (enemy.getPosY() == GAME_HEIGHT) {
                playerDead = true;
                return true;
            }
        }
        return false;
    }

    public boolean isPlayerDead() {
        return playerDead;
    }

    private void removeOutOfBoundsObjects(GameData gameData) {
        for (GameObject object : gameData.getGameObjects()) {
            if (object instanceof EnemyObject || object == null) {
                continue;
            }
            if (!object.isValidMove()) {
                removeObjects.add(object);
            }
        }
    }

    private void findCollisions(GameData gameData) {
        findCollidingObjects(Collections.singletonList(gameData.getPlayer()), gameData.getEnemies());
        findCollidingObjects(Collections.singletonList(gameData.getPlayer()), gameData.getEnemyBullets());
        findCollidingObjects(gameData.getEnemies(), gameData.getPlayerBullets());
        if (gameData.getBonusObject() != null) {
            findCollidingObjects(Collections.singletonList(gameData.getBonusObject()), gameData.getPlayerBullets());
        }
    }

    private void findCollidingObjects(List<? extends GameObject> objects1, List<? extends GameObject> objects2) {
        for (GameObject object2 : objects2) {
            for (GameObject object1 : objects1) {
                if (checkCollision(object1, object2)) {
                    checkPlayer(object2, object1);
                    removeObjects.add(object1);
                    removeObjects.add(object2);
                    increaseScore(object1, object2);
                }
            }
        }
    }

    private void checkPlayer(GameObject object2, GameObject object1) {
        if (object1 instanceof PlayerObject || object2 instanceof PlayerObject) {
            playerDead = true;
        }
    }

    private void increaseScore(GameObject object1, GameObject object2) {
        gameData.increaseScore(object1);
        gameData.increaseScore(object2);
    }

    private boolean checkCollision(GameObject object1, GameObject object2) {
        Rectangle bounds1 = new Rectangle(object1.getPosX(), object1.getPosY(),
                object1.getImage().getWidth(null),
                object1.getImage().getHeight(null));
        Rectangle bounds2 = new Rectangle(object2.getPosX(), object2.getPosY(),
                object2.getImage().getWidth(null),
                object2.getImage().getHeight(null));
        return bounds1.intersects(bounds2);
    }
}
