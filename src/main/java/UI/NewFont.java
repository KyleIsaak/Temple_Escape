package UI;

import java.awt.*;
import java.io.InputStream;

/**
 * Store the info of new font.
 */
public class NewFont {
    /**
     * store the font info.
     */
    private Font font;

    /**
     * Constructor.
     */
    public NewFont() {
        try {
            InputStream input = GameScreen.class.getResourceAsStream("/font.ttf");
            System.out.println(input);
            font = Font.createFont(Font.TRUETYPE_FONT, input);
            font = font.deriveFont(18f);
        } catch (Exception e) {
            e.printStackTrace();
            font = new Font("serif", Font.PLAIN, 14);
        }
    }

    /**
     * getter.
     *
     * @return the font of the class.
     */
    public Font getFont() {
        return font;
    }

}
