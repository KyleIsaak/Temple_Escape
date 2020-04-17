package UI;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Store and setup control screen.
 */
public class ControlScreen extends JPanel implements ActionListener {
    /**
     * Store the gameScreen Info.
     */
    GameScreen gameScreen;
    /**
     * Store the titleScreen info.
     */
    TitleScreen title;
    /**
     * Store the pauseScreen info.
     */
    PauseScreen pause;
    //actual value of button
    /**
     * the string for setting action command and button name.
     */
    /**
     * the string for setting action command and button name.
     */
    private final static String UP = "UP";
    /**
     * the string for setting action command and button name.
     */
    private final static String DOWN = "DOWN";
    /**
     * the string for setting action command and button name.
     */
    private final static String LEFT = "LEFT";
    /**
     * the string for setting action command and button name.
     */
    private final static String RIGHT = "RIGHT";
    /**
     * the string for setting action command and button name.
     */
    private final static String DONE = "DONE";
    /**
     * the string for setting action command and button name.
     */
    private final static String MUTE = "MUTE";
    /**
     * the string for setting action command and button name.
     */
    private final static String CONTROL = "CONTROL";
    /**
     * the string for setting action command and button name.
     */
    private final static String ERROR = "<html>" + "Invalid duplicated" + "<br>" + "key bindings!" + "</html>";
    /**
     * the string for setting action command and button name.
     */
    private JButton error;

    /**
     * the button for setting control.
     */
    private JButton setUP;
    /**
     * the button for setting control.
     */
    private JButton setDOWN;
    /**
     * the button for setting control.
     */
    private JButton setLEFT;
    /**
     * the button for setting control.
     */
    private JButton setRIGHT;
    /**
     * the button for setting control.
     */
    private JButton mute;
    /**
     * the button for setting control.
     */
    private JButton done;

    /**
     * store is up or not info.
     */
    boolean up = false;
    /**
     * store is down or not info.
     */
    boolean down = false;
    /**
     * store is left or not info.
     */
    boolean left = false;
    /**
     * store is right or not info.
     */
    boolean right = false;
    /**
     * Store the key bindings info.
     */
    private ArrayList<String> keyBindings;
    /**
     * Store JComponent info.
     */
    JComponent errorGirl;

    /**
     * setup of GameScreen.
     *
     * @param gameScreen Storing the information of GameScreen.
     */
    public void setGameScreen(GameScreen gameScreen) {
        keyBindings = new ArrayList<>();

        JComponent background = new JComponent() {
            @Override
            public void paint(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Sprite.controlBackground(), 0, 0, this);
            }
        };
        errorGirl = new JComponent() {
            @Override
            public void paint(Graphics g) {
                super.paintComponent(g);
                g.drawImage(Sprite.controlError(), 0, 0, this);
            }
        };
        this.gameScreen = gameScreen;

        JButton subtitle = new Button(CONTROL, this, false);

        error = new Button(ERROR, this, false);

        JButton txtUP = new Button(UP, this, false);
        JButton txtDOWN = new Button(DOWN, this, false);
        JButton txtLEFT = new Button(LEFT, this, false);
        JButton txtRIGHT = new Button(RIGHT, this, false);
        mute = new Button(MUTE, this, true);
        done = new Button(DONE, this, true);
        setUP = new Button(String.valueOf((char) (gameScreen.getUP())), this, true);
        setDOWN = new Button(String.valueOf((char) gameScreen.getDOWN()), this, true);
        setLEFT = new Button(String.valueOf((char) gameScreen.getLEFT()), this, true);
        setRIGHT = new Button(String.valueOf((char) gameScreen.getRIGHT()), this, true);

        keyBindings.add(setUP.getText());
        keyBindings.add(setLEFT.getText());
        keyBindings.add(setDOWN.getText());
        keyBindings.add(setRIGHT.getText());

        //adding because the command is different from the initialized
        setUP.setActionCommand(UP);
        setDOWN.setActionCommand(DOWN);
        setLEFT.setActionCommand(LEFT);
        setRIGHT.setActionCommand(RIGHT);

        errorGirl.setBounds(310, 490, 200, 200);
        error.setBounds(420, 500, 220, 100);

