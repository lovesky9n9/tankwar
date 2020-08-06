import java.awt.*;

public class Wall extends GameObject{
    private boolean horizontal;//判斷方向(水平或垂直)
    private int bricks;//圍牆數量

    Wall(int x, int y, boolean horizontal, int bricks,Image[] image) {
        super(x,y,image);
        this.horizontal = horizontal;
        this.bricks = bricks;
    }

    @Override
    public Rectangle getRectangle() {
        return horizontal ? new Rectangle(x,y,bricks*width,height):
                new Rectangle(x,y,width,bricks*height);
    }

    public void draw(Graphics g) {
        if (horizontal) {
            for (int i = 0; i < bricks; i++) {
                g.drawImage(image[0], x + i * width, y, null);
            }
        } else {
            for (int i = 0; i < bricks; i++) {
                g.drawImage(image[0], x, y + i * height, null);
            }
        }
    }
}
