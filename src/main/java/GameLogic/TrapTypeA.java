package GameLogic;

/**
 * Represents a specific kind of Traps (Spikes) in the game.
 * Player lose points when stepped on traps.
 */
public class TrapTypeA extends Trap {
    private int damage = 150;
    private  char type = 'A';

    /**
     * Default Constructor
     */
    public TrapTypeA() { super(new int[] {0,0}); }

    /**
     * Get the damage of this trap.
     * @return the damage of this trap.
     */
    public int getDamage() { return damage; }

    /**
     * Get the type of this Trap which is A (Spikes)
     * @return the type of this Trap which is A (Spikes)
     */
    public char getType() { return type; }

}
