package UI;

import GameLogic.Board;
import GameLogic.LevelGenerator;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

public class Interface extends JFrame {

    public Interface(int step) {
        setLayout(null);
        setTitle("Game");
        PauseScreen pause = new PauseScreen();
        GameScreen game = new GameScreen(step);
        JPanel title = new TitleScreen(game);
        JPanel misc = new Misc(game, pause);
        NextScreen nextScreen = new NextScreen();

        game.setNextScreen(nextScreen);
        nextScreen.setGameScreen(game);
        pause.setGameScreen(game);
        title.setBounds(0, 0, 1000, 1000);
        game.setBounds(0, 0, 880, 1000);
        pause.setBounds(0, 0, 1000, 1000);
        misc.setBounds(880, 0, 120, 1000);
        nextScreen.setBounds(0, 0, 1000, 1000);

        getContentPane().add(title);
        getContentPane().add(pause);
        getContentPane().add(nextScreen);
        getContentPane().add(game);
        getContentPane().add(misc);

        createWindow();
    }

    private void createWindow(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setVisible(true);
    }

}


