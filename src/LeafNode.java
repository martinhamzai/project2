
public class LeafNode implements QuadNode {

    private KVPair<String, Point> pair;
    
    public LeafNode(KVPair<String, Point> pair) {
        this.pair = pair;
    }
    
    public QuadNode insert(KVPair<String, Point> insertPair, int x, int y, int width, int height) {
        InternalNode internal = new InternalNode();
        internal = (InternalNode) internal.insert(pair, x, y, width, height);
        internal = (InternalNode) internal.insert(insertPair, x, y, width, height);
        return internal;
    }
    
}

