package Game;

import javax.swing.*;

public class RunGame {
    public static Scene scene;

    public static void main(String[] args) {
        JFrame field = new JFrame("Space Invaders");
        field.setSize(Constants.WIDTH, Constants.HEIGHT);
        field.setResizable(false);
        field.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        field.setLocation(700, 100);
        scene = new Scene();
        field.setContentPane(scene);
        field.setVisible(true);
    }
}