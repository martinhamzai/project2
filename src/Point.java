/**
 * Point object class to be used for the PointsProject that resembles Java's
 * Point class.
 * 
 * @author Martin Hamzai and Richmond Southall
 * @version 2025-03-22
 */
public class Point {

    private int x;
    private int y;

    /**
     * Creates a new point.
     * 
     * @param x
     *       x coordinate
     * @param y
     *       y coordinate
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }


    /**
     * @return the x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y coordinate
     */
    public int getY() {
        return y;
    }


    /**
     * @return the string representation of the point.
     */
    @Override
    public String toString() {
        return x + ", " + y;
    }
    
}
