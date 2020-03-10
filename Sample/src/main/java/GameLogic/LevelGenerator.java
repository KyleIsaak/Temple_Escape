package GameLogic;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class LevelGenerator {
    private int x;
    private int y;
    private int[][] board;
    private Stack<Integer> unvisitX;
    private Stack<Integer> unvisitY;
    private ArrayList<Integer> neighborX;
    private ArrayList<Integer> neighborY;
    private final int WALL = 0;
    private final int PATH = 1;

    public static void main(String[] args){
        LevelGenerator test = new LevelGenerator(40, 40);
        test.setLevel(LevelGenerator.Difficulty.EASY);
        int[][] map = test.getBoard();
        for (int y = 0; y < map[0].length; y++) {
            for (int x = 0; x < map.length; x++) {
                System.out.print(map[x][y]);
            }
            System.out.println();
        }

        System.out.println();
        System.out.println();
        System.out.println();
        for (int y = 0; y < map[0].length; y++) {
            for (int x = 0; x < map.length; x++) {
                System.out.print(map[x][y]);
            }
            System.out.println();
        }
    }

    public LevelGenerator(int x, int y){
        this.x = x;
        this.y = y;
        this.board = new int[x][y];
        this.unvisitX = new Stack<Integer>();
        this.unvisitY = new Stack<Integer>();
        this.neighborX = new ArrayList<Integer>();
        this.neighborY = new ArrayList<Integer>();
        generate();
    }

     public enum Difficulty{
        EASY,
        MEDIUM,
        HARD
    }
    public void setLevel(Difficulty choice){
        switch(choice){
            case EASY:
                for(int i = 0; i < (x * y / 3); i++){
                    randomRemoveWall();
                    break;
                }
            case MEDIUM:
                for(int i = 0; i < (x * y / 4); i ++){
                    randomRemoveWall();
                    break;
                }
            case HARD:
                for(int i = 0; i < (x * y / 5); i ++){
                    randomRemoveWall();
                    break;
                }
            default:
                break;

        }
    }

    public int[][] getBoard(){
        return this.board;
    }
    private void generate(){
        board[1][y/2] = PATH;
        unvisited(1, y/2);
        int currentX = 1;
        int currentY = y/2;
        int next;
        while (!unvisitX.empty()) {
            findNeighbor(currentX, currentY);
            while (hasNeighbor()) {
                next = randomNeighbor();
                makePath(currentX, currentY, next);
                if (!isComplete()){
                    unvisited(currentX, currentY);
                }

                currentX = neighborX.get(next);
                currentY = neighborY.get(next);
                findNeighbor(currentX, currentY);
            }
            currentX = unvisitX.pop();
            currentY = unvisitY.pop();
        }
    }

    public boolean isWall(int x, int y){ return board[x][y] == WALL;}

    private void unvisited(int x, int y){
            unvisitX.push(x);
            unvisitY.push(y);
    }

    private void makePath(int x, int y, int next){
        int nextX = neighborX.get(next);
        int nextY = neighborY.get(next);
        int wallX = x - (x - nextX) / 2;
        int wallY = y - (y - nextY) / 2;
        board[wallX][wallY] = PATH;
        board[nextX][nextY] = PATH;
    }

    private boolean isComplete(){
        return (neighborX.size() == 1);
    }

    private boolean hasNeighbor(){
        return !neighborX.isEmpty();
    }

    private void findNeighbor(int x, int y){
        neighborX.clear();
        neighborY.clear();

        if (isInBound(x - 2, y) && isWall(x - 2, y)){   // left
            neighborX.add(x - 2);
            neighborY.add(y);
        }
        if (isInBound(x + 2, y) && isWall(x + 2, y)){   //right
            neighborX.add(x + 2);
            neighborY.add(y);
        }
        if (isInBound(x, y + 2) && isWall(x, y + 2)) {   //top
            neighborX.add(x);
            neighborY.add(y + 2);
        }
        if (isInBound(x, y - 2) && isWall(x, y - 2)){   //down
            neighborX.add(x);
            neighborY.add(y - 2);
        }
    }

    private  boolean isInBound(int x, int y){
        if (x < 1 || x > this.x - 2 || y < 1 || y > this.y - 2) {
            return false;
        }
        return true;
    }

    private int randomNeighbor(){
        Random random = new Random();
        return random.nextInt(neighborX.size());
    }

    private void randomRemoveWall(){
        Random random = new Random();
        boolean isRemoved = false;

        int ranX = 0;
        int ranY = 0;
        while(!isRemoved) {
            ranX = random.nextInt(this.x);
            ranY = random.nextInt(this.y);
            if (isInBound(ranX, ranY) && board[ranX][ranY] == WALL){
                isRemoved = true;
            }
        }
        board[ranX][ranY] = PATH;


    }
}
