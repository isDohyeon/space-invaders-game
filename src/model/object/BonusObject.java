package model.object;

public class BonusObject extends GameObject {

    public BonusObject(int posX, int posY, String imagePath) {
        super(posX, posY, imagePath);
        this.speed = 5;
    }
}
