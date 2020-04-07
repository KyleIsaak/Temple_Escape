package gameLogic;

import java.util.ArrayList;

/**
 * Abstract class as an interface.
 */
public class BoardArrayManager {
    /**
     * Store board info.
     */
    protected int[][] board;
    /**
     * Store trap info.
     */
    protected ArrayList<Trap> trapArrayManager;
    /**
     * Store reward info.
     */
    protected ArrayList<Reward> rewardArrayManager;
    /**
     * Store Enemy info.
     */
    protected ArrayList<Enemy> enemyArrayManager;
    /**
     * Store exit info.
     */
    protected Exit exit;
    /**
     * Store trap finder info.
     */
    protected ArrayFinderManager trapFinder = new TrapArray();
    /**
     * Store array finder info.
     */
    protected ArrayFinderManager rewardFinder = new RewardArray();
    /**
     * Store enemy finder info.
     */
    protected ArrayFinderManager enemyFinder = new EnemyArray();

    public BoardArrayManager(int[][] board) {
        trapArrayManager = new ArrayList<Trap>();
        rewardArrayManager = new ArrayList<Reward>();
        enemyArrayManager = new ArrayList<Enemy>();
        exit = new Exit();
        this.board = board;
    }


    /**
     * Get the Trap Array Manager for this BoardArrayManager Class.
     *
     * @return this BoardArrayManager class's Trap Array Manager.
     */
    public ArrayList<Trap> getTrapArrayManager() {
        return trapArrayManager;
    }

    /**
     * Get the Reward Array Manager for this BoardArrayManager Class.
     *
     * @return this BoardArrayManager class's Reward Array Manager.
     */
    public ArrayList<Reward> getRewardArrayManager() {
        return rewardArrayManager;
    }

    /**
     * Get the Enemy Array Manager for this BoardArrayManager Class.
     *
     * @return this BoardArrayManager class's Enemy Array Manager.
     */
    public ArrayList<Enemy> getEnemyArrayManager() {
        return enemyArrayManager;
    }

    /**
     * Get the exit object for this BoardArrayManager Class.
     *
     * @return this BoardArrayManager class's exit.
     */
    public Exit getExit() {
        return exit;
    }

    /**
     * Get the board object for this BoardArrayManager Class.
     *
     * @return this BoardArrayManager class's board class.
     */
    public int[][] getBoard() {
        return board;
    }

    /**
     * Get the ith index's Enemy's position from the Enemy Array.
     *
     * @param i index of the Enemy located in the array.
     * @return the ith index's Enemy's position from the Enemy Array.
     */
    public int[] getEnemyPos(int i) {
        return enemyArrayManager.get(i).getPosition();
    }

    /**
     * Check whether that position consists a wall.
     * Wall in the matrix represent by 0 ; Path in the matrix represent by 1.
     *
     * @param x An integer for the x position.
     * @param y An integer for the y position.
     * @return true if that position contains a wall and vise versa.
     */
    public boolean isWall(int x, int y) {
        return board[x][y] == 0;
    }

    /**
     * Check whether that location on the map is the exit.
     *
     * @param x An integer for the x position.
     * @param y An integer for the y position.
     * @return true when the location is the exit.
     */
    public boolean isExit(int x, int y) {
        return exit.getPosition()[0] == x && exit.getPosition()[1] == y;
    }

    /**
     * Check whether that location on the map is inside the boundary of the game play area.
     *
     * @param x An integer for the x position.
     * @param y An integer for the y position.
     * @return true when the location is inside the boundary and vise versa.
     */
    public boolean isInBounds(int x, int y) {
        return (x >= 0 && x < 27 && y >= 0 && y < 27);
    }

    /**
     * /**
     * Check whether that location on the map contains that type of object.
     *
     * @param x    An integer for the x position.
     * @param y    An integer for the y position.
     * @param type A string that defines the type of object.
     * @return true when it contains a trap and vise versa.
     */
    public boolean isObject(int x, int y, String type) {
        boolean isFound = false;
        switch (type) {
            case "Trap":
                isFound = trapFinder.isObject(x, y, trapArrayManager);
                break;
            case "Reward":
                isFound = rewardFinder.isObject(x, y, rewardArrayManager);
                break;
            case "Enemy":
                isFound = enemyFinder.isObject(x, y, enemyArrayManager);
                break;
            default:
        }
        return isFound;
    }

    /**
     * Check whether that location on the map contains that type of object and return the index of the object in the array.
     *
     * @param x    An integer for the x position.
     * @param y    An integer for the y position.
     * @param type A string that defines the type of object.
     * @return Return the index of the object in the object Array.
     */
    public int objectFinder(int x, int y, String type) {
        int index = 0;
        switch (type) {
            case "Trap":
                index = trapFinder.objectFinder(x, y, trapArrayManager);
                break;
            case "Reward":
                index = rewardFinder.objectFinder(x, y, rewardArrayManager);
                break;
            case "Enemy":
                index = enemyFinder.objectFinder(x, y, enemyArrayManager);
                break;
            default:
        }
        return index;
    }

    /**
     * Unlock this Board class's exit.
     */
    public void unlockExit() {
        exit.setUnlocked(true);
        System.out.println("Exit unlocked");
    }

}
