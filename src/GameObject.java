public class GameObject {

    private int posX;
    private int posY;
    private String image;
    private String replaceImage;

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

    public String getReplaceImage() {
        return replaceImage;
    }

    public void setReplaceImage(String replaceImage) {
        this.replaceImage = replaceImage;
    }

    public int getIndex() {
        return (getPosY() * (GameView.CENTER_WIDTH + 1)) + getPosX();
    }
}
