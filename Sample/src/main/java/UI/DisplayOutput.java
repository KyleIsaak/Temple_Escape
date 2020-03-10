package UI;

import javax.swing.JFrame;
public class DisplayOutput {
    private JFrame window;
    private int step;

    private DrawCell player;

    public DisplayOutput(int step){
        this.window = new JFrame();
        this.step = step;

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(30, 30, 1000, 1000);
        window.setVisible(true);

        player = new DrawCell(new int[]{0, 0}, DrawCell.cellType.PLAYER);
        window.getContentPane().add(player);
    }
    public void renderPlayer(int[] pos) {
        pos = new int[]{pos[0] * step, pos[1] * step};

        player.setNewPosition(pos);
        window.setVisible(true);
    }


    public void renderBoard(int[][] board){
        int pos[];
        for (int y = 0; y < board[0].length; y++){
            for (int x = 0; x < board.length; x++){
                pos = new int[]{x * step, y * step};
                if (board[x][y] == 0){
                    window.getContentPane().add(new DrawCell(pos, DrawCell.cellType.WALL));
                    window.setVisible(true);
                } else{
                    window.getContentPane().add(new DrawCell(pos, DrawCell.cellType.PATH));
                    window.setVisible(true);
                }
            }
        }
    }

    public JFrame getFrame(){
        return this.window;
    }
}
