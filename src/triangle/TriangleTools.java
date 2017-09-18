package triangle;

/**
 * @author Jonathon Brandt on 9/6/17.
 * @project Zombies
 */
public class TriangleTools {

    /**
     * This method is used to find out how many pixels the x and y needs to move by. The hypotenuse is the
     * length of the sprite moving forward. (Ex: sprite is at 45 degrees and moves forward 10 [hypotenuse],
     * the x and y will both increase by a calculated amount of 8)
     *
     * This method will return an array which will hold two values. The first value is the amount the x needs to
     * change and the second value is the amount the y needs to change.
     *
     * @param _degrees The degrees the sprite is facing toward
     * @param _hypotenuseSide The length of the hypotenuse side (in this case, how far forward the sprite will
     *                        be moving
     * @return An array which will contain the x change and the y change
     */

    public static int[] getLegs(int _degrees, int _hypotenuseSide){
        double test = Math.cos(Math.toRadians(_degrees)) * _hypotenuseSide;
        int adjacentSide = (int)Math.round(Math.cos(Math.toRadians(_degrees)) * _hypotenuseSide);
        int oppositeSide = (int)Math.round(Math.sin(Math.toRadians(_degrees)) * _hypotenuseSide);
        return new int[] {adjacentSide, oppositeSide};
    }
    
    /**
     * This method will be used when we need to know how many degrees a sprite needs to turn in order to face
     * it's target using the current position of the sprite (x & y values) and the position of it's
     * target (x & y values)
     *
     * @param _currentSpriteX x value of the current sprite
     * @param _currentSpriteY y value of the current sprite
     * @param _targetX x value of the target
     * @param _targetY y value of the target
     * @return the amount of degrees the sprite needs to turn before facing its target
     */

    public static int getDegreesTowardTarget(int _currentSpriteX, int _currentSpriteY, int _targetX, int _targetY){
        int xDistance = _targetX - _currentSpriteX;
        int yDistance = _targetY - _currentSpriteY;
        return (int)-Math.toDegrees(Math.atan2(yDistance, xDistance));
    }

    public static int correctDegrees(int _degrees){
        if(_degrees < 0){
            return 360 + _degrees;
        }
        else if(_degrees >= 360){
            return _degrees - 360;
        }
        else{
            return _degrees;
        }
    }

    public static int differenceBetweenTwoDegrees(int _currentDegrees, int _targetedDegrees){
        int leftTurnAmount;
        int rightTurnAmount;
        if(_targetedDegrees >= _currentDegrees){
            leftTurnAmount = _targetedDegrees - _currentDegrees;
            rightTurnAmount = -((_currentDegrees + 360) - _targetedDegrees);
        }
        else{
            leftTurnAmount = (_targetedDegrees + 360) - _currentDegrees;
            rightTurnAmount = -(_currentDegrees - _targetedDegrees);
        }
        if(Math.abs(rightTurnAmount) < leftTurnAmount){
            return rightTurnAmount;
        }
        else{
            return leftTurnAmount;
        }
    }

    public static int getOppositeDegrees(int _degrees){
        return correctDegrees(_degrees - 180);
    }

    public static int getHypotenues(int _legOne, int _legTwo){
        return (int)Math.round(Math.sqrt(Math.pow(_legOne, 2) + Math.pow(_legTwo, 2)));
    }
}