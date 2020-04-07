package gameLogic;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * Level Generator is a class that uses Depth-first search algorithm to auto generate a maze.
 * To make the game playable, we randomly deleted walls to make more room for the player.
 * this class provides a getter for the generated maze.
 */
public class LevelGenerator {
    /**
     * Store the horizontal bound.
     */
    private int x;
    /**
     * Store the vertical bound.
     */
    private int y;
    /**
     * Store the board info.
     */
    private int[][] board;
    /**
     * Store the unvisit X info.
     */
    private Stack<Integer> unvisitX;
    /**
     * Store the unvisit Y info.
     */
    private Stack<Integer> unvisitY;
    /**
     * Store the neighbor X info.
     */
    private ArrayList<Integer> neighborX;
    /**
     * Store the neighbor Y info.
     */
    private ArrayList<Integer> neighborY;
    /**
     * state wall = 0.
     */
    private final static int WALL = 0;
    /**
     * state path = 1.
     */
    private final static int PATH = 1;

    /**
     * main program of generate the maze.
     *
     * @param args main program require.
     */
    public static void main(String[] args) {
        LevelGenerator test = new LevelGenerator(27, 27);
        test.setLevel(LevelGenerator.Difficulty.EASY);
        int[][] map = test.getBoard();

        for (int y = 0; y < 27; y++) {
            for (int x = 0; x < 27; x++) {
                System.out.print(map[x][y]);
            }
            System.out.println();
        }
    }


    /**
     * Non Default Constructor.
     *
     * @param x An integer that specify the horizontal length of a board.
     * @param y An integer that specify the vertical length of a board.
     */
    public LevelGenerator(int x, int y) {
        this.x = x;
        this.y = y;
        this.board = new int[x][y];
        this.unvisitX = new Stack<Integer>();
        this.unvisitY = new Stack<Integer>();
        this.neighborX = new ArrayList<Integer>();
        this.neighborY = new ArrayList<Integer>();
        generate();
    }

    /**
     * Choose the difficult.
     */
    public enum Difficulty {
        EASY,
        MEDIUM,
        HARD
    }

    /**
     * Set the Difficulty of the game.
     * The harder the game, the more walls the game have.
     *
     * @param choice The chosen Difficulty (EASY, MEDIUM, HARD).
     */
    public void setLevel(Difficulty choice) {
        int easy = 3;
        int medium = 4;
        int hard = 5;
        switch (choice) {
            case EASY:
                for (int i = 0; i < (x * y / easy); i++) {
                    randomRemoveWall();
                }
                break;
            case MEDIUM:
                for (int i = 0; i < (x * y / medium); i++) {
                    randomRemoveWall();
                }
                break;
            case HARD:
                for (int i = 0; i < (x * y / hard); i++) {
                    randomRemoveWall();
                }
                break;

            default:
                break;

        }
    }

    /**
     * Generate the board of the game using Depth First Search.
     */
    private void generate() {
        board[1][1] = PATH;                //player init

        pushStacks(1, 1);
        int currentX = 1;
        int currentY = 1;
        int next;
        while (!unvisitX.empty()) {
            findNeighbor(currentX, currentY);
            while (hasNeighbor()) {
                next = randomNeighbor();
                makePath(currentX, currentY, next);
                if (!isComplete()) {
                    pushStacks(currentX, currentY);
                }

                currentX = neighborX.get(next);
                currentY = neighborY.get(next);
                findNeighbor(currentX, currentY);
            }
            currentX = unvisitX.pop();
            currentY = unvisitY.pop();
        }
    }

    /**
     * Check whether that position consists a wall
     *
     * @param x An integer for the x position
     * @param y An integer for the y position
     * @return true if that position contains a wall and vise versa.
     */
    public boolean isWall(int x, int y) {
        return board[x][y] == WALL;
    }

    /**
     * Get the board of this class
     *
     * @return the board of this class
     */
    public int[][] getBoard() {
        return board.clone();
    }

    /**
     * Helper Function for generate() function.
     * Push x, y position to unvisited points.
     *
     * @param x An integer for the x position
     * @param y An integer for the y position
     */
    private void pushStacks(int x, int y) {
        unvisitX.push(x);
        unvisitY.push(y);
    }

    /**
     * Helper function for generate() function
     *
     * @param x    An integer for the x position
     * @param y    An integer for the y position
     * @param next An integer of the next position
     */
    private void makePath(int x, int y, int next) {
        int nextX = neighborX.get(next);
        int nextY = neighborY.get(next);
        int wallX = x - (x - nextX) / 2;
        int wallY = y - (y - nextY) / 2;
        board[wallX][wallY] = PATH;
        board[nextX][nextY] = PATH;
    }

    /**
     * Helper Function for generate() function.
     *
     * @param x An integer for the x position
     * @param y An integer for the y position
     */
    private void findNeighbor(int x, int y) {
        neighborX.clear();
        neighborY.clear();

        if (isInBound(x - 2, y) && isWall(x - 2, y)) {   // left
            neighborX.add(x - 2);
            neighborY.add(y);
        }

        if (isInBound(x + 2, y) && isWall(x + 2, y)) {   //right
            neighborX.add(x + 2);
            neighborY.add(y);
        }

        if (isInBound(x, y + 2) && isWall(x, y + 2)) {   //top
            neighborX.add(x);
            neighborY.add(y + 2);
        }

        if (isInBound(x, y - 2) && isWall(x, y - 2)) {   //down
            neighborX.add(x);
            neighborY.add(y - 2);
        }
    }

    /**
     * Helper Function for generate() function
     *
     * @return true if there is only one elements in neighborX.
     */
    private boolean isComplete() {
        return (neighborX.size() == 1);
    }

    /**
     * Helper function for generate() function
     *
     * @return true if neighborX is not empty and vise versa.
     */
    private boolean hasNeighbor() {
        return !neighborX.isEmpty();
    }

    /**
     * Helper function for generate() function
     *
     * @return randomize Neighbor
     */
    private int randomNeighbor() {
        Random random = new Random();
        return random.nextInt(neighborX.size());
    }

    /**
     * Helper Function for generate() function
     *
     * @param x An integer for the x position
     * @param y An integer for the y position
     * @return true if the position is in bound and vise versa
     */
    private boolean isInBound(int x, int y) {
        return x >= 1 && x <= this.x - 2 && y >= 1 && y <= this.y - 2;
    }

    /**
     * Remove walls in random location of the map.
     */
    private void randomRemoveWall() {
        Random random = new Random();
        boolean isRemoved = false;
        boolean found = false;
        int ranX = 0;
        int ranY = 0;
        while (!isRemoved) {

            while (!found) {
                ranX = random.nextInt(x);
                ranY = random.nextInt(y);
                if (board[ranX][ranY] == WALL && isInBound(ranX, ranY)) {
                    found = true;
                }
            }
            isRemoved = true;
            board[ranX][ranY] = PATH;
        }
    }
}
