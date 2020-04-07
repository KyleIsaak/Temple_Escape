package gameLogic;

import java.util.ArrayList;

/**
 * Abstract class as an interface for finder functionality of an array.
 */
public abstract class ArrayFinderManager {

    /**
     * Check whether that location on the map contains that type of object.
     *
     * @param x           An integer for the x position.
     * @param y           An integer for the y position
     * @param objectArray The array containing the Objects.
     * @return true when it contains a trap and vise versa.
     */
    public boolean isObject(int x, int y, ArrayList<?> objectArray) {
        throw new UnsupportedOperationException();
    }

    /**
     * Check whether that location on the map contains that type of object and return the index of the object in the array.
     *
     * @param x           An integer for the x position.
     * @param y           An integer for the y position.
     * @param objectArray The array containing the Objects.
     * @return Return the index of the object in the object Array.
     */
    public int objectFinder(int x, int y, ArrayList<?> objectArray) {
        throw new UnsupportedOperationException();
    }

}
