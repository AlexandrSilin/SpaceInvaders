package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import Objects.Aliens.AttackShip;
import Objects.Aliens.Invasion;
import Objects.Aliens.RedAlien;
import Objects.Defence;
import Objects.ship.AttackInvasion;
import Objects.ship.Ship;

public class Scene extends JPanel {
    public int wave = 1;
    public Ship ship = new Ship();
    public Score score = new Score();
    public Invasion invasion = new Invasion(score);
    public AttackInvasion attackInvasion = new AttackInvasion();
    public static Defence [] defence = new Defence[Constants.DEF_COUNT];
    public AttackShip[] alienFire = new AttackShip[Constants.ALIEN_FIRE_POWER];
    public RedAlien redAlien;
    Random random = new Random(5000);

    public Scene(){
        super();
        this.setFocusable(true);
        this.requestFocusInWindow();
        for (int col = 0; col < defence.length; col++)
            defence[col] = new Defence(Constants.MARGIN + Constants.X_POS_DEF + col *
                    (Constants.DEF_WIDTH + Constants.SPACE));
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (ship.isAlive()) {
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT && ship.getxPos() - Constants.SHIP_WIDTH < 390)
                        ship.setxPos(ship.getxPos() + Constants.SHIP_SPEED);
                    if (e.getKeyCode() == KeyEvent.VK_LEFT && ship.getxPos() - Constants.SHIP_WIDTH > -24)
                        ship.setxPos(ship.getxPos() - Constants.SHIP_SPEED);
                    if (e.getKeyCode() == KeyEvent.VK_SPACE)

                        if (!attackInvasion.isShoot()) {
                            attackInvasion.setxPos(ship.getxPos() + Constants.SHIP_WIDTH + 4);
                            attackInvasion.setyPos(ship.getyPos());
                            attackInvasion.setShoot(true);
                        }
                }
            }
        });
        Thread Game = new Thread(new Game());
        Game.start();
    }

    private void drawField(Graphics graphics){
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
        graphics.setColor(Color.GREEN);
        graphics.fillRect(15, 515, 455, 3);
    }

    private void drawInvasion(Graphics graphics){
        invasion.drawAliens(graphics);
        attackInvasion.drawLaser(graphics);
        this.invasion.move();
        this.invasion.alienGetKilled(this.attackInvasion);
        if (invasion.getCountAliens() == 0) {
            invasion = new Invasion(score);
            wave++;
        }
    }

    private void drawDefence(Graphics graphics){
        for (Defence def : defence)
            def.drawDefence(graphics);
        this.attackInvasion.hitDefence(defence);
    }

    private void drawFireInvasion(Graphics graphics){
        for (int i = 0; i < Constants.ALIEN_FIRE_POWER * wave; i++) {
            if (random.nextInt() % invasion.getFirePower() == 0 && ship.isAlive()) {
                if (alienFire[i] == null)
                    alienFire[i] = new AttackShip(this.invasion.alienFire());
                    alienFire[i].setShoot(true);
            }
            if (alienFire[i] != null) {
                alienFire[i].drawShoot(graphics);
                alienFire[i].hitDefence(defence);
                if (alienFire[i].hitShip(this.ship))
                    ship.blowUp(graphics);
                if (!alienFire[i].isShoot())
                    alienFire[i] = null;
            }
        }
    }

    private void drawRedAlien(Graphics graphics){
        if (random.nextInt() % 87 == 0 && redAlien == null){
            redAlien = new RedAlien(score);
        }
        if (redAlien != null) {
            redAlien.drawRedAlien(graphics);
            redAlien.move();
            redAlien.alienGetKilled(attackInvasion, graphics);
            if (!redAlien.isAlive())
                redAlien = null;
        }
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        drawField(graphics);
        drawRedAlien(graphics);
        if (ship.isAlive())
            ship.drawShip(graphics);
        drawInvasion(graphics);
        drawDefence(graphics);
        drawFireInvasion(graphics);
        graphics.setFont(score.getFont());
        graphics.drawString("SCORE: " + score.getPoints(), Constants.HEIGHT / 2 , 30);
    }
}
