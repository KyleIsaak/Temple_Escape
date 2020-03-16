package GameLogic;

public class TrapTypeA extends Trap {
    private int damage = 10;
    private  char type = 'A';

    public TrapTypeA() {super(new int[] {0,0}); }

    public int getDamage() { return damage; }
    public char getType() { return type; }

}
