
public class LeafNode implements QuadNode {

    private KVPair<String, Point> pair;
    
    public LeafNode(KVPair<String, Point> pair) {
        this.pair = pair;
    }
    
    public QuadNode insert(KVPair<String, Point> insertPair, int xMin, int xMax, int yMin, int yMax) {
        InternalNode internal = new InternalNode();
        internal =  internal.insert(pair, xMin, xMax, yMin, yMax);
        internal = internal.insert(insertPair, xMin, xMax, yMin, yMax);
        return internal;
    }

