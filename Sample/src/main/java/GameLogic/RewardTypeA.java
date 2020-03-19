package GameLogic;

public class RewardTypeA extends Reward {
    private int rewardAmount = 50;
    private  char type = 'A';


    //Constructors
    public RewardTypeA() {super(new int[] {0,0}); }
    public RewardTypeA(int[] position) {super(position); }


    //Getters
    public int getRewardAmount(){
        return this.rewardAmount;
    }
    public char getType() { return type; }

}
