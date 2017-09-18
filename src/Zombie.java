import java.awt.*;
import java.util.*;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import screen.CanvasTools;
import pictures.PictureTools;
import triangle.TriangleTools;

/**
 * @author Jonathon Brandt on 9/6/17.
 * @project Zombies
 */
public class Zombie extends Creature{
    private final int MAX_SPEED = 3;
    private final int MIN_SPEED = 1;
    private final int SPEED_RANGE = 1;
    private final int NUM_OF_ROUNDS_SPEED_INCREASE = 2;

    private final int MAX_HEALTH = 3;
    private final int MIN_HEALTH = 1;
    private final int NUM_OF_ROUNDS_HEALTH_INCREASE = 3;

    private final int MAX_ROTATION_SPEED = 3;
    private final int MIN_ROTATION_SPEED = 1;
    private final int ROTATION_SPEED_RANGE = 2;
    private final int NUM_OF_ROUNDS_ROTATION_SPEED_INCREASE = 2;

    private final int SPAWN_BUFFER_ZONE = 50;

    private final int FRAMES_BETWEEN_ATTACKS = 10;
    private int framesBeforeNextAttack = 0;

    private Random random = new Random();

    /**
     * Creates a zombie and calculates it's health, speed and rotation speed using the current round
     *
     * @param _round The current round
     */

    public Zombie(int _round){
        setDegrees(0);
        setImage(PictureTools.getZombiePicture());
        setHealth(calcHealth(_round));
        setSpeed(calcSpeed(_round));
        setRotationSpeed(calcRotationSpeed(_round));
        spawn();
    }

    /**
     * The zombie will rotate toward a target sprite and then move forward toward it
     *
     * @param _target The target sprite
     */

    public void chase(Sprite _target){
        int rotation = calcRotation(_target);
        setDegrees(calcNextDegree(rotation));
        moveForward();
    }

    /**
     * Spawns the zombie in the game outside of the canvas. It can spawn above the canvas, on the left side of the
     * canvas, on the right side or the bottom of the canvas. It randomly generates where the zombie will spawn.
     */

    public void spawn(){
        int randSide = random.nextInt(4);
        int xPos = 0;
        int yPos = 0;
        switch(randSide){
            case 0:
                // Spawns from the left side of the screen
                yPos = random.nextInt(CanvasTools.getHeight() - getImage().getHeight(null));
                xPos = -SPAWN_BUFFER_ZONE;
                // degree is at 0 by default
                break;
            case 1:
                // Spawns from the top side of the screen
                xPos = random.nextInt(CanvasTools.getWidth() - getImage().getWidth(null));
                yPos = -SPAWN_BUFFER_ZONE;
                setDegrees(90);
                break;
            case 2:
                // Spawns from the right side of the screen
                yPos = random.nextInt(CanvasTools.getHeight() - getImage().getHeight(null));
                xPos = CanvasTools.getWidth() + SPAWN_BUFFER_ZONE - getImage().getWidth(null);
                setDegrees(180);
                break;
            case 3:
                // Spawns from the bottom side of the screen
                xPos = random.nextInt(CanvasTools.getWidth() - getImage().getWidth(null));
                yPos = CanvasTools.getHeight() + 50 -  getImage().getHeight(null);
                setDegrees(270);
                break;
        }
        setPosition(xPos, yPos);
    }

    /**
     * Calculates the degrees of the current zombie after rotating
     * @param _rotation The total rotation of the zombie
     * @return The degrees of the current zombie after rotating
     */

    public int calcNextDegree(int _rotation){
        return TriangleTools.correctDegrees(getDegrees() + _rotation);
    }

    /**
     * Calculates how much the zombie will rotate (it will rotate as much as it can toward the targeted sprite)
     * @param _sprite The sprite/object it is targeting
     * @return The degrees it will rotate
     */

