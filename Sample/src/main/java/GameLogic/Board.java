package GameLogic;


public class Board {
    private Player player;
    private int[] playerInit;
    //could have a board generator that generates enemy position randomly, or it could be designed hard code

    //private Enemy enemy;
    //private enemy number = level * 2

    public Board(int level){
        //test
        if (level == 1) {
            playerInit = new int[]{10, 10};
            player = new Player(playerInit);
        }
    }

    public void movePlayer(int[] direction){
        player.move(direction);
    }

    public int[] getPlayerPos(){
        return player.getPosition();
    }
}
