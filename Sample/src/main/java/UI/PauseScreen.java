package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseScreen extends JPanel implements ActionListener{
    String imageSrc = "src/pic/pause.png";
    Image image = Toolkit.getDefaultToolkit().getImage(imageSrc);
    JButton button_control;
    JButton button_resume;
    public PauseScreen(){
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
        add(button_control);
        add(button_resume);

        background.setBounds(300, 200,380, 110);
        button_control.setBounds(450, 370, 80, 30);
        button_resume.setBounds(445, 420, 90, 30);

        button_control.addActionListener(this);
        setBackground(new Color(0, 0, 0, 125));
        setFocusable(true);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        button_control.setText("YES");
    }

}
