package UI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;


public class Input extends KeyAdapter{
    private int[] direction = new int[] {0, 0};

    private boolean isReleased = false;

    private int UP = KeyEvent.VK_W;
    private int DOWN = KeyEvent.VK_S;
    private int LEFT = KeyEvent.VK_A;
    private int RIGHT = KeyEvent.VK_D;
    private int key;


    public void keyPressed(KeyEvent event) {
        System.out.println("a");
        key = event.getKeyCode();
        changeDirection(key);
    }

    public void keyReleased(KeyEvent event) {
        isReleased = true;
    }



    //TO DO:
    // have a set control function later

    public int[] getDirection(){
        return this.direction;
    }


    private void changeDirection(int key){
        if (isReleased) {
            if (key == UP) {
                direction = new int[]{0, -1};
            } else if (key == DOWN) {
                direction = new int[]{0, 1};
            } else if (key == LEFT) {
                direction = new int[]{-1, 0};
            } else if (key == RIGHT) {
                direction = new int[]{1, 0};
            } else {
                direction = new int[]{0, 0};
            }
        }
        this.isReleased = false;
    }

    public void resetDirection(){
        direction = new int[] {0, 0};
    }

}

