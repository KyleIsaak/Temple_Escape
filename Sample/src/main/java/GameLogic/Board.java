package GameLogic;


import java.util.ArrayList;
import java.util.Random;

public class Board {
    private Player player;
    private int[] playerInit;
    private ArrayList<Trap> trapArrayManager;
    private Exit exit;
    private LevelGenerator generator;

    public Board(int level){
        //test
        generator = new LevelGenerator(41, 41);
        trapArrayManager = new ArrayList<Trap>();
        trapGenerator(level);
        exit = new Exit();
        randomizeExitPosition();
        playerInit = new int[]{1, 1};
        player = new Player(playerInit);


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
}
