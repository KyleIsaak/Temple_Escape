package gameLogic;

/**
 * Represent the Exit Door in the game.
 */
public class Exit {
    /**
     * Store exit position.
     */
    private int[] position;
    /**
     * Store exit is lock or not info.
     */
    private boolean isUnlocked;

    /**
     * Default Constructor.
     * isUnlocked set to false.
     */
    public Exit() {
        isUnlocked = false;
    }

    /**
     * Non-Default Constructor.
     *
     * @param position A x, y position that specify the location of exit.
     */
    public Exit(int[] position) {
        this.position = position.clone();
    }

    /**
     * Get the position of the exit in the map.
     *
     * @return the position of the exit in the map.
     */
    public int[] getPosition() {
        return position.clone();
    }

    /**
     * Get whether this exit is unlock.
     *
     * @return true if it is unlocked or vise versa.
     */
    public boolean getIsUnlocked() {
        return isUnlocked;
    }

    /**
     * Set the position of the exit of the game.
     *
     * @param position A x, y position that specify the new position for the exit.
     */
    public void setPosition(int[] position) {
        this.position = position.clone();
    }

    /**
     * Set whether this exit is unlocked or locked.
     *
     * @param isUnlocked A boolean that specify whether the door is locked or unlocked.
     */
    public void setUnlocked(boolean isUnlocked) {
        this.isUnlocked = isUnlocked;
    }

}
