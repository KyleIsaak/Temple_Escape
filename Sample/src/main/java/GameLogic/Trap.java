package GameLogic;
import java.util.Random;

public abstract class Trap {
    private int[] position;

    public Trap(){position = new int[]{0, 0}; }
    public Trap (int[] position) { this.position = position; }
    public int[] getPosition(){ return position; }
    public void setPosition(int[] position) { this.position = position; }
    public abstract int getDamage();
    public abstract char getType();
}
