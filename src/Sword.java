import pictures.PictureTools;
import triangle.TriangleTools;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Jonathon Brandt on 9/13/17.
 * @project Zombies
 */
public class Sword extends Weapon {

    private final int SWORD_BOUNDS_SIZE = 5;
    private final int ATTACK_LENGTH = 15;
    private final int FRAMES_PER_ATTACK = 3;
    private int framesLeftForAttack = FRAMES_PER_ATTACK;

    /**
     * Creates a sword object
     *
     * @param _player The player object this weapon will be attached to
     */

    public Sword(Player _player){
        super(_player, PictureTools.getSwordPicture());
    }

    /**
     * Makes the sword perform an attack action. (Moves it forward)
     */

    public void attack(){
        framesLeftForAttack--;
        moveForward();
        if(framesLeftForAttack <= 0){
            player.setAttacking(false);
            framesLeftForAttack = FRAMES_PER_ATTACK;
        }
    }

    /**
     * Makes the sword move forward to perform an attack
     */

    public void moveForward(){
        // The 'legs' array will hold two values.
        // The first value is how much the x value has to change
        // the second value is how much the y value has to change
        int[] legs = TriangleTools.getLegs(getDegrees(), ATTACK_LENGTH);
        /*
        The reason why 'legs[1]' is negative here is because usually the higher the y value is, the higher the object
        is (graphically), however, in this game, the higher the y value is, the lower the object is. The y value of the
        top of the screen is 0 and the y value of the bottom of the screen is the y value of the canvas size.
         */
        setPosition(getX() + legs[0], getY() + -(legs[1]));
    }

    /**
     * This gets the bounds (Rectangle object) of the tip of the sword. The tip of the sword is what damages the
     * enemies
     */

    public Rectangle getAttackBounds(){
        int distanceFromCurrentPosition = (getWidth() / 2) - SWORD_BOUNDS_SIZE; // this should normally be 11

        // The 'legs' array will hold two values.
        // The first value is how much the x value has to change
        // the second value is how much the y value has to change
        int[] legs = TriangleTools.getLegs(getDegrees(), distanceFromCurrentPosition);
        /*
        The reason why 'legs[1]' is negative here is because usually the higher the y value is, the higher the object
        is (graphically), however, in this game, the higher the y value is, the lower the object is. The y value of the
        top of the screen is 0 and the y value of the bottom of the screen is the y value of the canvas size.
         */
        int xPosition = -(SWORD_BOUNDS_SIZE / 2) + getX() + legs[0];
        int yPosition = -(SWORD_BOUNDS_SIZE) + getY() + -(legs[1]);

        return new Rectangle(xPosition, yPosition, SWORD_BOUNDS_SIZE, SWORD_BOUNDS_SIZE);
    }
}
