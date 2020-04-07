package gameLogic;

/**
 * Manages the game timer.
 * To get different time formats (minutes, seconds,
 * or milliseconds elapsed) use the corresponding getter method.
 */
public class Timer {

    /**
     * store the start time.
     */
    private long startTime;
    /**
     * store the elasped time.
     */
    private long elapsedTime;
    /**
     * store the paused time.
     */
    private long pausedTime;
    /**
     * store the start paused time.
     */
    private long startPausedTime;
    /**
     * store the end paused time.
     */
    private long endPausedTime;


    /**
     * Default Constructor.
     */
    public Timer() {
        this.startTime = System.currentTimeMillis();
        this.pausedTime = 0;
    }

    /**
     * Non-Default Constructor.
     *
     * @param oldTimer A Timer of the previous level.
     */
    public Timer(Timer oldTimer) {
        this.startTime = oldTimer.getStartTime();
        this.pausedTime = oldTimer.getPausedTime();
    }

    /**
     * Get the current game time.
     * Calculate time elapsed in milliseconds.
     *
     * @return the current game time.
     */
    private long getTime() {
        this.elapsedTime = System.currentTimeMillis() - this.startTime;
        this.elapsedTime -= pausedTime;
        return elapsedTime;
    }

    /**
     * Get the Start Time of this Timer.
     *
     * @return the Start Time of this Timer.
     */
    public long getStartTime() {
        return this.startTime;
    }

    /**
     * Get the Pause Time of this Timer.
     *
     * @return the Pause Time of this Timer.
     */
    public long getPausedTime() {
        return this.pausedTime;
    }

    /**
     * Get the current time in seconds.
     *
     * @return the current time in seconds.
     */
    public long getSeconds() {
        int div = 1000;
        this.elapsedTime = getTime() / div;
        return elapsedTime;
    }

    /**
     * Display current time in seconds.
     *
     * @return current time in seconds.
     */
    public long displaySeconds() {
        int div = 60;
        this.elapsedTime = getSeconds() % div;
        return elapsedTime;
    }

    /**
     * Pause this timer.
     */
    public void pauseTimer() {
        this.startPausedTime = System.currentTimeMillis();
    }

    /**
     * Resume this timer.
     */
    public void resumeTimer() {
        this.endPausedTime = System.currentTimeMillis();
        this.pausedTime = this.endPausedTime - this.startPausedTime;
    }
}
