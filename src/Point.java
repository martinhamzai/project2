/**
 * Point object class to be used for the PointsProject that resembles Java's
 * Point class.
 * 
 * @author Martin Hamzai and Richmond Southall
 * @version 3-23-2025
 */
public class Point
{

    private int x;
    private int y;

    /**
     * Creates a new point.
     * 
     * @param x
     *            x coordinate
     * @param y
     *            y coordinate
     */
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }


    /**
     * @return the x coordinate
     */
    public int getX()
    {
        return x;
    }


    /**
     * @return the y coordinate
     */
    public int getY()
    {
        return y;
    }


    /**
     * @return the string representation of the point.
     */
    @Override
    public String toString()
    {
        return x + ", " + y;
    }


    /**
     * Returns true if the points are equal
     * 
     * @param p
     *            the point being compared
     * @return true if they are equal
     */
    @Override
    public boolean equals(Object p)
    {
        if (this == p)
        {
            return true;
        }
        if (p == null || getClass() != p.getClass())
        {
            return false;
        }
        Point other = (Point)p;

        return this.getX() == other.getX() && this.getY() == other.getY();
    }
}
