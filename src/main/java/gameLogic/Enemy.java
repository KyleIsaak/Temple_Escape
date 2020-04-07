package gameLogic;

/**
 * Represents the enemy that chases the player in the game.
 */
public class Enemy {
    /**
     * Store Enemy position
     */
    private int[] position;

    /**
     * Non default constructor of Enemy Class
     *
     * @param position A x,y position on the Map
     */
    public Enemy(int[] position) {
        this.position = position.clone();
    }

    /**
     * Set the position of this enemy class
     *
     * @param position A x,y position in the map for the enemy's current position to set as.
     */
    public void setPosition(int[] position) {
        this.position = position.clone();
    }

    /**
     * Get the current position of the enemy
     *
     * @return this enemy object's current position
     */
    public int[] getPosition() {
        return this.position.clone();
    }

    /**
     * Enemy Move Helper Function: Ensure enemy is only moving one step at a time.
     *
     * @param direction A x,y position on the map that defines the new location that the enemy is moving to.
     * @return true if the enemy is moving by one step from current position vise versa.
     */
    private boolean nextTo(int[] direction) {
        return (direction[0] == position[0] + 1 && direction[1] == position[1])
                || (direction[0] == position[0] - 1 && direction[1] == position[1])
                || (direction[0] == position[0] && direction[1] == position[1] + 1)
                || (direction[0] == position[0] && direction[1] == position[1] - 1);
    }

    /**
     * Move the enemy to the desired position
     *
     * @param direction An x,y position in the map
     */
    public void move(int[] direction) {
        if (nextTo(direction)) {
            this.position[0] = direction[0];
            this.position[1] = direction[1];
        } else {
            throw new UnsupportedOperationException("ERROR: Enemy not moving by one step. PLease check enemy movement.");
        }
    }
}
