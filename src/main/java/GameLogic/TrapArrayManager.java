package GameLogic;

import UI.Misc;
import java.util.ArrayList;
import java.util.Random;

public class TrapArrayManager {
    private ArrayList<Trap> trapArrayManager;
    private final LevelGenerator generator;
    private int sizeX;

    /**
     * Non Default Constructor
     * @param level An integer defining the difficulty selected.
     * @param generator A level generator integer used to provide insight details of the walls of the board
     * @param sizeX An integer of the size of X axis of the board
     */
    public TrapArrayManager(int level, LevelGenerator generator, int sizeX){
        this.sizeX = sizeX;
        this.generator = generator;
        trapArrayManager = new ArrayList<Trap>();
        trapGenerator(level);
    }

    /**
     * Get this class's Trap Array Manager.
     * @return this class's Trap Array Manager.
     */
    public ArrayList<Trap> getTrapArray() { return trapArrayManager; }

    /**
     * Create Traps (Lava Pit and Spikes) in the gameplay area depending on the difficulty selected.
     * @param difficultyLevel an integer that specify the difficulty selected.
     */
    public void trapGenerator (int difficultyLevel){
        for (int i = 0; i < difficultyLevel*2; i++) {   //Spikes
            trapTypeGenerator('A');
        }
        for (int i = 0; i < difficultyLevel; i++){      //Lava Pits
            trapTypeGenerator('B');
        }
    }

    /**
     * trapGenerator's Helper Function: Facilitates in the randomizing the location of the traps
     * @param trapObject Specific Trap Object
     */
    public void trapLocationRandomizer (Trap trapObject){
        int x = integerRandomizer();
        int y = integerRandomizer();

        while ((generator.isWall(x,y)) || (isTrap(x,y))){
            x = integerRandomizer();
            y = integerRandomizer();
        }

        trapObject.setPosition(new int[]{x, y});
        trapArrayManager.add(trapObject);
    }

    /**
     * Returns the index of the Trap in the Trap Array Manager which is located in that position
     * @param x An integer for the x position.
     * @param y An integer for the y position.
     * @return the index of the Trap of that position in the Trap Array Manager
     */
    public int trapFinder (int x, int y){
        for (int i = 0; i < trapArrayManager.size(); i++){
            Trap current = trapArrayManager.get(i);
            if ((current.getPosition()[0] == x )&& (current.getPosition()[1] == y)){
                return i;
            }
        }
        return -1;
    }

    /**
     * trapGenerator's Helper function: Facilitates in creating the specific trap (Spikes and Lava Pits)
     * Implements the factory method design pattern.
     * @param type A character that specify the type of specific trap selected.
     */
    private void trapTypeGenerator (char type){
        switch (type){
            case 'A' :  //Spikes
                trapLocationRandomizer(new TrapTypeA());
                break;

            case 'B' :  //Lava Pits
                trapLocationRandomizer(new TrapTypeB());
                break;
        }
    }
    /**
     * Check whether that location on the map contains a trap.
     * @param x An integer for the x position.
     * @param y An integer for the y position
     * @return true when it contains a trap and vise versa.
     */
    public boolean isTrap(int x, int y){
        for (int i = 0; i < trapArrayManager.size(); i++) {
            int[] current = trapArrayManager.get(i).getPosition();
            if ((current[0] == x) && (current[1] == y)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Integer Randomizer with the bound of sizeX
     * @return an randomized integer with the bound of sizeX
     */
    public int integerRandomizer(){
        Random random = new Random();
        return (random.nextInt(sizeX));
    }
}
