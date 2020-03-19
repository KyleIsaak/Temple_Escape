package UI;

import org.graalvm.compiler.nodes.calc.RightShiftNode;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.InputStream;

public class ControlScreen extends JPanel implements ActionListener {

    GameScreen gameScreen;
    TitleScreen title;
    //actual value of button

    //the string for setting action command and button name
    private final String UP = "UP";
    private final String DOWN = "DOWN";
    private final String LEFT = "LEFT";
    private final String RIGHT = "RIGHT";
    private final String DONE = "DONE";

    //the button for name
    private JButton subtitle;
    private JButton txtUP;
    private JButton txtDOWN;
    private JButton txtLEFT;
    private JButton txtRIGHT;

    //the button for setting control
    private JButton setUP;
    private JButton setDOWN;
    private JButton setLEFT;
    private JButton setRIGHT;
    private JButton done;

    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;
    Image image;
    public void setGameScreen(GameScreen gameScreen) {
        InputStream inputStream = PauseScreen.class.getResourceAsStream("/control.png");
        try {
            image = ImageIO.read(inputStream);
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
        this.gameScreen = gameScreen;
        subtitle = new Button("CONTROL");
        subtitle.setEnabled(false);

        txtUP = new Button(UP);         txtUP.setEnabled(false);
        txtDOWN = new Button(DOWN);     txtDOWN.setEnabled(false);
        txtLEFT = new Button(LEFT);     txtLEFT.setEnabled(false);
        txtRIGHT = new Button(RIGHT);   txtRIGHT.setEnabled(false);
        done = new Button(DONE);
        setUP = new Button(String.valueOf((char)(gameScreen.getUP())));
        setDOWN = new Button(String.valueOf((char)gameScreen.getDOWN()));
        setLEFT = new Button(String.valueOf((char)gameScreen.getLEFT()));
        setRIGHT = new Button(String.valueOf((char)gameScreen.getRIGHT()));

        setUP.setActionCommand(UP);     setUP.addActionListener(this);
        setDOWN.setActionCommand(DOWN);   setDOWN.addActionListener(this);
        setLEFT.setActionCommand(LEFT);   setLEFT.addActionListener(this);
        setRIGHT.setActionCommand(RIGHT);  setRIGHT.addActionListener(this);

        done.addActionListener(this);

        add(subtitle);  subtitle.setBounds(400, 120, 150, 50);
        add(txtUP);     txtUP.setBounds(280, 320, 80, 50);
        add(txtDOWN);   txtDOWN.setBounds(280, 400, 80, 50);
        add(txtLEFT);   txtLEFT.setBounds(630, 320, 80, 50);
        add(txtRIGHT);  txtRIGHT.setBounds(630, 400, 80, 50);
        add(done);      done.setBounds(600, 500, 80, 50);
        add(setUP);     setUP.setBounds(360, 320, 80, 50);
        add(setDOWN);   setDOWN.setBounds(360, 400, 80, 50);
        add(setLEFT);   setLEFT.setBounds(710, 320, 80, 50);
        add(setRIGHT);  setRIGHT.setBounds(710, 400, 80, 50);

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
            title.flipButtons();
            System.out.println("done");
            setVisible(false);
            gameScreen.setControlUP(setUP.getText().charAt(0));
            gameScreen.setControlDOWN(setDOWN.getText().charAt(0));
            gameScreen.setControlLEFT(setLEFT.getText().charAt(0));
            gameScreen.setControlRIGHT(setRIGHT.getText().charAt(0));

            if(title.isVisible()){
                title.requestFocus();
            } else if (gameScreen.isVisible()){
                gameScreen.requestFocus();
            }
        } else if (listener.equals(UP)) {
            up = true;
        } else if (listener.equals(DOWN)) {
            down = true;
        } else if (listener.equals(LEFT)) {
            left = true;
        } else if (listener.equals(RIGHT)) {
            right = true;
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
                } else if (down){
                    setDOWN.setText(String.valueOf((char)key));
                }  else if (left){
                    setLEFT.setText(String.valueOf((char)key));
                } else if (right){
                    setRIGHT.setText(String.valueOf((char)key));
                }
            }
        }
    }
}