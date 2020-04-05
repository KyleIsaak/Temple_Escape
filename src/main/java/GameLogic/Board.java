package GameLogic;


import UI.Misc;

import java.util.ArrayList;
import java.util.Random;

/**
 * Store the dimension of the Game Play Area.
 * Manages all movable and non-movable objects in the Game Play Area.
 *
 */
public class Board {

    private int sizeX = 27;
    private int sizeY = 27;
    private Player player;
    private int[] playerInit;
    private Score score;
    private Timer timer;

    private LocationRandomizerGenerator boardArrayManager;
    private LevelGenerator generator;

    /**
     * Non-Default Constructor (Board Class)
     * @param level An integer defining the difficulty selected.
     */
    public Board(int level){
        generator = new LevelGenerator(sizeX, sizeY);
        boardArrayManager = new LocationRandomizerGenerator(generator.getBoard());
        boardArrayManager.objectGenerator(level,"Trap");
        boardArrayManager.objectGenerator(level,"Reward");
        boardArrayManager.objectGenerator(level,"Enemy");
        boardArrayManager.randomizeExitPosition();

        playerInit = new int[]{1, 1};
        player = new Player(playerInit);

        score = new Score();
        timer = new Timer();
    }

    /**
     * Non Default Constructor (Board Class)
     * To be used for recreating board when transiting to next level.
     * @param level An integer defining the difficulty selected.
     * @param scoreAmount An integer holding the current score from the current level
     */
    public Board(int level, int scoreAmount){
        generator = new LevelGenerator(sizeX, sizeY);
        boardArrayManager = new LocationRandomizerGenerator(generator.getBoard());
        boardArrayManager.objectGenerator(level,"Trap");
        boardArrayManager.objectGenerator(level,"Reward");
        boardArrayManager.objectGenerator(level,"Enemy");
        boardArrayManager.randomizeExitPosition();
        playerInit = new int[]{1, 1};
        player = new Player(playerInit);
        score = new Score(scoreAmount);
        timer = new Timer();

    }

    /**
     * Non Default Constructor (Board Class)
     * To be used for recreating board when transiting to next level.
     * @param level An integer defining the difficulty selected.
     * @param scoreAmount An integer holding the current score from the current level.
     * @param oldTimer A timer class that has the old information.
     */
    public Board(int level, int scoreAmount, Timer oldTimer){
        generator = new LevelGenerator(sizeX, sizeY);
        boardArrayManager = new LocationRandomizerGenerator(generator.getBoard());
        boardArrayManager.objectGenerator(level,"Trap");
        boardArrayManager.objectGenerator(level,"Reward");
        boardArrayManager.objectGenerator(level,"Enemy");
        boardArrayManager.randomizeExitPosition();

        playerInit = new int[]{1, 1};
        player = new Player(playerInit);

        score = new Score(scoreAmount);
        timer = new Timer(oldTimer);
    }

    /**
     * Set the difficulty of the game.
     * @param choice Difficulty of the Game.
     */
    public void setDifficulty(LevelGenerator.Difficulty choice) { generator.setLevel(choice); }

    /**
     * Get the Player of this board.
     * @return this Board's Player.
     */
    public Player getPlayer(){ return this.player; }

    /**
     * Get the Position of this Board's Player
     * @return the position of this Board's Player.
      */
    public int[] getPlayerPos(){ return player.getPosition(); }

    /**
     * Get the score of this Board class
     * @return this Board class's Score
     */
    public Score getScore() { return score; }
    public Timer getTimer() { return timer; }


    /**
     * Check whether the position is at a corner of a wall in the map.
     * @param position Position in the Map
     * @return true if the position is at a corner of a wall in the map and vise versa.
     */
    public boolean isCorner(int[] position)
    {
        int X=position[0];
        int Y=position[1];
        if(boardArrayManager.isWall(X+1,Y) && boardArrayManager.isWall(X,Y+1))
            return true;
        if(boardArrayManager.isWall(X-1,Y) && boardArrayManager.isWall(X,Y+1))
            return true;
        if(boardArrayManager.isWall(X+1,Y) && boardArrayManager.isWall(X,Y-1))
            return true;
        if(boardArrayManager.isWall(X-1,Y) && boardArrayManager.isWall(X,Y-1))
            return true;
        return false;
    }

    /**
     * Check whether the position is at a corner of a wall in the map.
     * @param position Position in the Map
     * @return true if the position is at a corner of a wall in the map and vise versa.
     */
    public boolean isThreeWall(int[]position)
    {
        int X=position[0];
        int Y=position[1];
        if(boardArrayManager.isWall(X+1,Y) && boardArrayManager.isWall(X,Y+1) && boardArrayManager.isWall(X-1,Y))
            return true;
        if(boardArrayManager.isWall(X+1,Y) && boardArrayManager.isWall(X,Y-1) && boardArrayManager.isWall(X-1,Y))
            return true;
        if(boardArrayManager.isWall(X,Y+1) && boardArrayManager.isWall(X,Y-1) && boardArrayManager.isWall(X-1,Y))
            return true;
        if(boardArrayManager.isWall(X,Y+1) && boardArrayManager.isWall(X,Y-1) && boardArrayManager.isWall(X+1,Y))
            return true;
        return false;
    }

