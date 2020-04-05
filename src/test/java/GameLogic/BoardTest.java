package GameLogic;
import GameLogic.*;
import UI.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.swing.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class BoardTest {

   @Test
    void Board_Non_default_Constructor_test() {
        Board test =new Board(1,1000);
        int[] testPlayerLocation={1,1};
        assertEquals(1,Misc.getCurrentLevel());
        assertEquals(100,Misc.getScoreContainer());
        assertArrayEquals(testPlayerLocation,test.getPlayerPos());
    }
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


    ////////////////Enemy test case/////////////////////
    @ParameterizedTest
    @ValueSource (ints = {1, 2 ,3,4,5})
    void isEnemy_Test(int level){
       //test base on the level
       Board test=new Board(level);

       //max enemy=3;
       if (level>=4)
           level=3;
       Enemy enemy=test.getEnemyArrayManager().get(level-1);
       assertEquals(true,test.isEnemy(enemy.getPosition()[0],enemy.getPosition()[1]));
       //enemy cannot stay the player position
       assertEquals(false,test.isEnemy(1,1));

    }

    @ParameterizedTest
    @ValueSource (ints = {1, 2 ,3,4,5})
    void getEnemy_test(int level)
    {

        Board board=new Board(level);
        //max enemy=3;
        if (level>=4)
            level=3;
        ArrayList<Enemy> test=board.getEnemyArrayManager();
        for(int i=0;i<level;i++)
            assertEquals(test.get(i),board.getEnemyArrayManager().get(i));
    }

    @ParameterizedTest
    @ValueSource (ints = {1, 2 ,3,4,5})
    void getEnemyPos(int level) {
        Board board = new Board(level);
        //max enemy=3;
        if (level >= 4)
            level = 3;
        ArrayList<Enemy> test = board.getEnemyArrayManager();
        for (int i = 0; i < level; i++) {
            boolean test1 = true;
            test1 = board.isEnemy(test.get(i).getPosition()[0], test.get(i).getPosition()[1]);
            assertEquals(true, test1);
        }

    }
    @ParameterizedTest
    @ValueSource (ints = {1, 2 ,3,4,5})
    //test enemyLocationRandomizer Function
    void testEnemyLocationRandomizerInTheMaze (int level){
        Board board = new Board(level);
        boolean InBound = true;
        for (int i = 0; i < board.getEnemyArrayManager().size(); i++){
            int[] position = board.getEnemyArrayManager().get(i).getPosition();
            if ((position[0] < 0 || position[0] >= 27) || (position[1] < 0 || position[1] >= 27)){
                InBound = false;
            }
        }
        assertEquals(true,InBound);
    }

    @ParameterizedTest
    @ValueSource (ints = {1, 2 ,3,4,5,6})
    //test function EnemyGenerator function
    void numberOfEnemyCreatedBasedOnLevel (int level) {
        Board board = new Board(level);
        int numberofEnemyCreated = 0;
        for (int i = 0; i <27; i++)
        {
            for(int j=0;j<27;j++)
            {
                if(board.isEnemy(i,j)==true)
                    numberofEnemyCreated ++;
            }
        }
        if(level>=4)
            level=3;
        assertEquals(level, numberofEnemyCreated);
    }

    @ParameterizedTest
    @ValueSource (ints = {1, 2 ,3,4,5,6})
    void EnemyFinderTest(int level)
    {
       Board board=new Board(level);
       ArrayList<Enemy> test = board.getEnemyArrayManager();
       for(int i=0;i<board.getEnemyArrayManager().size();i++)
       {
           int x=test.get(i).getPosition()[0];
           int y=test.get(i).getPosition()[1];
           int result=board.EnemyFinder(x,y);
           assertEquals(result,i);
       }
    }

}

