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
    public boolean collision() {
        if (collisionBound()) {//邊界偵測
            alive = false;
            return true;
        }
        for (GameObject object : TankWar.gameClient.getGameObjects()) {
            //子彈不互相抵銷(火花不偵測)
            if (object == this || object instanceof Bullet || object instanceof Explosion) {
                continue;
            }
            //偵測坦克碰撞
            if (object instanceof Tank) {
                if (((Tank) object).enemy == enemy) {
                    continue;
                }
            }
            //碰撞後消失
            if (object.getRectangle().intersects(this.getRectangle())) {
                alive = false;
                //敵方坦克消失
                if (object instanceof Tank) {
                    object.alive = false;
                }
                //產生爆炸火花
                TankWar.gameClient.addGameObject(new Explosion(x, y, GameClient.explosionImage));
                return true;
            }
        }
        return false;
    }
}