    /**
     * Provide enemy functionality of approaching the player using the nearest path
     * @param enemy The enemy that is chasing the player.
     * @param i The index of that enemy in the Enemy Array Manager.
     */
    public void chaseThePlayer(Enemy enemy, int i){
        boolean testValidMove = true;
        int[] planMove = {0,0};
        planMove = enemy.chaseThePlayer(player.getPosition());
        int[] PlayerPosition = getPlayerPos();
        int PlayerX = PlayerPosition[0];//playerPositionX
        int PlayerY = PlayerPosition[1];//PlayerPositionY
        int[] enemyPosition = boardArrayManager.getEnemyPos(i);
        int EnemyX = enemyPosition[0];
        int EnemyY = enemyPosition[1];
        while(testValidMove) {
            testValidMove = boardArrayManager.isWall(EnemyX+planMove[0],EnemyY+planMove[1]);
            if(testValidMove == true)
            {
                 if(isThreeWall(enemyPosition) == true)
                    {
                        if(boardArrayManager.isWall(EnemyX+1,EnemyY) && boardArrayManager.isWall(EnemyX,EnemyY+1) && boardArrayManager.isWall(EnemyX-1,EnemyY))
                        {
                            planMove[0]=0;
                            planMove[1]=-1;
                        }

                        if(boardArrayManager.isWall(EnemyX+1,EnemyY) && boardArrayManager.isWall(EnemyX,EnemyY-1) && boardArrayManager.isWall(EnemyX-1,EnemyY))
                        {
                            planMove[0]=0;
                            planMove[1]=1;
                        }

                        if(boardArrayManager.isWall(EnemyX,EnemyY+1) && boardArrayManager.isWall(EnemyX,EnemyY-1) && boardArrayManager.isWall(EnemyX-1,EnemyY))
                        {
                            planMove[0]=1;
                            planMove[1]=0;
                        }

                        if(boardArrayManager.isWall(EnemyX,EnemyY+1) && boardArrayManager.isWall(EnemyX,EnemyY-1) && boardArrayManager.isWall(EnemyX+1,EnemyY))
                        {
                            planMove[0]=-1;
                            planMove[1]=0;
                        }
                    }

                 else if(isCorner(enemyPosition) == true)
                 {
                     if(boardArrayManager.isWall(EnemyX+1,EnemyY) && boardArrayManager.isWall(EnemyX,EnemyY+1))
                     {
                         planMove[0]=0;
                         planMove[1]=-1;
                     }

                     if(boardArrayManager.isWall(EnemyX-1,EnemyY) && boardArrayManager.isWall(EnemyX,EnemyY+1))
                     {
                         planMove[0]=0;
                         planMove[1]=-1;
                     }

                     if(boardArrayManager.isWall(EnemyX+1,EnemyY) && boardArrayManager.isWall(EnemyX,EnemyY-1))
                     {
                         planMove[0]=0;
                         planMove[1]=1;
                     }

                     if(boardArrayManager.isWall(EnemyX-1,EnemyY) && boardArrayManager.isWall(EnemyX,EnemyY-1))
                     {
                         planMove[0]=0;
                         planMove[1]=1;
                     }
                 }

                 else if(isCorner(enemyPosition)==false)
                 {
                     if (planMove[0] == 1) {
                         planMove[0] = 0;
                         if (PlayerY > EnemyY)
                             planMove[1] = -1;
                         else
                             planMove[1] = 1;

                     }

                     else if (planMove[0] == -1) {
                         planMove[0] = 0;
                         if (PlayerY > EnemyY)
                             planMove[1] = 1;
                         else
                             planMove[1] = -1;
                     }

                     else if (planMove[1] == 1) {
                         planMove[1] = 0;
                         if (PlayerX > EnemyX)
                             planMove[0] = 1;
                         else
                             planMove[0] = -1;
                     }

                     else if (planMove[1] == -1) {
                         planMove[1] = 0;
                         if (PlayerX > EnemyX)
                             planMove[0] = 1;
                         else
                             planMove[0] = -1;
                     }
                 }
                testValidMove = boardArrayManager.isWall(EnemyX+planMove[0],EnemyY+planMove[1]);
            }
        }
        //Solving two enemy standing in a same position
        int level = Misc.getCurrentLevel();
        if(level >= 3) {
            level = 3;
        }
        for (int j = 0; j < level; j++) {
            if (i == j)
                continue;
            else if (EnemyX + planMove[0] == boardArrayManager.getEnemyArrayManager().get(j).getPosition()[0] && EnemyY + planMove[1] == boardArrayManager.getEnemyArrayManager().get(j).getPosition()[1]) {
                planMove[0] = 0;
                planMove[1] = 0;
            }
        }
        enemy.move(planMove);

    }

    //GameOverCase
    public boolean isGameOver(Enemy enemy)
    {
        boolean test=false;
        if(enemy.getPosition()[0] == player.getPosition()[0] && enemy.getPosition()[1] == player.getPosition()[1])
            test = true;
        if(score.isNegative() == true)
            test = true;
        return test;
    }
}





