package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

public class NextScreen extends JPanel implements ActionListener {
    GameScreen gameScreen;
    InputStream inputStream;
    Image image;
    String NEXT = "Continue";

    JButton button_resume;

    public NextScreen(){
        inputStream = NextScreen.class.getResourceAsStream("/next.png");
        try {
            image = ImageIO.read(inputStream);
        } catch (Exception e){
            e.printStackTrace();
        }

        button_resume = new Button(NEXT, this, true);

        JComponent background = new JComponent(){
            @Override
            public void paint(Graphics g){
                super.paintComponent(g);
                g.drawImage(image, 0, 0, this);
            }
        };

        setLayout(null);
        add(background);
        button_resume.setBounds(430, 330, 100, 30);

        background.setBounds(50, 150,800, 400);

        setBackground(new Color(0, 0, 0, 175));
        setFocusable(true);
        setVisible(false);

    }

    public void setGameScreen(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String listener = actionEvent.getActionCommand();
        if (listener.equals(NEXT)){
            setVisible(false);
            gameScreen.setVisible(true);
            gameScreen.requestFocus();

        }
    }

}