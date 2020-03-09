package UI;

import java.awt.*;

import javax.swing.JComponent;


class DrawTimer extends JComponent {
    private int width = 100;
    private int height = 100;
    private int[] pos;
    public DrawTimer(int[] pos ){
        this.pos = pos;
    }
    public void paint(Graphics graphic) {
        graphic.setColor(Color.yellow);
        //graphic.fillRect (pos[0], pos[1], width, height);
    }
}
