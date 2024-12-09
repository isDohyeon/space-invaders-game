package controller;

import model.ObjectHandler;
import model.PlayerHandler;
import view.InputView;
import view.OutputView;

public class GameController implements Runnable {

    private final ObjectHandler objectHandler;
    private final PlayerHandler playerHandler;
    private final InputView inputView;
    private final OutputView outputView;
    private long cycleStartTime;
    private boolean playerLose;
    private boolean isGameOver;

    public GameController(PlayerHandler playerHandler, ObjectHandler objectHandler, InputView inputView, OutputView outputView) {
        this.objectHandler = objectHandler;
        this.playerHandler = playerHandler;
        this.inputView = inputView;
        this.outputView = outputView;
        cycleStartTime = System.currentTimeMillis();
    }

    @Override
    public void run() {
        while (!isGameOver) {
            sleep();
            handlePlayerAction();
            createGameObjects();
            objectHandler.updateGameObjects();
            updateView();
            isGameOver = objectHandler.isGameOver();
        }
        playerLose = objectHandler.isPlayerDead();
    }

    private void createGameObjects() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - cycleStartTime >= 1000) {
            objectHandler.createEnemyBullet();
            objectHandler.createBonusObject();
            cycleStartTime = currentTime;
        }
    }

    private void updateView() {
        outputView.updateGameView(objectHandler.getGameData());
        outputView.updateScore(objectHandler.getGameData());
    }

    private void sleep() {
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void handlePlayerAction() {
        String command = inputView.getLastCommand();
        if (playerHandler.movePlayer(command)) {
            updateView();
        }
        if (inputView.isSpacePressed()) {
            playerHandler.fireBullet();
        }
    }

    public boolean isPlayerLose() {
        return playerLose;
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
