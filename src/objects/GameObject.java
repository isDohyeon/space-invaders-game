package objects;

import view.GameView;

public abstract class GameObject {

    protected int posX;
    protected int posY;
    protected String image;

    public GameObject(int posX, int posY) {
        setPosX(posX);
        setPosY(posY);
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getImage() {
        return image;
    }

    public int getIndex() {
        return (getPosY() * (GameView.CENTER_WIDTH + 1)) + getPosX();
    }
}
