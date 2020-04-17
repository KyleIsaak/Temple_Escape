package gameLogic;

/**
 * Represents a specific kind of Traps (Spikes) in the game.
 * Player lose points when stepped on traps.
 */
public class TrapTypeA extends Trap {
    /**
     * store damage value.
     */
    private int damage = 150;
    /**
     * Define the Trap type.
     */
    private char type = 'A';

    /**
     * Default Constructor.
     */
    public TrapTypeA() {
        super(new int[]{0, 0});
    }

    /**
     * Non Default Constructor.
     * @param pos A x,y position for the initial position of the player.
     */
    public TrapTypeA(int[] pos) {
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
     * Get the type of this Trap which is A (Spikes).
     *
     * @return the type of this Trap which is A (Spikes).
     */
    public char getType() {
        return type;
    }

}
