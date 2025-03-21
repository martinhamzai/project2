import java.util.Iterator;

/**
 * This class is responsible for interfacing between the command processor, the 
 * SkipList, and the PRQuadTree. The responsibility of this class is to further 
 * interpret variations of commands and do some error checking of those 
 * commands. This class further interpreting the command means that the two 
 * types of remove will be overloaded methods for if we are removing by name or 
 * by coordinates. Many of these methods will simply call the appropriate 
 * version of the SkipList and/or QuadTree method after some preparation.
 * 
 * @author Martin Hamzai and Richmond Southall
 * @version 03-22-2025
 */
public class Database {

    private SkipList<String, Point> list;
    private QuadTree qt;
    private final int size = 1024;

    /**
     * The constructor for this class initializes a SkipList object with String
     * and Rectangle a its parameters and a QuadTree object.
     */
    public Database() {
        list = new SkipList<String, Point>();
        qt = new QuadTree();
    }


    /**
     * Inserts the KVPair into the SkipList and QuadTree if the name is valid
     * and the points are within the valid region.
     * 
     * @param pair
     *          The KVPair to be inserted.
     */
    public void insert(KVPair<String, Point> pair) {
        String name = pair.key();
        Point p = pair.value();
        String regex = "^[A-Za-z]\\w*$";

        if (!name.matches(regex)) {
            System.out.println("Point rejected: " + pair.toString());
            return;
        }

        if (p.getX() < 0 || p.getX() >= size || p.getY() < 0 || p
            .getY() >= size) {
            System.out.println("Point rejected: " + pair.toString());
            return;
        }
        
        list.insert(pair);
        qt.insert(pair);
        System.out.println("Point inserted: " + pair.toString());
    }


    /**
     * Removes a KVPair from the SkipList and QuadTree by its key.
     * @param name
     *          The key of the KVPair to remove.
     */
    public void remove(String name) {
        list.remove(name);
        //qt.remove(pair.value());
    }


    /**
     * Removes a KVPair from the SkipList and QuadTree by its value.
     * @param p
     *      The value of the KVPair to remove.
     */
    public void remove(Point p) {
        qt.remove(p);
        //list.remove(pair.key());
    }


    /**
     * Prints out all points inside the query rectangle.
     * 
     * @param x
     *      The starting x of the rectangle
     * @param y
     *      The starting y of the rectangle
     * @param w
     *      The width of the rectangle
     * @param h
     *      The height of the rectangle
     */
    public void regionsearch(int x, int y, int w, int h) {
        qt.regionSearch(x, y, w, h);
    }


    /**
     * Prints out all duplicate points inside the QuadTree.
     */
    public void duplicates() {
        System.out.println("Duplicates");
    }


    /**
     * Searches for a point by name inside the SkipList.
     * @param name
     */
    public void search(String name) {
        ArrayList<KVPair<String, Point>> pairs = list.search(name);
        if (pairs.size() == 0) {
            System.out.println("Point not found: " + name);
        }
        for (int i = 0; i < pairs.size(); i++) {
            KVPair<String, Point> pair = pairs.get(i);
            System.out.println("Found " + pair.toString());
        }
    }
 

    /**
     * Prints out a dump of the SkipList and QuadTree containing information
     * about the nodes and points themselves.
     */
    public void dump() {
        list.dump();
        qt.dump();
    }

}
