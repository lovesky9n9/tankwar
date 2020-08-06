import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TankWar {
    public static GameClient gameClient;
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        gameClient = new GameClient();
        frame.add(gameClient);//可設定視窗大小，為空則為預設大小
        frame.setTitle("TankWar");//視窗title名稱
        frame.pack();//畫面展開
        frame.setLocationRelativeTo(null);//置中顯示
        frame.setVisible(true);//顯示視窗
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//視窗關閉程式停止

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {//回傳按下按鍵值
                gameClient.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {//回傳放開按鍵值
                gameClient.keyReleased(e);
            }
        });
    }
}

