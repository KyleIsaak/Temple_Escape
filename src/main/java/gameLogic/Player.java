package gameLogic;

/**
 * Represent the main character of the game.
 */
public class Player {
    /**
     * Store the player position info.
     */
    private int[] pos;

    /**
     * Non-default constructor.
     *
     * @param pos A x,y position for the initial position of the player.
     */
    public Player(final int[] pos) {
        this.pos = pos.clone();
    }

    /**
     * Get the current position of the player.
     *
     * @return the current position of the player.
     */
    public int[] getPosition() {
        return this.pos.clone();
    }

    /**
     * Move the player up by one step.
     */
    public void moveUp() {
        pos[1] -= 1;
    }

    /**
     * Move the player left by one step.
     */
    public void moveLeft() {
        pos[0] -= 1;
    }

    /**
     * Move the player down by one step.
     */
    public void moveDown() {
        pos[1] += 1;
    }

    /**
     * Move the player right by one step.
     */
    public void moveRight() {
        pos[0] += 1;
    }
}