        subtitle.setBounds(450, 120, 150, 50);
        txtUP.setBounds(280, 320, 80, 50);
        txtDOWN.setBounds(280, 400, 80, 50);
        txtLEFT.setBounds(630, 320, 80, 50);
        txtRIGHT.setBounds(630, 400, 80, 50);
        done.setBounds(480, 550, 80, 50);
        mute.setBounds(480, 470, 80, 50);
        setUP.setBounds(360, 320, 80, 50);
        setDOWN.setBounds(360, 400, 80, 50);
        setLEFT.setBounds(710, 320, 80, 50);
        setRIGHT.setBounds(710, 400, 80, 50);

        errorOff();

        add(errorGirl);
        add(background);
        background.setBounds(0, 0, 1000, 1000);

        setUP.setBorder(BorderFactory.createLoweredBevelBorder());
        setDOWN.setBorder(BorderFactory.createLoweredBevelBorder());
        setLEFT.setBorder(BorderFactory.createLoweredBevelBorder());
        setRIGHT.setBorder(BorderFactory.createLoweredBevelBorder());

        //for reading key input
        setUP.addKeyListener(new keyListener());
        setDOWN.addKeyListener(new keyListener());
        setLEFT.addKeyListener(new keyListener());
        setRIGHT.addKeyListener(new keyListener());

        setBackground(Color.decode("#483b3a"));
        setLayout(null);
        setFocusable(true);
        setVisible(false);
    }

    /**
     * turn on the error case.
     */
    private void errorOn() {
        error.setVisible(true);
        errorGirl.setVisible(true);
    }

    /**
     * turn off the error case.
     */
    private void errorOff() {
        error.setVisible(false);
        errorGirl.setVisible(false);
    }

    /**
     * setup pause.
     *
     * @param pause store the pasue information.
     */
    public void setPause(PauseScreen pause) {
        this.pause = pause;
    }

    /**
     * setup title
     *
     * @param title store the title information.
     */
    public void setTitle(TitleScreen title) {
        this.title = title;
    }

    /**
     * base on the action event and preformed the corresponding performed.
     *
     * @param actionEvent the user action.
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        up = false;
        down = false;
        left = false;
        right = false;
        String listener = actionEvent.getActionCommand();
        if (listener.equals(DONE)) {

            setVisible(false);
            gameScreen.setControlUP(setUP.getText().charAt(0));
            gameScreen.setControlDOWN(setDOWN.getText().charAt(0));
            gameScreen.setControlLEFT(setLEFT.getText().charAt(0));
            gameScreen.setControlRIGHT(setRIGHT.getText().charAt(0));

            if (title.isVisible()) {
                title.flipButtons();
                title.requestFocus();
            } else if (pause.isVisible()) {
                pause.requestFocus();
                pause.flipButtons();
            }
        } else if (listener.equals(UP)) {
            up = true;
        } else if (listener.equals(DOWN)) {
            down = true;
        } else if (listener.equals(LEFT)) {
            left = true;
        } else if (listener.equals(RIGHT)) {
            right = true;
        } else if (listener.equals(MUTE)) {
            if (gameScreen.getMusic().isPause()) {
                gameScreen.getMusic().unPause();
            } else {
                gameScreen.getMusic().pauseMusic();
            }
        }
    }

    private class keyListener extends KeyAdapter {
        boolean isReleased = true;

        /**
         * detected the key is released or not.
         *
         * @param e the kep that user pressed.
         */
        @Override
        public void keyReleased(KeyEvent e) {
            isReleased = true;
        }

        /**
         * detected the key the user pressed.
         *
         * @param e the alpha the user pressed.
         */
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (isReleased) {
                isReleased = false;
                if (up) {
                    setUP.setText(String.valueOf((char) key));
                    if (keyBindings.contains(setUP.getText())) {
                        errorOn();
                        System.out.println("found");
                    } else errorOff();
                    keyBindings.set(0, setUP.getText());
                }
                if (down) {
                    setDOWN.setText(String.valueOf((char) key));
                    if (keyBindings.contains(setDOWN.getText())) {
                        errorOn();
                    } else errorOff();
                    keyBindings.set(1, setDOWN.getText());
                }
                if (left) {
                    setLEFT.setText(String.valueOf((char) key));
                    if (keyBindings.contains(setLEFT.getText())) {
                        errorOn();
                    } else errorOff();
                    keyBindings.set(2, setLEFT.getText());
                }
                if (right) {
                    setRIGHT.setText(String.valueOf((char) key));
                    if (keyBindings.contains(setRIGHT.getText())) {
                        errorOn();
                    } else errorOff();
                    keyBindings.set(3, setRIGHT.getText());
                }
            }
        }
    }
}