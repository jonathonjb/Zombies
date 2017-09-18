import screen.CanvasTools;
import pictures.PictureTools;

import java.awt.*;

/**
 * @author Jonathon Brandt on 9/5/17.
 * @project Zombies
 */
public class Player extends Creature{
    private final int SPEED = 15;
    private final int ROTATION_SPEED = 45;
    private final int MAX_HEALTH = 10;

    private int xStartingPosition;
    private int yStartingPosition;

    private boolean isAlive;
    private boolean attacking;

    private HealthBar healthBar;
    private Weapon weapon;

    private final int FRAMES_BETWEEN_HEALTH_INCREASE = 70;
    private int framesBeforeNextHealthIncrease = FRAMES_BETWEEN_HEALTH_INCREASE;

    /**
     * Creates the player sprite and set its health, speed, rotation speed, and position
     */

    public Player(){
        setImage(PictureTools.getPlayerPicture());
        setSpeed(SPEED);
        setRotationSpeed(ROTATION_SPEED);
        xStartingPosition = CanvasTools.getWidth() / 2 - getWidth();
        yStartingPosition = CanvasTools.getHeight() / 2 - getHeight();
        setPosition(xStartingPosition, yStartingPosition);
        setHealth(MAX_HEALTH);
        setIsAlive(true);
        setAttacking(false);

        // Sets its weapon and creates a health bar to hold
        setWeapon(new Sword(this));
        healthBar = new HealthBar(this);

        setPosition(xStartingPosition, yStartingPosition);
    }

    /**
     * Paints the player on the canvas at its position (x & y positions)
     * @param g2d The graphics we will be using to paint the player
     */

    public void paintPlayer(Graphics2D g2d){
        /*
        Goes to the position the player character is supposed to be at, rotates the canvas according to the degree
        the player character is supposed to have, paints the character, reverts the degrees back to normal and reverts
        the screen position back to (0, 0)
         */
        if(getIsAlive()){
            paintSprite(g2d);
            weapon.updateWeapon(g2d);

            healthBar.paintHealthBar(g2d);
        }
    }

    /**
     * Sets whether the player is alive or not
     * @param _isAlive whether the player is alive or not
     */

    public void setIsAlive(boolean _isAlive){
        isAlive = _isAlive;
    }

    /**
     * Sets the player current weapon
     *
     * @param _weapon weapon object
     */
    public void setWeapon(Weapon _weapon){
        weapon = _weapon;
    }

    /**
     * Checks if a player is alive or not
     * @return whether the player is alive or not
     */

    public boolean getIsAlive(){
        return isAlive;
    }

    /**
     * Gets the player's current weapon
     *
     * @return the weapon object
     */

    public Weapon getWeapon(){
        return  weapon;
    }

    /**
     * Updates the player's health and paints it on the canvas
     *
     * @param g2d graphics which will be used to paint the player
     */

    public void updatePlayer(Graphics2D g2d){
        if(getHealth() != MAX_HEALTH){
            if(framesBeforeNextHealthIncrease == 0){
                increaseHealth();
                framesBeforeNextHealthIncrease = FRAMES_BETWEEN_HEALTH_INCREASE;
            }
            else{
                framesBeforeNextHealthIncrease--;
            }
        }
        paintPlayer(g2d);
    }

    /**
     * gets whether or not the player is attacking
     *
     * @return whether or not the player is attacking
     */

    public boolean getAttacking() {
        return attacking;
    }

    /**
     * Sets whether the player is attacking or not
     *
     * @param _attacking whether the player is attacking or not
     */

    public void setAttacking(boolean _attacking) {
        attacking = _attacking;
    }

    /**
     * Checks to seee if the player's weapon collides with any of the zombies. If it does, the zombie takes a damnage.
     * If the zombie's health is at 0, then it will be removed from the zombies list, thus, removed from the game.
     *
     * @param _list The zombies list
     */

    public void checkAttack(ZombiesList _list){
        if(getAttacking()) {
            for (int i = 0; i < _list.getSize(); i++) {
                if(getWeapon().collides(_list.getZombie(i))){
                    _list.getZombie(i).damageHealth();
                    if(_list.getZombie(i).getHealth() == 0){
                        _list.removeZombie(i);
                    }
                }
            }
        }
    }
}