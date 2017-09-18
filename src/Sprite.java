import java.awt.*;
import screen.CanvasTools;

import javax.swing.*;

/**
 * @author Jonathon Brandt on 9/6/17.
 * @project Zombies
 *
 * A sprite is an object in the game. In this game, a sprite can be a Creature (a player or a zombie) or a weapon.
 * This class holds all of the general attributes of a sprite.
 */
public class Sprite {
    private Image image;
    private int x;
    private int y;
    private int degrees;

    /**
     * Sets the x position and the y position of the sprite (at the middle of the sprite picture)
     *
     * @param _x X position
     * @param _y Y position
     */
    public void setPosition(int _x, int _y){
        x = _x;
        y = _y;
    }

    /**
     * Set the degrees the sprite is facing toward. (0 degrees = facing right, 90 degrees = facing north,
     * 180 degrees = facing left, 270 degrees = facing south, etc...)
     *
     * @param _degrees The degrees the sprite will be facing
     */

    public void setDegrees(int _degrees){
        degrees = _degrees;
    }

    /**
     * Sets the image of the sprite
     *
     * @param _image The image
     */

    public void setImage(Image _image){
        image = _image;
    }

    public int getX(){
        return x;
    }

    /**
     * Gets the y position of the middle of the sprite
     * @return the y position
     */

    public int getY(){
        return y;
    }

    /**
     * Gets the degrees the sprite is facing toward
     * @return The degrees
     */

    public int getDegrees(){
        return degrees;
    }

    /**
     * Gets the image of the sprite
     * @return the image of the sprite
     */

    public Image getImage(){
        return image;
    }

    /**
     * Gets the width of the sprite
     * @return the width of the sprite
     */

    public int getWidth(){
        return getImage().getWidth(null);
    }

    /**
     * Gets the height of the sprite
     * @return the height of the sprite
     */

    public int getHeight(){
        return getImage().getHeight(null);
    }

    /**
     * Paints the sprite on the canvas
     *
     * @param g2d The graphics object we will be using to paint the sprite
     */

    public void paintSprite(Graphics2D g2d){
        g2d.translate(getX(), getY());
        g2d.rotate(Math.toRadians(-getDegrees()));
        g2d.drawImage(getImage(), -(getWidth() / 2), -(getHeight() / 2), null);
        g2d.rotate(Math.toRadians(getDegrees()));
        g2d.translate(-getX(), -getY());
    }
}