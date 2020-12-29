package Objects;

import Game.Constants;

import java.awt.*;

public class Defence {
    private final int LINES = Constants.DEF_HEIGHT / Constants.DEF;
    private final int COLUMNS = Constants.DEF_WIDTH / Constants.DEF;

    private final int xPos;
    private final int yPos;

    boolean[][] model = new boolean[LINES][COLUMNS];

    public Defence(int xPos){
        this.xPos = xPos;
        this.yPos = Constants.Y_POS_DEF;
        for(int line = 0; line < LINES; line++)
            for(int col = 0; col < COLUMNS; col++)
                model[line][col] = true;

        for(int col = 0; col < 6; col++)
            for(int line = 0; line < 2; line++) {
                model[line][col] = false;
                model[line][COLUMNS - col - 1] = false;
            }

        for(int col = 0; col < 4; col++)
            for(int line = 2; line < 4; line++) {
                model[line][col] = false;
                model[line][COLUMNS - col - 1] = false;
            }

        for(int col = 0; col < 2; col++)
            for(int line = 4; line < 6; line++) {
                model[line][col] = false;
                model[line][COLUMNS - col - 1] = false;
            }

        for(int line = 18; line < LINES; line++)
            for(int col = 10; col < COLUMNS-10; col++)
                model[line][col] = false;

        for(int col = 12; col < COLUMNS - 12; col++)
            for(int line = 16; line < 18; line++) {
                model[line][col] = false;
                model[line][COLUMNS - col - 1] = false;
            }

        for(int col = 14; col < COLUMNS - 14; col++)
            for(int line = 14; line < 16; line++) {
                model[line][14] = false;
                model[line][COLUMNS - col - 1] = false;
            }
    }

    public int getxPos() {
        return xPos;
    }

    public int getCol(int shot) {
        return (shot - this.xPos) / Constants.DEF;
    }

    public int findBricks(int col) {
        int line = LINES - 1;
        while(line >= 0 && !model[line][col])
            line--;
        return line;
    }

    private void deleteBrick(int line, int col) {
        for(int i = 0; i < 6; i++)
            if(line - i >= 0) {
                model[line - i][col] = false;
                if(col < COLUMNS - 1)
                    model[line - i][col + 1] = false;
            }
    }

    public void destroyedBricks(int shot) {
        int col = this.getCol(shot);
        this.deleteBrick(findBricks(col), col);
    }

    public int findBricksFromInvasionSide(int col) {
        int line = 0;
        if (col != -1)
            while(line < LINES && !model[line][col])
                line++;
        return line;
    }

    private void deleteBrickFromInvasionSide(int line, int col) {
        for(int i = 0; i < 6; i++)
            if(line + i < LINES && col != -1) {
                model[line + i][col] = false;
                if(col < COLUMNS - 1)
                    model[line + i][col + 1] = false;
            }
    }

    public void destroyedBricksFromInvasionSide(int shot) {
        int col = this.getCol(shot);
        this.deleteBrickFromInvasionSide(findBricksFromInvasionSide(col), col);
    }

    public void drawDefence(Graphics graphics) {
        graphics.setColor(Color.GREEN);
        for (int line = 0; line < LINES; line++)
            for (int col = 0; col < COLUMNS; col++)
                if (model[line][col])
                    graphics.fillRect(this.xPos + Constants.DEF * col, this.yPos + Constants.DEF * line,
                        Constants.DEF, Constants.DEF);
    }
}
