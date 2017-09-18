package pictures;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jonathon Brandt on 9/16/17.
 * @project Zombies
 */
public class Numbers {
    public static final String ONE_FILE_PATH = "One.png";
    public static final String TWO_FILE_PATH = "Two.png";
    public static final String THREE_FILE_PATH = "Three.png";
    public static final String FOUR_FILE_PATH = "Four.png";
    public static final String FIVE_FILE_PATH = "Five.png";
    public static final String SIX_FILE_PATH = "Six.png";
    public static final String SEVEN_FILE_PATH = "Seven.png";
    public static final String EIGHT_FILE_PATH = "Eight.png";
    public static final String NINE_FILE_PATH = "Nine.png";
    public static final String ZERO_FILE_PATH = "Zero.png";

    public static Image getImage(char number){
        java.net.URL url = null;
        if(number == '1'){
            url = PictureTools.class.getResource(ONE_FILE_PATH);
        }
        else if(number == '2'){
            url = PictureTools.class.getResource(TWO_FILE_PATH);
        }
        else if(number == '3'){
            url = PictureTools.class.getResource(THREE_FILE_PATH);
        }
        else if(number == '4'){
            url = PictureTools.class.getResource(FOUR_FILE_PATH);
        }
        else if(number == '5'){
            url = PictureTools.class.getResource(FIVE_FILE_PATH);
        }
        else if(number == '6'){
            url = PictureTools.class.getResource(SIX_FILE_PATH);
        }
        else if(number == '7'){
            url = PictureTools.class.getResource(SEVEN_FILE_PATH);
        }
        else if(number == '8'){
            url = PictureTools.class.getResource(EIGHT_FILE_PATH);
        }
        else if(number == '9'){
            url = PictureTools.class.getResource(NINE_FILE_PATH);
        }
        else if(number == '0'){
            url = PictureTools.class.getResource(ZERO_FILE_PATH);
        }
        ImageIcon ii = new ImageIcon(url);
        Image img = ii.getImage();
        return img;
    }
}
