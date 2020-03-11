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

    public Interface(int step, Board board){

        this.step = step;
        this.board = board;
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
        int[][] map= board.getBoard();
        int pos[];
        for (int y = 1; y < map[0].length; y++){
            for (int x = 0; x < map.length - 1; x++){
                pos = new int[]{x, y - 1};
                if (map[x][y] == 0){
                    DrawCell wall = new DrawCell(pos, step, DrawCell.cellType.WALL);
                    wallCell.add(wall);
                    add(wall);
                } else{
                    DrawCell path = new DrawCell(pos, step, DrawCell.cellType.PATH);
                    pathCell.add(path);
                    add(path);
                }
            }
        }
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
                } else if (key == DOWN){
                    board.getPlayer().moveDown();
                } else if (key == RIGHT){
                    board.getPlayer().moveRight();
                } else if (key == LEFT){
                    board.getPlayer().moveLeft();
                }
                player.setNewPosition(board.getPlayerPos());
                repaint();
            }
        }
    }

}
