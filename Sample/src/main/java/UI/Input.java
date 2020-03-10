//package UI;
//
//import javax.swing.*;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//
//
//public class Input{
//    private int[] direction = new int[] {0, 0};
//
//    private boolean isReleased = false;
//
//    private char UP = 'w';
//    private char LEFT = 'a';
//    private char DOWN = 's';
//    private char RIGHT = 'd';
//
//    KeyListener keyInput = new KeyListener() {
//        @Override
//        public void keyTyped(KeyEvent event) {
//        }
//
//        @Override
//        public void keyPressed(KeyEvent event) {
//            changeDirection(event);
//        }
//
//        @Override
//        public void keyReleased(KeyEvent event) {
//            released();
//        }
//    };
//
//
//    //TO DO:
//    // have a set control function later
//
//    public int[] getDirection(JFrame frame){
//        frame.addKeyListener(keyInput);
//        return this.direction;
//    }
//
//    private void released(){
//        this.isReleased = true;
//    }
//
//    private void changeDirection(KeyEvent event){
//        if (isReleased) {
//            if (event.getKeyChar() == UP) {
//                direction = new int[]{0, -1};
//            } else if (event.getKeyChar() == DOWN) {
//                direction = new int[]{0, 1};
//            } else if (event.getKeyChar() == LEFT) {
//                direction = new int[]{-1, 0};
//            } else if (event.getKeyChar() == RIGHT) {
//                direction = new int[]{1, 0};
//            } else {
//                direction = new int[]{0, 0};
//            }
//        }
//        this.isReleased = false;
//    }
//
//    public void resetDirection(){
//        direction = new int[] {0, 0};
//    }
//}

package UI;

import javax.swing.*;

public class Input extends JComponent {
    KeyStroke UP = KeyStroke.getKeyStroke('w');
    KeyStroke DOWN = KeyStroke.getKeyStroke('s');
    KeyStroke LEFT = KeyStroke.getKeyStroke('a');
    KeyStroke RIGHT = KeyStroke.getKeyStroke('d');

    public int[] getDirection(){
        this.getInputMap
    }
        }