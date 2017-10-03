import java.awt.*;
import triangle.TriangleTools;

/**
 * @author Jonathon Brandt on 9/13/17.
 * @project Zombies
 */
public abstract class Weapon extends Sprite {
    private final int X_BUFFER = 10;

    protected Player player;
    private int distanceFromPlayer;
    private int degreesFromPlayer;

    /**
     * Constructor; creates a new weapon.
     *
     * @param _player The player object; this will be used to determine where the weapon is located on the canvas
     * @param _image The image of the weapon
     */

    public Weapon(Player _player, Image _image){
        setImage(_image);
        player = _player;

        double adjacent = (player.getHeight() / 2) + (getHeight() / 2);
        double opposite = X_BUFFER;
        distanceFromPlayer = TriangleTools.getHypotenues((int)adjacent, (int)opposite);
        degreesFromPlayer = 360 - (90 - (int)Math.round(Math.toDegrees(Math.atan(opposite / adjacent))));
    }

    /**
     * Updates the current weapon's location before every repaint in the canvas
     *
     * @param g2d The graphics object we will be using to paint the weapon on the canvas
     */

    public void updateWeapon(Graphics2D g2d) {
        calculatePosition();
        if(player.getAttacking()){
            attack();
        }
        paintWeapon(g2d);
    }

    /**
     * Paints the weapon on the canvas
     *
     * @param g2d the graphics object we will be using to paint the weapon
     */

    public void paintWeapon(Graphics2D g2d){
        paintSprite(g2d);
    }

    /**
     * Calculates the position of the weapon on the canvas
     */

    public void calculatePosition(){
        setDegrees(player.getDegrees());

        // The 'legs' array will hold two values.
        // The first value is how much the x value has to change
        // the second value is how much the y value has to change
        int[] legs = TriangleTools.getLegs(degreesFromPlayer + getDegrees(), distanceFromPlayer);
        /*
        The reason why 'legs[1]' is negative here is because usually the higher the y value is, the higher the object
        is (graphically), however, in this game, the higher the y value is, the lower the object is. The y value of the
        top of the screen is 0 and the y value of the bottom of the screen is the y value of the canvas size.
         */
        setPosition(player.getX() + legs[0], player.getY() + -(legs[1]));
    }

    /**
     * This is a method all weapons (Classes that extends Weapon) will have, and overwrite
     */
    public abstract void attack();

    /**
     * (Does nothing)
     * All Weapons (objects which extends Weapon) has this method
     *
     * @return A rectangle which represents the attack bounds of the current weapon
     */

    public abstract Rectangle getAttackBounds();

    /**
     * Checks if the current weapon bounds collides with a sprite
     *
     * @param _sprite The sprite we are checking if the weapon is colliding with
     * @return whether the weapon is colliding with the sprite
     */

    public boolean collides(Sprite _sprite){
        Rectangle zombieBounds = new Rectangle(_sprite.getX() - (_sprite.getWidth() / 2),
                _sprite.getY() - (_sprite.getHeight() / 2),
                _sprite.getWidth(),
                _sprite.getHeight());

        if(this.getAttackBounds().intersects(zombieBounds)){
            return true;
        }
        return false;
    }
}