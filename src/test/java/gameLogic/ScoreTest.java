package gameLogic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreTest {

    @Test
    void getScore() {
        int testAmount = 513;
        Score scoreTest = new Score(testAmount);

        assertEquals(testAmount, scoreTest.getScore());
    }

    @Test
    public void addScore() {
        int testAmount = 100;
        Score scoreTest = new Score(testAmount);

        // Adding Positive Test
        int addAmount = 50;
        testAmount += addAmount;
        scoreTest.addScore(addAmount);
        assertEquals(testAmount, scoreTest.getScore());

        // Adding Zero Test
        addAmount = 0;
        testAmount += addAmount;
        scoreTest.addScore(addAmount);
        assertEquals(testAmount, scoreTest.getScore());

        // Adding Negative Test
        addAmount = -500;
        testAmount += addAmount;
        scoreTest.addScore(addAmount);
        assertEquals(testAmount, scoreTest.getScore());
    }

    @Test
    void subtractScore() {
        int testAmount = 100;
        Score scoreTest = new Score(testAmount);

        // Subtracting Positive Test
        int subAmount = 50;
        testAmount -= subAmount;
        scoreTest.subtractScore(subAmount);
        assertEquals(testAmount, scoreTest.getScore());

        // Subtracting Zero Test
        subAmount = 0;
        testAmount -= subAmount;
        scoreTest.subtractScore(subAmount);
        assertEquals(testAmount, scoreTest.getScore());

        // Subtracting Negative Test
        subAmount = -500;
        testAmount -= subAmount;
        scoreTest.subtractScore(subAmount);
        assertEquals(testAmount, scoreTest.getScore());
    }

    @Test
    public void isNegative() {
        // Negative Test
        int testAmount = -100;
        Score scoreTestNegative = new Score(testAmount);
        assertEquals(true, scoreTestNegative.isNegative());

        // Positive Test
        testAmount = 100;
        Score scoreTestPositive = new Score(testAmount);
        assertEquals(false, scoreTestPositive.isNegative());

        //Zero Test
        testAmount = 100;
        Score scoreTestZero = new Score(testAmount);
        assertEquals(false, scoreTestZero.isNegative());
    }

}