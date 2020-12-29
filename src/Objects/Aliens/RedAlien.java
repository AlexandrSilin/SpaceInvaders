package Objects.Aliens;

import Game.Constants;
import Game.Score;
import Objects.ship.AttackInvasion;

import javax.swing.*;
import java.awt.*;

public class RedAlien implements AliensView{
    private int xPox;
    private final int yPos;
    private final Image img = new ImageIcon(getClass().getResource(redAlien)).getImage();
    private final Image bonus = new ImageIcon(getClass().getResource(bonusForRedAlien)).getImage();
    private boolean isAlive;
    private Score score;

    public RedAlien(Score score){
        this.score = score;
        this.isAlive = true;
        this.xPox = Constants.RED_ALIEN_X_POS;
        this.yPos = Constants.RED_ALIEN_Y_POS;
    }

    public void alienGetKilled(AttackInvasion attackInvasion, Graphics graphics){
        if (attackInvasion.hitRedAlien(this.xPox, this.yPos)){
            graphics.drawImage(this.bonus, this.xPox, this.yPos, null);
            isAlive = false;
            score.addScore(Constants.RED_ALIEN);
        }
    }

    public boolean isAlive(){
        return isAlive;
    }

    public void move(){
        this.xPox += Constants.RED_ALIEN_SPEED;
        isAlive = this.xPox < Constants.WIDTH + Constants.RED_ALIEN_WIDTH;
    }

    public void drawRedAlien(Graphics graphics){
        graphics.drawImage(this.img, this.xPox, this.yPos, null);
    }
}
