package UI;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;

public class Music {
    void playSound(){
        try{
            String filepath = "src/pic/BackgroundMusic.wav";
            File musicFilePath = new File(filepath);
            if (musicFilePath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFilePath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
            else{
                System.out.println("Error: Can't find file");
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
