package UI;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class Input{
    private int[] direction = new int[] {0, 0};

    private char UP = 'w';
    private char LEFT = 'a';
    private char DOWN = 's';
    private char RIGHT = 'd';

    KeyListener keyInput = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent event) {
        }

        @Override
        public void keyPressed(KeyEvent event) {
        }

        @Override
        public void keyReleased(KeyEvent event) {
            changeDirection(event);
        }
    };


    //TO DO:
    // have a set control function later

    public int[] getDirection(JFrame frame){
        frame.addKeyListener(keyInput);
        return this.direction;
    }

    private void changeDirection(KeyEvent event){
        if (event.getKeyChar() == UP) {
            direction = new int[]{0, -1};
        } else if (event.getKeyChar() == DOWN) {
            direction = new int[]{0, 1};
        } else if (event.getKeyChar() == LEFT) {
            direction = new int[]{-1, 0};
        } else if (event.getKeyChar() == RIGHT){
            direction = new int[]{1, 0};
        } else {
            direction = new int[]{0, 0};
        }
    }

    public void resetDirection(){
        direction = new int[] {0, 0};
    }
}
