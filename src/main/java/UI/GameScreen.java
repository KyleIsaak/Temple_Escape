package UI;

import gameLogic.Board;
import gameLogic.LevelGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Storing the information when the program.
 */
public class GameScreen extends JPanel {
    /**
     * store how far the charater can move at each time.
     */
    private int step;
    /**
     * Store Draw live : player info.
     */
    private DrawLive player;
    /**
     * Store Draw live : exit info.
     */
    private DrawDead exit;
    /**
     * Store Draw live : enemy info.
     */
    private ArrayList<DrawLive> enemy;

    /**
     * Store Draw Dead : wall info.
     */
    private ArrayList<DrawDead> wallCell;
    /**
     * Store Draw Dead : path info.
     */
    private ArrayList<DrawDead> pathCell;
    /**
     * Store Draw Dead : trap A info.
     */
    private ArrayList<DrawDead> trapACell;
    /**
     * Store Draw Dead : trap B info.
     */
    private ArrayList<DrawDead> trapBCell;
    /**
     * Store Draw Dead : trap A info.
     */
    private ArrayList<DrawDead> rewardACell;
    /**
     * Store Draw Dead : trap B info.
     */
    private ArrayList<DrawDead> rewardBCell;

    /**
     * Store the info of difficult.
     */
    private LevelGenerator.Difficulty mode;
    /**
     * store the board info.
     */
    private Board board;
    /**
     * store the map info.
     */
    private int[][] map;
    /**
     * store the music info.
     */
    private Music music;
    /**
     * store the released info.
     */
    private boolean isReleased = true;
    /**
     * store the next Screen info.
     */
    private JPanel nextScreen;
    /**
     * store the end Screen info.
     */
    private EndScreen endScreen;
    /**
     * store the misc info.
     */
    private Misc misc;
    /**
     * default controls of Up.
     */
    private int UP = KeyEvent.VK_W;
    /**
     * default controls of Down.
     */
    private int DOWN = KeyEvent.VK_S;
    /**
     * default controls of left.
     */
    private int LEFT = KeyEvent.VK_A;
    /**
     * default controls of right.
     */
    private int RIGHT = KeyEvent.VK_D;

    /**
     * Non-Default Constructor(GameScreen class).
     *
     * @param step: how far the charater can move at each time.
     */
    public GameScreen(int step) {
        this.step = step;
        addKeyListener(new listener());
        music = new Music();
        music.playSound();
    }

    /**
     * set misc info.
     *
     * @param misc: store the misc info.
     */
    public void setMisc(Misc misc) {
        this.misc = misc;
    }

    /**
     * set the UP control information.
     * @param UP An integer that specify the new controls.
     */
    public void setControlUP(int UP) {
        this.UP = UP;
    }

    /**
     * set the DOWN control information.
     * @param DOWN An integer that specify the new controls.
     */
    public void setControlDOWN(int DOWN) {
        this.DOWN = DOWN;
    }

    /**
     * set the LEFT control information.
     * @param LEFT An integer that specify the new controls.
     */
    public void setControlLEFT(int LEFT) {
        this.LEFT = LEFT;
    }

    /**
     * set the RIGHT control information.
     * @param RIGHT An integer that specify the new controls.
     */
    public void setControlRIGHT(int RIGHT) {
        this.RIGHT = RIGHT;
    }

    /**
     * get the control information.
     * @return Return the key control for UP
     */
    public int getUP() {
        return UP;
    }

    /**
     * get the control information.
     * @return Return the key control for DOWN
     */
    public int getDOWN() {
        return DOWN;
    }

    /**
     * get the control information.
     * @return Return the key control for LEFT
     */
    public int getLEFT() {
        return LEFT;
    }

    /**
     * get the control information.
     * @return Return the key control for RIGHT
     */
    public int getRIGHT() {
        return RIGHT;
    }

    /**
     * get the background music.
     *
     * @return the background music.
     */
    public Music getMusic() {
        return music;
    }

    /**
     * set the next Screen variable.
     * @param next A nextScreen that set the current nextscreen as.
     */
    public void setNextScreen(NextScreen next) {
        nextScreen = next;
    }

    /**
     * set the End Screen variable.
     * @param endScreen A endScreen that set the current endScreen as.
     */
    public void setEnd(EndScreen endScreen) {
        this.endScreen = endScreen;
    }

