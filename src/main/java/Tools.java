import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Tools {
    public static Image getImage(String fileName){
        return new ImageIcon("assets/images/"+fileName+".png").getImage();
    }
//    public static void playAudio(String fileName){
//        Media sound=new Media(new File("assets/audios/"+fileName).toURI().toString());
//        MediaPlayer mediaPlayer=new MediaPlayer(sound);
//        mediaPlayer.play();
//    }
}
