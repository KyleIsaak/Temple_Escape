package UI;

        import GameLogic.Score;

        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

public class Misc extends JPanel implements ActionListener {
    private static int currentLevel = 1;
    private static int scoreContainer = 100;
    private JButton button_pause;
    private JButton button_TxtScore;
    private static JButton button_score;
    GameScreen gameScreen;
    JPanel pauseScreen;
    private final String PAUSE = " pause ";
    private final String SCORE = " score: ";

    public Misc(GameScreen gameScreen, JPanel pauseScreen){
        this.gameScreen = gameScreen;
        this.pauseScreen = pauseScreen;
        setLayout(null);

        button_pause = new Button(PAUSE);
        button_TxtScore = new Button(SCORE);
        button_score = new Button("100");

        add(button_pause);
        button_pause.setBounds(0, 50, 100, 40);
        button_pause.addActionListener(this);

        add(button_TxtScore);
        button_TxtScore.setBounds(0, 120, 100, 40);
        button_TxtScore.setEnabled(false);

        add(button_score);
        button_score.setBounds(0, 160, 100, 40);
        button_score.setEnabled(false);


        setBackground(Color.decode("#483b3a"));
        setFocusable(true);
        setVisible(false);
    }

    public static int getScoreContainer() {return scoreContainer; }
    public static void setScore(int score){
        scoreContainer = score;
        button_score.setText(String.valueOf(score));
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
            pauseScreen.requestFocus();
            pauseScreen.setVisible(true);
        }
    }

}
