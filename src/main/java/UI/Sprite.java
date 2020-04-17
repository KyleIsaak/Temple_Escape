package UI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Store sprite image of the game.
 */
public class Sprite {
    private static int NUM_OF_SKIN = 4;
    private static int currentSkin = 0;
    /**
     * store the control background image.
     */
    private static Image controlBackground;
    /**
     * store the control error image.
     */
    private static Image controlError;
    /**
     * store the pause background image.
     */
    private static Image pauseBackground;
    /**
     * store the title background image.
     */
    private static Image titleBackground;
    /**
     * store the game wall image.
     */
    private static Image gameWall;
    /**
     * store the spike image.
     */
    private static Image gameSpike;
    /**
     * store the lava image.
     */
    private static Image gameLava;
    /**
     * store the path image.
     */
    private static Image gamePath;
    /**
     * store the path2 image.
     */
    private static Image gamePath2;
    /**
     * store the coin image.
     */
    private static Image gameCoin;
    /**
     * store the key image.
     */
    private static Image gameKey;
    /**
     * store the lock image.
     */
    private static Image gameLock;
    /**
     * store the unlock image.
     */
    private static Image gameUnlock;
    /**
     * store the wall_cross image.
     */
    private static Image wall_cross;
    /**
     * store the wall_top_right image.
     */
    private static Image wall_top_right;
    /**
     * store the wall_horizontal image.
     */
    private static Image wall_horizontal;
    /**
     * store the wall_top_left image.
     */
    private static Image wall_top_left;
    /**
     * store the wall_bottom_right image.
     */
    private static Image wall_bottom_right;
    /**
     * store the wall_top image.
     */
    private static Image wall_top;
    /**
     * store the wall_bottom_left image.
     */
    private static Image wall_bottom_left;
    /**
     * store the wall image.
     */
    private static Image wall;
    /**
     * store the wall_down image.
     */
    private static Image wall_down;
    /**
     * store the wall_right image.
     */
    private static Image wall_right;
    /**
     * store the wall_left image.
     */
    private static Image wall_left;
    /**
     * store the player_up image.
     */
    private static ArrayList<Image> player_up = new ArrayList<>();
    /**
     * store the player_left image.
     */
    private static ArrayList<Image> player_left = new ArrayList<>();
    /**
     * store the player_right image.
     */
    private static ArrayList<Image> player_right = new ArrayList<>();
    /**
     * store the player_down image.
     */
    private static ArrayList<Image> player_down = new ArrayList<>();
    /**
     * store the enemy_right image.
     */
    private static Image enemy_right;
    /**
     * store the gameOver image.
     */
    private static Image gameOver;
    /**
     * store the next image.
     */
    private static Image next;
    /**
     * store the skin image.
     */
    private static Image skin;
    /**
     * store the skinTitle image.
     */
    private static Image skinTitle;
    /**
     * store the skinTitle image.
     */
    private static Image helpPage;

