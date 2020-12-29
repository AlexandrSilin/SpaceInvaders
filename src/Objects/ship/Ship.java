package Objects.ship;

import Game.Constants;

import javax.swing.*;
import java.awt.*;

public class Ship implements ShipView {
    private int xPos = Constants.X_POS_SHIP;
    private final int yPos = Constants.Y_POS_SHIP;
    private boolean isAlive;
    private final Image img = new ImageIcon(getClass().getResource(ship)).getImage();

    public Ship(){
        isAlive = true;
    }

    public boolean isAlive(){
        return isAlive;
    }

    public int getxPos(){
        return xPos;
    }

    public int getyPos(){
        return yPos;
    }

    public void setxPos(int xPos){
        this.xPos = xPos;
    }

    public void blowUp(Graphics graphics){
        isAlive = false;
        graphics.drawImage(new ImageIcon(getClass().getResource(shipBlowUp1)).getImage(), this.xPos, this.yPos,
                null);
        graphics.drawImage(new ImageIcon(getClass().getResource(shipBlowUp2)).getImage(), this.xPos, this.yPos,
                null);
    }

    public void drawShip(Graphics graphics){
        graphics.drawImage(this.img, this.xPos, this.yPos, null);
    }
}
