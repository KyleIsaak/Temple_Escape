package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

/**
 * Storing the nextScreen Info.
 */
public class NextScreen extends JPanel implements ActionListener {
    /**
     * Storing the game screen Info.
     */
    private GameScreen gameScreen;
    /**
     * Storing the input stream.
     */
    private InputStream inputStream;
    /**
     * Storing the sprite next image.
     */
    private Image image = Sprite.next();
    /**
     * Storing the string continue name.
     */
    private String NEXT = "Continue";
    /**
     * store the button: resume info.
     */
    JButton button_resume;

    /**
     * Default Constructor.
     */
    public NextScreen() {
        button_resume = new Button(NEXT, this, true);

        JComponent background = new JComponent() {
            @Override
            public void paint(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, this);
            }
        };

        setLayout(null);
        add(background);
        button_resume.setBounds(430, 330, 100, 30);

        background.setBounds(50, 150, 800, 400);

        setBackground(new Color(0, 0, 0, 175));
        setFocusable(true);
        setVisible(false);

    }

    /**
     * Setter.
     *
     * @param gameScreen store game screen info.
     */
    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String listener = actionEvent.getActionCommand();
        if (listener.equals(NEXT)) {
            setVisible(false);
            gameScreen.setVisible(true);
            gameScreen.requestFocus();

        }
    }

}