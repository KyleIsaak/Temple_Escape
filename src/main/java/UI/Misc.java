package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Storing The misc info of the game.
 */
public class Misc extends JPanel implements ActionListener {
    /**
     * store the current level info.
     */
    private static int currentLevel = 1;
    /**
     * store the game screen info.
     */
    private GameScreen gameScreen;
    /**
     * store the game screen info.
     */
    private HelpScreen helpScreen;
    /**
     * store the pause screen info.
     */
    private JPanel pauseScreen;
    /**
     * store the button info.
     */
    private JButton button_pause;
    /**
     * store the initial score info.
     */
    private static int scoreContainer = 100;
    /**
     * store the score info.
     */
    private JButton button_score;
    /**
     * store the time info.
     */
    private JButton button_time;
    /**
     * button to bring up help page.
     */
    private JButton button_help;
    /**
     * store the string pause name.
     */
    private final static String PAUSE = " pause ";
    /**
     * store the string score name.
     */
    private final static String SCORE = " score: ";
    /**
     * store the string time name.
     */
    private final static String TIME = "time: ";
    /**
     * store the string help name.
     */
    private final static String HELP = "help";

    /**
     * NOn default constructor.
     *
     * @param gameScreen  storing the gameScreen info.
     * @param pauseScreen storing the pauseScreen info.
     */
    public Misc(GameScreen gameScreen, JPanel pauseScreen) {
        this.gameScreen = gameScreen;
        this.pauseScreen = pauseScreen;
        setLayout(null);

        button_pause = new Button(PAUSE, this, true);
        JButton button_TxtScore = new Button(SCORE, this, false);
        button_score = new Button("100", this, false);
        JButton button_TxtTime = new Button(TIME, this, false);
        button_time = new Button("000", this, false);
        button_help = new Button(HELP, this, true);

        button_pause.setBounds(0, 50, 100, 40);
        button_TxtScore.setBounds(0, 120, 100, 40);
        button_score.setBounds(0, 160, 100, 40);
        button_TxtTime.setBounds(0, 240, 100, 40);
        button_time.setBounds(0, 280, 100, 40);
        button_help.setBounds(0, 360, 100, 40);

        setBackground(Color.decode("#483b3a"));
        setFocusable(true);
        setVisible(false);
    }

    /**
     * Set pause.
     *
     * @param visible storing the info visible or not.
     */
    public void setPause(boolean visible) {
        button_pause.setEnabled(visible);
        button_pause.setVisible(visible);
    }

    public void setHelpScreen(HelpScreen help){
        helpScreen = help;
    }

    public static int getScoreContainer() {
        return scoreContainer;
    }

    /**
     * set Score.
     *
     * @param score score of the player.
     */
    public void setScore(int score) {
        scoreContainer = score;
        button_score.setText(String.valueOf(score));
    }

    /**
     * set the time.
     *
     * @param time storing the time info.
     */
    public void setTime(long time) {
        String timeText = time / 60 + ":" + String.format("%02d", time % 60);
        button_time.setText(timeText);
        //button_time.setText(String.valueOf(time));    //This line gives seconds only
    }

    /**
     * add 1 to the current level.
     */
    public static void incCurrentLevel() {
        currentLevel++;
    }

    /**
     * get current level.
     *
     * @return current level.
     */
    public static int getCurrentLevel() {
        return currentLevel;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String listener = actionEvent.getActionCommand();
        if (listener.equals(PAUSE)) {
            gameScreen.getBoard().getTimer().pauseTimer();
            button_pause.setEnabled(false);
            pauseScreen.requestFocus();
            pauseScreen.setVisible(true);
        }
        else if (listener.equals(HELP)){
            helpScreen.setVisible(true);
            helpScreen.requestFocus();
        }
    }

    /**
     * set CurrentLevel.
     *
     * @param currentLevel: game current level.
     */
    public void setCurrentLevel(int currentLevel) {
        Misc.currentLevel = currentLevel;
    }
}
