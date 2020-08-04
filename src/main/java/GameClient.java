import sun.security.mscapi.CPublicKey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameClient extends JComponent {
    private int screenWidth;
    private int screenHeight;
    private Tank playerTank;//玩家坦克
    private List<Tank> enemyTanks=new ArrayList<>();//敵方坦克
    private List<Wall> walls=new ArrayList<>();//圍牆
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

    public void init() {//遊戲初始屬性
        playerTank = new Tank(getCenterPosX(47), 100, Direction.DOWN);//玩家屬性
        for(int i=0;i<3;i++){
            for(int j=0;j<4;j++){
                enemyTanks.add(new Tank(360+j*80,500+i*80,Direction.UP,true));//敵方坦克
            }
        }
        Wall[] walls={
                new Wall(250,150,true,15),
                new Wall(150,200,false,15),
                new Wall(800,200,false,15),
        };
        this.walls.addAll(Arrays.asList(walls));//圍牆
    }

    @Override
    protected void paintComponent(Graphics g) {//顯示坦克
        playerTank.draw(g);
        for(Tank tank:enemyTanks){
            tank.draw(g);
        }
        for(Wall wall:walls){
            wall.draw(g);
        }
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