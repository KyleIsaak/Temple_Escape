package UI;
import GameLogic.Board;



public class TestMain  {
    private static boolean isGameOver = false;
    private static int delay = 200;
    private static int step = 20;
    private static int currentLevel = 1;
    private static Board board;


    public static void main (String[] args) {
        board = new Board(currentLevel);
        new Interface(step, board);

//        JFrame test = new JFrame();
//        JPanel panel = new JPanel();
//        panel.setLayout(new OverlayLayout(panel));
//
//        DrawCell player = new DrawCell(new int[]{0, 0}, DrawCell.cellType.PLAYER);
//        DrawCell player2 = new DrawCell(new int[]{100, 100}, DrawCell.cellType.PLAYER);
//        panel.add(player);
//        panel.add(player2);
//        test.add(panel);
//        test.setSize(1000, 1000);
//
//        test.setVisible(true);
    }

}
