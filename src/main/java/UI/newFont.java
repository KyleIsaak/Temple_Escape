package UI;

import java.awt.*;
import java.io.InputStream;

public class newFont {
    private static Font font;

    public newFont(){
        try {
            InputStream input = GameScreen.class.getResourceAsStream("/font.ttf");
            System.out.println(input);
            font = Font.createFont(Font.TRUETYPE_FONT, input);
            font = font.deriveFont(18f);
        } catch (Exception e){
            e.printStackTrace();
            font = new Font("serif", Font.PLAIN, 14);
        }
    }

    public static Font getFont(){
        return font;
    }

}
