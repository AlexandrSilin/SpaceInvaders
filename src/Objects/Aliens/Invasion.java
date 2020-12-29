package Objects.Aliens;

import Game.Constants;
import Game.Score;
import Objects.ship.AttackInvasion;
import static Objects.Aliens.AliensView.*;

import java.awt.*;
import java.util.Random;

public class Invasion {
    private boolean direction = true;
    private final Alien [][] aliens = new Alien[Constants.ALIENS_LINES][Constants.ALIENS_COLUMNS];
    Random random = new Random();
    private int countAliens = Constants.ALIENS_LINES * Constants.ALIENS_COLUMNS;
    private int isAnimation = 0;
    private int firePower = 50;
    private Score score;

    public Invasion(Score score){
        this.score = score;
        for (int col = 0; col < Constants.ALIENS_COLUMNS; col++){
            this.aliens[0][col] = new Alien(Constants.X_POS_ALIEN + (Constants.COLUMN_SPACE_ALIEN +
                    Constants.ALIEN_HEIGHT) * col, Constants.Y_POS_ALIEN, alienBase, alienBaseAnimation);
            for (int line = 1; line < Constants.ALIENS_LINES - 2; line++)
                this.aliens[line][col] = new Alien(Constants.X_POS_ALIEN + (Constants.COLUMN_SPACE_ALIEN +
                        Constants.ALIEN_HEIGHT) * col, Constants.Y_POS_ALIEN + Constants.LINE_SPACE_ALIEN * line,
                        alienMiddle, alienMiddleAnimation);
            for (int line = 3; line < Constants.ALIENS_LINES; line++)
                this.aliens[line][col] = new Alien(Constants.X_POS_ALIEN + (Constants.COLUMN_SPACE_ALIEN +
                        Constants.ALIEN_HEIGHT) * col, Constants.Y_POS_ALIEN + Constants.LINE_SPACE_ALIEN * line,
                        alienSupreme, alienSupremeAnimation);
        }
    }

    private void increaseFirepower(){
        if (this.firePower > 5)
            this.firePower -= 5;
    }

    private void addScore(int line){
        if (line == 0)
            score.addScore(Constants.ALIEN_SUPREME);
        else if (line < 3)
            score.addScore(Constants.ALIEN_MIDDLE);
        else
            score.addScore(Constants.ALIEN_BASE);
    }

    public void alienGetKilled(AttackInvasion attackInvasion){
        for (int col = 0; col < Constants.ALIENS_COLUMNS; col++)
            for (int line = 0; line < Constants.ALIENS_LINES; line++)
                if (this.aliens[line][col] != null)
                    if (attackInvasion.getHit(this.aliens[line][col])){
                        this.aliens[line][col].isDead();
                        countAliens--;
                        attackInvasion.setyPos(-1);
                        attackInvasion.setShoot(false);
                        addScore(line);
                        increaseFirepower();
                        break;
                    }
    }

    public int getFirePower(){
        return firePower;
    }

    public int getCountAliens(){
        return countAliens;
    }

    public void drawAliens(Graphics graphics){
        for (int col = 0; col < Constants.ALIENS_COLUMNS; col++)
            for (int line = 0; line < Constants.ALIENS_LINES; line++)
                if (this.aliens[line][col] != null)
                    if (this.aliens[line][col].isAlive()){
                        if (this.aliens[line][col].isAnimation()) {
                            graphics.drawImage(this.aliens[line][col].getImg1(), this.aliens[line][col].getxPos(),
                                    this.aliens[line][col].getyPos(), null);
                            if (isAnimation % 3 == 0)
                                this.aliens[line][col].setAnimation(false);
                        }
                        else {
                            graphics.drawImage(this.aliens[line][col].getImg2(), this.aliens[line][col].getxPos(),
                                    this.aliens[line][col].getyPos(), null);
                            if (isAnimation % 12 == 0)
                                this.aliens[line][col].setAnimation(true);
                        }
                    }
                    else {
                        graphics.drawImage(this.aliens[line][col].getImg3(), this.aliens[line][col].getxPos(),
                                this.aliens[line][col].getyPos(), null);
                        if (isAnimation % 6 == 0)
                            this.aliens[line][col] = null;
                    }
        isAnimation++;
    }

    private void changeDirection(){
        direction = !direction;
        for (int col = Constants.ALIENS_COLUMNS - 1; col >= 0; col--)
            for (int line = Constants.ALIENS_LINES - 1; line >= 0; line--)
                if (this.aliens[line][col] != null)
                    if (this.aliens[line][col].getyPos() < Constants.Y_POS_DEF - Constants.ALIEN_COMING * 2)
                        this.aliens[line][col].setyPos(this.aliens[line][col].getyPos() + Constants.ALIEN_COMING);
                    else
                        return;
    }

    private void checkBorder(){
        for (int col = 0; col < Constants.ALIENS_COLUMNS; col++)
            for (int line = 0; line < Constants.ALIENS_LINES; line++) {
                if (this.aliens[line][col] != null)
                    if (this.aliens[line][col].getxPos() == 450 || this.aliens[line][col].getxPos() == 20) {
                        changeDirection();
                        return;
                    }
            }
    }

    private void moveRight(){
        for (int col = 0; col < Constants.ALIENS_COLUMNS; col++)
            for (int line = 0; line < Constants.ALIENS_LINES; line++)
                if (this.aliens[line][col] != null)
                    this.aliens[line][col].setxPos(this.aliens[line][col].getxPos() + Constants.ALIEN_SPEED);
            checkBorder();
    }

    private void moveLeft(){
        for (int col = 0; col < Constants.ALIENS_COLUMNS; col++)
            for (int line = 0; line < Constants.ALIENS_LINES; line++)
                if (this.aliens[line][col] != null)
                    this.aliens[line][col].setxPos(this.aliens[line][col].getxPos() - Constants.ALIEN_SPEED);
        checkBorder();
    }

    public void move(){
        if (direction)
            moveRight();
        else
            moveLeft();
    }

    public int[] alienFire(){
        int[] alienPosition = {-1, -1};
        if (countAliens != 0)
            do {
                int col = random.nextInt(Constants.ALIENS_COLUMNS);
                for (int line = Constants.ALIENS_LINES - 1; line >= 0; line--)
                    if (aliens[line][col] != null) {
                        alienPosition[0] = this.aliens[line][col].getxPos();
                        alienPosition[1] = this.aliens[line][col].getyPos();
                        break;
                    }
            } while (alienPosition[0] == -1);
        return alienPosition;
    }
}