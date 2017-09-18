import java.awt.*;
import java.util.*;

/**
 * @author Jonathon Brandt on 9/5/17.
 * @project Zombies
 *
 * This class holds the info of all of the zombies that are currently in the game.
 */
public class ZombiesList {
    private ArrayList<Zombie> zombiesList;
    private int zombiesLeftToSpawn;

    private final int NUM_OF_ZOMBIES_TO_BEGIN_WITH = 2;
    private final double ZOMBIES_INCREASE_PER_ROUND = 1.25;

    private final int ZOMBIES_SPAWNED_RIGHT_AWAY = 5;
    private final int MAX_ZOMBIES_AT_A_TIME = 20;

    private final int MIN_FRAMES_BETWEEN_SPAWNS = 50;
    private final int MAX_FRAMES_BETWEEN_SPAWNS = 200;
    int framesTillNextSpawn = 0;

    Random random = new Random();

    /**
     * Creates a list which holds all of the zombies
     */

    public ZombiesList(){
        zombiesList = new ArrayList<>();
    }

    /**
     * Paints each of the zombies that are currently on the list (if they are on the list, it means they are alive
     * and in the game) using the Graphics2D object
     *
     * @param g2d Graphics2D object used to paint the zombies
     */

    public void paintZombies(Graphics2D g2d){
        for(Zombie zombie : zombiesList){
            zombie.paintZombie(g2d);
        }
    }

    /**
     * Makes each of the zombies chase a target sprite (typically the human player)
     *
     * @param _target The targeted sprite
     */

    public void chaseTarget(Player _target){
        for(Zombie zombie : zombiesList){
            if(!collides(zombie, _target)){
                zombie.chase(_target);
                zombie.setFramesBeforeNextAttack(0);
            }
            else{
                // this prevents the player from losing their entire health the second the zombie hits them
                // if a zombie harms the player, it has to wait a certain number of frames before attacking again
                if(zombie.getFramesBeforeNextAttack() == 0) {
                    _target.damageHealth();
                    if(_target.getHealth() == 0){
                        _target.setIsAlive(false);
                    }
                    zombie.setFramesBeforeNextAttack(zombie.getFramesBetweenAttacks());
                }
                else{
                    zombie.setFramesBeforeNextAttack(zombie.getFramesBeforeNextAttack() - 1);
                }
            }
        }
    }

    /**
     * Checks to see if the zombie's bounds collides with the target's bounds
     *
     * @param _zombie The zombie object
     * @param _target A sprite object
     * @return Whether or not those two objects collides on the canvas
     */

    public boolean collides(Zombie _zombie, Sprite _target){
        Rectangle targetBounds = new Rectangle(_target.getX() - (_target.getWidth() / 2),
                _target.getY() - (_target.getHeight() / 2),
                _target.getWidth(),
                _target.getHeight());

        Rectangle zombieBounds = new Rectangle(_zombie.getX() - (_zombie.getWidth() / 2),
                _zombie.getY() - (_zombie.getHeight() / 2),
                _zombie.getWidth(),
                _zombie.getHeight());

        return targetBounds.intersects(zombieBounds);
    }

    /**
     * Spawn a zombie into the game by adding a new zombie object to this list
     *
     * @param _round The current round of the game
     */

    public void spawnZombie(int _round){
        zombiesList.add(new Zombie(_round));
        zombiesLeftToSpawn--;
    }

    /**
     * The round of the game increases; will calculates how many zombies will be spawned in this round and
     * then spawns the maximum amount of zombies that can be in the game at a time.
     *
     * @param _round The current round in the game
     */

    public void roundUp(int _round){
        setZombiesLeftToSpawn(((int)(_round * ZOMBIES_INCREASE_PER_ROUND)) + NUM_OF_ZOMBIES_TO_BEGIN_WITH);
        for(int i = 0, totalZombies = zombiesLeftToSpawn; i < totalZombies && i < ZOMBIES_SPAWNED_RIGHT_AWAY; i++){
            spawnZombie(_round);
        }
    }

    /**
     * Updates the zombies list. This occurs every frame in the game. This checks if any additional zombie needs to be
     * spawned.
     *
     * @param _round The current round in the game
     */

    public void frameUpdate(int _round){
        if (zombiesList.size() == MAX_ZOMBIES_AT_A_TIME) {
            framesTillNextSpawn = 0;
        }
        if (framesTillNextSpawn > 0) {
            framesTillNextSpawn--;
            if (framesTillNextSpawn == 0 || zombiesList.size() == 0) {
                spawnZombie(_round);
            }
        }
        if (zombiesLeftToSpawn > 0 && framesTillNextSpawn == 0) {
            framesTillNextSpawn = random.nextInt(MAX_FRAMES_BETWEEN_SPAWNS - MIN_FRAMES_BETWEEN_SPAWNS + 1)
                    + MIN_FRAMES_BETWEEN_SPAWNS;
        }
    }

    /**
     * Sets the number of zombies that needs to be spawned in the current round
     *
     * @param _zombiesLeft The number of zombies that needs to be spawned in the current round
     */

    public void setZombiesLeftToSpawn(int _zombiesLeft){
        zombiesLeftToSpawn = _zombiesLeft;
    }

    /**
     * Gets the size of the zombies list
     *
     * @return size of the zombies list
     */

    public int getSize(){
        return zombiesList.size();
    }

    /**
     * Gets a zombie object from the list
     *
     * @param _index The index zombie object in the list
     * @return A zombie object
     */

    public Zombie getZombie(int _index){
        return zombiesList.get(_index);
    }

    /**
     * Removes a zombie object from the list
     *
     * @param _index The index of the zombie object we will remove from the list
     */

    public void removeZombie(int _index){
        zombiesList.remove(_index);
    }
}