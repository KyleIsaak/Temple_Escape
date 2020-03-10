package UI;

import GameLogic.Board;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Interface extends JFrame implements ActionListener{
    
    private int step;
    private Input input;
    private DrawCell player;
    private ArrayList<DrawCell> wallCell;
    private ArrayList<DrawCell> pathCell;
    private Board board;

    public Interface(int step, Board board){

        this.step = step;
        this.input = new Input();
        this.board = board;
        addKeyListener(input);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(30, 30, 1000, 1000);

        player = new DrawCell(new int[]{0, 0}, DrawCell.cellType.PLAYER);
        add(player);
        wallCell = new ArrayList<>();
        pathCell = new ArrayList<>();

        createBoard();

        setVisible(true);
    }


    private void createBoard(){
        int[][] map= board.getBoard();
        int pos[];
        for (int y = 0; y < map[0].length; y++){
            for (int x = 0; x < map.length; x++){
                pos = new int[]{x * step, y * step};
                if (map[x][y] == 0){
                    DrawCell wall = new DrawCell(pos, DrawCell.cellType.WALL);
                    wallCell.add(wall);
                    add(wall);
                } else{
                    DrawCell path = new DrawCell(pos, DrawCell.cellType.PATH);
                    pathCell.add(path);
                    add(path);
                }
            }
        }
    }

    int[] pos;
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("test");
        pos = input.getDirection();
        board.movePlayer(pos);
        player.repaint();
    }

//    public void renderPlayer(int[] pos) {
//        pos = new int[]{pos[0] * step, pos[1] * step};
//        player.setNewPosition(pos);
//        window.setVisible(true);
//    }

//    public void renderBoard(int[][] board){
//        int pos[];
//        for (int y = 0; y < board[0].length; y++){
//            for (int x = 0; x < board.length; x++){
//                pos = new int[]{x * step, y * step};
//                if (board[x][y] == 0){
//                    window.getContentPane().add(new DrawCell(pos, DrawCell.cellType.WALL));
//                    window.setVisible(true);
//                } else{
//                    window.getContentPane().add(new DrawCell(pos, DrawCell.cellType.PATH));
//                    window.setVisible(true);
//                }
//            }
//        }
//    }




}
