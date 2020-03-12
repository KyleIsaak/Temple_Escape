package UI;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class DrawCell extends JComponent {

    private String imageSrc;
    private Image image;
    private cellType type;
    private int[] pos;
    private int step;

    public enum cellType{
        PLAYER,
        ENEMY,
        WALL,
        PATH
    }
    //color can be set to sprites later on
    public DrawCell(int[] pos, int step, cellType type){
        this.step = step;
        this.pos = pos;
        this.type = type;
        selectSprite();
    }

    private void selectSprite(){
        switch(type){
            case PLAYER:
                imageSrc = "src/pic/player.png";

                break;
//            case ENEMY:
//
//                break;
            case WALL:

                break;
            case PATH:
                imageSrc = "src/pic/path.png";
                break;
        }
        image = Toolkit.getDefaultToolkit().getImage(imageSrc);
    }

    public void setWallDirection(boolean top, boolean left, boolean down, boolean right){
        if (top && left && down && right){
            imageSrc = "src/pic/wall_cross.png";
        } else if (top && left && down){
            imageSrc = "src/pic/wall_no_right.png";
        } else if (top && left && right){
            imageSrc = "src/pic/wall_no_down.png";
        } else if (top && right && down){
            imageSrc = "src/pic/wall_no_left.png";
        } else if (right && left && down){
            imageSrc = "src/pic/wall_no_up.png";
        } else if (top && left){
            imageSrc = "src/pic/wall_bottom_right.png";
        } else if (top && down){
            imageSrc = "src/pic/wall_vertical.png";
        } else if (top && right){
            imageSrc = "src/pic/wall_bottom_left.png";
        } else if (left && down){
            imageSrc = "src/pic/wall_top_right.png";
        } else if (left && right){
            imageSrc = "src/pic/wall_horizontal.png";
        } else if (right && down){
            imageSrc = "src/pic/wall_top_left.png";
        } else if (top){
            imageSrc = "src/pic/wall_broken_down.png";
        } else if (left){
            imageSrc = "src/pic/wall_broken_right.png";
        } else if (right){
            imageSrc = "src/pic/wall_broken_left.png";
        } else if (down){
            imageSrc = "src/pic/wall_broken_up.png";
        } else{
            imageSrc = "src/pic/wall.png";
        }
        image = Toolkit.getDefaultToolkit().getImage(imageSrc);
    }
    public void setNewPosition(int[] pos){
        this.pos = pos;
        repaint();
    }

    public void paint (Graphics graphic) {
        super.paintComponent(graphic);
        graphic.drawImage(image, this.pos[0] * step, this.pos[1] * step, this);

    }

}