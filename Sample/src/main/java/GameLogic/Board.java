package GameLogic;


import UI.Misc;
import UI.TestMain;

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

    private ArrayList<Trap> trapArrayManager;
    private ArrayList<Reward> rewardArrayManager;
    private ArrayList<Enemy> EnemyArrayManager;
    private Exit exit;
    private LevelGenerator generator;
    //private static Timer timer;

    /**
     * Non-Default Constructor (Board Class)
     * @param level An integer defining the difficulty selected.
     */
    public Board(int level){
        generator = new LevelGenerator(sizeX, sizeY);

        trapArrayManager = new ArrayList<Trap>();
        trapGenerator(level);
        rewardArrayManager = new ArrayList<Reward>();
        rewardGenerator(level);
        EnemyArrayManager = new ArrayList<Enemy>();
        enemyGenerator(level);

        exit = new Exit();
        randomizeExitPosition();

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

        trapArrayManager = new ArrayList<Trap>();
        trapGenerator(level);
        rewardArrayManager = new ArrayList<Reward>();
        rewardGenerator(level);
        EnemyArrayManager = new ArrayList<Enemy>();
        enemyGenerator(level);
        exit = new Exit();
        randomizeExitPosition();
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

        trapArrayManager = new ArrayList<Trap>();
        trapGenerator(level);
        rewardArrayManager = new ArrayList<Reward>();
        rewardGenerator(level);
        EnemyArrayManager = new ArrayList<Enemy>();
        enemyGenerator(level);

        exit = new Exit();
        randomizeExitPosition();

        playerInit = new int[]{1, 1};
        player = new Player(playerInit);

        score = new Score(scoreAmount);
        timer = new Timer(oldTimer);
    }

    /**
     * Check whether that location on the map contains a wall.
     * @param x An integer for the x position.
     * @param y An integer for the y position
     * @return true when it contains a wall and vise versa.
     */
    public boolean isWall(int x, int y){ return generator.isWall(x, y); }

    /**
     * Check whether that location on the map is inside the boundary of the game play area.
     * @param x An integer for the x position
     * @param y An integer for the y position
     * @return true when the location is inside the boundary and vise versa.
     */
    public boolean isInBounds(int x, int y) { return (x >= 0 && x < sizeX && y >= 0 && y < sizeY); }

    /**
     * Check whether that location on the map contains a trap.
     * @param x An integer for the x position.
     * @param y An integer for the y position
     * @return true when it contains a trap and vise versa.
     */
    public boolean isTrap(int x, int y){
        for (int i = 0; i < trapArrayManager.size(); i++) {
            int[] current = trapArrayManager.get(i).getPosition();
            if ((current[0] == x) && (current[1] == y)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether that location on the map contains an enemy.
     * @param x An integer for the x position.
     * @param y An integer for the y position
     * @return true when it contains an enemy and vise versa.
     */
    public boolean isEnemy(int x, int y) {
        for (int i = 0; i < EnemyArrayManager.size(); i++) {
            int[] current = EnemyArrayManager.get(i).getPosition();
            if ((current[0] == x) && (current[1] == y)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether that location on the map contains a reward.
     * @param x An integer for the x position.
     * @param y An integer for the y position
     * @return true when it contains a reward and vise versa.
     */
    public boolean isReward(int x, int y){
        for (int i = 0; i < rewardArrayManager.size(); i++) {
            int[] current = rewardArrayManager.get(i).getPosition();
            if ((current[0] == x) && (current[1] == y)) {
                return true;
            }
        }
        return false;
    }
    /**
     * Get this Board class's Reward Array Manager.
     * @return this Board class's Reward Array Manager.
     */
    public ArrayList<Reward> getRewardArrayManager(){ return rewardArrayManager; }

    /**
     * Gets the board of the Generator class
     * @return Generator's Board
     */
    public int[][] getBoard() { return generator.getBoard(); }

    /**
     * Gets the Exit of this Board
     * @return this Board's Exit
     */
    public Exit getExit() { return exit; }

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
     * Get this Board class's Enemy Array Manager.
     * @return this Board class's Enemy Array Manager.
     */
    public ArrayList<Enemy> getEnemyArrayManager() { return EnemyArrayManager; }

    /**
     * Get the ith index's Enemy from the Enemy Array
     * @param i index of the Enemy located in the array
     * @return the ith index's Enemy from the Enemy Array
     */
    public Enemy getEnemy(int i){ return EnemyArrayManager.get(i); }

    /**
     * Get the ith index's Enemy's position from the Enemy Array
     * @param i index of the Enemy located in the array
     * @return the ith index's Enemy's position from the Enemy Array
     */
    public int[] getEnemyPos(int i){ return EnemyArrayManager.get(i).getPosition(); }

    /**
     * Get the score of this Board class
     * @return this Board class's Score
     */
    public Score getScore() { return score; }
    public Timer getTimer() { return timer; }

    /**
     * Integer Randomizer with the bound of sizeX
     * @return an randomized integer with the bound of sizeX
     */
    public int integerRandomizer(){
        Random random = new Random();
        return (random.nextInt(sizeX));
    }

    public int[] randomExitPicker(){
        Random random = new Random();
        int randX = (random.nextInt(4));

        int[] exitPos = new int[2];

        switch(randX) {
            case 0:                 // lock to left wall
                exitPos[0] = 1;
                exitPos[1] = integerRandomizer();
                break;

            case 1:                 // lock to right wall
                exitPos[0] = sizeX - 2;
                exitPos[1] = integerRandomizer();
                break;

            case 2:                 // lock to top wall
                exitPos[0] = integerRandomizer();
                exitPos[1] = 1;
                break;

            case 3:                 // lock to bottom wall
                exitPos[0] = integerRandomizer();
                exitPos[1] = sizeY - 2;
                break;
        }

        //Testing values - can be removed
        //System.out.println(sizeX);
        //System.out.println((sizeY));
        //System.out.println(exitPos[0]);
        //System.out.println(exitPos[1]);
        return exitPos;
    }

    /**
     * Get the Trap Array Manager of this Board Class
     * @return this Board class's Trap Array Manager
     */
    public ArrayList<Trap> getTrapArrayManager(){ return trapArrayManager; }

    /**
     * Create Traps (Lava Pit and Spikes) in the gameplay area depending on the difficulty selected.
     * @param difficultyLevel an integer that specify the difficulty selected.
     */
    public void trapGenerator (int difficultyLevel){
        for (int i = 0; i < difficultyLevel*2; i++) {   //Spikes
            trapTypeGenerator('A');
        }
        for (int i = 0; i < difficultyLevel; i++){      //Lava Pits
            trapTypeGenerator('B');
        }

    }

    /**
     * trapGenerator's Helper function: Facilitates in creating the specific trap (Spikes and Lava Pits)
     * Implements the factory method design pattern.
     * @param type A character that specify the type of specific trap selected.
     */
    private void trapTypeGenerator (char type){
        switch (type){
            case 'A' :  //Spikes
                trapLocationRandomizer(new TrapTypeA());
                break;

            case 'B' :  //Lava Pits
                trapLocationRandomizer(new TrapTypeB());
                break;
        }
    }

    /**
     * trapGenerator's Helper Function: Facilitates in the randomizing the location of the traps
     * @param trapObject Specific Trap Object
     */
    public void trapLocationRandomizer (Trap trapObject){
        int x = integerRandomizer();
        int y = integerRandomizer();

        while ((isWall(x,y)) || (isTrap(x,y))){
            x = integerRandomizer();
            y = integerRandomizer();
        }

        trapObject.setPosition(new int[]{x, y});
        trapArrayManager.add(trapObject);
    }

    /**
     * Returns the index of the Trap in the Trap Array Manager which is located in that position
     * @param x An integer for the x position.
     * @param y An integer for the y position.
     * @return the index of the Trap of that position in the Trap Array Manager
     */
    public int trapFinder (int x, int y){
        for (int i = 0; i < trapArrayManager.size(); i++){
            Trap current = trapArrayManager.get(i);
            if ((current.getPosition()[0] == x )&& (current.getPosition()[1] == y)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Randomize the position of this Board class's exit.
     */
    public void randomizeExitPosition(){
        //int x = integerRandomizer();
        //int y = integerRandomizer();

        int exitPos[] = randomExitPicker();
        int x = exitPos[0];
        int y = exitPos[1];

        while ((( x < 5 && y < 5) || (isReward(x,y)) || (isTrap(x,y)))){
            exitPos = randomExitPicker();
            x = exitPos[0];
            y = exitPos[1];
        }

        exit.setPosition(new int[]{x, y});
        //exit.setPosition(exitPos);

    }

    /**
     * Check whether this Board class's exit is unlock
     * @return true if this Board class's exit is unlock or vise versa
     */
    public boolean isExitUnlocked(){
        if (exit.getIsUnlocked()){
            return true;
        }
        return false;
    }

    /**
     * Unlock this Board class's exit.
     */
    public void unlockExit(){
        exit.setUnlocked(true);
        System.out.println("Exit unlocked");
    }

    /**
     * Check whether the position is at a corner of a wall in the map.
     * @param position Position in the Map
     * @return true if the position is at a corner of a wall in the map and vise versa.
     */
    public boolean isCorner(int[] position)
    {
        int X=position[0];
        int Y=position[1];
        if(isWall(X+1,Y) && isWall(X,Y+1))
            return true;
        if(isWall(X-1,Y)&&isWall(X,Y+1))
            return true;
        if(isWall(X+1,Y)&&isWall(X,Y-1))
            return true;
        if(isWall(X-1,Y)&&isWall(X,Y-1))
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
        if(isWall(X+1,Y) && isWall(X,Y+1) && isWall(X-1,Y))
            return true;
        if(isWall(X+1,Y) && isWall(X,Y-1) && isWall(X-1,Y))
            return true;
        if(isWall(X,Y+1) && isWall(X,Y-1) && isWall(X-1,Y))
            return true;
        if(isWall(X,Y+1) && isWall(X,Y-1) && isWall(X+1,Y))
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
        planMove=enemy.chaseThePlayer(player.getPosition());
        int[] PlayerPosition=getPlayerPos();
        int PlayerX=PlayerPosition[0];//playerPositionX
        int PlayerY=PlayerPosition[1];//PlayerPositionY
        int[] Enemyposition=getEnemyPos(i);
        int EnemyX=Enemyposition[0];
        int EnemyY=Enemyposition[1];
        while(testValidMove) {
            testValidMove = isWall(EnemyX+planMove[0],EnemyY+planMove[1]);
            if(testValidMove == true)
            {
                 if(isThreeWall(Enemyposition)==true)
                    {
                        if(isWall(EnemyX+1,EnemyY) && isWall(EnemyX,EnemyY+1) && isWall(EnemyX-1,EnemyY))
                        {
                            planMove[0]=0;
                            planMove[1]=-1;
                        }

                        if(isWall(EnemyX+1,EnemyY) && isWall(EnemyX,EnemyY-1) && isWall(EnemyX-1,EnemyY))
                        {
                            planMove[0]=0;
                            planMove[1]=1;
                        }

                        if(isWall(EnemyX,EnemyY+1) && isWall(EnemyX,EnemyY-1) && isWall(EnemyX-1,EnemyY))
                        {
                            planMove[0]=1;
                            planMove[1]=0;
                        }

                        if(isWall(EnemyX,EnemyY+1) && isWall(EnemyX,EnemyY-1) && isWall(EnemyX+1,EnemyY))
                        {
                            planMove[0]=-1;
                            planMove[1]=0;
                        }
                    }

                 else if(isCorner(Enemyposition) == true)
                 {
                     if(isWall(EnemyX+1,EnemyY) && isWall(EnemyX,EnemyY+1))
                     {
                         planMove[0]=0;
                         planMove[1]=-1;
                     }

                     if(isWall(EnemyX-1,EnemyY)&&isWall(EnemyX,EnemyY+1))
                     {
                         planMove[0]=0;
                         planMove[1]=-1;
                     }

                     if(isWall(EnemyX+1,EnemyY)&&isWall(EnemyX,EnemyY-1))
                     {
                         planMove[0]=0;
                         planMove[1]=1;
                     }

                     if(isWall(EnemyX-1,EnemyY)&&isWall(EnemyX,EnemyY-1))
                     {
                         planMove[0]=0;
                         planMove[1]=1;
                     }
                 }

                 else if(isCorner(Enemyposition)==false)
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
                testValidMove = isWall(EnemyX+planMove[0],EnemyY+planMove[1]);
            }
        }
        //Solving two enemy standing in a same position
        int level=Misc.getCurrentLevel();
        if(level>=3)
            level=3;
        for (int j = 0; j < level; j++) {
            if (i == j)
                continue;
            else if (EnemyX + planMove[0] == EnemyArrayManager.get(j).getPosition()[0] && EnemyY + planMove[1] == EnemyArrayManager.get(j).getPosition()[1]) {
                planMove[0] = 0;
                planMove[1] = 0;
            }
        }
        enemy.move(planMove);

    }

    /**
     * Create Enemy in the gameplay area depending on the difficulty selected.
     * @param difficultyLevel An integer of the difficulty selected.
     */
    public void enemyGenerator(int difficultyLevel)
    {
        if(difficultyLevel < 3) {
            for (int i = 0; i < difficultyLevel; i++) {
                enemyLocationRandomizer(new Enemy(playerInit));
            }
        }
        else{
            for (int i = 0; i < 3; i++) {
                enemyLocationRandomizer(new Enemy(playerInit));
            }
        }
    }

    /**
     *  EnemyGenerator's Helper Function: Facilitates in the randomizing the location of the enemy
     * @param enemy A specific enemy object
     */
    public void enemyLocationRandomizer(Enemy enemy) {
        int x = integerRandomizer();
        int y = integerRandomizer();

        while (((x < 6 && y < 6) || isWall(x, y)) || (isTrap(x, y))) {
            x = integerRandomizer();
            y = integerRandomizer();
        }

        enemy.setPosition(new int[]{x, y});
        //cellStatusManager.add(new int [] {x, y});
        EnemyArrayManager.add(enemy);
    }

    /**
     * Returns the index of the Enemy in the Enemy Array Manager which is located in that position
     *  @param x An integer for the x position.
     *  @param y An integer for the y position.
     *  @return the index of the Enemy of that position in the Enemy Array Manager
     */
    public int EnemyFinder(int x, int y) {
        for (int i = 0; i < EnemyArrayManager.size(); i++) {
            Enemy current = EnemyArrayManager.get(i);
            if ((current.getPosition()[0] == x) && (current.getPosition()[1] == y)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Move the enemy by one step in either direction.
     * @param currentlevel An integer of the current difficulty selected.
     */
    public void MoveEnemy(int currentlevel) {
        for (int i = 0; i < currentlevel; i++) {
            int[] position = EnemyArrayManager.get(i).chaseThePlayer(getPlayerPos());
            for(int j = 0; j < currentlevel; j++)
            {
                if(i==j)
                    continue;
                if(position[0]==EnemyArrayManager.get(j).chaseThePlayer(getPlayerPos())[0] && position[1]==EnemyArrayManager.get(j).chaseThePlayer(getPlayerPos())[1])
                {
                    position[0]=0;
                    position[1]=0;
                }
            }
            EnemyArrayManager.get(i).move(position);
        }
    }

    /**
     * Create Specific Rewards in the gameplay area depending on the difficulty selected.
     * @param difficultyLevel An integer of the difficulty selected.
     */
    public void rewardGenerator (int difficultyLevel){
        for (int i = 0; i < difficultyLevel*2; i++) {   //Coins
            rewardTypeGenerator('A');
        }
        for (int i = 0; i < difficultyLevel; i++){      //Keys
            rewardTypeGenerator('B');
        }
    }

    /**
     * rewardGenerator's Helper function: Facilitates in creating the specific rewards (Coins and Keys)
     * Implements the factory method design pattern.
     * @param type A character that specify the type of specific reward selected.
     */
    private void rewardTypeGenerator (char type){
        switch (type){
            case 'A' :
                rewardLocationRandomizer(new RewardTypeA());
                break;

            case 'B' :
                rewardLocationRandomizer(new RewardTypeB());
                break;
        }
    }

    /**
     * rewardGenerator's Helper Function: Facilitates in the randomizing the location of the rewards
     * @param rewardObject Specific Reward Object
     */
    public void rewardLocationRandomizer (Reward rewardObject){
        int x = integerRandomizer();
        int y = integerRandomizer();

        while ((isWall(x,y)) || (isReward(x,y) || (isTrap(x,y)))){
            x = integerRandomizer();
            y = integerRandomizer();
        }

        rewardObject.setPosition(new int [] {x, y});
        //cellStatusManager.add(new int [] {x, y});
        rewardArrayManager.add(rewardObject);
    }

    /**
     * Returns the index of the Reward in the Reward Array Manager which is located in that position
     *  @param x An integer for the x position.
     *  @param y An integer for the y position.
     *  @return the index of the Reward of that position in the Reward Array Manager
     */
    public int rewardFinder (int x, int y){
        for (int i = 0; i < rewardArrayManager.size(); i++){
            Reward current = rewardArrayManager.get(i);
            if ((current.getPosition()[0] == x )&& (current.getPosition()[1] == y)){
                return i;
            }
        }
        return -1;
    }
    //GameOverCase
    public boolean isGameOver(Enemy enemy)
    {
        boolean test=false;
        if(enemy.getPosition()[0]==player.getPosition()[0] && enemy.getPosition()[1]==player.getPosition()[1])
            test=true;
        if(score.isNegative()==true)
            test=true;
        return test;
    }
}





