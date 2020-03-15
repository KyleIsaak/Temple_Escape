package UI;

import java.awt.*;

import javax.swing.JComponent;


class DrawEnemy extends JComponent {
    private int width = 20;
    private int height = 20;
    private int[] pos;
    public DrawEnemy(int[] pos ){
        this.pos = pos;
    }
    public void paint(Graphics graphic) {
        graphic.setColor(Color.green);
        graphic.fillRect (pos[0], pos[1], width, height);
    }
}
