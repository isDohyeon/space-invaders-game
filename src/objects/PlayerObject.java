package objects;

public class PlayerObject extends GameObject {

    public static final int INIT_PLAYER_X = 36;
    public static final int INIT_PLAYER_Y = 23;

    private static final String PLAYER_IMAGE = ">-O-<";

    private boolean isAlive;

    public PlayerObject() {
        super(INIT_PLAYER_X, INIT_PLAYER_Y);
        image = PLAYER_IMAGE;
        isAlive = true;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean getAlive() {
        return isAlive;
    }
}
