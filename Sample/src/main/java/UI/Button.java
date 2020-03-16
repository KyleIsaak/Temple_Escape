package UI;

import javax.swing.*;
import java.awt.*;

public class Button extends JButton {


    public Button(String text){
        setText(text);
        setForeground(Color.decode("#aa8d7a"));
        setFont(newFont.getFont());
        setActionCommand(text);
        setBorder(BorderFactory.createRaisedBevelBorder());
        setBackground(null);
    }
}
