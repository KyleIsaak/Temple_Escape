package gameLogic;

/**
 * Manages the game timer
 * To get different time formats (minutes, seconds, or milliseconds elapsed) use the corresponding getter method.
 */
public class Timer {
    private long startTime;
    private long elapsedTime;
    private long pausedTime;
    private long startPausedTime;
    private long endPausedTime;


    /**
     * Default Constructor.
     */
    public Timer(){
        this.startTime = System.currentTimeMillis();
        this.pausedTime = 0;
    }

    /**
     * Non-Default Constructor.
     * @param oldTimer A Timer of the previous level.
     */
    public Timer(Timer oldTimer){
        this.startTime = oldTimer.getStartTime();
        this.pausedTime = oldTimer.getPausedTime();
    }

    /**
     * Get the current game time.
     * Calculate time elapsed in milliseconds.
     * @return the current game time.
     */
    private long getTime(){
        this.elapsedTime = System.currentTimeMillis() - this.startTime;
        this.elapsedTime -= pausedTime;
        return elapsedTime;
    }

    /**
     * Get the Start Time of this Timer.
     * @return the Start Time of this Timer.
     */
    public long getStartTime(){ return this.startTime; }

    /**
     * Get the Pause Time of this Timer.
     * @return  the Pause Time of this Timer.
     */
    public long getPausedTime(){ return  this.pausedTime; }

    /**
     * Get the current time in seconds.
     * @return the current time in seconds.
     */
    public long getSeconds(){
        this.elapsedTime = getTime() / 1000;
        return elapsedTime;
    }

    /**
     * Display current time in seconds.
     * @return current time in seconds.
     */
    public long displaySeconds(){
        this.elapsedTime = getSeconds() %60;
        return elapsedTime;
    }

    /**
     * Pause this timer
     */
    public void pauseTimer(){
        this.startPausedTime = System.currentTimeMillis();
    }

    /**
     * Resume this timer
     */
    public void resumeTimer(){
        this.endPausedTime = System.currentTimeMillis();
        this.pausedTime = this.endPausedTime - this.startPausedTime;
    }
}
