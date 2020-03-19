package GameLogic;

public class RewardTypeB extends Reward {
    private int rewardAmount = 100;
    private  char type = 'B';


    //Constructors
    public RewardTypeB() {super(new int[] {0,0}); }
    public RewardTypeB(int[] position) {super(position); }


    //Getters
    public int getRewardAmount(){
        return this.rewardAmount;
    }
    public char getType() { return type; }

}
