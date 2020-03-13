package GameLogic;


import java.util.ArrayList;
import java.util.Random;

public class Board {
    private Player player;
    private int[] playerInit;
    private ArrayList<Trap> trapArrayManager;
    private Exit exit;
    private LevelGenerator generator;
    private Enemy enemy;
    private int[] enemyInit;


    public Board(int level){
        //test
        generator = new LevelGenerator(41, 41);
        trapArrayManager = new ArrayList<Trap>();
        trapGenerator(level);
        exit = new Exit();
        randomizeExitPosition();
        playerInit = new int[]{1, 1};
        player = new Player(playerInit);
        //test// need to modify the number of enemy base on the level
        enemyInit = new int[]{39, 39};
        enemy = new Enemy(enemyInit);
    }

    public boolean isWall(int x, int y){ return generator.isWall(x, y);}
    public boolean isInBounds(int x, int y) {return (x >= 0 && x < 41 && y >= 0 && y < 41);}

    public int[][] getBoard() { return generator.getBoard();}
    public Exit getExit() { return exit; }
    public void setDifficulty(LevelGenerator.Difficulty choice) { generator.setLevel(choice);}
    public Player getPlayer(){return this.player;}
    public int[] getPlayerPos(){
        return player.getPosition();
    }

    public Enemy getEnemy(){return this.enemy;}
    public int[] getEnemyPos(){return enemy.getPosition();}

    public int integerRandomizer(){
        Random random = new Random();
        return random.nextInt(40);
    }
    ///////////////// Trap Functionality ///////////////
    public ArrayList<Trap> getTrapArrayManager(){ return trapArrayManager; }
    public void trapGenerator (int difficultyLevel){
        switch (difficultyLevel){
            case 1:
                for (int i = 0; i < 4; i++) {
                    trapTypeGenerator('A');
                }
                for (int i = 0; i < 2; i++){
                    trapTypeGenerator('B');
                }
                break;

            case 2:
                for (int i = 0; i < 8; i++){
                    trapTypeGenerator('A');
                }
                for (int i = 0; i < 4; i++){
                    trapTypeGenerator('B');
                }
                break;

            case 3:
                for (int i = 0; i < 12; i++){
                    trapTypeGenerator('A');
                }
                for (int i = 0; i < 8; i++){
                    trapTypeGenerator('B');
                }
                break;
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
                if(planMove[0]== 1)
                {
                    planMove[0]=0;
                    if(PlayerY>EnemyY  )
                        planMove[1]=-1;
                    else
                        planMove[1]=1;

                }
                else if(planMove[0]==-1)
                {
                    planMove[0]=0;
                    if(PlayerY>EnemyY)
                        planMove[1]=1;
                    else
                        planMove[1]=-1;
                }
                else if(planMove[1]==1)
                {
                    planMove[1]=0;
                    if(PlayerX>EnemyX)
                        planMove[0]=1;
                    else
                        planMove[0]=-1;
                }
                else if(planMove[1]==-1)
                {
                    planMove[1]=0;
                    if(PlayerX>EnemyX)
                        planMove[0]=1;
                    else
                        planMove[0]=-1;
                }
                testValidMove = isWall(EnemyX+planMove[0],EnemyY+planMove[1]);
            }
        }
        enemy.move(planMove);
    }
}
