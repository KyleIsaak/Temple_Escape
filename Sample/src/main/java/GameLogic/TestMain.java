package GameLogic;
import UI.*;

import java.util.concurrent.TimeUnit;

public class TestMain {
    private static boolean isGameOver = false;
    private static int delay = 200;
    private static int step = 20;
    private static int currentLevel = 1;
    public static void main (String[] args) throws InterruptedException {

        Input input = new Input(step);
        DisplayOutput output = new DisplayOutput(step);

        Board board = new Board(currentLevel);
        int map[][] = board.getBoard(LevelGenerator.Difficulty.EASY);

        int[] direction;
        while(!isGameOver) {
            output.renderBoard(map);
            //TimeUnit.MILLISECONDS.sleep(delay);
            output.renderPlayer(board.getPlayerPos());
//            output.renderEnemy(board.getEnemyPos());
            direction = input.getDirection(output.getFrame());
            input.resetDirection();
//            board.moveEnemy(direction);
            board.movePlayer(direction);
            //have a function later that reads the position list and output it

        }


    }
}
