package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Setup for pauseScreen
 */
public class PauseScreen extends JPanel implements ActionListener {
    /**
     * store the gameScreen info.
     */
    private GameScreen gameScreen;
    /**
     * store the title screen info.
     */
    private TitleScreen titleScreen;
    /**
     * store the misc screen info.
     */
    private Misc miscScreen;
    /**
     * store the string main menu name.
     */
    private final String MAIN_MENU = "Main Menu";
    /**
     * store the string mute name.
     */
    private final String MUTE = "Mute";
    /**
     * store the string resume name.
     */
    private final String RESUME = "Resume";
    /**
     * store the string control name.
     */
    private final String CONTROL = "Control";
    /**
     * store the button title info.
     */
    private JButton button_title;
    /**
     * store the button mute info.
     */
    private JButton button_mute;
    /**
     * store the button control info.
     */
    private JButton button_control;
    /**
     * store the button resume info.
     */
    private JButton button_resume;
    /**
     * store the control screen info.
     */
    private ControlScreen controlScreen;

    /**
     * Constructor.
     *
     * @param controlScreen storing the control screen info.
     */
    public PauseScreen(ControlScreen controlScreen) {
        this.controlScreen = controlScreen;

        JComponent background = new JComponent() {
            @Override
            public void paint(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new Sprite().pauseBackground(), 0, 0, this);
            }
        };
        setLayout(null);
        add(background);
        addButtons();

        background.setBounds(300, 200, 380, 110);

        setBackground(new Color(0, 0, 0, 175));
        setFocusable(true);
        setVisible(false);

    }

    /**
     * set up the button option on pause screen.
     */
    private void addButtons() {
        button_title = new Button(MAIN_MENU, this, true);
        button_mute = new Button(MUTE, this, true);
        button_resume = new Button(RESUME, this, true);
        button_control = new Button(CONTROL, this, true);

        button_title.setBackground(Color.decode("#483b3a"));
        button_mute.setBackground(Color.decode("#483b3a"));
        button_control.setBackground(Color.decode("#483b3a"));
        button_resume.setBackground(Color.decode("#483b3a"));

        button_title.setBounds(405, 470, 150, 30);
        button_mute.setBounds(430, 320, 100, 30);
        button_control.setBounds(430, 370, 100, 30);
        button_resume.setBounds(430, 420, 100, 30);
    }

    /**
     * setup the gameScreen.
     *
     * @param gameScreen store the game screen info.
     */
    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    /**
     * to be used when user press Mute Button on pause Screen.
     */
    public void flipButtons() {
        if (button_mute.isEnabled()) {
            button_control.setEnabled(false);
            button_control.setVisible(false);
            button_mute.setEnabled(false);
            button_resume.setEnabled(false);
        } else {
            button_control.setEnabled(true);
            button_control.setVisible(true);
            button_mute.setEnabled(true);
            button_resume.setEnabled(true);
        }
    }

    /**
     * set the title screen info.
     *
     * @param title storing the title screen info.
     */
    public void setTitle(TitleScreen title) {

        titleScreen = title;
    }
    /**
     * set the misc screen info.
     *
     * @param misc storing the misc screen info.
     */
    public void setMisc(Misc misc) {

        miscScreen = misc;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String listener = actionEvent.getActionCommand();
        if (listener.equals(RESUME)) {
            gameScreen.getBoard().getTimer().resumeTimer();
            setVisible(false);
            miscScreen.setPause(true);
            gameScreen.requestFocus();
        } else if (listener.equals(CONTROL)) {
            flipButtons();
            controlScreen.setVisible(true);
            controlScreen.requestFocus();

        } else if (listener.equals(MUTE)) {
            if (gameScreen.getMusic().isPause()) {
                gameScreen.getMusic().unPause();
            } else {
                gameScreen.getMusic().pauseMusic();
            }
        } else if (listener.equals(MAIN_MENU)) {
            setVisible(false);
            titleScreen.requestFocus();
            titleScreen.setVisible(true);
        }
    }

}
