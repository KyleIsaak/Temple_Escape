package UI;

import gameLogic.LevelGenerator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.InputStream;

/**
 * Show the information of the title Screen.
 */
public class TitleScreen extends JPanel implements ActionListener {
    /**
     * button to start easy mode.
     */
    private Button button_easy;
    /**
     * button to start medium mode.
     */
    private Button button_medium;
    /**
     * button to start hard mode.
     */
    private Button button_hard;
    /**
     * a button that quits the game.
     */
    private Button button_quit;
    /**
     * a button to control setting page.
     */
    private Button button_control;
    /**
     * a button to skin page.
     */
    private Button button_skin;
    /**
     * a button for help page.
     */
    private Button button_help;

    /**
     * store the misc info.
     */
    private Misc misc;
    /**
     * stores the gameScreen panel.
     */
    private GameScreen gameScreen;
    /**
     * stores the helpScreen panel.
     */
    private HelpScreen helpScreen;
    /**
     * store the control info.
     */
    private ControlScreen control;
    /**
     * store the skin screen info.
     */
    private SkinScreen skinScreen;
    /**
     * store the frame info.
     */
    private JFrame frame;
    /**
     * store the name easy as string.
     */
    private final static String EASY = "EASY";
    /**
     * store the name medium as string.
     */
    private final static String MEDIUM = "MEDIUM";
    /**
     * store the name hard as string.
     */
    private final static String HARD = "HARD";
    /**
     * store the name quit as string.
     */
    private final static String QUIT = "QUIT";
    /**
     * store the name control as string.
     */
    private final static String CONTROL = "CONTROL";
    /**
     * store the name skin as string.
     */
    private final static String SKIN = "SKIN";
    /**
     * store the name HELP as string.
     */
    private final static String HELP = "HELP";

    /**
     * Non default Constructor ((Title Screen)).
     *
     * @param gameScreen store the gameScreen info.
     * @param control    store the control info.
     * @param misc       store the misc info.
     * @param frame      JFrame Extension.
     */
    public TitleScreen(GameScreen gameScreen, ControlScreen control, Misc misc, JFrame frame) {
        this.gameScreen = gameScreen;
        this.control = control;
        this.misc = misc;
        this.frame = frame;
        setLayout(null);

        JComponent background = new JComponent() {
            @Override
            public void paint(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Sprite.titleBackground(), 0, 0, this);
            }
        };

        addButton();
        add(background);
        background.setBounds(0, 0, 1000, 1000);

        setBackground(Color.decode("#483b3a"));
        setFocusable(true);
        setVisible(true);
    }

    /**
     * set/change when flip the buttons.
     */
    public void flipButtons() {
        if (button_easy.isEnabled()) {
            button_quit.setEnabled(false);
            button_easy.setEnabled(false);
            button_control.setEnabled(false);
            button_control.setVisible(false);
            button_hard.setEnabled(false);
            button_medium.setEnabled(false);
            button_skin.setEnabled(false);
            button_help.setEnabled(false);
        } else {
            button_quit.setEnabled(true);
            button_easy.setEnabled(true);
            button_control.setEnabled(true);
            button_control.setVisible(true);
            button_hard.setEnabled(true);
            button_medium.setEnabled(true);
            button_skin.setEnabled(true);
            button_help.setEnabled(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        String listener = actionEvent.getActionCommand();
        if (listener.equals(EASY)) {
            gameScreen.setDifficulty(LevelGenerator.Difficulty.EASY);
            enterGame();
        } else if (listener.equals(MEDIUM)) {
            gameScreen.setDifficulty(LevelGenerator.Difficulty.MEDIUM);
            enterGame();
        } else if (listener.equals(HARD)) {
            gameScreen.setDifficulty(LevelGenerator.Difficulty.HARD);
            enterGame();
        } else if (listener.equals(QUIT)) {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        } else if (listener.equals(CONTROL)) {
            flipButtons();
            control.setVisible(true);
            control.requestFocus();
        } else if (listener.equals(SKIN)){
            flipButtons();
            skinScreen.setVisible(true);
            skinScreen.requestFocus();
        } else if (listener.equals(HELP)){
            flipButtons();
            helpScreen.setVisible(true);
            helpScreen.requestFocus();
        }

    }

    /**
     * Setting before the program Enter the game.
     */
    private void enterGame() {
        gameScreen.setVisible(true);
        gameScreen.requestFocus();
        misc.setVisible(true);
        setVisible(false);
        setFocusable(false);
        misc.setPause(true);
    }

    /**
     * add the button on main screen.
     */
    private void addButton() {
        button_easy = new Button(EASY, this, true);
        button_medium = new Button(MEDIUM, this, true);
        button_hard = new Button(HARD, this, true);
        button_quit = new Button(QUIT, this, true);
        button_control = new Button(CONTROL, this, true);
        button_skin = new Button(SKIN, this, true);
        button_help = new Button(HELP, this, true);

        button_easy.setBounds(160, 540, 100, 35);
        button_medium.setBounds(440, 540, 100, 35);
        button_hard.setBounds(730, 540, 100, 35);
        button_quit.setBounds(570, 620, 100, 35);
        button_control.setBounds(280, 620, 150, 35);
        button_skin.setBounds(160, 700, 100, 35);
        button_help.setBounds(700, 700, 100, 35);
    }

    public void setSkinScreen(SkinScreen skinScreen){
        this.skinScreen = skinScreen;
    }

    public void setHelp(HelpScreen helpScreen){
        this.helpScreen = helpScreen;
    }
}
