package model.object;

public class BulletObject extends GameObject {

    public BulletObject(int posX, int posY, String imagePath) {
        super(posX, posY, imagePath);
        this.speed = 8;
    }
}
