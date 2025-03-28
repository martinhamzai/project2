/**
 * The class is a LeafNode which stores points inside a QuadTree. LeafNodes may
 * hold 1-3 points except if the points are the same, which in that case can
 * store infinitely many. LeafNodes store the points in an ArrayList.
 * 
 * @author Martin Hamzai and Richmond Southall
 * @version 03-23-2025
 */
public class LeafNode
    implements QuadNode
{

    private ArrayList<KVPair<String, Point>> pairs;

    /**
     * Create a new LeafNode with the specified pair
     * 
     * @param pair
     *            The pair to store in the node
     */
    public LeafNode(KVPair<String, Point> pair)
    {
        pairs = new ArrayList<>();
        pairs.insert(pair);
    }


    /**
     * Insert a new pair into the LeafNode, uses private method same() which
     * checks if all of the points in the lead are the same point
     * 
     * @param insertPair
     *            pair to insert
     * @param x
     *            curr x
     * @param y
     *            curr y
     * @param width
     *            curr width
     * @param height
     *            curr height
     * @return this LeafNode or a new InternalNode if decomposition rule comes
     *             into play
     */
    public QuadNode insert(
        KVPair<String, Point> insertPair,
        int x,
        int y,
        int width,
        int height)
    {

        // check if new node will affect decomp before officially adding it
        ArrayList<KVPair<String, Point>> temp = new ArrayList<>();
        for (int i = 0; i < pairs.size(); i++)
        {
            temp.insert(pairs.get(i));
        }
        temp.insert(insertPair);

        if (temp.size() <= 3 || same(temp))
        {
            pairs = temp;
            return this;
        }

        // if over 3 add the points to internalnode
        InternalNode internal = new InternalNode();
        for (int i = 0; i < temp.size(); i++)
        {
            internal.insert(temp.get(i), x, y, width, height);
        }
        return internal;
    }


    private boolean same(ArrayList<KVPair<String, Point>> list)
    {
        Point ref = list.get(0).value();
        for (int i = 1; i < list.size(); i++)
        {
            Point p = list.get(i).value();
            if (!p.equals(ref))
            {
                return false;
            }
        }
        return true;
    }


    /**
     * Prints out the information in the LeafNode.
     * 
     * @param depth
     *            the current depth
     * @param x
     *            the current x
     * @param y
     *            the current y
     * @param size
     *            the current size
     * @return 1 for the count of nodes
     */
    public int dump(int depth, int x, int y, int size)
    {
        // print spaces based on depth
        for (int i = 0; i < depth; i++)
        {
            System.out.print("  ");
        }
        System.out.println("Node at " + x + ", " + y + ", " + size + ":");

        // print each pair in the array
        for (int i = 0; i < pairs.size(); i++)
        {
            for (int j = 0; j < depth; j++)
            {
                System.out.print("  ");
            }
            System.out.println(pairs.get(i).toString());
        }
        return 1;
    }


    /**
     * Removes a point from the LeafNode
     * 
     * @param p
     *            the point to remove
     * @param x
     *            the current x
     * @param y
     *            the current y
     * @param width
     *            the current width
     * @param height
     *            the current height
     * @return this LeafNode
     */
    @Override
    public QuadNode remove(Point p, int x, int y, int width, int height)
    {
        // check each pair in list and see if match
        for (int i = 0; i < pairs.size(); i++)
        {
            Point current = pairs.get(i).value();
            if (current.equals(p))
            {
                KVPair<String, Point> removed = pairs.remove(i);
                System.out.println(
                    "Point removed: (" + removed.key() + ", " + p.getX() + ", "
                        + p.getY() + ")");
                if (pairs.size() == 0)
                {
                    return EmptyNode.getInstance();
                }
                return this;
            }
        }
        // if not found
        System.out
            .println("Point not found: (" + p.getX() + ", " + p.getY() + ")");
        return this;
    }


    /**
     * Checks the points in this leafNode if the match the regionsearch query.
     * 
     * @param searchX
     *            search boundary
     * @param searchY
     *            search boundary
     * @param searchWidth
     *            search boundary
     * @param searchHeight
     *            search boundary
     * @param currX
     *            the current X
     * @param currY
     *            the current Y
     * @param size
     *            the current size
     * @return 1 for the number of nodes visited
     */
    public int regionSearch(
        int searchX,
        int searchY,
        int searchWidth,
        int searchHeight,
        int currX,
        int currY,
        int size)
    {

        int count = 1; // visited this node

        for (int i = 0; i < pairs.size(); i++)
        {
            Point p = pairs.get(i).value();
            int px = p.getX();
            int py = p.getY();

            // Check if point is inside the search rectangle
            if (px >= searchX && px < searchX + searchWidth && py >= searchY
                && py < searchY + searchHeight)
            {
                System.out.println("Point found: " + pairs.get(i).toString());
            }
        }
        return count;
    }


    /**
     * Search for a point in the LeafNode.
     * 
     * @param p
     *            the point to search for
     * @param x
     *            the current x
     * @param y
     *            the current y
     * @param width
     *            the current width
     * @param height
     *            the current height
     * @return the KVPair or null depending if found.
     */
    @Override
    public
        KVPair<String, Point>
        search(Point p, int x, int y, int width, int height)
    {
        // check all items for a match
        for (int i = 0; i < pairs.size(); i++)
        {
            if (pairs.get(i).value().getX() == p.getX()
                && pairs.get(i).value().getY() == p.getY())
            {
                return pairs.get(i);
            }
        }
        return null;
    }


    /**
     * Gets the pairs stored in this leaf node
     * @return the list of pairs in the LeafNode.
     */
    public ArrayList<KVPair<String, Point>> getPairs()
    {
        return pairs;
    }


    /**
     * adds any dups in this node to the duplicates list
     */
    public void findDup()
    {
        ArrayList<Point> unique = new ArrayList<>();
        ArrayList<Point> dups = new ArrayList<>();
        for (int i = 0; i < pairs.size(); i++)
        {
            Point p = pairs.get(i).value();
            if (unique.contains(p))
            {
                if (!dups.contains(p))
                {
                    dups.insert(p);
                }
            }
            else
            {
                unique.insert(p);
            }
        }
        for (int k = 0; k < dups.size(); k++)
        {
            System.out.println("(" + dups.get(k).toString() + ")");
        }
    }


    /**
     * returns the count of points
     * 
     * @return num of points
     */
    @Override
    public int getCount()
    {
        return pairs.size();
    }


    /**
     * adds the points in this leaf to the points array
     * 
     * @param points
     *            the array of points
     */
    public void addPoints(ArrayList<KVPair<String, Point>> points)
    {
        for (int i = 0; i < pairs.size(); i++)
        {
            points.insert(pairs.get(i));
        }
    }

}
