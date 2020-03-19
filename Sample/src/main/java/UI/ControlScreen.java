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

    //the button for name
    private JButton subtitle;
    private JButton error;
    private JButton txtUP;
    private JButton txtDOWN;
    private JButton txtLEFT;
    private JButton txtRIGHT;

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
    Image image;
    Image imageError;
    JComponent errorGirl;
    public void setGameScreen(GameScreen gameScreen) {
        keyBindings = new ArrayList<>();
        InputStream inputError = ControlScreen.class.getResourceAsStream("/control_error.png");
        InputStream inputStream = ControlScreen.class.getResourceAsStream("/control.png");
        try {
            image = ImageIO.read(inputStream);
            imageError = ImageIO.read(inputError);
        } catch (Exception e){
            e.printStackTrace();
        }
        JComponent background = new JComponent(){
            @Override
            public void paint(Graphics g){
                super.paintComponent(g);
                g.drawImage(image, 0, 0, this);
            }
        };
        errorGirl = new JComponent(){
            @Override
            public void paint(Graphics g){
                super.paintComponent(g);
                g.drawImage(imageError, 0, 0, this);
            }
        };
        this.gameScreen = gameScreen;

        subtitle = new Button("CONTROL");
        subtitle.setEnabled(false);
        error = new Button("ERROR");
        error.setText("<html>" + "Invalid duplicated" + "<br>" + "key bindings!" + "</html>");
        error.getFont().deriveFont(14f);
        error.setEnabled(false);

        txtUP = new Button(UP);         txtUP.setEnabled(false);
        txtDOWN = new Button(DOWN);     txtDOWN.setEnabled(false);
        txtLEFT = new Button(LEFT);     txtLEFT.setEnabled(false);
        txtRIGHT = new Button(RIGHT);   txtRIGHT.setEnabled(false);
        mute = new Button(MUTE);
        done = new Button(DONE);
        setUP = new Button(String.valueOf((char)(gameScreen.getUP())));
        setDOWN = new Button(String.valueOf((char)gameScreen.getDOWN()));
        setLEFT = new Button(String.valueOf((char)gameScreen.getLEFT()));
        setRIGHT = new Button(String.valueOf((char)gameScreen.getRIGHT()));

        keyBindings.add(setUP.getText());
        keyBindings.add(setLEFT.getText());
        keyBindings.add(setDOWN.getText());
        keyBindings.add(setRIGHT.getText());

        setUP.setActionCommand(UP);     setUP.addActionListener(this);
        setDOWN.setActionCommand(DOWN);   setDOWN.addActionListener(this);
        setLEFT.setActionCommand(LEFT);   setLEFT.addActionListener(this);
        setRIGHT.setActionCommand(RIGHT);  setRIGHT.addActionListener(this);

        done.addActionListener(this);

        add(errorGirl); errorGirl.setBounds(310, 490, 200, 200);
        add(error);     error.setBounds(420, 500, 220, 100);
        mute.addActionListener(this);

        add(subtitle);  subtitle.setBounds(450, 120, 150, 50);
        add(txtUP);     txtUP.setBounds(280, 320, 80, 50);
        add(txtDOWN);   txtDOWN.setBounds(280, 400, 80, 50);
        add(txtLEFT);   txtLEFT.setBounds(630, 320, 80, 50);
        add(txtRIGHT);  txtRIGHT.setBounds(630, 400, 80, 50);
        add(done);      done.setBounds(600, 550, 80, 50);
        add(mute);      mute.setBounds(600, 470, 80, 50);
        add(setUP);     setUP.setBounds(360, 320, 80, 50);
        add(setDOWN);   setDOWN.setBounds(360, 400, 80, 50);
        add(setLEFT);   setLEFT.setBounds(710, 320, 80, 50);
        add(setRIGHT);  setRIGHT.setBounds(710, 400, 80, 50);

        errorOff();

        add(background);
        background.setBounds(0, 0, 1000, 1000);
        setUP.setBorder(BorderFactory.createLoweredBevelBorder());
        setDOWN.setBorder(BorderFactory.createLoweredBevelBorder());
        setLEFT.setBorder(BorderFactory.createLoweredBevelBorder());
        setRIGHT.setBorder(BorderFactory.createLoweredBevelBorder());
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