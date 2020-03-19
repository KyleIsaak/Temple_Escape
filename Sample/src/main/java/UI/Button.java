package UI;

import javax.swing.*;
import java.awt.*;
/**
 * Set the button infomation
 */
public class Button extends JButton {

    /**
     * Non-Default Constructor(Button Class)
     * set the button information
     * @param text: the name of the button
     */
    public Button(String text){
        setText(text);
        setForeground(Color.decode("#aa8d7a"));
        setFont(newFont.getFont());
        setActionCommand(text);
        setBorder(BorderFactory.createRaisedBevelBorder());
        setBackground(null);
    }
}
