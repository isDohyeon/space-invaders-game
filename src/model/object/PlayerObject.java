package model.object;

public class PlayerObject extends GameObject {

    public PlayerObject(int posX, int posY, String imagePath) {
        super(posX, posY, imagePath);
        speed = 5;
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);
        if (!isValidMove()) {
            posX -= dx;
            posY -= dy;
        }
    }

    public void moveUpLeft() {
        moveUp();
        moveLeft();
    }

    public void moveUpRight() {
        moveUp();
        moveRight();
    }

    public void moveDownLeft() {
        moveDown();
        moveLeft();
    }

    public void moveDownRight() {
        moveDown();
        moveRight();
    }
}
