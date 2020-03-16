package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;


public class DrawLive extends JComponent {

    private String imageSrc;
    private InputStream inputStream;
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


    public void setNewPosition(int[] pos){
        this.pos = pos;
        try {
            image = ImageIO.read(inputStream);
        } catch (Exception e){
            e.printStackTrace();
        }
        repaint();
    }

    public void paint (Graphics graphic) {
        super.paintComponent(graphic);
        graphic.drawImage(image, this.pos[0] * step + 10, this.pos[1] * step + 42, this);

    }

}
