package GameLogic;

import java.util.ArrayList;

public abstract class ArrayFinderManager {

    /**
     * Check whether that location on the map contains that type of object.
     * @param x An integer for the x position.
     * @param y An integer for the y position
     * @return true when it contains a trap and vise versa.
     */
    public boolean isObject (int x, int y, ArrayList<?> objectArray){
        throw new UnsupportedOperationException();
    }

    public int objectFinder (int x, int y, ArrayList<?> objectArray){
        throw new UnsupportedOperationException();
    }

}
