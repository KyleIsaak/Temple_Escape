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
        ControlScreen control = new ControlScreen();
        PauseScreen pause = new PauseScreen(control);
        GameScreen game = new GameScreen(step);
        JPanel misc = new Misc(game, pause);
        TitleScreen title = new TitleScreen(game, control, misc);
        NextScreen nextScreen = new NextScreen();

        control.setPause(pause);
        control.setGameScreen(game);
        control.setTitle(title);
        game.setNextScreen(nextScreen);

        nextScreen.setGameScreen(game);
        pause.setGameScreen(game);
        title.setBounds(0, 0, 1000, 1000);
        game.setBounds(0, 0, 880, 1000);
        pause.setBounds(0, 0, 1000, 1000);
        misc.setBounds(880, 0, 120, 1000);
        nextScreen.setBounds(0, 0, 1000, 1000);
        control.setBounds(0, 0, 1000, 1000);

        getContentPane().add(control);
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


