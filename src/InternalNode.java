/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 * 
 * @author 18046
 * @version Mar 21, 2025
 */
public class InternalNode implements QuadNode {

    public static EmptyNode flyweight = null;

    QuadNode nw, ne, sw, se;

    public InternalNode() {
        this.nw = this.ne = this.sw = this.se = EmptyNode.getInstance();
    }


    @Override
    public QuadNode insert(
        KVPair<String, Point> pair,
        int x,
        int y,
        int width,
        int height) {
        Point p = pair.value();
        int midX = (x + width) / 2;
        int midY = (y + height) / 2;

        if (p.getX() <= midX && p.getY() <= midY) {
            nw = nw.insert(pair, x, y, width / 2, height / 2);
        }
        else if (p.getX() > midX && p.getY() <= midY) {
            ne = ne.insert(pair, midX + 1, y, width / 2, height / 2);
        }
        else if (p.getX() <= midX && p.getY() > midY) {
            sw = sw.insert(pair, x, midY + 1, width / 2, height / 2);
        }
        else {
            se = se.insert(pair, midX + 1, midY + 1, width / 2, height / 2);
        }

        return this;
    }


    public int dump(int depth, int x, int y, int size) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("Node at " + x + ", " + y + ", " + size
            + ": Internal");

        int mid = size / 2;
        int count = 1;

        count += nw.dump(depth + 1, x, y, mid);
        count += ne.dump(depth + 1, x + mid, y, mid);
        count += sw.dump(depth + 1, x, y + mid, mid);
        count += se.dump(depth + 1, x + mid, y + mid, mid);

        return count;
    }


    @Override
    public QuadNode remove(
        Point p,
        int x,
        int y,
        int width,
        int height) {
        int midX = (x + width) / 2;
        int midY = (y + height) / 2;

        if (p.getX() <= midX && p.getY() <= midY) {
            nw = nw.remove(p, x, y, width / 2, height / 2);
        }
        else if (p.getX() > midX && p.getY() <= midY) {
            ne = ne.remove(p, midX + 1, y, width / 2, height / 2);
        }
        else if (p.getX() <= midX && p.getY() > midY) {
            sw = sw.remove(p, x, midY + 1, width / 2, height / 2);
        }
        else {
            se = se.remove(p, midX + 1, midY + 1, width / 2, height / 2);
        }

        if (nw instanceof EmptyNode && ne instanceof EmptyNode
            && sw instanceof EmptyNode && se instanceof EmptyNode) {
            return EmptyNode.getInstance();
        }

        return this;
    }


    public int regionSearch(
        int searchX,
        int searchY,
        int searchWidth,
        int searchHeight,
        int currX,
        int currY,
        int size) {

        int count = 1; 
        int half = size / 2;

        if (intersect(searchX, searchY, searchWidth, searchHeight, currX, currY, half, half)) {
            count += nw.regionSearch(searchX, searchY, searchWidth, searchHeight, currX, currY, half);
        }

        if (intersect(searchX, searchY, searchWidth, searchHeight, currX + half, currY, size - half, half)) {
            count += ne.regionSearch(searchX, searchY, searchWidth, searchHeight, currX + half, currY, size - half);
        }

        if (intersect(searchX, searchY, searchWidth, searchHeight, currX, currY + half, half, size - half)) {
            count += sw.regionSearch(searchX, searchY, searchWidth, searchHeight, currX, currY + half, half);
        }

        if (intersect(searchX, searchY, searchWidth, searchHeight, currX + half, currY + half, size - half, size - half)) {
            count += se.regionSearch(searchX, searchY, searchWidth, searchHeight, currX + half, currY + half, size - half);
        }

        return count;
    }
    
    @Override
    public
        KVPair<String, Point>
        search(Point p, int x, int y, int width, int height)
    {
        int midX = (x + width) / 2;
        int midY = (y + height) / 2;

        if (p.getX() <= midX && p.getY() <= midY) {
            return nw.search(p, x, y, midX, midY);
        } else if (p.getX() > midX && p.getY() <= midY) {
            return ne.search(p, midX + 1, y, width, midY);
        } else if (p.getX() <= midX && p.getY() > midY) {
            return sw.search(p, x, midY + 1, midX, height);
        } else {
            return se.search(p, midX + 1, midY + 1, width, height);
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
        int h2) {
        
        return !(x1 + w1 <= x2 || x2 + w2 <= x1 ||
            y1 + h1 <= y2 || y2 + h2 <= y1);
    }

}
