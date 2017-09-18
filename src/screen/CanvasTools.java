package screen;

/**
 * @author Jonathon Brandt on 9/5/17.
 * @project Zombies
 *
 * This library allows us to access some of the important information about the canvas such as it's height, width,
 * and location
 */

import java.awt.*;

public class CanvasTools {
    private static Dimension  screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    // the width and height of the game screen (not the whole computer screen)
    private static int width = (int)(screenSize.getWidth() * 0.7);
    private static int height = (int)(screenSize.getHeight() * 0.7);

    // where the game screen will be located at on your computer screen
    private static int xLocation = (int)((screenSize.getWidth() - width) / 2);
    private static int yLocation = (int)((screenSize.getHeight() - height) / 2);

    public static int getWidth(){
        return width;
    }

    public static int getHeight(){
        return height;
    }

    public static int getXLocation(){
        return xLocation;
    }

    public static int getYLocation(){
        return yLocation;
    }
}