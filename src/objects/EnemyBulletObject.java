package objects;

public class EnemyBulletObject extends GameObject {

    private static final String ENEMY_BULLET_IMG = "v";

    public EnemyBulletObject(int posX, int posY) {
        super(posX, posY);
        image = ENEMY_BULLET_IMG;
    }
}
