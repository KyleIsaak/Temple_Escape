package GameLogic;


public class Board {
    private Player player;
    private int[] playerInit;
    private Enemy enemy;
    private int[] enemyInit;
    private LevelGenerator generator;
    //private enemy number = level * 2

    public Board(int level){
        //test
        generator = new LevelGenerator(40, 40);
        if (level == 1) {
            playerInit = new int[]{1, 1};
            player = new Player(playerInit);
            enemyInit= new int[]{1000, 1000};
            enemy = new Enemy(enemyInit);
        }
    }

    public boolean isWall(int x, int y){ return generator.isWall(x, y);}
    public int[][] getBoard(LevelGenerator.Difficulty choice) { return generator.getLevel(choice);}
    public void moveEnemy(int [] direction){enemy.chaseThePlayer(player.getPosition(),20); }
    public void movePlayer(int[] direction){
        player.move(direction);
    }
    public int[] getPlayerPos(){
        return player.getPosition();
    }
    public int[] getEnemyPos() { return enemy.getPosition(); }
}
