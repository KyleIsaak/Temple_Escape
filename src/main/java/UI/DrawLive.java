package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

/**
 * Draw the moving object in the game
 */
public class DrawLive extends JComponent {

    private Image sprite;
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
     * sprites can be set to sprites later on
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
                sprite = Sprite.player_right();
                break;
            case ENEMY:
                sprite = Sprite.enemy_right();
                break;
        }
    }

    /**
     * set the graph of the player based on its direction
     */
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

    /**
     * update the graph position when the object move
     * @param pos: the new location of
     */
    public void setNewPosition(int[] pos){
        this.pos = pos;
        repaint();
    }

    /**
     * Paint the graphic
     * @param graphic: graphic of the object
     */
    public void paint (Graphics graphic) {
        super.paintComponent(graphic);
        graphic.drawImage(sprite, this.pos[0] * step + 10, this.pos[1] * step + 42, this);

    }

}
