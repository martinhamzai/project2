import java.util.Set;

/**
 * InternalNode class that implements the QuadNode interface. The QuadTree uses
 * this type of Node when a LeafNode contains more than 3 Points that are
 * different to subdivide the QuadTree into a smaller area.
 * 
 * @author Martin Hamzai and Richmond Southall
 * @version 2025-03-22
 */
public class InternalNode
    implements QuadNode
{

    private QuadNode nw;
    private QuadNode ne;
    private QuadNode sw;
    private QuadNode se;

    /**
     * Initialize a new InternalNode object
     */
    public InternalNode()
    {
        this.nw = EmptyNode.getInstance();
        this.ne = EmptyNode.getInstance();
        this.sw = EmptyNode.getInstance();
        this.se = EmptyNode.getInstance();
    }


    /**
     * Insert a Point into a specific part of the InternalNode depending on its
     * coordinates
     * 
     * @param pair
     *            Pair to insert
     * @param x
     *            x coordinate to pass to LeafNode
     * @param y
     *            y coordinate to pass to LeafNode
     * @param width
     *            width to pass to LeafNode
     * @param height
     *            height to pass to LeafNode
     * @return this InternalNode
     */
    @Override
    public
        QuadNode
        insert(KVPair<String, Point> pair, int x, int y, int width, int height)
    {

        Point p = pair.value();
        int midX = x + (width / 2);
        int midY = y + (height / 2);

        // insert point to nw, new, sw, or se depending on area.
        if (p.getX() < midX && p.getY() < midY)
        {
            nw = nw.insert(pair, x, y, width / 2, height / 2);
        }
        else if (p.getX() >= midX && p.getY() < midY)
        {
            ne = ne.insert(pair, midX + 1, y, width / 2, height / 2);
        }
        else if (p.getX() < midX && p.getY() >= midY)
        {
            sw = sw.insert(pair, x, midY + 1, width / 2, height / 2);
        }
        else
        {
            se = se.insert(pair, midX + 1, midY + 1, width / 2, height / 2);
        }

        return this;
    }


    /**
     * Looks at all nodes in the InternalNode and calls dump to print out the
     * information about the node.
     * 
     * @param depth
     *            the current depth
     * @param x
     *            the current x value
     * @param y
     *            the current y value
     * @param size
     *            the current width and height
     * @return the number of nodes
     */
    public int dump(int depth, int x, int y, int size)
    {
        // print spaces based on depth
        for (int i = 0; i < depth; i++)
        {
            System.out.print("  ");
        }
        System.out
            .println("Node at " + x + ", " + y + ", " + size + ": Internal");

        int mid = size / 2;
        int count = 1;

        // count up each quadrant
        count += nw.dump(depth + 1, x, y, mid);
        count += ne.dump(depth + 1, x + mid, y, mid);
        count += sw.dump(depth + 1, x, y + mid, mid);
        count += se.dump(depth + 1, x + mid, y + mid, mid);

        return count;
    }


    /**
     * Locates a Point to remove in the InternalNode by examining if it fits the
     * criteria.
     * 
     * @param p
     *            The point to remove
     * @param x
     *            The current x
     * @param y
     *            the current y
     * @param width
     *            the current width
     * @param height
     *            the current height
     * @return this Internal Node
     */
    @Override
    public QuadNode remove(Point p, int x, int y, int width, int height)
    {
        int midX = x + (width / 2);
        int midY = y + (height / 2);

        // check which quadrant to further search in
        if (p.getX() < midX && p.getY() < midY)
        {
            nw = nw.remove(p, x, y, width / 2, height / 2);
        }
        else if (p.getX() >= midX && p.getY() < midY)
        {
            ne = ne.remove(p, midX + 1, y, width / 2, height / 2);
        }
        else if (p.getX() < midX && p.getY() >= midY)
        {
            sw = sw.remove(p, x, midY + 1, width / 2, height / 2);
        }
        else
        {
            se = se.remove(p, midX + 1, midY + 1, width / 2, height / 2);
        }

        
        // if all 4 quadrants are empty nodes
        if (nw instanceof EmptyNode && ne instanceof EmptyNode
            && sw instanceof EmptyNode && se instanceof EmptyNode)
        {
            return EmptyNode.getInstance();
        }

        return this;
    }


    /**
     * checks the quadrants of the InternalNode if they fit in the boundaries of
     * the regionsearch.
     * 
     * @param searchX
     *            regionsearch boundary
     * @param searchY
     *            regionsearch boundary
     * @param searchWidth
     *            regionsearch boundary
     * @param searchHeight
     *            regionsearch bounday
     * @param currX
     *            the current x
     * @param currY
     *            the current y
     * @param size
     *            the current width and height
     * @return the number of nodes visited.
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

        int count = 1;
        int half = size / 2;

        // check each quadrant
        if (intersect(
            searchX,
            searchY,
            searchWidth,
            searchHeight,
            currX,
            currY,
            half,
            half))
        {
            count += nw.regionSearch(
                searchX,
                searchY,
                searchWidth,
                searchHeight,
                currX,
                currY,
                half);
        }

        if (intersect(
            searchX,
            searchY,
            searchWidth,
            searchHeight,
            currX + half,
            currY,
            size - half,
            half))
        {
            count += ne.regionSearch(
                searchX,
                searchY,
                searchWidth,
                searchHeight,
                currX + half,
                currY,
                size - half);
        }

        if (intersect(
            searchX,
            searchY,
            searchWidth,
            searchHeight,
            currX,
            currY + half,
            half,
            size - half))
        {
            count += sw.regionSearch(
                searchX,
                searchY,
                searchWidth,
                searchHeight,
                currX,
                currY + half,
                half);
        }

        if (intersect(
            searchX,
            searchY,
            searchWidth,
            searchHeight,
            currX + half,
            currY + half,
            size - half,
            size - half))
        {
            count += se.regionSearch(
                searchX,
                searchY,
                searchWidth,
                searchHeight,
                currX + half,
                currY + half,
                size - half);
        }

        return count;
    }


    /**
     * Searches for a KVPair in the InternalNode based on the Point value
     * 
     * @param p
     *            The point to search for
     * @param x
     *            the current x
     * @param y
     *            the current y
     * @param width
     *            the current width
     * @param height
     *            the current height
     */
    @Override
    public
        KVPair<String, Point>
        search(Point p, int x, int y, int width, int height)
    {

        int midX = x + (width / 2);
        int midY = y + (height / 2);

        // determine which quadrant the point would be under and step into it
        if (p.getX() < midX && p.getY() < midY)
        {
            return nw.search(p, x, y, width / 2, width / 2);
        }
        else if (p.getX() >= midX && p.getY() < midY)
        {
            return ne.search(p, midX + 1, y, width / 2, width / 2);
        }
        else if (p.getX() < midX && p.getY() >= midY)
        {
            return sw.search(p, x, midY + 1, width / 2, height / 2);
        }
        else
        {
            return se.search(p, midX + 1, midY + 1, width / 2, height / 2);
        }
    }


    private boolean intersect(
        int x1,
        int y1,
        int w1,
        int h1,
        int x2,
        int y2,
        int w2,
        int h2)
    {

        return !(x1 + w1 <= x2 || x2 + w2 <= x1 || y1 + h1 <= y2
            || y2 + h2 <= y1);
    }
    
    /**
     * adds any dups in this node to the duplicates list
     */
    public void findDup() {
        nw.findDup();
        ne.findDup();
        sw.findDup();
        se.findDup();
    }
    
}
