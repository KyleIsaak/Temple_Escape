package gameLogic;

import java.util.ArrayList;

/**
 * Implements the interface of ArrayFinderManager (Enemy Objects).
 */
public class EnemyArray extends ArrayFinderManager {
    /**
     * test given location is enemy or not.
     *
     * @param x           An integer for the x position.
     * @param y           An integer for the y position
     * @param objectArray The array containing the Objects.
     * @return true if given location is enemy, else return false.
     */
    public boolean isObject(int x, int y, ArrayList<?> objectArray) {
        for (Object object : objectArray) {
            Enemy currentTrap = (Enemy) object;
            int[] current = currentTrap.getPosition();
            if ((current[0] == x) && (current[1] == y)) {
                return true;
            }
        }
        return false;
    }

    /**
     * find the rank of the given location in the given Array(Enemy).
     *
     * @param x           An integer for the x position.
     * @param y           An integer for the y position.
     * @param objectArray The array containing the Objects.
     * @return the rank the given location object in the ArrayList.
     */
    public int objectFinder(int x, int y, ArrayList<?> objectArray) {
        for (int i = 0; i < objectArray.size(); i++) {
            Enemy currentTrap = (Enemy) objectArray.get(i);
            int[] current = currentTrap.getPosition();
            if ((current[0] == x) && (current[1] == y)) {
                return i;
            }
        }
        return -1;
    }

}
