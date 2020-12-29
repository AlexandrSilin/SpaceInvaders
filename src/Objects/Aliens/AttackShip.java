package Objects.Aliens;

import Game.Constants;
import Game.Scene;
import Objects.Defence;
import Objects.ship.Ship;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class AttackShip {
    private final int xPos;
    private int yPos;
    private final Image img;
    private boolean isShoot;

    public AttackShip(int[] aliens) {
        this.xPos = aliens[0];
        this.yPos = aliens[1];
        Random random = new Random();
        if (random.nextInt() % 2 == 0)
            this.img = new ImageIcon(getClass().getResource(AliensView.laser1)).getImage();
        else
            this.img = new ImageIcon(getClass().getResource(AliensView.laser2)).getImage();
        this.isShoot = false;
    }

    public boolean hitShip(Ship ship){
        return this.yPos < ship.getyPos() + Constants.SHIP_HEIGHT &&
                this.yPos > ship.getyPos() - Constants.SHIP_HEIGHT &&
                this.xPos < ship.getxPos() + Constants.SHIP_WIDTH &&
                this.xPos > ship.getxPos() - Constants.SHIP_WIDTH;
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

    private boolean checkHigh() {
        return this.yPos < Constants.Y_POS_DEF + Constants.DEF_HEIGHT &&
                this.yPos + Constants.HEIGHT_LASER > Constants.Y_POS_DEF;
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
            if(defences[destruct[0]].findBricksFromInvasionSide(defences[destruct[0]].getCol(destruct[1])) != -1) {
                defences[destruct[0]].destroyedBricksFromInvasionSide(destruct[1]);
                this.yPos = Constants.HEIGHT + Constants.HEIGHT_LASER + 1;
                this.isShoot = false;
            }
    }

    public boolean isShoot(){
        return isShoot;
    }

    public int shoot(){
        if (isShoot)
            if (this.yPos < Constants.HEIGHT)
                this.yPos += Constants.HEIGHT_LASER;
            else this.isShoot = false;
        return yPos;
    }

    public void setShoot(boolean shoot){
        this.isShoot = shoot;
    }

    public void drawShoot(Graphics graphics){
        graphics.drawImage(this.img, this.xPos, this.shoot(), null);
    }
}