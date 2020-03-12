package GameLogic;


import java.util.ArrayList;
import java.util.Random;

public class Board {
    private Player player;
    private int[] playerInit;
    private ArrayList<Trap> trapArrayManager;
    private LevelGenerator generator;

    public Board(int level){
        //test
        generator = new LevelGenerator(41, 41);
        trapArrayManager = new ArrayList<Trap>();
        trapGenerator(level);
        playerInit = new int[]{1, 1};

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
                locationRandomizer(new TrapTypeA());

                break;

            case 'B' :
                locationRandomizer(new TrapTypeB());
                break;
        }
    }

    public void locationRandomizer (Trap trapObject){
        Random random = new Random();
        int x = random.nextInt(40);
        int y = random.nextInt(40);

        while ((isWall(x,y)) || (isContain(x,y))){
            x = random.nextInt(40);
            y = random.nextInt(40);
        }

        trapObject.setPosition(new int [] {x, y});
        //cellStatusManager.add(new int [] {x, y});
        trapArrayManager.add(trapObject);
    }

    public boolean isContain(int x, int y){
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
}
