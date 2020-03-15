package GameLogic;
import java.util.Random;

public abstract class Reward {
    private int[] position;

    //Default constructor
    public Reward(){position = new int[] {0, 0}; }
    //Constructor with pos input
    public Reward (int[] position) { this.position = position; }

    //Setters
    public void setPosition(int[] position) { this.position = position; }

    //Getters
    public int[] getPosition(){ return position; }
    public abstract int getRewardAmount();
    public abstract char getType();
}
