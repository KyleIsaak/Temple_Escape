package UI;

import java.awt.*;

import javax.swing.JComponent;


class DrawReward extends JComponent {
    private int width = 40;
    private int height = 40;
    private int[] pos;
    public DrawReward(int[] pos ){
        this.pos = pos;
    }
    public void paint(Graphics graphic) {
        graphic.setColor(Color.blue);
        graphic.fillRect (pos[0], pos[1], width, height);
    }
}