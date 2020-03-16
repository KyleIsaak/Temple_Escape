package UI;

import GameLogic.Board;
import GameLogic.LevelGenerator;

import javax.swing.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Interface extends JFrame {

    private int step;
    //    private Input input;
    private DrawCell player;
    private DrawCell exit;
    private DrawCell enemy;
    //private DrawCell path;
    private ArrayList<DrawCell> wallCell;
    private ArrayList<DrawCell> pathCell;
    private ArrayList<DrawCell> trapACell;
    private ArrayList<DrawCell> trapBCell;
    private ArrayList<DrawCell> rewardACell;
    private ArrayList<DrawCell> rewardBCell;
    private Board board;
    private int[][] map;

    public Interface(int step, Board board) {

        this.step = step;
        this.board = board;
        this.map = board.getBoard();
        addKeyListener(new listener());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new OverlayLayout(getContentPane()));

        player = new DrawCell(board.getPlayerPos(), step, DrawCell.cellType.PLAYER);
        add(player);
        exit = new DrawCell(board.getExit().getPosition(), step, DrawCell.cellType.EXIT);
        add(exit);
        enemy = new DrawCell(board.getEnemyPos(), step, DrawCell.cellType.ENEMY);
        add(enemy);

        wallCell = new ArrayList<>();
        pathCell = new ArrayList<>();
        trapACell = new ArrayList<>();
        trapBCell = new ArrayList<>();
        rewardACell = new ArrayList<>();
        rewardBCell = new ArrayList<>();

        createBoard();
        setSize(1000, 1000);
        setVisible(true);
    }

    private void createBoard() {
        int pos[];
        for (int y = 1; y < map[0].length; y++) {
            for (int x = 1; x < map.length; x++) {
                pos = new int[]{x, y};
                if (board.isTrap(x, y)) {
                    int index = board.trapFinder(x, y);
                    char trapType = board.getTrapArrayManager().get(index).getType();
                    if (trapType == 'A') {
                        DrawCell trapA = new DrawCell(pos, step, DrawCell.cellType.TRAPTYPEA);
                        trapACell.add(trapA);
                        add(trapA);
                    } else if (trapType == 'B') {
                        DrawCell trapB = new DrawCell(pos, step, DrawCell.cellType.TRAPTYPEB);
                        trapBCell.add(trapB);
                        add(trapB);
                    }
                }
                if (board.isReward(x, y)) {
                    int index = board.rewardFinder(x, y);
                    char rewardType = board.getRewardArrayManager().get(index).getType();
                    if (rewardType == 'A') {
                        DrawCell rewardA = new DrawCell(pos, step, DrawCell.cellType.REWARDTYPEA);
                        rewardACell.add(rewardA);
                        add(rewardA);
                        DrawCell path = new DrawCell(pos, step, DrawCell.cellType.PATH);
                        pathCell.add(path);
                        add(path);
                    } else if (rewardType == 'B') {
                        DrawCell rewardB = new DrawCell(pos, step, DrawCell.cellType.REWARDTYPEB);
                        rewardBCell.add(rewardB);
                        add(rewardB);
                        DrawCell path = new DrawCell(pos, step, DrawCell.cellType.PATH);
                        pathCell.add(path);
                        add(path);
                    }
                } else if (map[x][y] == 0) {
                    createWall(x, y, pos);

                } else {
                    DrawCell path = new DrawCell(pos, step, DrawCell.cellType.PATH);
                    pathCell.add(path);
                    add(path);
                }
            }
        }
    }

    private void createWall(int x, int y, int[] pos) {
        boolean left = false;
        boolean right = false;
        boolean top = false;
        boolean down = false;

        if (board.isInBounds(x - 1, y) && board.isWall(x - 1, y)) {
            left = true;
        }
        if (board.isInBounds(x + 1, y) && board.isWall(x + 1, y)) {
            right = true;
        }
        if (board.isInBounds(x, y + 1) && board.isWall(x, y + 1)) {
            down = true;
        }
        if (board.isInBounds(x, y - 1) && board.isWall(x, y - 1)) {
            top = true;
        }

        DrawCell wall = new DrawCell(pos, step, DrawCell.cellType.WALL);
        wall.setWallDirection(top, left, down, right);
        wallCell.add(wall);
        add(wall);
    }

    private class listener extends KeyAdapter {
        boolean isReleased = false;
        int UP = KeyEvent.VK_W;
        int DOWN = KeyEvent.VK_S;
        int LEFT = KeyEvent.VK_A;
        int RIGHT = KeyEvent.VK_D;

        @Override
        public void keyReleased(KeyEvent e) {
            isReleased = true;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            int[] playerPos = board.getPlayerPos();
            if (isReleased) {
                isReleased = false;

                if (key == UP) {
                    if (!board.isWall(playerPos[0], playerPos[1] - 1)) {
                        board.getPlayer().moveUp();
                        player.setPlayerUP();
                    }

                } else if (key == DOWN) {
                    if (!board.isWall(playerPos[0], playerPos[1] + 1)) {
                    board.getPlayer().moveDown();
                    player.setPlayerDOWN();
                    }

                } else if (key == RIGHT) {
                    if (!board.isWall(playerPos[0] + 1, playerPos[1])) {
                    board.getPlayer().moveRight();
                    player.setPlayerRIGHT();
                    }

                } else if (key == LEFT) {
                    if (!board.isWall(playerPos[0] - 1, playerPos[1])) {
                        board.getPlayer().moveLeft();
                        player.setPlayerLEFT();
                    }

                }
                player.setNewPosition(board.getPlayerPos());
                repaint();
                // Check whether player stepped on any traps
                if (board.isTrap(playerPos[0], playerPos[1])) {
                    // Test
                    System.out.print("Trap Stepped On: ");
                    int trapIndex = board.trapFinder(playerPos[0], playerPos[1]);
                    int damage = board.getTrapArrayManager().get(trapIndex).getDamage();
                    board.getScore().subtractScore(damage);
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
                            DrawCell oldReward = rewardACell.get(i);
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
                            DrawCell oldReward = rewardBCell.get(i);
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
                            exit.setNewPosition(board.getExit().getPosition());
                            repaint();
                        }

                    }

                    DrawCell newPath = new DrawCell(rewardPos, step, DrawCell.cellType.PATH);
                    pathCell.add(newPath);
                    add(newPath);

                    board.getRewardArrayManager().remove(rewardIndex);  //Remove reward from its array
                    newPath.updateCell(DrawCell.cellType.PATH);
                    repaint();
                    //newPath.setPlayerREWARD();    //If we want a sprite of the player getting a reward

                    // Test
                    System.out.println("Reward Stepped On At Position: " + rewardPos[0] + "," + rewardPos[1]);
                    System.out.println("New score: " + board.getScore().getScore());
                    System.out.println();
                }

            }

            if ((board.getExit().getPosition()[0] == playerPos[0]) && (board.getExit().getPosition()[1] == playerPos[1])) {
                if (board.isExitUnlocked()) {
                    //Test
                    System.out.println("Game Completed");
                }
            }
        }
    }
}


