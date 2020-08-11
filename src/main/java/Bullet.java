import java.awt.*;

public class Bullet extends MoveObject {
    public Bullet(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, direction, enemy, image);
        speed = 10;
    }

    @Override
    public void draw(Graphics g) {
        if (!alive) {
            return;
        }
        move();
        collision();
        g.drawImage(image[direction.ordinal()], x, y, null);
    }

    @Override
    public void collision() {
        if(collisionBound()){//邊界偵測
            alive=false;
            return;
        }
        for (GameObject object : TankWar.gameClient.getGameObjects()) {
            if (object == this) {
                continue;
            }
            if (object instanceof Tank) {//偵測坦克碰撞
                if (((Tank) object).enemy == enemy) {
                    continue;
                }
            }
            if (object.getRectangle().intersects(this.getRectangle())) {//碰撞後消失
                alive = false;
                if (object instanceof Tank) {//敵方坦克消失
                    object.alive=false;
                }
                return;
            }
        }
    }
}