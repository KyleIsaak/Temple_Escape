package GameLogic;

/**
 * Abstract class for the rewards in the game play area.
 */
public abstract class Reward {
    private int[] position;

    /**
     * Default constructor
     */
    public Reward(){ position = new int[] {0, 0}; }

    /**
     * Non-Default Constructor
     * @param position A x,y position for the location of this Reward.
     */
    public Reward (int[] position) { this.position = position; }

    /**
     * Set the current position of this Reward object.
     * @param position A x,y position for the new location of this Reward.
     */
    public void setPosition(int[] position) { this.position = position; }

    /**
     * Get the current position of this reward.
     * @return the current position of this reward.
     */
    public int[] getPosition(){ return position; }

    /**
     * Get the value of this Reward.
     * @return the value of this Reward.
     */
    public abstract int getRewardAmount();

    /**
     * Get the type of this Reward.
     * @return the type of this Reward.
     */
    public abstract char getType();
}
