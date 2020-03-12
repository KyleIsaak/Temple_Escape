package GameLogic;

public class TrapTypeA extends Trap {
    private int damage = 10;
    private  char type = 'A';

    public TrapTypeA(int[] position) {super(position); }
    public int getDamage() { return damage; }
    public int getType() { return type; }

}
