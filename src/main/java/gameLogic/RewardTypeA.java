package gameLogic;

/**
 * Represents a specific kind of reward (Keys) in the game
 * Player is required to collect all of these rewards before
 * they can proceed to the next level.
 */
public class RewardTypeA extends Reward {
    private final int rewardAmount = 50;
    private  char type = 'A';

    /**
     * Default Constructor.
     */
    public RewardTypeA() {
        super(new int[] {0, 0});
    }

    /**
     * Non Default Constructor.
     * @param pos An x,y position in the map where the reward will be created.
     */
    public RewardTypeA(final int[] pos) {
        this.setPosition(pos);
    }

    /**
     * Get the value of this reward.
     * @return the value of this reward.
     */
    public int getRewardAmount() {
        return this.rewardAmount;
    }

    /**
     * Get the type of this Reward.
     * @return the type of this rewards which is A (Keys).
     */
    public char getType() {
        return type;
    }

}
