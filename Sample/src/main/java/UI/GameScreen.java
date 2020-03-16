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


public class GameScreen extends JPanel implements ActionListener {

    private int step;
    private JButton button_pause;
    private DrawLive player;
    private ArrayList<DrawDead> wallCell;
    private ArrayList<DrawDead> pathCell;
    private ArrayList<DrawDead> trapACell;
    private ArrayList<DrawDead> trapBCell;
    private Board board;
    private int[][] map;
    private final String PAUSE = " pause ";
    private JPanel pauseScreen;

    //default controls
    int UP = KeyEvent.VK_W;
    int DOWN = KeyEvent.VK_S;
    int LEFT = KeyEvent.VK_A;
    int RIGHT = KeyEvent.VK_D;

    public GameScreen(int step, Board board, JPanel pauseScreen) {
        wallCell = new ArrayList<>();
        pathCell = new ArrayList<>();
        trapACell = new ArrayList<>();
        trapBCell = new ArrayList<>();

        this.pauseScreen = pauseScreen;
        this.step = step;
        this.board = board;
        board.setDifficulty(LevelGenerator.Difficulty.EASY);
        this.map = board.getBoard();

        addPauseButton();

        player = new DrawLive(board.getPlayerPos(), step, DrawLive.cellType.PLAYER);
        add(player);

        createBoard();

        setBackground(Color.decode("#483b3a"));
        setLayout(new OverlayLayout(this));
        addKeyListener(new listener());
        setFocusable(true);
        setVisible(false);
    }

    private void addPauseButton() {
        Font font;
        try {
            InputStream input = GameScreen.class.getResourceAsStream("/font.ttf");
            System.out.println(input);
            font = Font.createFont(Font.TRUETYPE_FONT, input);
            font = font.deriveFont(18f);
        } catch (Exception e){
            e.printStackTrace();
            font = new Font("serif", Font.PLAIN, 14);
        }
        button_pause = new JButton(PAUSE);
        button_pause.setForeground(Color.white);
        button_pause.setFont(font);
        button_pause.setBorder(BorderFactory.createRaisedBevelBorder());
        button_pause.setBackground(null);
        button_pause.addActionListener(this);
        button_pause.setActionCommand(PAUSE);
        add(button_pause);
        button_pause.setAlignmentX(0.97f);
        button_pause.setAlignmentY(0.1f);
    }
    private void createBoard() {
        int pos[];
        for (int y = 0; y < map[0].length; y++) {
            for (int x = 0; x < map.length; x++) {
                pos = new int[]{x, y};
                if (board.isContain(x, y)) {
                    int index = board.trapFinder(x, y);
                    char trapType = board.getTrapArrayManager().get(index).getType();
                    if (trapType == 'A'){
                        DrawDead trapA = new DrawDead(pos, step, DrawDead.cellType.TRAPTYPEA);
                        trapACell.add(trapA);
                        add(trapA);
                    }
                    else if (trapType == 'B'){
                        DrawDead trapB = new DrawDead(pos, step, DrawDead.cellType.TRAPTYPEB);
                        trapBCell.add(trapB);
                        add(trapB);
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

    public void setButton(boolean bool){
        button_pause.setVisible(bool);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String listener = actionEvent.getActionCommand();
        if (listener.equals(PAUSE)){
            pauseScreen.requestFocus();
            pauseScreen.setVisible(true);
            button_pause.setVisible(false);
        }
    }

    private class listener extends KeyAdapter {
        boolean isReleased = false;


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
                if (key == UP){
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

            }
        }
    }
}



