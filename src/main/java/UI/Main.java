package UI;
import gameLogic.Board;
import gameLogic.Timer;

//<<<<<<< HEAD
//import javax.swing.*;
//import javax.swing.border.Border;
//import java.awt.*;
//
//=======
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

public class Main  {
    private static boolean isGameOver = false;
    private static int delay = 200;
    private static int step = 32;
    private static int currentLevel = 1;
    private static Board board;


    /**
     * main program
     * @param args
     */
    public static void main (String[] args){
        new newFont();
        new Interface(step);

        //while(!isGameOver)
        //{
        //System.out.println("Time elapsed:" + timer.displayMinutes() + ":" + timer.displaySeconds());
        //}
    }
}
