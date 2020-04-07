package gameLogic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

class TimerTest {

    @Test
    public void getSeconds() {
        long delay = 1;
        Timer timer = new Timer();
        long startTime = System.currentTimeMillis();

        //TimeUnit.SECONDS.sleep(delay);
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        long testTime = timer.getSeconds();
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        assertEquals(totalTime / 1000, testTime);
    }

    @Test
    public void pauseTimer() {
        long delay = 1;
        Timer timer = new Timer();

        timer.pauseTimer();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

        timer.resumeTimer();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }

        assertEquals(2, timer.getSeconds());
    }

}