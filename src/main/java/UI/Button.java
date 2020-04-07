package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
/**
 * Set the button infomation
 */
public class Button extends JButton {
    /**
     * Non-Default Constructor(Button Class)
     * set the button information
     * @param text: the name of the button
     */

    public Button(String text, JPanel panel, boolean enabled){
        panel.add(this);
        setEnabled(enabled);
        addActionListener((ActionListener) panel);
        setText(text);
        setForeground(Color.decode("#aa8d7a"));
        setFont(new NewFont().getFont());
        setActionCommand(text);
        setBorder(BorderFactory.createRaisedBevelBorder());
        setBackground(null);
    }
}
