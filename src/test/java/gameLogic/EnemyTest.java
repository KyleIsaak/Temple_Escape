package gameLogic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {

    @Test
    void getPosition() {
        int[] test = {1, 1};
        Enemy enemy = new Enemy(test);
        assertArrayEquals(test, enemy.getPosition());
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4})
    void testValidMove(int y) {
        int[] newPosition = {5, y};
        int[] originalPosition = {5, 3};
        Enemy enemy = new Enemy(originalPosition);
        assertDoesNotThrow(() -> enemy.move(newPosition));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 7})
    void testInvalidMove(int y) {
        int[] newPosition = {5, y};
        int[] originalPosition = {5, 3};
        Enemy enemy = new Enemy(originalPosition);
        assertThrows(UnsupportedOperationException.class, () -> enemy.move(newPosition));
    }

    @Test
    void setPosition() {
        int[] test = {1, 1};
        Enemy enemy = new Enemy(test);
        int[] testSetPosition = {0, 0};
        enemy.setPosition(testSetPosition);
        assertArrayEquals(testSetPosition, enemy.getPosition());
    }
}