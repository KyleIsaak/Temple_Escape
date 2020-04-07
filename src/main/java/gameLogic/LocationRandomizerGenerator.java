package gameLogic;


import java.util.Random;

/**
 * Extension of BoardArrayManager : Add additional randomizing location functionality for objects.
 */
public class LocationRandomizerGenerator extends BoardArrayManager {
    /**
     * Non-default constructor
     *
     * @param board, the main maze of the game
     */
    public LocationRandomizerGenerator(int[][] board) {
        super(board);
    }

    /**
     * Integer Randomizer with the bound of latitudinal length of board.
     *
     * @return an randomized integer with the bound of x latitudinal of board.
     */
    private int integerRandomizer() {
        Random random = new Random();
        return (random.nextInt(27 - 5) + 4);
    }

    /**
     * Create objects in the gameplay area depending on the difficulty selected.
     *
     * @param difficultyLevel an integer that specify the difficulty selected.
     * @param type            a string that defines the type of object needed to be created.
     */
    public void objectGenerator(int difficultyLevel, String type) {
        if (type.equals("Enemy")) {
            if (difficultyLevel < 3) {
                for (int i = 0; i < difficultyLevel; i++) {
                    typeGenerator('A', type);
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    typeGenerator('A', type);
                }
            }
        } else {
            for (int i = 0; i < difficultyLevel * 2; i++) {
                typeGenerator('A', type);
            }
            for (int i = 0; i < difficultyLevel; i++) {
                typeGenerator('B', type);
            }
        }
    }

    /**
     * objectGenerator's Helper function: Facilitates in creating the specific objects.
     * Implements the factory method design pattern.
     *
     * @param objectType A character that specify the type of specific object selected.
     * @param classType  A string that defines the general type of object being created.
     */
    private void typeGenerator(char objectType, String classType) {
        int[] objectPosition;
        objectPosition = objectLocationRandomizer();
        switch (classType) {
            case "Trap":
                switch (objectType) {
                    case 'A':  //Spikes
                        Trap trapObjectA = new TrapTypeA();
                        trapObjectA.setPosition(objectPosition);
                        trapArrayManager.add(trapObjectA);
                        break;

                    case 'B':  //Lava Pits
                        Trap trapObjectB = new TrapTypeB();
                        trapObjectB.setPosition(objectPosition);
                        trapArrayManager.add(trapObjectB);
                        break;
                }
                break;

            case "Reward":
                switch (objectType) {
                    case 'A':  //Keys
                        Reward rewardObjectA = new RewardTypeA();
                        rewardObjectA.setPosition(objectPosition);
                        rewardArrayManager.add(rewardObjectA);
                        break;

                    case 'B':  //Coins
                        Reward rewardObjectB = new RewardTypeB();
                        rewardObjectB.setPosition(objectPosition);
                        rewardArrayManager.add(rewardObjectB);
                        break;
                }
                break;

            case "Enemy":
                Enemy enemy = new Enemy(objectPosition);
                enemyArrayManager.add(enemy);
                break;
        }
    }

    /**
     * objectGenerator's Helper Function: Facilitates in the randomizing the location of the objects.
     */
    private int[] objectLocationRandomizer() {
        int x = integerRandomizer();
        int y = integerRandomizer();

        while ((isWall(x, y)) || (trapFinder.isObject(x, y, trapArrayManager)) || (rewardFinder.isObject(x, y, rewardArrayManager)) || (isExit(x, y))) {
            x = integerRandomizer();
            y = integerRandomizer();
        }
        return new int[]{x, y};
    }

    /**
     * Randomize the position of this Board class's exit.
     */
    public void randomizeExitPosition() {
        int[] exitPos;
        int x, y;
        do {
            exitPos = randomExitPicker();
            x = exitPos[0];
            y = exitPos[1];
        } while (((isWall(x, y)) || (!isInBounds(x, y)) || (x < 5 && y < 5) || (rewardFinder.isObject(x, y, rewardArrayManager)) || (trapFinder.isObject(x, y, trapArrayManager))));

        exit.setPosition(new int[]{x, y});
    }

    /**
     * Helper function for randomizeExitPosition.
     *
     * @return A pair of integer for position of exit.
     */
    private int[] randomExitPicker() {
        Random random = new Random();
        int randX = (random.nextInt(4));

        int[] exitPos = new int[2];

        switch (randX) {
            case 0:                 // lock to left wall
                exitPos[0] = 1;
                exitPos[1] = integerRandomizer();
                break;

            case 1:                 // lock to right wall
                exitPos[0] = 25;
                exitPos[1] = integerRandomizer();
                break;

            case 2:                 // lock to top wall
                exitPos[0] = integerRandomizer();
                exitPos[1] = 1;
                break;

            case 3:                 // lock to bottom wall
                exitPos[0] = integerRandomizer();
                exitPos[1] = 25;
                break;
        }
        return exitPos;
    }
}
