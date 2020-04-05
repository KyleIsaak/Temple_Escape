package GameLogic;

import java.util.ArrayList;

public class BoardArrayManager {
    protected int[][] board;
    protected ArrayList<Trap> trapArrayManager;
    protected ArrayList<Reward> rewardArrayManager;
    protected ArrayList<Enemy> enemyArrayManager;
    protected Exit exit;

    protected ArrayFinderManager trapFinder;
    protected ArrayFinderManager rewardFinder;

    public BoardArrayManager(int[][] board){
        trapArrayManager = new ArrayList<Trap>();
        rewardArrayManager = new ArrayList<Reward>();
        enemyArrayManager = new ArrayList<Enemy>();
        exit = new Exit();
        this.board = board;
    }

    public ArrayList<Trap> getTrapArrayManager(){ return trapArrayManager;}
    public ArrayList<Reward> getRewardArrayManager(){ return rewardArrayManager;}
    public ArrayList<Enemy> getEnemyArrayManager(){ return enemyArrayManager;}
    public Exit getExit(){ return exit;}

    /**
     * Check whether that position consists a wall
     * Wall in matrix represent by 0 ; Path in matrix represent by 1
     * @param x An integer for the x position
     * @param y An integer for the y position
     * @return true if that position contains a wall and vise versa.
     */
    public boolean isWall(int x, int y){ return board[x][y] == 0; }

    /**
     * Check whether that location on the map is the exit
     * @param x An integer for the x position
     * @param y An integer for the y position
     * @return true when the location is the exit.
     */
    public boolean isExit(int x, int y){ return exit.getPosition()[0] == x && exit.getPosition()[1] == y; }

    /**
     * Check whether that location on the map is inside the boundary of the game play area.
     * @param x An integer for the x position
     * @param y An integer for the y position
     * @return true when the location is inside the boundary and vise versa.
     */
    public boolean isInBounds(int x, int y) { return (x >= 0 && x < 27 && y >= 0 && y < 27); }

}
