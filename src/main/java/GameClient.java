import sun.security.mscapi.CPublicKey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GameClient extends JComponent {
    private int screenWidth;
    private int screenHeight;
    private Tank playerTank;
    private boolean stop;

    GameClient() {//預設視窗大小
        this(1024, 768);
    }

    public GameClient(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        init();
        new Thread(() -> {//設定執行緒持續更新畫面
            while (!stop) {
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void init() {//設定坦克初始屬性
        playerTank = new Tank(getCenterPosX(47), getCenterPosY(47), Direction.DOWN);
    }

    @Override
    protected void paintComponent(Graphics g) {//輸出玩家坦克
        playerTank.draw(g);
    }

    public int getCenterPosX(int width) {//(螢幕大小-圖檔大小)/2 X軸置中運算
        return (screenWidth - width) / 2;
    }

    public int getCenterPosY(int height) {//(螢幕大小-圖檔大小)/2 Y軸置中運算
        return (screenHeight - height) / 2;
    }

    public void keyPressed(KeyEvent e) {//偵測按鍵按下後移動
        boolean[] dirs = playerTank.getDirs();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                dirs[0] = true;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1] = true;
                break;
            case KeyEvent.VK_LEFT:
                dirs[2] = true;
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3] = true;
                break;
            default:
        }
    }

    public void keyReleased(KeyEvent e) {//偵測按鍵放開後停止
        boolean[] dirs = playerTank.getDirs();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                dirs[0] = false;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1] = false;
                break;
            case KeyEvent.VK_LEFT:
                dirs[2] = false;
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3] = false;
                break;
            default:
        }
    }
}