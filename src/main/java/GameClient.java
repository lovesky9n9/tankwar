import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameClient extends JComponent {
    private int screenWidth;
    private int screenHeight;
    private Tank playerTank;//玩家坦克
    private CopyOnWriteArrayList<GameObject> objects = new CopyOnWriteArrayList<>();//統一GameObject控管

    GameClient() {//預設視窗大小
        this(1024, 768);
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public List<GameObject> getGameObjects() {
        return objects;
    }

    public void addGameObject(GameObject gameObject) {//增加物件
        objects.add(gameObject);
    }

    public GameClient(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        init();
        new Thread(() -> {//設定執行緒持續更新畫面
            while (true) {
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static Image[] bulletImage = new Image[8];//子彈圖片
    public static Image[] explosionImage = new Image[11];//爆炸圖片

    public void init() {//遊戲初始屬性
        Image[] brickImage = {Tools.getImage("brick")};
        Image[] iTankImage = new Image[8];
        Image[] eTankImage = new Image[8];//敵方坦克圖片
        String[] sub = {"U", "D", "L", "R", "LU", "RU", "LD", "RD"};
        for (int i = 0; i < iTankImage.length; i++) {
            iTankImage[i] = Tools.getImage("itank" + sub[i]);
            eTankImage[i] = Tools.getImage("etank" + sub[i]);
            bulletImage[i] = Tools.getImage("missile" + sub[i]);
        }
        for(int i=0;i<explosionImage.length;i++){
            explosionImage[i]=Tools.getImage(i +"");
        }
        //玩家坦克
        playerTank = new Tank(getCenterPosX(47), 100, Direction.DOWN, iTankImage);
        objects.add(playerTank);
        //敵方坦克enemyTanks
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                objects.add(new EnemyTank(360 + j * 80, 500 + i * 80, Direction.UP, true, eTankImage));
            }
        }
        //圍牆walls
        objects.add(new Wall(250, 150, true, 15, brickImage));
        objects.add(new Wall(100, 200, false, 15, brickImage));
        objects.add(new Wall(850, 200, false, 15, brickImage));
    }

    @Override
    protected void paintComponent(Graphics g) {//顯示坦克
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getScreenWidth(), getScreenHeight());
        for (GameObject object : objects) {
            object.draw(g);
        }
        for (GameObject object : objects) {
            //移除死亡坦克
            if (!object.alive) {
                objects.remove(object);
            }
        }
        //使用迭代器進行移除
//        Iterator<GameObject> iterator = objects.iterator();
//        while (iterator.hasNext()) {
//            if (!(iterator.next()).alive) {
//                iterator.remove();
//            }
//        }
//        System.out.println(objects.size());
    }

    public int getCenterPosX(int width) {//(螢幕大小-圖檔大小)/2 X軸置中運算
        return (screenWidth - width) / 2;
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
            case KeyEvent.VK_CONTROL:
                playerTank.fire();
                break;
            case KeyEvent.VK_A:
                playerTank.superFire();
                break;
//            case KeyEvent.VK_F2:
//                for(GameObject object:objects){
//                    if(object instanceof EnemyTank){
//                        objects.remove(object);
//                    }
//                }
//                break;
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

//    public void checkGameStatus(){
//        boolean gameWin=true;
//        for(GameObject object:objects){
//            if(object instanceof EnemyTank){
//                gameWin=false;
//            }
//        }
//        if(gameWin){
//            for(int i=0;i<3;i++){
//                for(int j=0;j<4;j++){
//                    addGameObject((new Tank(360 + j * 80, 500 + i * 80, Direction.UP, eTankImage)));
//                }
//            }
//        }
//    }
}