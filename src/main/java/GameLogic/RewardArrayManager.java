package GameLogic;
import java.util.ArrayList;
import java.util.Random;

public class RewardArrayManager {
    private ArrayList<Reward> rewardArrayManager;
    private final LevelGenerator generator;
    private final TrapArrayManager trapArrayManager;
    private int sizeX;

    /**
     * Non Default Constructor
     * @param level An integer defining the difficulty selected.
     * @param generator A constant level generator integer used to provide insight details of the walls of the board
     * @param sizeX An integer of the size of X axis of the board
     * @param trapArrayManager A constant trap Array Manager used to provide insight of traps on the board)
     */
    public RewardArrayManager(int level, LevelGenerator generator, int sizeX,  TrapArrayManager trapArrayManager){
        this.sizeX = sizeX;
        this.generator = generator;
        rewardArrayManager = new ArrayList<Reward>();
        this.trapArrayManager = trapArrayManager;
        rewardGenerator(level);
    }

    /**
     * Get this class's Reward Array Manager.
     * @return this class's Reward Array Manager.
     */
    public ArrayList<Reward> getRewardArray() { return rewardArrayManager; }

    /**
     * Check whether that location on the map contains a reward.
     * @param x An integer for the x position.
     * @param y An integer for the y position
     * @return true when it contains a reward and vise versa.
     */
    public boolean isReward(int x, int y){
        for (int i = 0; i < rewardArrayManager.size(); i++) {
            int[] current = rewardArrayManager.get(i).getPosition();
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

    /**
     * Create Specific Rewards in the gameplay area depending on the difficulty selected.
     * @param difficultyLevel An integer of the difficulty selected.
     */
    public void rewardGenerator (int difficultyLevel){
        for (int i = 0; i < difficultyLevel*2; i++) {   //Coins
            rewardTypeGenerator('A');
        }
        for (int i = 0; i < difficultyLevel; i++){      //Keys
            rewardTypeGenerator('B');
        }
    }

    /**
     * rewardGenerator's Helper function: Facilitates in creating the specific rewards (Coins and Keys)
     * Implements the factory method design pattern.
     * @param type A character that specify the type of specific reward selected.
     */
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

    /**
     * rewardGenerator's Helper Function: Facilitates in the randomizing the location of the rewards
     * @param rewardObject Specific Reward Object
     */
    public void rewardLocationRandomizer (Reward rewardObject){
        int x = integerRandomizer();
        int y = integerRandomizer();

        while ((generator.isWall(x,y)) || (isReward(x,y) || (trapArrayManager.isTrap(x,y)))){
            x = integerRandomizer();
            y = integerRandomizer();
        }

        rewardObject.setPosition(new int [] {x, y});
        //cellStatusManager.add(new int [] {x, y});
        rewardArrayManager.add(rewardObject);
    }

    /**
     * Returns the index of the Reward in the Reward Array Manager which is located in that position
     *  @param x An integer for the x position.
     *  @param y An integer for the y position.
     *  @return the index of the Reward of that position in the Reward Array Manager
     */
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