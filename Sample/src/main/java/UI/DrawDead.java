package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.util.Random;


public class DrawDead extends JComponent {

    private String imageSrc;
    private Image imageSource;
    private cellType type;
    private int[] pos;
    private int step;
    private InputStream inputStream;

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
                inputStream = DrawLive.class.getResourceAsStream("/wall.png");
                break;

            case TRAPTYPEA:
                inputStream = DrawLive.class.getResourceAsStream("/spike.png");
                break;

            case TRAPTYPEB:
                inputStream = DrawLive.class.getResourceAsStream("/lava.png");
                break;

            case PATH:
                Random ran = new Random();
                int choice = ran.nextInt(2);
                if (choice == 0){
                    inputStream = DrawLive.class.getResourceAsStream("/path.png");
                } else if (choice == 1){
                    inputStream = DrawLive.class.getResourceAsStream("/path2.png");
                }

                break;
        }
        try {
            imageSource = ImageIO.read(inputStream);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setWallDirection(boolean top, boolean left, boolean down, boolean right){
        if (top && left && down && right){
            inputStream = DrawLive.class.getResourceAsStream("/wall_cross.png");
        } else if (top && left && down){
            inputStream = DrawLive.class.getResourceAsStream("/wall_top_right.png");
        } else if (top && left && right){
            inputStream = DrawLive.class.getResourceAsStream("/wall_horizontal.png");
        } else if (top && right && down){
            inputStream = DrawLive.class.getResourceAsStream("/wall_top_left.png");
        } else if (right && left && down){
            inputStream = DrawLive.class.getResourceAsStream("/wall_cross.png");
        } else if (top && left){
            inputStream = DrawLive.class.getResourceAsStream("/wall_bottom_right.png");
        } else if (top && down){
            inputStream = DrawLive.class.getResourceAsStream("/wall_top.png");
        } else if (top && right){
            inputStream = DrawLive.class.getResourceAsStream("/wall_bottom_left.png");
        } else if (left && down){
            inputStream = DrawLive.class.getResourceAsStream("/wall_top_right.png");
        } else if (left && right){
            inputStream = DrawLive.class.getResourceAsStream("/wall_horizontal.png");
        } else if (right && down){
            inputStream = DrawLive.class.getResourceAsStream("/wall_top_left.png");
        } else if (top){
            inputStream = DrawLive.class.getResourceAsStream("/wall_down.png");
        } else if (left){
            inputStream = DrawLive.class.getResourceAsStream("/wall_right.png");
        } else if (right){
            inputStream = DrawLive.class.getResourceAsStream("/wall_left.png");
        } else if (down){
            inputStream = DrawLive.class.getResourceAsStream("/wall_top.png");
        } else{
            inputStream = DrawLive.class.getResourceAsStream("/wall.png");
        }
        try {
            imageSource = ImageIO.read(inputStream);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void paint (Graphics graphic) {
        super.paintComponent(graphic);
        graphic.drawImage(imageSource, this.pos[0] * step + 10, this.pos[1] * step + 50, this);

    }

}
