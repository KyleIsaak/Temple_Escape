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
    String resume = "resume";
    String control = "control";


    JButton button_control;
    JButton button_resume;

    public PauseScreen(){
        inputStream = PauseScreen.class.getResourceAsStream("/pause.png");
        try {
            image = ImageIO.read(inputStream);
        } catch (Exception e){
            e.printStackTrace();
        }

        button_resume = new JButton("Resume");
        button_control = new JButton("Control");
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
        Font font;
        try {
            InputStream input = GameScreen.class.getResourceAsStream("/font.ttf");
            System.out.println(input);
            font = Font.createFont(Font.TRUETYPE_FONT, input);
            font = font.deriveFont(18f);
        } catch (Exception e){
            e.printStackTrace();
            font = new Font("serif", Font.PLAIN, 14);
        }


        add(button_control);
        add(button_resume);

        button_control.setForeground(Color.white);
        button_resume.setForeground(Color.white);
        button_control.setFont(font);
        button_resume.setFont(font);
        button_control.setBorder(null);
        button_resume.setBorder(null);
        button_control.setBackground(Color.decode("#483b3a"));
        button_resume.setBackground(Color.decode("#483b3a"));

        button_control.setBounds(430, 370, 100, 30);
        button_resume.setBounds(430, 420, 100, 30);

        button_resume.addActionListener(this);
        button_control.addActionListener(this);

        button_resume.setActionCommand(resume);
        button_control.setActionCommand(control);
    }
    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String listener = actionEvent.getActionCommand();
        if (listener.equals(resume)){
            setVisible(false);
            gameScreen.requestFocus();
            gameScreen.setButton(true);
        } else if (listener.equals(control)){
            button_control.setText("NO");
        }
    }

}
