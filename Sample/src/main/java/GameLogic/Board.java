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
    private ArrayList<Enemy> EnemyArrayManager;

    private Exit exit;
    private LevelGenerator generator;
    //private static Timer timer;

    public Board(int level){
        generator = new LevelGenerator(sizeX, sizeY);

        trapArrayManager = new ArrayList<Trap>();
        trapGenerator(level);
        rewardArrayManager = new ArrayList<Reward>();
        rewardGenerator(level);
        EnemyArrayManager = new ArrayList<Enemy>();
        EnemyGenerator(level);

        exit = new Exit();
        randomizeExitPosition();

        playerInit = new int[]{1, 1};
        player = new Player(playerInit);

        score = new Score();
        //timer = new Timer();
    }

    //Non-Default Constructor
    public Board(int level, int scoreAmount){
        generator = new LevelGenerator(sizeX, sizeY);

        trapArrayManager = new ArrayList<Trap>();
        trapGenerator(level);
        rewardArrayManager = new ArrayList<Reward>();
        rewardGenerator(level);
        EnemyArrayManager = new ArrayList<Enemy>();
        EnemyGenerator(level);

        exit = new Exit();
        randomizeExitPosition();

        playerInit = new int[]{1, 1};
        player = new Player(playerInit);

        score = new Score(scoreAmount);
        //timer = new Timer();
    }

    public boolean isWall(int x, int y){ return generator.isWall(x, y);}
    public boolean isInBounds(int x, int y) {return (x >= 0 && x < sizeX && y >= 0 && y < sizeY);}

    public int[][] getBoard() { return generator.getBoard();}
    public Exit getExit() { return exit; }
    public void setDifficulty(LevelGenerator.Difficulty choice) { generator.setLevel(choice);}

    public Player getPlayer(){return this.player;}
    public int[] getPlayerPos(){ return player.getPosition(); }
    public Enemy getEnemy(int i){return EnemyArrayManager.get(i);}
    public int[] getEnemyPos(int i){return EnemyArrayManager.get(i).getPosition();}
    public Score getScore() { return score; }

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
        enemy.move(planMove);
    }


    public ArrayList<Enemy> getEnemyArrayManager() {
        return EnemyArrayManager;
    }


    public void EnemyGenerator(int difficultyLevel)
    {
        if(difficultyLevel < 3) {
            for (int i = 0; i < difficultyLevel; i++) {
                EnemyLocationRandomizer(new Enemy(playerInit));
            }
        }
        else{
            for (int i = 0; i < 3; i++) {
                EnemyLocationRandomizer(new Enemy(playerInit));
            }
        }
    }

    public void EnemyLocationRandomizer(Enemy enemy) {
        int x = integerRandomizer();
        int y = integerRandomizer();

        while ((isWall(x, y)) || (isTrap(x, y))) {
            x = integerRandomizer();
            y = integerRandomizer();
        }

        enemy.setPosition(new int[]{x, y});
        //cellStatusManager.add(new int [] {x, y});
        EnemyArrayManager.add(enemy);
    }

    public boolean isEnemy(int x, int y) {
        for (int i = 0; i < EnemyArrayManager.size(); i++) {
            int[] current = EnemyArrayManager.get(i).getPosition();
            if ((current[0] == x) && (current[1] == y)) {
                return true;
            }
        }
        return false;
    }

    public int EnemyFinder(int x, int y) {
        for (int i = 0; i < EnemyArrayManager.size(); i++) {
            Enemy current = EnemyArrayManager.get(i);
            if ((current.getPosition()[0] == x) && (current.getPosition()[1] == y)) {
                return i;
            }
        }
        return -1;
    }

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




    ///////////////// Reward Functionality ///////////////
    public ArrayList<Reward> getRewardArrayManager(){ return rewardArrayManager; }
    public void rewardGenerator (int difficultyLevel){

        for (int i = 0; i < difficultyLevel*2; i++) {   //Coins
            rewardTypeGenerator('A');
        }
        for (int i = 0; i < difficultyLevel; i++){      //Keys
            rewardTypeGenerator('B');
        }
        //regularRewardCounter = 4;

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



