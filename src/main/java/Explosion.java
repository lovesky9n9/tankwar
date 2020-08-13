import java.awt.*;

public class Explosion extends GameObject {
    public Explosion(int x, int y, Image[] image) {
        super(x, y, image);
        new Thread(() -> {
            while (alive) {
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (++frame >= image.length) {
                    alive = false;
                }
            }
        }
        ).start();
    }

    @Override
    void draw(Graphics g) {
        if(alive){
            g.drawImage(image[frame],x,y,null);
        }
    }
}
