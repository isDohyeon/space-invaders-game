package objects;

public class BulletObject extends GameObject {

    private static final String PLAYER_BULLET_IMG = "!";

    public BulletObject(int posX, int posY) {
        super(posX, posY);
        image = PLAYER_BULLET_IMG;
    }
}
