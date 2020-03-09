package UI;

import javax.swing.*;
import java.awt.*;


public class DrawCell extends JComponent {
    private int width = 20;
    private int height = 20;
    private Color color;
    private cellType type;
    private int[] pos;

    public enum cellType{
        PLAYER,
        ENEMY,
        WALL,
        PATH
    }
    //color can be set to sprites later on
    public DrawCell(int[] pos, cellType type){
        this.pos = pos;
        this.type = type;
        selectSprite();
    }

    private void selectSprite(){
        switch(type){
            case PLAYER:
                this.color = Color.green;
                break;
            case ENEMY:
                this.color = Color.red;
                break;
            case WALL:
                this.color = Color.darkGray;
                break;
            case PATH:
                this.color = Color.white;
                break;
        }
    }
    public void paint(Graphics graphic) {
        graphic.setColor(this.color);
        graphic.fillRect (pos[0], pos[1], width, height);
    }
}