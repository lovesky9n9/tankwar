import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Tank extends GameObject {
    protected int speed;
    protected Direction direction;
    protected boolean[] dirs = new boolean[4];//上下左右四個方向
    protected boolean enemy;

    public Tank(int x, int y, Direction direction, Image[] image) {//玩家坦克
        this(x, y, direction, false, image);
    }

    public Tank(int x, int y, Direction direction, boolean enemy, Image[] image) {//敵方坦克
        super(x, y, image);
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.speed = 15;
        this.enemy = enemy;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean[] getDirs() {
        return dirs;
    }

    public void move() {//移動坦克
        oldX = x;
        oldY = y;
        switch (direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case UP_LEFT:
                y -= speed;
                x -= speed;
                break;
            case UP_RIGHT:
                y -= speed;
                x += speed;
                break;
            case DOWN_LEFT:
                y += speed;
                x -= speed;
                break;
            case DOWN_RIGHT:
                y += speed;
                x += speed;
                break;
        }
    }

    private void determineDirection() {//八方向判斷設定
        if (dirs[0] && dirs[2] && !dirs[1] && !dirs[3]) direction = Direction.UP_LEFT;
        else if (dirs[0] && dirs[3] && !dirs[2] && !dirs[1]) direction = Direction.UP_RIGHT;
        else if (dirs[1] && dirs[2] && !dirs[0] && !dirs[3]) direction = Direction.DOWN_LEFT;
        else if (dirs[1] && dirs[3] && !dirs[0] && !dirs[2]) direction = Direction.DOWN_RIGHT;
        else if (dirs[0] && !dirs[3] && !dirs[2] && !dirs[1]) direction = Direction.UP;
        else if (dirs[1] && !dirs[3] && !dirs[2] && !dirs[0]) direction = Direction.DOWN;
        else if (dirs[2] && !dirs[3] && !dirs[1] && !dirs[0]) direction = Direction.LEFT;
        else if (dirs[3] && !dirs[2] && !dirs[1] && !dirs[0]) direction = Direction.RIGHT;
    }

    public void draw(Graphics g) {//整合輸出坦克
        if (isStop()) {
            determineDirection();
            move();
            collision();
        }
        g.drawImage(image[direction.ordinal()], x, y, null);//direction.ordinal()取得方向
    }

    public boolean isStop() {//判斷未按下按鍵則停止坦克
        for (boolean dir : dirs) {
            if (dir) {
                return true;
            }
        }
        return false;
    }

    public void collision() {
        //邊界偵測
        if (x < 0) {
            x = 0;
        } else if (x > TankWar.gameClient.getScreenWidth() - width) {
            x = TankWar.gameClient.getScreenWidth() - width;
        }
        if (y < 0) {
            y = 0;
        } else if (y > TankWar.gameClient.getScreenHeight() - height) {
            y = TankWar.gameClient.getScreenHeight() - height;
        }

        //圍牆、敵方坦克碰撞偵測
        for (GameObject object : TankWar.gameClient.getGameObjects()) {
            if (object != this && object.getRectangle().intersects(this.getRectangle())) {
                x = oldX;
                y = oldY;
                return;
            }
        }
    }
    public void fire(){//發設子彈
        TankWar.gameClient.addGameObject(
                new Bullet(x,y,direction,false,GameClient.bulletImage));
    }
    public void superFire(){//八方向發射
        for(Direction direction:Direction.values()){
            Bullet bullet=new Bullet(x,y,direction,false,GameClient.bulletImage);
            bullet.setSpeed(15);
            TankWar.gameClient.addGameObject(bullet);
        }
    };
}

