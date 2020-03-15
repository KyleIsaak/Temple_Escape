package UI;

import GameLogic.Board;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

public class Interface extends JFrame {

    public Interface(int step, Board board) {
        setLayout(new OverlayLayout(getContentPane()));

        JPanel game = new GameScreen(step, board);
        JPanel pause = new PauseScreen();
        pause.setAlignmentX(0);
        pause.setAlignmentY(0);
        getContentPane(). add(pause);
        getContentPane().add(game);


        createWindow();

    }

    private void createWindow(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(960, 980);
        setVisible(true);
    }

}

