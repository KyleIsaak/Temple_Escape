package GameLogic;

import javax.swing.*;

public class Enemy {
    private int[] position;
    private static int damage=9999999;


    public Enemy(int[] position) {
        this.position = position;
    }

    public int[] getPosition() {
        return this.position;
    }

    public void setDamage(int damage) {
        this.damage=damage;
    }

    public int getDamage(){
        return this.damage;
    }

    public void move(int[] direction) {
        this.position[0] += direction[0];
        this.position[1] += direction[1];
    }

    public void chaseThePlayer(int[] position,int step) {
        int[] chase;
        chase = new int[]{0,0};
        if( position[0]>this.position[0])
        {
            chase[0]+=step;
        }
        else
        {
            chase[0]+=(-step);
        }
        if(position[1]>this.position[1])
        {
            chase[1]+=step;
        }
        else {
            chase[1] +=(-step);
        }
        this.move(chase);
        return ;
    }



}
