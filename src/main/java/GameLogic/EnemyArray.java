package GameLogic;

import java.util.ArrayList;

/**
 * Implements the interface of ArrayFinderManager (Enemy Objects)
 */
public class EnemyArray extends ArrayFinderManager {

    public boolean isObject (int x, int y, ArrayList<?> objectArray){
        for (Object object : objectArray) {
            Enemy currentTrap = (Enemy) object;
            int[] current = currentTrap.getPosition();
            if ((current[0] == x) && (current[1] == y)) {
                return true;
            }
        }
        return false;
    }

    public int objectFinder (int x, int y, ArrayList<?> objectArray){
        for (int i = 0; i < objectArray.size(); i++){
            Enemy currentTrap = (Enemy) objectArray.get(i);
            int[] current = currentTrap.getPosition();
            if ((current[0] == x) && (current[1] == y)) {
                return i;
            }
        }
        return -1;
    }

}