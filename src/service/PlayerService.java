package service;

import java.util.List;
import objects.BulletObject;
import objects.PlayerObject;

public class PlayerService {

    private final PlayerObject player;
    private final PlayerBulletService playerBulletService;

    public PlayerService() {
        this.player = new PlayerObject();
        this.playerBulletService = new PlayerBulletService();
    }

    public void movePlayer(int dx, int dy) {
        player.setPosX(player.getPosX() + dx);
        player.setPosY(player.getPosY() + dy);
    }

    public void fireBullet() {
        playerBulletService.createBullet(player.getPosX(), player.getPosY() - 1);
    }

    public void removePlayerBullet(BulletObject playerBullet) {
        playerBulletService.removePlayerBullet(playerBullet);
    }

    public PlayerObject getPlayer() {
        return player;
    }

    public List<BulletObject> getPlayerBullets() {
        return playerBulletService.getPlayerBullets();
    }
}
