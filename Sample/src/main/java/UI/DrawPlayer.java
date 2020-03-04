package UI;

import java.awt.*;

import javax.swing.JComponent;


class DrawPlayer extends JComponent {
    private int width = 40;
    private int height = 40;
    private int[] pos;
    public DrawPlayer(int[] pos ){
        this.pos = pos;
    }
    public void paint(Graphics graphic) {
        graphic.setColor(Color.green);
        graphic.fillRect (pos[0], pos[1], width, height);
    }
}
