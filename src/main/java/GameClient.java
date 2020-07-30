import javax.swing.*;
import java.awt.*;

public class GameClient extends JComponent {
    private int screenWidth;
    private int screenHeight;

    GameClient() {//預設視窗大小
        this(1024, 768);
    }

    public GameClient(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    }

    @Override
    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        g.drawImage(new ImageIcon("assets/images/itankD.png").getImage(),
                488, 360, null);
    }
}