package GameLogic;

public class TrapTypeB extends Trap {
    private int damage = 20;
    private  char type = 'B';

    public TrapTypeB() {super(new int[] {0,0}); }
    public TrapTypeB(int[] position) {super(position); }
    public int getDamage() { return damage; }
    public char getType() { return type; }

}
