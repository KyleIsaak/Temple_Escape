package gameLogic;

/**
 * Abstract class for the traps in the game play area.
 * Uses Factory Method for different kind of traps.
 */
public abstract class Trap {
    /**
     * Store trap position.
     */
    private int[] position;

    /**
     * Default constructor.
     */

    public Trap() {
        position = new int[]{0, 0};
    }

    /**
     * Non Default Constructor.
     *
     * @param position A x,y position for the location of this Trap.
     */
    public Trap(int[] position) {
        this.position = position.clone();
    }

    /**
     * Get the current position of this Trap.
     *
     * @return the current position of this Trap.
     */
    public int[] getPosition() {
        return position.clone();
    }

    /**
     * Set the current position of this Trap object.
     *
     * @param position A x,y position for the new location of this Trap.
     */
    public void setPosition(int[] position) {
        this.position = position.clone();
    }

    /**
     * Get the damage of this trap.
     *
     * @return the damage of this trap.
     */
    public abstract int getDamage();

    /**
     * Get the type of this Trap.
     *
     * @return the type of this Trap.
     */
    public abstract char getType();
}
