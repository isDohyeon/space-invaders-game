public class BulletObject extends GameObject implements Runnable {

    GameView view;

    public BulletObject(int posX, int posY, GameView view) {
        super(posX, posY);
        setImage("!");
        this.view = view;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
                if (getPosY() <= 0) {
                    view.centerTextArea.replaceRange(" ", getIndex(), getIndex() + 1);
                    return;
                }
                view.replacePos(this, 0, -1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}