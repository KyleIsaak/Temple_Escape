package UI;

import gameLogic.Board;
import gameLogic.LevelGenerator;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * store the screen the the game will show depend on the situation.
 */
public class Interface extends JFrame {
    /**
     * non-default constructor.
     *
     * @param step store how far the charater can move at each time.
     */
    public Interface(int step) {
        setLayout(null);
        setTitle("Game");
        new Sprite();
        ControlScreen control = new ControlScreen();
        PauseScreen pause = new PauseScreen(control);
        GameScreen game = new GameScreen(step);
        Misc misc = new Misc(game, pause);
        TitleScreen title = new TitleScreen(game, control, misc, this);
        HelpScreen helpScreen = new HelpScreen(title, game);
        NextScreen nextScreen = new NextScreen();
        EndScreen endScreen = new EndScreen(title);
        SkinScreen skinScreen = new SkinScreen(title);

        control.setPause(pause);
        control.setGameScreen(game);
        control.setTitle(title);
        game.setMisc(misc);
        game.setEnd(endScreen);
        game.setNextScreen(nextScreen);
        nextScreen.setGameScreen(game);
        pause.setTitle(title);
        pause.setGameScreen(game);
        pause.setMisc(misc);
        title.setSkinScreen(skinScreen);
        title.setHelp(helpScreen);
        misc.setHelpScreen(helpScreen);


        title.setBounds(0, 0, 1000, 1000);
        game.setBounds(0, 0, 880, 1000);
        pause.setBounds(0, 0, 1000, 1000);
        misc.setBounds(880, 0, 120, 1000);
        nextScreen.setBounds(0, 0, 1000, 1000);
        control.setBounds(0, 0, 1000, 1000);
        endScreen.setBounds(0, 0, 1000, 1000);
        skinScreen.setBounds(0, 0, 1000, 1000);
        helpScreen.setBounds(0, 0, 1000, 1000);

        getContentPane().add(helpScreen);
        getContentPane().add(skinScreen);
        getContentPane().add(endScreen);
        getContentPane().add(control);
        getContentPane().add(title);
        getContentPane().add(pause);
        getContentPane().add(nextScreen);
        getContentPane().add(game);
        getContentPane().add(misc);

        createWindow();
    }

    /**
     * create the size of the window pop up when start run the program.
     */
    private void createWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1010, 1010);
        setVisible(true);
    }

}


