package UI;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class MusicTest {
    // Test whether the Music .wav file can be loaded
    @Test
    void musicFileTest() {
        String filepath = "src/main/resources/BackgroundMusic.wav";
        File musicFilePath = new File(filepath);
        boolean musicFileAvailable = false;
        if (musicFilePath.exists()) {
            musicFileAvailable = true;
        }
        assertTrue(musicFileAvailable);
    }
}
