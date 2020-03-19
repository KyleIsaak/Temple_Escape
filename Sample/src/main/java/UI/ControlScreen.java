package UI;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.util.ArrayList;

public class ControlScreen extends JPanel implements ActionListener {

    GameScreen gameScreen;
    TitleScreen title;
    PauseScreen pause;
    //actual value of button

    //the string for setting action command and button name
    private final String UP = "UP";
    private final String DOWN = "DOWN";
    private final String LEFT = "LEFT";
    private final String RIGHT = "RIGHT";
    private final String DONE = "DONE";
    private final String MUTE = "MUTE";
    private final String CONTROL = "CONTROL";
    private final String ERROR = "ERROR";

    private JButton error;

    //the button for setting control
    private JButton setUP;
    private JButton setDOWN;
    private JButton setLEFT;
    private JButton setRIGHT;
    private JButton mute;
    private JButton done;

    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;

    private ArrayList<String> keyBindings;
    JComponent errorGirl;
    public void setGameScreen(GameScreen gameScreen) {
        keyBindings = new ArrayList<>();

        JComponent background = new JComponent(){
            @Override
            public void paint(Graphics g){
                super.paintComponent(g);
                g.drawImage(Sprite.controlBackground(), 0, 0, this);
            }
        };
        errorGirl = new JComponent(){
            @Override
            public void paint(Graphics g){
                super.paintComponent(g);
                g.drawImage(Sprite.controlError(), 0, 0, this);
            }
        };
        this.gameScreen = gameScreen;

        JButton subtitle = new Button(CONTROL, this, false);

        error = new Button(ERROR, this, false);
        error.setText("<html>" + "Invalid duplicated" + "<br>" + "key bindings!" + "</html>");
        error.getFont().deriveFont(14f);

        JButton txtUP = new Button(UP, this, false);
        JButton txtDOWN = new Button(DOWN, this, false);
        JButton txtLEFT = new Button(LEFT, this, false);
        JButton txtRIGHT = new Button(RIGHT, this, false);
        mute = new Button(MUTE, this, true);
        done = new Button(DONE, this, true);
        setUP = new Button(String.valueOf((char)(gameScreen.getUP())), this, true);
        setDOWN = new Button(String.valueOf((char)gameScreen.getDOWN()), this, true);
        setLEFT = new Button(String.valueOf((char)gameScreen.getLEFT()), this, true);
        setRIGHT = new Button(String.valueOf((char)gameScreen.getRIGHT()), this, true);

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

    private void errorOn(){
        error.setVisible(true);
        errorGirl.setVisible(true);
    }

    private void errorOff(){
        error.setVisible(false);
        errorGirl.setVisible(false);
    }

    public void setPause(PauseScreen pause){
        this.pause = pause;
    }
    public void setTitle(TitleScreen title){
        this.title = title;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        up = false;
        down = false;
        left = false;
        right = false;
        String listener = actionEvent.getActionCommand();
        if (listener.equals(DONE)) {

            System.out.println("done");
            setVisible(false);
            gameScreen.setControlUP(setUP.getText().charAt(0));
            gameScreen.setControlDOWN(setDOWN.getText().charAt(0));
            gameScreen.setControlLEFT(setLEFT.getText().charAt(0));
            gameScreen.setControlRIGHT(setRIGHT.getText().charAt(0));

            if(title.isVisible()){
                title.flipButtons();
                title.requestFocus();
            } else if (gameScreen.isVisible()){
                gameScreen.requestFocus();
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
            if (gameScreen.getMusic().isPause()){
                gameScreen.getMusic().unPause();
            }
            else{
                gameScreen.getMusic().pauseMusic();
            }
        }
    }

    private class keyListener extends KeyAdapter {
        boolean isReleased = true;

        @Override
        public void keyReleased(KeyEvent e) {
            isReleased = true;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (isReleased){
                isReleased = false;
                if (up){
                    setUP.setText(String.valueOf((char)key));
                    if (keyBindings.contains(setUP.getText())){
                        errorOn();
                        System.out.println("found");
                    } else errorOff();
                    keyBindings.set(0, setUP.getText());
                }if (down){
                    setDOWN.setText(String.valueOf((char)key));
                    if (keyBindings.contains(setDOWN.getText())){
                        errorOn();
                    } else errorOff();
                    keyBindings.set(1, setDOWN.getText());
                } if (left){
                    setLEFT.setText(String.valueOf((char)key));
                    if (keyBindings.contains(setLEFT.getText())){
                        errorOn();
                    } else errorOff();
                    keyBindings.set(2, setLEFT.getText());
                } if (right){
                    setRIGHT.setText(String.valueOf((char)key));
                    if (keyBindings.contains(setRIGHT.getText())){
                        errorOn();
                    } else errorOff();
                    keyBindings.set(3, setRIGHT.getText());
                }
            }
        }
    }
}