import java.awt.*;
import screen.CanvasTools;

/**
 * @author Jonathon Brandt on 9/11/17.
 * @project Zombies
 */
public class HealthBar {
    private Player player;

    private final int WIDTH = 20;
    private final int HEIGHT = 15;
    private final Color COLOR = Color.RED;
    // the health bar will be at the top, right side of the canvas
    private final int X_POSITION = CanvasTools.getWidth() - (WIDTH + 10);
    private final int Y_POSITION = 10;
    private final int SPACE_BETWEEN_BOXES = 10;

    /**
     * Creates the health bar object. This will hold a player object so it can see how much health the player has
     *
     * @param _player The player object
     */
    public HealthBar(Player _player){
        player = _player;
    }

    /**
     * Paints the health bar on the top right corner of the canvas
     *
     * @param g2d The graphics we will be using to paint the health bar
     */

    public void paintHealthBar(Graphics2D g2d){
        int xPos = X_POSITION;
        int yPos = Y_POSITION;
        for(int i = 0; i < player.getHealth(); i++){
            g2d.setColor(COLOR);
            g2d.drawRect(xPos, yPos, WIDTH, HEIGHT);
            g2d.fillRect(xPos, yPos, WIDTH, HEIGHT);

            xPos -= SPACE_BETWEEN_BOXES + WIDTH;
        }
    }
}