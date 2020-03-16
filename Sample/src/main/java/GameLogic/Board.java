package GameLogic;


import java.util.ArrayList;
import java.util.Random;

public class Board {

    private int sizeX = 27;
    private int sizeY = 27;
    private Player player;
    private int[] playerInit;

    private Score score;

    private ArrayList<Trap> trapArrayManager;
    private ArrayList<Reward> rewardArrayManager;
    private int regularRewardCounter;
    private Exit exit;
    private LevelGenerator generator;

    private Enemy enemy;
    private int[] enemyInit;
    private Reward reward;
    private int[] rewardInit;

    public Board(int level){
        generator = new LevelGenerator(sizeX, sizeY);
        trapArrayManager = new ArrayList<Trap>();
        trapGenerator(level);
        rewardArrayManager = new ArrayList<Reward>();
        rewardGenerator(level);
        exit = new Exit();
        randomizeExitPosition();
        playerInit = new int[]{1, 1};
        player = new Player(playerInit);
        score = new Score();
        //test// need to modify the number of enemy base on the level
        enemyInit = new int[]{sizeX - 2, sizeY - 2};
        enemy = new Enemy(enemyInit);


    }

    public boolean isWall(int x, int y){ return generator.isWall(x, y);}
    public boolean isInBounds(int x, int y) {return (x >= 0 && x < sizeX && y >= 0 && y < sizeY);}

    public int[][] getBoard() { return generator.getBoard();}
    public Exit getExit() { return exit; }
    public void setDifficulty(LevelGenerator.Difficulty choice) { generator.setLevel(choice);}

    public Player getPlayer(){return this.player;}
    public int[] getPlayerPos(){ return player.getPosition(); }
    public Enemy getEnemy(){return this.enemy;}
    public int[] getEnemyPos(){return enemy.getPosition();}
    public Reward getReward(){return this.reward;}
    public int[] getRewardPos(){return reward.getPosition();}
    public Score getScore() { return score; }
    public void decreaseRegularRewardCounter() { regularRewardCounter = regularRewardCounter - 1; }

    public int integerRandomizer(){
        Random random = new Random();
        return (random.nextInt(sizeX));
    }
    ///////////////// Trap Functionality ///////////////
    public ArrayList<Trap> getTrapArrayManager(){ return trapArrayManager; }
    public void trapGenerator (int difficultyLevel){
        for (int i = 0; i < difficultyLevel*2; i++) {   //Spikes
            trapTypeGenerator('A');
        }
        for (int i = 0; i < difficultyLevel; i++){      //Lava
            trapTypeGenerator('B');
        }

    }


    private void trapTypeGenerator (char type){
        switch (type){
            case 'A' :
                trapLocationRandomizer(new TrapTypeA());
                break;

            case 'B' :
                trapLocationRandomizer(new TrapTypeB());
                break;
        }
    }


    public void trapLocationRandomizer (Trap trapObject){
        int x = integerRandomizer();
        int y = integerRandomizer();

        while ((isWall(x,y)) || (isTrap(x,y))){
            x = integerRandomizer();
            y = integerRandomizer();
        }

        trapObject.setPosition(new int [] {x, y});
        //cellStatusManager.add(new int [] {x, y});
        trapArrayManager.add(trapObject);
    }

    public boolean isTrap(int x, int y){
        for (int i = 0; i < trapArrayManager.size(); i++) {
            int[] current = trapArrayManager.get(i).getPosition();
            if ((current[0] == x) && (current[1] == y)) {
                return true;
            }
        }
        return false;
    }

    public int trapFinder (int x, int y){
        for (int i = 0; i < trapArrayManager.size(); i++){
            Trap current = trapArrayManager.get(i);
            if ((current.getPosition()[0] == x )&& (current.getPosition()[1] == y)){
                return i;
            }
        }
        return -1;
    }

    ////////////// Exit /////////////
    public void randomizeExitPosition(){
        int x = integerRandomizer();
        int y = integerRandomizer();

        while ((isWall(x,y)) || (isTrap(x,y))){
            x = integerRandomizer();
            y = integerRandomizer();
        }
        exit.setPosition(new int[]{x, y});

    }

    public boolean isExitUnlocked(){
        if (exit.getIsUnlocked()){
            return true;
        }
        else if (regularRewardCounter <= 0){
            System.out.println("Unlock Exit");
            exit.setUnlocked(true);
            return true;
        }
        return false;
    }

    public void unlockExit(){
        exit.setUnlocked(true);
        System.out.println("Exit unlocked");
    }



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

    //Enemy functionality
    public void chaseThePlayer(){
        boolean testValidMove = true;
        int[] planMove = {0,0};
        planMove=enemy.chaseThePlayer(player.getPosition());
        int[] PlayerPosition=getEnemyPos();
        int PlayerX=PlayerPosition[0];//playerPositionX
        int PlayerY=PlayerPosition[1];//PlayerPositionY
        int[] Enemyposition=getEnemyPos();
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

                     } else if (planMove[0] == -1) {
                         planMove[0] = 0;
                         if (PlayerY > EnemyY)
                             planMove[1] = 1;
                         else
                             planMove[1] = -1;
                     } else if (planMove[1] == 1) {
                         planMove[1] = 0;
                         if (PlayerX > EnemyX)
                             planMove[0] = 1;
                         else
                             planMove[0] = -1;
                     } else if (planMove[1] == -1) {
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
        enemy.move(planMove);
    }

    ///////////////// Reward Functionality ///////////////
    public ArrayList<Reward> getRewardArrayManager(){ return rewardArrayManager; }
    public void rewardGenerator (int difficultyLevel){

        for (int i = 0; i < difficultyLevel*2; i++) {   //Coins
            rewardTypeGenerator('A');
        }
        for (int i = 0; i < difficultyLevel; i++){      //Keys
            rewardTypeGenerator('B');
        }
        regularRewardCounter = 4;

    }

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

    public boolean isReward(int x, int y){
        for (int i = 0; i < rewardArrayManager.size(); i++) {
            int[] current = rewardArrayManager.get(i).getPosition();
            if ((current[0] == x) && (current[1] == y)) {
                return true;
            }
        }
        return false;
    }

    public int rewardFinder (int x, int y){
        for (int i = 0; i < rewardArrayManager.size(); i++){
            Reward current = rewardArrayManager.get(i);
            if ((current.getPosition()[0] == x )&& (current.getPosition()[1] == y)){
                return i;
            }
        }
        return -1;
    }
}



