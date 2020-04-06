package UI;

import gameLogic.Board;
import gameLogic.LevelGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
/**
 * Storing the information when the program.
 */
public class GameScreen extends JPanel {

    private int step;
    private DrawLive player;
    private DrawDead exit;
    private DrawLive enemy;

    private ArrayList<DrawDead> wallCell;
    private ArrayList<DrawDead> pathCell;
    private ArrayList<DrawDead> trapACell;
    private ArrayList<DrawDead> trapBCell;
    private ArrayList<DrawDead> rewardACell;
    private ArrayList<DrawDead> rewardBCell;

    private LevelGenerator.Difficulty mode;
    private Board board;
    private int[][] map;
    private static Music music;
    private boolean despawnTraps = true;
    private boolean isReleased = true;

    private JPanel nextScreen;
    private EndScreen endScreen;
    private Misc misc;
    //default controls
    private int UP = KeyEvent.VK_W;
    private int DOWN = KeyEvent.VK_S;
    private int LEFT = KeyEvent.VK_A;
    private int RIGHT = KeyEvent.VK_D;

    /**
     * Non-Default Constructor(GameScreen class)
     * @param step: how far the charater can move at each time
     */
    public GameScreen(int step) {
        this.step = step;
        addKeyListener(new listener());
        music = new Music();
        music.playSound();
    }

    /**
     * set misc info
     * @param misc:
     */
    public void setMisc(Misc misc){
        this.misc = misc;
    }

    /**
     * get the control information
     */
    public void setControlUP(int UP){
        this.UP = UP;
    }
    public void setControlDOWN(int DOWN){
        this.DOWN = DOWN;
    }
    public void setControlLEFT(int LEFT){
        this.LEFT = LEFT;
    }
    public void setControlRIGHT(int RIGHT){
        this.RIGHT = RIGHT;
    }

    /**
     * get the control information
     */
    public int getUP() {
        return UP;
    }
    public int getDOWN() {
        return DOWN;
    }
    public int getLEFT() {
        return LEFT;
    }
    public int getRIGHT() {
        return RIGHT;
    }

    /**
     * get the background music
     * @return the background music
     */
    public Music getMusic(){ return music; }

    /**
     * set the next Screen variable
     */
    public void setNextScreen(NextScreen next){
        nextScreen = next;
    }

    /**
     * set the End Screen variable
     */
    public void setEnd(EndScreen endScreen){
        this.endScreen = endScreen;
    }

    /**
     * set the difficulty of the game
     * @param mode store the game difficult
     */
    public void setDifficulty(LevelGenerator.Difficulty mode){
        this.mode = mode;
        startGame();
    }

