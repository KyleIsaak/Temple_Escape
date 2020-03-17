package GameLogic;

import javax.swing.*;

public class Enemy {
    private int[] position;
    private static int damage = 9999999;

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
    public void setPosition(int[] position) {
        this.position = position;
    }

    public int[] chaseThePlayer(int[] position) {
        int[] chase;
        int step = 1;
        chase = new int[]{0,0};
        double distanceX= position[0]-this.position[0];//enemy - player location;
        double distanceY= position[1]-this.position[1];//enemy - player location;
        //double test= distanceY- distanceX;
        if( Math.abs(distanceY)>Math.abs(distanceX))//distance Y> distance X
        {
            if(this.position[1]<position[1]) {
                chase[0] = 0;
                chase[1] = step;
            }

            else if(this.position[1]>position[1]) {
                chase[0] = 0;
                chase[1] = - step;
            }

            else{
                chase[0]=0;
                chase[1]=0;
            }
        }
        else{
            if(this.position[0]<position[0]) {
                chase[0] = step;
                chase[1] = 0;
            }

            else if(this.position[0]>position[0]){
                chase[0] = -step;
                chase[1] =  0;
            }

            else {
                chase[0] = 0;
                chase[1] = 0;
            }
        }
        return chase;
    }
}
