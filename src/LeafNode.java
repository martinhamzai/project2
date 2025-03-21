
public class LeafNode implements QuadNode {

    private ArrayList<KVPair<String, Point>> pairs;
    
    public LeafNode(KVPair<String, Point> pair) {
        pairs = new ArrayList<>();
        pairs.insert(pair);
    }
    
    public QuadNode insert(KVPair<String, Point> insertPair, int x, int y, int width, int height) {
        pairs.insert(insertPair);
        
        if (pairs.size() < 4 || same()) {
            return this;
        }
         
        
        InternalNode internal = new InternalNode();
        for (int i = 0; i < pairs.size(); i++) {
            internal.insert(pairs.get(i), x, y, width, height);
        }
        internal = (InternalNode) internal.insert(insertPair, x, y, width, height);
        return internal;
    }
    
    private boolean same() {
        if (pairs.size() == 0) {
            return true;
        }
        Point p = pairs.get(0).value();
        for (int i = 1; i < pairs.size(); i++) {
            Point compareP = pairs.get(i).value();
            if (p.getX() != compareP.getX() || p.getY() != compareP.getY()) {
                return false;
            }
        }
        return true;
    }
    
    public int dump(int depth, int x, int y, int size) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("Node at " + x + ", " + y + ", " + size + ":");
        for (int i = 0; i < pairs.size(); i++) {
            System.out.println("  " + pairs.get(i).toString());
        }
        return 1;
    }
    
    public ArrayList<KVPair<String, Point>> getList() {
        return pairs;
    }

    @Override
    public
        KVPair<String, Point>
        remove(Point p, int x, int y, int wdith, int height)
    {
        // TODO Auto-generated method stub
        return null;
    }
    
    
    
    
}

