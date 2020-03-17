package GameLogic;

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
        return System.currentTimeMillis() - this.startTime;
    }


    //Get time in various formats
    public long getMilliseconds(){
        return getTime();
    }

    public long getSeconds(){
        return getTime() / 1000;
    }

    public long displaySeconds(){
        return getSeconds() %60;
    }

    public long displayMinutes(){
        return getSeconds() / 60;
    }


    // call this function to reset the timer
    public void resetTimer(){
        this.startTime = System.currentTimeMillis();
    }

}
