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
     * store the button easy info.
     */
    private Button button_easy;
    /**
     * store the button medium info.
     */
    private Button button_medium;
    /**
     * store the button hard info.
     */
    private Button button_hard;
    /**
     * store the button quit info.
     */
    private Button button_quit;
    /**
     * store the button control info.
     */
    private Button button_control;
    /**
     * store the button skin info.
     */
    private Button button_skin;

    /**
     * store the misc info.
     */
    private Misc misc;
    /**
     * store the game screen info.
     */
    private GameScreen gameScreen;
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
     * Non default Constructor ((Title Screen)).
     *
     * @param gameScreen storing the gameScreen info.
     * @param control    storing the control info.
     * @param misc       storing the misc info.
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
        } else {
            button_quit.setEnabled(true);
            button_easy.setEnabled(true);
            button_control.setEnabled(true);
            button_control.setVisible(true);
            button_hard.setEnabled(true);
            button_medium.setEnabled(true);
            button_skin.setEnabled(true);

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

        button_easy.setBounds(160, 540, 100, 35);
        button_medium.setBounds(440, 540, 100, 35);
        button_hard.setBounds(730, 540, 100, 35);
        button_quit.setBounds(570, 620, 100, 35);
        button_control.setBounds(280, 620, 150, 35);
        button_skin.setBounds(160, 700, 100, 35);
    }

    public void setSkinScreen(SkinScreen skinScreen){
        this.skinScreen = skinScreen;
    }
}
