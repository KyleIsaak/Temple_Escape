package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

/**
 * Draw the moving object in the game
 */
public cl
public class DrawLive extends JComponent {

    private String imageSrc;
    private InputStream inputStream;
    private Image image;
    private cellType type;
    private int[] pos;
    private int step;
    /**
     * There are two moving object in the game: player and enemies
     */
    public enum cellType{
        PLAYER,
        ENEMY,
    }
    /**
     * color can be set to sprites later on
     */
    public DrawLive(int[] pos, int step, cellType type){
        this.step = step;
        this.pos = pos;
        this.type = type;
        selectSprite();
    }

    /**
     * add the graph to the object base on the type of that object
     */
    private void selectSprite(){
        switch(type){
            case PLAYER:
                //is = DrawLive.class.getResourceAsStream(player_right.png";
                inputStream = DrawLive.class.getResourceAsStream("/player_right.png");
//                is = DrawLive.class.getResourceAsStream(player_right.png";

                System.out.println(imageSrc);
                break;
            case ENEMY:
                inputStream = DrawLive.class.getResourceAsStream("/enemy_right.png");
                break;
        }
//        image = Toolkit.getDefaultToolkit().getImage(imageSrc);
        try {
            image = ImageIO.read(inputStream);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
//
//    public void setPlayerUP(){ is = DrawLive.class.getResourceAsStream(player_up.png";
//    }
//    public void setPlayerLEFT(){
//        is = DrawLive.class.getResourceAsStream(player_left.png";
//    }
//    public void setPlayerRIGHT(){
//        is = DrawLive.class.getResourceAsStream(player_right.png";
//    }
//    public void setPlayerDOWN(){
//        is = DrawLive.class.getResourceAsStream(player_down.png";
//    }
    /**
     * set the graph of the player base on its direction
     */
    public void setPlayerUP(){
        inputStream = DrawLive.class.getResourceAsStream("/player_up.png");
    }
    public void setPlayerLEFT(){
        inputStream = DrawLive.class.getResourceAsStream("/player_left.png");
    }
    public void setPlayerRIGHT(){
        inputStream = DrawLive.class.getResourceAsStream("/player_right.png");
    }
    public void setPlayerDOWN(){
        inputStream = DrawLive.class.getResourceAsStream("/player_down.png");
    }

    /**
     * update the graph position when the object move
     * @param pos: the new location of
     */
    public void setNewPosition(int[] pos){
        this.pos = pos;
        try {
            image = ImageIO.read(inputStream);
        } catch (Exception e){
            e.printStackTrace();
        }
        repaint();
    }
    /**
     * Paint the graphic
     * @param graphic: graphic of the object
     */
    public void paint (Graphics graphic) {
        super.paintComponent(graphic);
        graphic.drawImage(image, this.pos[0] * step + 10, this.pos[1] * step + 42, this);

    }

}
