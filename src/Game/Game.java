package Game;

public class Game implements Runnable{

    @Override
    public void run(){
        while (true){
            RunGame.scene.repaint();
            int DELAY = 30;
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
