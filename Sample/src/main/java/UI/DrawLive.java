package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;


public class DrawLive extends JComponent {

    private Image sprite;
    private cellType type;
    private int[] pos;
    private int step;

    public enum cellType{
        PLAYER,
        ENEMY,
    }
    public DrawLive(int[] pos, int step, cellType type){
        this.step = step;
        this.pos = pos;
        this.type = type;
        selectSprite();
    }

    private void selectSprite(){
        switch(type){
            case PLAYER:
                sprite = Sprite.player_right();
                break;
            case ENEMY:
                sprite = Sprite.enemy_right();
                break;
        }
    }

    public void setPlayerUP(){
        sprite = Sprite.player_up();
    }
    public void setPlayerLEFT(){sprite = Sprite.player_left(); }
    public void setPlayerRIGHT(){
        sprite = Sprite.player_right();
    }
    public void setPlayerDOWN(){
        sprite = Sprite.player_down();
    }


    public void setNewPosition(int[] pos){
        this.pos = pos;
        repaint();
    }

    public void paint (Graphics graphic) {
        super.paintComponent(graphic);
        graphic.drawImage(sprite, this.pos[0] * step + 10, this.pos[1] * step + 42, this);

    }

}
