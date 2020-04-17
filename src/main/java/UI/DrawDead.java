package UI;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * To Draw the object that does not move in the game.
 */
public class DrawDead extends JComponent {
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
     *
     */
    public enum CellType {
        WALL,
        PATH,
        TRAPTYPEA,
        TRAPTYPEB,
        REWARDTYPEA,
        REWARDTYPEB,
        EXIT
    }

    /**
     * color can be set to sprites later on
     * @param pos An x, y position on the board
     * @param step An int that specify size of a block
     * @param type An Celltype that specify what is the object.
     */
    public DrawDead(int[] pos, int step, CellType type) {
        this.step = step;
        this.pos = pos.clone();
        this.type = type;
        selectsprite();
    }

    /**
     * add a picture to the object base on what objects they are.
     */
    private void selectsprite() {
        switch (type) {
            case WALL:
                image = Sprite.gameWall();
                break;

            case TRAPTYPEA:
                image = Sprite.gameSpike();
                break;

            case TRAPTYPEB:
                image = Sprite.gameLava();
                break;

            case PATH:
                Random ran = new Random();
                int choice = ran.nextInt(5);
                if (choice <= 3) {
                    image = Sprite.gamePath();
                } else if (choice == 4) {
                    image = Sprite.gamePath2();
                }
                break;
            case REWARDTYPEA:
                image = Sprite.gameCoin();
                break;

            case REWARDTYPEB:
                image = Sprite.gameKey();
                break;
            case EXIT:
                image = Sprite.gameLock();
                break;
        }
    }

    /**
     * unlock the location graph
     */
    public void setLockUnlocked() {
        image = Sprite.gameUnlock();
        repaint();
    }

    /**
     * add the wall graph base on the direction of the wall
     *
     * @param top:   the wall on top
     * @param left:  the wall on left
     * @param down:  the wall on down
     * @param right: the wall on right
     */
    public void setWallDirection(boolean top, boolean left, boolean down, boolean right) {
        if (top && left && down && right) {
            image = Sprite.wall_cross();
        } else if (top && left && down) {
            image = Sprite.wall_top_right();
        } else if (top && left && right) {
            image = Sprite.wall_horizontal();
        } else if (top && right && down) {
            image = Sprite.wall_top_left();
        } else if (right && left && down) {
            image = Sprite.wall_cross();
        } else if (top && left) {
            image = Sprite.wall_bottom_right();
        } else if (top && down) {
            image = Sprite.wall_top();
        } else if (top && right) {
            image = Sprite.wall_bottom_left();
        } else if (left && down) {
            image = Sprite.wall_top_right();
        } else if (left && right) {
            image = Sprite.wall_horizontal();
        } else if (right && down) {
            image = Sprite.wall_top_left();
        } else if (top) {
            image = Sprite.wall_down();
        } else if (left) {
            image = Sprite.wall_right();
        } else if (right) {
            image = Sprite.wall_left();
        } else if (down) {
            image = Sprite.wall_top();
        } else {
            image = Sprite.wall();
        }
    }

    /**
     * paint the graphic
     *
     * @param graphic: Graphic Extension.
     */
    public void paint(Graphics graphic) {
        super.paintComponent(graphic);
        graphic.drawImage(image, this.pos[0] * step + 10, this.pos[1] * step + 50, this);

    }

    /**
     * get the position
     *
     * @return the object position
     */
    public int[] getPosition() {
        return this.pos.clone();
    }

    /**
     * update the cell if there is change
     *
     * @param type: The object type
     */
    public void updateCell(CellType type) {
        this.type = type;
        selectsprite();
        repaint();

    }
}
