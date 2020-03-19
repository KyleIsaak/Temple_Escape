package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Button extends JButton {


    public Button(String text, JPanel panel, boolean enabled){
        panel.add(this);
        addActionListener((ActionListener) panel);
        setText(text);
        setForeground(Color.decode("#aa8d7a"));
        setFont(newFont.getFont());
        setActionCommand(text);
        setBorder(BorderFactory.createRaisedBevelBorder());
        setBackground(null);
    }
}
