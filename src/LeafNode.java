
public class LeafNode implements QuadNode {

    private ArrayList<KVPair<String, Point>> pairs;
    
    public LeafNode(KVPair<String, Point> pair) {
        pairs = new ArrayList<>();
        pairs.insert(pair);
    }
    
    public QuadNode insert(KVPair<String, Point> insertPair, int x, int y, int width, int height) {
        Point p1 = pairs.get(0).value();
        Point p2 = insertPair.value();  
        
        if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) {
            pairs.insert(insertPair);
            return this;
        }
        
        InternalNode internal = new InternalNode();
        for (int i = 0; i < pairs.size(); i++) {
            internal.insert(pairs.get(i), x, y, width, height);
        }
        internal = (InternalNode) internal.insert(insertPair, x, y, width, height);
        return internal;
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

