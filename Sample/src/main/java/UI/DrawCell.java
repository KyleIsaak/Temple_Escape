package UI;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class DrawCell extends JComponent {

    private String imageSrc;
    private Image image;
    private cellType type;
    private int[] pos;
    private int step;

    public enum cellType{
        PLAYER,
        ENEMY,
        WALL,
        PATH,
        TRAPTYPEA,
        TRAPTYPEB,
        REWARDTYPEA,
        REWARDTYPEB,
        EXIT
    }

    public DrawCell(int[] pos, int step, cellType type){
        this.step = step;
        this.pos = pos;
        this.type = type;
        selectSprite();
    }


    private void selectSprite(){
        switch(type){
            case PLAYER:
                imageSrc = "src/pic/player.png";

                break;
            case ENEMY:
                imageSrc = "src/pic/enemy.png";
                break;

            case WALL:
                Random ran = new Random();
                int choice = ran.nextInt(2);
                if (choice == 0){
                    imageSrc = "src/pic/wall.png";
                } else if (choice == 1){
                    imageSrc = "src/pic/wall2.png";
                }
                break;

            case TRAPTYPEA:
                imageSrc = "src/pic/Spikes.png";
                break;

            case TRAPTYPEB:
                imageSrc = "src/pic/Lava.png";
                break;

            case REWARDTYPEA:
                imageSrc = "src/pic/coin.png";
                break;

            case REWARDTYPEB:
                imageSrc = "src/pic/key.png";
                break;

            case PATH:
                imageSrc = "src/pic/path.png";
                break;

            case EXIT:
                imageSrc = "src/pic/Lock.png";
                break;
        }
        image = Toolkit.getDefaultToolkit().getImage(imageSrc);
    }

    public void setLockUnlocked() {
        imageSrc = "src/pic/Unlock.png";
        this.image = Toolkit.getDefaultToolkit().getImage(imageSrc);
        repaint();
    }
    public void setPlayerUP(){
        imageSrc = "src/pic/player_up.png";
    }
    public void setPlayerDOWN(){
        imageSrc = "src/pic/player.png";
    }
    public void setPlayerLEFT(){ imageSrc = "src/pic/player_left.png"; }
    public void setPlayerRIGHT(){
        imageSrc = "src/pic/player_right.png";
    }
    public void setWallDirection(boolean top, boolean left, boolean down, boolean right){
        if (top && left && down && right){
            imageSrc = "src/pic/wall_cross.png";
        } else if (top && left && down){
            imageSrc = "src/pic/wall_no_right.png";
        } else if (top && left && right){
            imageSrc = "src/pic/wall_no_down.png";
        } else if (top && right && down){
            imageSrc = "src/pic/wall_no_left.png";
        } else if (right && left && down){
            imageSrc = "src/pic/wall_no_up.png";
        } else if (top && left){
            imageSrc = "src/pic/wall_bottom_right.png";
        } else if (top && down){
            imageSrc = "src/pic/wall_vertical.png";
        } else if (top && right){
            imageSrc = "src/pic/wall_bottom_left.png";
        } else if (left && down){
            imageSrc = "src/pic/wall_top_right.png";
        } else if (left && right){
            imageSrc = "src/pic/wall_horizontal.png";
        } else if (right && down){
            imageSrc = "src/pic/wall_top_left.png";
        } else if (top){
            imageSrc = "src/pic/wall_broken_down.png";
        } else if (left){
            imageSrc = "src/pic/wall_broken_right.png";
        } else if (right){
            imageSrc = "src/pic/wall_broken_left.png";
        } else if (down){
            imageSrc = "src/pic/wall_broken_up.png";
        } else{
            imageSrc = "src/pic/wall.png";
        }
        image = Toolkit.getDefaultToolkit().getImage(imageSrc);
    }

    public void setNewPosition(int[] pos){
        this.pos = pos;
        image = Toolkit.getDefaultToolkit().getImage(imageSrc);
        repaint();
    }


    public void paint (Graphics graphic) {
        super.paintComponent(graphic);
        graphic.drawImage(image, this.pos[0] * step, this.pos[1] * step, this);

    }

    public void updateCell(cellType type){
        this.type = type;
        selectSprite();
        this.image = Toolkit.getDefaultToolkit().getImage(imageSrc);
        repaint();

    }

    public int[] getPosition(){
        return this.pos;
    }

}
