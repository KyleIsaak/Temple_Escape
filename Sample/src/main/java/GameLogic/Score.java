package GameLogic;

public class Score {
    private int currentScore;

    //Constructors
    public Score (){ this.currentScore = 100; }

    public Score(int score){ this.currentScore = score; }

    //Getters
    public int getScore() {
        return this.currentScore;
    }

    //Setters
    public void setScore(int newScore) {
        this.currentScore = newScore;
    }

    public void addScore(int scoreToAdd) {
        this.currentScore += scoreToAdd;
    }

    public void subtractScore(int scoreToSubtract) {
        this.currentScore -= scoreToSubtract;
    }

    public boolean isNegative (){
        return currentScore < 0;
    }

    public void resetScore() {
        this.currentScore = 0;
    }
}
