package UI;

import javax.swing.*;
import java.awt.*;

/**
 * Draw the moving object in the game
 */
public class DrawLive extends JComponent {
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
                image = Sprite.player_right();
                break;
            case ENEMY:
                image = Sprite.enemy_right();
                break;
        }
    }

    /**
     * set the graph of the player based on its direction
     */
    public void setPlayerUP(){
        image = Sprite.player_up();
    }
    public void setPlayerLEFT(){
        image = Sprite.player_left(); }
    public void setPlayerRIGHT(){
        image = Sprite.player_right();
    }
    public void setPlayerDOWN(){
        image = Sprite.player_down();
    }

    /**
     * update the graph position when the object move
     * @param pos: the new location of
     */
    public void setNewPosition(int[] pos){
        this.pos = pos;
        System.out.println(image.getSource());
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
