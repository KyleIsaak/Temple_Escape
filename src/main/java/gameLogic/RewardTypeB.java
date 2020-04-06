package gameLogic;

/**
 * Represents a specific kind of rewards (Coins) in the game
 * Optional rewards to collect to increase scores.
 */
public class RewardTypeB extends Reward {
    private int rewardAmount = 100;
    private  char type = 'B';

    /**
     * Default Constructor
     */
    public RewardTypeB() {super(new int[] {0,0}); }

    /**
     * Non Default Constructor
     */
    public RewardTypeB(int[] pos) {
        this.setPosition(pos);
    }

    /**
     * Get the value of this reward.
     * @return the value of this reward.
     */
    public int getRewardAmount(){ return this.rewardAmount; }

    /**
     * Get the type of this Rewards
     * @return the type of this rewards which is B (Coins).
     */
    public char getType() { return type; }

}
