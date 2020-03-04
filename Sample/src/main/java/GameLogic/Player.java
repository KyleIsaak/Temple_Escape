package GameLogic;

public class Player {
    private int[] pos;

    public Player(int[] pos){
        this.pos = pos;
    }

    public int[] getPosition(){
        return this.pos;
    }

    public void move(int[] direction){
        pos[0] += direction[0];
        pos[1] += direction[1];
    }
}
