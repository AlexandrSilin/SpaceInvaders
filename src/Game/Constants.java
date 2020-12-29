package Game;

public interface Constants {
    int WIDTH = 500;
    int HEIGHT = 600;
    int MARGIN = 45;

    int SHIP_WIDTH = 14;
    int SHIP_HEIGHT = 15;
    int X_POS_SHIP = (WIDTH - SHIP_WIDTH) / 2;
    int Y_POS_SHIP = 490;
    int SHIP_SPEED = 3;

    int ALIEN_WIDTH = 19;
    int ALIEN_HEIGHT = 25;
    int Y_POS_ALIEN = 70;
    int X_POS_ALIEN = 29 + MARGIN;
    int LINE_SPACE_ALIEN = 40;
    int COLUMN_SPACE_ALIEN = 12;
    int ALIEN_SPEED = 1;
    int ALIEN_COMING = 20;
    int ALIENS_COLUMNS = 10;
    int ALIENS_LINES = 5;
    int ALIEN_FIRE_POWER = 3;

    int RED_ALIEN_WIDTH = 50;
    int RED_ALIEN_HEIGHT = 25;
    int RED_ALIEN_X_POS = -RED_ALIEN_WIDTH;
    int RED_ALIEN_Y_POS = MARGIN;
    int RED_ALIEN_SPEED = 3;

    int DEF_COUNT = 4;
    int DEF = 2;
    int DEF_WIDTH = 72;
    int DEF_HEIGHT = 54;
    int Y_POS_DEF = 420;
    int X_POS_DEF = -20;
    int SPACE = 50;

    int HEIGHT_LASER = 13;
    int WIDTH_LASER = 3;

    int ALIEN_BASE = 20;
    int ALIEN_MIDDLE = 40;
    int ALIEN_SUPREME = 60;
    int RED_ALIEN = 100;
}
