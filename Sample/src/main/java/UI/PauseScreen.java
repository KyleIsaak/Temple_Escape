package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

public class PauseScreen extends JPanel implements ActionListener{
    GameScreen gameScreen;
    InputStream inputStream;
    Image image;
    String mute = "Mute";
    String resume = "Resume";
    String control = "Control";

    JButton button_mute;
    JButton button_control;
    JButton button_resume;
    ControlScreen controlScreen;

    public PauseScreen(ControlScreen controlScreen){
        this.controlScreen = controlScreen;

        inputStream = PauseScreen.class.getResourceAsStream("/pause.png");
        try {
            image = ImageIO.read(inputStream);
        } catch (Exception e){
            e.printStackTrace();
        }

        button_mute = new Button(mute);
        button_resume = new Button(resume);
        button_control = new Button(control);
        JComponent background = new JComponent(){
            @Override
            public void paint(Graphics g){
                super.paintComponent(g);
                g.drawImage(image, 0, 0, this);
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

    private void addButtons(){

        add(button_mute);
        add(button_control);
        add(button_resume);


        button_mute.setBackground(Color.decode("#483b3a"));
        button_control.setBackground(Color.decode("#483b3a"));
        button_resume.setBackground(Color.decode("#483b3a"));

        button_mute.setBounds(430, 320, 100, 30);
        button_control.setBounds(430, 370, 100, 30);
        button_resume.setBounds(430, 420, 100, 30);

        button_mute.addActionListener(this);
        button_resume.addActionListener(this);
        button_control.addActionListener(this);

    }
    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

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
