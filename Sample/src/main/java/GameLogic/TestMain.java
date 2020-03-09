package GameLogic;
import UI.*;

import java.util.concurrent.TimeUnit;

public class TestMain {
    private static boolean isGameOver = false;
    private static int delay = 200;
    private static int step = 40;
    private static int currentLevel = 1;
    public static void main (String[] args) throws InterruptedException {

        Input input = new Input(step);
        DisplayOutput output = new DisplayOutput();

        Board board = new Board(currentLevel);

        int[] direction;
        while(!isGameOver) {
            TimeUnit.MILLISECONDS.sleep(delay);
            output.renderPlayer(board.getPlayerPos());
            output.renderEnemy(board.getEnemyPos());
            direction = input.getDirection(output.getFrame());
            input.resetDirection();
            board.moveEnemy(direction);
            board.movePlayer(direction);
            //have a function later that reads the position list and output it

            /*
            //Timer and score testing:
            Timer timer = new Timer();
            System.out.println(timer.getMilliseconds());
            TimeUnit.MILLISECONDS.sleep(delay);
            System.out.println(timer.getMilliseconds());
            */

        }


    }
}
