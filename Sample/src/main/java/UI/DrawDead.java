package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
import java.util.Random;


public class DrawDead extends JComponent {

    private Image sprite;
    private cellType type;
    private int[] pos;
    private int step;

    public enum cellType{
        WALL,
        PATH,
        TRAPTYPEA,
        TRAPTYPEB,
        REWARDTYPEA,
        REWARDTYPEB,
        EXIT
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
                sprite = Sprite.gameWall();
                break;

            case TRAPTYPEA:
                sprite = Sprite.gameSpike();
                break;

            case TRAPTYPEB:
                sprite = Sprite.gameLava();
                break;

            case PATH:
                Random ran = new Random();
                int choice = ran.nextInt(2);
                if (choice == 0){
                    sprite = Sprite.gamePath();
                } else if (choice == 1){
                    sprite = Sprite.gamePath2();
                }
                break;
            case REWARDTYPEA:
                sprite = Sprite.gameCoin();
                break;

            case REWARDTYPEB:
                sprite = Sprite.gameKey();
                break;
            case EXIT:
                sprite = Sprite.gameLock();
                break;
        }
    }

    public void setLockUnlocked() {
        sprite = Sprite.gameUnlock();
        repaint();
    }

    public void setWallDirection(boolean top, boolean left, boolean down, boolean right){
        if (top && left && down && right){
            sprite = Sprite.wall_cross();
        } else if (top && left && down){
            sprite = Sprite.wall_top_right();
        } else if (top && left && right){
            sprite = Sprite.wall_horizontal();
        } else if (top && right && down){
            sprite = Sprite.wall_top_left();
        } else if (right && left && down){
            sprite = Sprite.wall_cross();
        } else if (top && left){
            sprite = Sprite.wall_bottom_right();
        } else if (top && down){
            sprite = Sprite.wall_top();
        } else if (top && right){
            sprite = Sprite.wall_bottom_left();
        } else if (left && down){
            sprite = Sprite.wall_top_right();
        } else if (left && right){
            sprite = Sprite.wall_horizontal();
        } else if (right && down){
            sprite = Sprite.wall_top_left();
        } else if (top){
            sprite = Sprite.wall_down();
        } else if (left){
            sprite = Sprite.wall_right();
        } else if (right){
            sprite = Sprite.wall_left();
        } else if (down){
            sprite = Sprite.wall_top();
        } else{
            sprite = Sprite.wall();
        }
    }

    public void paint (Graphics graphic) {
        super.paintComponent(graphic);
        graphic.drawImage(sprite, this.pos[0] * step + 10, this.pos[1] * step + 50, this);

    }

    public int[] getPosition(){
        return this.pos;
    }

    public void updateCell(cellType type){
        this.type = type;
        selectSprite();
        repaint();

    }
}
