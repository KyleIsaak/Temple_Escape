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
        for (int i = 0; i < board.getBoardArrayManager().getTrapArrayManager().size(); i++) {
            if (board.getBoardArrayManager().getTrapArrayManager().get(i).getType() == 'A') {
                numberofTrapACreated++;
            }
            if (board.getBoardArrayManager().getTrapArrayManager().get(i).getType() == 'B'){
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
        for (int i = 0; i < board.getBoardArrayManager().getTrapArrayManager().size(); i++){
            int[] position = board.getBoardArrayManager().getTrapArrayManager().get(i).getPosition();
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
                if (board.getBoardArrayManager().isObject(i, j, "Trap")){
                    int trapIndex = board.getBoardArrayManager().objectFinder(i, j, "Trap");
                    intialScore = intialScore - board.getBoardArrayManager().getTrapArrayManager().get(trapIndex).getDamage();
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
        for (int i = 0; i < board.getBoardArrayManager().getRewardArrayManager().size(); i++) {
            if (board.getBoardArrayManager().getTrapArrayManager().get(i).getType() == 'A') {
                numberofRewardACreated++;
            }
        }

        int numberofRewardBCreated = 0;
        for (int i = 0; i < board.getBoardArrayManager().getRewardArrayManager().size(); i++){
            if (board.getBoardArrayManager().getRewardArrayManager().get(i).getType() == 'B'){
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
        for (int i = 0; i < board.getBoardArrayManager().getRewardArrayManager().size(); i++){
            int[] position = board.getBoardArrayManager().getRewardArrayManager().get(i).getPosition();
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
                if (board.getBoardArrayManager().isObject(i, j, "Reward")){
                    int RewardIndex = board.getBoardArrayManager().objectFinder(i, j, "Reward");
                    intialScore = intialScore + board.getBoardArrayManager().getRewardArrayManager().get(RewardIndex).getRewardAmount();
                }
            }
        }
        assertEquals(200,intialScore);
    }

    @Test
    void ExitLocationChecker (){
        Board board = new Board(3);
        boolean InBounds = true;
        int[] exitPosition = board.getBoardArrayManager().getExit().getPosition();
        if ((exitPosition[0] < 0 || exitPosition[0] >= 27) || (exitPosition[1] < 0 || exitPosition[1] >= 27)){
            InBounds = false;
        }
        if (!(board.getBoardArrayManager().isWall(exitPosition[0] - 1, exitPosition[1]) || board.getBoardArrayManager().isWall(exitPosition[0] + 1, exitPosition[1]) || board.getBoardArrayManager().isWall(exitPosition[0], exitPosition[1] - 1) || board.getBoardArrayManager().isWall(exitPosition[0], exitPosition[1] + 1))){
            InBounds = false;
        }
        assertTrue(InBounds);
    }
}

