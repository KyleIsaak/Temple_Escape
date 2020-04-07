package gameLogic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RewardTest {

    @Test
    void getPosition() {
        int[] test = {1, 1};
        Reward rewardA = new RewardTypeA(test);
        Reward rewardB = new RewardTypeB(test);

        assertArrayEquals(test, rewardA.getPosition());
        assertArrayEquals(test, rewardB.getPosition());
    }

    @Test
    public void getRewardAmount() {
        Reward rewardA = new RewardTypeA();
        Reward rewardB = new RewardTypeB();

        int testRewardAmount = 50;
        assertEquals(testRewardAmount, rewardA.getRewardAmount());

        testRewardAmount = 100;
        assertEquals(testRewardAmount, rewardB.getRewardAmount());
    }

    @Test
    void setPosition() {
        int[] test = {1, 1};
        int[] testSetPosition = {0, 0};

        Reward rewardA = new RewardTypeA(test);
        Reward rewardB = new RewardTypeA(test);

        rewardA.setPosition(testSetPosition);
        rewardB.setPosition(testSetPosition);

        assertArrayEquals(testSetPosition, rewardA.getPosition());
        assertArrayEquals(testSetPosition, rewardB.getPosition());
    }

    @Test
    public void setRewardAmount() {
        Reward rewardA = new RewardTypeA();
        Reward rewardB = new RewardTypeB();

        assertEquals('A', rewardA.getType());
        assertEquals('B', rewardB.getType());
    }

}