    public int calcRotation(Sprite _sprite){
        int degreesTowardSprite = TriangleTools.getDegreesTowardTarget(getX(), getY(), _sprite.getX(), _sprite.getY());
        int degreesDifference = TriangleTools.differenceBetweenTwoDegrees(getDegrees(), degreesTowardSprite);
        if(Math.abs(degreesDifference) > getRotationSpeed()){
            if(degreesDifference > 0){
                return getRotationSpeed();
            }
            else{
                return -getRotationSpeed();
            }
        }
        else{
            return degreesDifference;
        }
    }

    /**
     * Calculates the health of the current zombie using the round number
     * @param _round The current round number
     * @return The calculated health of the current zombie
     */

    public int calcHealth(int _round){
        // calculates the health of the zombie. Can only be 1-3
        int remainder = _round % NUM_OF_ROUNDS_HEALTH_INCREASE;
        _round -= remainder;
        int health = (_round / NUM_OF_ROUNDS_HEALTH_INCREASE) + MIN_HEALTH;
        if(health > MAX_HEALTH){
            return MAX_HEALTH;
        }
        return health;
    }

    /**
     * Calculates the speed of the current zombie using the round number
     * @param _round The current round number
     * @return The calculated speed of the current zombie
     */

    public int calcSpeed(int _round){
        int remainder = _round % NUM_OF_ROUNDS_SPEED_INCREASE;
        _round -= remainder;
        int maxSpeed = (_round / NUM_OF_ROUNDS_SPEED_INCREASE) + MIN_SPEED;
        if(maxSpeed > MAX_SPEED){
            maxSpeed = MAX_SPEED;
        }
        int minSpeed = maxSpeed - SPEED_RANGE;
        if(minSpeed < MIN_SPEED){
            minSpeed = MIN_SPEED;
        }
        Random random = new Random();
        return random.nextInt(maxSpeed - minSpeed + 1) + minSpeed;
    }

    /**
     * Calculates the rotation of the current zombie using the round number
     * @param _round The current round number
     * @return The calculated rotation speed of the current zombie
     */

    public int calcRotationSpeed(int _round){
        int remainder = _round % NUM_OF_ROUNDS_ROTATION_SPEED_INCREASE;
        _round -= remainder;
        int maxRotationSpeed = (_round / NUM_OF_ROUNDS_ROTATION_SPEED_INCREASE) + MIN_ROTATION_SPEED;
        if (maxRotationSpeed > MAX_ROTATION_SPEED){
            maxRotationSpeed = MAX_ROTATION_SPEED;
        }
        int minRotationSpeed = maxRotationSpeed - ROTATION_SPEED_RANGE;
        if(minRotationSpeed < MIN_ROTATION_SPEED){
            minRotationSpeed = MIN_ROTATION_SPEED;
        }
        return random.nextInt(maxRotationSpeed - minRotationSpeed + 1) + minRotationSpeed;
    }

    /**
     * Paints the zombie on the canvas at its position (x & y positions)
     * @param g2d The graphics we will be using to paint the zombie
     */

    public void paintZombie(Graphics2D g2d){
        paintSprite(g2d);
    }

    /**
     * Sets the number of frames that must be used before the zombie can attack again
     * This method is used so that the player doesn't die the second the zombie touches them.
     *
     * @param _frames The number of frames before the next attack
     */

    public void setFramesBeforeNextAttack(int _frames){
        framesBeforeNextAttack = _frames;
    }

    /**
     * Gets the number of frames that must be used before the zombie can attack again
     * This method is used so that the player doesn't die the second the zombie touches them.
     *
     * @return The number of frames before the next attack
     */

    public int getFramesBeforeNextAttack(){
        return framesBeforeNextAttack;
    }

    /**
     * Gets the number of frames that are required between attacks.
     *
     * @return the number of frames between attacks
     */

    public int getFramesBetweenAttacks(){
        return FRAMES_BETWEEN_ATTACKS;
    }
}