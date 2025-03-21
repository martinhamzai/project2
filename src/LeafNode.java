
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
    
}

