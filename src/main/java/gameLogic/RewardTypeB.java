package gameLogic;

/**
 * Represents a specific kind of rewards (Coins) in the game.
 * Optional rewards to collect to increase scores.
 */
public class RewardTypeB extends Reward {
    /**
     * Store the reward amount.
     */
    private int rewardAmount = 100;
    /**
     * Store the reward type.
     */
    private char type = 'B';

    /**
     * Default Constructor.
     */
    public RewardTypeB() {
        super(new int[]{0, 0});
    }

    /**
     * Non Default Constructor.
     * @param pos A x,y position for the initial position of the player.
     */
    public RewardTypeB(int[] pos) {
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
     * @return the type of this rewards which is B (Coins).
     */
    public char getType() {
        return type;
    }

}
