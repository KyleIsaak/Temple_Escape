package GameLogic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {

    @Test
    void getPosition() {
        int[] test = {1, 1};
        Enemy enemy = new Enemy(test);
        assertArrayEquals(test, enemy.getPosition());
    }

//    @Test
//    void move() {
//        int[] test = {0, 4};
//        int[] move = {1, 11};
//        int[] AfterMove;
//        AfterMove = new int[]{1, 15};
//        Enemy enemy = new Enemy(test);
//        enemy.move(move);
//        assertArrayEquals(AfterMove, enemy.getPosition());
//    }

    @Test
    void setPosition() {
        int[] test = {1, 1};
        Enemy enemy = new Enemy(test);
        int[] testSetPosition = {0, 0};
        enemy.setPosition(testSetPosition);
        assertEquals(testSetPosition, enemy.getPosition());
    }
}
//
//    @Test
//    public void chaseThePlayer() {
//        int[] test={1,1};
//        Enemy enemy =new Enemy(test);
//        //Same y location,move x
//        int[] playerPosition={30,1};
//        int[] functionReturn=enemy.chaseThePlayer(playerPosition);
//        int[] expectedReturn={1,0};
//         assertArrayEquals(expectedReturn,functionReturn);
//        //Same X location,move y
//        int[] playerPosition1={1,30};
//        int[] functionReturn1=enemy.chaseThePlayer(playerPosition1);
//        int[] expectedReturn1={0,1};
//        assertArrayEquals(expectedReturn1,functionReturn1);
//
//        //Distance X>Y
//        int[] playerPosition2={2,30};
//        int[] functionReturn2=enemy.chaseThePlayer(playerPosition2);
//        int[] expectedReturn2={0,1};
//        assertArrayEquals(expectedReturn2,functionReturn2);
//
//        //Distance X>Y
//        int[] playerPosition3={30,2};
//        int[] functionReturn3=enemy.chaseThePlayer(playerPosition3);
//        int[] expectedReturn3={1,0};
//        assertArrayEquals(expectedReturn3,functionReturn3);
//
//         // Square Case
//        int[] playerPosition4={2,2};
//        int[] functionReturn4=enemy.chaseThePlayer(playerPosition4);
//        int[] expectedReturn4={1,0};
//        assertArrayEquals(expectedReturn4,functionReturn4);
//
//    }
//
//}