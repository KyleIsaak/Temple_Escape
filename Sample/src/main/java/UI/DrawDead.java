package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.util.Random;

/**
 * To Draw the object that does not move in the game
 */
public class DrawDead extends JComponent {

    private Image imageSource;
    private cellType type;
    private int[] pos;
    private int step;
    private InputStream inputStream;

    public enum cellType{
        WALL,
        PATH,
        TRAPTYPEA,
        TRAPTYPEB,
        REWARDTYPEA,
        REWARDTYPEB,
        EXIT
    }
    /**
     *   color can be set to sprites later on
     */
    public DrawDead(int[] pos, int step, cellType type){
        this.step = step;
        this.pos = pos;
        this.type = type;
        selectSprite();
    }
    /**
     * add a picture to the object base on what objects they are.
     */
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
            case REWARDTYPEA:
                inputStream = DrawLive.class.getResourceAsStream("/coin.png");
                break;

            case REWARDTYPEB:
                inputStream = DrawLive.class.getResourceAsStream("/key.png");
                break;
            case EXIT:
                inputStream = DrawLive.class.getResourceAsStream("/Lock.png");
                break;
        }
        try {
            imageSource = ImageIO.read(inputStream);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * unlock the location graph
     */
    public void setLockUnlocked() {
        inputStream = DrawLive.class.getResourceAsStream("/Unlock.png");
        try {
            imageSource = ImageIO.read(inputStream);
        } catch (Exception e){
            e.printStackTrace();
        }
        repaint();
    }
    /**
     * add the wall graph base on the direction of the wall
     * @param top: the wall on top
     * @param left: the wall on left
     * @param down: the wall on down
     * @param right: the wall on right
     */
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
    /**
     * paint the graphic
     * @param graphic
     */
    public void paint (Graphics graphic) {
        super.paintComponent(graphic);
        graphic.drawImage(imageSource, this.pos[0] * step + 10, this.pos[1] * step + 50, this);

    }
    /**
     * get the position
     * @return the object position
     */
    public int[] getPosition(){
        return this.pos;
    }
    /**
     * update the cell if there is change
     * @param type: The object type
     */
    public void updateCell(cellType type){
        this.type = type;
        selectSprite();
        try {
            imageSource = ImageIO.read(inputStream);
        } catch (Exception e){
            e.printStackTrace();
        }
        repaint();

    }
}
