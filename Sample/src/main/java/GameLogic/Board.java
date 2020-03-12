package GameLogic;


public class Board {
    private Player player;
    private int[] playerInit;

    private LevelGenerator generator;

    public Board(int level){
        //test
        generator = new LevelGenerator(41, 41);
        playerInit = new int[]{0, 0};
        player = new Player(playerInit);
    }

    public boolean isWall(int x, int y){ return generator.isWall(x, y);}
    public boolean isInBounds(int x, int y) {return (x >= 0 && x < 41 && y >= 0 && y < 41);}
    public int[][] getBoard() { return generator.getBoard();}
    public void setDifficulty(LevelGenerator.Difficulty choice) { generator.setLevel(choice);}

    public Player getPlayer(){return this.player;}
    public int[] getPlayerPos(){
        return player.getPosition();
    }

}
