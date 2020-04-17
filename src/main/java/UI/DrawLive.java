package UI;

import javax.swing.*;
import java.awt.*;

/**
 * Draw the moving object in the game.
 */
public class DrawLive extends JComponent {
    /**
     * the object image info.
     */
    private Image image;
    /**
     * store the object type info.
     */
    private CellType type;
    /**
     * store the object position.
     */
    private int[] pos;
    /**
     * store the object step info.
     */
    private int step;

    /**
     * There are two moving object in the game: player and enemies.
     */
    public enum CellType {
        PLAYER,
        ENEMY,
    }

    /**
     * sprites can be set to sprites later on.
     * @param pos An x, y position on the board
     * @param step An int that specify size of a block
     * @param type An Celltype that specify what is the object.
     */
    public DrawLive(int[] pos, int step, CellType type) {
        this.step = step;
        this.pos = pos.clone();
        this.type = type;
        selectSprite();
    }

    /**
     * add the graph to the object base on the type of that object.
     */
    private void selectSprite() {
        switch (type) {
            case PLAYER:
                image = Sprite.player_right();
                break;
            case ENEMY:
                image = Sprite.enemy_right();
                break;
        }
    }

    /**
     * set the graph of the player based on its direction.
     */
    public void setPlayerUP() {
        image = Sprite.player_up();
    }

    public void setPlayerLEFT() {
        image = Sprite.player_left();
    }

    public void setPlayerRIGHT() {
        image = Sprite.player_right();
    }

    public void setPlayerDOWN() {
        image = Sprite.player_down();
    }

    /**
     * update the graph position when the object move.
     *
     * @param pos: the new location of the object.
     */
    public void setNewPosition(int[] pos) {
        this.pos = pos.clone();
        System.out.println(image.getSource());
        repaint();
    }

    /**
     * Paint the graphic.
     *
     * @param graphic: graphic of the object.
     */
    public void paint(Graphics graphic) {
        super.paintComponent(graphic);
        graphic.drawImage(image, this.pos[0] * step + 10, this.pos[1] * step + 42, this);

    }

}
