package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
/**
 * Setup for pauseScreen
 */
public class PauseScreen extends JPanel implements ActionListener{
    GameScreen gameScreen;
    String mute = "Mute";
    String resume = "Resume";
    String control = "Control";

    JButton button_mute;
    JButton button_control;
    JButton button_resume;
    ControlScreen controlScreen;

    /**
     * Constructor
     * @param controlScreen
     */
    public PauseScreen(ControlScreen controlScreen){
        this.controlScreen = controlScreen;

        button_mute = new Button(mute, this, true);
        button_resume = new Button(resume, this, true);
        button_control = new Button(control, this, true);
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
        button_mute.setBackground(Color.decode("#483b3a"));
        button_control.setBackground(Color.decode("#483b3a"));
        button_resume.setBackground(Color.decode("#483b3a"));

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

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String listener = actionEvent.getActionCommand();
        if (listener.equals(resume)){
            gameScreen.getBoard().getTimer().resumeTimer();
            setVisible(false);
            gameScreen.requestFocus();

        } else if (listener.equals(control)){
            flipButtons();
            controlScreen.setVisible(true);
            controlScreen.requestFocus();

        } else if (listener.equals(mute)){
            if (gameScreen.getMusic().isPause()){
                gameScreen.getMusic().unPause();
            }
            else{
                gameScreen.getMusic().pauseMusic();
            }
        }
    }

}