    /**
     * Set the stuff that need to be use for the player press start button.
     */
    private void startGame(){
        removeAll();

        board = new Board(Misc.getCurrentLevel(), Misc.getScoreContainer());
        board.setDifficulty(mode);

        wallCell = new ArrayList<>();
        pathCell = new ArrayList<>();
        trapACell = new ArrayList<>();
        trapBCell = new ArrayList<>();
        rewardACell = new ArrayList<>();
        rewardBCell = new ArrayList<>();
        this.map = board.getBoardArrayManager().getBoard();


        player = new DrawLive(board.getPlayerPos(), step, DrawLive.cellType.PLAYER);
        exit = new DrawDead(board.getBoardArrayManager().getExit().getPosition(), step, DrawDead.cellType.EXIT);
        for(int i = 0; i < Math.min(Misc.getCurrentLevel(), 3); i++) {
            enemy = new DrawLive(board.getBoardArrayManager().getEnemyPos(i), step, DrawLive.cellType.ENEMY);
            add(enemy);
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
     * Setup all the stuff that in the game
     */
    private void setUp(){
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


        player = new DrawLive(board.getPlayerPos(), step, DrawLive.cellType.PLAYER);
        exit = new DrawDead(board.getBoardArrayManager().getExit().getPosition(), step, DrawDead.cellType.EXIT);
        for(int i = 0; i < Math.min(Misc.getCurrentLevel(), 3); i++) {
            enemy = new DrawLive(board.getBoardArrayManager().getEnemyPos(i), step, DrawLive.cellType.ENEMY);
            add(enemy);
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
     * create the game board of the game
     */
    private void createBoard() {
        int pos[];
        for (int y = 0; y < map[0].length; y++) {
            for (int x = 0; x < map.length; x++) {
                pos = new int[]{x, y};

                if (board.getBoardArrayManager().isObject(x, y, "Trap")) {
                    int index = board.getBoardArrayManager().objectFinder(x, y, "Trap");
                    char trapType = board.getBoardArrayManager().getTrapArrayManager().get(index).getType();
                    if (trapType == 'A'){
                        DrawDead trapA = new DrawDead(pos, step, DrawDead.cellType.TRAPTYPEA);
                        trapACell.add(trapA);
                        add(trapA);
                    } else if (trapType == 'B'){
                        DrawDead trapB = new DrawDead(pos, step, DrawDead.cellType.TRAPTYPEB);
                        trapBCell.add(trapB);
                        add(trapB);
                    }
                }
                if (board.getBoardArrayManager().isObject(x, y, "Reward")) {
                    int index = board.getBoardArrayManager().objectFinder(x, y, "Reward");
                    char rewardType = board.getBoardArrayManager().getRewardArrayManager().get(index).getType();
                    if (rewardType == 'A') {
                        DrawDead rewardA = new DrawDead(pos, step, DrawDead.cellType.REWARDTYPEA);
                        rewardACell.add(rewardA);
                        add(rewardA);

                        DrawDead path = new DrawDead(pos, step, DrawDead.cellType.PATH);
                        pathCell.add(path);
                        add(path);
                    } else if (rewardType == 'B') {
                        DrawDead rewardB = new DrawDead(pos, step, DrawDead.cellType.REWARDTYPEB);
                        rewardBCell.add(rewardB);
                        add(rewardB);

                        DrawDead path = new DrawDead(pos, step, DrawDead.cellType.PATH);
                        pathCell.add(path);
                        add(path);
                    }
                }
                else if (map[x][y] == 0) {
                    createWall(x, y, pos);

                } else {
                    DrawDead path = new DrawDead(pos, step, DrawDead.cellType.PATH);
                    pathCell.add(path);
                    add(path);
                }
            }
        }
    }

    /**
     * create the wall of the game;
     * @param x
     * @param y
     * @param pos
     */
    private void createWall(int x, int y, int[] pos){
        boolean left = false;
        boolean right = false;
        boolean top = false;
        boolean down = false;

        if (board.getBoardArrayManager().isInBounds(x - 1, y) && board.getBoardArrayManager().isWall(x - 1, y)){ left = true; }
        if (board.getBoardArrayManager().isInBounds(x + 1, y) && board.getBoardArrayManager().isWall(x + 1, y)){ right = true; }
        if (board.getBoardArrayManager().isInBounds(x, y + 1) && board.getBoardArrayManager().isWall(x, y + 1)){ down = true; }
        if (board.getBoardArrayManager().isInBounds(x, y - 1) && board.getBoardArrayManager().isWall(x, y - 1)){ top = true; }

        DrawDead wall = new DrawDead(pos, step, DrawDead.cellType.WALL);
        wall.setWallDirection(top, left, down, right);
        wallCell.add(wall);
        add(wall);
    }

    public Board getBoard(){ return this.board; }

    private class listener extends KeyAdapter {


        @Override
        public void keyReleased(KeyEvent e) {
            isReleased = true;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            Misc.setTime(board.getTimer().getSeconds());
            int key = e.getKeyCode();
            int[] playerPos = board.getPlayerPos();
            if (isReleased) {
                isReleased = false;

                //player movement
                if (key == UP){
                    if(!board.getBoardArrayManager().isWall(playerPos[0], playerPos[1] - 1)) {
                        board.getPlayer().moveUp();
                    }
                    player.setPlayerUP();
                    player.setNewPosition(board.getPlayerPos());
                    repaint();
                } else if (key == DOWN){
                    if(!board.getBoardArrayManager().isWall(playerPos[0], playerPos[1] + 1)) {
                        board.getPlayer().moveDown();
                    }
                    player.setPlayerDOWN();
                    player.setNewPosition(board.getPlayerPos());
                    repaint();
                } else if (key == RIGHT){
                    if(!board.getBoardArrayManager().isWall(playerPos[0] + 1, playerPos[1])) {
                        board.getPlayer().moveRight();
                    }
                    player.setPlayerRIGHT();
                    player.setNewPosition(board.getPlayerPos());
                    repaint();
                } else if (key == LEFT){
                    if(!board.getBoardArrayManager().isWall(playerPos[0]-  1, playerPos[1])) {
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


                    if(despawnTraps) {
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
                        if(rewardBCell.isEmpty()){
                            board.getBoardArrayManager().unlockExit();
                            exit.setLockUnlocked();
                            repaint();
                        }
                    }


                    DrawDead newPath = new DrawDead(rewardPos, step, DrawDead.cellType.PATH);
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
                Misc.setScore(board.getScore().getScore());
            }

            for(int i = 0; i < Math.min(Misc.getCurrentLevel(), 3); i++) {
                board.chaseThePlayer(board.getBoardArrayManager().getEnemyArrayManager().get(i));
                if(board.isGameOver(board.getBoardArrayManager().getEnemyArrayManager().get(i))) {
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



