package GameLogic;

public class Reward {
    private int[] pos;
    private int rewardAmount;


    //Constructors
    public Reward(){
        this.rewardAmount = 50;
    }

    public Reward(int[] pos){
        this.pos = pos;
    }

    public Reward(int reward){
        this.rewardAmount = reward;
    }

    public Reward(int[] pos, int reward){
        this.pos = pos;
        this.rewardAmount = reward;
    }



    //Getters
    public int[] getPosition(){
        return this.pos;
    }
    public int getRewardAmount(){
        return this.rewardAmount;
    }


    //Setters
    public void setRewardAmount(int reward){
        this.rewardAmount = reward;
    }
    public void setPosition(int[] pos){ this.pos = pos; }


}
