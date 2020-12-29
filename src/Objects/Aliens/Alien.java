package Objects.Aliens;

import javax.swing.*;
import java.awt.*;

public class Alien implements AliensView {
    private int xPos;
    private int yPos;

    private final Image img1;
    private final Image img2;
    private final Image img3;
    private boolean alive;
    public boolean animation = true;

    public Alien(int xPos, int yPos, String img1, String img2){
        this.xPos = xPos;
        this.yPos = yPos;
        this.img1 = new ImageIcon(getClass().getResource(img1)).getImage();
        this.img2 = new ImageIcon(getClass().getResource(img2)).getImage();
        this.img3 = new ImageIcon(getClass().getResource(alienDead)).getImage();
        alive = true;
    }

    public boolean isAnimation() {
        return animation;
    }

    public boolean isAlive(){
        return alive;
    }

    public void isDead(){
        alive = false;
    }

    public void setAnimation(boolean animation) {
        this.animation = animation;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setxPos(int xPos){
        this.xPos = xPos;
    }

    public void setyPos(int yPos){
        this.yPos = yPos;
    }

    public Image getImg1() {
        return img1;
    }

    public Image getImg2() {
        return img2;
    }

    public Image getImg3(){
        return img3;
    }
}
