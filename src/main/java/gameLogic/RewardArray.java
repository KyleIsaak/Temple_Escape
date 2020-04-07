package gameLogic;

import java.util.ArrayList;

/**
 * Implements the interface of ArrayFinderManager (Rewards Objects).
 */
public class RewardArray extends ArrayFinderManager {
    /**
     * test the given location is reward type or not.
     *
     * @param x           An integer for the x position.
     * @param y           An integer for the y position
     * @param objectArray The array containing the Objects.
     * @return true if given location is reward type.
     */
    public boolean isObject(int x, int y, ArrayList<?> objectArray) {
        for (Object object : objectArray) {
            Reward currentTrap = (Reward) object;
            int[] current = currentTrap.getPosition();
            if ((current[0] == x) && (current[1] == y)) {
                return true;
            }
        }
        return false;
    }

    /**
     * find the given position is which object in the object Array.
     *
     * @param x           An integer for the x position.
     * @param y           An integer for the y position.
     * @param objectArray The array containing the Objects.
     * @return the rank of object that in the object Array, if no, return -1.
     */
    public int objectFinder(int x, int y, ArrayList<?> objectArray) {
        for (int i = 0; i < objectArray.size(); i++) {
            Reward currentTrap = (Reward) objectArray.get(i);
            int[] current = currentTrap.getPosition();
            if ((current[0] == x) && (current[1] == y)) {
                return i;
            }
        }
        return -1;
    }

}
