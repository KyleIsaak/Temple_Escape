package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * storing the skin screen info.
 */
public class SkinScreen extends JPanel implements ActionListener {
    /**
     * storing the title screen info.
     */
    private TitleScreen title;
    /**
     * storing the button_skin info.
     */
    private JButton skin;
    /**
     * storing the image skin info.
     */
    private Image image = Sprite.skin();
    /**
     * storing the string < name.
     */
    private final static String LEFT = "<";
    /**
     * storing the string > name.
     */
    private final static String RIGHT = ">";

    /**
     * non default constructor.
     *
     * @param title store the title screen info.
     */
    public SkinScreen(TitleScreen title) {
        this.title = title;
        JComponent background = new JComponent() {
            @Override
            public void paint(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, this);
            }
        };
        setLayout(null);
        addButtons();
        add(background);
        background.setBounds(0, 0, 1000, 1000);
        setFocusable(true);
        setVisible(false);
    }

    /**
     * add the button info.
     */
    private void addButtons() {
        skin = new Button("PLAYER", this, false);
        skin.setBounds(500, 450, 80, 80);
        skin.setIcon(new ImageIcon(Sprite.player_right(0)));
        JButton buttonLeft = new Button(LEFT, this, true);
        buttonLeft.setBounds(400, 500, 80, 50);

        JButton buttonRight = new Button(RIGHT, this, true);
        buttonRight.setBounds(600, 500, 80, 50);
    }

    /**
     * change the skin icon.
     *
     * @param direction left or right.
     */
    private void swapSkin(int direction) {
        skin.setIcon(new ImageIcon(Sprite.player_right(direction % 2)));
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String listener = actionEvent.getActionCommand();
        if (listener.equals(LEFT)) {

        } else if (listener.equals(RIGHT)) {

        }
    }
}
