public class GameObject {

    private int posX;
    private int posY;
    private String image;

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

    public void setImage(String image) {
        this.image = image;
    }

    public int getIndex() {
        return (getPosY() * (SpaceInvaderApp.CENTER_WIDTH + 1)) + getPosX();
    }
}
