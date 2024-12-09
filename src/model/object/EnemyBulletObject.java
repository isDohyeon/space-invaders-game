package model.object;

public class EnemyBulletObject extends GameObject {

    public EnemyBulletObject(int posX, int posY, String imagePath) {
        super(posX, posY, imagePath);
        this.speed = 3;
    }
}
