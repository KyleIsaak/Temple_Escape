package UI;

import GameLogic.Board;
import GameLogic.LevelGenerator;

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

    private JPanel nextScreen;
    //default controls
    private int UP = KeyEvent.VK_W;
    private int DOWN = KeyEvent.VK_S;
    private int LEFT = KeyEvent.VK_A;
    private int RIGHT = KeyEvent.VK_D;

    public GameScreen(int step) {
        this.step = step;
        addKeyListener(new listener());
        music = new Music();
        music.playSound();
    }

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

    public Music getMusic(){ return music; }
    public void setNextScreen(NextScreen next){
        nextScreen = next;
    }
    public void setDifficulty(LevelGenerator.Difficulty mode){
        this.mode = mode;
        startGame();
    }

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
        this.map = board.getBoard();


        player = new DrawLive(board.getPlayerPos(), step, DrawLive.cellType.PLAYER);
        exit = new DrawDead(board.getExit().getPosition(), step, DrawDead.cellType.EXIT);
        for(int i = 0; i < Math.min(Misc.getCurrentLevel(), 3); i++) {
            enemy = new DrawLive(board.getEnemyPos(i), step, DrawLive.cellType.ENEMY);
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

    private void setUp(){
        removeAll();

        board = new Board(Misc.getCurrentLevel(), Misc.getScoreContainer(), board.getTimer());
        board.setDifficulty(mode);

        wallCell = new ArrayList<>();
        pathCell = new ArrayList<>();
        trapACell = new ArrayList<>();
        trapBCell = new ArrayList<>();
        rewardACell = new ArrayList<>();
        rewardBCell = new ArrayList<>();
        this.map = board.getBoard();


        player = new DrawLive(board.getPlayerPos(), step, DrawLive.cellType.PLAYER);
        exit = new DrawDead(board.getExit().getPosition(), step, DrawDead.cellType.EXIT);
        for(int i = 0; i < Math.min(Misc.getCurrentLevel(), 3); i++) {
            enemy = new DrawLive(board.getEnemyPos(i), step, DrawLive.cellType.ENEMY);
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

    private void createBoard() {
        int pos[];
        for (int y = 0; y < map[0].length; y++) {
            for (int x = 0; x < map.length; x++) {
                pos = new int[]{x, y};

                if (board.isTrap(x, y)) {
                    int index = board.trapFinder(x, y);
                    char trapType = board.getTrapArrayManager().get(index).getType();
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
                if (board.isReward(x, y)) {
                    int index = board.rewardFinder(x, y);
                    char rewardType = board.getRewardArrayManager().get(index).getType();
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

    private void createWall(int x, int y, int[] pos){
        boolean left = false;
        boolean right = false;
        boolean top = false;
        boolean down = false;

        if (board.isInBounds(x - 1, y) && board.isWall(x - 1, y)){ left = true; }
        if (board.isInBounds(x + 1, y) && board.isWall(x + 1, y)){ right = true; }
        if (board.isInBounds(x, y + 1) && board.isWall(x, y + 1)){ down = true; }
        if (board.isInBounds(x, y - 1) && board.isWall(x, y - 1)){ top = true; }

        DrawDead wall = new DrawDead(pos, step, DrawDead.cellType.WALL);
        wall.setWallDirection(top, left, down, right);
        wallCell.add(wall);
        add(wall);
    }

    public Board getBoard(){ return this.board; }

    private class listener extends KeyAdapter {
        boolean isReleased = true;

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
                if (key == UP){
                    System.out.println("UP");
                    if(!board.isWall(playerPos[0], playerPos[1] - 1)) {
                        board.getPlayer().moveUp();
                    }
                    player.setPlayerUP();
                    player.setNewPosition(board.getPlayerPos());
                    repaint();
                } else if (key == DOWN){
                    if(!board.isWall(playerPos[0], playerPos[1] + 1)) {
                        board.getPlayer().moveDown();
                    }
                    player.setPlayerDOWN();
                    player.setNewPosition(board.getPlayerPos());
                    repaint();
                } else if (key == RIGHT){
                    if(!board.isWall(playerPos[0] + 1, playerPos[1])) {
                        board.getPlayer().moveRight();
                    }
                    player.setPlayerRIGHT();
                    player.setNewPosition(board.getPlayerPos());
                    repaint();
                } else if (key == LEFT){
                    if(!board.isWall(playerPos[0]-  1, playerPos[1])) {
                        board.getPlayer().moveLeft();
                    }
                    player.setPlayerLEFT();
                    player.setNewPosition(board.getPlayerPos());
                    repaint();
                }

                // Check whether player stepped on any traps
                if (board.isTrap(playerPos[0], playerPos[1])) {
                    // Test
                    System.out.print("Trap Stepped On: ");
                    int trapIndex = board.trapFinder(playerPos[0], playerPos[1]);
                    int[] trapPos = board.getTrapArrayManager().get(trapIndex).getPosition();
                    int damage = board.getTrapArrayManager().get(trapIndex).getDamage();
                    char trapType = board.getTrapArrayManager().get(trapIndex).getType();
                    board.getScore().subtractScore(damage);


                    if(despawnTraps) {
                        if (trapType == 'A') {
                            for (int i = 0; i < trapACell.size(); i++) {
                                DrawDead oldTrap = trapACell.get(i);
                                if (oldTrap.getPosition()[0] == trapPos[0]) {
                                    if (oldTrap.getPosition()[1] == trapPos[1]) {
                                        System.out.println("Trap type A removed");
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
                                        System.out.println("Trap type B removed");
                                        oldTrap.setVisible(false);
                                        trapBCell.remove(oldTrap);
                                        remove(oldTrap);
                                        break;
                                    }
                                }
                            }
                        }
                        board.getTrapArrayManager().remove(trapIndex);  //Remove reward from its array
                    }


                    //Test
                    System.out.print(board.getScore().getScore());
                    System.out.println();
                }

                if (board.isReward(playerPos[0], playerPos[1])) {
                    int rewardIndex = board.rewardFinder(playerPos[0], playerPos[1]);
                    int[] rewardPos = board.getRewardArrayManager().get(rewardIndex).getPosition();
                    int rewardAmount = board.getRewardArrayManager().get(rewardIndex).getRewardAmount();
                    char rewardType = board.getRewardArrayManager().get(rewardIndex).getType();
                    board.getScore().addScore(rewardAmount);


                    if (rewardType == 'A') {
                        for (int i = 0; i < rewardACell.size(); i++) {
                            DrawDead oldReward = rewardACell.get(i);
                            if (oldReward.getPosition()[0] == rewardPos[0]) {
                                if (oldReward.getPosition()[1] == rewardPos[1]) {
                                    System.out.println("Reward type A removed");
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
                                    System.out.println("Reward type B removed");
                                    oldReward.setVisible(false);
                                    rewardBCell.remove(oldReward);
                                    remove(oldReward);
                                    break;
                                }
                            }
                        }
                        //System.out.println(rewardBCell);
                        if(rewardBCell.isEmpty()){
                            System.out.println("All keys collected");
                            board.unlockExit();
                            exit.setLockUnlocked();
                            repaint();
                        }
                    }


                    DrawDead newPath = new DrawDead(rewardPos, step, DrawDead.cellType.PATH);
                    pathCell.add(newPath);
                    add(newPath);

                    board.getRewardArrayManager().remove(rewardIndex);  //Remove reward from its array
                    //newPath.updateCell(DrawDead.cellType.PATH);
                    //repaint();
                    //newPath.setPlayerREWARD();    //If we want a sprite of the player getting a reward

                    // Test
                    System.out.println("Reward Stepped On At Position: " + rewardPos[0] + "," + rewardPos[1]);
                    System.out.println("New score: " + board.getScore().getScore());
                    System.out.println();
                }

                if (board.getExit().getIsUnlocked()) {
                    if (board.getPlayer().getPosition()[0] == board.getExit().getPosition()[0] &&
                            board.getPlayer().getPosition()[1] == board.getExit().getPosition()[1]) {
                        System.out.println("next level");
                        Misc.incCurrentLevel();
                        setUp();
                        nextScreen.requestFocus();
                        nextScreen.setVisible(true);

                    }
                }
                Misc.setScore(board.getScore().getScore());
            }

            for(int i=0; i < Math.min(Misc.getCurrentLevel(), 3); i++) {
                board.chaseThePlayer(board.getEnemyArrayManager().get(i), i);
                int j = i+1;
                System.out.println("Enemy " + j + " current location" + board.getEnemyArrayManager().get(i).getPosition()[0] + ',' + board.getEnemyArrayManager().get(i).getPosition()[1]);
                if(board.isGameOver(board.getEnemyArrayManager().get(i)))
                {
                    System.out.println("Game Over");
                }
            }
        }
    }
}



