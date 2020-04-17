package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpScreen extends JPanel implements ActionListener {
    private TitleScreen title;
    private GameScreen gameScreen;
    private final static String BACK = "BACK";

    public HelpScreen(TitleScreen title, GameScreen gameScreen) {
        this.title = title;
        this.gameScreen = gameScreen;
        JComponent background = new JComponent() {
            @Override
            public void paint(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Sprite.help(), 0, 0, this);
            }
        };
        JButton button_back = new Button(BACK, this, true);
        setLayout(null);
        add(button_back);
        add(background);
        button_back.setBounds(450, 800, 100, 30);
        background.setBounds(0, 0, 1000, 1000);
        setFocusable(true);
        setVisible(false);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String listener = actionEvent.getActionCommand();
        if (listener.equals(BACK)) {
            setVisible(false);
            if (title.isVisible()) {
                title.flipButtons();
                title.requestFocus();
            } else if (gameScreen.isVisible()) {
                gameScreen.requestFocus();
            }
        }
    }
}
