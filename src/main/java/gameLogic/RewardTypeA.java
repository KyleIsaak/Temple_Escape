package gameLogic;

/**
 * Represents a specific kind of rewards (Keys) in the game.
 * Player is required to collect all this rewards before can proceed to
 * next level.
 */
public class RewardTypeA extends Reward {
    /**
     * Store the reward amount.
     */
    private int rewardAmount = 50;
    /**
     * Store the reward type.
     */
    private char type = 'A';

    /**
     * Default Constructor.
     */
    public RewardTypeA() {
        super(new int[]{0, 0});
    }

    /**
     * Non Default Constructor.
     * @param pos A x,y position for the initial position of the player.
     */
    public RewardTypeA(int[] pos) {

        this.setPosition(pos);
    }

    /**
     * Get the value of this reward.
     *
     * @return the value of this reward.
     */
    public int getRewardAmount() {
        return this.rewardAmount;
    }

    /**
     * Get the type of this Rewards.
     *
     * @return the type of this rewards which is A (Keys).
     */
    public char getType() {
        return type;
    }

}
