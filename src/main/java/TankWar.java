import javax.swing.*;

public class TankWar {
    public static void main(String[] args) {
        JFrame frame=new JFrame();
        GameClient gameClient=new GameClient();
        frame.add(gameClient);//可設定視窗大小，為空則為預設大小
        frame.setTitle("TankWar");//視窗title名稱
        frame.setVisible(true);//顯示視窗
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//視窗關閉程式停止
        frame.pack();//畫面展開

        gameClient.repaint();
    }
}