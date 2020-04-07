package gameLogic;


import UI.Misc;

import java.util.ArrayList;
import java.util.Random;

/**
 * Store the dimension of the Game Play Area.
 * Manages all movable and non-movable objects in the Game Play Area.
 */
public class Board {
    /**
     * store the board horizontal bound info.
     */
    private int sizeX = 27;
    /**
     * store the board vertical bound info.
     */
    private int sizeY = 27;
    /**
     * store the player info.
     */
    private Player player;
    /**
     * store the player position info.
     */
    private int[] playerInit;
    /**
     * store the score info.
     */
    private Score score;
    /**
     * Store the timer info.
     */
    private Timer timer;
    /**
     * store location randomizer generator info.
     */
    private LocationRandomizerGenerator boardArrayManager;
    /**
     * Store the level generator info.
     */
    private LevelGenerator generator;
    /**
     * store the player finder info.
     */
    private PlayerFinder playerFinder = new PlayerFinder();

    /**
     * Non-Default Constructor (Board Class).
     *
     * @param level An integer defining the difficulty selected.
     */
    public Board(int level) {
        generator = new LevelGenerator(sizeX, sizeY);
        boardArrayManager = new LocationRandomizerGenerator(generator.getBoard());
        boardArrayManager.randomizeExitPosition();
        boardArrayManager.objectGenerator(level, "Trap");
        boardArrayManager.objectGenerator(level, "Reward");
        boardArrayManager.objectGenerator(level, "Enemy");


        playerInit = new int[]{1, 1};
        player = new Player(playerInit);

        score = new Score();
        timer = new Timer();
    }

    /**
     * Non Default Constructor (Board Class).
     * To be used for recreating board when transiting to next level.
     *
     * @param level       An integer defining the difficulty selected.
     * @param scoreAmount An integer holding the current score from the current level.
     */
    public Board(int level, int scoreAmount) {
        generator = new LevelGenerator(sizeX, sizeY);
        boardArrayManager = new LocationRandomizerGenerator(generator.getBoard());
        boardArrayManager.randomizeExitPosition();
        boardArrayManager.objectGenerator(level, "Trap");
        boardArrayManager.objectGenerator(level, "Reward");
        boardArrayManager.objectGenerator(level, "Enemy");
        playerInit = new int[]{1, 1};
        player = new Player(playerInit);
        score = new Score(scoreAmount);
        timer = new Timer();

    }

    /**
     * Non Default Constructor (Board Class)
     * To be used for recreating board when transiting to next level.
     *
     * @param level       An integer defining the difficulty selected.
     * @param scoreAmount An integer holding the current score from the current level.
     * @param oldTimer    A timer class that has the old information.
     */
    public Board(int level, int scoreAmount, Timer oldTimer) {
        generator = new LevelGenerator(sizeX, sizeY);
        boardArrayManager = new LocationRandomizerGenerator(generator.getBoard());
        boardArrayManager.randomizeExitPosition();
        boardArrayManager.objectGenerator(level, "Trap");
        boardArrayManager.objectGenerator(level, "Reward");
        boardArrayManager.objectGenerator(level, "Enemy");

        playerInit = new int[]{1, 1};
        player = new Player(playerInit);

        score = new Score(scoreAmount);
        timer = new Timer(oldTimer);
    }

    /**
     * Set the difficulty of the game.
     *
     * @param choice Difficulty of the Game.
     */
    public void setDifficulty(LevelGenerator.Difficulty choice) {
        generator.setLevel(choice);
    }

    public LocationRandomizerGenerator getBoardArrayManager() {
        return boardArrayManager;
    }

    /**
     * Get the Player of this board.
     *
     * @return this Board's Player.
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Get the Position of this Board's Player.
     *
     * @return the position of this Board's Player.
     */
    public int[] getPlayerPos() {
        return player.getPosition();
    }


    /**
     * Get the score of this Board class.
     *
     * @return this Board class's Score.
     */
    public Score getScore() {
        return score;
    }

    /**
     * Get the timer of this Board Class.
     *
     * @return this Board Class's Timer.
     */
    public Timer getTimer() {
        return timer;
    }

    /**
     * Provide enemy functionality of approaching the player using the nearest path (Breadth First Search Algorithm).
     *
     * @param enemy The enemy that is chasing the player.
     */
    public void chaseThePlayer(Enemy enemy) {
        int[] nextMove;
        nextMove = playerFinder.shortPathStep(boardArrayManager.getBoard(), enemy.getPosition(), player.getPosition());
        for (int i = 0; i < boardArrayManager.getEnemyArrayManager().size(); i++) {
            if (nextMove[0] == boardArrayManager.getEnemyArrayManager().get(i).getPosition()[0] && nextMove[1] == boardArrayManager.getEnemyArrayManager().get(i).getPosition()[1]) {
                return;
            }
        }
        enemy.move(nextMove);
    }

    /**
     * test the game is over or not.
     *
     * @param enemy the enemy info.
     * @return true if enemy catch the player or score become negative, else return false.
     */
    public boolean isGameOver(Enemy enemy) {
        boolean test = false;
        if (enemy.getPosition()[0] == player.getPosition()[0] && enemy.getPosition()[1] == player.getPosition()[1])
            test = true;
        if (score.isNegative())
            test = true;
        return test;
    }
}





