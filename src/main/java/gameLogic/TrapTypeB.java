package gameLogic;

/**
 * Represents a specific kind of Traps (Lava Pits) in the game.
 * Player lose points when stepped on traps.
 */
public class TrapTypeB extends Trap {
    /**
     * Store the trap damage value.
     */
    private int damage = 200;
    /**
     * Store the trap type.
     */
    private char type = 'B';

    /**
     * Default Constructor.
     */
    public TrapTypeB() {
        super(new int[]{0, 0});
    }

    /**
     * Non Default Constructor.
     * @param pos A x,y position for the initial position of the player.
     */
    public TrapTypeB(int[] pos) {
        this.setPosition(pos);
    }

    /**
     * Get the damage of this trap.
     *
     * @return the damage of this trap.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Get the type of this Trap which is A (Lava Pit).
     *
     * @return the type of this Trap which is A (Lava Pit).
     */
    public char getType() {
        return type;
    }

}