    /**
     * set the difficulty of the game.
     *
     * @param mode store the game difficult.
     */
    public void setDifficulty(LevelGenerator.Difficulty mode) {
        this.mode = mode;
        misc.setCurrentLevel(1);
        misc.setScore(100);
        startGame();
    }

    /**
     * Set the stuff that need to be use for the player press start button.
     */
    private void startGame() {
        removeAll();

        board = new Board(Misc.getCurrentLevel(), Misc.getScoreContainer());
        board.setDifficulty(mode);
        enemy = new ArrayList<>();
        wallCell = new ArrayList<>();
        pathCell = new ArrayList<>();
        trapACell = new ArrayList<>();
        trapBCell = new ArrayList<>();
        rewardACell = new ArrayList<>();
        rewardBCell = new ArrayList<>();
        this.map = board.getBoardArrayManager().getBoard();


        player = new DrawLive(board.getPlayerPos(), step, DrawLive.CellType.PLAYER);
        exit = new DrawDead(board.getBoardArrayManager().getExit().getPosition(), step, DrawDead.CellType.EXIT);
        for (int i = 0; i < Math.min(Misc.getCurrentLevel(), 3); i++) {
            enemy.add(new DrawLive(board.getBoardArrayManager().getEnemyPos(i), step, DrawLive.CellType.ENEMY));
            add(enemy.get(i));
        }

        add(player);
        add(exit);

        createBoard();
        setBackground(Color.decode("#483b3a"));
        setLayout(new OverlayLayout(this));

        setFocusable(true);
        setVisible(false);
        repaint();
    }

    /**
     * Setup all the stuff that in the game.
     */
    private void setUp() {
        removeAll();
        isReleased = true;
        board = new Board(Misc.getCurrentLevel(), Misc.getScoreContainer(), board.getTimer());
        board.setDifficulty(mode);

        wallCell = new ArrayList<>();
        pathCell = new ArrayList<>();
        trapACell = new ArrayList<>();
        trapBCell = new ArrayList<>();
        rewardACell = new ArrayList<>();
        rewardBCell = new ArrayList<>();
        this.map = board.getBoardArrayManager().getBoard();


        player = new DrawLive(board.getPlayerPos(), step, DrawLive.CellType.PLAYER);
        exit = new DrawDead(board.getBoardArrayManager().getExit().getPosition(), step, DrawDead.CellType.EXIT);
        for (int i = 0; i < Math.min(Misc.getCurrentLevel(), 3); i++) {
            enemy.add(new DrawLive(board.getBoardArrayManager().getEnemyPos(i), step, DrawLive.CellType.ENEMY));
            add(enemy.get(i));
        }

        add(player);
        add(exit);

        createBoard();
        setBackground(Color.decode("#483b3a"));
        setLayout(new OverlayLayout(this));

        setFocusable(true);
        setVisible(false);
        repaint();
    }

    /**
     * create the game board of the game.
     */
    private void createBoard() {
        int[] pos;
        for (int y = 0; y < map[0].length; y++) {
            for (int x = 0; x < map.length; x++) {
                pos = new int[]{x, y};

                if (board.getBoardArrayManager().isObject(x, y, "Trap")) {
                    int index = board.getBoardArrayManager().objectFinder(x, y, "Trap");
                    char trapType = board.getBoardArrayManager().getTrapArrayManager().get(index).getType();
                    if (trapType == 'A') {
                        DrawDead trapA = new DrawDead(pos, step, DrawDead.CellType.TRAPTYPEA);
                        trapACell.add(trapA);
                        add(trapA);
                    } else if (trapType == 'B') {
                        DrawDead trapB = new DrawDead(pos, step, DrawDead.CellType.TRAPTYPEB);
                        trapBCell.add(trapB);
                        add(trapB);
                    }
                }
                if (board.getBoardArrayManager().isObject(x, y, "Reward")) {
                    int index = board.getBoardArrayManager().objectFinder(x, y, "Reward");
                    char rewardType = board.getBoardArrayManager().getRewardArrayManager().get(index).getType();
                    if (rewardType == 'A') {
                        DrawDead rewardA = new DrawDead(pos, step, DrawDead.CellType.REWARDTYPEA);
                        rewardACell.add(rewardA);
                        add(rewardA);

                        DrawDead path = new DrawDead(pos, step, DrawDead.CellType.PATH);
                        pathCell.add(path);
                        add(path);
                    } else if (rewardType == 'B') {
                        DrawDead rewardB = new DrawDead(pos, step, DrawDead.CellType.REWARDTYPEB);
                        rewardBCell.add(rewardB);
                        add(rewardB);

                        DrawDead path = new DrawDead(pos, step, DrawDead.CellType.PATH);
                        pathCell.add(path);
                        add(path);
                    }
                } else if (map[x][y] == 0) {
                    createWall(x, y, pos);

                } else {
                    DrawDead path = new DrawDead(pos, step, DrawDead.CellType.PATH);
                    pathCell.add(path);
                    add(path);
                }
            }
        }
    }

