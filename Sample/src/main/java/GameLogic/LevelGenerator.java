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
        LevelGenerator test = new LevelGenerator(41, 41);
        test.setLevel(LevelGenerator.Difficulty.EASY);
        int[][] map = test.getBoard();

        for (int y = 0; y < 41; y++) {
            for (int x = 0; x < 41; x++) {
                System.out.print(map[x][y]);
            }
            System.out.println();
        }
//
//        int newx = 3;
//        int newy = 3;
//
//        map[newx][newy] = 5;
//        newx = 2;
//        map[newx][newy] = 4;
//        newx = 4;
//        map[newx][newy] = 6;
//        newx = 3; newy = 2;
//        map[newx][newy] = 8;
//        newy = 4;
//        map[newx][newy] = 7;
//
//        for (int y = 1; y < map[0].length; y++) {
//            for (int x = 0; x < map.length - 1; x++) {
//                System.out.print(map[x][y]);
//            }
//            System.out.println();
//        }
//
//        System.out.println("3, 4: " + map[3][4]);
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
                }
                break;
            case MEDIUM:
                for(int i = 0; i < (x * y / 4); i ++){
                    randomRemoveWall();
                }
                break;
            case HARD:
                for(int i = 0; i < (x * y / 5); i ++){
                    randomRemoveWall();
                }
                break;

            default:
                break;

        }
    }

    public void generate(){
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
                if (!isComplete()){
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

    public boolean isWall(int x, int y){
        return (board[x][y] == WALL);
    }

    public int[][] getBoard(){
        return board;
    }

    private void pushStacks(int x, int y){
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

    private boolean isComplete(){
        return (neighborX.size() == 1);
    }

    private boolean hasNeighbor(){
        return !neighborX.isEmpty();
    }

    private int randomNeighbor(){
        Random random = new Random();
        return random.nextInt(neighborX.size());
    }

    private boolean isInBound(int x, int y){
        if (x < 1 || x > this.x - 2 || y < 1 || y > this.y - 2) {
            return false;
        }
        return true;
    }

    private void randomRemoveWall(){
        Random random = new Random();
        boolean isRemoved = false;
        boolean found = false;
        int ranX = 0;
        int ranY = 0;
        while (!isRemoved){

            while(!found) {
                ranX = random.nextInt(x);
                ranY = random.nextInt(y);
                if (board[ranX][ranY] == WALL && isInBound(ranX, ranY)){
                    found = true;
                }
            }
            isRemoved = true;
            board[ranX][ranY] = PATH;
        }
    }
}
