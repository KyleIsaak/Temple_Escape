package gameLogic;

import java.util.ArrayList;

/**
 * Implements the interface of ArrayFinderManager (Traps Objects).
 */
public class TrapArray extends ArrayFinderManager {
    /**
     * testing the the given position is the given Object.
     *
     * @param x           An integer for the x position.
     * @param y           An integer for the y position
     * @param objectArray The array containing the Objects.
     * @return true if same .
     */
    public boolean isObject(int x, int y, ArrayList<?> objectArray) {
        for (Object object : objectArray) {
            Trap currentTrap = (Trap) object;
            int[] current = currentTrap.getPosition();
            if ((current[0] == x) && (current[1] == y)) {
                return true;
            }
        }
        return false;
    }

    /**
     * find the object.
     *
     * @param x           An integer for the x position.
     * @param y           An integer for the y position.
     * @param objectArray The array containing the Objects.
     * @return the number of the object, else return -1.
     */
    public int objectFinder(int x, int y, ArrayList<?> objectArray) {
        for (int i = 0; i < objectArray.size(); i++) {
            Trap currentTrap = (Trap) objectArray.get(i);
            int[] current = currentTrap.getPosition();
            if ((current[0] == x) && (current[1] == y)) {
                return i;
            }
        }
        return -1;
    }

}
