package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

/**
 * Store the End Game Screen (show up when game over).
 */
public class EndScreen extends JPanel implements ActionListener {
    /**
     * store the title screen info.
     */
    private TitleScreen title;
    /**
     * Store the image of game over.
     */
    private Image image = Sprite.gameOver();
    /**
     * Store the button info.
     */
    private static JButton button_score;
    /**
     * Store the title name.
     */
    private final static String TITLE = "TITLE";
    /**
     * Store the score name.
     */
    private final static String SCORE = "ERROR";

    /**
     * Non-Default Constructor(ENDSCREEN).
     *
     * @param title shoring the title screen info.
     */
    public EndScreen(TitleScreen title) {
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
     * Set the button that shows on endScreen.
     */
    private void addButtons() {
        JButton button_title = new Button(TITLE, this, true);
        button_title.setBounds(450, 500, 80, 50);

        button_score = new Button(SCORE, this, false);
        button_score.setBounds(385, 600, 200, 50);
    }

    /**
     * Set the score that shows on endScreen.
     *  @param score An integer to set the current score as.
     */
    public void setScore(int score) {
        //scoreContainer = score;
        button_score.setText("Score: " + String.valueOf(score));
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String listener = actionEvent.getActionCommand();
        if (listener.equals(TITLE)) {
            setVisible(false);
            title.setVisible(true);
            title.requestFocus();
        }
    }
}
