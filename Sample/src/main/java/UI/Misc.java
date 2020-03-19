package UI;

        import GameLogic.Score;

        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

public class Misc extends JPanel implements ActionListener {

    private static int currentLevel = 1;
    GameScreen gameScreen;
    JPanel pauseScreen;

    private JButton button_pause;

    private static int scoreContainer = 100;
    private JButton button_TxtScore;
    private static JButton button_score;

    private static long timeContainer = 0;
    private JButton button_TxtTime;
    private static JButton button_time;

    private final String PAUSE = " pause ";
    private final String SCORE = " score: ";
    private final String TIME = "time: ";

    public Misc(GameScreen gameScreen, JPanel pauseScreen){
        this.gameScreen = gameScreen;
        this.pauseScreen = pauseScreen;
        setLayout(null);

        button_pause = new Button(PAUSE);

        button_TxtScore = new Button(SCORE);
        button_score = new Button("100");

        button_TxtTime = new Button(TIME);
        button_time = new Button("000");

        add(button_pause);
        button_pause.setBounds(0, 50, 100, 40);
        button_pause.addActionListener(this);
        button_pause.setVisible(false);

        add(button_TxtScore);
        button_TxtScore.setBounds(0, 120, 100, 40);
        button_TxtScore.setEnabled(false);

        add(button_score);
        button_score.setBounds(0, 160, 100, 40);
        button_score.setEnabled(false);

        add(button_TxtTime);
        button_TxtTime.setBounds(0,240,100,40);
        button_TxtTime.setEnabled(false);

        add(button_time);
        button_time.setBounds(0,280,100,40);
        button_time.setEnabled(false);


        setBackground(Color.decode("#483b3a"));
        setFocusable(true);
        setVisible(false);
    }
    public void setPause(boolean visible){
        button_pause.setVisible(visible);
    }
    public static int getScoreContainer() {return scoreContainer; }
    public static void setScore(int score){
        scoreContainer = score;
        button_score.setText(String.valueOf(score));
    }

    public static long getTimeContainer(){return timeContainer;}
    public static void setTime(long time){
        timeContainer = time;
        String timeText = time /60 + ":" + String.format("%02d", time %60);
        button_time.setText(timeText);
        //button_time.setText(String.valueOf(time));    //This line gives seconds only
    }

    public static void incCurrentLevel(){
        currentLevel++;
    }

    public static int getCurrentLevel(){
        return currentLevel;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String listener = actionEvent.getActionCommand();
        if (listener.equals(PAUSE)){
            gameScreen.getBoard().getTimer().pauseTimer();
            pauseScreen.requestFocus();
            pauseScreen.setVisible(true);

        }
    }
}
