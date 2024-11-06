package objects;

public class EnemyObject extends GameObject {

    public static final int INIT_ENEMY_AMOUNT = 8;
    public static final int INIT_ENEMY_X = 7;
    public static final int INIT_ENEMY_Y = 1;

    private static final String ENEMY_IMAGE = "[XUX]";

    public EnemyObject(int posX, int posY) {
        super(posX, posY);
        image = ENEMY_IMAGE;
    }
}
