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

    @Test
    void move() {
        int[] test = {0, 4};
        int[] move = {1, 11};
        int[] AfterMove;
        AfterMove = new int[]{1, 15};
        Enemy enemy = new Enemy(test);
        enemy.move(move);
        assertArrayEquals(AfterMove, enemy.getPosition());
    }

    @Test
    void setPosition() {
        int[] test = {1, 1};
        Enemy enemy = new Enemy(test);
        int[] testSetPosition = {0, 0};
        enemy.setPosition(testSetPosition);
        assertEquals(testSetPosition, enemy.getPosition());
    }
}