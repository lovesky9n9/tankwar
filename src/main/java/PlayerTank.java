import java.awt.*;
import java.util.Random;

public class PlayerTank extends Tank implements SuperFire {
    public PlayerTank(int x, int y, Direction direction, Image[] image) {
        super(x, y, direction, image);
    }

    @Override
    public void superFire() {
        String audioFile=new Random().nextBoolean() ? "supershoot.aiff":"supershoot.wav";
//        Tools.playAudio(audioFile);
        for (Direction direction : Direction.values()) {
            Bullet bullet = new Bullet(x + width / 2 - GameClient.bulletImage[0].getWidth(null) / 2,
                    y + height / 2 - GameClient.bulletImage[0].getHeight(null) / 2,
                    direction, enemy, GameClient.bulletImage);
            bullet.setSpeed(15);
            TankWar.gameClient.addGameObject(bullet);
        }
    }
}
