package GameLogic;

public class Score {
    private int currentScore;

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

    public void resetScore() {
        this.currentScore = 0;
    }
}
