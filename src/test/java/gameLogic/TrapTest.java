package gameLogic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrapTest {

    @Test
    void getPosition() {
        int[] test = {1, 1};
        Trap trapA = new TrapTypeA(test);
        Trap trapB = new TrapTypeB(test);

        assertArrayEquals(test, trapA.getPosition());
        assertArrayEquals(test, trapB.getPosition());
    }

    @Test
    public void getDamage() {
        Trap trapA = new TrapTypeA();
        Trap trapB = new TrapTypeB();

        int testTrapAmount = 150;
        assertEquals(testTrapAmount, trapA.getDamage());

        testTrapAmount = 200;
        assertEquals(testTrapAmount, trapB.getDamage());
    }

    @Test
    void setPosition() {
        int[] test = {1, 1};
        int[] testSetPosition = {0, 0};

        Trap trapA = new TrapTypeA(test);
        Trap trapB = new TrapTypeA(test);

        trapA.setPosition(testSetPosition);
        trapB.setPosition(testSetPosition);

        assertArrayEquals(testSetPosition, trapA.getPosition());
        assertArrayEquals(testSetPosition, trapB.getPosition());
    }

    @Test
    public void setTrapAmount() {
        Trap trapA = new TrapTypeA();
        Trap trapB = new TrapTypeB();

        assertEquals('A', trapA.getType());
        assertEquals('B', trapB.getType());
    }

}