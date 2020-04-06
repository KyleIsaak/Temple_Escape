package gameLogic;
import gameLogic.*;
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
    @ValueSource (ints = {1, 2, 3})
    void isEnemy_Test(int level){
       //test base on the level
       Board test=new Board(level);

       Enemy enemy=test.getBoardArrayManager().getEnemyArrayManager().get(level-1);
       assertEquals(true,test.getBoardArrayManager().isObject(enemy.getPosition()[0],enemy.getPosition()[1],"Enemy"));
       //enemy cannot stay the player position
       assertEquals(false,test.getBoardArrayManager().isObject(1,1, "Enemy"));

    }

    @ParameterizedTest
    @ValueSource (ints = {1, 2 ,3})
    void getEnemy_test(int level){
        Board board=new Board(level);
        ArrayList<Enemy> test=board.getBoardArrayManager().getEnemyArrayManager();
        for(int i=0;i<level;i++) {
            assertEquals(test.get(i), board.getBoardArrayManager().getEnemyArrayManager().get(i));
        }
    }

    @ParameterizedTest
    @ValueSource (ints = {1, 2 ,3})
    void getEnemyPos(int level) {
        Board board = new Board(level);
        ArrayList<Enemy> test = board.getBoardArrayManager().getEnemyArrayManager();
        for (int i = 0; i < level; i++) {
            boolean test1 = true;
            test1 = board.getBoardArrayManager().isObject(test.get(i).getPosition()[0], test.get(i).getPosition()[1], "Enemy");
            assertEquals(true, test1);
        }

    }
    @ParameterizedTest
    @ValueSource (ints = {1, 2 ,3})
    //test enemyLocationRandomizer Function
    void testEnemyLocationRandomizerInTheMaze (int level){
        Board board = new Board(level);
        boolean InBound = true;
        for (int i = 0; i < board.getBoardArrayManager().getEnemyArrayManager().size(); i++){
            int[] position = board.getBoardArrayManager().getEnemyArrayManager().get(i).getPosition();
            if ((position[0] < 0 || position[0] >= 27) || (position[1] < 0 || position[1] >= 27)){
                InBound = false;
            }
        }
        assertEquals(true,InBound);
    }

    @ParameterizedTest
    @ValueSource (ints = {1, 2 ,3})
    //test function EnemyGenerator function
    void numberOfEnemyCreatedBasedOnLevel (int level) {
        Board board = new Board(level);
        int numberofEnemyCreated = 0;
        for (int i = 0; i < 27; i++) {
            for(int j = 0 ; j < 27; j++) {
                if(board.getBoardArrayManager().isObject(i, j, "Enemy")) {
                    numberofEnemyCreated++;
                }
            }
        }
        assertEquals(level, numberofEnemyCreated);
    }

    @ParameterizedTest
    @ValueSource (ints = {1, 2 ,3})
    void EnemyFinderTest(int level) {
       Board board=new Board(level);
       ArrayList<Enemy> test = board.getBoardArrayManager().getEnemyArrayManager();
       for(int i = 0; i < test.size(); i++) {
           int x = test.get(i).getPosition()[0];
           int y = test.get(i).getPosition()[1];
           int result = board.getBoardArrayManager().objectFinder(x, y, "Enemy");
           assertEquals(result, i);
       }
    }

    @Test
    void isItShortestPath()
    {
        Board board= new Board(1);
        int[] test3={1,1};
        assertArrayEquals(board.getPlayerPos(),test3);
        int[] test={1,2};
        board.getBoardArrayManager().enemyArrayManager.get(0).setPosition(test);
        int numberOfStep=0;
            board.chaseThePlayer(board.getBoardArrayManager().getEnemyArrayManager().get(0));
            numberOfStep++;
        assertArrayEquals(board.getPlayerPos(),board.getBoardArrayManager().getEnemyArrayManager().get(0).getPosition());

        int[] test1={2,1};
        board.getBoardArrayManager().enemyArrayManager.get(0).setPosition(test1);
        int numberOfStep1=0;
        if(board.getPlayerPos()!=board.getBoardArrayManager().getEnemyArrayManager().get(0).getPosition()) {
            board.chaseThePlayer(board.getBoardArrayManager().getEnemyArrayManager().get(0));
            numberOfStep1++;
        }
        assertArrayEquals(board.getPlayerPos(),board.getBoardArrayManager().getEnemyArrayManager().get(0).getPosition());
        assertEquals(1,numberOfStep);
        assertEquals(1,numberOfStep1);
    }



}

