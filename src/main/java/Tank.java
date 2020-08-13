import java.awt.*;

public class Tank extends MoveObject {
    protected boolean[] dirs = new boolean[4];//上下左右四個方向

    public Tank(int x, int y, Direction direction, Image[] image) {//玩家坦克
        this(x, y, direction, false, image);
    }

    public Tank(int x, int y, Direction direction, boolean enemy, Image[] image) {//敵方坦克
        super(x, y, direction, enemy, image);
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.speed = 15;
        this.enemy = enemy;
    }

    public boolean[] getDirs() {
        return dirs;
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

    public boolean collision() {//碰撞偵測
        //邊界偵測
        if (collisionBound()) {
            return true;
        }
        //圍牆、敵方坦克碰撞偵測
        for (GameObject object : TankWar.gameClient.getGameObjects()) {
            if (object != this && object.getRectangle().intersects(this.getRectangle())) {
                x = oldX;
                y = oldY;
                return true;
            }
        }
        return false;
    }

    public void fire() {//發設子彈
        TankWar.gameClient.addGameObject(
                new Bullet(x + width / 2 - GameClient.bulletImage[0].getWidth(null) / 2,
                        y + height / 2 - GameClient.bulletImage[0].getHeight(null) / 2,
                        direction, enemy, GameClient.bulletImage));
//        if(!enemy){
//            Tools.playAudio("shoot.wav");
//        }
    }
}

