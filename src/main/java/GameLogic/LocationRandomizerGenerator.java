package GameLogic;

import java.util.ArrayList;
import java.util.Random;

public class LocationRandomizerGenerator extends BoardArrayManager {
    public LocationRandomizerGenerator(int[][] board) {
        super(board);
    }

    private int integerRandomizer(){
        Random random = new Random();
        return (random.nextInt(27 - 5) + 4);
    }

    public void objectGenerator (int difficultyLevel, String type){
        for (int i = 0; i < difficultyLevel*2; i++) {
            typeGenerator('A', type);
        }
        for (int i = 0; i < difficultyLevel; i++){
            typeGenerator('B', type);
        }
    }

    private void typeGenerator (char objectType, String classType){
        int[] objectPosition;
        objectPosition = objectLocationRandomizer();
        switch (classType){
            case "Trap" :
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

            case "Reward" :
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

            case "Enemy" :
                Enemy enemy = new Enemy(objectPosition);
                enemyArrayManager.add(enemy);
            break;
        }
    }

    private int[] objectLocationRandomizer (){
        int x = integerRandomizer();
        int y = integerRandomizer();

        while ((isWall(x,y)) || (trapFinder.isObject(x,y, trapArrayManager)) || (rewardFinder.isObject(x, y, rewardArrayManager)) || (isExit(x, y))){
            x = integerRandomizer();
            y = integerRandomizer();
        }
        return new int []{x, y};
    }

    /**
     * Randomize the position of this Board class's exit.
     */
    public void randomizeExitPosition(){
        int[] exitPos;
        int x , y;
        do {
            exitPos = randomExitPicker();
            x = exitPos[0];
            y = exitPos[1];
        } while (( (!isInBounds(x,y))|| ( x < 5 && y < 5) || (rewardFinder.isObject(x, y, rewardArrayManager)) || (trapFinder.isObject(x, y, trapArrayManager))));

        exit.setPosition(new int[]{x, y});
    }

    /**
     * Helper function for randomizeExitPosition
     * @return A pair of integer for position of exit
     */
    private int[] randomExitPicker(){
        Random random = new Random();
        int randX = (random.nextInt(4));

        int[] exitPos = new int[2];

        switch(randX) {
            case 0:                 // lock to left wall
                exitPos[0] = 1;
                exitPos[1] = integerRandomizer();
                break;

            case 1:                 // lock to right wall
                exitPos[0] = 27 - 2;
                exitPos[1] = integerRandomizer();
                break;

            case 2:                 // lock to top wall
                exitPos[0] = integerRandomizer();
                exitPos[1] = 1;
                break;

            case 3:                 // lock to bottom wall
                exitPos[0] = integerRandomizer();
                exitPos[1] = 27 - 2;
                break;
        }
        return exitPos;
    }
}
