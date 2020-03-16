package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreen extends JPanel implements ActionListener{
    JButton button_start;
    JPanel gameScreen;
    public TitleScreen(JPanel gameScreen){
        this.gameScreen = gameScreen;
        button_start = new JButton("Start");
        setLayout(null);

        add(button_start);
        button_start.setBounds(0, 0, 80, 30);
        button_start.addActionListener(this);

        setBackground(Color.decode("#80a8de"));
        setFocusable(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
       setVisible(false);
       setFocusable(false);
       gameScreen.setVisible(true);
       gameScreen.requestFocus();
    }

}
