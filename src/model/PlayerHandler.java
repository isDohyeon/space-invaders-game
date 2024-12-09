package model;

import model.object.PlayerObject;

public class PlayerHandler {

    private final GameData gameData;
    private final PlayerObject player;

    public PlayerHandler(GameData gameData) {
        this.gameData = gameData;
        this.player = gameData.getPlayer();
    }

    public boolean movePlayer(String direction) {
        if (player.isValidMove()) {
            move(direction);
            return true;
        }
        return false;
    }

    private void move(String direction) {
        switch (direction) {
            case "UP":
                player.moveUp();
                break;
            case "DOWN":
                player.moveDown();
                break;
            case "LEFT":
                player.moveLeft();
                break;
            case "RIGHT":
                player.moveRight();
                break;
            case "UP_LEFT":
                player.moveUpLeft();
                break;
            case "UP_RIGHT":
                player.moveUpRight();
                break;
            case "DOWN_LEFT":
                player.moveDownLeft();
                break;
            case "DOWN_RIGHT":
                player.moveDownRight();
                break;
            default:
                break;
        }
    }

    public void fireBullet() {
        gameData.createPlayerBullet();
    }
}
