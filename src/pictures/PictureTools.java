package pictures;

import javax.swing.*;
import java.awt.*;

/**
 * @author Jonathon Brandt on 9/5/17.
 * @project Zombies
 *
 * This library simply allows us to store/retrieve the images we need
 */

public class PictureTools {
    private static final String BACKGROUND_PICTURE_PATH = "grass.png";
    private static final String ZOMBIE_PICTURE_PATH = "zombie.png";
    private static final String PLAYER_PICTURE_PATH = "soldier.png";
    private static final String SWORD_PICTURE_PATH = "sword.png";
    private static final String GAME_OVER_PICTURE_PATH = "gameOver.png";

    public static Image getBackgroundPicture(){
        java.net.URL url = PictureTools.class.getResource(BACKGROUND_PICTURE_PATH);
        ImageIcon ii = new ImageIcon(url);
        Image img = ii.getImage();
        return img;
    }

    public static Image getZombiePicture(){
        java.net.URL url = PictureTools.class.getResource(ZOMBIE_PICTURE_PATH);
        ImageIcon ii = new ImageIcon(url);
        Image img = ii.getImage();
        return img;
    }

    public static Image getPlayerPicture(){
        java.net.URL url = PictureTools.class.getResource(PLAYER_PICTURE_PATH);
        ImageIcon ii = new ImageIcon(url);
        Image img = ii.getImage();
        return img;
    }

    public static Image getSwordPicture(){
        java.net.URL url = PictureTools.class.getResource(SWORD_PICTURE_PATH);
        ImageIcon ii = new ImageIcon(url);
        Image img = ii.getImage();
        return img;
    }

    public static Image getGameOverPicture(){
        java.net.URL url = PictureTools.class.getResource(GAME_OVER_PICTURE_PATH);
        ImageIcon ii = new ImageIcon(url);
        Image img = ii.getImage();
        return img;
    }
}