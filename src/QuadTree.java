
public class QuadTree {
    
    private QuadNode root;
    
    private final int size = 1024;
    
    
    public QuadTree() {
        root = EmptyNode.getInstance();
    }
    
    public boolean insert(KVPair<String, Point> pair) {
        root = root.insert(pair, 0, 0, size - 1, size - 1);
        return true;
    }
    
    public void dump() {
        System.out.println("QuadTree dump:");
        int count = root.dump(0, 0, 0, size);
        System.out.println(count + " quadtree nodes printed");
    }
    
    
    public int regionSearch(int x, int y, int width, int height) {
        return root.regionSearch(x, y, width, height, 0, 0, size);
    }
}
