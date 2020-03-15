package UI;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class DrawLive extends JComponent {

    private String imageSrc;
    private Image image;
    private cellType type;
    private int[] pos;
    private int step;

    public enum cellType{
        PLAYER,
        ENEMY,
    }
    //color can be set to sprites later on
    public DrawLive(int[] pos, int step, cellType type){
        this.step = step;
        this.pos = pos;
        this.type = type;
        selectSprite();
    }


    private void selectSprite(){
        switch(type){
            case PLAYER:
                imageSrc = "src/pic/player_right.png";

                break;
//            case ENEMY:
//
//                break;
        }
        image = Toolkit.getDefaultToolkit().getImage(imageSrc);
    }

    public void setPlayerUP(){
        imageSrc = "src/pic/player_up.png";
    }
    public void setPlayerLEFT(){
        imageSrc = "src/pic/player_left.png";
    }
    public void setPlayerRIGHT(){
        imageSrc = "src/pic/player_right.png";
    }
    public void setPlayerDOWN(){
        imageSrc = "src/pic/player_down.png";
    }


    public void setNewPosition(int[] pos){
        this.pos = pos;
        image = Toolkit.getDefaultToolkit().getImage(imageSrc);
        repaint();
    }


    public void paint (Graphics graphic) {
        super.paintComponent(graphic);
        graphic.drawImage(image, this.pos[0] * step + 10, this.pos[1] * step + 2, this);

    }

}
