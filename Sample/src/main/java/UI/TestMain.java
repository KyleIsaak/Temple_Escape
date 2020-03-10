package UI;
import GameLogic.Board;

import javax.swing.*;


public class TestMain  {
    private static boolean isGameOver = false;
    private static int delay = 200;
    private static int step = 20;
    private static int currentLevel = 1;
    private static Board board;


    public static void main (String[] args) {
        board = new Board(currentLevel);
        JFrame test = new Interface(step, board);

    }

}
