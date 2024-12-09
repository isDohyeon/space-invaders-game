package model.object;

public class EnemyObject extends GameObject {

    private int direction;
    private double speed;

    public EnemyObject(int posX, int posY, String imagePath) {
        super(posX, posY, imagePath);
        speed = 1.0;
        direction = 1;
    }

    public void update() {
        move((int) speed, 0);
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx * direction, dy);
    }

    @Override
    public void moveDown() {
        posY += 20;
    }

    public void reverseDirection() {
        direction *= -1;
        move((int) speed, 0);
    }

    public void increaseSpeed() {
        speed += 0.5;
    }
}
