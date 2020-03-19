package GameLogic;

public class Timer {
    private long startTime;
    private long elapsedTime;
    private long pausedTime;
    private long startPausedTime;
    private long endPausedTime;

    /*
    To use the timer, just create a timer in the main() function for the game using the default constructor.
    To get different time formats (minutes, seconds, or milliseconds elapsed) use the corresponding getter method.
    */

    //Constructor
    public Timer(){
        this.startTime = System.currentTimeMillis();
        this.pausedTime = 0;
    }

    public Timer(Timer oldTimer){
        this.startTime = oldTimer.startTime;
        this.pausedTime = oldTimer.pausedTime;
    }


    //Calculate time elapsed in milliseconds
    private long getTime(){
        this.elapsedTime = System.currentTimeMillis() - this.startTime;
        this.elapsedTime -= pausedTime;
        return elapsedTime;
    }


    //Get time in various formats
    public long getMilliseconds(){
        return getTime();
    }

    public long getSeconds(){
        this.elapsedTime = getTime() / 1000;
        return elapsedTime;
    }

    public long displaySeconds(){
        this.elapsedTime = getSeconds() %60;
        return elapsedTime;
    }

    public long displayMinutes() {
        this.elapsedTime = displaySeconds() / 60;
        return elapsedTime;
    }

    public void pauseTimer(){
        this.startPausedTime = System.currentTimeMillis();
    }

    public void resumeTimer(){
        this.endPausedTime = System.currentTimeMillis();
        this.pausedTime = this.endPausedTime - this.startPausedTime;
    }

    // call this function to reset the timer
    public void resetTimer(){
        this.startTime = System.currentTimeMillis();
    }

}
