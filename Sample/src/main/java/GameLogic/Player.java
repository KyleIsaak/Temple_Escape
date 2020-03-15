package GameLogic;

public class Player {
    private int[] pos;

    public Player(int[] pos){
        this.pos = pos;
    }

    public int[] getPosition(){
        //System.out.println("Player x = " + this.pos[0] + " Player y = " + this.pos[1]);
        return this.pos;
    }

    public void moveUp(){
        pos[1] -= 1;
    }

    public void moveLeft(){
        pos[0] -= 1;
    }

    public void moveDown(){
        pos[1] += 1;
    }

    public void moveRight(){
        pos[0] += 1;
    }
}
