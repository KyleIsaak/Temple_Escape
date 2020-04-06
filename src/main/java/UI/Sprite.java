package UI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Store sprite image of the game
 */
public class Sprite {
    private static Image controlBackground;
    private static Image controlError;
    private static Image pauseBackground;
    private static Image titleBackground;
    private static Image gameWall;
    private static Image gameSpike;
    private static Image gameLava;
    private static Image gamePath;
    private static Image gamePath2;
    private static Image gameCoin;
    private static Image gameKey;
    private static Image gameLock;
    private static Image gameUnlock;
    private static Image wall_cross;
    private static Image wall_top_right;
    private static Image wall_horizontal;
    private static Image wall_top_left;
    private static Image wall_bottom_right;
    private static Image wall_top;
    private static Image wall_bottom_left;
    private static Image wall;
    private static Image wall_down;
    private static Image wall_right;
    private static Image wall_left;
    private static Image player_up;
    private static Image player_left;
    private static Image player_right;
    private static Image player_down;
    private static Image enemy_right;

    /**
     * Default Constructor
     */
    public Sprite(){
        try {
            controlBackground = ImageIO.read(read("/control.png"));
            controlError = ImageIO.read(read("/control_error.png"));
            pauseBackground = ImageIO.read(read("/pause.png"));
            titleBackground = ImageIO.read(read("/title.png"));
            gameWall = ImageIO.read(read("/wall.png"));
            gameSpike = ImageIO.read(read("/spike.png"));
            gameLava = ImageIO.read(read("/lava.png"));
            gamePath = ImageIO.read(read("/path.png"));
            gamePath2 = ImageIO.read(read("/path2.png"));
            gameCoin = ImageIO.read(read("/coin.png"));
            gameKey = ImageIO.read(read("/key.png"));
            gameLock = ImageIO.read(read("/lock.png"));
            gameUnlock = ImageIO.read(read("/unlock.png"));
            wall_cross = ImageIO.read(read("/wall_cross.png"));
            wall_top_right = ImageIO.read(read("/wall_top_right.png"));
            wall_horizontal = ImageIO.read(read("/wall_horizontal.png"));
            wall_top_left = ImageIO.read(read("/wall_top_left.png"));
            wall_bottom_right = ImageIO.read(read("/wall_bottom_right.png"));
            wall_top = ImageIO.read(read("/wall_top.png"));
            wall_bottom_left = ImageIO.read(read("/wall_bottom_left.png"));
            wall = ImageIO.read(read("/wall.png"));
            wall_down = ImageIO.read(read("/wall_down.png"));
            wall = ImageIO.read(read("/wall.png"));
            wall_right = ImageIO.read(read("/wall_right.png"));
            wall_left = ImageIO.read(read("/wall_left.png"));
            player_up = ImageIO.read(read("/player_up.png"));
            player_left = ImageIO.read(read("/player_left.png"));
            player_right = ImageIO.read(read("/player_right.png"));
            player_down = ImageIO.read(read("/player_down.png"));
            enemy_right = ImageIO.read(read("/enemy_right.png"));

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static InputStream read(String file){
        return Main.class.getResourceAsStream(file);
    }

    public static Image controlBackground(){return controlBackground;}
    public static Image controlError(){return controlError;}
    public static Image pauseBackground(){return pauseBackground;}
    public static Image titleBackground(){return titleBackground;}
    public static Image gameWall() {
        return gameWall;
    }
    public static Image gameSpike() {
        return gameSpike;
    }
    public static Image gameLava() {
        return gameLava;
    }
    public static Image gamePath() {
        return gamePath;
    }
    public static Image gamePath2() {
        return gamePath2;
    }
    public static Image gameCoin() {
        return gameCoin;
    }
    public static Image gameKey() {
        return gameKey;
    }
    public static Image gameLock() {
        return gameLock;
    }
    public static Image gameUnlock() {
        return gameUnlock;
    }
    public static Image wall_cross() {
        return wall_cross;
    }
    public static Image wall_top_right() {
        return wall_top_right;
    }
    public static Image wall_horizontal() {
        return wall_horizontal;
    }
    public static Image wall_top_left() {
        return wall_top_left;
    }
    public static Image wall_bottom_right() {
        return wall_bottom_right;
    }
    public static Image wall_top() {
        return wall_top;
    }
    public static Image wall_bottom_left() {
        return wall_bottom_left;
    }
    public static Image wall() {
        return wall;
    }
    public static Image wall_down() {
        return wall_down;
    }
    public static Image wall_right() {
        return wall_right;
    }
    public static Image wall_left() {
        return wall_left;
    }
    public static Image player_right() {
        return player_right;
    }
    public static Image player_up() {
        return player_up;
    }
    public static Image player_left() {
        return player_left;
    }
    public static Image player_down() {
        return player_down;
    }
    public static Image enemy_right() {
        return enemy_right;
    }
}
