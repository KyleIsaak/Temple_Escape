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
                Random ran = new Random();
                int choice = ran.nextInt(2);
                if (choice == 0){
                    imageSrc = "src/pic/wall.png";
                } else if (choice == 1){
                    imageSrc = "src/pic/wall2.png";
                }

                break;
            case PATH:
                imageSrc = "src/pic/path.png";
                break;
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
