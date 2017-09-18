import javax.swing.*;
import screen.CanvasTools;

/**
 * @author Jonathon Brandt on 9/5/17.
 * @project Zombies
 */
public class Survive extends JFrame{
    Canvas canvas;

    /**
     * Sets up the GUI's size, location. Makes it closable and visible. Adds the canvas to the GUI.
     */

    public Survive(){
        super("SURVIVE");
        setSize(CanvasTools.getWidth(), CanvasTools.getHeight());
        setLocation(CanvasTools.getXLocation(), CanvasTools.getYLocation());
        setResizable(false);

        // the canvas is a JPanel where the game will take place in
        canvas = new Canvas();
        add(canvas);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * Survive.
     *
     * Starts the game. The player will appear on the middle of the screen and he/she will try to survive for
     * as long as possible. The user will be able to attack the zombies with their weapon using the space
     * bar.
     *
     * @param args Arguments not used
     */

    public static void main(String[] args){
        new Survive();
    }
}