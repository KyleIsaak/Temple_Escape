package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

public class EndScreen extends JPanel implements ActionListener {

    private TitleScreen title;
    private Image image;
    private JButton button_title;
    private final String TITLE = "TITLE";
    public EndScreen(TitleScreen title){
        this.title = title;
        InputStream inputStream = PauseScreen.class.getResourceAsStream("/gameOver.png");
        try {
            image = ImageIO.read(inputStream);
        } catch (Exception e){
            e.printStackTrace();
        }

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
        background.setBounds(0, 0,1000, 1000);

        setFocusable(true);
        setVisible(false);
    }

    private void addButtons(){
        button_title = new Button(TITLE);
        add(button_title);
        button_title.setBounds(450, 500, 80, 50);
        button_title.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String listener = actionEvent.getActionCommand();
        if (listener.equals(TITLE)){
            setVisible(false);
            title.setVisible(true);
            title.requestFocus();
        }
    }
}
