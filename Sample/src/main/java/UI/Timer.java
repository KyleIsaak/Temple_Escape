package UI;

public class Timer {
    private long startTime;

    /*
    To use the timer, just create a timer in the main() function for the game using the default constructor.
    To get different time formats (minutes, seconds, or milliseconds elapsed) use the corresponding getter method.
     */

    //Constructor
    public Timer(){
        this.startTime = System.currentTimeMillis();
    }


    //Calculate time elapsed in milliseconds
    private long getTime(){
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;
        return elapsedTime;
    }


    //Get time in various formats
    public long getMilliseconds(){
        return getTime();
    }

    public long getSeconds(){
        long seconds = getTime() / 1000;
        return seconds;
    }

    public long getMinutes(){
        long minutes = getTime() / 60000;
        return minutes;
    }


    // call this function to reset the timer
    public void resetTimer(){
        this.startTime = System.currentTimeMillis();
    }

}
