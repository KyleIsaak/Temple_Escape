package UI;

import javax.swing.JFrame;
public class DisplayOutput {
    private JFrame window;
    public DisplayOutput(){
        this.window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(30, 30, 300, 300);
        window.setVisible(true);
    }
    public void renderPlayer(int[] pos) {
        window.getContentPane().add(new DrawPlayer(pos));
        window.setVisible(true);
    }
    public JFrame getFrame(){
        return this.window;
    }
}
