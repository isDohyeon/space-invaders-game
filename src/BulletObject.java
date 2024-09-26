import javax.swing.*;

public class BulletObject extends GameObject implements Runnable {
    public BulletObject(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    public String getImage() {
        return "!";  // 총알을 나타내는 이미지
    }

    @Override
    public void setImage(String image) {
        super.setImage(image);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);  // 총알의 이동 속도 설정
                if (getPosY() <= 0) {
                    SpaceInvaderApp.centerTextArea.replaceRange(" ", getIndex(), getIndex() + 1);
                    return;
                }
                SpaceInvaderApp.replacePos(this, 0, -1);
         } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
