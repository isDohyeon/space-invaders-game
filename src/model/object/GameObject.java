package model.object;

import javax.swing.*;
import java.awt.*;

import static view.OutputView.GAME_HEIGHT;
import static view.panel.GamePanel.GAME_PANEL_WIDTH;

public abstract class GameObject {

    protected int posX;
    protected int posY;
    protected Image image;
    protected int speed;

    public GameObject(int posX, int posY, String fileName) {
        this.posX = posX;
        this.posY = posY;
        image = new ImageIcon("out/images/" + fileName).getImage();
    }

    public void move(int dx, int dy) {
        posX += dx;
        posY += dy;
    }

    public boolean isValidMove() {
        if (posX < 0 || posX + image.getWidth(null) > GAME_PANEL_WIDTH) {
            return false;
        }
        if (posY < 0 || posY + image.getHeight(null) > GAME_HEIGHT) {
            return false;
        }
        return true;
    }

    public int getStartX() {
        return posX + (image.getWidth(null) / 2);
    }

    public void moveUp() {
        move(0, -speed);
    }

    public void moveDown() {
        move(0, speed);
    }

    public void moveLeft() {
        move(-speed, 0);
    }

    public void moveRight() {
        move(speed, 0);
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public Image getImage() {
        return image;
    }
}
