public class BulletObject extends GameObject implements Runnable {

    private final GameView view;

    public BulletObject(int posX, int posY, GameView view) {
        super(posX, posY);
        setImage("!");
        setReplaceImage(" ");
        this.view = view;
    }

    @Override
    public void run() {
        while (true) {
            if (getPosY() == 0) {
                view.replaceIndex(this, " ", 0);
                return;
            }

            view.replacePosition(this, 0, -1);

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}