    /**
     * create the wall of the game.
     *
     * @param x    : x location
     * @param y    : y location
     * @param pos: location
     */
    private void createWall(int x, int y, int[] pos) {
        boolean left = false;
        boolean right = false;
        boolean top = false;
        boolean down = false;

        if (board.getBoardArrayManager().isInBounds(x - 1, y) && board.getBoardArrayManager().isWall(x - 1, y)) {
            left = true;
        }
        if (board.getBoardArrayManager().isInBounds(x + 1, y) && board.getBoardArrayManager().isWall(x + 1, y)) {
            right = true;
        }
        if (board.getBoardArrayManager().isInBounds(x, y + 1) && board.getBoardArrayManager().isWall(x, y + 1)) {
            down = true;
        }
        if (board.getBoardArrayManager().isInBounds(x, y - 1) && board.getBoardArrayManager().isWall(x, y - 1)) {
            top = true;
        }

        DrawDead wall = new DrawDead(pos, step, DrawDead.CellType.WALL);
        wall.setWallDirection(top, left, down, right);
        wallCell.add(wall);
        add(wall);
    }

    public Board getBoard() {
        return this.board;
    }

    private class listener extends KeyAdapter {


        @Override
        public void keyReleased(KeyEvent e) {
            isReleased = true;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            misc.setTime(board.getTimer().getSeconds());
            int key = e.getKeyCode();
            int[] playerPos = board.getPlayerPos();
            if (isReleased) {
                isReleased = false;

                //player movement
                if (key == UP) {
                    if (!board.getBoardArrayManager().isWall(playerPos[0], playerPos[1] - 1)) {
                        board.getPlayer().moveUp();
                    }
                    player.setPlayerUP();
                    player.setNewPosition(board.getPlayerPos());
                    repaint();
                } else if (key == DOWN) {
                    if (!board.getBoardArrayManager().isWall(playerPos[0], playerPos[1] + 1)) {
                        board.getPlayer().moveDown();
                    }
                    player.setPlayerDOWN();
                    player.setNewPosition(board.getPlayerPos());
                    repaint();
                } else if (key == RIGHT) {
                    if (!board.getBoardArrayManager().isWall(playerPos[0] + 1, playerPos[1])) {
                        board.getPlayer().moveRight();
                    }
                    player.setPlayerRIGHT();
                    player.setNewPosition(board.getPlayerPos());
                    repaint();
                } else if (key == LEFT) {
                    if (!board.getBoardArrayManager().isWall(playerPos[0] - 1, playerPos[1])) {
                        board.getPlayer().moveLeft();
                    }
                    player.setPlayerLEFT();
                    player.setNewPosition(board.getPlayerPos());
                    repaint();
                }

                // Check whether player stepped on any traps
                if (board.getBoardArrayManager().isObject(playerPos[0], playerPos[1], "Trap")) {
                    int trapIndex = board.getBoardArrayManager().objectFinder(playerPos[0], playerPos[1], "Trap");
                    int[] trapPos = board.getBoardArrayManager().getTrapArrayManager().get(trapIndex).getPosition();
                    int damage = board.getBoardArrayManager().getTrapArrayManager().get(trapIndex).getDamage();
                    char trapType = board.getBoardArrayManager().getTrapArrayManager().get(trapIndex).getType();
                    board.getScore().subtractScore(damage);


                    boolean despawnTraps = true;
                    if (trapType == 'A') {
                        for (int i = 0; i < trapACell.size(); i++) {
                            DrawDead oldTrap = trapACell.get(i);
                            if (oldTrap.getPosition()[0] == trapPos[0]) {
                                if (oldTrap.getPosition()[1] == trapPos[1]) {
                                    oldTrap.setVisible(false);
                                    trapACell.remove(oldTrap);
                                    remove(oldTrap);
                                    break;
                                }
                            }
                        }
                    }

                    if (trapType == 'B') {
                        for (int i = 0; i < trapBCell.size(); i++) {
                            DrawDead oldTrap = trapBCell.get(i);
                            if (oldTrap.getPosition()[0] == trapPos[0]) {
                                if (oldTrap.getPosition()[1] == trapPos[1]) {

                                    oldTrap.setVisible(false);
                                    trapBCell.remove(oldTrap);
                                    remove(oldTrap);
                                    break;
                                }
                            }
                        }
                    }
                    board.getBoardArrayManager().getTrapArrayManager().remove(trapIndex);  //Remove reward from its array
                }

                if (board.getBoardArrayManager().isObject(playerPos[0], playerPos[1], "Reward")) {
                    int rewardIndex = board.getBoardArrayManager().objectFinder(playerPos[0], playerPos[1], "Reward");
                    int[] rewardPos = board.getBoardArrayManager().getRewardArrayManager().get(rewardIndex).getPosition();
                    int rewardAmount = board.getBoardArrayManager().getRewardArrayManager().get(rewardIndex).getRewardAmount();
                    char rewardType = board.getBoardArrayManager().getRewardArrayManager().get(rewardIndex).getType();
                    board.getScore().addScore(rewardAmount);


                    if (rewardType == 'A') {
                        for (int i = 0; i < rewardACell.size(); i++) {
                            DrawDead oldReward = rewardACell.get(i);
                            if (oldReward.getPosition()[0] == rewardPos[0]) {
                                if (oldReward.getPosition()[1] == rewardPos[1]) {
                                    oldReward.setVisible(false);
                                    rewardACell.remove(oldReward);
                                    remove(oldReward);
                                    break;
                                }
                            }
                        }
                    }
                    if (rewardType == 'B') {

                        for (int i = 0; i < rewardBCell.size(); i++) {
                            DrawDead oldReward = rewardBCell.get(i);
                            if (oldReward.getPosition()[0] == rewardPos[0]) {
                                if (oldReward.getPosition()[1] == rewardPos[1]) {
                                    oldReward.setVisible(false);
                                    rewardBCell.remove(oldReward);
                                    remove(oldReward);
                                    break;
                                }
                            }
                        }
                        if (rewardBCell.isEmpty()) {
                            board.getBoardArrayManager().unlockExit();
                            exit.setLockUnlocked();
                            repaint();
                        }
                    }


                    DrawDead newPath = new DrawDead(rewardPos, step, DrawDead.CellType.PATH);
                    pathCell.add(newPath);
                    add(newPath);

                    board.getBoardArrayManager().getRewardArrayManager().remove(rewardIndex);  //Remove reward from its array
                }

                //check if exit is unlocked and goes to next level
                if (board.getBoardArrayManager().getExit().getIsUnlocked()) {
                    if (board.getPlayer().getPosition()[0] == board.getBoardArrayManager().getExit().getPosition()[0] &&
                            board.getPlayer().getPosition()[1] == board.getBoardArrayManager().getExit().getPosition()[1]) {
                        Misc.incCurrentLevel();
                        setUp();
                        nextScreen.requestFocus();
                        nextScreen.setVisible(true);

                    }
                }
                misc.setScore(board.getScore().getScore());
            }
            for (int i = 0; i < Math.min(Misc.getCurrentLevel(), 3); i++) {
                if (board.isGameOver(board.getBoardArrayManager().getEnemyArrayManager().get(i))) {
                    setVisible(false);
                    misc.setPause(false);
                    endScreen.setScore(board.getScore().getScore());
                    endScreen.setVisible(true);
                    endScreen.requestFocus();
                }
                board.chaseThePlayer(board.getBoardArrayManager().getEnemyArrayManager().get(i));
                enemy.get(i).setNewPosition(board.getBoardArrayManager().getEnemyArrayManager().get(i).getPosition());
                if (board.isGameOver(board.getBoardArrayManager().getEnemyArrayManager().get(i))) {
                    setVisible(false);
                    misc.setPause(false);
                    endScreen.setScore(board.getScore().getScore());
                    endScreen.setVisible(true);
                    endScreen.requestFocus();
                }
            }
        }
    }
}



