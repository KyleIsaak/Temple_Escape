package GameLogic;
import GameLogic.*;
import UI.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;


public class BoardTest{

    @ParameterizedTest
    @ValueSource (ints = {1, 2 ,3})
    void numberOfTrapsCreatedBasedOnLevel (int level) {
        Board board = new Board(level);
        int numberofTrapACreated = 0;
        int numberofTrapBCreated = 0;
        for (int i = 0; i < board.getTrapArrayManager().size(); i++) {
            if (board.getTrapArrayManager().get(i).getType() == 'A') {
                numberofTrapACreated++;
            }
            if (board.getTrapArrayManager().get(i).getType() == 'B'){
                numberofTrapBCreated++;
            }
        }
        assertEquals(level * 2, numberofTrapACreated);
        assertEquals(level, numberofTrapBCreated);
    }

    @Test
    void trapLocationBounds (){
        Board board = new Board(3);
        boolean InBounds = true;
        for (int i = 0; i < board.getTrapArrayManager().size(); i++){
            int[] position = board.getTrapArrayManager().get(i).getPosition();
            if ((position[0] < 0 || position[0] >= 27) || (position[1] < 0 || position[1] >= 27)){
                InBounds = false;
            }
        }
        assertTrue(InBounds);
    }

     @Test
    void totalDamageDeductionCheck (){
        Board board = new Board(1);
        int intialScore = 500;
        for (int i = 0; i < 27; i++){
            for (int j = 0; j < 27; j++){
                if (board.isTrap(i,j)){
                    int trapIndex = board.trapFinder(i, j);
                    intialScore = intialScore - board.getTrapArrayManager().get(trapIndex).getDamage();
                }
            }
        }
        assertEquals(0,intialScore);
    }

    @ParameterizedTest
    @ValueSource (ints = {1, 2 ,3})
    void numberOfRewardsCreatedBasedOnLevel (int level) {
        Board board = new Board(level);
        int numberofRewardACreated = 0;
        for (int i = 0; i < board.getRewardArrayManager().size(); i++) {
            if (board.getTrapArrayManager().get(i).getType() == 'A') {
                numberofRewardACreated++;
            }
        }

        int numberofRewardBCreated = 0;
        for (int i = 0; i < board.getRewardArrayManager().size(); i++){
            if (board.getRewardArrayManager().get(i).getType() == 'B'){
                numberofRewardBCreated++;
            }
        }
        assertEquals(level * 2, numberofRewardACreated);
        assertEquals(level, numberofRewardBCreated);
    }


    @Test
    void RewardLocationBounds (){
        Board board = new Board(3);
        boolean InBounds = true;
        for (int i = 0; i < board.getRewardArrayManager().size(); i++){
            int[] position = board.getRewardArrayManager().get(i).getPosition();
            if ((position[0] < 0 || position[0] >= 27) || (position[1] < 0 || position[1] >= 27)){
                InBounds = false;
            }
        }
        assertTrue(InBounds);
    }

    @Test
    void totalRewardsAdditionCheck (){
        Board board = new Board(1);
        int intialScore = 0;
        for (int i = 0; i < 27; i++){
            for (int j = 0; j < 27; j++){
                if (board.isReward(i,j)){
                    int RewardIndex = board.rewardFinder(i, j);
                    intialScore = intialScore + board.getRewardArrayManager().get(RewardIndex).getRewardAmount();
                }
            }
        }
        assertEquals(200,intialScore);
    }

    @Test
    void ExitLocationChecker (){
        Board board = new Board(3);
        boolean InBounds = true;
        int[] exitPosition = board.getExit().getPosition();
        if ((exitPosition[0] < 0 || exitPosition[0] >= 27) || (exitPosition[1] < 0 || exitPosition[1] >= 27)){
            InBounds = false;
        }
        if (!(board.isWall(exitPosition[0] - 1, exitPosition[1]) || board.isWall(exitPosition[0] + 1, exitPosition[1]) || board.isWall(exitPosition[0], exitPosition[1] - 1) || board.isWall(exitPosition[0], exitPosition[1] + 1))){
            InBounds = false;
        }
        assertTrue(InBounds);
    }
}

