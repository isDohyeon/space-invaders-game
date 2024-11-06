package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import objects.BulletObject;

public class PlayerBulletService {

    private static final int BULLET_TIMER_SPEED = 30;

    private final List<BulletObject> playerBullets = new ArrayList<>();
    private final Timer bulletTimer = new Timer();

    public PlayerBulletService() {
        scheduleBulletMovement();
    }

    private void scheduleBulletMovement() {
        bulletTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                updateBullets();
            }
        }, 0, BULLET_TIMER_SPEED);
    }

    private void updateBullets() {
        List<BulletObject> bulletsToRemove = new ArrayList<>();
        for (BulletObject bullet : new ArrayList<>(playerBullets)) {
            int newY = bullet.getPosY() - 1;
            if (newY < 0) {
                bulletsToRemove.add(bullet);
            } else {
                bullet.setPosY(newY);
            }
        }
        playerBullets.removeAll(bulletsToRemove);
    }

    public void createBullet(int posX, int posY) {
        BulletObject bullet = new BulletObject(posX, posY);
        playerBullets.add(bullet);
    }

    public void removePlayerBullet(BulletObject playerBullet) {
        playerBullets.remove(playerBullet);
    }

    public List<BulletObject> getPlayerBullets() {
        return new ArrayList<>(playerBullets);
    }
}
