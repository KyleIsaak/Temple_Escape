package gameLogic;

/**
 * Keep Track of Player's Score Throughout the Game Session.
 */
public class Score {
    /**
     * store current score.
     */
    private int currentScore;

    /**
     * Default Constructor.
     */
    public Score() {
        this.currentScore = 100;
    }

    /**
     * Non-Default Constructor.
     *
     * @param score An integer to set the current score as.
     */
    public Score(int score) {
        this.currentScore = score;
    }

    /**
     * Get the current score.
     *
     * @return the current score.
     */
    public int getScore() {
        return this.currentScore;
    }

    /**
     * Add points to current score.
     *
     * @param scoreToAdd An integer of the points to be added.
     */
    public void addScore(int scoreToAdd) {
        this.currentScore += scoreToAdd;
    }

    /**
     * Subtract points from the current score.
     *
     * @param scoreToSubtract An integer of the point to be subtracted.
     */
    public void subtractScore(int scoreToSubtract) {
        this.currentScore -= scoreToSubtract;
    }

    /**
     * Check whether the current score is negative.
     *
     * @return true is the current score is negative and vise versa.
     */
    public boolean isNegative() {
        return currentScore < 0;
    }
}
