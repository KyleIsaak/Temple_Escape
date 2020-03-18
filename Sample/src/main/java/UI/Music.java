package UI;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Music {
    private long clipPosition;
    private boolean isPause;
    private Clip clip;
    void playSound(){
        try{
            String filepath = "src/pic/BackgroundMusic.wav";
            File musicFilePath = new File(filepath);
            if (musicFilePath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFilePath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                isPause = false;
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
    public boolean isPause(){ return isPause; }
    public void pauseMusic(){
        clipPosition = clip.getMicrosecondPosition();
        clip.stop();
        isPause = true;
    }
    public void unPause(){
        clip.setMicrosecondPosition(clipPosition);
        clip.start();
        isPause = false;
    }
}
