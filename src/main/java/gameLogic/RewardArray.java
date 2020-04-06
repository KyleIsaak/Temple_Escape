package gameLogic;

import java.util.ArrayList;

/**
 * Implements the interface of ArrayFinderManager (Rewards Objects)
 */
public class RewardArray extends ArrayFinderManager {

    public boolean isObject (int x, int y, ArrayList<?> objectArray){
        for (Object object : objectArray) {
            Reward currentTrap = (Reward) object;
            int[] current = currentTrap.getPosition();
            if ((current[0] == x) && (current[1] == y)) {
                return true;
            }
        }
        return false;
    }

    public int objectFinder (int x, int y, ArrayList<?> objectArray){
        for (int i = 0; i < objectArray.size(); i++){
            Reward currentTrap = (Reward) objectArray.get(i);
            int[] current = currentTrap.getPosition();
            if ((current[0] == x) && (current[1] == y)) {
                return i;
            }
        }
        return -1;
    }

}
