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
    private DrawLive skin;

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
     * storing the string BACK name.
     */
    private final static String BACK = "BACK";

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
        JComponent skinTitle = new JComponent() {
            @Override
            public void paint(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Sprite.skinTitle(), 0, 0, this);
            }
        };
        setLayout(null);
        addButtons();
        add(background);
        add(skinTitle);
        background.setBounds(440, 400, 128, 96);
        skinTitle.setBounds(0, 0, 1000, 1000);
        setBackground(Color.decode("#483b3a"));
        setFocusable(true);
        setVisible(false);
    }

    /**
     * add the button info.
     */
    private void addButtons() {
        skin = new DrawLive(new int[]{0, 0}, 32, DrawLive.CellType.PLAYER);
        skin.setBounds(470, 400, 80, 80);
        JButton buttonLeft = new Button(LEFT, this, true);
        buttonLeft.setBounds(360, 500, 80, 50);

        JButton buttonRight = new Button(RIGHT, this, true);
        buttonRight.setBounds(565, 500, 80, 50);

        JButton buttonBack = new Button(BACK, this, true);
        buttonBack.setBounds(470, 600, 80, 50);

        add(skin);
        add(buttonRight);
        add(buttonLeft);
        add(buttonBack);
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String listener = actionEvent.getActionCommand();
        if (listener.equals(LEFT)) {
            Sprite.skinPrevious();
        } else if (listener.equals(RIGHT)) {
            Sprite.skinNext();
        } else if (listener.equals(BACK)){
            title.requestFocus();
            setVisible(false);
            title.flipButtons();
        }
        skin.setPlayerRIGHT();
        repaint();
    }
}
