import java.awt.*;
import java.util.Random;

public class EnemyTank extends Tank {
    public EnemyTank(int x, int y, Direction direction, boolean enemy, Image[] image) {
        super(x, y, direction, enemy, image);
    }

    public void ai() {
        Random random = new Random();
        //移動偵測
        if (random.nextInt(20) == 1) {
            dirs = new boolean[4];
            //停止
            if (random.nextBoolean() == true) {
                return;
            }
            getNewDirection();
        }
        //開火
        if(random.nextInt(50)==1){
            fire();
        }
    }

    private void getNewDirection() {
        Random random = new Random();
        int dir=random.nextInt(Direction.values().length);
        if(dir<=Direction.RIGHT.ordinal()){
            dirs[dir]=true;
        }else{//0:up 1:down 2:left 3:right
            if(dir==Direction.UP_LEFT.ordinal()){
                dirs[0]=true;
                dirs[2]=true;
            }else if(dir==Direction.UP_RIGHT.ordinal()) {
                dirs[0] = true;
                dirs[3] = true;
            }else if(dir==Direction.DOWN_LEFT.ordinal()) {
                dirs[1] = true;
                dirs[2] = true;
            }else if(dir==Direction.DOWN_RIGHT.ordinal()) {
                dirs[1] = true;
                dirs[3] = true;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        ai();
        super.draw(g);
    }

    @Override
    public boolean collision() {
        if(super.collision()){
            getNewDirection();
            return true;
        }return false;
    }
}
