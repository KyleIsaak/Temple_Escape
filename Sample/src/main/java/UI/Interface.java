package UI;

import GameLogic.Board;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

public class Interface extends JFrame {

    public Interface(int step, Board board) {
        setLayout(null);
        setTitle("Game");
        PauseScreen pause = new PauseScreen();
        GameScreen game = new GameScreen(step, board, pause);
        JPanel title = new TitleScreen(game);

        pause.setGameScreen(game);
        title.setBounds(0, 0, 1000, 1000);
        game.setBounds(0, 0, 1000, 1000);
        pause.setBounds(0, 0, 1000, 1000);

        getContentPane().add(title);
        getContentPane().add(pause);
        getContentPane().add(game);

        createWindow();

    }

    private void createWindow(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setVisible(true);
    }

}

