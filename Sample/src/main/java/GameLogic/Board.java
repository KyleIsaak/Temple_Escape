package GameLogic;


public class Board {
    private Player player;
    private int[] playerInit;

    private LevelGenerator generator;

    public Board(int level){
        //test
        generator = new LevelGenerator(40, 40);
        playerInit = new int[]{1, 1};
        player = new Player(playerInit);
    }

    public boolean isWall(int x, int y){ return generator.isWall(x, y);}
    public int[][] getBoard() { return generator.getBoard();}
    public void setDifficulty(LevelGenerator.Difficulty choice) { generator.setLevel(choice);}

    public Player getPlayer(){return this.player;}
    public int[] getPlayerPos(){
        return player.getPosition();
    }

}