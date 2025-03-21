
public class QuadTree {
    
    private QuadNode root;
    
    private final int SIZE = 1024;
    
    
    public QuadTree() {
        root = EmptyNode.getInstance();
    }
    
    public boolean insert(KVPair<String, Point> pair) {
        root = root.insert(pair, 0, 0, SIZE - 1, SIZE - 1);
        return true;
    }
    
    public void dump() {
        root.dump();
    }
}
