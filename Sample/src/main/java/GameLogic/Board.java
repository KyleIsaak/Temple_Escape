package GameLogic;


public class Board {
    private Player player;
    private int[] playerInit;
    //could have a board generator that generates enemy position randomly, or it could be designed hard code
    private Enemy enemy;
    private int[] enemyInit;
    //private enemy number = level * 2
    private Reward reward;
    private int[] rewardInit;

    public Board(int level){
        //test
        if (level == 1) {
            playerInit = new int[]{20, 20};
            player = new Player(playerInit);
            enemyInit= new int[]{1000, 1000};
            enemy = new Enemy(enemyInit);
            rewardInit = new int[]{500,500};
            reward = new Reward(rewardInit);
        }
    }
    public void moveEnemy(int [] direction){enemy.chaseThePlayer(player.getPosition(),20); }
    public void movePlayer(int[] direction){
        player.move(direction);
    }
    public int[] getPlayerPos(){
        return player.getPosition();
    }
    public int[] getEnemyPos() { return enemy.getPosition(); }
    public int[] getRewardPos() { return reward.getPosition();}
}