    /**
     * Default Constructor.
     */
    static {
        try {
            controlBackground = ImageIO.read(read("/control.png"));
            controlError = ImageIO.read(read("/control_error.png"));
            pauseBackground = ImageIO.read(read("/pause.png"));
            titleBackground = ImageIO.read(read("/title.png"));
            gameWall = ImageIO.read(read("/wall.png"));
            gameSpike = ImageIO.read(read("/spike.png"));
            gameLava = ImageIO.read(read("/Lava.png"));
            gamePath = ImageIO.read(read("/path.png"));
            gamePath2 = ImageIO.read(read("/path2.png"));
            gameCoin = ImageIO.read(read("/coin.png"));
            gameKey = ImageIO.read(read("/key.png"));
            gameLock = ImageIO.read(read("/Lock.png"));
            gameUnlock = ImageIO.read(read("/Unlock.png"));
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
            for (int i = 0; i < NUM_OF_SKIN; i++){
                player_up.add(ImageIO.read(read("/player_up" + i + ".png")));
                player_left.add(ImageIO.read(read("/player_left" + i + ".png")));
                player_right.add(ImageIO.read(read("/player_right" + i + ".png")));
                player_down.add(ImageIO.read(read("/player_down" + i + ".png")));
            }
            enemy_right = ImageIO.read(read("/enemy_right.png"));
            gameOver = ImageIO.read(read("/gameOver.png"));
            next = ImageIO.read(read("/next.png"));
            skin = ImageIO.read(read("/skin.png"));
            skinTitle = ImageIO.read(read("/skinTitle.png"));
            helpPage = ImageIO.read(read("/help.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * get the input stream.
     *
     * @param file file name.
     * @return the resources.
     */
    private static InputStream read(String file) {
        return Main.class.getResourceAsStream(file);
    }

    /**
     * getter.
     *
     * @return control background image.
     */
    public static Image controlBackground() {
        return controlBackground;
    }

    /**
     * getter.
     *
     * @return control error image.
     */
    public static Image controlError() {
        return controlError;
    }

    /**
     * getter.
     *
     * @return pause background image.
     */
    public static Image pauseBackground() {
        return pauseBackground;
    }

    /**
     * getter.
     *
     * @return title background image.
     */
    public static Image titleBackground() {
        return titleBackground;
    }

    /**
     * getter.
     *
     * @return game wall image.
     */
    public static Image gameWall() {
        return gameWall;
    }

    /**
     * getter.
     *
     * @return game spike image.
     */
    public static Image gameSpike() {
        return gameSpike;
    }

    /**
     * getter.
     *
     * @return game lava image.
     */
    public static Image gameLava() {
        return gameLava;
    }

    /**
     * getter.
     *
     * @return game path image.
     */
    public static Image gamePath() {
        return gamePath;
    }

    /**
     * getter.
     *
     * @return game path2 image.
     */
    public static Image gamePath2() {
        return gamePath2;
    }

    /**
     * getter.
     *
     * @return game coin image.
     */
    public static Image gameCoin() {
        return gameCoin;
    }

    /**
     * getter.
     *
     * @return game key image.
     */
    public static Image gameKey() {
        return gameKey;
    }

    /**
     * getter.
     *
     * @return game lock image.
     */
    public static Image gameLock() {
        return gameLock;
    }

    /**
     * getter.
     *
     * @return game unlock image.
     */
    public static Image gameUnlock() {
        return gameUnlock;
    }

    /**
     * getter.
     *
     * @return wall_cross image.
     */
    public static Image wall_cross() {
        return wall_cross;
    }

    /**
     * getter.
     *
     * @return wall_top_right image.
     */
    public static Image wall_top_right() {
        return wall_top_right;
    }

    /**
     * getter.
     *
     * @return wall_horizontal image.
     */
    public static Image wall_horizontal() {
        return wall_horizontal;
    }

    /**
     * getter.
     *
     * @return wall_top_left image.
     */
    public static Image wall_top_left() {
        return wall_top_left;
    }

    /**
     * getter.
     *
     * @return wall_bottom_right image.
     */
    public static Image wall_bottom_right() {
        return wall_bottom_right;
    }

    /**
     * getter.
     *
     * @return wall_top image.
     */
    public static Image wall_top() {
        return wall_top;
    }

    /**
     * getter.
     *
     * @return wall_bottom_left image.
     */
    public static Image wall_bottom_left() {
        return wall_bottom_left;
    }

    /**
     * getter.
     *
     * @return wall image.
     */
    public static Image wall() {
        return wall;
    }

    /**
     * getter.
     *
     * @return wall_down image.
     */
    public static Image wall_down() {
        return wall_down;
    }

    /**
     * getter.
     *
     * @return wall_right image.
     */
    public static Image wall_right() {
        return wall_right;
    }

    /**
     * getter.
     *
     * @return wall_left image.
     */
    public static Image wall_left() {
        return wall_left;
    }

    public static void skinNext(){
        currentSkin++;
    }

    public static void skinPrevious(){
        if (currentSkin == 0){
            currentSkin = 2 * NUM_OF_SKIN - 1;
        }
        currentSkin--;
    }
    /**
     * getter.
     *
     * @return player_right image.
     */
    public static Image player_right() {
        return player_right.get(currentSkin % NUM_OF_SKIN);
    }

    /**
     * getter.
     *
     * @return player_up image.
     */
    public static Image player_up() {
        return player_up.get(currentSkin % NUM_OF_SKIN);
    }

    /**
     * getter.
     *
     * @return player_left image.
     */
    public static Image player_left() {
        return player_left.get(currentSkin % NUM_OF_SKIN);
    }

    /**
     * getter.
     *
     * @return player_down image.
     */
    public static Image player_down() {
        return player_down.get(currentSkin % NUM_OF_SKIN);
    }

    /**
     * getter.
     *
     * @return enemy_right image.
     */
    public static Image enemy_right() {
        return enemy_right;
    }

    /**
     * getter.
     *
     * @return gameOver image.
     */
    public static Image gameOver() {
        return gameOver;
    }

    /**
     * getter.
     *
     * @return next image.
     */
    public static Image next() {
        return next;
    }

    /**
     * getter.
     *
     * @return skin image.
     */
    public static Image skin() {
        return skin;
    }
    /**
     * getter.
     *
     * @return skin image.
     */
    public static Image skinTitle() {
        return skinTitle;
    }
    /**
     * getter.
     *
     * @return skin image.
     */
    public static Image help() {
        return helpPage;
    }

}
