package UI;

import GameLogic.Board;

import javax.swing.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Interface extends JFrame{

    private int step;
//    private Input input;
    private DrawCell player;
    private ArrayList<DrawCell> wallCell;
    private ArrayList<DrawCell> pathCell;
    private Board board;
    private int[][] map;

    public Interface(int step, Board board){

        this.step = step;
        this.board = board;
        this.map = board.getBoard();
        addKeyListener(new listener());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new OverlayLayout(getContentPane()));

        player = new DrawCell(board.getPlayerPos(), step, DrawCell.cellType.PLAYER);

        add(player);
        wallCell = new ArrayList<>();
        pathCell = new ArrayList<>();

        createBoard();
        setSize( 1000, 1000);
        setVisible(true);
    }

    private void createBoard(){
        int pos[];
        for (int y = 0; y < map[0].length; y++){
            for (int x = 0; x < map.length; x++){
                pos = new int[]{x, y};
                if (map[x][y] == 0){
                    createWall(x, y, pos);

                } else{
                    DrawCell path = new DrawCell(pos, step, DrawCell.cellType.PATH);
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
        public void keyReleased(KeyEvent e){
            isReleased = true;
        }

        @Override
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            if (isReleased){
                isReleased = false;
                if (key == UP){
                    board.getPlayer().moveUp();
                    player.setPlayerUP();
                } else if (key == DOWN){
                    board.getPlayer().moveDown();
                    player.setPlayerDOWN();
                } else if (key == RIGHT){
                    board.getPlayer().moveRight();
                    player.setPlayerRIGHT();
                } else if (key == LEFT){
                    board.getPlayer().moveLeft();
                    player.setPlayerLEFT();
                }
                player.setNewPosition(board.getPlayerPos());
                repaint();
            }
        }
    }

}
