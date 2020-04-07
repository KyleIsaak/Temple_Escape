package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Setup for pauseScreen
 */
public class PauseScreen extends JPanel implements ActionListener{
    private GameScreen gameScreen;
    private TitleScreen titleScreen;
    private final String MAIN_MENU = "Main Menu";
    private final String MUTE = "Mute";
    private final String RESUME = "Resume";
    private final String CONTROL = "Control";

    private JButton button_title;
    private JButton button_mute;
    private JButton button_control;
    private JButton button_resume;
    private ControlScreen controlScreen;

    /**
     * Constructor
     * @param controlScreen
     */
    public PauseScreen(ControlScreen controlScreen){
        this.controlScreen = controlScreen;

        JComponent background = new JComponent(){
            @Override
            public void paint(Graphics g){
                super.paintComponent(g);
                g.drawImage(Sprite.pauseBackground(), 0, 0, this);
            }
        };
        setLayout(null);
        add(background);
        addButtons();

        background.setBounds(300, 200,380, 110);

        setBackground(new Color(0, 0, 0, 175));
        setFocusable(true);
        setVisible(false);

    }

    /**
     * set up the button option on pause screen
     */
    private void addButtons(){
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
     * setup the gameScreen
     * @param gameScreen
     */
    public void setGameScreen(GameScreen gameScreen) { this.gameScreen = gameScreen; }

    /**
     * to be used when user press Mute Button on pause Screen
     */
    public void flipButtons(){
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

    public void setTitle(TitleScreen title){
        titleScreen = title;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String listener = actionEvent.getActionCommand();
        if (listener.equals(RESUME)){
            gameScreen.getBoard().getTimer().resumeTimer();
            setVisible(false);
            gameScreen.requestFocus();
        } else if (listener.equals(CONTROL)){
            flipButtons();
            controlScreen.setVisible(true);
            controlScreen.requestFocus();

        } else if (listener.equals(MUTE)){
            if (gameScreen.getMusic().isPause()){
                gameScreen.getMusic().unPause();
            }
            else{
                gameScreen.getMusic().pauseMusic();
            }
        } else if (listener.equals(MAIN_MENU)){
            setVisible(false);
            titleScreen.requestFocus();
            titleScreen.setVisible(true);
        }
    }

}
