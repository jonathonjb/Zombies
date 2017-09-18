import triangle.TriangleTools;

import java.awt.*;

/**
 * @author Jonathon Brandt on 9/6/17.
 * @project Zombies
 */
public class Creature extends Sprite{
    private int health;
    private int speed;
    private int rotationSpeed;

    /**
     * Makes the sprite move forward by changing it's x and y values
     */

    public void moveForward(){
        // The 'legs' array will hold two values.
        // The first value is how much the x value has to change
        // the second value is how much the y value has to change
        int[] legs = TriangleTools.getLegs(getDegrees(), getSpeed());
        /*
        The reason why 'legs[1]' is negative here is because usually the higher the y value is, the higher the object
        is (graphically), however, in this game, the higher the y value is, the lower the object is. The y value of the
        top of the screen is 0 and the y value of the bottom of the screen is the y value of the canvas size.
         */
        setPosition(getX() + legs[0], getY() + -(legs[1]));
    }

    /**
     * Makes the sprite move backwards by changing it's x and y values
     */

    public void moveBackwards(){
        // The 'legs' array will hold two values.
        // The first value is how much the x value has to change
        // the second value is how much the y value has to change
        int[] legs = TriangleTools.getLegs(TriangleTools.getOppositeDegrees(getDegrees()), getSpeed());
        /*
        The reason why 'legs[1]' is negative here is because usually the higher the y value is, the higher the object
        is (graphically), however, in this game, the higher the y value is, the lower the object is. The y value of the
        top of the screen is 0 and the y value of the bottom of the screen is the y value of the canvas size.
         */
        setPosition(getX() + legs[0], getY() + -(legs[1]));
    }

    /**
     * Rotates the sprite left using the rotation speed of the sprite
     */

    public void turnLeft(){
        setDegrees(TriangleTools.correctDegrees(getDegrees() + rotationSpeed));
    }

    /**
     * Rotates the sprite right using the rotation speed of the sprite
     */

    public void turnRight(){
        setDegrees(TriangleTools.correctDegrees(getDegrees() - rotationSpeed));
    }

    /**
     * Increases the health of the creature by 1;
     */

    public void increaseHealth() {
        setHealth(getHealth() + 1);
    }

    /**
     * Decreases the health of the creature by 1
     */

    public void damageHealth(){
        setHealth(getHealth() - 1);
    }

    /**
     * Sets the health of the creature
     * @param _health the health
     */

    public void setHealth(int _health){
        health = _health;
    }

    /**
     * Sets the speed (pixels per frame) of the creature
     * @param _speed The speed
     */

    public void setSpeed(int _speed){
        speed = _speed;
    }

    /**
     * Sets the rotation speed of the creature
     * @param _rotationSpeed rotation speed
     */

    public void setRotationSpeed(int _rotationSpeed){
        rotationSpeed = _rotationSpeed;
    }

    /**
     * gets the current health of the creature
     * @return current health of the creature
     */

    public int getHealth(){
        return health;
    }

    /**
     * Gets the current speed of the creature
     * @return speed of the creature
     */

    public int getSpeed(){
        return speed;
    }

    /**
     * Gets the current rotation speed of the creature
     * @return rotation speed of the creature
     */

    public int getRotationSpeed(){
        return rotationSpeed;
    }
}