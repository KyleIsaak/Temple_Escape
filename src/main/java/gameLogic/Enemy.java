package gameLogic;

/**
 * Represents the enemy that chases the player in the game.
 */
public class Enemy {
    private int[] position;

    /**
     * Non default constructor of Enemy Class
     *
     * @param position A x,y position on the Map
     */
    public Enemy(int[] position) {
        this.position = position;
    }

    /**
     * Set the position of this enemy class
     * @param position A x,y position in the map for the enemy's current position to set as.
     */
    public void setPosition(int[] position) {
        this.position = position;
    }

    /**
     * Get the current position of the enemy
     *
     * @return this enemy object's current position
     */
    public int[] getPosition() {
        return this.position;
    }

    /**
     * Move the enemy to the desired position
     *
     * @param direction An x,y position in the map
     */
    public void move(int[] direction) {
        this.position[0] = direction[0];
        this.position[1] = direction[1];
    }
}
