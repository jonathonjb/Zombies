package triangle;

/**
 * @author Jonathon Brandt on 9/6/17.
 * @project Zombies
 */
public class TestTriangleTools {
    public static void main(String[] args){
        /*int currentX = 200;
        int currentY = 200;

        int targetX = 418;
        int targetY = 250;*/

        int currentX = 5;
        int currentY = 5;

        int targetX = 4;
        int targetY = 4;

        System.out.println(TriangleTools.getDegreesTowardTarget(currentX, currentY, targetX, targetY));
    }
}
