package Objects.ship;

import Game.Constants;
import Game.Scene;
import Objects.Aliens.Alien;
import Objects.Aliens.RedAlien;
import Objects.Defence;

import javax.swing.*;
import java.awt.*;

public class AttackInvasion {
    private int xPos;
    private int yPos;
    private final Image img;
    private boolean isShoot;

    public AttackInvasion(){
        this.img = new ImageIcon(getClass().getResource("/images/ship/laser.png")).getImage();
        this.isShoot = false;
    }

    public boolean isShoot() {
        return isShoot;
    }

    public void setShoot(boolean shoot){
        this.isShoot = shoot;
    }

    public int shoot(){
        if (isShoot)
            if (this.yPos > 0)
                this.yPos -= Constants.HEIGHT_LASER;
            else this.isShoot = false;
            return yPos;
    }

    public boolean hitRedAlien(int xPos, int yPos){
        return this.yPos < yPos + Constants.RED_ALIEN_HEIGHT &&
                this.yPos > yPos - Constants.RED_ALIEN_HEIGHT &&
                this.xPos < xPos + Constants.RED_ALIEN_WIDTH &&
                this.xPos > xPos - Constants.RED_ALIEN_WIDTH;
    }

    public boolean getHit(Alien alien){
        return this.yPos < alien.getyPos() + Constants.ALIEN_HEIGHT &&
                this.yPos > alien.getyPos() - Constants.ALIEN_HEIGHT &&
                this.xPos < alien.getxPos() + Constants.ALIEN_WIDTH + 15 &&
                this.xPos > alien.getxPos() - Constants.ALIEN_WIDTH + 15;
    }

    private boolean checkHigh() {
        return this.yPos < Constants.Y_POS_DEF + Constants.DEF_HEIGHT &&
                this.yPos + Constants.HEIGHT_LASER > Constants.Y_POS_DEF;
    }

    private int getNumberDefence() {
        int number = -1;
        int col = -1;
        while (number == -1 && col < 4) {
            col++;
            if(this.xPos + Constants.WIDTH_LASER > Constants.MARGIN + Constants.X_POS_DEF + col *
                    (Constants.DEF_WIDTH + Constants.SPACE)
                    && this.xPos < Constants.MARGIN + Constants.X_POS_DEF + Constants.DEF_WIDTH + col *
                    (Constants.DEF_WIDTH + Constants.SPACE)) {
                number = col;
            }
        }
        return number;
    }

    private int checkHit(Defence defence) {
        int xPosHit = -1;
        if(this.xPos + Constants.WIDTH_LASER > defence.getxPos() && this.xPos < defence.getxPos() + Constants.DEF_WIDTH)
            xPosHit = this.xPos;
        return xPosHit;
    }

    public int[] getDestruction() {
        int[] destruction = {-1, -1};
        if(this.checkHigh()) {
            destruction[0] = this.getNumberDefence();
            if(destruction[0] != -1)
                destruction[1] = this.checkHit(Scene.defence[destruction[0]]);
        }
        return destruction;
    }

    public void hitDefence(Defence[] defences) {
        int[] destruct = this.getDestruction();
        if(destruct[0] != -1)
            if(defences[destruct[0]].findBricks(defences[destruct[0]].getCol(destruct[1])) != -1) {
                defences[destruct[0]].destroyedBricks(destruct[1]);
                this.yPos = -Constants.HEIGHT_LASER + 1;
            }
    }

    public void drawLaser(Graphics graphics){
        if (isShoot)
            graphics.drawImage(this.img, this.xPos, this.shoot(), null);
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
}