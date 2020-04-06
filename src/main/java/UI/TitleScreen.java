package UI;

import gameLogic.LevelGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Show the information of the title Screen
 */
public class TitleScreen extends JPanel implements ActionListener{
    private Button button_easy;
    private Button button_medium;
    private Button button_hard;
    private Button button_quit;
    private Button button_control;

    private Misc misc;
    private GameScreen gameScreen;
    private ControlScreen control;

    private final String EASY = "EASY";
    private final String MEDIUM = "MEDIUM";
    private final String HARD = "HARD";
    private final String QUIT = "QUIT";
    private final String CONTROL = "CONTROL";

    /**
     * Non default Constructor ((Title Screen
     * @param gameScreen storing the gameScreen info
     * @param control storing the control info
     * @param misc storing the misc info
     */
    public TitleScreen(GameScreen gameScreen, ControlScreen control, Misc misc){
        this.gameScreen = gameScreen;
        this.control = control;
        this.misc = misc;
        setLayout(null);

        JComponent background = new JComponent(){
            @Override
            public void paint(Graphics g){
                super.paintComponent(g);
                g.drawImage(Sprite.titleBackground(), 0, 0, this);
            }
        };

        addButton();
        add(background);
        background.setBounds(0, 0,1000, 1000);

        setBackground(Color.decode("#483b3a"));
        setFocusable(true);
        setVisible(true);
    }

    public void flipButtons(){
        if (button_easy.isEnabled()){
            button_quit.setEnabled(false);
            button_easy.setEnabled(false);
            button_control.setEnabled(false);
            button_control.setVisible(false);
            button_hard.setEnabled(false);
            button_medium.setEnabled(false);
        } else{
            button_quit.setEnabled(true);
            button_easy.setEnabled(true);
            button_control.setEnabled(true);
            button_control.setVisible(true);
            button_hard.setEnabled(true);
            button_medium.setEnabled(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        String listener = actionEvent.getActionCommand();
        if (listener.equals(EASY)){
            gameScreen.setDifficulty(LevelGenerator.Difficulty.EASY);
            enterGame();
        } else if (listener.equals(MEDIUM)){
            gameScreen.setDifficulty(LevelGenerator.Difficulty.MEDIUM);
            enterGame();
        } else if (listener.equals(HARD)){
            gameScreen.setDifficulty(LevelGenerator.Difficulty.HARD);
            enterGame();
        } else if (listener.equals(QUIT)){
            System.exit(0);
        } else if (listener.equals(CONTROL)){
            flipButtons();
            control.setVisible(true);
            control.requestFocus();
        }

    }

    /**
     * Setting before the program Enter the game
     */
    private void enterGame(){
        gameScreen.setVisible(true);
        gameScreen.requestFocus();
        misc.setVisible(true);
        setVisible(false);
        setFocusable(false);
        misc.setPause(true);
    }

    /**
     * add the button on main screen
     */
    private void addButton(){
        button_easy = new Button(EASY, this, true);
        button_medium = new Button(MEDIUM, this, true);
        button_hard = new Button(HARD, this, true);
        button_quit = new Button(QUIT, this, true);
        button_control = new Button(CONTROL, this, true);

        button_easy.setBounds(160, 540, 100, 35);
        button_medium.setBounds(435, 540, 100, 35);
        button_hard.setBounds(730, 540, 100, 35);
        button_quit.setBounds(570, 620, 100, 35);
        button_control.setBounds(280, 620, 150, 35);
    }
}
