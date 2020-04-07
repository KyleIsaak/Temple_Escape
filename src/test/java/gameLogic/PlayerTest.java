package gameLogic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class PlayerTest {

    @Test
    void getPosition() {
        int[] init = {1, 1};
        Player player = new Player(init);
        assertArrayEquals(init, player.getPosition());
    }

    @Test
    void moveUp() {
        int[] init = {1, 1};
        int[] moved = {1, 0};
        Player player = new Player(init);
        player.moveUp();
        assertArrayEquals(moved, player.getPosition());
    }

    @Test
    void moveLeft() {
        int[] init = {1, 1};
        int[] moved = {0, 1};
        Player player = new Player(init);
        player.moveLeft();
        assertArrayEquals(moved, player.getPosition());
    }

    @Test
    void moveRight() {
        int[] init = {1, 1};
        int[] moved = {2, 1};
        Player player = new Player(init);
        player.moveRight();
        assertArrayEquals(moved, player.getPosition());
    }

    @Test
    void moveDown() {
        int[] init = {1, 1};
        int[] moved = {1, 2};
        Player player = new Player(init);
        player.moveDown();
        assertArrayEquals(moved, player.getPosition());
    }
}
