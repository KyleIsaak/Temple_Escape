package UI;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class DrawDead extends JComponent {

    private String imageSrc;
    private Image image;
    private cellType type;
    private int[] pos;
    private int step;

    public enum cellType{
        WALL,
        PATH,
        TRAPTYPEA,
        TRAPTYPEB
    }
    //color can be set to sprites later on
    public DrawDead(int[] pos, int step, cellType type){
        this.step = step;
        this.pos = pos;
        this.type = type;
        selectSprite();
    }


    private void selectSprite(){
        switch(type){
            case WALL:

                break;

            case TRAPTYPEA:
                imageSrc = "src/pic/spike.png";
                break;

            case TRAPTYPEB:
                imageSrc = "src/pic/lava.png";
                break;

            case PATH:
                Random ran = new Random();
                int choice = ran.nextInt(2);
                if (choice == 0){
                    imageSrc = "src/pic/path.png";
                } else if (choice == 1){
                    imageSrc = "src/pic/path2.png";
                }

                break;
        }
        image = Toolkit.getDefaultToolkit().getImage(imageSrc);
    }

    public void setWallDirection(boolean top, boolean left, boolean down, boolean right){
        if (top && left && down && right){
            imageSrc = "src/pic/wall_cross.png";
        } else if (top && left && down){
            imageSrc = "src/pic/wall_top_right.png";
        } else if (top && left && right){
            imageSrc = "src/pic/wall_horizontal.png";
        } else if (top && right && down){
            imageSrc = "src/pic/wall_top_left.png";
        } else if (right && left && down){
            imageSrc = "src/pic/wall_cross.png";
        } else if (top && left){
            imageSrc = "src/pic/wall_bottom_right.png";
        } else if (top && down){
            imageSrc = "src/pic/wall_top.png";
        } else if (top && right){
            imageSrc = "src/pic/wall_bottom_left.png";
        } else if (left && down){
            imageSrc = "src/pic/wall_top_right.png";
        } else if (left && right){
            imageSrc = "src/pic/wall_horizontal.png";
        } else if (right && down){
            imageSrc = "src/pic/wall_top_left.png";
        } else if (top){
            imageSrc = "src/pic/wall_down.png";
        } else if (left){
            imageSrc = "src/pic/wall_right.png";
        } else if (right){
            imageSrc = "src/pic/wall_left.png";
        } else if (down){
            imageSrc = "src/pic/wall_top.png";
        } else{
            imageSrc = "src/pic/wall.png";
        }
        image = Toolkit.getDefaultToolkit().getImage(imageSrc);
    }

    public void paint (Graphics graphic) {
        super.paintComponent(graphic);
        graphic.drawImage(image, this.pos[0] * step + 10, this.pos[1] * step + 10, this);

    }

}
