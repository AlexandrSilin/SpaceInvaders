package Game;

import java.awt.*;

public class Score {
    //private Font score = new Font("Arial", Font.PLAIN, 20);
    private Font text = new Font("Arial", Font.PLAIN, 25);
    int points;

    public Score(){
        this.points = 0;
    }

    public void addScore(int points){
        this.points += points;
    }

    public int getPoints(){
        return points;
    }

    public Font getFont(){
        return text;
    }
}
