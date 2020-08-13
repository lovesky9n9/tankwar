import java.awt.*;

public abstract class MoveObject extends GameObject {
    protected int speed;
    protected Direction direction;
    protected boolean enemy;

    public MoveObject(int x, int y, Direction direction, boolean enemy, Image[] image) {//敵方坦克
        super(x, y, image);
        this.x = x;
        this.y = y;
        this.direction = direction;
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

    public void draw(Graphics g) {//整合輸出坦克
        move();
        collision();
        g.drawImage(image[direction.ordinal()], x, y, null);//direction.ordinal()取得方向
    }

    public boolean collisionBound() {
        boolean collision = false;
        if (x < 0) {
            x = 0;
            collision = true;
        } else if (x > TankWar.gameClient.getScreenWidth() - width) {
            x = TankWar.gameClient.getScreenWidth() - width;
            collision = true;
        }
        if (y < 0) {
            y = 0;
            collision = true;
        } else if (y > TankWar.gameClient.getScreenHeight() - height) {
            y = TankWar.gameClient.getScreenHeight() - height;
            collision = true;
        }
        return collision;
    }

    public abstract boolean collision();
}