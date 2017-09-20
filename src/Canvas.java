import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import pictures.PictureTools;
import pictures.Numbers;
import screen.CanvasTools;

/**
 * @author Jonathon Brandt on 9/5/17.
 * @project Zombies
 *
 * This is where everything happens. The player, the zombies and the background will be painted and repainted
 * over and over again until the game ends.
 *
 * This class will hold the ZombiesList and a player object.
 */
public class Canvas extends JPanel implements ActionListener, KeyListener {
    ZombiesList zombiesList;
    Player player;

    private final int ROUND_NUMBER_X_POSITION = 10;
    private final int ROUND_NUMBER_Y_POSITION = 10;
    private final int SPACE_BETWEEN_NUMBERS = 0;
    private int round = 0;

    // the number of milliseconds before updating and repainting the JPanel
    Timer timer = new Timer(50, this);

    /**
     * Creates the canvas (which is a JPanel). All of the pictures will be painted here
     */

    public Canvas() {
        player = new Player();
        zombiesList = new ZombiesList();
        //%TEST%
        // zombiesList.spawnZombie(10);
        round = 10;

        timer.start();
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    /**
     * This will paint the JPanel as soon it is created and every time a repaint() method is called.
     * This will paint the player, the zombies and the background picture
     *
     * @param g The Graphics object we will use to paint everything
     */

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        paintBackground(g2d);
        paintRoundNumber(g2d);
        // if the player is alive, the game goes on
        // if not, the game ends and a "Game Over" picture appears
        if (player.getIsAlive()) {
            player.updatePlayer(g2d);
            player.checkAttack(zombiesList);
            zombiesList.paintZombies(g2d);
            zombiesList.chaseTarget(player);

            /*
            TESTS THE WEAPON TIP BOUNDS

            Rectangle rect = player.getWeapon().getAttackBounds();
            int x = (int)player.getWeapon().getAttackBounds().getX();
            int y = (int)player.getWeapon().getAttackBounds().getY();
            int width = (int)player.getWeapon().getAttackBounds().getWidth();
            int height = (int)player.getWeapon().getAttackBounds().getHeight();

            g2d.drawRect(x, y, width, height);*/
        }
        else{
            g2d.drawImage(PictureTools.getGameOverPicture(), 0, 0,
                    CanvasTools.getWidth(), CanvasTools.getHeight(), null);
        }

        // Makes the whole thing smoother
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Paints the background picture on the canvas
     *
     * @param g2d the graphics which allows us to paint the picture on the canvas
     */

    public void paintBackground(Graphics2D g2d) {
        g2d.drawImage(PictureTools.getBackgroundPicture(), 0, 0,
                CanvasTools.getWidth(), CanvasTools.getHeight(), null);
    }

    /**
     * Paints the round number on the upper left corner.
     *
     * @param g2d The graphics we will be using to paint the round number
     */

    public void paintRoundNumber(Graphics2D g2d){
        int currentX = ROUND_NUMBER_X_POSITION;
        int currentY = ROUND_NUMBER_Y_POSITION;
        String[] splitRoundNumber = Integer.toString(round).split("");
        for(int i = 0; i < splitRoundNumber.length; i++){
            Image numberImage = Numbers.getImage(splitRoundNumber[i].charAt(0));

            g2d.drawImage(numberImage, currentX, currentY,null);

            currentX += numberImage.getWidth(null) + SPACE_BETWEEN_NUMBERS;
        }
    }

    /**
     * Updates the canvas during every frame
     * This method will be called every time the current cycle of the Timer ends
     *
     * @param ae ActionEvent not used here
     */
    public void actionPerformed(ActionEvent ae) {
        if (player.getIsAlive()) {
            if (zombiesList.getSize() == 0) {
                roundUp();
            }
            zombiesList.frameUpdate(round);
        }
        repaint();
    }

    /**
     * Performs an action when the user presses certain keys
     *
     * @param ke The KeyEvent
     */

    public void keyPressed(KeyEvent ke) {
        int keyCode = ke.getKeyCode();
        // if left or right is clicked, the player character will rotate left or right.
        // if up for down is clicked, the player character will move forward or backwards.
        if (keyCode == KeyEvent.VK_LEFT) {
            player.turnLeft();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            player.turnRight();
        } else if (keyCode == KeyEvent.VK_DOWN) {
            player.moveBackwards();
        } else if (keyCode == KeyEvent.VK_UP) {
            player.moveForward();
        } else if (keyCode == KeyEvent.VK_SPACE) {
            player.setAttacking(true);
        }
    }

    /**
     * (Not used)
     * Performs an action when certain keys are released
     *
     * @param ke a KeyEvent
     */

    public void keyReleased(KeyEvent ke) {}

    /**
     * (Not used)
     *
     * @param ke a KeyEvent
     */

    public void keyTyped(KeyEvent ke) {}

    /**
     * Increases the round by 1, updates the zombies list
     */

    public void roundUp() {
        round += 1;
        zombiesList.roundUp(round);
    }
}